package signalcraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

/**
 * Base for mod GUIs laid out with fixed pixel offsets from width/2 and height/4 (e.g.
 * "this.width / 2 - 100"). Those offsets assume a minimum amount of scaled screen space;
 * at high GUI Scale settings or small windows, the real scaled width/height can fall below
 * that, pushing buttons and text off-screen.
 *
 * Subclasses declare how much virtual space their layout needs via getDesignWidth()/
 * getDesignHeight(). setWorldAndResolution() lays the screen out against at least that much
 * virtual space (enlarging this.width/this.height when the real screen is smaller). Minecraft's
 * mouse-click handling (GuiScreen.handleMouseInput) maps clicks using that same enlarged
 * this.width/this.height, so buttons and text fields need no changes at all - they just work
 * at whatever size they were written for.
 *
 * Rendering is different: Minecraft re-derives its own GUI Scale ortho projection every frame
 * from the real window resolution, independent of this.width/this.height. Left alone, that
 * mismatch is exactly what pushed content off-screen in the first place. Rather than layer a
 * uniform GL scale on top of whatever ortho vanilla happened to set up that frame (fragile -
 * relies on the two independently-computed numbers lining up), beginContentScale() replaces
 * the projection outright with one spanning exactly the enlarged virtual space, so "where a
 * button is drawn" and "where a click lands" are computed against the literal same numbers.
 *
 * That enlarged virtual space must keep the real screen's aspect ratio, or mapping it onto
 * the full (fixed-aspect) viewport stretches X and Y by different amounts. So width and
 * height are always enlarged by the same proportional factor - the smallest one that covers
 * both design minimums - never independently.
 */
public abstract class ScalableGuiScreen extends GuiScreen {
    private int layoutWidth;
    private int layoutHeight;
    private boolean layoutEnlarged;

    /** Minimum virtual width this GUI's fixed-offset layout needs to avoid clipping. */
    protected abstract int getDesignWidth();

    /** Minimum virtual height this GUI's fixed-offset layout needs to avoid clipping. */
    protected abstract int getDesignHeight();

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        int designWidth = Math.max(1, this.getDesignWidth());
        int designHeight = Math.max(1, this.getDesignHeight());
        double scaleUp = Math.max(1.0, Math.max((double) designWidth / width, (double) designHeight / height));
        this.layoutWidth = (int) Math.ceil(width * scaleUp);
        this.layoutHeight = (int) Math.ceil(height * scaleUp);
        this.layoutEnlarged = scaleUp > 1.0;
        super.setWorldAndResolution(mc, this.layoutWidth, this.layoutHeight);
    }

    /** Call as the very first line of drawScreen(); pairs with endContentScale() at the very end. */
    protected final void beginContentScale() {
        if (this.layoutEnlarged) {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, this.layoutWidth, this.layoutHeight, 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
        }
    }

    /** Call as the very last line of drawScreen(), after the vanilla super.drawScreen(...) call. */
    protected final void endContentScale() {
        if (this.layoutEnlarged) {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPopMatrix();
        }
    }
}
