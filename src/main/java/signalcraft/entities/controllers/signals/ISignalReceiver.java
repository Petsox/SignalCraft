package signalcraft.entities.controllers.signals;

import signalcraft.signalUtils.SignalState;

public interface ISignalReceiver {
    void setState(SignalState state);
    SignalState getStateOnSignal();
    void setStateToSignalsMostRestrictive();
    SignalState[] getValidStatesForSignal();
}
