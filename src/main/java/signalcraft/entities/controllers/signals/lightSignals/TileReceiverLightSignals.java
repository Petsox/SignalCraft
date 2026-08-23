/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package signalcraft.entities.controllers.signals.lightSignals;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.controllers.signals.ISignalReceiver;
import signalcraft.entities.signals.ISignal;
import signalcraft.messages.MessageStateUpdate;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.SignalState;

import java.util.Arrays;


public class TileReceiverLightSignals extends TileReceiver implements ISignalReceiver {

    private static final ResourceLocation TEXTURE = TextureRegistry.RECE_LIGHT_SIGNALS.get();

    public TileReceiverLightSignals() {
        super(TEXTURE);
    }

    private boolean canSetStateForSignal(SignalState state, ISignal tileE) {
        EntityPlayer closest = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, -1);

        if (!Arrays.asList(tileE.getValidStatesForTile()).contains(state)) {
            if (closest != null) {
                closest.addChatMessage(new ChatComponentText("Nelze nastavit " + state + " pro toto navestidlo"));
            }
            return false;
        }
        return true;
    }

    @Override
    public void setState(SignalState state) {
        ISignal signal = getFirstSignal();

        if (signal != null) {
            if (canSetStateForSignal(state, signal)) {
                signal.setState(state);
                BlockPos pos = signal.getWorldPosition();
                if (!worldObj.isRemote) {
                    SignalCraft.SCNet.sendToAll(new MessageStateUpdate(pos.getX(), pos.getY(), pos.getZ(), state.StateToString()));
                }
            }
        }
    }

    private ISignal getFirstSignal() {
        for (int i = 1; i <= 10; ++i) {
            final TileEntity tileE = worldObj.getTileEntity(this.xCoord, this.yCoord + i, this.zCoord);
            if (tileE instanceof ISignal) {
                return (ISignal) tileE;
            }
        }
        return null;
    }

    @Override
    public boolean isControllerValid(TileController controller){
        return controller instanceof ILightSignalsController;
    }

    @Override
    public SignalState getStateOnSignal() {
        if (getFirstSignal() != null) {
            return getFirstSignal().getState();
        }
        return SignalState.ZHAS;
    }

    @Override
    public void setStateToSignalsMostRestrictive() {
        if (getFirstSignal() != null) {
            getFirstSignal().setStateToMostRestrictive();
        }
    }

    @Override
    public SignalState[] getValidStatesForSignal() {
        if (getFirstSignal() != null) {
           return getFirstSignal().getValidStatesForTile();
        }
        return new SignalState[0];
    }
}
