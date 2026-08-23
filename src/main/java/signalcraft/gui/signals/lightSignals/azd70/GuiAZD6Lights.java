package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZD6Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZD6Lights extends GuiLightSignals {
        public GuiAZD6Lights(TileAZD6Lights tileE) {
                super(tileE);
        }

        @Override
        public void initGui() {
                super.initGui();
                this.TypeButton.visible = false;
                this.Pruhy3Button.visible = false;
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