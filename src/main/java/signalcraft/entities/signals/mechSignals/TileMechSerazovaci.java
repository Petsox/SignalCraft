package signalcraft.entities.signals.mechSignals;

import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mechanical shunting signal (seřazovací návěstidlo). Arm 1 rotation is the board ("stit") angle:
 * 0 = board face-on, blue lens in front of the lamp (Posun zakázán), 90 = board tipped flat,
 * white lens in front of the lamp (Posun dovolen).
 */
public class TileMechSerazovaci extends TileMechSignal {
    private final List<SignalState> everyValidState = new ArrayList<>(Arrays.asList(
            SignalState.ALL,
            SignalState.POSUNZAK,
            SignalState.POSUNDOV
    ));

    public TileMechSerazovaci() {
        this.setMostRestrictiveState(SignalState.POSUNZAK);
        this.ValidStates = getValidStatesForTile();
        this.setGuiId(Consts.GuiIDs.MECH_SERAZOVACI);
    }

    @Override
    public void updateEntity() {
        boolean boardHidden = this.getState().equals(SignalState.POSUNDOV);

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
