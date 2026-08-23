/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package signalcraft.entities.controllers.universal;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;
import signalcraft.entities.IActivatable;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.switches.IManual;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.models.TextureRegistry;


public class TileReceiverUniversal extends TileReceiver {

    private static final ResourceLocation TEXTURE = TextureRegistry.RECE_UNIVERSAL.get();

    public TileReceiverUniversal() {
        super(TEXTURE);
        this.setName("Receiver");
    }

    public void activate(boolean activate) {
        for (int i = 1; i <= 10; ++i) {
            final TileEntity tileE = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tileE instanceof IActivatable && !worldObj.isRemote) {

                if (tileE instanceof IManual) break;

                SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(xCoord, yCoord + i, zCoord, activate));
                ((IActivatable) tileE).setIsActive(activate);
                break;
            }
        }
    }

    @Override
    public boolean isControllerValid(TileController controller) {
        return controller instanceof IUniversalController;
    }
}
