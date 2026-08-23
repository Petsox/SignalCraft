package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZD3Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZD3Lights extends GuiLightSignals {
        public GuiAZD3Lights(TileAZD3Lights tileE) {
                super(tileE);
        }

        @Override
        public void initGui() {
                super.initGui();
                this.PruhyButton.visible = false;
                this.Pruhy3Button.visible = false;
                this.SpeedButton.visible = false;
        }

        @Override
        public void drawScreen(int mouseX, int mouseY, float par3) {
                super.drawScreen(mouseX, mouseY, par3);
        }

        @Override
        protected void mouseClicked(int x, int y, int buttonClicked) {
                super.mouseClicked(x, y, buttonClicked);
        }

        @Override
        protected void keyTyped(char character, int code) {
                super.keyTyped(character, code);
        }

        @Override
        public void updateScreen() {
                super.updateScreen();
        }

        @Override
        public void onGuiClosed() {
                super.onGuiClosed();
        }
}