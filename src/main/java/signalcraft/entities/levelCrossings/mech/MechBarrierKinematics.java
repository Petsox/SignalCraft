package signalcraft.entities.levelCrossings.mech;

/**
 * Four-bar linkage of the mechanical barrier (shared by the tile entity and the client model).
 * Geometry in OBJ units (1 m = 1/1.5), measured from models/mech/zavora_mech.obj.
 * theta = how far the boom is raised: 0 = closed, 85 = open.
 */
public final class MechBarrierKinematics {
    public static final float PIVOT_X = -0.0871f, PIVOT_Y = 0.5803f;     // boom trunnion axis
    public static final float KOLO_X = 0.0062f, KOLO_Y = 0.7736f;        // wheel / cranked shaft axis
    public static final double CLAMP = 0.233333;     // link strap on the boom, distance from the pivot
    public static final double CRANK = 0.133333;     // crank radius
    public static final double PHI0 = Math.toRadians(205.0);   // crank angle with the boom closed
    public static final double LINK = 0.247243;      // link length

    private MechBarrierKinematics() {
    }

    /** Crank angle (radians, from +X towards +Y) for a boom raised by thetaDeg. */
    public static double crankAngle(double thetaDeg) {
        double t = Math.toRadians(thetaDeg);
        double cx = PIVOT_X - CLAMP * Math.cos(t), cy = PIVOT_Y + CLAMP * Math.sin(t);
        double dx = cx - KOLO_X, dy = cy - KOLO_Y, d = Math.hypot(dx, dy);
        double a = (CRANK * CRANK - LINK * LINK + d * d) / (2 * d);
        double h = Math.sqrt(Math.max(0, CRANK * CRANK - a * a));
        double ux = dx / d, uy = dy / d;
        return Math.atan2(a * uy - h * ux, a * ux + h * uy);
    }

    /** How far the drive wheel has turned from the closed position, degrees (continuous over 0..85). */
    public static float wheelTurn(double thetaDeg) {
        // the wheel turns monotonically from 0 to about -198 deg over 0..85: map into (-360, 0]
        double d = ((Math.toDegrees(crankAngle(thetaDeg) - PHI0) % 360) + 360) % 360;
        return (float) (d > 1e-6 ? d - 360 : 0);
    }

    /** Drive wheel speed in degrees per degree of boom travel at the given boom angle (magnitude). */
    public static float wheelRate(double thetaDeg) {
        double lo = Math.max(0, thetaDeg - 0.5), hi = Math.min(85, thetaDeg + 0.5);
        return (float) Math.abs((wheelTurn(hi) - wheelTurn(lo)) / (hi - lo));
    }
}
