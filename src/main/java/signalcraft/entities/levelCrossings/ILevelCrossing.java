package signalcraft.entities.levelCrossings;

import signalcraft.entities.controllers.crossings.TileCrossingReceiver;

public interface ILevelCrossing {
    /**
     * Sets the state of the crossing (activated/deactivated) right now. Server side only - the
     * clients get it in the state snapshot this sends.
     * @param activated - true = activated, false = deactivated
     */
    void setCrossingActive(Boolean activated);

    /**
     * Switches the crossing off once all the given barriers (x, y, z triples) are up, plus delay
     * ticks; no barriers and no delay = now. Server side only - the clients get the same instruction
     * and judge it against their own barriers, so the signal goes dark on each client exactly when
     * the barriers that client shows are up.
     */
    void deactivateWhenBarriersUp(int[] barriers, int delayTicks);

    /**
     * Gets the state of the crossing (up/down)
     * @return true = activated, false = deactivated
     */
    boolean isCrossingActive();

    /**
     * Sets the state of the sound (on/off)
     * @param soundOn - true = sound on, false = sound off
     */
    void setSoundOn(Boolean soundOn);

    /**
     * Gets the receiver below the crossing, which is used to update the state of the crossing when the barriers are raised or lowered
     * @return the receiver below the crossing
     */
    TileCrossingReceiver getReceiverBelow();
}
