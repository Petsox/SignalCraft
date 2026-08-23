package signalcraft.entities.signals.mechSignals;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.SignalCraft;
import signalcraft.entities.TileSignal;
import signalcraft.entities.signals.ISignal;
import signalcraft.messages.MessageStateUpdate;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.List;

public class TileMechSignal extends TileSignal implements ISignal {
    private SignalState MostRestrictiveState;

    public SignalState[] ValidStates;
    private final List<SignalState> everyValidState = new ArrayList<>();
    /** Client-only render state easing lamp brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();
    /**
     * String[] mechanicalSignalProperties - Lenght in Consts.java
     * [0] - State
     * [1] - Position
     * [2] - IsDeparture
     * [3] - IsGroupped
     * [4] - Type
     * [5] - Signal Name
     * [6] - Signal Render Scale
     * [7] - Arm 1 Rotation
     * [8] - Arm 2 Rotation
     */
    private final String[] mechanicalSignalProperties;

    public TileMechSignal() {
        this.mechanicalSignalProperties = new String[Consts.mechPropArrLenght];
        this.MostRestrictiveState = SignalState.ZHAS;
        this.mechanicalSignalProperties[0] = this.MostRestrictiveState.toString();
        this.mechanicalSignalProperties[1] = Consts.Position.MIDDLE.toString();
        this.mechanicalSignalProperties[2] = Consts.BooleanSTR.NO.toString();
        this.mechanicalSignalProperties[3] = Consts.BooleanSTR.NO.toString();
        this.mechanicalSignalProperties[4] = Consts.Types.TYPE_1.toString();
        this.mechanicalSignalProperties[5] = "Signal Name";
        this.mechanicalSignalProperties[6] = "Scale 0.1 - 2.0";
        this.mechanicalSignalProperties[7] = "0";
        this.mechanicalSignalProperties[8] = "0";
    }

    public SignalState getMostRestrictiveState() {
        return MostRestrictiveState;
    }

    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }

    public SignalState[] getValidState() {
        return ValidStates;
    }

    public LampFade getLampFade() {
        return lampFade;
    }

    @Override
    public SignalState[] getValidStatesForTile() {
        return new SignalState[]{SignalState.STUJ};
    }

    public void setValidStates(SignalState[] validStates) {
        ValidStates = validStates;
    }

    public void
    setMostRestrictiveState(SignalState mostRestrictiveState) {
        MostRestrictiveState = mostRestrictiveState;
    }

    @Override
    public void setState(SignalState state) {
        this.mechanicalSignalProperties[0] = state.StateToString();
        if (!worldObj.isRemote) {
            SignalCraft.SCNet.sendToAll(new MessageStateUpdate(this.xCoord, this.yCoord, this.zCoord, state.StateToString()));
        }
    }

    public void setPosition(Consts.Position position) {
        this.mechanicalSignalProperties[1] = position.toString();
    }

    public void setIsDeparture(Consts.BooleanSTR boo) {
        this.mechanicalSignalProperties[2] = boo.toString();
    }

    public void setIsGrupped(Consts.BooleanSTR boo) {
        this.mechanicalSignalProperties[3] = boo.toString();
    }

    public void setType(Consts.Types type) {
        this.mechanicalSignalProperties[4] = type.toString();
    }

    @Override
    public void setName(String name) {
        this.mechanicalSignalProperties[5] = name;
    }

    public void setScale(float scale) {
        this.mechanicalSignalProperties[6] = Float.toString(scale);
    }

    public void setArm1Rotation(int rotation) {
        this.mechanicalSignalProperties[7] = String.valueOf(rotation);
    }

    public void setArm2Rotation(int rotation) {
        this.mechanicalSignalProperties[8] = String.valueOf(rotation);
    }

    public SignalState getState() {
        return SignalState.fromOrdinal(this.mechanicalSignalProperties[0]);
    }

    public Consts.Position getPosition() {
        return Consts.Position.fromString(this.mechanicalSignalProperties[1]);
    }

    public Consts.BooleanSTR getIsDeparture() {
        return Consts.BooleanSTR.fromString(this.mechanicalSignalProperties[2]);
    }

    public Consts.BooleanSTR getIsGrupped() {
        return Consts.BooleanSTR.fromString(this.mechanicalSignalProperties[3]);
    }

    public Consts.Types getType() {
        return Consts.Types.fromString(this.mechanicalSignalProperties[4]);
    }

    @Override
    public String getName() {
        return this.mechanicalSignalProperties[5];
    }

    public float getScale() {
        try {
            return Float.parseFloat(this.mechanicalSignalProperties[6]);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public String getScaleString() {
        return this.mechanicalSignalProperties[6];
    }

    public int getArm1Rotation() {
        try {
            return Integer.parseInt(this.mechanicalSignalProperties[7]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getArm2Rotation() {
        try {
            return Integer.parseInt(this.mechanicalSignalProperties[8]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void setStateToMostRestrictive() {
        this.mechanicalSignalProperties[0] = this.getMostRestrictiveState().StateToString();
        if (!worldObj.isRemote) {
            SignalCraft.SCNet.sendToAll(new MessageStateUpdate(this.xCoord, this.yCoord, this.zCoord, this.getMostRestrictiveState().StateToString()));
        }
    }

    @Override
    public BlockPos getWorldPosition() {
        return new BlockPos(this.xCoord, this.yCoord, this.zCoord);
    }

    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.mechanicalSignalProperties[0] = NBTTC.getString("State");
        this.mechanicalSignalProperties[1] = NBTTC.getString("Position");
        this.mechanicalSignalProperties[2] = NBTTC.getString("IsDeparture");
        this.mechanicalSignalProperties[3] = NBTTC.getString("IsGroupped");
        this.mechanicalSignalProperties[4] = NBTTC.getString("Type");
        this.mechanicalSignalProperties[5] = NBTTC.getString("Name");
        this.mechanicalSignalProperties[6] = NBTTC.getString("Scale");
        this.mechanicalSignalProperties[7] = NBTTC.getString("Arm1Rotation");
        this.mechanicalSignalProperties[8] = NBTTC.getString("Arm2Rotation");
    }

    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("State", this.mechanicalSignalProperties[0]);
        NBTTC.setString("Position", this.mechanicalSignalProperties[1]);
        NBTTC.setString("IsDeparture", this.mechanicalSignalProperties[2]);
        NBTTC.setString("IsGroupped", this.mechanicalSignalProperties[3]);
        NBTTC.setString("Type", this.mechanicalSignalProperties[4]);
        NBTTC.setString("Name", this.mechanicalSignalProperties[5]);
        NBTTC.setString("Scale", this.mechanicalSignalProperties[6]);
        NBTTC.setString("Arm1Rotation", this.mechanicalSignalProperties[7]);
        NBTTC.setString("Arm2Rotation", this.mechanicalSignalProperties[8]);
    }
}
