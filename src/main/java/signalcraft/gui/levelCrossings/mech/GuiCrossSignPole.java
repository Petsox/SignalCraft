package signalcraft.gui.levelCrossings.mech;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.levelCrossings.mech.TileCrossSignPole;
import signalcraft.gui.signals.signSignals.GuiSignSignal;

/** Stand-alone pole with the sign and the cross: the same options as the pole on the mechanical barrier. */
public class GuiCrossSignPole extends GuiSignSignal {
    private final TileCrossSignPole thisTile;
    private GuiButton signButton;
    private GuiButton crossButton;
    private GuiButton reflexButton;
    private GuiButton largeButton;
    private GuiButton slovakButton;
    private GuiButton stopButton;
    private GuiButton stripesButton;

    public GuiCrossSignPole(TileCrossSignPole thisTileE) {
        super(thisTileE);
        thisTile = thisTileE;
    }

    @Override
    protected int getDesignWidth() {
        return 400;
    }

    @Override
    protected int getDesignHeight() {
        return 280;
    }

    @Override
    public void initGui() {
        this.buttonList.add(this.signButton = new GuiButton(1, this.width / 2 - 40, this.height / 4 - 5, 40, 20, yesNo(thisTile.hasSign())));
        this.buttonList.add(this.crossButton = new GuiButton(2, this.width / 2 - 40, this.height / 4 + 20, 70, 20, crossText()));
        this.buttonList.add(this.reflexButton = new GuiButton(3, this.width / 2 - 40, this.height / 4 + 45, 40, 20, yesNo(thisTile.isCrossReflective())));
        this.buttonList.add(this.largeButton = new GuiButton(4, this.width / 2 - 40, this.height / 4 + 70, 40, 20, yesNo(thisTile.isCrossLarge())));
        this.buttonList.add(this.slovakButton = new GuiButton(5, this.width / 2 - 40, this.height / 4 + 95, 40, 20, yesNo(thisTile.isCrossSlovak())));
        this.buttonList.add(this.stopButton = new GuiButton(6, this.width / 2 - 40, this.height / 4 + 120, 40, 20, yesNo(thisTile.hasStop())));
        this.buttonList.add(this.stripesButton = new GuiButton(7, this.width / 2 + 130, this.height / 4 + 120, 40, 20, yesNo(thisTile.hasStripes())));
        super.initGui();
    }

    private static String yesNo(boolean value) {
        return I18n.format(value ? "gui.levelcross.mech.lattice.true" : "gui.levelcross.mech.lattice.false");
    }

    private String crossText() {
        if (!thisTile.hasCross()) return I18n.format("gui.levelcross.mech.cross.none");
        return I18n.format(thisTile.isCrossOneTrack() ? "gui.levelcross.mech.cross.one" : "gui.levelcross.mech.cross.more");
    }

    @Override
    public void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 1:
                thisTile.setSign(!thisTile.hasSign());
                this.signButton.displayString = yesNo(thisTile.hasSign());
                break;
            case 2:
                // none -> more tracks -> one track -> none
                if (!thisTile.hasCross()) {
                    thisTile.setCross(true);
                    thisTile.setCrossOneTrack(false);
                } else if (!thisTile.isCrossOneTrack()) {
                    thisTile.setCrossOneTrack(true);
                } else {
                    thisTile.setCross(false);
                }
                this.crossButton.displayString = crossText();
                break;
            case 3:
                thisTile.setCrossReflective(!thisTile.isCrossReflective());
                this.reflexButton.displayString = yesNo(thisTile.isCrossReflective());
                break;
            case 4:
                thisTile.setCrossLarge(!thisTile.isCrossLarge());
                this.largeButton.displayString = yesNo(thisTile.isCrossLarge());
                break;
            case 5:
                thisTile.setCrossSlovak(!thisTile.isCrossSlovak());
                this.slovakButton.displayString = yesNo(thisTile.isCrossSlovak());
                break;
            case 6:
                thisTile.setStop(!thisTile.hasStop());
                this.stopButton.displayString = yesNo(thisTile.hasStop());
                break;
            case 7:
                thisTile.setStripes(!thisTile.hasStripes());
                this.stripesButton.displayString = yesNo(thisTile.hasStripes());
                break;
            default:
                super.actionPerformed(button);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        this.beginContentScale();
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.pole.sign.text"), this.width / 2 - 110, this.height / 4, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.text"), this.width / 2 - 110, this.height / 4 + 25, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.reflex.text"), this.width / 2 - 110, this.height / 4 + 50, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.large.text"), this.width / 2 - 110, this.height / 4 + 75, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.cross.slovak.text"), this.width / 2 - 110, this.height / 4 + 100, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.pole.stop.text"), this.width / 2 - 110, this.height / 4 + 125, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.mech.pole.stripes.text"), this.width / 2 + 70, this.height / 4 + 125, 16777200);
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }
}
