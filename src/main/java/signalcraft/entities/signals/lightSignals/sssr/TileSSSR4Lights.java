package signalcraft.entities.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileSSSR4Lights extends TileLightSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.VYSTRAHA,
            SignalState.PN,
            SignalState.R40VYSTRAHA,
            SignalState.R60VYSTRAHA,
            SignalState.R80VYSTRAHA,
            SignalState.R40VOLNO,
            SignalState.R60VOLNO,
            SignalState.R80VOLNO,
            SignalState.R40OCEK40,
            SignalState.R40OCEK60,
            SignalState.R40OCEK80,
            SignalState.R60OCEK40,
            SignalState.R60OCEK60,
            SignalState.R60OCEK80,
            SignalState.R80OCEK40,
            SignalState.R80OCEK60,
            SignalState.R80OCEK80,
            SignalState.OCEK40,
            SignalState.OCEK60,
            SignalState.OCEK80,
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
    public TileSSSR4Lights() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.setGuiId(Consts.GuiIDs.SSSR_4_LIGHTS);
    }

    @Override
    public SignalState[] getValidStatesForTile() {
        List<SignalState> validStates = new ArrayList<>(everyValidState);
        switch (this.getType()) {
            case TYPE_1:
                validStates.removeIf(state -> state.StateToString().contains("R40"));
                validStates.removeIf(state -> state.StateToString().contains("R60"));
                validStates.removeIf(state -> state.StateToString().contains("R80"));
                break;
            case TYPE_2:
                validStates.remove(SignalState.VYSTRAHA);
                validStates.remove(SignalState.R40VYSTRAHA);
                break;
            case TYPE_3:
                validStates.removeIf(state -> state.equals(SignalState.VOLNO) && !this.getIsDeparture().toBoolean());
                validStates.removeIf(state -> state.equals(SignalState.R40VOLNO) && !this.getIsDeparture().toBoolean());
                validStates.removeIf(state -> state.equals(SignalState.OCEK80) && !this.getIsDeparture().toBoolean());
                break;
        }

        validStates.removeIf(state -> !this.getHasStripes().toBoolean() && state.pruhy);

        return validStates.toArray(new SignalState[0]);
    }

    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }
}
