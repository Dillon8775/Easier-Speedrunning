package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.dillon8775.easierspeedrunning.option.ModListOptions;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.io.File;
import java.util.List;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

@Environment(EnvType.CLIENT)
public class ModOptionsScreen extends GameOptionsScreen {
    public Text BLANK_TEXT = new LiteralText("");
    private static final Option[] OPTIONS;
    private ButtonListWidget list;
    private final Screen parent;
    private final File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toUri());

    public ModOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS);
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModListOptions.STRUCTURE_SPAWN_RATES);
        this.list.addSingleOptionEntry(ModListOptions.PANORAMA);
        this.list.addSingleOptionEntry(ModListOptions.MOB_SPAWNER_MINIMUM_SPAWN_DURATION);
        this.list.addSingleOptionEntry(ModListOptions.MOB_SPAWNER_MAXIMUM_SPAWN_DURATION);
        this.list.addAll(OPTIONS);
        this.addSelectableChild(this.list);

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 10, this.height - 29, 140, 20, ModTexts.MENU_OPEN_OPTIONS_DIRECTORY, (button) -> {
            Util.getOperatingSystem().open(this.configDirectory);
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 150, this.height - 29, 140, 20, ModTexts.SAVE, (button) -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        if (RestartRequiredScreen.needsRestart()) {
            this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
        } else {
            ModOptions.saveConfig();
            ClientModOptions.saveClientConfig();
            info("Saved changes");
            this.client.setScreen(this.parent);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        VideoOptionsScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
        List<OrderedText> list = VideoOptionsScreen.getHoveredButtonTooltip(this.list, mouseX, mouseY);
        if (list != null) {
            this.renderOrderedTooltip(matrices, list, mouseX, mouseY);
        }
    }

    static {
        OPTIONS = new Option[]{
                ModListOptions.FASTER_BLOCK_BREAKING,
                ModListOptions.FOG,

                ModListOptions.DRAGON_PERCH_TIME,
                ModListOptions.FIREPROOF_ITEMS,

                ModListOptions.FAST_WORLD_CREATION,
                ModListOptions.GAMEMODE,

                ModListOptions.DIFFICULTY,
                ModListOptions.ALLOW_CHEATS,

                ModListOptions.ICARUS_MODE,
                ModListOptions.INFINI_PEARL_MODE,

                ModListOptions.DOOM_MODE,
                ModListOptions.BETTER_FOODS,

                ModListOptions.HIGHER_BREATH_TIME,
                ModListOptions.MODIFIED_BIOMES,

                ModListOptions.NETHER_WATER,
                ModListOptions.LAVA_BOATS,

                ModListOptions.FALL_DAMAGE,
                ModListOptions.ITEM_MESSAGES,

                ModListOptions.BETTER_ANVIL,
                ModListOptions.ANVIL_COST_LIMIT,

                Option.GAMMA,
                ModListOptions.STRONGHOLD_PORTAL_ROOM_COUNT,

                ModListOptions.STRONGHOLD_LIBRARY_COUNT,
                ModListOptions.STRONGHOLD_DISTANCE,

                ModListOptions.STRONGHOLD_SPREAD,
                ModListOptions.STRONGHOLD_COUNT,

                ModListOptions.NETHER_PORTAL_COOLDOWN,
                ModListOptions.KILL_GHAST_ON_FIREBALL
        };
    }
}