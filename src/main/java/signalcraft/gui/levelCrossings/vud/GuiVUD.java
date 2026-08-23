package signalcraft.gui.levelCrossings.vud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.vud.TileVUD;
import signalcraft.gui.levelCrossings.GuiLevelCrossings;

public class GuiVUD extends GuiLevelCrossings {

    private final TileLevelCrossing thisTileE;


    public GuiVUD(TileVUD thisTileE) {
        super(thisTileE);
        this.thisTileE = thisTileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.KrizStozarButton.visible = false;
        this.DistanceButton.visible = false;
        this.PozorButton.visible = false;
        this.PozDylButton.visible = false;
        this.ZebrikButton.visible = false;
        this.OtradoviceButton.visible = false;
        this.LightCoverButton.visible = false;
        this.LightPosButton.visible = false;
        this.textFieldHeadRot.setVisible(false);
        this.LightsAlterButton.visible = false;
        this.NewerButton.visible = false;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 8) {
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
