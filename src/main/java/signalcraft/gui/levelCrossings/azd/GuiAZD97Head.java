package signalcraft.gui.levelCrossings.azd;

import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD97Head;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;

public class GuiAZD97Head extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;

    public GuiAZD97Head(TileAZD97Head thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.PozorButton.visible = false;
        this.DistanceButton.visible = false;
        this.PruhyButton.visible = false;
        this.PozDylButton.visible = false;
        this.ZebrikButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightCoverButton.visible = false;
        this.LightPosButton.visible = false;
        this.LightsAlterButton.visible = false;
    }
}
