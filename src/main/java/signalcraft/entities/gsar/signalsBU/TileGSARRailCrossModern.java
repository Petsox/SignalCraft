package signalcraft.entities.gsar.signalsBU;


public class TileGSARRailCrossModern extends TileGSARCrossing {

    @Override
    protected void handleSounds() {

        if (this.isActive) {
            int blinkCounter = this.getBlinkCounter();
            if (blinkCounter == 15 || blinkCounter == 45) {
                playSound("signalcraft:ring3", 1.0f, 1.0f);
            }
        }
    }
}
