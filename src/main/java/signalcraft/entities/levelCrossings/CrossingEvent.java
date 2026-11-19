package signalcraft.entities.levelCrossings;

import io.netty.buffer.ByteBuf;

/**
 * Something the server decided about a crossing tile. The server applies it to its own tile and
 * sends it unchanged to the clients, which apply it to theirs - each side to its OWN state. No
 * positions or tick counts are sent: an arm keeps moving from wherever that side has it, and
 * "switch off once these barriers are up" is judged by each side against its own barriers. So a
 * server that ticks a chunk slowly (or catches up fast after lag) can't make a client's barrier
 * jump or its lamps go out before its barrier is up.
 */
public final class CrossingEvent {
    public static final byte ON = 1;
    public static final byte OFF = 2;
    public static final byte OFF_WHEN_UP = 3;

    public final byte type;
    /** OFF_WHEN_UP: x, y, z of each barrier to wait for. */
    public final int[] barriers;
    /** OFF_WHEN_UP: ticks to stay on after all those barriers are up. */
    public final int delay;

    private CrossingEvent(byte type, int[] barriers, int delay) {
        this.type = type;
        this.barriers = barriers;
        this.delay = delay;
    }

    public static CrossingEvent on() {
        return new CrossingEvent(ON, new int[0], 0);
    }

    public static CrossingEvent off() {
        return new CrossingEvent(OFF, new int[0], 0);
    }

    /** Switch off once every listed barrier is up, plus delay ticks; no barriers and no delay = now. */
    public static CrossingEvent offWhenUp(int[] barriers, int delay) {
        return new CrossingEvent(OFF_WHEN_UP, barriers.clone(), Math.max(0, delay));
    }

    public void write(ByteBuf buf) {
        buf.writeByte(type);
        buf.writeInt(delay);
        buf.writeShort(barriers.length);
        for (int v : barriers) buf.writeInt(v);
    }

    public static CrossingEvent read(ByteBuf buf) {
        byte type = buf.readByte();
        int delay = buf.readInt();
        int[] barriers = new int[buf.readShort()];
        for (int i = 0; i < barriers.length; i++) barriers[i] = buf.readInt();
        return new CrossingEvent(type, barriers, delay);
    }

    @Override
    public String toString() {
        switch (type) {
            case ON: return "ON";
            case OFF: return "OFF";
            case OFF_WHEN_UP: return "OFF when " + (barriers.length / 3) + " barrier(s) up + " + delay + " ticks";
            default: return "?" + type;
        }
    }
}
