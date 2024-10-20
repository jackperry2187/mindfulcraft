package jackperry2187.mindfulcraft;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jackperry2187.mindfulcraft.config.InitializeConfig.generateConfigFile;

public class MindfulCraft implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MindfulCraft");
	public static final String MOD_ID = "mindfulcraft";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MindfulCraft...");

		generateConfigFile();

        LOGGER.info("Initialized Successfully!");
	}
}