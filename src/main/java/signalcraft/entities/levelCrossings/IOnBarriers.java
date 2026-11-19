package signalcraft.entities.levelCrossings;

/**
 * A warning signal mounted on a barrier (directly on top of an {@link IBarriers} tile). Its bell
 * goes quiet once the barrier below has come fully down, until the signal switches off - worked out
 * by each side from its own arm (see TileLevelCrossing), not sent by the server.
 */
public interface IOnBarriers extends ILevelCrossing {
    /** Kept for old saves and the GUI; signals on a barrier no longer use it to mute the bell. */
    void setStrongSoundOn(Boolean strongSoundOn);

    Boolean isStrongSoundOn();
}
