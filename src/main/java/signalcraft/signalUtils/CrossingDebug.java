package signalcraft.signalUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Debug log of everything level crossings do - switched on in game with {@code /scdebug on}.
 * <p>
 * The server and each client write their own file, so the two can be laid side by side:
 * <ul>
 *   <li>{@code logs/signalcraft-crossings-server.log} - on the server (in single player in the game folder too)</li>
 *   <li>{@code logs/signalcraft-crossings-client.log} - on the client of the player who switched it on</li>
 * </ul>
 * Every line has the wall-clock time, the side, the world tick and the tile. State snapshots carry
 * the server tick they were sent at, so the client log shows how late (and how far off) each one
 * arrived. Costs nothing while off.
 */
public final class CrossingDebug {
    private static final SimpleDateFormat TIME = new SimpleDateFormat("HH:mm:ss.SSS");
    private static final int HEARTBEAT_TICKS = 100;

    private static volatile boolean serverOn = false;
    private static volatile boolean clientOn = false;
    private static PrintWriter serverOut;
    private static PrintWriter clientOut;
    private static int heartbeat = 0;

    private CrossingDebug() {}

    public static boolean isOn(World world) {
        return world != null && (world.isRemote ? clientOn : serverOn);
    }

    public static boolean isServerOn() {
        return serverOn;
    }

    public static boolean isClientOn() {
        return clientOn;
    }

    public static synchronized void setServer(boolean on) {
        if (on == serverOn) return;
        if (on) {
            serverOut = open("signalcraft-crossings-server.log");
            serverOn = serverOut != null;
            write(false, "=== crossing debug ON (server) ===");
        } else {
            write(false, "=== crossing debug OFF (server) ===");
            serverOn = false;
            close(serverOut);
            serverOut = null;
        }
    }

    public static synchronized void setClient(boolean on) {
        if (on == clientOn) return;
        if (on) {
            clientOut = open("signalcraft-crossings-client.log");
            clientOn = clientOut != null;
            write(true, "=== crossing debug ON (client) ===");
        } else {
            write(true, "=== crossing debug OFF (client) ===");
            clientOn = false;
            close(clientOut);
            clientOut = null;
        }
    }

    /** Logs an event of a tile, if logging is on for its side. */
    public static void log(TileEntity tile, String message) {
        if (tile == null || !isOn(tile.getWorldObj())) return;
        World world = tile.getWorldObj();
        write(world.isRemote, "tick=" + world.getTotalWorldTime() + " " + describe(tile) + " " + message);
    }

    /** Logs a general event of the given side, if logging is on for it. */
    public static void log(World world, String message) {
        if (!isOn(world)) return;
        write(world.isRemote, "tick=" + world.getTotalWorldTime() + " " + message);
    }

    /** Logs a line on the given side, if logging is on for it. */
    public static void log(boolean client, String message) {
        if (client ? !clientOn : !serverOn) return;
        write(client, message);
    }

    public static String describe(TileEntity tile) {
        return tile.getClass().getSimpleName().replace("Tile", "") + "@" + tile.xCoord + "," + tile.yCoord + "," + tile.zCoord;
    }

    private static synchronized void write(boolean client, String message) {
        PrintWriter out = client ? clientOut : serverOut;
        if (out == null) return;
        out.println(TIME.format(new Date()) + (client ? " [C] " : " [S] ") + message);
    }

    private static PrintWriter open(String name) {
        try {
            File dir = new File("logs");
            if (!dir.exists() && !dir.mkdirs()) return null;
            return new PrintWriter(new FileWriter(new File(dir, name), true), true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void close(PrintWriter out) {
        if (out != null) out.close();
    }

    /** Server heartbeat: how long the server's ticks take, every 5 s while logging - shows lag. */
    public static final class ServerTicker {
        @SubscribeEvent
        public void onServerTick(TickEvent.ServerTickEvent event) {
            if (event.phase != TickEvent.Phase.END || !serverOn) return;
            if (++heartbeat < HEARTBEAT_TICKS) return;
            heartbeat = 0;
            MinecraftServer server = MinecraftServer.getServer();
            if (server == null) return;
            long sum = 0;
            long max = 0;
            for (long t : server.tickTimeArray) {
                sum += t;
                max = Math.max(max, t);
            }
            double meanMs = sum / (double) server.tickTimeArray.length / 1.0E6;
            double tps = Math.min(20.0, 1000.0 / Math.max(meanMs, 1.0E-3));
            write(false, String.format("heartbeat: mean tick %.1f ms (max %.1f ms), ~%.1f TPS", meanMs, max / 1.0E6, tps));
        }
    }
}
