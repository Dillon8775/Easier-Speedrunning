package net.dillon8775.easierspeedrunning.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.MathHelper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.*;

public class ModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static final String CONFIG = "easier_speedrunning-options.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();
    // OPTIONS:
    @RequiresRestart(true)
    public StructureSpawnRates structureSpawnRates = StructureSpawnRates.COMMON;
    public boolean fasterBlockBreaking = true;
    @RequiresRestart(true)
    public boolean modifiedBiomes = true;
    public boolean betterFoods = true;
    public boolean fireproofItems = true;
    public int dragonPerchTime = 30;
    public int netherPortalCooldown = 2;
    public boolean iCarusMode = false;
    public boolean infiniPearlMode = false;
    @RequiresRestart(true)
    public boolean doomMode = false;
    public boolean netherWater = true;
    public boolean lavaBoats = true;
    public boolean fallDamage = true;
    public boolean killGhastOnFireball = false;
    public boolean betterAnvil = true;
    public int anvilCostLimit = 10;
    public boolean higherBreathTime = true;
    @RequiresRestart(true)
    public int strongholdPortalRoomCount = 3;
    @RequiresRestart(true)
    public int strongholdLibraryCount = 2;
    @RequiresRestart(true)
    public int strongholdDistance = 4;
    @RequiresRestart(true)
    public int strongholdSpread = 3;
    @RequiresRestart(true)
    public int strongholdCount = 128;
    public int mobSpawnerMinimumSpawnDuration = 20;
    public int mobSpawnerMaximumSpawnDuration = 40;

    public enum StructureSpawnRates {
        EVERYWHERE(0, "easierspeedrunning.options.structure_spawn_rates.everywhere"),
        VERY_COMMON(1, "easierspeedrunning.options.structure_spawn_rates.very_common"),
        COMMON(2, "easierspeedrunning.options.structure_spawn_rates.common"),
        NORMAL(3, "easierspeedrunning.options.structure_spawn_rates.normal"),
        DEFAULT(4, "easierspeedrunning.options.structure_spawn_rates.default"),
        RARE(5, "easierspeedrunning.options.structure_spawn_rates.rare"),
        VERY_RARE(6, "easierspeedrunning.options.structure_spawn_rates.very_rare"),
        OFF(7, "easierspeedrunning.options.structure_spawn_rates.off");

        private static final StructureSpawnRates[] VALUES = (StructureSpawnRates[]) Arrays.stream(values()).sorted(Comparator.comparingInt(StructureSpawnRates::getId)).toArray((i) -> {
            return new StructureSpawnRates[i];
        });
        private final int id;
        private final String translateKey;

        StructureSpawnRates(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public static StructureSpawnRates byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public static void loadConfig() {
        File configFile = getConfigFile();

        if (!configFile.exists()) {
            OPTIONS = new ModOptions();
            info("Creating easier speedrunning options file...");
        } else {
            info("Reading easier speedrunning options...");
            check();
            safeCheck();
            readConfig();
        }
        saveConfig();
    }

    private static void readConfig() {
        OPTIONS = getConfig();
    }

    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    private static ModOptions getConfig() {
        File file = getConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setConfig(newconfig);
            return newconfig;
        }
    }

    private static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    private static void check() {
        if (DOOM_MODE) {
            if (options().strongholdLibraryCount > 5) {
                options().strongholdLibraryCount = 5;
                warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
            }
        }
    }

    @Deprecated
    public static void isSafe(boolean safe) {
        EasierSpeedrunning.safeBoot = !safe;
    }

    @Deprecated
    protected static void doNothing() {
    }

    @Deprecated
    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;

        if (options().structureSpawnRates == StructureSpawnRates.EVERYWHERE ||
                options().structureSpawnRates == StructureSpawnRates.VERY_COMMON ||
                options().structureSpawnRates == StructureSpawnRates.COMMON ||
                options().structureSpawnRates == StructureSpawnRates.NORMAL ||
                options().structureSpawnRates == StructureSpawnRates.DEFAULT ||
                options().structureSpawnRates == StructureSpawnRates.RARE ||
                options().structureSpawnRates == StructureSpawnRates.VERY_RARE ||
                options().structureSpawnRates == StructureSpawnRates.OFF) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.structureSpawnRates");
            isSafe(false);
            BrokenModOptions.structureSpawnRates = true;
        }

        if (options().dragonPerchTime >= 21 &&
                options().dragonPerchTime <= 90) {
            doNothing();
        } else {
            warn(OPTIONS_WARNING_MESSAGE + related + "easierspeedrunning.options.dragonPerchTime");
        }

        if (options().strongholdPortalRoomCount >= 1 &&
                options().strongholdPortalRoomCount <= 10) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.strongholdPortalRoomCount");
            isSafe(false);
            BrokenModOptions.strongholdPortalRoomCount = true;
        }

        if (options().strongholdLibraryCount >= 1 &&
                options().strongholdLibraryCount <= 10) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.strongholdLibraryCount");
            isSafe(false);
            BrokenModOptions.strongholdLibraryCount = true;
        }

        if (options().anvilCostLimit >= 1 &&
                options().anvilCostLimit <= 50 ||
                options().anvilCostLimit > 50) {
            doNothing();
        } else {
            warn(OPTIONS_WARNING_MESSAGE + related + "easierspeedrunning.options.anvilCostLimit");
        }

        if (options().netherPortalCooldown >= 0) {
            doNothing();
        } else {
            warn(OPTIONS_WARNING_MESSAGE + related + "easierspeedrunning.options.netherPortalCooldown");
        }
    }
}