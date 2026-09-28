package signalcraft.entities.signals.mechSignals;

import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileMech2Arms extends TileMechSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO,
            SignalState.R40VOLNO
    ));

    public TileMech2Arms() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.ValidStates = getValidStatesForTile();
        this.setGuiId(Consts.GuiIDs.MECH_2ARMS);
    }

    @Override
    public void updateEntity() {
        boolean arm1Out = this.getState().equals(SignalState.VOLNO) || this.getState().equals(SignalState.R40VOLNO);
        boolean arm2Out = this.getState().equals(SignalState.R40VOLNO);

        if (arm1Out) {
            if (this.getArm1Rotation() < 45) {
                this.setArm1Rotation(this.getArm1Rotation() + 1);
            }
        } else if (this.getArm1Rotation() > 0) {
            this.setArm1Rotation(this.getArm1Rotation() - 1);
        }

        if (arm2Out) {
            if (this.getArm2Rotation() < 45) {
                this.setArm2Rotation(this.getArm2Rotation() + 1);
            }
        } else if (this.getArm2Rotation() > 0) {
            this.setArm2Rotation(this.getArm2Rotation() - 1);
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
