package signalcraft.models.levelCrossing;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 * Draws a barrier boom bent by signalcraft.entities.levelCrossings.BoomFlex, in the arm's frame (the boom turns about
 * Z): the boom from where it leaves its clamp (root) to the tip is cut by clip planes into segments, each turned with
 * the joints below it. The joints are at equal steps along the boom, each taking its SHARE of the bend (a beam sagging
 * under its own weight bends most at the root). A + bend turns the boom anticlockwise about Z.
 */
public final class BoomBend {
    private static final float[] SHARE = {0.578f, 0.297f, 0.109f, 0.016f};
    private static final DoubleBuffer PLANE = BufferUtils.createDoubleBuffer(4);

    private BoomBend() {
    }

    /**
     * root (x, y), the unit direction (dx, dy) from it towards the tip, the length to the tip, the bend (degrees at the
     * tip); draw renders the boom's parts - the part behind the root stays straight
     */
    public static void render(float x, float y, float dx, float dy, float length, float flex, Runnable draw) {
        if (Math.abs(flex) < 0.01f) {
            draw.run();
            return;
        }
        float step = length / SHARE.length;
        GL11.glPushAttrib(GL11.GL_TRANSFORM_BIT | GL11.GL_ENABLE_BIT);
        clipPlane(GL11.GL_CLIP_PLANE0, x, y, -dx, -dy);
        GL11.glEnable(GL11.GL_CLIP_PLANE0);
        draw.run();
        GL11.glPushMatrix();
        for (int i = 0; i < SHARE.length; i++) {
            float px = x + dx * step * i, py = y + dy * step * i;
            turn(px, py, SHARE[i] * flex);
            // the planes are taken in the current (turned) frame: beyond this joint, before the next one
            clipPlane(GL11.GL_CLIP_PLANE0, px, py, dx, dy);
            if (i < SHARE.length - 1) {
                clipPlane(GL11.GL_CLIP_PLANE1, px + dx * step, py + dy * step, -dx, -dy);
                GL11.glEnable(GL11.GL_CLIP_PLANE1);
            } else {
                GL11.glDisable(GL11.GL_CLIP_PLANE1);
            }
            draw.run();
        }
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    /** Turns the current matrix with the bent boom at distance s from the root (for parts hung on the boom). */
    public static void follow(float x, float y, float dx, float dy, float length, float flex, float s) {
        float step = length / SHARE.length;
        for (int i = 0; i < SHARE.length && step * i < s; i++) {
            turn(x + dx * step * i, y + dy * step * i, SHARE[i] * flex);
        }
    }

    private static void turn(float px, float py, float angle) {
        GL11.glTranslatef(px, py, 0f);
        GL11.glRotatef(angle, 0f, 0f, 1f);
        GL11.glTranslatef(-px, -py, 0f);
    }

    /** A clip plane through (px, py) across the boom, keeping the side the normal (nx, ny) points to. */
    private static void clipPlane(int plane, float px, float py, float nx, float ny) {
        PLANE.clear();
        PLANE.put(nx).put(ny).put(0).put(-(nx * px + ny * py));
        PLANE.flip();
        GL11.glClipPlane(plane, PLANE);
    }
}
