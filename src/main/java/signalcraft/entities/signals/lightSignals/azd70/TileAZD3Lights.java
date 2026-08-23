package signalcraft.entities.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileAZD3Lights extends TileLightSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.VYSTRAHA,
            SignalState.PN,
            SignalState.R40VOLNO,
            SignalState.OCEK40,
            SignalState.OCEK60,
            SignalState.OCEK80,
            SignalState.OCEK100,
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
    public TileAZD3Lights() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.setGuiId(Consts.GuiIDs.AZD_3_LIGHTS);
        this.ValidStates = getValidStatesForTile();
    }
    public SignalState[] getValidStatesForTile(){
        List<SignalState> validStates = new ArrayList<>(everyValidState);
        switch (this.getType()) {
            case TYPE_1:
                validStates.remove(SignalState.VYSTRAHA);
                validStates.remove(SignalState.R40VOLNO);
                validStates.remove(SignalState.OCEK40);
                validStates.remove(SignalState.OCEK60);
                break;
            case TYPE_2:
                validStates.remove(SignalState.PN);
                validStates.remove(SignalState.POSUNDOV);
                validStates.remove(SignalState.R40VOLNO);
                break;
            case TYPE_3:
                validStates.remove(SignalState.OCEK40);
                validStates.remove(SignalState.OCEK60);
                validStates.remove(SignalState.PN);
                validStates.remove(SignalState.POSUNDOV);
                validStates.remove(SignalState.VYSTRAHA);
                break;
        }

        return validStates.toArray(new SignalState[0]);
    }
    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }
}
