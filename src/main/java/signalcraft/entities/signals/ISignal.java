package signalcraft.entities.signals;

import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

public interface ISignal {
    void setState(SignalState state);
    SignalState getState();
    void setStateToMostRestrictive();
    SignalState getMostRestrictiveState();
    SignalState[] getValidStatesForTile();
    BlockPos getWorldPosition();
    Consts.GuiIDs getGuiId();
}
