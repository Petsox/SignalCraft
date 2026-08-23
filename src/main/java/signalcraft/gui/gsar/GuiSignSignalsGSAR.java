package signalcraft.gui.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import signalcraft.entities.gsar.signalsSO.TileGSARHectometer;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.gui.signals.signSignals.GuiSignSignal;
import signalcraft.signalUtils.Network;

@SideOnly(Side.CLIENT)
public class GuiSignSignalsGSAR extends GuiSignSignal {
    final Minecraft MC = Minecraft.getMinecraft();
    private final TileSignSignal tileSignSignal;
    GuiTextField editTextField_KM;
    GuiTextField editTextField_M;
    private final String guiName;
    private final String editMsg_TITLE;
    private final String editMsg_1;
    private final String editMsg_2;
    private final String editMsg_3;
    private final String editMsg_4;
    private final String editMsg_5;

    public GuiSignSignalsGSAR(final TileSignSignal thisTileE) {
        super(thisTileE);
        this.guiName = this.getClass().getSimpleName();
        this.editMsg_TITLE = GuiModI18.gui(this.guiName, "editMsg_TITLE_" + thisTileE.getGuiId().toString());
        this.editMsg_1 = GuiModI18.gui(this.guiName, "editMsg_1_" + thisTileE.getGuiId().toString());
        this.editMsg_2 = GuiModI18.gui(this.guiName, "editMsg_2_" + thisTileE.getGuiId().toString());
        this.editMsg_3 = GuiModI18.gui(this.guiName, "editMsg_3_" + thisTileE.getGuiId().toString());
        this.editMsg_4 = GuiModI18.gui(this.guiName, "editMsg_4_" + thisTileE.getGuiId().toString());
        this.editMsg_5 = GuiModI18.gui(this.guiName, "editMsg_5_" + thisTileE.getGuiId().toString());
        this.tileSignSignal = thisTileE;
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.editMsg_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.editMsg_1, this.width / 2 - 84, this.height / 4 - 10, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.editMsg_2, this.width / 2 - 84, this.height / 4 - 2, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.editMsg_3, this.width / 2 - 84, this.height / 4 + 28, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.editMsg_4, this.width / 2 - 84, this.height / 4 + 36, 16777215);
        if (!(tileSignSignal instanceof TileGSARHectometer))
            this.drawCenteredString(this.fontRendererObj, this.editMsg_5, this.width / 2 - 84, this.height / 4 + 44, 16777215);
        this.editTextField_KM.drawTextBox();
        if (tileSignSignal instanceof TileGSARHectometer) this.editTextField_M.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(this.allowUserInput = true);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.buttonList.clear();
        (this.editTextField_KM = new GuiTextField(this.fontRendererObj, this.width / 2 - 95, this.height / 4 + 10, 20, 10)).setMaxStringLength(3);
        (this.editTextField_M = new GuiTextField(this.fontRendererObj, this.width / 2 - 95, this.height / 4 + 48, 20, 10)).setMaxStringLength(1);
        editTextField_KM.setText(this.tileSignSignal.getSignalLabelStativ());
        editTextField_M.setText(this.tileSignSignal.getStationLabelStativ());
        super.initGui();
    }

    @Override
    protected void keyTyped(final char character, final int code) {
        if (Character.isDigit(character) || code == 14) {
            this.editTextField_KM.textboxKeyTyped(character, code);
            this.editTextField_M.textboxKeyTyped(character, code);
        }
        if ((code != 18 || !this.editTextField_KM.isFocused()) && (code != 18 || !this.editTextField_M.isFocused())) {
            super.keyTyped(character, code);
        }
        if (code == 1) {
            this.actionPerformed(this.doneButton);
        }
    }

    @Override
    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.editTextField_KM.mouseClicked(x, y, buttonClicked);
        this.editTextField_M.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        this.tileSignSignal.setSignalLabelStativ(this.editTextField_KM.getText());
        this.tileSignSignal.setStationLabelStativ(this.editTextField_M.getText());
        Network.updateSignSignals(tileSignSignal);
    }

    @Override
    public void updateScreen() {
        this.editTextField_KM.updateCursorCounter();
        this.editTextField_M.updateCursorCounter();
    }
}
