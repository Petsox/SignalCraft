package signalcraft.fonts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;

public class GSARFontRendererDin1451Alt extends GSARFontRenderer
{
    private static final ResourceLocation[] unicodePageLocations;
    private final ResourceLocation locationFontTexture;
    private final TextureManager renderEngine;
    private final boolean unicodeFlag;
    private final int[] colorCode;
    private final byte[] glyphWidth;
    
    public GSARFontRendererDin1451Alt(final GameSettings par1GameSettings, final ResourceLocation par2ResourceLocation, final TextureManager par3TextureManager, final boolean unicode) {
        super(par1GameSettings, par2ResourceLocation, par3TextureManager, unicode);
        this.colorCode = new int[32];
        this.glyphWidth = new byte[65536];
        this.locationFontTexture = par2ResourceLocation;
        this.renderEngine = par3TextureManager;
        this.unicodeFlag = unicode;
        par3TextureManager.bindTexture(this.locationFontTexture);
        for (int i = 0; i < 32; ++i) {
            final int j = (i >> 3 & 0x1) * 85;
            int k = (i >> 2 & 0x1) * 170 + j;
            int l = (i >> 1 & 0x1) * 170 + j;
            int i2 = (i & 0x1) * 170 + j;
            if (i == 6) {
                k += 85;
            }
            if (par1GameSettings.anaglyph) {
                final int j2 = (k * 30 + l * 59 + i2 * 11) / 100;
                final int k2 = (k * 30 + l * 70) / 100;
                final int l2 = (k * 30 + i2 * 70) / 100;
                k = j2;
                l = k2;
                i2 = l2;
            }
            if (i >= 16) {
                k /= 4;
                l /= 4;
                i2 /= 4;
            }
            this.colorCode[i] = ((k & 0xFF) << 16 | (l & 0xFF) << 8 | (i2 & 0xFF));
        }
        this.readGlyphSizes();
    }
    
    public ResourceLocation getUnicodePageLocation(final int par1) {
        if (GSARFontRendererDin1451Alt.unicodePageLocations[par1] == null) {
            GSARFontRendererDin1451Alt.unicodePageLocations[par1] = new ResourceLocation(String.format("signalcraft:fonts/din1451alt.png", par1));
        }
        return GSARFontRendererDin1451Alt.unicodePageLocations[par1];
    }
    
    private void readGlyphSizes() {
        try {
            final InputStream inputstream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/glyph_sizes.bin")).getInputStream();
            inputstream.read(this.glyphWidth);
        }
        catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }
    }
    
    static {
        unicodePageLocations = new ResourceLocation[256];
    }
}
