package net.dillon8775.easierspeedrunning;

import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

/**
 * The main class file for the client-side of the {@code Easier Speedrunning mod}.
 */
@Environment(EnvType.CLIENT)
public class EasierSpeedrunningClient implements ClientModInitializer {

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ClientModOptions.loadClientConfig();

        info("Client-side options have successfully loaded!");
    }

    /**
     * The {@code Easier Speedrunning} client options method.
     */
    public static ClientModOptions clientOptions() {
        return ClientModOptions.CLIENT_OPTIONS;
    }
}