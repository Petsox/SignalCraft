package signalcraft.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

public interface IGeneric {
    @SideOnly(Side.CLIENT)
    ResourceLocation getTexture();

    @SideOnly(Side.CLIENT)
    IModelCustom getModel();

    @SideOnly(Side.CLIENT)
    boolean doesRenderGenericString();

    boolean needsRod();
}
