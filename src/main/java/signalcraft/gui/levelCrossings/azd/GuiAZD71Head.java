package signalcraft.gui.levelCrossings.azd;

import net.minecraft.client.gui.GuiButton;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD71Head;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;
import signalcraft.signalUtils.Consts;

public class GuiAZD71Head extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;

    public GuiAZD71Head(TileAZD71Head thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.PozorButton.visible = false;
        this.PruhyButton.visible = false;
        this.PozDylButton.visible = false;
        this.ZebrikButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightCoverButton.visible = false;
        this.LightPosButton.visible = false;
        this.LightsAlterButton.visible = false;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 4) {
            if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_100)) {
                this.KrizStozarButton.visible = true;
                this.DistanceButton.displayString = "30";
                this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_30);
            } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_30)) {
                this.DistanceButton.displayString = "50";
                this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_50);
            } else if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_50)) {
                this.DistanceButton.displayString = "100";
                this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_100);
            }
        } else {
            super.actionPerformed(button);
        }
    }
}
