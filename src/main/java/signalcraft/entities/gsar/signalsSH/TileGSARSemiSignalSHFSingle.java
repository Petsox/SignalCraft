package signalcraft.entities.gsar.signalsSH;

import signalcraft.entities.IActivatable;
import signalcraft.entities.gsar.signalsHP.TileGSARSemiSignal;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARSemiSignalSHFSingle extends TileGSARSemiSignal implements IActivatable {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.POSUNDOV
    ));

    /**
     * String[] mechanicalSignalProperties - Lenght in Consts.java
     * [0] - State
     * [1] - Position
     * [2] - IsDeparture
     * [3] - IsGroupped
     * [4] - Type
     * [5] - Signal Name
     * [6] - Signal Render Scale
     * [7] - Arm 1 Rotation
     * [8] - Arm 2 Rotation
     */
    public TileGSARSemiSignalSHFSingle() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.ValidStates = getValidStatesForTile();
    }

    @Override
    public void updateEntity() {
        if (this.getState().equals(SignalState.POSUNDOV) && this.getArm1Rotation() < 45) {
            this.setArm1Rotation(this.getArm1Rotation() + 1);
        } else if (!this.getState().equals(SignalState.POSUNDOV) && this.getArm1Rotation() > 0) {
            this.setArm1Rotation(this.getArm1Rotation() - 1);
        }
    }

    @Override
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
    public void setBlinkCounter(int counter) {
    }
}
