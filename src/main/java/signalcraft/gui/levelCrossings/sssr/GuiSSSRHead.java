package signalcraft.gui.levelCrossings.sssr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSRHead;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;
import signalcraft.signalUtils.Consts;

public class GuiSSSRHead extends GuiLevelCrossings {
    private final TileLevelCrossing thisTileE;

    public GuiSSSRHead(TileSSSRHead thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }
    @Override
    public void initGui() {
        super.initGui();
        this.KrizStozarButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.PozorButton.visible = false;
        this.ZebrikButton.visible = false;
        this.PruhyButton.visible = false;
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
