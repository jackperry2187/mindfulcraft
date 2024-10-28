package jackperry2187.mindfulcraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jackperry2187.mindfulcraft.config.InitializeConfig.generateConfigFile;

public class MindfulCraft implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MindfulCraft");
	public static final String MOD_ID = "mindfulcraft";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MindfulCraft...");

		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			generateConfigFile();
		} else {
			LOGGER.warn("MindfulCraft is a client-side mod and will not be loaded on the server!");
		}

        LOGGER.info("Initialized Successfully!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}