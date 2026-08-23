package signalcraft.entities.gsar.signalsSH;

import signalcraft.entities.IActivatable;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARLightSignalSHL extends TileGSARLightSignal implements IActivatable {

    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.POSUNDOV
    ));

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
    public TileGSARLightSignalSHL() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.ValidStates = getValidStatesForTile();
    }

    public SignalState[] getValidStatesForTile() {
        return everyValidState.toArray(new SignalState[0]);
    }

    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }

    @Override
    public void setIsActive(Boolean active) {
        if (active){
            this.setState(SignalState.POSUNDOV);
        } else {
            this.setState(SignalState.STUJ);
        }
    }

    @Override
    public Boolean getIsActive() {
        return this.getState().equals(SignalState.POSUNDOV);
    }

    @Override
    public void setBlinkCounter(int counter) {}
}
