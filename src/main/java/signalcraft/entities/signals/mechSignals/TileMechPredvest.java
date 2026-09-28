package signalcraft.entities.signals.mechSignals;

import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileMechPredvest extends TileMechSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.VYSTRAHA,
            SignalState.VOLNO
    ));

    public TileMechPredvest() {
        this.setMostRestrictiveState(SignalState.VYSTRAHA);
        this.ValidStates = getValidStatesForTile();
    }

    @Override
    public void updateEntity() {
        boolean boardHidden = this.getState().equals(SignalState.VOLNO);

        if (boardHidden) {
            if (this.getArm1Rotation() < 90) {
                this.setArm1Rotation(this.getArm1Rotation() + 2);
            }
        } else if (this.getArm1Rotation() > 0) {
            this.setArm1Rotation(this.getArm1Rotation() - 2);
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
