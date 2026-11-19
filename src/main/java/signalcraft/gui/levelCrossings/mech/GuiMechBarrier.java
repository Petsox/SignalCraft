package signalcraft.gui.levelCrossings.mech;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.mech.TileMechBarrier;
import signalcraft.gui.ScalableGuiScreen;
import signalcraft.signalUtils.Network;

import java.awt.*;

public class GuiMechBarrier extends ScalableGuiScreen {
    private GuiButton doneButton;
    private GuiButton lengthButton;
    private GuiButton typeButton;
    private GuiButton latticeButton;
    private GuiButton sideButton;
    private GuiButton crossButton;
    private GuiButton reflexButton;
    private GuiButton largeButton;
    private GuiButton poleButton;
    private GuiButton slovakButton;
    private GuiButton signButton;
    private final TileMechBarrier thisTileE;
    private GuiTextField textFieldArmDownDelay;
    private GuiTextField textFieldSignalOffDelay;
    protected GuiTextField Scale;

    public GuiMechBarrier(final TileMechBarrier thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
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
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 165, I18n.format("gui.done")));
        this.buttonList.add(this.lengthButton = new GuiButton(1, this.width / 2 - 120, this.height / 4 + 5, 30, 20, lengthText()));
        this.buttonList.add(this.typeButton = new GuiButton(2, this.width / 2 - 25, this.height / 4 + 5, 40, 20, typeText()));
        this.buttonList.add(this.latticeButton = new GuiButton(3, this.width / 2 + 95, this.height / 4 + 5, 30, 20, latticeText()));
        this.buttonList.add(this.sideButton = new GuiButton(4, this.width / 2 + 95, this.height / 4 + 35, 40, 20, sideText()));
        this.buttonList.add(this.crossButton = new GuiButton(5, this.width / 2 + 95, this.height / 4 + 60, 70, 20, crossText()));
        this.buttonList.add(this.reflexButton = new GuiButton(6, this.width / 2 + 95, this.height / 4 + 85, 40, 20, yesNo(this.thisTileE.isKrizReflex())));
        this.buttonList.add(this.largeButton = new GuiButton(7, this.width / 2 + 95, this.height / 4 + 110, 40, 20, yesNo(this.thisTileE.isKrizVelky())));
        this.buttonList.add(this.poleButton = new GuiButton(8, this.width / 2 - 75, this.height / 4 + 85, 40, 20, yesNo(this.thisTileE.hasSignPole())));
        this.buttonList.add(this.slovakButton = new GuiButton(9, this.width / 2 - 75, this.height / 4 + 110, 40, 20, yesNo(this.thisTileE.isSlovak())));
        this.buttonList.add(this.signButton = new GuiButton(10, this.width / 2 - 75, this.height / 4 + 135, 40, 20, yesNo(this.thisTileE.hasPoleSign())));
        this.Scale = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 4 - 27, 80, 15);
        this.textFieldArmDownDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, this.height / 4 + 40, 50, 10);
        this.textFieldSignalOffDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, this.height / 4 + 65, 50, 10);
        this.Scale.setText(this.thisTileE.getScaleString());
        this.textFieldArmDownDelay.setText(Integer.toString(this.thisTileE.getArmDownDelay()));
        this.textFieldSignalOffDelay.setText(Integer.toString(this.thisTileE.getSignalOffDelay()));
    }

    private String lengthText() {
        return this.thisTileE.getBoomMetres() + ",0m";
    }

    private String typeText() {
        return I18n.format(this.thisTileE.isPzm1() ? "gui.levelcross.mech.pzm1" : "gui.levelcross.mech.pzm2");
    }

    private String latticeText() {
        return I18n.format(this.thisTileE.hasLattice() ? "gui.levelcross.mech.lattice.true" : "gui.levelcross.mech.lattice.false");
    }

    private String sideText() {
        return I18n.format(this.thisTileE.isBoomLeft() ? "gui.levelcross.mech.side.left" : "gui.levelcross.mech.side.right");
    }

    private static String yesNo(boolean value) {
        return I18n.format(value ? "gui.levelcross.mech.lattice.true" : "gui.levelcross.mech.lattice.false");
    }

    private String crossText() {
        if (!this.thisTileE.hasKriz()) return I18n.format("gui.levelcross.mech.cross.none");
        return I18n.format(this.thisTileE.isKrizJedno() ? "gui.levelcross.mech.cross.one" : "gui.levelcross.mech.cross.more");
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.beginContentScale();
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawModel();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.lenght.text"), this.width / 2 - 150, this.height / 4 + 10, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.down.delay.text"), this.width / 2 - 150, this.height / 4 + 40, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.signal.off.delay.text"), this.width / 2 - 150, this.height / 4 + 65, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.type.text"), this.width / 2 - 55, this.height / 4 + 10, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.lattice.text"), this.width / 2 + 55, this.height / 4 + 10, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.side.text"), this.width / 2 + 55, this.height / 4 + 40, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.text"), this.width / 2 + 55, this.height / 4 + 65, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.reflex.text"), this.width / 2 + 55, this.height / 4 + 90, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.large.text"), this.width / 2 + 55, this.height / 4 + 115, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.pole.text"), this.width / 2 - 150, this.height / 4 + 90, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.slovak.text"), this.width / 2 - 150, this.height / 4 + 115, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.pole.sign.text"), this.width / 2 - 150, this.height / 4 + 140, 16777200);
        this.textFieldArmDownDelay.drawTextBox();
        this.textFieldSignalOffDelay.drawTextBox();
        this.Scale.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                if (!this.textFieldArmDownDelay.getText().isEmpty()) thisTileE.setArmDownDelay(Integer.parseInt(textFieldArmDownDelay.getText()));
                if (!this.textFieldSignalOffDelay.getText().isEmpty()) thisTileE.setSignalOffDelay(Integer.parseInt(textFieldSignalOffDelay.getText()));
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                String[] lengths = TileMechBarrier.LENGTHS;
                int next = 0;
                for (int i = 0; i < lengths.length; i++) {
                    if (lengths[i].equals(this.thisTileE.getBarrierLength())) {
                        next = (i + 1) % lengths.length;
                        break;
                    }
                }
                this.thisTileE.setBarrierLength(lengths[next]);
                this.lengthButton.displayString = lengthText();
                break;
            }
            case 2: {
                this.thisTileE.setPzm1(!this.thisTileE.isPzm1());
                this.typeButton.displayString = typeText();
                break;
            }
            case 3: {
                this.thisTileE.setLattice(!this.thisTileE.hasLattice());
                this.latticeButton.displayString = latticeText();
                break;
            }
            case 4: {
                this.thisTileE.setBoomLeft(!this.thisTileE.isBoomLeft());
                this.sideButton.displayString = sideText();
                break;
            }
            case 5: {
                // none -> more tracks -> one track -> none
                if (!this.thisTileE.hasKriz()) {
                    this.thisTileE.setHasKriz(true);
                    this.thisTileE.setKrizJedno(false);
                } else if (!this.thisTileE.isKrizJedno()) {
                    this.thisTileE.setKrizJedno(true);
                } else {
                    this.thisTileE.setHasKriz(false);
                }
                this.crossButton.displayString = crossText();
                break;
            }
            case 6: {
                this.thisTileE.setKrizReflex(!this.thisTileE.isKrizReflex());
                this.reflexButton.displayString = yesNo(this.thisTileE.isKrizReflex());
                break;
            }
            case 7: {
                this.thisTileE.setKrizVelky(!this.thisTileE.isKrizVelky());
                this.largeButton.displayString = yesNo(this.thisTileE.isKrizVelky());
                break;
            }
            case 8: {
                this.thisTileE.setSignPole(!this.thisTileE.hasSignPole());
                this.poleButton.displayString = yesNo(this.thisTileE.hasSignPole());
                break;
            }
            case 9: {
                this.thisTileE.setSlovak(!this.thisTileE.isSlovak());
                this.slovakButton.displayString = yesNo(this.thisTileE.isSlovak());
                break;
            }
            case 10: {
                this.thisTileE.setPoleSign(!this.thisTileE.hasPoleSign());
                this.signButton.displayString = yesNo(this.thisTileE.hasPoleSign());
                break;
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.Scale.mouseClicked(x, y, buttonClicked);
        this.textFieldArmDownDelay.mouseClicked(x, y, buttonClicked);
        this.textFieldSignalOffDelay.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (code == 1) {
            this.actionPerformed(this.doneButton);
            return;
        }
        if (this.Scale.getText().length() <= 3 || code == 14) {
            this.Scale.textboxKeyTyped(character, code);
        }
        if (Character.isDigit(character) || code == 14) {
            this.textFieldArmDownDelay.textboxKeyTyped(character, code);
            this.textFieldSignalOffDelay.textboxKeyTyped(character, code);
        }
    }

    public void updateScreen() {
        this.Scale.updateCursorCounter();
        this.textFieldArmDownDelay.updateCursorCounter();
        this.textFieldSignalOffDelay.updateCursorCounter();
    }

    public void onGuiClosed() {
        try {
            float scale = Float.parseFloat(this.Scale.getText());
            if (scale < 0.1f) scale = 0.1f;
            if (scale > 2.0f) scale = 2.0f;
            thisTileE.setScale(scale);
        } catch (NumberFormatException e) {
            thisTileE.setScale(1.0f);
        }
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        if (!this.textFieldArmDownDelay.getText().isEmpty()) {
            this.thisTileE.setArmDownDelay(Integer.parseInt(this.textFieldArmDownDelay.getText()));
        }
        if (!this.textFieldSignalOffDelay.getText().isEmpty()) {
            this.thisTileE.setSignalOffDelay(Integer.parseInt(this.textFieldSignalOffDelay.getText()));
        }
        Network.updateCrossings(this.thisTileE);
    }

    private void drawModel() {
        final float SizePercent = 35.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 5.0f) * 4, (this.height / 5.0f) * 4, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
