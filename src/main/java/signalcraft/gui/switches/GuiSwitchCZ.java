package signalcraft.gui.switches;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.cz.TileSwitchCZ;
import signalcraft.entities.switches.cz.TileSwitchElectricCZ;
import signalcraft.entities.switches.cz.TileSwitchManualCZ;
import signalcraft.gui.ScalableGuiScreen;
import signalcraft.signalUtils.Network;

import java.awt.*;

/** Settings of the Czech point machines (hand-operated stand and EP 600 / EP-6xx). */
public class GuiSwitchCZ extends ScalableGuiScreen {
    private GuiButton doneButton;
    private GuiButton sideButton;
    private GuiButton signalButton;
    private GuiButton weightButton;
    private GuiButton typeButton;
    private GuiButton standButton;
    private GuiButton trackButton;
    private GuiTextField numberField;
    private GuiTextField scaleField;
    private final TileSwitchCZ thisTileE;
    private final TileSwitchElectricCZ electric;

    public GuiSwitchCZ(final TileSwitchCZ thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
        this.electric = thisTileE instanceof TileSwitchElectricCZ ? (TileSwitchElectricCZ) thisTileE : null;
    }

    @Override
    protected int getDesignWidth() {
        return 440;
    }

    @Override
    protected int getDesignHeight() {
        return 285;
    }

    public void initGui() {
        int x = this.width / 2 - 60, y = this.height / 4;
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, y + 165, I18n.format("gui.done")));
        this.buttonList.add(this.sideButton = new GuiButton(1, x, y + 25, 60, 20, sideText()));
        this.buttonList.add(this.signalButton = new GuiButton(2, x, y + 50, 90, 20, signalText()));
        this.buttonList.add(new GuiButton(3, x, y + 75, 20, 20, "<"));
        this.buttonList.add(new GuiButton(4, x + 70, y + 75, 20, 20, ">"));
        if (this.electric == null) {
            this.buttonList.add(this.weightButton = new GuiButton(5, x, y + 100, 90, 20, weightText()));
        } else {
            this.buttonList.add(this.typeButton = new GuiButton(6, x, y + 100, 60, 20, typeText()));
            this.buttonList.add(this.standButton = new GuiButton(7, x, y + 125, 40, 20, yesNo(this.electric.hasStand())));
        }
        this.buttonList.add(this.trackButton = new GuiButton(8, x + 110, y + 25, 60, 20, trackText()));
        this.scaleField = new GuiTextField(this.fontRendererObj, x + 110, y + 55, 40, 12);
        this.scaleField.setMaxStringLength(4);
        this.scaleField.setText(Float.toString(this.thisTileE.getScale()));
        this.numberField = new GuiTextField(this.fontRendererObj, x, y + 5, 30, 12);
        this.numberField.setMaxStringLength(3);
        this.numberField.setText(this.thisTileE.getSwitchName() == null ? "" : this.thisTileE.getSwitchName());
    }

    private String sideText() {
        return I18n.format(this.thisTileE.isRightTurnout() ? "gui.switch.cz.side.right" : "gui.switch.cz.side.left");
    }

    private String trackText() {
        return I18n.format(this.thisTileE.isTrackLeft() ? "gui.switch.cz.side.left" : "gui.switch.cz.side.right");
    }

    private String signalText() {
        return I18n.format("gui.switch.cz.signal." + this.thisTileE.getSignal());
    }

    private String weightText() {
        return I18n.format(((TileSwitchManualCZ) this.thisTileE).isYellowWeight() ? "gui.switch.cz.weight.yellow" : "gui.switch.cz.weight.bw");
    }

    private String typeText() {
        return this.electric.isEp6xx() ? "EP-6xx" : "EP 600";
    }

    private static String yesNo(boolean value) {
        return I18n.format(value ? "gui.levelcross.mech.lattice.true" : "gui.levelcross.mech.lattice.false");
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.beginContentScale();
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawCenteredString(this.fontRendererObj, I18n.format(this.electric == null ? "tile.SwitchManualCZ.name" : "tile.SwitchElectricCZ.name"), this.width / 2, this.height / 4 - 40, 16777215);
        this.drawModel();
        int x = this.width / 2 - 140, y = this.height / 4;
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.number.text"), x, y + 7, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.side.text"), x, y + 31, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.signal.text"), x, y + 56, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.shift.text"), x, y + 81, 16777200);
        this.drawCenteredString(this.fontRendererObj, String.format("%+.2f m", this.thisTileE.getShift() / 16f), this.width / 2 - 15, y + 81, 16777200);
        if (this.electric == null) {
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.weight.text"), x, y + 106, 16777200);
        } else {
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.type.text"), x, y + 106, 16777200);
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.stand.text"), x, y + 131, 16777200);
        }
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.track.text"), this.width / 2 + 80, y + 15, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.switch.cz.scale.text"), this.width / 2 + 80, y + 45, 16777200);
        this.numberField.drawTextBox();
        this.scaleField.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0:
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            case 1:
                this.thisTileE.setSwitchSide(!this.thisTileE.isRightTurnout());
                this.sideButton.displayString = sideText();
                break;
            case 2:
                this.thisTileE.setSignal((this.thisTileE.getSignal() + 1) % 3);
                this.signalButton.displayString = signalText();
                break;
            case 3:
                this.thisTileE.setShift(this.thisTileE.getShift() - 1);
                break;
            case 4:
                this.thisTileE.setShift(this.thisTileE.getShift() + 1);
                break;
            case 5:
                TileSwitchManualCZ stand = (TileSwitchManualCZ) this.thisTileE;
                stand.setYellowWeight(!stand.isYellowWeight());
                this.weightButton.displayString = weightText();
                break;
            case 6:
                this.electric.setEp6xx(!this.electric.isEp6xx());
                this.typeButton.displayString = typeText();
                break;
            case 7:
                this.electric.setStand(!this.electric.hasStand());
                this.standButton.displayString = yesNo(this.electric.hasStand());
                break;
            case 8:
                this.thisTileE.setTrackLeft(!this.thisTileE.isTrackLeft());
                this.trackButton.displayString = trackText();
                break;
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.numberField.mouseClicked(x, y, buttonClicked);
        this.scaleField.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (code == 1) {
            this.actionPerformed(this.doneButton);
            return;
        }
        if (Character.isDigit(character) || code == 14) {
            this.numberField.textboxKeyTyped(character, code);
        }
        if (Character.isDigit(character) || character == '.' || code == 14 || code == 203 || code == 205) {
            this.scaleField.textboxKeyTyped(character, code);
        }
    }

    public void updateScreen() {
        this.numberField.updateCursorCounter();
        this.scaleField.updateCursorCounter();
        this.thisTileE.setSwitchName(this.numberField.getText());
        try {
            this.thisTileE.setScale(Float.parseFloat(this.scaleField.getText()));
        } catch (NumberFormatException ignored) {
        }
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateSwitches(this.thisTileE);
    }

    private void drawModel() {
        final float SizePercent = 60.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 4.0f) * 3, (this.height / 5.0f) * 3, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        GL11.glRotatef(150f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-20f, 1.0f, 0.0f, 0.0f);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
