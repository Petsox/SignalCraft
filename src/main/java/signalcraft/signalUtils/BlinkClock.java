package signalcraft.signalUtils;

/**
 * Client-side clock that blinking lamps and their bells run from - plain real time at 20
 * "ticks" per second, with no link to the server's world time at all.
 * <p>
 * Lamps and sounds used to follow the server's world time, so a lagging server made them slow
 * down, speed up and skip, and the server-played bell landed out of step with the lamps.
 * Now the server only decides whether a crossing is active (and where its arms are), and each
 * client blinks the lamps and rings the bell from this clock by itself - steady no matter how
 * the server is doing, and always in step with each other because they share it.
 */
public final class BlinkClock {
    private static final long EPOCH = System.nanoTime();
    private static final double NANOS_PER_TICK = 50_000_000.0;

    private BlinkClock() {}

    /** Real time in ticks, with a fractional part that keeps advancing between ticks. */
    public static double getTicks() {
        return (System.nanoTime() - EPOCH) / NANOS_PER_TICK;
    }

    /** Blink phase in [0, period). */
    public static int getPhase(int period) {
        if (period <= 0) return 0;
        return (int) (((long) Math.floor(getTicks())) % period);
    }

    /**
     * Per-tile helper for firing sounds at fixed points of a blink cycle. Client tile ticks
     * don't line up exactly with {@link BlinkClock}'s ticks (and a slow frame can skip one), so
     * instead of checking "is the phase exactly X right now" - which could miss or double a ring -
     * this checks whether phase X was passed since the previous tile tick.
     */
    public static final class Beat {
        /** A gap longer than this (chunk was unloaded, game was paused) is treated as one tick. */
        private static final long MAX_GAP = 40;
        private long prev = -1;
        private long now = -1;

        /** Call once at the start of every client tile tick, before any {@link #hit}. */
        public void tick() {
            long current = (long) Math.floor(getTicks());
            this.prev = (this.now < 0 || current - this.now > MAX_GAP) ? current - 1 : this.now;
            this.now = current;
        }

        /** Whether the phase {@code value} of a {@code period}-long cycle was reached since the previous tick. */
        public boolean hit(int period, int value) {
            return hit(period, value, 0);
        }

        /** Like {@link #hit(int, int)}, for a cycle that started at tick {@code origin} (see {@link Activation#since()}). */
        public boolean hit(int period, int value, long origin) {
            if (period <= 0 || this.now < 0) return false;
            // never look back past the start of the cycle (a skipped tick right at activation)
            long from = Math.max(this.prev + 1, origin);
            long first = from + Math.floorMod(value + origin - from, (long) period);
            return first <= this.now;
        }
    }

    /**
     * Per-tile helper that remembers when (on this client's clock) a crossing switched on, so its
     * blink cycle starts right then - first flash immediately on activation - instead of joining
     * a shared cycle partway through. Picks up the switch-on lazily from whichever asks first
     * (render or tile tick), so it works however the active state arrived (packet, chunk load).
     */
    public static final class Activation {
        private long since = -1;

        /** Ticks this has been active for, or 0 while inactive. */
        public long ticksActive(boolean active) {
            if (!active) {
                this.since = -1;
                return 0;
            }
            long now = (long) Math.floor(getTicks());
            if (this.since < 0) this.since = now;
            return now - this.since;
        }

        /** Phase in [0, period) of a cycle that started on activation; 0 while inactive. */
        public int getPhase(boolean active, int period) {
            if (period <= 0) return 0;
            return (int) (ticksActive(active) % period);
        }

        /** Tick the current activation started at - pass to {@link Beat#hit(int, int, long)}. */
        public long since() {
            return Math.max(this.since, 0);
        }
    }
}
