package signalcraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import signalcraft.SignalCraft;
import signalcraft.items.ItemScaler;
import signalcraft.messages.ScalerValueMessage;

/** Sets the scale kept in the Scaler item (like GuiItemRenamer). The block's limits are checked when it is applied. */
public class GuiItemScaler extends ScalableGuiScreen {
    private GuiButton doneButton;
    protected GuiTextField textFieldScale;
    private final ItemStack scaler;

    public GuiItemScaler(ItemStack scaler) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.scaler = scaler;
    }

    @Override
    protected int getDesignWidth() {
        return 260;
    }

    @Override
    protected int getDesignHeight() {
        return 200;
    }

    public void initGui() {
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, I18n.format("gui.done")));
        this.textFieldScale = new GuiTextField(this.fontRendererObj, this.width / 2 + 10, this.height / 4 + 80, 60, 20);
        this.textFieldScale.setMaxStringLength(6);
        this.textFieldScale.setText(ItemScaler.format(ItemScaler.getScale(this.scaler)));
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.beginContentScale();
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.scaler.text"), this.width / 2 - 40, this.height / 4 + 85, 16777200);
        this.textFieldScale.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }

    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(null);
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.textFieldScale.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (code == 1) {
            this.actionPerformed(this.doneButton);
            return;
        }
        // digits, one decimal point (a comma is taken as one too), editing keys
        if (this.textFieldScale.isFocused()) {
            char c = character == ',' ? '.' : character;
            if (Character.isDigit(c) || (c == '.' && !this.textFieldScale.getText().contains(".")) || character < 32 || code == Keyboard.KEY_DELETE) {
                this.textFieldScale.textboxKeyTyped(c, code);
            }
        }
    }

    public void updateScreen() {
        this.textFieldScale.updateCursorCounter();
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        try {
            float scale = Float.parseFloat(this.textFieldScale.getText());
            if (scale > 0f) {
                SignalCraft.SCNet.sendToServer(new ScalerValueMessage(scale, this.mc.thePlayer.inventory.currentItem));
            }
        } catch (NumberFormatException ignored) {
            // nothing typed / not a number: the item keeps its value
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
