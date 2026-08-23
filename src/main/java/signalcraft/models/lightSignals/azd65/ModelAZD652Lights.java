package signalcraft.models.lightSignals.azd65;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD652Lights extends ModelAZD65 {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD62_2LIGHT.getModel();;
    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stozar_2svet" + PNLight);

    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("odjezd_2svet");
        this.modelLightSignals.renderPart("odjezd_2svet_" + Pos);
    }

    @Override
    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (Pos.equals("S")){
            this.modelLightSignals.renderPart("skupinove_2svet" + PNLight + "_S");
        } else {
            this.modelLightSignals.renderPart("skupinove_2svet" + PNLight + "_RL");
        }
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);

        GL11.glDisable(GL11.GL_CULL_FACE);
        this.modelLightSignals.renderPart("stit_2svet" + PNLight + "_" + Pos);
        this.modelLightSignals.renderPart("uchyty_2svet" + PNLight + "_" + Pos);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    //Render Návěstí
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String pos, String PNLight) {
        if (SigState.equals(SignalState.ALL)) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
            for (String state : SignalState.getPossibleColorsFromStates(tileSignal)) {
                String pozice;
                if (tileSignal.hasPNLight().Boo){
                    pozice = state + "_2svetPN_" + pos;
                } else {
                    pozice = state + "_2svet_" + pos + "_" + tileSignal.getType();
                }
                this.modelLightSignals.renderPart(pozice);
            }
            return;
        }
        for (String state : SigState.signals) {
            String pozice;
            if (tileSignal.hasPNLight().Boo){
                pozice = state + "_2svetPN_" + pos;
            } else {
                pozice = state + "_2svet_" + pos + "_" + tileSignal.getType();
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
            if (SignalState.getIsStateBlink(state, SigState)) {
                if (SigState.blinkSlow != null || SigState.blinkFast != null) {
                    if (SigState.blinkSlow == null) {
                        if (tileSignal.getBlinkCounterFast() < 6) {
                            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
                        } else Minecraft.getMinecraft().renderEngine.bindTexture(this.CockyOff);
                    } else {
                        if (tileSignal.getBlinkCounter() < 11) {
                            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
                        } else Minecraft.getMinecraft().renderEngine.bindTexture(this.CockyOff);
                    }
                }
            }
            this.modelLightSignals.renderPart(pozice);
        }
    }
}
