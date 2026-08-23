package signalcraft.gui.controllers;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import signalcraft.entities.controllers.signals.TileRedReceiverSignals;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.Network;
import signalcraft.signalUtils.SignalState;
import signalcraft.signalUtils.Utils;

import java.awt.*;

public class GuiRedstoneReceiver extends GuiScreen {
    private final String guiName;
    private final int[][] signalStates = new int[4][];
    protected GuiButton doneButton;
    private final TileRedReceiverSignals thisTileE;
    private GuiTextField ControllerName;
    private GuiTextField[] stateFields = new GuiTextField[4];
    private String[] sideLangLabels = new String[4];

    public GuiRedstoneReceiver(final TileRedReceiverSignals thisTileE) {
        this.guiName = this.getClass().getSimpleName();
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    public void initGui() {
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 110, this.height / 4 + 160, I18n.format("gui.done")));
        ControllerName = new GuiTextField(this.fontRendererObj, this.width / 2 - 105, this.height / 4 - 57, 80, 15);

        for (int i = 0; i < stateFields.length; i++) {
            int xOffset = (i % 2 == 0) ? -105 : 10; // Alternate between left and right columns
            int yOffset = (i / 2) * 22 - 10; // Adjust vertical spacing
            stateFields[i] = new GuiTextField(this.fontRendererObj, this.width / 2 + xOffset, this.height / 4 + yOffset, 80, 15);

            // Store the side label separately
            sideLangLabels[i] = String.valueOf(I18n.format(Consts.Side.values()[i].getLangKey()));

            // Put the side's comma-separated signal states in the field
            int[] states = thisTileE.getSignalStates()[i];
            if (states != null && states.length > 0) {
                StringBuilder text = new StringBuilder();
                for (int ordinal : states) {
                    if (text.length() > 0) text.append(",");
                    text.append(SignalState.values()[ordinal].StateToString());
                }
                stateFields[i].setText(text.toString());
            }
        }

        this.ControllerName.setText(this.thisTileE.getName());
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        ControllerName.drawTextBox();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightSignalRedRece.use"), this.width / 2 + 5, this.height / 4 + 60, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.lightSignalRedRece.use2"), this.width / 2 - 5, this.height / 4 + 70, 16777200);

        for (int i = 0; i < stateFields.length; i++) {
            // Draw side label to the left of each field
            this.fontRendererObj.drawString(sideLangLabels[i], stateFields[i].xPosition - this.fontRendererObj.getStringWidth(sideLangLabels[i]) - 5, stateFields[i].yPosition + 3, 0xFFFFFF);
            stateFields[i].drawTextBox();
        }

        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.ControllerName.mouseClicked(x, y, buttonClicked);

        for (GuiTextField field : stateFields) {
            field.mouseClicked(x, y, buttonClicked);
        }

        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (this.ControllerName.getText().length() <= 12 || code == 14) {
            this.ControllerName.textboxKeyTyped(character, code);
        }

        for (GuiTextField field : stateFields) {
            if (field.getText().length() <= 12 || code == 14) {
                field.textboxKeyTyped(character, code);
            }
        }

        if (code == 1) {
            this.actionPerformed(this.doneButton);
        }
    }

    public void updateScreen() {
        this.ControllerName.updateCursorCounter();
    }

    public void onGuiClosed() {
        thisTileE.setName(this.ControllerName.getText());
        String[] colorNames = new String[signalStates.length];

        for (int i = 0; i < stateFields.length; i++) {
            String[] tokens = stateFields[i].getText().split(",");
            int[] parsedOrdinals = new int[tokens.length];
            StringBuilder namesJoined = new StringBuilder();
            int count = 0;

            for (String rawToken : tokens) {
                String token = rawToken.trim();
                if (token.isEmpty() || !SignalState.contains(token)) continue;

                SignalState state = SignalState.fromString(token);
                int value = state.ordinal();
                // ordinal 0 (ZHAS) is the "unset" sentinel and ordinal 1 (ALL) is a wildcard
                // pseudo-state, neither is a matchable side state
                if (value == 0 || value == 1 || value > SignalState.values().length - 1) continue;

                parsedOrdinals[count++] = value;
                if (namesJoined.length() > 0) namesJoined.append(",");
                namesJoined.append(state.StateToString());
            }

            signalStates[i] = java.util.Arrays.copyOf(parsedOrdinals, count);
            colorNames[i] = count == 0 ? "0" : namesJoined.toString();
        }

        thisTileE.setSignalStates(signalStates);


        Utils.addLocalizedChatMessage(this.mc.thePlayer, "message.controllerUpdatedWithStates");
        Utils.addChatMessage(this.mc.thePlayer, java.util.Arrays.toString(colorNames));

        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateControllers(this.thisTileE);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
