package signalcraft.entities.controllers.signals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.controllers.signals.lightSignals.ILightSignalsController;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.Arrays;

public class TileRedReceiverSignals extends TileReceiver implements ISignalReceiver{

    // Each side now holds a *list* of state ordinals it should react to (comma-separated in the
    // GUI), not just one. Ordinal 0 (ZHAS) is reserved as "unset" — see setCrossingActive() — so it never
    // appears as a real entry.
    private int[][] signalStates = new int[4][0];
    private byte[] outputtingSide = new byte[4];
    private static final ResourceLocation TEXTURE = TextureRegistry.RED_RECE_LIGHT_SIGNALS.get();

    public TileRedReceiverSignals() {
        super(TEXTURE);
        this.setGuiId(Consts.GuiIDs.REDSTONE_RECEIVER);
        this.setName("Receiver");
    }

    public int[][] getSignalStates() {
        return signalStates;
    }

    public byte[] getOutputtingSide() {
        return outputtingSide;
    }

    public void setSignalStates(int[][] states) {
        if (states.length == 4) {
            this.signalStates = states;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("Name", this.getName());
        for (int i = 0; i < signalStates.length; i++) {
            tag.setIntArray("signalStatesSide" + i, signalStates[i]);
        }
        tag.setByteArray("outputtingSide", outputtingSide);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setName(tag.getString("Name"));

        // Pre-multi-state saves stored one ordinal per side under "signalStates" — migrate those
        // into a single-entry list per side when the new per-side keys aren't present yet.
        int[] legacy = tag.hasKey("signalStates") ? tag.getIntArray("signalStates") : null;

        for (int i = 0; i < signalStates.length; i++) {
            String key = "signalStatesSide" + i;
            if (tag.hasKey(key)) {
                int[] stored = tag.getIntArray(key);
                signalStates[i] = stored != null ? stored : new int[0];
            } else if (legacy != null && legacy.length == 4 && legacy[i] != 0) {
                signalStates[i] = new int[]{legacy[i]};
            } else {
                signalStates[i] = new int[0];
            }
        }

        if (tag.hasKey("outputtingSide")){
            byte[] stored = tag.getByteArray("outputtingSide");
            if (stored != null) {
                if (stored.length == 4) {
                    outputtingSide = stored;
                } else {
                    outputtingSide = Arrays.copyOf(stored, 4);
                }
            }
        }
    }

    @Override
    public boolean isControllerValid(TileController controller) {
        return controller instanceof ILightSignalsController;
    }

    @Override
    public void setState(SignalState state) {

        Arrays.fill(outputtingSide, (byte) 0);

        int ordinal = state.ordinal();
        if (ordinal != 0) {
            outer:
            for (int i = 0; i < this.signalStates.length; i++) {
                for (int configured : signalStates[i]) {
                    if (configured == ordinal) {
                        outputtingSide[i] = 1;
                        break outer;
                    }
                }
            }
        }

        if (this.worldObj != null) {
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        }
    }

    @Override
    public SignalState getStateOnSignal() {return null;}

    @Override
    public void setStateToSignalsMostRestrictive() {

        Arrays.fill(outputtingSide, (byte) 0);

        if (this.worldObj != null) {
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        }

    }

    @Override
    public SignalState[] getValidStatesForSignal() {
        return SignalState.values();
    }
}
