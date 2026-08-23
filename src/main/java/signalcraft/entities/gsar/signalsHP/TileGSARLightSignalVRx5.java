package signalcraft.entities.gsar.signalsHP;

import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARLightSignalVRx5 extends TileGSARLightSignal
{

    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.VOLNO,
            SignalState.VYSTRAHA,
            SignalState.OCEK40,
            SignalState.OCEK60,
            SignalState.OCEK80
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
    public TileGSARLightSignalVRx5() {
        this.setMostRestrictiveState(SignalState.VYSTRAHA);
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
