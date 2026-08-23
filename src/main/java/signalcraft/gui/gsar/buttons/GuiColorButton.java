package signalcraft.gui.gsar.buttons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiColorButton extends GuiButton
{
    private final Color color;
    private final ButtonFrontStyle frontStyle;
    
    public GuiColorButton(final int ID, final int X, final int Y, final int W, final int H, final Color color) {
        super(ID, X, Y, W, H, "");
        this.color = color;
        this.frontStyle = ButtonFrontStyle.COLORBLOCK;
    }
    
    public void drawButton(final Minecraft MC, final int X, final int Y) {
        if (this.visible && this.frontStyle == ButtonFrontStyle.COLORBLOCK) {
            this.drawColorBlockButton(MC, X, Y);
        }
    }
    
    private void drawColorBlockButton(final Minecraft MC, final int X, final int Y) {
        this.field_146123_n = (X >= this.xPosition && Y >= this.yPosition && X < this.xPosition + this.width && Y < this.yPosition + this.height);
        final int k = this.getHoverState(this.field_146123_n);
        final Color quadColor = new Color(225, 225, 225, 32 * k);
        this.renderQuad(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, quadColor);
        this.renderQuad(this.xPosition + 1, this.yPosition + 1, this.xPosition + this.width - 1, this.yPosition + this.height - 1, this.color);
    }
    
    private void renderQuad(final int X, final int Y, final int W, final int H, final Color color) {
        final Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(color.getRed() / 225.0f, color.getGreen() / 225.0f, color.getBlue() / 225.0f, color.getAlpha() / 225.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertex(X, H, 0.0);
        tessellator.addVertex(W, H, 0.0);
        tessellator.addVertex(W, Y, 0.0);
        tessellator.addVertex(X, Y, 0.0);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public enum ButtonFrontStyle
    {
        COLORBLOCK
    }
}
