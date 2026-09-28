package signalcraft.models;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Wraps a directory of individual part .obj files (one unique mesh per file,
 * as produced by tools/objdedupe) plus a CSV placement manifest mapping
 * legacy per-variant group names to a (partId, offset) pair. Callers that
 * used to do {@code modelCustom.renderPart("stozar_5svet_S")} against a large
 * per-variant .obj keep working unchanged against the part library instead.
 */
public class PartLibrary {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final class Instance {
        final String partId;
        final float scale, dx, dy, dz;

        Instance(String partId, float scale, float dx, float dy, float dz) {
            this.partId = partId;
            this.scale = scale;
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;
        }
    }

    private final String partsDirPath;
    private final ResourceLocation manifestLocation;
    private Map<String, Instance> instances;
    private Map<String, IModelCustom> parts;
    private final Set<String> warnedMissing = new HashSet<>();

    /**
     * @param partsDirPath   resource path prefix (e.g. "signalcraft:models/azd70/parts/")
     *                       under which each partId is stored as "<partId>.obj"
     * @param manifestPath   resource path to the placement manifest CSV
     */
    public PartLibrary(String partsDirPath, String manifestPath) {
        this.partsDirPath = partsDirPath;
        this.manifestLocation = new ResourceLocation(manifestPath);
    }

    public void load() {
        if (instances == null) {
            instances = loadManifest(manifestLocation);
        }
        if (parts == null) {
            parts = new HashMap<>();
            for (Instance inst : instances.values()) {
                parts.computeIfAbsent(inst.partId,
                        id -> AdvancedModelLoader.loadModel(new ResourceLocation(partsDirPath + id + ".obj")));
            }
        }
    }

    /**
     * No-ops on an unknown name (logged once) rather than throwing, matching
     * the old {@code IModelCustom.renderPart} contract this replaces: several
     * callers rely on that leniency for sentinel names with no real geometry,
     * e.g. {@link signalcraft.signalUtils.SignalState#ZHAS}'s "nic" lamp key
     * for "no lamp lit", which was never a real group in any source .obj.
     */
    public void renderPart(String legacyName) {
        Instance inst = instances.get(legacyName);
        if (inst == null) {
            if (warnedMissing.add(legacyName)) {
                LOGGER.warn("Unknown part instance '{}' (not present in {}) -- skipping render",
                        legacyName, manifestLocation);
            }
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(inst.dx, inst.dy, inst.dz);
        if (inst.scale != 1.0f) {
            GL11.glScalef(inst.scale, inst.scale, inst.scale);
        }
        parts.get(inst.partId).renderAll();
        GL11.glPopMatrix();
    }

    private static Map<String, Instance> loadManifest(ResourceLocation loc) {
        Map<String, Instance> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream(),
                StandardCharsets.UTF_8))) {
            String line = reader.readLine(); // header: source_files,legacy_group,part_id,scale,dx,dy,dz
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] cols = line.split(",");
                String legacyGroup = cols[1];
                String partId = cols[2];
                float scale = Float.parseFloat(cols[3]);
                float dx = Float.parseFloat(cols[4]);
                float dy = Float.parseFloat(cols[5]);
                float dz = Float.parseFloat(cols[6]);
                map.put(legacyGroup, new Instance(partId, scale, dx, dy, dz));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load part manifest " + loc, e);
        }
        return map;
    }
}
