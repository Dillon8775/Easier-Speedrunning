package net.dillon8775.easierspeedrunning.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.*;
import static net.dillon8775.easierspeedrunning.EasierSpeedrunningClient.clientOptions;
import static net.dillon8775.easierspeedrunning.option.ModOptions.doNothing;
import static net.dillon8775.easierspeedrunning.option.ModOptions.isSafe;

/**
 * <p>All the client-side options for the {@code Easier speedrunning mod}.</p>
 * <p>See {@link ModOptions} for more.</p>
 */
public class ClientModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CLIENT_CONFIG = "easier_speedrunning-client-options.json";
    private static File file;
    public static ClientModOptions CLIENT_OPTIONS = getClientConfig();
    // OPTIONS:
    public boolean fog = true;
    @RequiresRestart(true)
    public Panorama panorama = Panorama.EASIER_SPEEDRUNNING;
    public ItemMessages itemMessages = ItemMessages.ACTIONBAR;
    public boolean fastWorldCreation = true;
    public GameMode gameMode = GameMode.SURVIVAL;
    public Difficulty difficulty = Difficulty.EASY;
    public boolean allowCheats = false;
    public static final String MOD_KEYBINDS = "easierspeedrunning.keybinds";
    public static KeyBinding resetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("easierspeedrunning.reset", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MOD_KEYBINDS));
    public static KeyBinding fogKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("easierspeedrunning.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));
    public static KeyBinding hitboxesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("easierspeedrunning.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, MOD_KEYBINDS));
    public static KeyBinding chunkBordersKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("easierspeedrunning.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, MOD_KEYBINDS));

    public enum Panorama {
        SPEEDRUNNER_MOD(0, "easierspeedrunning.options.panorama.speedrunner_mod"),
        EASIER_SPEEDRUNNING(1, "easierspeedrunning.options.panorama.easier_speedrunning"),
        NIGHT(2, "easierspeedrunning.options.panorama.night"),
        CAVE(3, "easierspeedrunning.options.panorama.cave"),
        CLASSIC(4, "easierspeedrunning.options.panorama.classic"),
        EMPTY(5, "easierspeedrunning.options.panorama.empty"),
        DEFAULT(6, "easierspeedrunning.options.panorama.default");

        private static final Panorama[] VALUES = (Panorama[])Arrays.stream(values()).sorted(Comparator.comparingInt(Panorama::getId)).toArray((i) -> {
            return new Panorama[i];
        });
        private final int id;
        private final String translateKey;

        Panorama(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public static Panorama byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public enum GameMode {
        SURVIVAL(0, "easierspeedrunning.options.gamemode.survival"),
        CREATIVE(1, "easierspeedrunning.options.gamemode.creative"),
        SPECTATOR(2, "easierspeedrunning.options.gamemode.spectator");

        private static final GameMode[] VALUES = (GameMode[]) Arrays.stream(values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray((i) -> {
            return new GameMode[i];
        });
        private final int id;
        private final String translateKey;

        GameMode(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public static GameMode byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public enum Difficulty {
        PEACEFUL(0, "easierspeedrunning.options.difficulty.peaceful"),
        EASY(1, "easierspeedrunning.options.difficulty.easy"),
        NORMAL(2, "easierspeedrunning.options.difficulty.normal"),
        HARD(3, "easierspeedrunning.options.difficulty.hard"),
        HARDCORE(4, "easierspeedrunning.options.difficulty.hardcore");

        private static final Difficulty[] VALUES = (Difficulty[]) Arrays.stream(values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray((i) -> {
            return new Difficulty[i];
        });
        private final int id;
        private final String translateKey;

        Difficulty(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public static Difficulty byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public enum ItemMessages {
        ACTIONBAR(0, "easierspeedrunning.options.item_messages.actionbar"),
        CHAT(1, "easierspeedrunning.options.item_messages.chat");

        private static final ItemMessages[] VALUES = (ItemMessages[]) Arrays.stream(values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray((i) -> {
            return new ItemMessages[i];
        });
        private final int id;
        private final String translateKey;

        ItemMessages(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public static ItemMessages byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public static void loadClientConfig() {
        File configFile = getClientConfigFile();

        if (!configFile.exists()) {
            CLIENT_OPTIONS = new ClientModOptions();
            info("Creating easier speedrunning client options file...");
        } else {
            info("Reading easier speedrunning client options...");
            safeCheck();
            readClientConfig();
        }
        saveClientConfig();
    }

    private static void readClientConfig() {
        CLIENT_OPTIONS = getClientConfig();
    }

    public static void saveClientConfig() {
        File file = getClientConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(CLIENT_OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClientConfig(ClientModOptions config) {
        CLIENT_OPTIONS = config;
        saveClientConfig();
    }

    private static ClientModOptions getClientConfig() {
        File file = getClientConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ClientModOptions.class);
        } catch (Exception e) {
            ClientModOptions newconfig = new ClientModOptions();
            setClientConfig(newconfig);
            return newconfig;
        }
    }

    private static File getClientConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CLIENT_CONFIG);
        }
        return file;
    }

    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;

        if (clientOptions().panorama == Panorama.SPEEDRUNNER_MOD ||
                clientOptions().panorama == Panorama.EASIER_SPEEDRUNNING ||
                clientOptions().panorama == Panorama.NIGHT ||
                clientOptions().panorama == Panorama.CAVE ||
                clientOptions().panorama == Panorama.CLASSIC ||
                clientOptions().panorama == Panorama.EMPTY ||
                clientOptions().panorama == Panorama.DEFAULT) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.panorama");
            isSafe(false);
            BrokenModOptions.panorama = true;
        }

        if (clientOptions().gameMode == GameMode.SURVIVAL ||
                clientOptions().gameMode == GameMode.CREATIVE ||
                clientOptions().gameMode == GameMode.SPECTATOR) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.gamemode");
            isSafe(false);
            BrokenModOptions.gameMode = true;
        }

        if (clientOptions().difficulty == Difficulty.PEACEFUL ||
                clientOptions().difficulty == Difficulty.EASY ||
                clientOptions().difficulty == Difficulty.NORMAL ||
                clientOptions().difficulty == Difficulty.HARD ||
                clientOptions().difficulty == Difficulty.HARDCORE) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "easierspeedrunning.options.difficulty");
            isSafe(false);
            BrokenModOptions.difficulty = true;
        }
    }
}