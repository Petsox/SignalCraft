package signalcraft.gui.levelCrossings.azd;

import signalcraft.entities.levelCrossings.azd.TileAZD97;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;

public class GuiAZD97 extends GuiLevelCrossings {
    public GuiAZD97(TileAZD97 thisTileE) {
        super(thisTileE);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.PozorButton.visible = false;
        this.DistanceButton.visible = false;
        this.PozDylButton.visible = false;
        this.ZebrikButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightCoverButton.visible = false;
        this.LightPosButton.visible = false;
        this.LightsAlterButton.visible = false;
    }
}
