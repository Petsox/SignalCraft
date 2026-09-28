package signalcraft.models;

import signalcraft.SignalCraft;

/**
 * Shared part libraries assembled by tools/objdedupe from formerly-duplicated
 * per-variant .obj files. Loaded once at client startup alongside
 * {@link ModelRegistry}.
 */
public enum PartLibraryRegistry {
    AZD70(SignalCraft.MOD_ID + ":models/azd70/parts/",
            SignalCraft.MOD_ID + ":models/azd70/parts/azd70_instances.csv"),
    SSSR_NAV(SignalCraft.MOD_ID + ":models/sssr_nav/parts/",
            SignalCraft.MOD_ID + ":models/sssr_nav/parts/sssr_instances.csv"),
    ;

    private final PartLibrary library;

    PartLibraryRegistry(String objPath, String manifestPath) {
        this.library = new PartLibrary(objPath, manifestPath);
    }

    public void load() {
        library.load();
    }

    public PartLibrary get() {
        return library;
    }
}
