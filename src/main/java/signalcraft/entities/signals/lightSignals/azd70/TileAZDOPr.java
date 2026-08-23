package signalcraft.entities.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileAZDOPr extends TileLightSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.VOLNO,
            SignalState.VYSTRAHA,
            SignalState.OCEK40,
            SignalState.OCEK60,
            SignalState.OCEK80,
            SignalState.OCEK100,
            SignalState.OPAKVOLNO,
            SignalState.OPAKVYSTRAHA,
            SignalState.OPAKOCEK40,
            SignalState.OPAKOCEK60,
            SignalState.OPAKOCEK80,
            SignalState.OPAKOCEK100
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
    public TileAZDOPr() {
        this.setMostRestrictiveState(SignalState.VYSTRAHA);
        this.setGuiId(Consts.GuiIDs.AZD_RE_DIST);
        this.ValidStates = this.getValidStatesForTile();
    }
    public SignalState[] getValidStatesForTile(){
        return everyValidState.toArray(new SignalState[0]);
    }
    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }
}
