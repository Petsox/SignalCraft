package signalcraft.fonts;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BetterFontRenderer extends FontRenderer {
    public StringCache stringCache;
    private boolean bidiFlag;
    public boolean dropShadowEnabled = true;
    private int[] colorCode = new int[32];
    private float red;
    private float blue;
    private float green;
    private float alpha;
    private boolean randomStyle;
    private boolean boldStyle;
    private boolean italicStyle;
    private boolean underlineStyle;
    private boolean strikethroughStyle;
    private int textColor;
    private final boolean unicodeFlag;

    public BetterFontRenderer(final GameSettings par1GameSettings, final ResourceLocation par2ResourceLocation, final TextureManager par3TextureManager, final boolean unicode) {
        super(par1GameSettings, par2ResourceLocation, par3TextureManager, unicode);
        this.unicodeFlag = unicode;

        for(int i = 0; i < 32; ++i) {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i >> 0 & 1) * 170 + j;
            if (i == 6) {
                k += 85;
            }

            if (par1GameSettings.anaglyph) {
                int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
                int k1 = (k * 30 + l * 70) / 100;
                int l1 = (k * 30 + i1 * 70) / 100;
                k = j1;
                l = k1;
                i1 = l1;
            }

            if (i >= 16) {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.colorCode[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }

        if (this.stringCache == null) {
            this.stringCache = new StringCache(this.colorCode);

            String fontName = "din1451alt";
            int fontSize = 18;
            boolean antiAlias = true;
            dropShadowEnabled = true;

            this.stringCache.setDefaultFont(fontName, fontSize, antiAlias);
        }
    }

    private String bidiReorder(String p_147647_1_) {
        if (this.stringCache != null) {
            return p_147647_1_;
        }
        try {
            Bidi bidi = new Bidi((new ArabicShaping(8)).shape(p_147647_1_), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        } catch (ArabicShapingException var3) {
            return p_147647_1_;
        }
    }

    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    private int renderString(String p_78258_1_, int x, int y, int p_78258_4_, boolean p_78258_5_) {
        if (p_78258_1_ == null) {
            return 0;
        } else {
            if (this.bidiFlag) {
                p_78258_1_ = this.bidiReorder(p_78258_1_);
            }

            if ((p_78258_4_ & -67108864) == 0) {
                p_78258_4_ |= -16777216;
            }

            if (p_78258_5_) {
                p_78258_4_ = (p_78258_4_ & 16579836) >> 2 | p_78258_4_ & -16777216;
            }

            this.red = (float)(p_78258_4_ >> 16 & 255) / 255.0F;
            this.blue = (float)(p_78258_4_ >> 8 & 255) / 255.0F;
            this.green = (float)(p_78258_4_ & 255) / 255.0F;
            this.alpha = (float)(p_78258_4_ >> 24 & 255) / 255.0F;
            this.setColor(this.red, this.blue, this.green, this.alpha);
            this.posX = (float)x;
            this.posY = (float)y;
            if (this.stringCache != null) {
                this.posX += stringCache.renderString(p_78258_1_, x, y, p_78258_4_, p_78258_5_);
            } else {
                this.renderStringAtPos(p_78258_1_, p_78258_5_);
            }
            return (int)this.posX;
        }
    }

    private float renderCharAtPos(int p_78278_1_, char p_78278_2_, boolean p_78278_3_) {
        return p_78278_2_ == ' ' ? 4.0F : ("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".indexOf(p_78278_2_) != -1 && !this.unicodeFlag ? this.renderDefaultChar(p_78278_1_, p_78278_3_) : this.renderUnicodeChar(p_78278_2_, p_78278_3_));
    }

    private void renderStringAtPos(String p_78255_1_, boolean p_78255_2_) {
        for(int i = 0; i < p_78255_1_.length(); ++i) {
            char c0 = p_78255_1_.charAt(i);
            if (c0 == 167 && i + 1 < p_78255_1_.length()) {
                int j = "0123456789abcdefklmnor".indexOf(p_78255_1_.toLowerCase().charAt(i + 1));
                if (j < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (j < 0 || j > 15) {
                        j = 15;
                    }

                    if (p_78255_2_) {
                        j += 16;
                    }

                    int k = this.colorCode[j];
                    this.textColor = k;
                    this.setColor((float)(k >> 16) / 255.0F, (float)(k >> 8 & 255) / 255.0F, (float)(k & 255) / 255.0F, this.alpha);
                } else if (j == 16) {
                    this.randomStyle = true;
                } else if (j == 17) {
                    this.boldStyle = true;
                } else if (j == 18) {
                    this.strikethroughStyle = true;
                } else if (j == 19) {
                    this.underlineStyle = true;
                } else if (j == 20) {
                    this.italicStyle = true;
                } else if (j == 21) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    this.setColor(this.red, this.blue, this.green, this.alpha);
                }

                ++i;
            } else {
                int j = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".indexOf(c0);
                if (this.randomStyle && j != -1) {
                    int k;
                    do {
                        k = this.fontRandom.nextInt(this.charWidth.length);
                    } while(this.charWidth[j] != this.charWidth[k]);

                    j = k;
                }

                float f1 = this.unicodeFlag ? 0.5F : 1.0F;
                boolean flag1 = (c0 == 0 || j == -1 || this.unicodeFlag) && p_78255_2_;
                if (flag1) {
                    this.posX -= f1;
                    this.posY -= f1;
                }

                float f = this.renderCharAtPos(j, c0, this.italicStyle);
                if (flag1) {
                    this.posX += f1;
                    this.posY += f1;
                }

                if (this.boldStyle) {
                    this.posX += f1;
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

                    ++f;
                }

                this.doDraw(f);
            }
        }
    }

    @Override
    public int getStringWidth(String p_78256_1_) {
        if (this.stringCache != null) {
            return this.stringCache.getStringWidth(p_78256_1_);
        }
        return super.getStringWidth(p_78256_1_);
    }

    @Override
    public String trimStringToWidth(String p_78262_1_, int p_78262_2_, boolean p_78262_3_) {
        if (this.stringCache != null) {
            return this.stringCache.trimStringToWidth(p_78262_1_, p_78262_2_, p_78262_3_);
        }
        return super.trimStringToWidth(p_78262_1_, p_78262_2_, p_78262_3_);
    }

    private int sizeStringToWidth(String p_78259_1_, int p_78259_2_) {
        if (this.stringCache != null) {
            return this.stringCache.sizeStringToWidth(p_78259_1_, p_78259_2_);
        }

        int j = p_78259_1_.length();
        int k = 0;
        int l = 0;
        int i1 = -1;

        for(boolean flag = false; l < j; ++l) {
            char c0 = p_78259_1_.charAt(l);
            switch (c0) {
                case '\n':
                    --l;
                    break;
                case ' ':
                    i1 = l;
                default:
                    k += this.getCharWidth(c0);
                    if (flag) {
                        ++k;
                    }
                    break;
                case '§':
                    if (l < j - 1) {
                        ++l;
                        char c1 = p_78259_1_.charAt(l);
                        if (c1 != 'l' && c1 != 'L') {
                            if (c1 == 'r' || c1 == 'R' || isFormatColor(c1)) {
                                flag = false;
                            }
                        } else {
                            flag = true;
                        }
                    }
            }

            if (c0 == '\n') {
                ++l;
                i1 = l;
                break;
            }

            if (k > p_78259_2_) {
                break;
            }
        }

        return l != j && i1 != -1 && i1 < l ? i1 : l;
    }

    private int renderString(String par1Str, final float f, final float g, int par4, final boolean par5) {
        if (par1Str == null) {
            return 0;
        }
        if (this.bidiFlag) {
            par1Str = this.bidiReorder(par1Str);
        }
        if ((par4 & 0xFC000000) == 0x0) {
            par4 |= 0xFF000000;
        }
        if (par5) {
            par4 = ((par4 & 0xFCFCFC) >> 2 | (par4 & 0xFF000000));
        }
        this.red = (par4 >> 16 & 0xFF) / 255.0f;
        this.blue = (par4 >> 8 & 0xFF) / 255.0f;
        this.green = (par4 & 0xFF) / 255.0f;
        this.alpha = (par4 >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
        this.posX = f;
        this.posY = g;
        this.renderStringAtPos(par1Str, par5);
        return (int)this.posX;
    }

    public int drawString(final String par1Str, final float par2, final float par3, final int par4) {
        return this.drawString(par1Str, par2, par3, par4, false);
    }

    public int drawString(final String par1Str, final float par2, final float par3, final int par4, final boolean par5) {
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        this.resetStyles();
        int l;
        if (par5) {
            l = this.renderString(par1Str, par2 + 1.0f, par3 + 1.0f, par4, true);
            l = Math.max(l, this.renderString(par1Str, par2, par3, par4, false));
        }
        else {
            l = this.renderString(par1Str, par2, par3, par4, false);
        }
        return l;
    }

    private static boolean isFormatColor(char p_78272_0_) {
        return p_78272_0_ >= '0' && p_78272_0_ <= '9' || p_78272_0_ >= 'a' && p_78272_0_ <= 'f' || p_78272_0_ >= 'A' && p_78272_0_ <= 'F';
    }

    @Override
    public int drawString(String p_85187_1_, int p_85187_2_, int p_85187_3_, int p_85187_4_, boolean p_85187_5_) {
        this.enableAlpha();
        this.resetStyles();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        int l;
        if (p_85187_5_ && this.dropShadowEnabled) {
            l = this.renderString(p_85187_1_, p_85187_2_ + 1, p_85187_3_ + 1, p_85187_4_, true);
            l = Math.max(l, this.renderString(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false));
        } else {
            l = this.renderString(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false);
        }

        return l;
    }


}
