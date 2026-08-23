package signalcraft.gui.gsar;

import signalcraft.gui.gsar.buttons.GuiColorButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiColorPicker {

    private final List buttonList;
    private final int startId;
    private final int startX;
    private final int startY;

    public GuiColorPicker(List buttonList, int startId, int startX, int startY) {
        this.buttonList = buttonList;
        this.startId = startId;
        this.startX = startX;
        this.startY = startY;
    }

    public void addPalette() {

        for (int i = 0; i < COLORS.length; i++) {

            int row = i / 4;
            int col = i % 4;

            buttonList.add(
                    new GuiColorButton(
                            startId + i,
                            startX + col * 20,
                            startY + row * 20,
                            20,
                            20,
                            COLORS[i]
                    )
            );
        }
    }

    public List<GuiColorButton> getPaletteButtons() {
        List<GuiColorButton> colorButtons = new ArrayList<>();
        for (int i = 0; i < buttonList.toArray().length; i++) {
            if (buttonList.get(i) instanceof GuiColorButton) {
                colorButtons.add((GuiColorButton) buttonList.get(i));
            }
        }
        return colorButtons;
    }

    public static final Color[] COLORS = {
            Color.decode("#000000"),
            Color.decode("#800000"),
            Color.decode("#008000"),
            Color.decode("#482E1C"),
            Color.decode("#0000FF"),
            Color.decode("#800080"),
            Color.decode("#008080"),
            Color.decode("#808080"),
            Color.decode("#3B3B3B"),
            Color.decode("#D692A4"),
            Color.decode("#50C244"),
            Color.decode("#FFFF00"),
            Color.decode("#8EA6D6"),
            Color.decode("#FF00FF"),
            Color.decode("#DD8347"),
            Color.decode("#FFFFFF")
    };
}