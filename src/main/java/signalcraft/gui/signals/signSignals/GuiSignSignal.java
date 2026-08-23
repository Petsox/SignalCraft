package signalcraft.gui.signals.signSignals;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.signalUtils.Network;

import java.awt.*;

public abstract class GuiSignSignal extends GuiScreen {
    private final TileSignSignal thisTileE;
    protected GuiButton doneButton;


    public GuiSignSignal(final TileSignSignal thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    public void initGui() {
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 165, I18n.format("gui.done")));
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawModel();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            this.thisTileE.markDirty();
            this.mc.displayGuiScreen(null);
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (code == 1) {
            this.actionPerformed(this.doneButton);
        }
    }

    public void updateScreen() {
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateSignSignals(this.thisTileE);
    }

    private void drawModel() {
        final float SizePercent = (float) (((double) this.width / this.height) * 25);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 5.0f) * 4, (this.height / 5.0f) * 4, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f + 180;
        GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
