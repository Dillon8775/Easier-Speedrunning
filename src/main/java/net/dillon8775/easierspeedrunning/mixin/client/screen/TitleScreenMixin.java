package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.client.screen.ModMenuScreen;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunningClient.clientOptions;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow
    public static final CubeMapRenderer PANORAMA_CUBE_MAP;
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    private ButtonWidget createWorldButton;
    private ButtonWidget optionsButton;

    public TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        createWorldButton = this.addButton(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.openScreen(new CreateWorldScreen(this));
        }));
        createWorldButton.active = clientOptions().fastWorldCreation;

        optionsButton = this.addButton(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, new LiteralText(""), (button) -> {
            this.client.openScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
        }));
    }

    /**
     * <p>Fixes the {@code speedrunner edition} logo rendering incorrectly.</p>
     * <p>Using {@link ModifyArgs}, we can get the values inside the "drawTexture" method, and overwrite those values.</p>
     */
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V"))
    private void speedrunnerEdition(Args args) {
        int j = this.width / 2 - 137;
        args.set(1, j + 58);
        args.set(5, 184);
        args.set(7, 184);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButtonTextures(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.client.getTextureManager().bindTexture(new Identifier("easierspeedrunning:speedrun.png"));
        drawTexture(matrices, this.width / 2 - 122, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        this.client.getTextureManager().bindTexture(new Identifier("minecraft:textures/mob_effect/speed.png"));
        drawTexture(matrices, this.width / 2 - 123, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        drawStringWithShadow(matrices, this.textRenderer, EasierSpeedrunning.EASIER_SPEEDRUNNING_STRING + " " + EasierSpeedrunning.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }

    static {
        if (clientOptions().panorama == ClientModOptions.Panorama.SPEEDRUNNER_MOD) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/speedrunnermod/panorama"));
        } else if (clientOptions().panorama == ClientModOptions.Panorama.EASIER_SPEEDRUNNING) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/easierspeedrunning/panorama"));
        } else if (clientOptions().panorama == ClientModOptions.Panorama.NIGHT) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/night/panorama"));
        } else if (clientOptions().panorama == ClientModOptions.Panorama.CAVE) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/cave/panorama"));
        } else if (clientOptions().panorama == ClientModOptions.Panorama.CLASSIC) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/classic/panorama"));
        } else if (clientOptions().panorama == ClientModOptions.Panorama.EMPTY) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(EasierSpeedrunning.MOD_ID, "textures/gui/title/background/empty/panorama"));
        } else {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
        }
    }
}