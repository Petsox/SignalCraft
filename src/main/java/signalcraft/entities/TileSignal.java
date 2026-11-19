package signalcraft.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import signalcraft.signalUtils.Consts;

public abstract class TileSignal extends TileEntity {
    private Consts.GuiIDs guiId;
    protected String Name = "ERROR";

    public TileSignal() {
        this.guiId = Consts.GuiIDs.NOGUI;
    }

    public Consts.GuiIDs getGuiId() {
        return guiId;
    }

    public void setGuiId(Consts.GuiIDs guiId) {
        this.guiId = guiId;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return this.Name;
    }

    /** Model scale set with the Scaler item, 1 = as modelled; for tiles without a scale setting of their own. */
    private float modelScale = 1f;

    /**
     * The model scale (Scaler item). Tiles with their own scale setting (light / mechanical signals, crossings,
     * switches) override this and its limits to use that setting, so the Scaler and their GUI agree.
     */
    public float getModelScale() {
        return this.modelScale;
    }

    /** Sets the model scale, kept within this tile's limits. */
    public void setModelScale(float scale) {
        this.modelScale = clampModelScale(scale);
    }

    public float getMinModelScale() {
        return 0.1f;
    }

    public float getMaxModelScale() {
        return 2.0f;
    }

    public final float clampModelScale(float scale) {
        return Math.max(this.getMinModelScale(), Math.min(this.getMaxModelScale(), scale));
    }

    /** Whether the Scaler can scale this tile at all. */
    public boolean isScalable() {
        return true;
    }

    /**
     * true: the model scale is applied around this tile's own renderer by ScaledTileRenderer;
     * false: the tile's renderer scales the model itself.
     */
    public boolean isScaledByWrapper() {
        return true;
    }

    @Override
    public void readFromNBT(final NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.modelScale = tag.hasKey("modelScale") ? tag.getFloat("modelScale") : 1f;
    }

    @Override
    public void writeToNBT(final NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setFloat("modelScale", this.modelScale);
    }

    @Override
    public Packet getDescriptionPacket() {
        final NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
    }

    @Override
    public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 16384D;
    }
}
