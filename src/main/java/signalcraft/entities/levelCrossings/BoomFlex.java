package signalcraft.entities.levelCrossings;

/**
 * Bend of a long barrier boom (client only, updated once a tick): a damped spring driven by the arm's motion - the
 * tip lags behind when the arm speeds up and runs on when it slows down or stops, and a horizontal boom sags a little
 * under its own weight. A longer boom swings slower, further and sags more. Degrees at the tip, + = further down.
 * Drawn by signalcraft.models.levelCrossing.BoomBend.
 */
public class BoomFlex {
    private static final float BASE_LENGTH = 4.2f;
    /** for a BASE_LENGTH boom: natural frequency (radians per tick, ~2.9 Hz), lag, sag (degrees), damping ratio */
    private static final float OMEGA = 0.9f, LAG = 1.2f, SAG = 0.3f, DAMPING = 0.12f;
    /** with the length: frequency ~ length^-OMEGA_EXP, lag ~ length^LAG_EXP, sag ~ length^SAG_EXP */
    private static final float OMEGA_EXP = 0.8f, LAG_EXP = 0.5f, SAG_EXP = 1.5f;
    private static final int SUBSTEPS = 4;

    /** the arm angle and speed in the last tick, the bend and its speed */
    private float lastAngle = Float.NaN, lastSpeed = 0f, flex = 0f, prevFlex = 0f, flexSpeed = 0f;

    /** Once a tick: the arm angle (degrees, 0 = up .. 90 = horizontal) and the boom length in metres. */
    public void update(float angle, float metres) {
        float length = metres / BASE_LENGTH;
        float omega = OMEGA * (float) Math.pow(length, -OMEGA_EXP);
        float lag = LAG * (float) Math.pow(length, LAG_EXP);
        float rest = SAG * (float) Math.pow(length, SAG_EXP) * (float) Math.sin(Math.toRadians(angle));
        if (Float.isNaN(lastAngle)) {
            lastAngle = angle;
            flex = prevFlex = rest;
        }
        float speed = angle - lastAngle;
        flexSpeed -= lag * (speed - lastSpeed);
        lastAngle = angle;
        lastSpeed = speed;
        prevFlex = flex;
        float h = 1f / SUBSTEPS;
        for (int i = 0; i < SUBSTEPS; i++) {
            flexSpeed += h * (-omega * omega * (flex - rest) - 2 * DAMPING * omega * flexSpeed);
            flex += h * flexSpeed;
        }
    }

    /** The bend for rendering, degrees at the tip, + = further down. */
    public float get(float partialTicks) {
        return prevFlex + (flex - prevFlex) * partialTicks;
    }

    /** How far the arm turned in the last tick, degrees. */
    public float getLastSpeed() {
        return lastSpeed;
    }
}
