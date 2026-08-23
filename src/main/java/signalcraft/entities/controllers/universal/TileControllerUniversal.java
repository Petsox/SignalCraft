/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package signalcraft.entities.controllers.universal;

import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.models.TextureRegistry;


public class TileControllerUniversal extends TileController implements IUniversalController{

    private static final ResourceLocation TEXTURE = TextureRegistry.CONT_UNIVERSAL.get();

    public TileControllerUniversal() {
        super(TEXTURE);
        this.setName("Controller");
    }

    public void activateReceivers(boolean activate) {
        for (TileReceiver receiver : this.getReceivers()) {
            if (receiver instanceof TileReceiverUniversal){
                ((TileReceiverUniversal) receiver).activate(activate);
            }
        }
    }
}
