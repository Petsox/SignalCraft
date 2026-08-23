package signalcraft.entities.levelCrossings;

import signalcraft.entities.controllers.crossings.TileCrossingReceiver;

public interface ILevelCrossing {
    /**
     * Sets the state of the crossing (activated/deactivated)
     * @param activated - true = activated, false = deactivated
     */
    void setCrossingActive(Boolean activated);

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
