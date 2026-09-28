package signalcraft.entities.levelCrossings;

public interface IBarriers extends ILevelCrossing {
    boolean isArmDown();
    boolean isArmUp();

    /**
     * How long, in seconds, the receiver should wait after the arm is fully up before
     * restoring the on-barrier signal's sound and deactivating the barrier-less signals
     * that share this crossing's name.
     */
    int getSignalOffDelay();
}
