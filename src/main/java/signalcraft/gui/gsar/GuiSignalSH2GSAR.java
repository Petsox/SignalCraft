package signalcraft.gui.gsar;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;
import signalcraft.gui.signals.signSignals.GuiSignSignal;

public class GuiSignalSH2GSAR extends GuiSignSignal {
    private TileGSARSignSignalSH2 thisTile;
    private final String guiName;
    private final String editMsg_TITLE;
    private final String editMsg_SH2;
    private final String editMsg_SH2_1;
    private GuiButton switchLightButton;
    private String switchLightButtonText;
    private GuiButton switchRailModeButton;
    private String switchRailModeButtonText;

    public GuiSignalSH2GSAR(TileGSARSignSignalSH2 thisTileE) {
        super(thisTileE);
        this.guiName = this.getClass().getSimpleName();
        this.editMsg_TITLE = GuiModI18.gui(this.guiName, "editMsg_TITLE");
        this.editMsg_SH2 = GuiModI18.gui(this.guiName, "editMsg_SH2");
        this.editMsg_SH2_1 = GuiModI18.gui(this.guiName, "editMsg_SH2_1");
        thisTile = thisTileE;
    }
    @Override
    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.switchLightButton = new GuiButton(1, this.width / 2 - 105, this.height / 4 - 5, 30, 20, this.switchLightButtonText));
        this.buttonList.add(this.switchRailModeButton = new GuiButton(2, this.width / 2 - 105, this.height / 4 + 30, 30, 20, this.switchRailModeButtonText));
        super.initGui();
    }

    @Override
    public void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTile.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.thisTile.getHasSH2Lamp()) {
                    this.switchLightButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTile.setHasSH2Lamp(false);
                    break;
                } else {
                    this.switchLightButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTile.setHasSH2Lamp(true);
                    break;
                }
            }
            case 2: {
                if (this.thisTile.getHasSH2Stativ()) {
                    this.switchRailModeButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTile.setHasSH2Stativ(false);
                    break;
                } else {
                    this.switchRailModeButton.displayString = I18n.format("gui.general.text.yes");
                    this.thisTile.setHasSH2Stativ(true);
                    break;
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.editMsg_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_SH2, this.width / 2 - 220, this.height / 4 - 15, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_SH2_1, this.width / 2 - 220, this.height / 4 + 20, 16777215);
        super.drawScreen(mouseX, mouseY, par3);
    }

    private void loadValuesFromTile() {
        if (thisTile.getHasSH2Lamp()) {
            this.switchLightButtonText = I18n.format("gui.general.text.yes");
        } else {
            this.switchLightButtonText = I18n.format("gui.general.text.no");
        }
        if (thisTile.getHasSH2Stativ()) {
            this.switchRailModeButtonText = I18n.format("gui.general.text.yes");
        } else {
            this.switchRailModeButtonText = I18n.format("gui.general.text.no");
        }
    }
}
