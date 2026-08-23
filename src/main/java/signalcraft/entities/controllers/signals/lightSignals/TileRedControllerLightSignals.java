package signalcraft.entities.controllers.signals.lightSignals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.signals.ISignalReceiver;
import signalcraft.entities.controllers.TileController;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.Arrays;

public class TileRedControllerLightSignals extends TileController implements ILightSignalsController{

    private int[] signalStates = new int[4];
    private static final ResourceLocation TEXTURE = TextureRegistry.RED_CONT_LIGHT_SIGNALS.get();

    public TileRedControllerLightSignals() {
        super(TEXTURE);
        this.setGuiId(Consts.GuiIDs.REDSTONE_CONTROLLER);
        this.setName("Controller");
    }

    public int[] getSignalStates() {
        return signalStates;
    }

    public void changeStateOnAllForSide(int side) {
        this.getPairings().forEach((pairing, id) -> {
            TileEntity tile = worldObj.getTileEntity(pairing.getX(), pairing.getY(), pairing.getZ());
            if (tile instanceof ISignalReceiver) {
                ISignalReceiver receiver = (ISignalReceiver) tile;
                if (Arrays.asList(receiver.getValidStatesForSignal()).contains(getStateOnSide(side))) {
                    receiver.setState(getStateOnSide(side));
                }
            }
        });
    }

    private SignalState getStateOnSide(int side) {
        if (side < 0 || side >= signalStates.length) {
            return null;
        } else {
            return SignalState.fromInteger(signalStates[side]);
        }
    }

    public void setMostRestrictiveOnAll() {
        this.getPairings().forEach((pairing, id) -> {
            TileEntity tile = worldObj.getTileEntity(pairing.getX(), pairing.getY(), pairing.getZ());
            if (tile instanceof ISignalReceiver) {
                ((ISignalReceiver) tile).setStateToSignalsMostRestrictive();
            }
        });
    }

    public void setSignalStates(int[] states) {
        if (states.length == 4) {
            this.signalStates = states;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("Name", this.getName());
        tag.setIntArray("signalStates", signalStates);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setName(tag.getString("Name"));
        if (tag.hasKey("signalStates")) {
            int[] stored = tag.getIntArray("signalStates");
            if (stored != null) {
                if (stored.length == 4) {
                    signalStates = stored;
                } else {
                    signalStates = Arrays.copyOf(stored, 4);
                }
            }
        }
    }
}
