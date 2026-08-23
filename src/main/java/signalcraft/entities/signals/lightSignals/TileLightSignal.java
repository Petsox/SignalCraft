package signalcraft.entities.signals.lightSignals;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.SignalCraft;
import signalcraft.entities.TileSignal;
import signalcraft.entities.signals.ISignal;
import signalcraft.messages.MessageStateUpdate;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.*;

public class TileLightSignal extends TileSignal implements ISignal {
    private final boolean Editable;
    private SignalState MostRestrictiveState;
    protected int blinkCounter;
    private int blinkCounterFast;
    public SignalState[] ValidStates;
    private final List<SignalState> everyValidState = new ArrayList<>();
    /** Client-only render state easing lamp brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();
    /**
     * String[] lightSignalProperties - Lenght in Consts.java
     * [0] - State
     * [1] - Position
     * [2] - HasStripes
     * [3] - Has3Stripes
     * [4] - SpeedSignText
     * [5] - IsDeparture
     * [6] - IsGroupped
     * [7] - Type
     * [8] - Signal Name
     * [9] - Signal Render Scale
     * [10] - HasPNLight
     */
    private final String[] lightSignalProperties;
    public NBTTagCompound NBTTC;
    public TileLightSignal() {
        this.lightSignalProperties = new String[Consts.lightPropArrLenght];
        this.MostRestrictiveState = SignalState.ZHAS;
        this.Editable = true;
        this.NBTTC = new NBTTagCompound();
        this.lightSignalProperties[0] = this.MostRestrictiveState.toString();
        this.lightSignalProperties[1] = Consts.Position.MIDDLE.toString();
        this.lightSignalProperties[2] = Consts.BooleanSTR.NO.toString();
        this.lightSignalProperties[3] = Consts.BooleanSTR.NO.toString();
        this.lightSignalProperties[4] = Consts.SpeedSignText.NO_SIGN.toString();
        this.lightSignalProperties[5] = Consts.BooleanSTR.NO.toString();
        this.lightSignalProperties[6] = Consts.BooleanSTR.NO.toString();
        this.lightSignalProperties[7] = Consts.Types.TYPE_1.toString();
        this.lightSignalProperties[8] = "Signal Name";
        this.lightSignalProperties[9] = "Scale 0.1 - 2.0";
        this.lightSignalProperties[10] = Consts.BooleanSTR.NO.toString();
    }
    public void updateEntity() {
        ++this.blinkCounter;
        if (this.blinkCounter >= 22) {
            this.blinkCounter = 0;
        }
        ++this.blinkCounterFast;
        if (this.blinkCounterFast >= 11) {
            this.blinkCounterFast = 0;
        }
    }
    public int getBlinkCounter() {
        return blinkCounter;
    }
    public int getBlinkCounterFast() {
        return blinkCounterFast;
    }
    public LampFade getLampFade() {
        return lampFade;
    }
    public boolean isEditable() {
        return this.Editable;
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
    @Override
    public SignalState[] getValidStatesForTile(){
        return new SignalState[]{};
    }
    public void setValidStates(SignalState[] validStates) {
        ValidStates = validStates;
    }
    public void setMostRestrictiveState(SignalState mostRestrictiveState) {
        MostRestrictiveState = mostRestrictiveState;
    }
    @Override
    public void setState(SignalState state) {
        this.lightSignalProperties[0] = state.StateToString();
        if (!worldObj.isRemote) {
            SignalCraft.SCNet.sendToAll(new MessageStateUpdate(this.xCoord, this.yCoord, this.zCoord, state.StateToString()));
        }
    }
    public void setPosition(Consts.Position position) {
        this.lightSignalProperties[1] = position.toString();
    }
    public void setHasStripes(Consts.BooleanSTR boo) {
        this.lightSignalProperties[2] = boo.toString();
    }
    public void setHas3Stripes(Consts.BooleanSTR boo) {
        this.lightSignalProperties[3] = boo.toString();
    }
    public void setSpeedSignText(Consts.SpeedSignText str) {
        this.lightSignalProperties[4] = str.toString();
    }
    public void setIsDeparture(Consts.BooleanSTR boo) {
        this.lightSignalProperties[5] = boo.toString();
    }
    public void setIsGrupped(Consts.BooleanSTR boo) {
        this.lightSignalProperties[6] = boo.toString();
    }
    public void setType(Consts.Types type){
        this.lightSignalProperties[7] = type.toString();
    }
    @Override
    public void setName(String name) {
        this.lightSignalProperties[8] = name;
    }
    public void setScale(float scale) {
        this.lightSignalProperties[9] = Float.toString(scale);
    }
    public void setPNLight(Consts.BooleanSTR boo) {
        this.lightSignalProperties[10] = boo.toString();
    }
    public SignalState getState() {
        return SignalState.fromOrdinal(this.lightSignalProperties[0]);
    }
    public Consts.Position getPosition() {
        return Consts.Position.fromString(this.lightSignalProperties[1]);
    }
    public Consts.BooleanSTR getHasStripes() {
        return Consts.BooleanSTR.fromString(this.lightSignalProperties[2]);
    }
    public Consts.BooleanSTR getHas3Stripes() {
        return Consts.BooleanSTR.fromString(this.lightSignalProperties[3]);
    }
    public Consts.SpeedSignText getSpeedSignText() {
        return Consts.SpeedSignText.fromString(this.lightSignalProperties[4]);
    }
    public Consts.BooleanSTR getIsDeparture() {
        return Consts.BooleanSTR.fromString(this.lightSignalProperties[5]);
    }
    public Consts.BooleanSTR getIsGrupped() {
        return Consts.BooleanSTR.fromString(this.lightSignalProperties[6]);
    }
    public Consts.Types getType(){
        return Consts.Types.fromString(this.lightSignalProperties[7]);
    }
    @Override
    public String getName() {
        return this.lightSignalProperties[8];
    }
    public float getScale() {
        try {
            return Float.parseFloat(this.lightSignalProperties[9]);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    public String getScaleString() {
        return this.lightSignalProperties[9];
    }
    public Consts.BooleanSTR hasPNLight() {
        return Consts.BooleanSTR.fromString(this.lightSignalProperties[10]);
    }
    @Override
    public void setStateToMostRestrictive() {
        this.lightSignalProperties[0] = this.getMostRestrictiveState().StateToString();
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
        this.lightSignalProperties[0] = NBTTC.getString("State");
        this.lightSignalProperties[1] = NBTTC.getString("Position");
        this.lightSignalProperties[2] = NBTTC.getString("HasStripes");
        this.lightSignalProperties[3] = NBTTC.getString("Has3Stripes");
        this.lightSignalProperties[4] = NBTTC.getString("SpeedSignText");
        this.lightSignalProperties[5] = NBTTC.getString("IsDeparture");
        this.lightSignalProperties[6] = NBTTC.getString("IsGroupped");
        this.lightSignalProperties[7] = NBTTC.getString("Type");
        this.lightSignalProperties[8] = NBTTC.getString("Name");
        this.lightSignalProperties[9] = NBTTC.getString("Scale");
        this.lightSignalProperties[10] = NBTTC.getString("HasPNLight");
    }

    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("State", this.lightSignalProperties[0]);
        NBTTC.setString("Position", this.lightSignalProperties[1]);
        NBTTC.setString("HasStripes", this.lightSignalProperties[2]);
        NBTTC.setString("Has3Stripes", this.lightSignalProperties[3]);
        NBTTC.setString("SpeedSignText", this.lightSignalProperties[4]);
        NBTTC.setString("IsDeparture", this.lightSignalProperties[5]);
        NBTTC.setString("IsGroupped", this.lightSignalProperties[6]);
        NBTTC.setString("Type", this.lightSignalProperties[7]);
        NBTTC.setString("Name", this.lightSignalProperties[8]);
        NBTTC.setString("Scale", this.lightSignalProperties[9]);
        NBTTC.setString("HasPNLight", this.lightSignalProperties[10]);
    }
}
