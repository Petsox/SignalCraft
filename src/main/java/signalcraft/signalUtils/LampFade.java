package signalcraft.signalUtils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Per-tile brightness state for a set of named lamps, eased continuously toward
 * an on/off target instead of snapping instantly, so lit signal/crossing lamps
 * dim in and out like an incandescent bulb's filament rather than an LED.
 * <p>
 * Model/renderer classes are shared across every tile of a given type in this
 * mod, so this state can't live on them - instantiate one {@code LampFade} per
 * tile entity instead and keep calling {@link #beginFrame} / {@link #step}
 * from that tile's render code every frame.
 */
public class LampFade {
    private static final float RISE_MS = 80.0f;
    private static final float FALL_MS = 100.0f;
    private static final float SNAP_EPSILON = 0.01f;

    /**
     * Idle glow a lamp rests at instead of going fully dark while its owning signal stays active.
     * Renderers that draw this via plain alpha-test glColor4f (not additive blending) double it
     * before passing it to glColor4f, so this must clear the ~0.1 alpha-test cutoff with margin
     * once doubled - landing exactly on the cutoff made the idle lamp fully invisible instead of dim.
     */
    public static final float IDLE_BRIGHTNESS = 0.08f;

    private final Map<String, Float> brightness = new LinkedHashMap<>();
    private long lastFrame = -1L;

    /**
     * Call once per render call, before stepping any individual lamp. Returns the
     * elapsed time in milliseconds since the previous call, capped so a lag spike
     * or a freshly loaded tile doesn't produce a huge single-frame jump.
     */
    public long beginFrame(long now) {
        long dt = (this.lastFrame < 0L) ? 0L : Math.min(now - this.lastFrame, 250L);
        this.lastFrame = now;
        return dt;
    }

    /**
     * Eases the named lamp's brightness toward 1 (on) or 0 (off) and returns the
     * resulting value in [0, 1]. Lamps that reach 0 are forgotten so the tracked
     * set doesn't grow without bound.
     */
    public float step(String key, boolean on, long dtMillis) {
        return step(key, on ? 1.0f : 0.0f, dtMillis);
    }

    /**
     * Eases the named lamp's brightness toward an arbitrary target in [0, 1] (e.g. a
     * dim idle glow rather than fully on or off) and returns the resulting value.
     * Lamps that reach 0 are forgotten so the tracked set doesn't grow without bound.
     */
    public float step(String key, float target, long dtMillis) {
        float current = this.brightness.getOrDefault(key, 0.0f);
        float tau = target >= current ? RISE_MS : FALL_MS;
        float factor = 1.0f - (float) Math.exp(-dtMillis / tau);
        float next = current + (target - current) * factor;
        if (Math.abs(next - target) < SNAP_EPSILON) {
            next = target;
        }
        if (next <= 0.0f) {
            this.brightness.remove(key);
            return 0.0f;
        }
        this.brightness.put(key, next);
        return next;
    }

    /** Lamp keys still fading (brightness > 0) that this frame's target set may no longer include. */
    public Set<String> fadingKeys() {
        return new LinkedHashSet<>(this.brightness.keySet());
    }
}
