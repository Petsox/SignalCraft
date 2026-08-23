package signalcraft.fonts;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class DinFontRenderer extends BetterFontRenderer {
    public DinFontRenderer(final GameSettings par1GameSettings, final ResourceLocation par2ResourceLocation, final TextureManager par3TextureManager, final boolean unicode) {
        super(par1GameSettings, par2ResourceLocation, par3TextureManager, unicode);

        String fontName = "din1451alt";
        int fontSize = 48;
        boolean antiAlias = true;

        if (super.stringCache != null) {
            super.stringCache.setDefaultFont(fontName, fontSize, antiAlias);
        }
    }
}
