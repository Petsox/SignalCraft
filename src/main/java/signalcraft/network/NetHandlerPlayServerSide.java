package signalcraft.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import signalcraft.entities.TileSignal;

public class NetHandlerPlayServerSide extends NetHandlerPlayServer {
    private final EntityPlayerMP playerEntity;
    private final MinecraftServer serverController;

    public NetHandlerPlayServerSide(final MinecraftServer serverController, final EntityPlayerMP playerEntity, final NetworkManager networkManager) {
        super(serverController, networkManager, playerEntity);
        this.serverController = serverController;
        this.playerEntity = playerEntity;
    }

    public EntityPlayerMP getPlayerEntity() {
        return this.playerEntity;
    }

    public MinecraftServer getServerController() {
        return this.serverController;
    }

    public void handleUpdateServer(final ClientPacket thePacket) {
        this.playerEntity.func_143004_u();
        final WorldServer worldserver = this.serverController.worldServerForDimension(this.playerEntity.dimension);
        final int x = thePacket.getXCoordinate();
        final int y = thePacket.getYCoordinate();
        final int z = thePacket.getZCoordinate();
        if (worldserver.blockExists(x, y, z)) {
            final TileEntity tileentity = worldserver.getTileEntity(x, y, z);
            if (tileentity instanceof TileSignal) {
                final TileSignal tileSignal = (TileSignal) tileentity;
                tileSignal.readFromNBT(thePacket.getMainNBTTC());
                tileSignal.markDirty();
                worldserver.markBlockForUpdate(x, y, z);
            }
        }
    }
}
