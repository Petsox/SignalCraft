package signalcraft.entities.gsar.signalsBU;


public class TileGSARRailCrossLightS extends TileGSARCrossing {

    @Override
    protected void handleSounds() {

        if (isActive) {
            int blinkCounter = this.getBlinkCounter();
            if (blinkCounter == 15 || blinkCounter == 45) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }

            if (blinkCounter == 47) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }
        }
    }
}
