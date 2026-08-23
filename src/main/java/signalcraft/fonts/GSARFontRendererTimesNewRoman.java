package signalcraft.fonts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.InputStream;

public class GSARFontRendererTimesNewRoman extends GSARFontRenderer
{
    private static final ResourceLocation[] unicodePageLocations;
    private final ResourceLocation locationFontTexture;
    private final TextureManager renderEngine;
    private final boolean unicodeFlag;
    private final int[] colorCode;
    private final byte[] glyphWidth;
    
    public GSARFontRendererTimesNewRoman(final GameSettings par1GameSettings, final ResourceLocation par2ResourceLocation, final TextureManager par3TextureManager, final boolean unicode) {
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
        if (GSARFontRendererTimesNewRoman.unicodePageLocations[par1] == null) {
            GSARFontRendererTimesNewRoman.unicodePageLocations[par1] = new ResourceLocation(String.format("germansignsatrails:fonts/times_unicode_page_00.png", par1));
        }
        return GSARFontRendererTimesNewRoman.unicodePageLocations[par1];
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
    
    protected void renderStringAtPos(final String par1Str, final boolean par2) {
        for (int i = 0; i < par1Str.length(); ++i) {
            final char c0 = par1Str.charAt(i);
            if (c0 == '�' && i + 1 < par1Str.length()) {
                int j = "0123456789abcdefklmnor".indexOf(par1Str.toLowerCase().charAt(i + 1));
                if (j < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (j < 0 || j > 15) {
                        j = 15;
                    }
                    if (par2) {
                        j += 16;
                    }
                    final int k = this.colorCode[j];
                    this.textColor = k;
                    GL11.glColor4f((k >> 16) / 255.0f, (k >> 8 & 0xFF) / 255.0f, (k & 0xFF) / 255.0f, this.alpha);
                }
                else if (j == 16) {
                    this.randomStyle = true;
                }
                else if (j == 17) {
                    this.boldStyle = true;
                }
                else if (j == 18) {
                    this.strikethroughStyle = true;
                }
                else if (j == 19) {
                    this.underlineStyle = true;
                }
                else if (j == 20) {
                    this.italicStyle = true;
                }
                else if (j == 21) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
                }
                ++i;
            }
            else {
                int j = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8?\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1???��???��\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261�\u2265\u2264\u2320\u2321\u00f7\u2248�\u2219�\u221a\u207f?\u25a0\u0000".indexOf(c0);
                if (this.randomStyle && j != -1) {
                    int k;
                    do {
                        k = this.fontRandom.nextInt(this.charWidth.length);
                    } while (this.charWidth[j] != this.charWidth[k]);
                    j = k;
                }
                final float f1 = this.unicodeFlag ? 0.5f : 1.0f;
                final boolean flag1 = (c0 == '\0' || j == -1 || this.unicodeFlag) && par2;
                if (flag1) {
                    this.posX -= f1;
                    this.posY -= f1;
                }
                float f2 = this.renderCharAtPos(j, c0, this.italicStyle);
                if (flag1) {
                    this.posX += f1;
                    this.posY += f1;
                }
                if (this.boldStyle) {
                    this.posX += f1 - 0.33f;
                    if (flag1) {
                        this.posX -= f1;
                        this.posY -= f1;
                    }
                    this.renderCharAtPos(j, c0, this.italicStyle);
                    this.posX -= f1;
                    if (flag1) {
                        this.posX += f1;
                        this.posY += f1;
                    }
                    ++f2;
                }
                if (this.strikethroughStyle) {
                    final Tessellator tessellator = Tessellator.instance;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.startDrawingQuads();
                    tessellator.addVertex(this.posX, this.posY + (double) this.FONT_HEIGHT / 2 + 0.25f, 0.0);
                    tessellator.addVertex(this.posX + f2, this.posY + (double) this.FONT_HEIGHT / 2 + 0.25f, 0.0);
                    tessellator.addVertex(this.posX + f2, this.posY + (double) this.FONT_HEIGHT / 2 - 0.25f, 0.0);
                    tessellator.addVertex(this.posX, this.posY + (double) this.FONT_HEIGHT / 2 - 0.25f, 0.0);
                    tessellator.draw();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }
                if (this.underlineStyle) {
                    final Tessellator tessellator = Tessellator.instance;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.startDrawingQuads();
                    final int l = this.underlineStyle ? -1 : 0;
                    tessellator.addVertex(this.posX + l, this.posY + this.FONT_HEIGHT - 0.5f, 0.0);
                    tessellator.addVertex(this.posX + f2, this.posY + this.FONT_HEIGHT - 0.5f, 0.0);
                    tessellator.addVertex(this.posX + f2, this.posY + this.FONT_HEIGHT - 1.0f, 0.0);
                    tessellator.addVertex(this.posX + l, this.posY + this.FONT_HEIGHT - 1.0f, 0.0);
                    tessellator.draw();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }
                this.posX += (int)f2;
            }
        }
    }
    
    protected float renderUnicodeChar(final char par1, final boolean par2) {
        if (this.glyphWidth[par1] == 0) {
            return 0.0f;
        }
        final int i = par1 / '\u0100';
        this.loadGlyphTexture(i);
        final int j = this.glyphWidth[par1] >>> 4;
        final int k = this.glyphWidth[par1] & 0xF;
        final float g1 = k + 2.9f;
        final float f = (float)j;
        final float f2 = (float)(k + 1);
        final float f3 = par1 % '\u0010' * 16 + f;
        final float f4 = (float)((par1 & '\u00ff') / 16 * 16);
        final float f5 = g1 - f - 0.02f;
        final float f6 = par2 ? 1.0f : 0.0f;
        GL11.glBegin(5);
        GL11.glTexCoord2f(f3 / 256.0f, f4 / 256.0f);
        GL11.glVertex3f(this.posX + f6, this.posY, 0.0f);
        GL11.glTexCoord2f(f3 / 256.0f, (f4 + 15.98f) / 256.0f);
        GL11.glVertex3f(this.posX - f6, this.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((f3 + f5) / 256.0f, f4 / 256.0f);
        GL11.glVertex3f(this.posX + f5 / 2.0f + f6, this.posY, 0.0f);
        GL11.glTexCoord2f((f3 + f5) / 256.0f, (f4 + 15.98f) / 256.0f);
        GL11.glVertex3f(this.posX + f5 / 2.0f - f6, this.posY + 7.99f, 0.0f);
        GL11.glEnd();
        return (g1 - f) / 3.0f + 0.5f;
    }
    
    static {
        unicodePageLocations = new ResourceLocation[256];
    }
}
