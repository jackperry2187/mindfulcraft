package jackperry2187.mindfulcraft.config;

import jackperry2187.mindfulcraft.MindfulCraft;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static jackperry2187.mindfulcraft.config.ConfigLines.getBaseConfigLines;

public class InitializeConfig {
    private final static Path configPath = FabricLoader.getInstance().getConfigDir();
    private final static Path modConfigFile = configPath.resolve(MindfulCraft.MOD_ID + "-config.toml");

    public static void generateConfigFile() {
        try {
            if(Files.exists(modConfigFile)) {
                MindfulCraft.LOGGER.info("Config file already exists!");
                if (!checkConfigVersion()) {
                    MindfulCraft.LOGGER.info("Config version is different, overwriting config file!");
                    Files.write(modConfigFile, getBaseConfigLines(), StandardOpenOption.TRUNCATE_EXISTING);
                }
                MindfulCraft.LOGGER.info("Config version is correct!");
            } else {
                MindfulCraft.LOGGER.info("Creating config file!");
                // create file and add default information to file
                Files.createFile(modConfigFile);
                Files.write(modConfigFile,  getBaseConfigLines(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            MindfulCraft.LOGGER.error("Failed to create config file!");
            throw new RuntimeException(e);
        }
    }

    public static boolean checkConfigVersion() throws IOException {
        if(!Files.exists(modConfigFile)) Files.createFile(modConfigFile);
        boolean isCorrectVersion = false;

        for(String line : Files.readAllLines(modConfigFile)) {
            if(line.startsWith("#")) continue; // skip comments
            if(line.startsWith("configVersion=")) {
                String version = line.split("=")[1];
                if(Integer.parseInt(version) == DefaultSettings.configVersion) {
                    isCorrectVersion = true;
                    break;
                }
            }
        }

        return isCorrectVersion;
    }
}
