package signalcraft.gui.levelCrossings.azd;

import signalcraft.entities.levelCrossings.azd.TileAZD71;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;

public class GuiAZD71 extends GuiLevelCrossings {
    public GuiAZD71(TileAZD71 thisTileE) {
        super(thisTileE);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.PozorButton.visible = false;
        this.PozDylButton.visible = false;
        this.ZebrikButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightCoverButton.visible = false;
        this.LightPosButton.visible = false;
        this.LightsAlterButton.visible = false;
    }
}
