package signalcraft.entities.gsar.signalsHP;

import signalcraft.entities.signals.lightSignals.TileLightSignal;

public class TileGSARLightSignal extends TileLightSignal {

    public TileGSARLightSignal() {
    }

    @Override
    public void updateEntity() {
        ++this.blinkCounter;
        if (this.blinkCounter >= 31) {
            this.blinkCounter = 0;
        }
    }

    /** TileLightSignalsRendererGSAR ignores the scale - the shared wrapper applies it. */
    @Override
    public boolean isScaledByWrapper() {
        return true;
    }
}
