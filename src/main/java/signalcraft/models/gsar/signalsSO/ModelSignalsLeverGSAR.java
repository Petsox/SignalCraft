package signalcraft.models.gsar.signalsSO;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsSO.TileGSARSignalLever;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelSignalsLeverGSAR implements IModelCustom
{
    private final IModelCustom modelSignalsLever = ModelRegistry.GSAR_SIGNAL_LEVER.getModel();
    private final ResourceLocation texture = TextureRegistry.GSAR_SIGNAL_LEVER.get();
    private final ResourceLocation textureClear = TextureRegistry.GSAR_ICON_CLEAR.get();
    private final ResourceLocation textureHP1 = TextureRegistry.GSAR_ICON_HP1.get();
    private final ResourceLocation textureHP2 = TextureRegistry.GSAR_ICON_HP2.get();
    private final ResourceLocation textureWN1 = TextureRegistry.GSAR_ICON_WN1.get();
    private final ResourceLocation textureHL = TextureRegistry.GSAR_ICON_HL.get();
    private final ResourceLocation textureRailcross = TextureRegistry.GSAR_ICON_RAILCROSS.get();
    private final ResourceLocation defaultTexture = new ResourceLocation("textures/blocks/wool_colored_white.png");

    private static final ResourceLocation[] texturesLocation;
    private static final String[] texturesNames = new String[]{"wool_colored_black", "wool_colored_red", "wool_colored_green", "wool_colored_brown", "wool_colored_blue", "wool_colored_purple", "wool_colored_cyan", "wool_colored_silver", "wool_colored_gray", "wool_colored_pink", "wool_colored_lime", "wool_colored_yellow", "wool_colored_light_blue", "wool_colored_magenta", "wool_colored_orange", "wool_colored_white"};

    private ResourceLocation completeResourceLocation(final ResourceLocation location) {
        return new ResourceLocation(location.getResourceDomain(), String.format("%s/%s%s", "textures/blocks", location.getResourcePath(), ".png"));
    }

    public ModelSignalsLeverGSAR() {

    }
    
    public void renderSwitchBase() {
        GL11.glTranslatef(0.0f, -1.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.modelSignalsLever.renderPart("Base01_B01");
    }
    
    public void renderSwitchHebel(TileGSARSignalLever tileGSARSignalLever) {
        GL11.glRotatef((float)(tileGSARSignalLever.getRotate() * 2), 1.0f, 0.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.modelSignalsLever.renderPart("Hebelwerk01_H01");
        final int i = MathHelper.clamp_int(tileGSARSignalLever.getLeverTexture(), 0, texturesLocation.length - 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.completeResourceLocation(texturesLocation[i]));
        this.modelSignalsLever.renderPart("Hebelwerk02_H02");
    }
    
    public void renderSwitchHP1(TileGSARSignalLever tileGSARSignalLever) {
        int mode = Integer.parseInt(tileGSARSignalLever.getSignID());
        GL11.glPushMatrix();
        switch (mode) {
            case 1:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureHP1);
                break;
            case 2:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureHP2);
                break;
            case 3:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureWN1);
                break;
            case 4:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureHL);
                break;
            case 5:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureRailcross);
                break;
            default:
                Minecraft.getMinecraft().renderEngine.bindTexture(this.textureClear);
                break;
        }
        this.modelSignalsLever.renderPart("SignalSign01_SS01");
        GL11.glPopMatrix();
    }

    public void renderItem() {
        GL11.glPushMatrix();
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.modelSignalsLever.renderPart("Base01_B01");
        this.modelSignalsLever.renderPart("Hebelwerk01_H01");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.defaultTexture);
        this.modelSignalsLever.renderPart("Hebelwerk02_H02");
        GL11.glPopMatrix();
    }

    static {
        final int subTextures = texturesNames.length;
        texturesLocation = new ResourceLocation[subTextures];
        for (int i = 0; i < subTextures; ++i) {
            texturesLocation[i] = new ResourceLocation(texturesNames[i]);
        }
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public void renderAll() {

    }

    @Override
    public void renderOnly(String... strings) {

    }

    @Override
    public void renderPart(String s) {

    }

    @Override
    public void renderAllExcept(String... strings) {

    }
}
