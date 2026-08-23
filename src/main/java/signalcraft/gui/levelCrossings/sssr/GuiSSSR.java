package signalcraft.gui.levelCrossings.sssr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSR;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;

public class GuiSSSR extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;

    public GuiSSSR(TileSSSR thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.KrizStozarButton.visible = false;
        this.DistanceButton.visible = false;
        this.textFieldHeadRot.setVisible(false);
        this.LightsAlterButton.visible = false;
        this.NewerButton.visible = false;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 7) {
            if (this.thisTileE.hasPozLight()) {
                this.PozitButton.displayString = I18n.format("gui.general.text.no");
                this.thisTileE.setHasPozLight(false);
                this.PozDylButton.visible = false;
                this.UsePozButton.visible = false;
            } else {
                this.PozitButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setHasPozLight(true);
                this.PozDylButton.visible = true;
                this.UsePozButton.visible = true;
            }
        } else if (button.id == 8) {
            if (this.thisTileE.isSlovak()) {
                this.SlovenskoButton.displayString = I18n.format("gui.general.text.no");
                this.thisTileE.setSlovak(false);
                this.KrizVelkyButton.visible = true;
            } else {
                this.SlovenskoButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setSlovak(true);
                this.KrizVelkyButton.visible = false;
            }
        } else {
            super.actionPerformed(button);
        }
    }
}
