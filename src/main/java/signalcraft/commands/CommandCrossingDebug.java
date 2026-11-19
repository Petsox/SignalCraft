package signalcraft.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.messages.MessageCrossingDebug;
import signalcraft.signalUtils.CrossingDebug;

import java.util.List;

/**
 * {@code /scdebug on|off|dump [radius]} - the crossing debug log (see {@link CrossingDebug}).
 * <ul>
 *   <li>on / off - logging on the server and on the client of the player who typed it</li>
 *   <li>dump - writes the server's and that client's state of every crossing around into both logs</li>
 * </ul>
 */
public class CommandCrossingDebug extends CommandBase {
    private static final int DEFAULT_RADIUS = 64;

    @Override
    public String getCommandName() {
        return "scdebug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/scdebug <on|off|dump> [radius]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        // single player: allowed even with cheats off - it only writes log files
        MinecraftServer server = MinecraftServer.getServer();
        return (server != null && !server.isDedicatedServer()) || super.canCommandSenderUseCommand(sender);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) throw new WrongUsageException(getCommandUsage(sender));
        EntityPlayerMP player = sender instanceof EntityPlayerMP ? (EntityPlayerMP) sender : null;
        String action = args[0].toLowerCase();
        if ("on".equals(action)) {
            CrossingDebug.setServer(true);
            if (player != null) SignalCraft.SCNet.sendTo(new MessageCrossingDebug(MessageCrossingDebug.ON, 0), player);
            reply(sender, "Crossing debug ON - logs/signalcraft-crossings-server.log (server) and logs/signalcraft-crossings-client.log (your game). F3 shows the crossing you look at.");
        } else if ("off".equals(action)) {
            if (player != null) SignalCraft.SCNet.sendTo(new MessageCrossingDebug(MessageCrossingDebug.OFF, 0), player);
            CrossingDebug.setServer(false);
            reply(sender, "Crossing debug OFF");
        } else if ("dump".equals(action)) {
            int radius = args.length > 1 ? parseIntBounded(sender, args[1], 1, 512) : DEFAULT_RADIUS;
            if (!CrossingDebug.isServerOn()) {
                reply(sender, "Turn the log on first: /scdebug on");
                return;
            }
            World world = sender.getEntityWorld();
            double x = sender.getPlayerCoordinates().posX, y = sender.getPlayerCoordinates().posY, z = sender.getPlayerCoordinates().posZ;
            int count = MessageCrossingDebug.dump(world, x, y, z, radius);
            if (player != null) SignalCraft.SCNet.sendTo(new MessageCrossingDebug(MessageCrossingDebug.DUMP, radius), player);
            reply(sender, "Dumped " + count + " crossing tiles (server) - your client dumps its own into its log");
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "on", "off", "dump") : null;
    }

    private static void reply(ICommandSender sender, String text) {
        sender.addChatMessage(new ChatComponentText("[SignalCraft] " + text));
    }
}
