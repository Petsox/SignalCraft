package signalcraft.gui.levelCrossings;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.Network;

import java.awt.*;

public class GuiLevelCrossings extends GuiScreen {
    private GuiButton doneButton;
    protected GuiButton KrizButton;
    protected String KrizText;
    protected GuiButton KrizReflexButton;
    protected String KrizReflexText;
    protected GuiButton KrizStozarButton;
    protected String KrizStozarText;
    protected GuiButton KrizVelkyButton;
    protected String KrizVelkyText;
    protected GuiButton DistanceButton;
    protected String DistanceText;
    protected GuiButton ZvukTypeButton;
    protected String ZvukTypeText;
    protected GuiButton PruhyButton;
    protected String PruhyText;
    protected GuiButton PozitButton;
    protected String PozitText;
    protected GuiButton UsePozButton;
    protected String UsePozText;
    protected GuiButton SlovenskoButton;
    protected String SlovenskoText;
    protected GuiButton ZvukButton;
    protected String ZvukText;
    protected GuiButton PozorButton;
    protected String PozorText;
    protected GuiButton PozDylButton;
    protected String PozDylText;
    protected GuiButton ZebrikButton;
    protected String ZebrikText;
    protected GuiButton OtradoviceButton;
    protected String OtradoviceText;
    protected GuiButton LightCoverButton;
    protected String LightCoverText;
    protected GuiButton LightPosButton;
    protected String LightPosText;
    protected GuiButton LightsAlterButton;
    protected String LightsAlterText;
    protected GuiButton NewerButton;
    protected String NewerText;
    private final TileLevelCrossing thisTileE;
    protected GuiTextField textFieldPozitDelay;
    protected GuiTextField textFieldHeadRot;
    protected GuiTextField Scale;

    public GuiLevelCrossings(final TileLevelCrossing thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 180, I18n.format("gui.done")));
        this.buttonList.add(this.KrizButton = new GuiButton(1, this.width / 2 - 120, this.height / 4 - 20, 30, 20, this.KrizText));
        this.buttonList.add(this.KrizReflexButton = new GuiButton(2, this.width / 2 - 35, this.height / 4 - 20, 30, 20, this.KrizReflexText));
        this.buttonList.add(this.KrizStozarButton = new GuiButton(3, this.width / 2 + 60, this.height / 4 - 20, 30, 20, this.KrizStozarText));
        this.buttonList.add(this.DistanceButton = new GuiButton(4, this.width / 2 - 120, this.height / 4 + 10, 30, 20, this.DistanceText));
        this.buttonList.add(this.ZvukTypeButton = new GuiButton(5, this.width / 2 - 120, this.height / 4 + 40, 30, 20, this.ZvukTypeText));
        this.buttonList.add(this.PruhyButton = new GuiButton(6, this.width / 2 - 120, this.height / 4 + 80, 30, 20, this.PruhyText));
        this.buttonList.add(this.PozitButton = new GuiButton(7, this.width / 2 - 35, this.height / 4 + 10, 30, 20, this.PozitText));
        this.buttonList.add(this.SlovenskoButton = new GuiButton(8, this.width / 2 - 35, this.height / 4 + 40, 30, 20, this.SlovenskoText));
        this.buttonList.add(this.ZvukButton = new GuiButton(9, this.width / 2 - 35, this.height / 4 + 80, 30, 20, this.ZvukText));
        this.buttonList.add(this.UsePozButton = new GuiButton(10, this.width / 2 + 60, this.height / 4 + 10, 30, 20, this.UsePozText));
        this.buttonList.add(this.PozorButton = new GuiButton(11, this.width / 2 + 60, this.height / 4 - 20, 30, 20, this.PozorText));
        this.buttonList.add(this.PozDylButton = new GuiButton(12, this.width / 2 - 120, this.height / 4 + 10, 30, 20, this.PozDylText));
        this.buttonList.add(this.ZebrikButton = new GuiButton(13, this.width / 2 + 60, this.height / 4 + 110, 30, 20, this.ZebrikText));
        this.buttonList.add(this.OtradoviceButton = new GuiButton(14, this.width / 2 + 60, this.height / 4 + 80, 30, 20, this.OtradoviceText));
        this.buttonList.add(this.KrizVelkyButton = new GuiButton(15, this.width / 2 + 60, this.height / 4 + 40, 30, 20, this.KrizVelkyText));
        this.buttonList.add(this.LightCoverButton = new GuiButton(16, this.width / 2 - 120, this.height / 4 + 110, 30, 20, this.LightCoverText));
        this.buttonList.add(this.LightPosButton = new GuiButton(17, this.width / 2 - 35, this.height / 4 + 110, 30, 20, this.LightPosText));
        this.buttonList.add(this.LightsAlterButton = new GuiButton(18, this.width / 2 - 80, this.height / 4 + 150, 30, 20, this.LightsAlterText));
        this.buttonList.add(this.NewerButton = new GuiButton(19, this.width / 2 + 60, this.height / 4 + 80, 30, 20, this.NewerText));
        this.textFieldPozitDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 80, this.height / 4 + 155, 50, 10);
        this.textFieldHeadRot = new GuiTextField(this.fontRendererObj, this.width / 2 + 80, this.height / 4 + 155, 50, 10);
        this.Scale = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 4 - 57, 80, 15);
        this.textFieldPozitDelay.setText(String.valueOf(this.thisTileE.getArmDownDelay()));
        this.textFieldHeadRot.setText(String.valueOf(this.thisTileE.getHeadRot()));
        this.Scale.setText(this.thisTileE.getScaleString());
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawModel();
        if (KrizButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.crossnum.text"), this.width / 2 - 150, this.height / 4 - 15, 16777200);
        if (KrizReflexButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.reflcross.text"), this.width / 2 - 60, this.height / 4 - 15, 16777200);
        if (KrizStozarButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.crossonpole.text"), this.width / 2 + 30, this.height / 4 - 15, 16777200);
        if (UsePozButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.usepozlight.text"), this.width / 2 + 30, this.height / 4 + 15, 16777200);
        if (DistanceButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.distfrompole.text"), this.width / 2 - 165, this.height / 4 + 15, 16777200);
        if (PozitButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.pozlight.text"), this.width / 2 - 60, this.height / 4 + 15, 16777200);
        if (SlovenskoButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.slovak.text"), this.width / 2 - 60, this.height / 4 + 45, 16777200);
        if (ZvukButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.sound.text"), this.width / 2 - 60, this.height / 4 + 85, 16777200);
        if (ZvukTypeButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.soundtype.text"), this.width / 2 - 150, this.height / 4 + 45, 16777200);
        if (ZvukTypeButton.visible && !thisTileE.getSoundType().Comment.isEmpty()) this.drawCenteredString(this.fontRendererObj, "Sound from: " + thisTileE.getSoundType().Comment, this.width / 2 - 140, this.height / 4 + 65, 16777200);
        if (PruhyButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.stripesonpole.text"), this.width / 2 - 155, this.height / 4 + 85, 16777200);
        if (PozorButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.PVSign.text"), this.width / 2 + 30, this.height / 4 - 15, 16777200);
        if (PozDylButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.white.light.cover.length.text"), this.width / 2 - 155, this.height / 4 + 15, 16777200);
        if (ZebrikButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.ladder.text"), this.width / 2 + 30, this.height / 4 + 115, 16777200);
        if (OtradoviceButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.otradovice.text"), this.width / 2 + 30, this.height / 4 + 85, 16777200);
        if (NewerButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.isnewer.text"), this.width / 2 + 30, this.height / 4 + 85, 16777200);
        if (KrizVelkyButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.krizvelky.text"), this.width / 2 + 30, this.height / 4 + 45, 16777200);
        if (textFieldPozitDelay.getVisible()) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.pozlightdelay.text"), this.width / 2 - 150, this.height / 4 + 155, 16777200);
        if (textFieldHeadRot.getVisible()) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.headrot.text"), this.width / 2 + 30, this.height / 4 + 155, 16777200);
        if (LightCoverButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.red.light.cover.length.text"), this.width / 2 - 155, this.height / 4 + 115, 16777200);
        if (LightPosButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.light.position.text"), this.width / 2 - 60, this.height / 4 + 115, 16777200);
        if (LightsAlterButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.dolightsalter.text"), this.width / 2 - 150, this.height / 4 + 155, 16777200);
        this.textFieldPozitDelay.drawTextBox();
        this.textFieldHeadRot.drawTextBox();
        this.Scale.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.thisTileE.isKrizJedno()) {
                    this.KrizButton.displayString = I18n.format("gui.levelcross.crossnum.more.text");
                    this.thisTileE.setKrizJedno(false);
                    break;
                } else if (this.KrizButton.displayString.equals(I18n.format("gui.levelcross.crossnum.more.text"))) {
                    this.KrizButton.displayString = I18n.format("gui.levelcross.crossnum.none.text");
                    this.thisTileE.setHasKriz(false);
                    break;
                } else {
                    this.KrizButton.displayString = I18n.format("gui.levelcross.crossnum.one.text");
                    this.thisTileE.setKrizJedno(true);
                    this.thisTileE.setHasKriz(true);
                    break;
                }
            }
            case 2: {
                if (this.thisTileE.isKrizReflex()) {
                    this.KrizReflexButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setKrizReflex(false);
                    break;
                } else {
                    this.KrizReflexButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setKrizReflex(true);
                    break;
                }
            }
            case 3: {
                if (this.thisTileE.hasKrizNaStozaru()) {
                    this.KrizStozarButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasKrizNaStozaru(false);
                    break;
                } else {
                    this.KrizStozarButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHasKrizNaStozaru(true);
                    break;
                }
            }
            case 4: {
                if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_00)) {
                    this.KrizStozarButton.visible = true;
                    this.DistanceButton.displayString = "30";
                    this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_30);
                    break;
                } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_30)) {
                    this.DistanceButton.displayString = "50";
                    this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_50);
                    break;
                } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_50)) {
                    this.DistanceButton.displayString = "100";
                    this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_100);
                    break;
                } else {
                    this.DistanceButton.displayString = "00";
                    this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_00);
                    this.KrizStozarButton.visible = false;
                    break;
                }
            }
            case 5: {
                Consts.SoundType next = this.thisTileE.getSoundType().nextValid();
                this.thisTileE.setSoundType(next);
                this.ZvukTypeButton.displayString = I18n.format(next.GUIString);
                break;
            }

            case 6: {
                if (this.thisTileE.hasPruhy()) {
                    this.PruhyButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasPruhy(false);
                    break;
                } else {
                    this.PruhyButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHasPruhy(true);
                    break;
                }
            }
            case 7: {
                if (this.thisTileE.hasPozLight()) {
                    this.PozitButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasPozLight(false);
                    this.PozDylButton.visible = false;
                    this.UsePozButton.visible = false;
                    break;
                } else {
                    this.PozitButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHasPozLight(true);
                    this.UsePozButton.visible = true;
                    break;
                }
            }
            case 8: {
                if (this.thisTileE.isSlovak()) {
                    this.SlovenskoButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setSlovak(false);
                    this.KrizStozarButton.visible = true;
                    this.KrizVelkyButton.visible = true;
                    break;
                } else {
                    this.SlovenskoButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setSlovak(true);
                    this.KrizStozarButton.visible = false;
                    this.KrizVelkyButton.visible = true;
                    break;
                }
            }
            case 9: {
                if (this.thisTileE.hasSoundOn()) {
                    this.ZvukButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setSoundOn(false);
                    break;
                } else {
                    this.ZvukButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setSoundOn(true);
                    break;
                }
            }
            case 10: {
                if (this.thisTileE.usePozLight()) {
                    this.UsePozButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setUsePozLight(false);
                    break;
                } else {
                    this.UsePozButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setUsePozLight(true);
                    break;
                }
            }
            case 11: {
                if (this.thisTileE.isCedule().equals(Consts.CeduleState.UP)) {
                    this.PozorButton.displayString = I18n.format("gui.general.text.down");
                    this.thisTileE.setCedule(Consts.CeduleState.DOWN);
                    break;
                } else if (this.thisTileE.isCedule().equals(Consts.CeduleState.DOWN)) {
                    this.PozorButton.displayString = I18n.format("gui.general.text.none");
                    this.thisTileE.setCedule(Consts.CeduleState.NONE);
                    break;
                } else {
                    this.PozorButton.displayString = I18n.format("gui.general.text.up");
                    this.thisTileE.setCedule(Consts.CeduleState.UP);
                    break;
                }
            }
            case 12: {
                if (this.thisTileE.isPozLightShort()) {
                    this.PozDylButton.displayString = I18n.format("gui.general.text.long");
                    this.thisTileE.setPozLightShort(false);
                    break;
                } else {
                    this.PozDylButton.displayString = I18n.format("gui.general.text.short");
                    this.thisTileE.setPozLightShort(true);
                    break;
                }
            }
            case 13: {
                if (this.thisTileE.hasZebrik()) {
                    this.ZebrikButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasZebrik(false);
                    break;
                } else {
                    this.ZebrikButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHasZebrik(true);
                    break;
                }
            }
            case 14: {
                if (this.thisTileE.isOtradovice()) {
                    this.OtradoviceButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setOtradovice(false);
                    break;
                } else {
                    this.OtradoviceButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setOtradovice(true);
                    break;
                }
            }
            case 15: {
                if (this.thisTileE.isKrizVelky()) {
                    this.KrizVelkyButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setKrizVelky(false);
                    break;
                } else {
                    this.KrizVelkyButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setKrizVelky(true);
                    break;
                }
            }
            case 16: {
                if (this.thisTileE.isLightCoverShort()) {
                    this.LightCoverButton.displayString = I18n.format("gui.general.text.long");
                    this.thisTileE.setLightCoverShort(false);
                    break;
                } else {
                    this.LightCoverButton.displayString = I18n.format("gui.general.text.short");
                    this.thisTileE.setLightCoverShort(true);
                    break;
                }
            }
            case 17: {
                if (this.thisTileE.getLightPos().equals(Consts.Position.MIDDLE)) {
                    this.LightPosButton.displayString = I18n.format("gui.general.text.left");
                    this.thisTileE.setLightPos(Consts.Position.LEFT);
                    break;
                } else if (this.thisTileE.getLightPos().equals(Consts.Position.LEFT)) {
                    this.LightPosButton.displayString = I18n.format("gui.general.text.right");
                    this.thisTileE.setLightPos(Consts.Position.RIGHT);
                    break;
                } else {
                    this.LightPosButton.displayString = I18n.format("gui.general.text.straight");
                    this.thisTileE.setLightPos(Consts.Position.MIDDLE);
                    break;
                }
            }
            case 18: {
                if (this.thisTileE.doLightsAlter()) {
                    this.LightsAlterButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.doLightsAlter(false);
                    break;
                } else {
                    this.LightsAlterButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.doLightsAlter(true);
                    break;
                }
            }
            case 19: {
                if (this.thisTileE.isNewer()) {
                    this.NewerButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setNewer(false);
                    break;
                } else {
                    this.NewerButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setNewer(true);
                    break;
                }
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.Scale.mouseClicked(x, y, buttonClicked);
        this.textFieldPozitDelay.mouseClicked(x, y, buttonClicked);
        this.textFieldHeadRot.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (this.Scale.getText().length() <= 3 || code == 14) {
            this.Scale.textboxKeyTyped(character, code);
        }
        if (Character.isDigit(character) || code == 14 || character == '-') {
            if (textFieldPozitDelay.isFocused()) this.textFieldPozitDelay.textboxKeyTyped(character, code);
            if (textFieldHeadRot.isFocused()) this.textFieldHeadRot.textboxKeyTyped(character, code);
        }
    }

    public void updateScreen() {
        this.Scale.updateCursorCounter();
        this.textFieldPozitDelay.updateCursorCounter();
        this.textFieldHeadRot.updateCursorCounter();
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
        if (!this.textFieldPozitDelay.getText().isEmpty()) {
            this.thisTileE.setArmDownDelay(Integer.parseInt(this.textFieldPozitDelay.getText()));
        }
        if (!this.textFieldHeadRot.getText().isEmpty()) {
            this.thisTileE.setHeadRot(Integer.parseInt(this.textFieldHeadRot.getText()));
        }
        Network.updateCrossings(this.thisTileE);
    }

    private void drawModel() {
        final float SizePercent = 100.0f;
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
    private void loadValuesFromTile(){
        if (this.thisTileE.isKrizJedno()) {
            this.KrizText = I18n.format("gui.levelcross.crossnum.one.text");
        } else if (this.thisTileE.hasKriz()) {
            this.KrizText = I18n.format("gui.levelcross.crossnum.more.text");
        } else {
            this.KrizText = I18n.format("gui.levelcross.crossnum.none.text");
        }
        if (this.thisTileE.isKrizReflex()) {
            this.KrizReflexText = I18n.format("gui.general.text.yes");
        } else {
            this.KrizReflexText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.hasKrizNaStozaru()) {
            this.KrizStozarText = I18n.format("gui.general.text.yes");
        } else {
            this.KrizStozarText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_00)) {
            this.DistanceText = "00";
        } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_30)) {
            this.DistanceText = "30";
        } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_50)) {
            this.DistanceText = "50";
        } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_100)) {
            this.DistanceText = "100";
        }
        this.ZvukTypeText = I18n.format(this.thisTileE.getSoundType().GUIString);
        if (this.thisTileE.hasPruhy()) {
            this.PruhyText = I18n.format("gui.general.text.yes");
        } else {
            this.PruhyText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.hasPozLight()) {
            this.PozitText = I18n.format("gui.general.text.yes");
        } else {
            this.PozitText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.usePozLight()) {
            this.UsePozText = I18n.format("gui.general.text.yes");
        } else {
            this.UsePozText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isSlovak()) {
            this.SlovenskoText = I18n.format("gui.general.text.yes");
        } else {
            this.SlovenskoText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.hasSoundOn()) {
            this.ZvukText = I18n.format("gui.general.text.yes");
        } else {
            this.ZvukText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isCedule().equals(Consts.CeduleState.UP)) {
            this.PozorText = I18n.format("gui.general.text.up");
        } else if (this.thisTileE.isCedule().equals(Consts.CeduleState.DOWN)) {
            this.PozorText = I18n.format("gui.general.text.down");
        } else {
            this.PozorText = I18n.format("gui.general.text.none");
        }
        if (this.thisTileE.isPozLightShort()) {
            this.PozDylText = I18n.format("gui.general.text.short");
        } else {
            this.PozDylText = I18n.format("gui.general.text.long");
        }
        if (this.thisTileE.hasZebrik()) {
            this.ZebrikText = I18n.format("gui.general.text.yes");
        } else {
            this.ZebrikText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isOtradovice()) {
            this.OtradoviceText = I18n.format("gui.general.text.yes");
        } else {
            this.OtradoviceText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isKrizVelky()) {
            this.KrizVelkyText = I18n.format("gui.general.text.yes");
        } else {
            this.KrizVelkyText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isLightCoverShort()) {
            this.LightCoverText = I18n.format("gui.general.text.short");
        } else {
            this.LightCoverText = I18n.format("gui.general.text.long");
        }
        if (this.thisTileE.getLightPos().equals(Consts.Position.MIDDLE)) {
            this.LightPosText = I18n.format("gui.general.text.straight");
        } else if (this.thisTileE.getLightPos().equals(Consts.Position.LEFT)) {
            this.LightPosText = I18n.format("gui.general.text.left");
        } else {
            this.LightPosText = I18n.format("gui.general.text.right");
        }
        if (this.thisTileE.doLightsAlter()){
            this.LightsAlterText = I18n.format("gui.general.text.yes");
        } else {
            this.LightsAlterText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.isNewer()){
            this.NewerText = I18n.format("gui.general.text.yes");
        } else {
            this.NewerText = I18n.format("gui.general.text.no");
        }
    }
    public boolean doesGuiPauseGame() {
        return false;
    }
}
