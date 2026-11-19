package signalcraft.entities.levelCrossings;

import net.minecraft.nbt.NBTTagCompound;

/**
 * The moving part of a crossing's state (active, arm position, timers, pending switch-off) - as
 * opposed to its configuration (looks, sound type, delays set in the GUI).
 * <p>
 * How crossings stay consistent between server and clients:
 * <ul>
 *   <li>Only the server decides <b>events</b> ({@link CrossingEvent}: on, off, off once given
 *       barriers are up). It applies each to its tile and sends it to every client in the
 *       dimension ({@link signalcraft.messages.MessageCrossingEvent}), which apply it to theirs.</li>
 *   <li>Events carry no positions or tick counts. Everything that follows - the arm moving, the bell
 *       going quiet once the arm is down, the lamps going out once the barriers are up - each side
 *       simulates from its own state, the same way.</li>
 *   <li>The full runtime state only travels with the tile's NBT (chunk loading, GUI edits), to put
 *       a client that wasn't there for the events into the right state.</li>
 *   <li>The server brings its state up to the world time before using it (see
 *       TileLevelCrossing.catchUp), so a server that ticks some chunks slowly stays right too.</li>
 * </ul>
 */
public interface ICrossingRuntime {
    /** Writes the runtime state (saved NBT, chunk data). */
    void writeRuntime(NBTTagCompound tag);

    /** Takes over a runtime state written by {@link #writeRuntime}. */
    void readRuntime(NBTTagCompound tag);

    /**
     * Applies an event to this side's own state. The server calls it before sending the event, the
     * clients when they receive it. @return false if the event changed nothing (then it isn't sent).
     */
    boolean applyCrossingEvent(CrossingEvent event);

    /** One-line summary of the runtime state for the debug log and overlay. */
    String describeRuntime();
}
