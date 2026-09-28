package signalcraft.gui.signals.mechSignals;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.gui.ScalableGuiScreen;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.Network;
import signalcraft.signalUtils.SignalState;

import java.awt.*;

public class GuiMechSignal extends ScalableGuiScreen {
    protected GuiButton doneButton;
    protected GuiButton SkupinoveButton;
    protected String SkupinoveText;
    private final TileMechSignal thisTileE;
    private GuiTextField SignalName;
    private GuiTextField Scale;

    public GuiMechSignal(final TileMechSignal thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
        thisTileE.setState(SignalState.ALL);
    }

    @Override
    protected int getDesignWidth() {
        return 440;
    }

    @Override
    protected int getDesignHeight() {
        return 300;
    }

    public void initGui() {
        loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.buttonList.add(this.SkupinoveButton = new GuiButton(1, this.width / 2 - 30, this.height / 4 + 95, 30, 20, SkupinoveText));
        SignalName = new GuiTextField(this.fontRendererObj, this.width / 2 - 105, this.height / 4 - 57, 80, 15);
        Scale = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 4 - 57, 80, 15);
        this.SignalName.setText(this.thisTileE.getName());
        this.Scale.setText(this.thisTileE.getScaleString());
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.beginContentScale();
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawSignal();
        this.drawValidStates();
        if (SkupinoveButton.visible) {
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightsignal.grouped.text"), this.width / 2 - 50, this.height / 4 + 100, 16777200);
        }
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        SignalName.drawTextBox();
        Scale.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.thisTileE.getIsGrupped().toBoolean()) {
                    this.SkupinoveButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setIsGrupped(Consts.BooleanSTR.NO);
                    break;
                }
                this.SkupinoveButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setIsGrupped(Consts.BooleanSTR.YES);
                break;
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
        Network.updateMechSignals(this.thisTileE);
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

    private void loadValuesFromTile() {
        if (this.thisTileE.getIsGrupped().toBoolean()) {
            this.SkupinoveText = I18n.format("gui.general.text.yes");
        } else {
            this.SkupinoveText = I18n.format("gui.general.text.no");
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
