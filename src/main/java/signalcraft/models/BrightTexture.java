package signalcraft.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A texture file loaded brightened: its white point (the lightest grey / white that takes a real part of the picture)
 * is stretched to WHITE_TARGET, the colours with it. For photographs of signs taken in dull light - in the game they
 * are darkened once more by the lighting and look grey next to drawn textures. The file itself is not changed; a
 * picture that is already light stays as it is.
 */
public class BrightTexture extends AbstractTexture
{
    /** where the white of the picture ends up (0 - 255) */
    public static final int WHITE_TARGET = 250;
    /** the most a picture is brightened */
    public static final float MAX_GAIN = 2.2f;
    /** this share of the pixels is lighter than the white point (specks, glare, sky behind the sign) */
    private static final float ABOVE_WHITE = 0.10f;

    private static final Map<ResourceLocation, ResourceLocation> BRIGHT = new HashMap<ResourceLocation, ResourceLocation>();

    private final ResourceLocation source;

    private BrightTexture(ResourceLocation source) {
        this.source = source;
    }

    /** The brightened copy of this texture, to bind instead of it (loaded on the first call, from the render thread). */
    public static ResourceLocation of(ResourceLocation source) {
        ResourceLocation bright = BRIGHT.get(source);
        if (bright == null) {
            bright = new ResourceLocation(source.getResourceDomain(), source.getResourcePath() + ".bright");
            Minecraft.getMinecraft().getTextureManager().loadTexture(bright, new BrightTexture(source));
            BRIGHT.put(source, bright);
        }
        return bright;
    }

    @Override
    public void loadTexture(IResourceManager manager) throws IOException {
        this.deleteGlTexture();
        InputStream in = null;
        try {
            in = manager.getResource(this.source).getInputStream();
            BufferedImage image = ImageIO.read(in);
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), brighten(image), false, false);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private static BufferedImage brighten(BufferedImage image) {
        int width = image.getWidth(), height = image.getHeight();
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
        // white point: the grey level (lowest channel) that only ABOVE_WHITE of the visible pixels exceed
        int[] histogram = new int[256];
        int visible = 0;
        for (int argb : pixels) {
            if ((argb >>> 24) < 128) continue;
            int r = (argb >> 16) & 255, g = (argb >> 8) & 255, b = argb & 255;
            histogram[Math.min(r, Math.min(g, b))]++;
            visible++;
        }
        int white = 255;
        for (int above = 0; white > 1; white--) {
            above += histogram[white];
            if (above > visible * ABOVE_WHITE) break;
        }
        float gain = Math.max(1f, Math.min(MAX_GAIN, (float) WHITE_TARGET / white));
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        if (gain > 1f) {
            for (int i = 0; i < pixels.length; i++) {
                int argb = pixels[i];
                int r = Math.min(255, Math.round(((argb >> 16) & 255) * gain));
                int g = Math.min(255, Math.round(((argb >> 8) & 255) * gain));
                int b = Math.min(255, Math.round((argb & 255) * gain));
                pixels[i] = (argb & 0xFF000000) | (r << 16) | (g << 8) | b;
            }
        }
        out.setRGB(0, 0, width, height, pixels, 0, width);
        return out;
    }
}
