package signalcraft.models.gsar.signalsSH;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.entities.gsar.signalsHP.TileGSARSemiSignal;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11W;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11WL;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11Y;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11YL;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHLSingle;
import signalcraft.entities.gsar.signalsSH.TileGSARSemiSignalSHF;
import signalcraft.entities.gsar.signalsSH.TileGSARSemiSignalSHFSingle;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.models.gsar.ISemiSignalModelGSAR;
import signalcraft.signalUtils.SignalState;

public class ModelGSARSignalSHF implements ISemiSignalModelGSAR {
    private final IModelCustom modelSignalRA11 = ModelRegistry.GSAR_SH.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();


    @Override
    public void renderStoz(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);

        if (tileSignal instanceof TileGSARSemiSignalSHFSingle) {
            GL11.glTranslatef(0.0f, -5.0f, 0.0f);
            this.modelSignalRA11.renderPart("SignalBox01Stativ_SB01S");
        }

        if (tileSignal instanceof TileGSARSemiSignalSHF) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelSignalRA11.renderPart("Mast011_MA011");
            this.modelSignalRA11.renderPart("Mast02_MA02");
            this.modelSignalRA11.renderPart("Mast03_MA03");
        }

        this.modelSignalRA11.renderPart("SignalBox02_SB02");
    }

    @Override
    public void renderNavestidlo(TileGSARSemiSignal tileSignal) {}

    @Override
    public void renderRamena(TileGSARSemiSignal tileSignal) {
        GL11.glTranslatef(0.0f, 2.7f, 0.0f);
        GL11.glRotatef((float)tileSignal.getArm1Rotation(), 0.0f, 0.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSignalRA11.renderPart("SH1Box_SHB");
    }

    @Override
    public void renderKabel(TileGSARSemiSignal tileSignal) {}

    @Override
    public void renderNavest(SignalState SigState, TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        this.modelSignalRA11.renderPart("SignalBox02L_SBL02");
    }

    @Override
    public void renderItem(TileGSARSemiSignal tileSignal) {
        if(tileSignal instanceof TileGSARSemiSignalSHFSingle) {
            GL11.glTranslatef(0.0f, 0.5f, 0.0f);
        }
        GL11.glTranslatef(0.0f, 2.7f, 0.0f);
        renderStoz(tileSignal);
        renderNavest(SignalState.ACTIVATE, tileSignal);
        renderRamena(tileSignal);
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public void renderAll() {}

    @Override
    public void renderOnly(String... strings) {}

    @Override
    public void renderPart(String s) {}

    @Override
    public void renderAllExcept(String... strings) {}
}