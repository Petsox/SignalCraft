package signalcraft.entities.gsar.signalsBU;


public class TileGSARRailCrossLightS extends TileGSARCrossing {

    @Override
    protected void handleSounds() {

        if (isActive) {
            if (beat.hit(BLINK_PERIOD, 15, activation.since()) || beat.hit(BLINK_PERIOD, 45, activation.since())) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }

            if (beat.hit(BLINK_PERIOD, 47, activation.since())) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }
        }
    }
}
