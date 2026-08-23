package signalcraft.entities.levelCrossings;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.IActivatable;
import signalcraft.entities.TileSignal;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.LampFade;

public abstract class TileLevelCrossing extends TileSignal implements IActivatable, ILevelCrossing {
    protected int blinkCounter;
    protected int pozitBlinkCounter;
    protected final int MAX_ARM_ANGLE = 85;
    protected int BellDelayTimer;
    protected int[] angles = {0};
    /** Client-only render state easing the warning lamps' brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();
    /**
     * String[] crossPropArrLenght - Lenght in Consts.java
     * [0] - isActive
     * [1] - armDownDelay
     * [2] - soundOn
     * [3] - strongSoundOn
     * [4] - SoundType
     * [5] - hasKriz
     * [6] - isKrizJedno
     * [7] - hasKrizNaStozaru
     * [8] - isKrizReflex
     * [9] - hasPozLight
     * [10] - usePozLight
     * [11] - isPozLightShort
     * [12] - hasZebrik
     * [13] - isSlovak
     * [14] - hasPruhy
     * [15] - DistFromSloup
     * [16] - LightPos
     * [17] - isCedule
     * [18] - isOtradovice
     * [19] - headRot
     * [20] - Name
     * [21] - Scale
     * [22] - BarrierLength
     * [23] - armRotation
     * [24] - isKrizVelky
     * [25] - isLightCoverShort
     * [26] - isNewer
     * [27] - doLightsAlter
     */
    private final String[] levelCrossingProperties;
    private final boolean Editable;

    public TileLevelCrossing() {
        this.levelCrossingProperties = new String[Consts.crossPropArrLenght];
        this.Editable = true;
        this.setActive(false);
        this.setArmDownDelay(0);
        this.setSoundOn(false);
        this.setStrongSoundOn(false);
        this.setSoundType(Consts.SoundType.NO_SOUND);
        this.setHasKriz(false);
        this.setKrizJedno(false);
        this.setHasKrizNaStozaru(false);
        this.setKrizReflex(false);
        this.setHasPozLight(false);
        this.setUsePozLight(false);
        this.setPozLightShort(false);
        this.setHasZebrik(false);
        this.setSlovak(false);
        this.setHasPruhy(false);
        this.setDistFromSloup(Consts.DistFromPole.DIST_00);
        this.setLightPos(Consts.Position.MIDDLE);
        this.setCedule(Consts.CeduleState.DOWN);
        this.setOtradovice(false);
        this.setHeadRot(0);
        this.setName("Crossing Name");
        this.levelCrossingProperties[21] = " Scale 0.1 - 2.0";
        this.setBarrierLength("0m");
        this.setArmRotation(0);
        this.setKrizVelky(false);
        this.setLightCoverShort(false);
        this.setNewer(false);
        this.doLightsAlter(false);
    }

    public void updateEntity() {
        ++this.blinkCounter;
        if (this.blinkCounter >= getSoundType().blinkTimer) {
            this.blinkCounter = 0;
        }
        ++this.pozitBlinkCounter;
        if (this.pozitBlinkCounter >= getSoundType().pozitBlinkTimer) {
            this.pozitBlinkCounter = 0;
        }
        if (this.BellDelayTimer > 0 && !this.getIsActive()) {
            --BellDelayTimer;
        }
        if (this.getIsActive()) {
            if (this.hasSoundOn() && this.isStrongSoundOn() && this.blinkCounter == getSoundType().soundTimer && !worldObj.isRemote) {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, this.getSoundType().SoundLocation, 0.75f, 1.0f);
            }
        }
    }

    public boolean isEditable() {
        return Editable;
    }

    public Boolean getIsActive() {
        return Boolean.parseBoolean(this.levelCrossingProperties[0]);
    }

    public Integer getArmDownDelay() {
        return Integer.parseInt(this.levelCrossingProperties[1]);
    }

    public Boolean hasSoundOn() {
        return Boolean.parseBoolean(this.levelCrossingProperties[2]);
    }

    public Boolean isStrongSoundOn() {
        return Boolean.parseBoolean(this.levelCrossingProperties[3]);
    }

    public Consts.SoundType getSoundType() {
        return Consts.SoundType.fromString(this.levelCrossingProperties[4]);
    }

    public Boolean hasKriz() {
        return Boolean.parseBoolean(this.levelCrossingProperties[5]);
    }

    public Boolean isKrizJedno() {
        return Boolean.parseBoolean(this.levelCrossingProperties[6]);
    }

    public Boolean hasKrizNaStozaru() {
        return Boolean.parseBoolean(this.levelCrossingProperties[7]);
    }

    public Boolean isKrizReflex() {
        return Boolean.parseBoolean(this.levelCrossingProperties[8]);
    }

    public Boolean hasPozLight() {
        return Boolean.parseBoolean(this.levelCrossingProperties[9]);
    }

    public Boolean usePozLight() {
        return Boolean.parseBoolean(this.levelCrossingProperties[10]);
    }

    public Boolean isPozLightShort() {
        return Boolean.parseBoolean(this.levelCrossingProperties[11]);
    }

    public Boolean hasZebrik() {
        return Boolean.parseBoolean(this.levelCrossingProperties[12]);
    }

    public Boolean isSlovak() {
        return Boolean.parseBoolean(this.levelCrossingProperties[13]);
    }

    public Boolean hasPruhy() {
        return Boolean.parseBoolean(this.levelCrossingProperties[14]);
    }

    public Consts.DistFromPole getDistFromSloup() {
        return Consts.DistFromPole.fromString(this.levelCrossingProperties[15]);
    }

    public Consts.Position getLightPos() {
        return Consts.Position.fromString(this.levelCrossingProperties[16]);
    }

    public Consts.CeduleState isCedule() {
        return Consts.CeduleState.fromString(this.levelCrossingProperties[17]);
    }

    public Boolean isOtradovice() {
        return Boolean.parseBoolean(this.levelCrossingProperties[18]);
    }

    public Integer getHeadRot() {
        return Integer.parseInt(this.levelCrossingProperties[19]);
    }

    public int getBlinkCounter() {
        return this.blinkCounter;
    }

    public int getPozitBlinkCounter() {
        return this.pozitBlinkCounter;
    }

    public LampFade getLampFade() {
        return this.lampFade;
    }

    @Override
    public String getName() {
        return this.levelCrossingProperties[20];
    }

    public Float getScale() {
        try {
            return Float.parseFloat(this.levelCrossingProperties[21]);
        } catch (NumberFormatException e) {
            return 1f;
        }
    }

    public int getPozLightDelayTimer() {
        return BellDelayTimer;
    }

    public String getScaleString() {
        return this.levelCrossingProperties[21];
    }

    public String getBarrierLength() {
        return this.levelCrossingProperties[22];
    }

    public int getArmRotation() {
        return Integer.parseInt(this.levelCrossingProperties[23]);
    }

    public boolean isKrizVelky() {
        return Boolean.parseBoolean(this.levelCrossingProperties[24]);
    }

    public boolean isLightCoverShort() {
        return Boolean.parseBoolean(this.levelCrossingProperties[25]);
    }

    public boolean isNewer() {
        return Boolean.parseBoolean(this.levelCrossingProperties[26]);
    }
    public boolean doLightsAlter(){
        return Boolean.parseBoolean(this.levelCrossingProperties[27]);
    }

    private void setActive(Boolean isActive) {
        this.levelCrossingProperties[0] = isActive.toString();
    }

    @Override
    public void setIsActive(Boolean active) {
        setCrossingActive(active);
    }

    @Override
    public TileCrossingReceiver getReceiverBelow() {
        for (int i = 1; i < 10; i++) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - i, zCoord);
            if (tile instanceof TileCrossingReceiver) {
                return (TileCrossingReceiver) tile;
            }
        }
        return new TileCrossingReceiver();
    }

    @Override
    public void setCrossingActive(Boolean activated) {
        if (!activated) BellDelayTimer = this.getArmDownDelay() * 20;
        this.blinkCounter = 0;
        this.pozitBlinkCounter = 0;
        this.setActive(activated);
    }

    @Override
    public boolean isCrossingActive() {
        return this.getIsActive();
    }

    public void setArmDownDelay(Integer armDownDelay) {
        this.levelCrossingProperties[1] = String.valueOf(armDownDelay);
    }

    public void setSoundOn(Boolean soundOn) {
        this.levelCrossingProperties[2] = soundOn.toString();
    }

    public void setStrongSoundOn(Boolean strongSoundOn) {
        this.levelCrossingProperties[3] = strongSoundOn.toString();
    }

    public void setSoundType(Consts.SoundType soundType) {
        this.levelCrossingProperties[4] = soundType.SoundLocation;
    }

    public void setHasKriz(Boolean hasKriz) {
        this.levelCrossingProperties[5] = hasKriz.toString();
    }

    public void setKrizJedno(Boolean isKrizJedno) {
        this.levelCrossingProperties[6] = isKrizJedno.toString();
    }

    public void setHasKrizNaStozaru(Boolean hasKrizNaStozaru) {
        this.levelCrossingProperties[7] = hasKrizNaStozaru.toString();
    }

    public void setKrizReflex(Boolean isKrizReflex) {
        this.levelCrossingProperties[8] = isKrizReflex.toString();
    }

    public void setHasPozLight(Boolean hasPozLight) {
        this.levelCrossingProperties[9] = hasPozLight.toString();
    }

    public void setUsePozLight(Boolean usePozLight) {
        this.levelCrossingProperties[10] = usePozLight.toString();
    }

    public void setPozLightShort(Boolean isPozLightShort) {
        this.levelCrossingProperties[11] = isPozLightShort.toString();
    }

    public void setHasZebrik(Boolean hasZebrik) {
        this.levelCrossingProperties[12] = hasZebrik.toString();
    }

    public void setSlovak(Boolean isSlovak) {
        this.levelCrossingProperties[13] = isSlovak.toString();
    }

    public void setHasPruhy(Boolean hasPruhy) {
        this.levelCrossingProperties[14] = hasPruhy.toString();
    }

    public void setDistFromSloup(Consts.DistFromPole distFromSloup) {
        this.levelCrossingProperties[15] = distFromSloup.Dist;
    }

    public void setLightPos(Consts.Position lightPos) {
        this.levelCrossingProperties[16] = lightPos.Pos;
    }

    public void setCedule(Consts.CeduleState isCedule) {
        this.levelCrossingProperties[17] = isCedule.State;
    }

    public void setOtradovice(Boolean isOtradovice) {
        this.levelCrossingProperties[18] = isOtradovice.toString();
    }

    public void setHeadRot(Integer headRot) {
        this.levelCrossingProperties[19] = String.valueOf(headRot);
    }

    @Override
    public void setName(String crossingName) {
        this.levelCrossingProperties[20] = crossingName;
    }

    public void setScale(Float scale) {
        this.levelCrossingProperties[21] = String.valueOf(scale);
    }

    public void setBarrierLength(String barrierLength) {
        this.levelCrossingProperties[22] = barrierLength;
    }

    public void setArmRotation(int armRotation) {
        this.levelCrossingProperties[23] = String.valueOf(armRotation);
    }

    @Override
    public void setBlinkCounter(int blinkCounter) {
        this.blinkCounter = blinkCounter;
    }

    public void setKrizVelky(Boolean isKrizVelky) {
        this.levelCrossingProperties[24] = isKrizVelky.toString();
    }

    public void setLightCoverShort(Boolean isLightCoverShort) {
        this.levelCrossingProperties[25] = isLightCoverShort.toString();
    }

    public void setNewer(Boolean isNewer) {
        this.levelCrossingProperties[26] = isNewer.toString();
    }

    public void doLightsAlter(Boolean alterLights) {
        this.levelCrossingProperties[27] = alterLights.toString();
    }

    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.levelCrossingProperties[0] = NBTTC.getString("isActive");
        this.levelCrossingProperties[1] = NBTTC.getString("armDownDelay");
        this.levelCrossingProperties[2] = NBTTC.getString("soundOn");
        this.levelCrossingProperties[3] = NBTTC.getString("strongSoundOn");
        this.levelCrossingProperties[4] = NBTTC.getString("SoundType");
        this.levelCrossingProperties[5] = NBTTC.getString("hasKriz");
        this.levelCrossingProperties[6] = NBTTC.getString("isKrizJedno");
        this.levelCrossingProperties[7] = NBTTC.getString("hasKrizNaStozaru");
        this.levelCrossingProperties[8] = NBTTC.getString("isKrizReflex");
        this.levelCrossingProperties[9] = NBTTC.getString("hasPozLight");
        this.levelCrossingProperties[10] = NBTTC.getString("usePozLight");
        this.levelCrossingProperties[11] = NBTTC.getString("isPozLightShort");
        this.levelCrossingProperties[12] = NBTTC.getString("hasZebrik");
        this.levelCrossingProperties[13] = NBTTC.getString("isSlovak");
        this.levelCrossingProperties[14] = NBTTC.getString("hasPruhy");
        this.levelCrossingProperties[15] = NBTTC.getString("DistFromSloup");
        this.levelCrossingProperties[16] = NBTTC.getString("LightPos");
        this.levelCrossingProperties[17] = NBTTC.getString("isCedule");
        this.levelCrossingProperties[18] = NBTTC.getString("isOtradovice");
        this.levelCrossingProperties[19] = NBTTC.getString("headRot");
        this.levelCrossingProperties[20] = NBTTC.getString("Name");
        this.levelCrossingProperties[21] = NBTTC.getString("Scale");
        this.levelCrossingProperties[22] = NBTTC.getString("BarrierLength");
        this.levelCrossingProperties[23] = NBTTC.getString("armRotation");
        this.levelCrossingProperties[24] = NBTTC.getString("isKrizVelky");
        this.levelCrossingProperties[25] = NBTTC.getString("isLightCoverShort");
        this.levelCrossingProperties[26] = NBTTC.getString("isNewer");
        this.levelCrossingProperties[27] = NBTTC.getString("doLightsAlter");
    }

    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("isActive", this.levelCrossingProperties[0]);
        NBTTC.setString("armDownDelay", this.levelCrossingProperties[1]);
        NBTTC.setString("soundOn", this.levelCrossingProperties[2]);
        NBTTC.setString("strongSoundOn", this.levelCrossingProperties[3]);
        NBTTC.setString("SoundType", this.levelCrossingProperties[4]);
        NBTTC.setString("hasKriz", this.levelCrossingProperties[5]);
        NBTTC.setString("isKrizJedno", this.levelCrossingProperties[6]);
        NBTTC.setString("hasKrizNaStozaru", this.levelCrossingProperties[7]);
        NBTTC.setString("isKrizReflex", this.levelCrossingProperties[8]);
        NBTTC.setString("hasPozLight", this.levelCrossingProperties[9]);
        NBTTC.setString("usePozLight", this.levelCrossingProperties[10]);
        NBTTC.setString("isPozLightShort", this.levelCrossingProperties[11]);
        NBTTC.setString("hasZebrik", this.levelCrossingProperties[12]);
        NBTTC.setString("isSlovak", this.levelCrossingProperties[13]);
        NBTTC.setString("hasPruhy", this.levelCrossingProperties[14]);
        NBTTC.setString("DistFromSloup", this.levelCrossingProperties[15]);
        NBTTC.setString("LightPos", this.levelCrossingProperties[16]);
        NBTTC.setString("isCedule", this.levelCrossingProperties[17]);
        NBTTC.setString("isOtradovice", this.levelCrossingProperties[18]);
        NBTTC.setString("headRot", this.levelCrossingProperties[19]);
        NBTTC.setString("Name", this.levelCrossingProperties[20]);
        NBTTC.setString("Scale", this.levelCrossingProperties[21]);
        NBTTC.setString("BarrierLength", this.levelCrossingProperties[22]);
        NBTTC.setString("armRotation", this.levelCrossingProperties[23]);
        NBTTC.setString("isKrizVelky", this.levelCrossingProperties[24]);
        NBTTC.setString("isLightCoverShort", this.levelCrossingProperties[25]);
        NBTTC.setString("isNewer", this.levelCrossingProperties[26]);
        NBTTC.setString("doLightsAlter", this.levelCrossingProperties[27]);
    }
}
