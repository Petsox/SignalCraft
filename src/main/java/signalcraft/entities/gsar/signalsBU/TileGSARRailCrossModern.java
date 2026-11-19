package signalcraft.entities.gsar.signalsBU;


public class TileGSARRailCrossModern extends TileGSARCrossing {

    @Override
    protected void handleSounds() {

        if (this.isActive) {
            if (beat.hit(BLINK_PERIOD, 15, activation.since()) || beat.hit(BLINK_PERIOD, 45, activation.since())) {
                playSound("signalcraft:ring3", 1.0f, 1.0f);
            }
        }
    }
}
