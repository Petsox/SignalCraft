package signalcraft.entities.controllers;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.IGeneric;
import signalcraft.entities.TileSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileContReceBase extends TileSignal {

    private ResourceLocation texture = TextureRegistry.CONT_RECE_BASE.get();

    public TileContReceBase(ResourceLocation texture) {
        this.texture = texture;
    }

    public TileContReceBase() {
        setName(I18n.format("gui.general.text.crafting"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("Name", this.Name);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.Name = compound.getString("Name");
    }

    public ResourceLocation getTexture() {
        return texture;
    }

}
