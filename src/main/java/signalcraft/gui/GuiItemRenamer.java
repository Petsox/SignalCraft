package signalcraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import signalcraft.SignalCraft;
import signalcraft.items.ItemSignalRenamer;
import signalcraft.messages.RenamerNameMessage;

public class GuiItemRenamer extends GuiScreen {
    private GuiButton doneButton;
    protected GuiTextField textFieldName;
    private final ItemStack itemSignalRenamer;

    public GuiItemRenamer(ItemStack itemSignalRenamer) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.itemSignalRenamer = itemSignalRenamer;
    }

    public void initGui() {
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, I18n.format("gui.done")));
        this.textFieldName = new GuiTextField(this.fontRendererObj, this.width / 2 + 10, this.height / 4 + 80, 100, 20);
        this.textFieldName.setText(String.valueOf(itemSignalRenamer.getDisplayName()));
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.signalRenamer.text"), this.width / 2 - 40, this.height / 4 + 85, 16777200);
        this.textFieldName.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(null);
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.textFieldName.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (textFieldName.isFocused()) this.textFieldName.textboxKeyTyped(character, code);
    }

    public void updateScreen() {
        this.textFieldName.updateCursorCounter();
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        int slot = this.mc.thePlayer.inventory.currentItem;
        SignalCraft.SCNet.sendToServer(new RenamerNameMessage(textFieldName.getText(), slot));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
