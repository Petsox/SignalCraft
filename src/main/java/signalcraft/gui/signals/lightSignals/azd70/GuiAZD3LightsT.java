package signalcraft.gui.signals.lightSignals.azd70;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD3LightsT;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiAZD3LightsT extends GuiLightSignals {
    TileLightSignal thisTileE;

    public GuiAZD3LightsT(TileAZD3LightsT tileE) {
        super(tileE);
        this.thisTileE = tileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.PruhyButton.visible = false;
        this.OdjezdButton.visible = false;
        this.PoziceButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
        this.SkupinoveButton.visible = false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    protected void mouseClicked(int x, int y, int buttonClicked) {
        super.mouseClicked(x, y, buttonClicked);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 7: {
                if (this.thisTileE.getType().equals(Consts.Types.TYPE_1)) {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type2.text");
                    this.thisTileE.setType(Consts.Types.TYPE_2);
                    break;
                } else {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type1.text");
                    this.thisTileE.setType(Consts.Types.TYPE_1);
                    break;
                }
            }
        }
    }

    @Override
    protected void keyTyped(char character, int code) {
        super.keyTyped(character, code);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}