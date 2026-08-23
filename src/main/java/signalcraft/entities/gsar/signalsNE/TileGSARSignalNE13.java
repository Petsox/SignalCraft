package signalcraft.entities.gsar.signalsNE;

import signalcraft.entities.IActivatable;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARSignalNE13 extends TileGSARLightSignal implements IActivatable
{

    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ZHAS,
            SignalState.ACTIVATE
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
    public TileGSARSignalNE13() {
        this.setMostRestrictiveState(SignalState.ZHAS);
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
        if (active) {
            this.setState(SignalState.ACTIVATE);
        } else {
            this.setState(SignalState.ZHAS);
        }
    }

    @Override
    public Boolean getIsActive() {
        return this.getState().equals(SignalState.ACTIVATE);
    }

    @Override
    public void setBlinkCounter(int counter) {}
}
