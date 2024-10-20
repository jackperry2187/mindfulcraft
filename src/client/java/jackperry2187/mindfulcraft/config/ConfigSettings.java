package jackperry2187.mindfulcraft.config;

import jackperry2187.mindfulcraft.MindfulCraft;
import jackperry2187.mindfulcraft.util.Message;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ConfigSettings {
    private static boolean isInitialized = false;

    private static final Path configPath = FabricLoader.getInstance().getConfigDir();
    private static final Path modConfigFile = configPath.resolve(MindfulCraft.MOD_ID + "-config.toml");

    private static int configVersion;
    // TODO: private static boolean skipConfigVersionCheck;

    public static boolean enabled;
    public static int ticksBetweenMessages;

    public static List<Message> messages = new ArrayList<>();
    public static int lowestID;
    public static int highestID;

    private static final boolean isDebug = false;
    private static final int debugTicksBetweenMessages = 20 * 5; // 5 seconds

    public static void initialize() {
        if(isInitialized) {
            MindfulCraft.LOGGER.error("ConfigSettings have already been initialized!");
            return;
        }
        if(!Files.exists(modConfigFile)) {
            MindfulCraft.LOGGER.error("Config file does not exist!");
            throw new RuntimeException("Config file does not exist!");
        }

        readConfigFile();
        if(isDebug) ticksBetweenMessages = debugTicksBetweenMessages;

        messages.sort(Comparator.comparingInt(m -> m.ID));
        lowestID = messages.get(1).ID; // skip the initial message
        highestID = messages.get(messages.size() - 1).ID;

        isInitialized = true;
        if(isDebug) logConfigSettings();
    }

    private static void logConfigSettings() {
        MindfulCraft.LOGGER.info("Config Version: {}", configVersion);
        // TODO: MindfulCraft.LOGGER.info("Skip Config Version Check: {}", skipConfigVersionCheck);
        MindfulCraft.LOGGER.info("Enabled: {}", enabled);
        MindfulCraft.LOGGER.info("Time Between Messages: {} ticks, {} minutes", ticksBetweenMessages, ticksBetweenMessages / 20 / 60);
//        MindfulCraft.LOGGER.info("Messages:");
//        for(Message message : messages) {
//            MindfulCraft.LOGGER.info("ID: {}", message.ID);
//            MindfulCraft.LOGGER.info("Title: {}", message.Title.getString());
//            MindfulCraft.LOGGER.info("Message: {}", message.Description.getString());
//            MindfulCraft.LOGGER.info("Enabled: {}", message.Enabled);
//        }
    }

    private static void readConfigFile() {
        try {
            boolean isReadingMessages = false;
            for(String line : Files.readAllLines(modConfigFile)) {
                if (line.startsWith("#")) continue; // skip comments
                if (line.startsWith("configVersion=")) {
                    configVersion = Integer.parseInt(line.split("=")[1]);
                }
                // TODO: else if (line.startsWith("skipConfigVersionCheck=")) { skipConfigVersionCheck = Boolean.parseBoolean(line.split("=")[1]); }
                else if(line.startsWith("enabled=")) {
                    enabled = Boolean.parseBoolean(line.split("=")[1]);
                } else if(line.startsWith("timeBetweenMessages=")) {
                    ticksBetweenMessages = Integer.parseInt(line.split("=")[1]) * 20 * 60; // convert minutes to ticks
                } else if(line.startsWith("messages=[")) {
                    isReadingMessages = true;
                } else if (isReadingMessages) {
                    if(line.startsWith("]")) {
                        isReadingMessages = false;
                    }
                    else {
                        messages.add(parseMessage(line));
                    }
                }
            }
        } catch (Exception e) {
            MindfulCraft.LOGGER.error("Failed to read config file!");
            throw new RuntimeException(e);
        }
    }

    private static Message parseMessage(String line) {
        // line will be in format of:
        // {id=#, title="String", message="String", enabled=boolean, titleColor="String", messageColor="String"},
        // parse the line and create a new Message object
        String[][] parts = Arrays.stream(line.split(",")).map(s -> s.trim().replace("\"", "").split("=")).toArray(String[][]::new);
        int id = Integer.parseInt(parts[0][1]);
        String title = parts[1][1];
        String message = parts[2][1];
        boolean enabled = Boolean.parseBoolean(parts[3][1]);
        String titleColor = parts[4][1];
        String messageColor = parts[5][1];

        Text titleText = MutableText.of(Text.of(title).getContent()).formatted(Formatting.byName(titleColor));
        Text messageText = MutableText.of(Text.of(message).getContent()).formatted(Formatting.byName(messageColor));

        // create a new Message object and add it to the messages list
        return new Message(id, titleText, messageText, enabled);
    }
}
