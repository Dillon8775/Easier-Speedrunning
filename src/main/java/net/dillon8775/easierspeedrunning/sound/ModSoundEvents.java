package net.dillon8775.easierspeedrunning.sound;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

/**
 * The {@link EasierSpeedrunning} sound effects.
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA =
            Registry.register(Registry.SOUND_EVENT, "entity.boat.paddle_lava",
                    new SoundEvent(new Identifier(EasierSpeedrunning.MOD_ID, "entity.boat.paddle_lava")));

    public static void init() {
        info("Initialized mod sounds.");
    }
}