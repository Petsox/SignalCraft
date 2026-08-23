package signalcraft.entities.gsar.signalsHP;

import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARLightSignalHPx5 extends TileGSARLightSignal {

    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.PN,
            SignalState.R40VOLNO,
            SignalState.R60VOLNO,
            SignalState.R80VOLNO,
            SignalState.R40OCEK80,
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
    public TileGSARLightSignalHPx5() {
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

}
