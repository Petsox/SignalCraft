package signalcraft.gui.signals.signSignals;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.signSignals.TilePrUpoz;

public class GuiPrUpoz extends GuiSignSignal {
    private final TilePrUpoz thisTile;
    private GuiButton oddiloveButton;
    private String oddiloveText;

    public GuiPrUpoz(TilePrUpoz thisTileE) {
        super(thisTileE);
        thisTile = thisTileE;
    }

    @Override
    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.oddiloveButton = new GuiButton(1, this.width / 2 - 25, this.height / 4 - 5, 30, 20, this.oddiloveText));
        super.initGui();
    }

    @Override
    public void actionPerformed(final GuiButton button) {
        if (button.id == 1) {
            if (this.thisTile.getIsActive()) {
                this.oddiloveButton.displayString = I18n.format("gui.general.text.no");
                this.thisTile.setIsActive(false);
            } else {
                this.oddiloveButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTile.setIsActive(true);
            }
        } else {
            super.actionPerformed(button);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        this.drawDefaultBackground();
        this.drawString(this.fontRendererObj, I18n.format("gui.prUpozText"), this.width / 2 - 80, this.height / 4, 16777215);
        super.drawScreen(mouseX, mouseY, par3);
    }

    private void loadValuesFromTile() {
        if (thisTile.getHasSH2Stativ()) {
            this.oddiloveText = I18n.format("gui.general.text.yes");
        } else {
            this.oddiloveText = I18n.format("gui.general.text.no");
        }
    }
}
