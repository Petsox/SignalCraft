package signalcraft.entities.signals.lightSignals.azd65;

import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileAZD652Lights extends TileLightSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.PN,
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
    public TileAZD652Lights() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.setGuiId(Consts.GuiIDs.NOGUI);
        this.ValidStates = getValidStatesForTile();
    }
    public SignalState[] getValidStatesForTile(){
        List<SignalState> validStates = new ArrayList<>(everyValidState);
        if (this.getType().equals(Consts.Types.TYPE_1) && !this.hasPNLight().Boo){
            validStates.remove(SignalState.PN);
            validStates.remove(SignalState.POSUNDOV);
        } else if (this.getType().equals(Consts.Types.TYPE_2)){
            validStates.remove(SignalState.VOLNO);
        }
        if (this.hasPNLight().Boo){
            validStates.remove(SignalState.POSUNDOV);
        }

        return validStates.toArray(new SignalState[0]);
    }
    @Override
    public List<SignalState> getEveryValidState() {
        return everyValidState;
    }
}
