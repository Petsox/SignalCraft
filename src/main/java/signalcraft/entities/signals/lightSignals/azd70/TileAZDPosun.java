package signalcraft.entities.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileAZDPosun extends TileLightSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.POSUNDOV,
            SignalState.POSUNZAK
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
    public TileAZDPosun() {
        this.setMostRestrictiveState(SignalState.POSUNZAK);
        this.setGuiId(Consts.GuiIDs.AZD_SHUNT);
        this.ValidStates = getValidStatesForTile();
    }
    public SignalState[] getValidStatesForTile(){
        return everyValidState.toArray(new SignalState[0]);
    }
    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }
}
