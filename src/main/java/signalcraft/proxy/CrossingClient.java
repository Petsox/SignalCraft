package signalcraft.proxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import signalcraft.entities.levelCrossings.ICrossingRuntime;
import signalcraft.messages.MessageCrossingDebug;
import signalcraft.messages.MessageCrossingEvent;
import signalcraft.signalUtils.CrossingDebug;

/** Client side of the crossing state sync and of the crossing debug tools (log, F3 overlay). Client only. */
public class CrossingClient {
    private static final int HEARTBEAT_TICKS = 100;
    private int heartbeat = 0;

    public static void onEvent(MessageCrossingEvent message) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world == null || world.provider.dimensionId != message.dimension) return;
        TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
        if (!(tile instanceof ICrossingRuntime)) {
            CrossingDebug.log(world, "EVENT " + message.event + " for " + message.x + "," + message.y + "," + message.z
                    + " ignored - no crossing loaded there on this client");
            return;
        }
        ICrossingRuntime runtime = (ICrossingRuntime) tile;
        String before = CrossingDebug.isClientOn() ? runtime.describeRuntime() : null;
        boolean changed = runtime.applyCrossingEvent(message.event);
        if (before != null) {
            CrossingDebug.log(tile, "EVENT " + message.event + " (server tick " + message.serverTick + ")"
                    + (changed ? "" : " - nothing to change") + " | before {" + before + "} -> after {"
                    + runtime.describeRuntime() + "} | server {" + message.serverState + "}");
        }
    }

    public static void onDebug(MessageCrossingDebug message) {
        Minecraft mc = Minecraft.getMinecraft();
        if (message.action == MessageCrossingDebug.ON) {
            CrossingDebug.setClient(true);
            CrossingDebug.log(true, "ping " + ping(mc) + " ms");
        } else if (message.action == MessageCrossingDebug.OFF) {
            CrossingDebug.setClient(false);
        } else if (message.action == MessageCrossingDebug.DUMP && mc.theWorld != null && mc.thePlayer != null) {
            MessageCrossingDebug.dump(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, message.radius);
        }
    }

    /** This player's ping as shown in the tab list, -1 if unknown. */
    private static int ping(Minecraft mc) {
        if (mc.getNetHandler() == null || mc.thePlayer == null) return -1;
        for (Object o : mc.getNetHandler().playerInfoList) {
            GuiPlayerInfo info = (GuiPlayerInfo) o;
            if (info.name.equals(mc.thePlayer.getCommandSenderName())) return info.responseTime;
        }
        return -1;
    }

    /** Client heartbeat in the log: ping, so a late sync can be told apart from a lagging server. */
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !CrossingDebug.isClientOn()) return;
        if (++heartbeat < HEARTBEAT_TICKS) return;
        heartbeat = 0;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null) return;
        CrossingDebug.log(mc.theWorld, "heartbeat: ping " + ping(mc) + " ms");
    }

    /** F3 overlay while the debug log is on: this client's state of the crossing looked at (and the tiles stacked on it). */
    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Text event) {
        if (!CrossingDebug.isClientOn()) return;
        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition hit = mc.objectMouseOver;
        if (mc.theWorld == null || hit == null || hit.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return;
        event.left.add("");
        event.left.add("[SignalCraft crossing debug - client state]");
        for (int i = 0; i < 4; i++) {
            TileEntity tile = mc.theWorld.getTileEntity(hit.blockX, hit.blockY + i, hit.blockZ);
            if (tile instanceof ICrossingRuntime) {
                event.left.add(CrossingDebug.describe(tile));
                event.left.add("  " + ((ICrossingRuntime) tile).describeRuntime());
            }
        }
    }
}
