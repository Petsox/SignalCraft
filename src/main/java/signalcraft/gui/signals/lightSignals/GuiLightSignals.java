package signalcraft.gui.signals.lightSignals;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD6Lights;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.Network;
import signalcraft.signalUtils.SignalState;

import java.awt.*;

public class GuiLightSignals extends GuiScreen {
    private final String guiName;
    protected GuiButton doneButton;
    protected GuiButton PoziceButton;
    protected String PoziceText;
    protected GuiButton OdjezdButton;
    protected String OdjezdText;
    protected GuiButton PruhyButton;
    protected String PruhyText;
    protected GuiButton Pruhy3Button;
    protected String Pruhy3Text;
    protected GuiButton SkupinoveButton;
    protected String SkupinoveText;
    protected GuiButton SpeedButton;
    protected String SpeedText;
    protected GuiButton TypeButton;
    protected String TypeText;
    private final TileLightSignal thisTileE;
    private GuiTextField SignalName;
    private GuiTextField Scale;

    public GuiLightSignals(final TileLightSignal thisTileE) {
        this.guiName = this.getClass().getSimpleName();
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
        thisTileE.setState(SignalState.ALL);
    }

    public void initGui() {
        loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.buttonList.add(this.OdjezdButton = new GuiButton(1, this.width / 2 - 105, this.height / 4 - 5, 30, 20, OdjezdText));
        this.buttonList.add(this.PoziceButton = new GuiButton(2, this.width / 2 - 105, this.height / 4 + 30, 30, 20, PoziceText));
        this.buttonList.add(this.PruhyButton = new GuiButton(3, this.width / 2 - 105, this.height / 4 + 65, 30, 20, PruhyText));
        this.buttonList.add(this.Pruhy3Button = new GuiButton(4, this.width / 2 - 30, this.height / 4 + 65, 30, 20, Pruhy3Text));
        this.buttonList.add(this.SkupinoveButton = new GuiButton(5, this.width / 2 - 30, this.height / 4 + 95, 30, 20, SkupinoveText));
        this.buttonList.add(this.SpeedButton = new GuiButton(6, this.width / 2 - 105, this.height / 4 + 95, 30, 20, SpeedText));
        this.buttonList.add(this.TypeButton = new GuiButton(7, this.width / 2 - 30, this.height / 4 - 5, 30, 20, TypeText));
        SignalName = new GuiTextField(this.fontRendererObj, this.width / 2 - 105 , this.height / 4 - 57, 80, 15);
        Scale = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 4 - 57, 80, 15);
        this.SignalName.setText(this.thisTileE.getName());
        this.Scale.setText(this.thisTileE.getScaleString());
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawSignal();
        this.drawValidStates();
        if (OdjezdButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.departure.text"), this.width / 2 - 140, this.height / 4, 16777200);
        if (PoziceButton.visible) this.drawCenteredString(this.fontRendererObj,I18n.format("gui.lightsignal.position.text"), this.width / 2 - 140, this.height / 4 + 35, 16777200);
        if (PruhyButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.has.stripes.text"), this.width / 2 - 140, this.height / 4 + 70, 16777200);
        if (Pruhy3Button.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.has.3stripes.text"), this.width / 2 - 50, this.height / 4 + 70, 16777200);

        if (SkupinoveButton.visible && !(thisTileE.getGuiId() == Consts.GuiIDs.AZD_AB3 || thisTileE.getGuiId() == Consts.GuiIDs.AZD_AB4)){
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.grouped.text"), this.width / 2 - 50, this.height / 4 + 100, 16777200);
        } else if (SkupinoveButton.visible) {
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.end.autoblock.text"), this.width / 2 - 50, this.height / 4 + 100, 16777200);
        }

        if (SpeedButton.visible) this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.speed.sign.text"), this.width / 2 - 140, this.height / 4 + 100, 16777200);
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        SignalName.drawTextBox();
        Scale.drawTextBox();
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
                if (this.thisTileE.getIsDeparture().toBoolean()) {
                    this.OdjezdButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setIsDeparture(Consts.BooleanSTR.NO);
                    break;
                }
                this.OdjezdButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setIsDeparture(Consts.BooleanSTR.YES);
                break;
            }
            case 2: {
                if (this.thisTileE.getPosition().equals(Consts.Position.MIDDLE)) {
                    this.PoziceButton.displayString = I18n.format("gui.general.text.right");
                    this.thisTileE.setPosition(Consts.Position.RIGHT);
                    break;
                } else if (this.thisTileE.getPosition().equals(Consts.Position.RIGHT)) {
                    this.PoziceButton.displayString = I18n.format("gui.general.text.left");
                    this.thisTileE.setPosition(Consts.Position.LEFT);
                    break;
                } else {
                    this.PoziceButton.displayString = I18n.format("gui.general.text.straight");
                    this.thisTileE.setPosition(Consts.Position.MIDDLE);
                    break;
                }
            }
            case 3: {
                if (this.thisTileE.getHasStripes().toBoolean()) {
                    this.PruhyButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasStripes(Consts.BooleanSTR.NO);
                    break;
                } else {
                    this.PruhyButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHasStripes(Consts.BooleanSTR.YES);
                    break;
                }
            }
            case 4: {
                if (this.thisTileE.getHas3Stripes().toBoolean()) {
                    this.Pruhy3Button.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHas3Stripes(Consts.BooleanSTR.NO);
                    this.PruhyButton.visible = true;
                    break;
                } else {
                    this.Pruhy3Button.displayString = I18n.format("gui.general.text.yes");
                    this.thisTileE.setHas3Stripes(Consts.BooleanSTR.YES);
                    this.PruhyButton.displayString = I18n.format("gui.general.text.no");
                    this.PruhyButton.visible = false;
                    this.thisTileE.setHasStripes(Consts.BooleanSTR.NO);
                    break;
                }
            }
            case 5: {
                if (this.thisTileE.getIsGrupped().toBoolean()) {
                    this.SkupinoveButton.displayString = I18n.format("gui.general.text.no");;
                    this.thisTileE.setIsGrupped(Consts.BooleanSTR.NO);
                    break;
                }
                this.SkupinoveButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setIsGrupped(Consts.BooleanSTR.YES);
                break;
            }
            case 6: {
                if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.NO_SIGN)) {
                    this.SpeedButton.displayString = "30";
                    this.thisTileE.setSpeedSignText(Consts.SpeedSignText.SIGN_30);
                    break;
                } else if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.SIGN_30)) {
                    this.SpeedButton.displayString = "50";
                    this.thisTileE.setSpeedSignText(Consts.SpeedSignText.SIGN_50);
                    break;
                } else if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.SIGN_50) && thisTileE instanceof TileAZD6Lights) {
                    this.SpeedButton.displayString = "30 Light";
                    this.thisTileE.setSpeedSignText(Consts.SpeedSignText.LIGHT_30);
                } else {
                    this.SpeedButton.displayString = I18n.format("gui.general.text.none");
                    this.thisTileE.setSpeedSignText(Consts.SpeedSignText.NO_SIGN);
                    break;
                }
            }
            case 7: {
                if (this.thisTileE.getType().equals(Consts.Types.TYPE_1)) {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type2.text");
                    this.thisTileE.setType(Consts.Types.TYPE_2);
                    break;
                } else if (this.thisTileE.getType().equals(Consts.Types.TYPE_2)) {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type3.text");
                    this.thisTileE.setHas3Stripes(Consts.BooleanSTR.NO);
                    this.Pruhy3Button.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setHasStripes(Consts.BooleanSTR.NO);
                    this.PruhyButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setType(Consts.Types.TYPE_3);
                    break;
                } else {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type1.text");
                    this.thisTileE.setType(Consts.Types.TYPE_1);
                    this.thisTileE.setHasStripes(Consts.BooleanSTR.NO);
                    this.PruhyButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setSpeedSignText(Consts.SpeedSignText.NO_SIGN);
                    break;
                }
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.SignalName.mouseClicked(x, y, buttonClicked);
        this.Scale.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (this.SignalName.getText().length() <= 12 || code == 14) {
            this.SignalName.textboxKeyTyped(character, code);
        }
        if (this.Scale.getText().length() <= 3 || code == 14) {
            this.Scale.textboxKeyTyped(character, code);
        }
        if (code == 1) {
            this.actionPerformed(this.doneButton);
        }
    }

    public void updateScreen() {
        this.SignalName.updateCursorCounter();
        this.Scale.updateCursorCounter();
    }

    public void onGuiClosed() {
        thisTileE.setStateToMostRestrictive();
        thisTileE.setName(this.SignalName.getText());

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
        Network.updateLightSignals(this.thisTileE);
    }

    private void drawValidStates() {
        int x = 50;
        int y = 80;
        int rowHeight = 10;
        this.drawString(this.fontRendererObj, I18n.format("gui.lightsignal.validstates.text"), x, y, 16777215);
        int startY = y + 12;

        SignalState[] states = this.thisTileE.getValidStatesForTile();
        int maxTextWidth = 0;
        for (SignalState state : states) {
            if (state == SignalState.ALL || state == SignalState.ACTIVATE) continue;
            maxTextWidth = Math.max(maxTextWidth, this.fontRendererObj.getStringWidth(state.StateToString()));
        }
        int columnWidth = maxTextWidth + 10;
        int maxRows = Math.max(1, (this.height - 10 - startY) / rowHeight);

        int row = 0;
        int column = 0;
        for (SignalState state : states) {
            if (state == SignalState.ALL || state == SignalState.ACTIVATE) continue;
            if (row >= maxRows) {
                row = 0;
                column++;
            }
            this.drawString(this.fontRendererObj, state.StateToString(), x + column * columnWidth, startY + row * rowHeight, 16777200);
            row++;
        }
    }

    private void drawSignal() {
        final float SizePercent = (float) (((double) this.width / this.height) * 25);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 5.0f) * 4, (this.height / 5.0f) * 4, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(angle + 180, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    private void loadValuesFromTile(){
        if (this.thisTileE.getIsDeparture().toBoolean()) {
            this.OdjezdText = I18n.format("gui.general.text.yes");
        } else {
            this.OdjezdText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.getPosition().equals(Consts.Position.MIDDLE)) {
            this.PoziceText = I18n.format("gui.general.text.straight");
        } else if (this.thisTileE.getPosition().equals(Consts.Position.RIGHT)) {
            this.PoziceText = I18n.format("gui.general.text.right");
        } else {
            this.PoziceText = I18n.format("gui.general.text.left");
        }
        if (this.thisTileE.getHasStripes().toBoolean()) {
            this.PruhyText = I18n.format("gui.general.text.yes");
        } else {
            this.PruhyText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.getHas3Stripes().toBoolean()) {
            this.Pruhy3Text = I18n.format("gui.general.text.yes");
        } else {
            this.Pruhy3Text = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.getIsGrupped().toBoolean()) {
            this.SkupinoveText = I18n.format("gui.general.text.yes");
        } else {
            this.SkupinoveText = I18n.format("gui.general.text.no");
        }
        if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.NO_SIGN)) {
            this.SpeedText = I18n.format("gui.general.text.none");
        } else if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.SIGN_30)) {
            this.SpeedText = "30";
        } else if (this.thisTileE.getSpeedSignText().equals(Consts.SpeedSignText.SIGN_50)){
            this.SpeedText = "50";
        } else {
            this.SpeedText = "30 Light";
        }
        if (this.thisTileE.getType().equals(Consts.Types.TYPE_1)) {
            this.TypeText = I18n.format("gui.lightsignal.type1.text");
        } else if (this.thisTileE.getType().equals(Consts.Types.TYPE_2)) {
            this.TypeText = I18n.format("gui.lightsignal.type2.text");
        } else {
            this.TypeText = I18n.format("gui.lightsignal.type3.text");
        }
    }
    public boolean doesGuiPauseGame() {
        return false;
    }
}
