package signalcraft.entities.signals.mechSignals;

import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileMech1Arm extends TileMechSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.STUJ,
            SignalState.VOLNO
    ));

    public TileMech1Arm() {
        this.setMostRestrictiveState(SignalState.STUJ);
        this.ValidStates = getValidStatesForTile();
        this.setGuiId(Consts.GuiIDs.MECH_1ARM);
    }

    @Override
    public void updateEntity() {
        boolean armOut = this.getState().equals(SignalState.VOLNO);

        if (armOut) {
            if (this.getArm1Rotation() < 45) {
                this.setArm1Rotation(this.getArm1Rotation() + 1);
            }
        } else if (this.getArm1Rotation() > 0) {
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
}
