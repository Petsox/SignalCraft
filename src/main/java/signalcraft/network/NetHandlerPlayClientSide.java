package signalcraft.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.TileSignal;
import signalcraft.gui.SCGuis;

@SideOnly(Side.CLIENT)
public class NetHandlerPlayClientSide extends NetHandlerPlayClient {
    private final WorldClient clientWorldController;
    private final Minecraft gameController;
    private final GuiScreen guiScreenServer;

    public NetHandlerPlayClientSide(final Minecraft gameController, final GuiScreen guiScreenServer, final NetworkManager networkManager) {
        super(gameController, guiScreenServer, networkManager);
        this.gameController = gameController;
        this.guiScreenServer = guiScreenServer;
        this.clientWorldController = this.gameController.theWorld;
    }

    public void handleUpdateClient(final ServerPacket thePacket) {
        final WorldClient worldClient = this.gameController.theWorld;
        final int x = thePacket.getXCoordinate();
        final int y = thePacket.getYCoordinate();
        final int z = thePacket.getZCoordinate();
        if (worldClient.blockExists(x, y, z)) {
            final TileEntity tileentity = worldClient.getTileEntity(x, y, z);
            if (tileentity instanceof TileSignal) {
                final TileSignal tileSignal = (TileSignal) tileentity;
                tileSignal.readFromNBT(thePacket.getMainNBTTC());
                tileSignal.markDirty();
            }
        }
    }

    public void handleEditorOpen(final ServerPacket thePacket) {
        final int x = thePacket.getXCoordinate();
        final int y = thePacket.getYCoordinate();
        final int z = thePacket.getZCoordinate();
        final int guiID = thePacket.getGuiId();
        TileEntity tileEntity = this.gameController.theWorld.getTileEntity(x, y, z);
        if (guiID != 0 && !SCGuis.isTileValidForGui(guiID, tileEntity)) {
            tileEntity = SCGuis.getTileByGuiId(guiID);

            assert tileEntity != null;
            tileEntity.setWorldObj(this.gameController.theWorld);
            tileEntity.xCoord = x;
            tileEntity.yCoord = y;
            tileEntity.zCoord = z;
        }
        if (guiID != 0) {
            this.gameController.displayGuiScreen(SCGuis.handleGuiById(guiID, tileEntity));
        }
    }
}
