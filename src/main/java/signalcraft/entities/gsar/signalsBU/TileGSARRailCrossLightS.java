package signalcraft.entities.gsar.signalsBU;


public class TileGSARRailCrossLightS extends TileGSARCrossing {

    @Override
    protected void handleSounds() {

        if (isActive) {
            if (this.blinkCounter == 15 || this.blinkCounter == 45) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }

            if (this.blinkCounter == 47) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }
        }
    }
}
