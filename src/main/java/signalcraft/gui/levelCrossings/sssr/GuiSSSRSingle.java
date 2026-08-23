package signalcraft.gui.levelCrossings.sssr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSRSingle;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;
import signalcraft.signalUtils.Consts;

public class GuiSSSRSingle extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;

    public GuiSSSRSingle(TileSSSRSingle thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.PozDylButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightPosButton.visible = false;
        this.ZebrikButton.visible = false;
        this.PozitButton.visible = false;
        this.UsePozButton.visible = false;
        this.KrizStozarButton.visible = false;
        this.textFieldPozitDelay.setVisible(false);
        this.LightsAlterButton.visible = false;
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
