package signalcraft.gui.gsar.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonModeStates extends GuiButton
{
    private String popupText;
    
    public GuiButtonModeStates(final int x, final int y, final int z, final int l, final int i1, final String s) {
        super(x, y, z, l, i1, s);
    }
    
    public boolean inBounds(final int x, final int y) {
        return x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
    }
    
    public String getPopupText() {
        if (this.popupText != null && !this.popupText.isEmpty()) {
            return this.popupText;
        }
        return "";
    }
    
    protected void drawToolTip(final Minecraft mc, final int x, final int y) {
        final String buttonPopupText = this.getPopupText();
        if (!buttonPopupText.isEmpty()) {
            final int l1 = mc.fontRenderer.getStringWidth(buttonPopupText);
            final int i = 0;
            final int j = -10;
            final int j2 = x - i + 12;
            final int l2 = y - j - 12;
            final int i2 = l1 + 5;
            final int j3 = 8;
            this.zLevel = 300.0f;
            final int k3 = -267386864;
            this.drawGradientRect(j2 - 3, l2 - 4, j2 + i2 + 3, l2 - 3, k3, k3);
            this.drawGradientRect(j2 - 3, l2 + j3 + 3, j2 + i2 + 3, l2 + j3 + 4, k3, k3);
            this.drawGradientRect(j2 - 3, l2 - 3, j2 + i2 + 3, l2 + j3 + 3, k3, k3);
            this.drawGradientRect(j2 - 4, l2 - 3, j2 - 3, l2 + j3 + 3, k3, k3);
            this.drawGradientRect(j2 + i2 + 3, l2 - 3, j2 + i2 + 4, l2 + j3 + 3, k3, k3);
            final int l3 = 1347420415;
            final int i3 = (l3 & 0xFEFEFE) >> 1 | (l3 & 0xFF000000);
            this.drawGradientRect(j2 - 3, l2 - 3 + 1, j2 - 3 + 1, l2 + j3 + 3 - 1, l3, i3);
            this.drawGradientRect(j2 + i2 + 2, l2 - 3 + 1, j2 + i2 + 3, l2 + j3 + 3 - 1, l3, i3);
            this.drawGradientRect(j2 - 3, l2 - 3, j2 + i2 + 3, l2 - 3 + 1, l3, l3);
            this.drawGradientRect(j2 - 3, l2 + j3 + 2, j2 + i2 + 3, l2 + j3 + 3, i3, i3);
            mc.fontRenderer.drawSplitString(buttonPopupText, x + 15, y - 1, l1 * 2, -1);
            this.zLevel = 0.0f;
        }
    }
}
