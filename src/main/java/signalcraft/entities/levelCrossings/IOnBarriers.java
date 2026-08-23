package signalcraft.entities.levelCrossings;

public interface IOnBarriers extends ILevelCrossing{
    /**
     * Gates whether this crossing's sound is currently armed based on barrier arm
     * position, independent of the user's persistent {@link ILevelCrossing#setSoundOn}
     * preference set via the GUI. Barrier controllers must use this instead of
     * setSoundOn so they don't override the user's mute setting.
     * @param strongSoundOn - true = armed, false = silenced
     */
    void setStrongSoundOn(Boolean strongSoundOn);

    Boolean isStrongSoundOn();
}
