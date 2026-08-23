package signalcraft.gui.levelCrossings.sssr;

import net.minecraft.client.gui.GuiButton;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSRSingleHead;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;
import signalcraft.signalUtils.Consts;

public class GuiSSSRSingleHead extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;

    public GuiSSSRSingleHead(TileSSSRSingleHead thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.KrizStozarButton.visible = false;
        this.SlovenskoButton.visible = false;
        this.PruhyButton.visible = false;
        this.PozDylButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightPosButton.visible = false;
        this.ZebrikButton.visible = false;
        this.PozitButton.visible = false;
        this.UsePozButton.visible = false;
        this.textFieldPozitDelay.setVisible(false);
        this.NewerButton.visible = false;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 4) {
            if (this.thisTileE.getDistFromSloup().equals(Consts.DistFromPole.DIST_50)) {
                this.DistanceButton.displayString = "30";
                this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_30);
            } else {
                this.DistanceButton.displayString = "50";
                this.thisTileE.setDistFromSloup(Consts.DistFromPole.DIST_50);
            }
        } else {
            super.actionPerformed(button);
        }
    }
}
