package signalcraft.entities.gsar.signalsHP;

import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileGSARSemiSignal2Wingsx5 extends TileGSARSemiSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.R40VOLNO
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
    public TileGSARSemiSignal2Wingsx5() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.ValidStates = getValidStatesForTile();
    }

    @Override
    public void updateEntity() {
        if (this.getState().equals(SignalState.VOLNO) || this.getState().equals(SignalState.R40VOLNO)) {
            if (this.getArm1Rotation() < 45) {
                this.setArm1Rotation(this.getArm1Rotation() + 1);
            }
            if (this.getState().equals(SignalState.R40VOLNO) && this.getArm1Rotation() < 45) {
                this.setArm2Rotation(this.getArm2Rotation() + 1);
            }
        } else {
            if (this.getArm1Rotation() > 0) {
                this.setArm1Rotation(this.getArm1Rotation() - 1);
            }
            if (this.getArm2Rotation() > 0) {
                this.setArm2Rotation(this.getArm2Rotation() - 1);
            }
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

}
