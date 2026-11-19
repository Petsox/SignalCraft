package signalcraft.entities.levelCrossings;

public interface IBarriers extends ILevelCrossing {
    boolean isArmDown();
    boolean isArmUp();

    /**
     * How many ticks the (already deactivated) arm still needs to get fully up, computed with exactly
     * the same steps the arm will take in its updateEntity - on the server and on every client.
     */
    int ticksUntilArmUp();

    /**
     * How long, in seconds, after the arm is fully up the on-barrier signal and the barrier-less
     * signals sharing this crossing's name stay on.
     */
    int getSignalOffDelay();
}
