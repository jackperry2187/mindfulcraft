package jackperry2187.mindfulcraft.clientCommands;

import com.mojang.brigadier.context.CommandContext;
import jackperry2187.mindfulcraft.MindfulCraftClient;
import jackperry2187.mindfulcraft.config.ConfigSettings;
import jackperry2187.mindfulcraft.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static jackperry2187.mindfulcraft.config.EditConfigSettings.*;

@Environment(value = EnvType.CLIENT)
public class CommandInit {
    public static int help(CommandContext<FabricClientCommandSource> context) {
        FabricClientCommandSource source = context.getSource();

        source.sendFeedback(Text.literal("Thank you for downloading MindfulCraft!").formatted(Formatting.AQUA));
        source.sendFeedback(Text.literal("Please enjoy the usage and descriptions of the commands found below.").formatted(Formatting.AQUA));
        source.sendFeedback(Text.literal("Commands are in ").formatted(Formatting.AQUA).append("GOLD").formatted(Formatting.GOLD).append(Text.literal(" and descriptions are in ").formatted(Formatting.AQUA).append("GRAY").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Required arguments for the commands are in ").formatted(Formatting.AQUA).append("GREEN").formatted(Formatting.GREEN));
        source.sendFeedback(Text.literal("Commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft").formatted(Formatting.GOLD).append(Text.literal(" - Displays this help message.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft help").formatted(Formatting.GOLD).append(Text.literal(" - Displays this help message.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Get commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft get TimeRemaining").formatted(Formatting.GOLD).append(Text.literal(" - Displays the time remaining until the next mindful message.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft get ConfigPath").formatted(Formatting.GOLD).append(Text.literal(" - Displays the path to the config file.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft get ConfigVersion").formatted(Formatting.GOLD).append(Text.literal(" - Displays the version of the config file.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft get TicksBetweenMessages").formatted(Formatting.GOLD).append(Text.literal(" - Displays the number of ticks between messages, as well as minutes and seconds.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft get Messages").formatted(Formatting.GOLD).append(Text.literal(" [pageNumber]").formatted(Formatting.GREEN)).append(Text.literal(" - Displays the messages in the config file.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Set commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft set TicksBetweenMessages").formatted(Formatting.GOLD).append(Text.literal(" [ticks]").formatted(Formatting.GREEN)).append(Text.literal(" - Sets the number of ticks between messages. Remember there are 20 ticks per second when inputting a number.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft set Message").formatted(Formatting.GOLD).append(Text.literal(" [id] [title] [message] [enabled] [titleColor] [messageColor]").formatted(Formatting.GREEN)).append(Text.literal(" - Overwrites the title, message, enabled status, and colors of a message by ID.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Add commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft add Message").formatted(Formatting.GOLD).append(Text.literal(" [title] [message] [enabled] [titleColor] [messageColor]").formatted(Formatting.GREEN)).append(Text.literal(" - Adds a message to the config file.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Remove commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft remove Message").formatted(Formatting.GOLD).append(Text.literal(" [id]").formatted(Formatting.GREEN)).append(Text.literal(" - Removes a message from the config file by ID.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Enable commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft enable MindfulCraft").formatted(Formatting.GOLD).append(Text.literal(" - Enables the mod.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft enable Message").formatted(Formatting.GOLD).append(Text.literal(" [id]").formatted(Formatting.GREEN)).append(Text.literal(" - Enables a message by ID.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("Disable commands:").formatted(Formatting.LIGHT_PURPLE));
        source.sendFeedback(Text.literal("/mindfulcraft disable MindfulCraft").formatted(Formatting.GOLD).append(Text.literal(" - Disables the mod.").formatted(Formatting.GRAY)));
        source.sendFeedback(Text.literal("/mindfulcraft disable Message").formatted(Formatting.GOLD).append(Text.literal(" [id]").formatted(Formatting.GREEN)).append(Text.literal(" - Disables a message by ID.").formatted(Formatting.GRAY)));

        return 1;
    }

    public static int getTimeUntilNextMessage(CommandContext<FabricClientCommandSource> context) {
        // account for the fact that the ticker counter is NOT reset to 0 when the message is sent
        int remainingTime = (ConfigSettings.ticksBetweenMessages - (MindfulCraftClient.tickCounter % ConfigSettings.ticksBetweenMessages));
        // ticks -> minutes
        double remainingTimeInMinutes = (double) remainingTime / 20 / 60;
        // truncate the decimal
        int remainingMinutes = (int) remainingTimeInMinutes;
        // get the remaining seconds from the remainder of the decimal
        int remainingSeconds = (int) ((remainingTimeInMinutes - remainingMinutes) * 60);

        context.getSource().sendFeedback(Text.literal("Remaining time until next mindful message: ").formatted(Formatting.AQUA));
        context.getSource().sendFeedback(Text.literal((remainingMinutes > 0 ? remainingMinutes + " minutes and " : "") + remainingSeconds + " seconds").formatted(Formatting.GOLD));

        return 1;
    }

    public static int getConfigPath(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal("Config path:").formatted(Formatting.AQUA));
        context.getSource().sendFeedback(Text.literal(ConfigSettings.configPath + "").formatted(Formatting.GOLD));
        return 1;
    }

    public static int getConfigVersion(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal("Config version:").formatted(Formatting.AQUA));
        context.getSource().sendFeedback(Text.literal(ConfigSettings.configVersion + "").formatted(Formatting.GOLD));
        return 1;
    }

    public static int getTicksBetweenMessages(CommandContext<FabricClientCommandSource> context) {
        // ticks -> minutes
        double remainingTimeInMinutes = (double) ConfigSettings.ticksBetweenMessages / 20 / 60;
        // truncate the decimal
        int remainingMinutes = (int) remainingTimeInMinutes;
        // get the remaining seconds from the remainder of the decimal
        int remainingSeconds = (int) ((remainingTimeInMinutes - remainingMinutes) * 60);

        context.getSource().sendFeedback(Text.literal("Ticks between messages:").formatted(Formatting.AQUA));
        context.getSource().sendFeedback(Text.literal(ConfigSettings.ticksBetweenMessages
                + ", which is "
                + (remainingMinutes > 0 ? remainingMinutes + " minutes" : "")
                + (remainingMinutes > 0 && remainingSeconds > 0 ? " and " : "")
                + (remainingSeconds > 0 ? remainingSeconds + " seconds" : "")
        ).formatted(Formatting.GOLD));
        return 1;
    }

    public static int getMessages(CommandContext<FabricClientCommandSource> context) {
        Integer pageNumber = context.getArgument("pageNumber", Integer.class);
        List<Message> messages = ConfigSettings.messages;

        int totalPages = (int) Math.ceil((double) messages.size() / 5);
        int startIndex = (pageNumber - 1) * 5;
        int endIndex = Math.min(pageNumber * 5, messages.size());

        context.getSource().sendFeedback(Text.literal("Messages:").formatted(Formatting.AQUA));
        for (int i = startIndex; i < endIndex; i++) {
            Message message = messages.get(i);
            context.getSource().sendFeedback(Text.literal("ID: ").formatted(Formatting.GOLD).append(Text.literal(message.ID + "").formatted(Formatting.AQUA)));
            context.getSource().sendFeedback(Text.literal("Title: ").formatted(Formatting.GOLD).append(message.Title));
            context.getSource().sendFeedback(Text.literal("Description: ").formatted(Formatting.GOLD).append(message.Description));
            context.getSource().sendFeedback(Text.literal("Enabled: ").formatted(Formatting.GOLD).append(Text.literal(message.Enabled + "").formatted(message.Enabled ? Formatting.GREEN : Formatting.RED)));
        }

        context.getSource().sendFeedback(Text.literal("Page ").formatted(Formatting.AQUA).append(Text.literal(pageNumber + "").formatted(Formatting.GOLD)).append(Text.literal(" of ").formatted(Formatting.AQUA).append(Text.literal(totalPages + "").formatted(Formatting.GOLD))));

        return 1;
    }

    public static int setTicksBetweenMessages(CommandContext<FabricClientCommandSource> context) {
        Integer ticks = context.getArgument("ticks", Integer.class);
        ConfigSettings.ticksBetweenMessages = ticks;
        boolean fileSuccess = setFileValue("timeBetweenMessages", Math.max(ticks/20/60, 1) + "");

        context.getSource().sendFeedback(Text.literal("Ticks between messages has been set to ").formatted(Formatting.AQUA).append(Text.literal(ticks + "").formatted(Formatting.GOLD)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int setMessage(CommandContext<FabricClientCommandSource> context) {
        Integer id = context.getArgument("id", Integer.class);
        String title = context.getArgument("title", String.class);
        String message = context.getArgument("message", String.class);
        boolean enabled = context.getArgument("enabled", Boolean.class);
        Formatting titleColor = context.getArgument("titleColor", Formatting.class);
        Formatting messageColor = context.getArgument("messageColor", Formatting.class);
        Text formattedTitle = Text.literal(title).formatted(titleColor);
        Text formattedMessage = Text.literal(message).formatted(messageColor);

        boolean foundMessage = false;

        for (Message m : ConfigSettings.messages) {
            if (m.ID != id) continue;
            m.Title = formattedTitle;
            m.Description = formattedMessage;
            m.Enabled = enabled;
            foundMessage = true;
            break;
        }

        if(!foundMessage) {
            context.getSource().sendFeedback(Text.literal("Message with ID " + id + " not found!").formatted(Formatting.RED));
            return 1;
        }

        boolean fileSuccess = setFileMessage(id, new Message(id, formattedTitle, formattedMessage, enabled));

        context.getSource().sendFeedback(Text.literal("Message with ID ").formatted(Formatting.AQUA).append(Text.literal(id + "").formatted(Formatting.GOLD)).append(Text.literal(" has been updated.").formatted(Formatting.AQUA)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int addMessage(CommandContext<FabricClientCommandSource> context) {
        int id = ConfigSettings.highestMessageID + 1;

        String title = context.getArgument("title", String.class);
        String message = context.getArgument("message", String.class);
        boolean enabled = context.getArgument("enabled", Boolean.class);
        Formatting titleColor = context.getArgument("titleColor", Formatting.class);
        Formatting messageColor = context.getArgument("messageColor", Formatting.class);
        Text formattedTitle = Text.literal(title).formatted(titleColor);
        Text formattedMessage = Text.literal(message).formatted(messageColor);

        ConfigSettings.messages.add(new Message(id, formattedTitle, formattedMessage, enabled));
        ConfigSettings.highestMessageID = id;

        boolean fileSuccess = addFileMessage(new Message(id, formattedTitle, formattedMessage, enabled));

        context.getSource().sendFeedback(Text.literal("Message with ID ").formatted(Formatting.AQUA).append(Text.literal(id + "").formatted(Formatting.GOLD)).append(Text.literal(" has been added.").formatted(Formatting.AQUA)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int removeMessage(CommandContext<FabricClientCommandSource> context) {
        Integer id = context.getArgument("id", Integer.class);
        boolean foundMessage = false;

        for (Message message : ConfigSettings.messages) {
            if (message.ID != id) continue;
            ConfigSettings.messages.remove(message);
            if(ConfigSettings.highestMessageID == id) ConfigSettings.highestMessageID--;
            foundMessage = true;
            break;
        }

        if(!foundMessage) {
            context.getSource().sendFeedback(Text.literal("Message with ID " + id + " not found!").formatted(Formatting.RED));
            return 1;
        }

        boolean fileSuccess = removeFileLine("{id", id + "");

        context.getSource().sendFeedback(Text.literal("Message with ID ").formatted(Formatting.AQUA).append(Text.literal(id + "").formatted(Formatting.GOLD)).append(Text.literal(" has been removed.").formatted(Formatting.AQUA)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int enableMod(CommandContext<FabricClientCommandSource> context) {
        ConfigSettings.enabled = true;
        boolean fileSuccess = setFileValue("enabled", "true");

        context.getSource().sendFeedback(Text.literal("MindfulCraft has been enabled.").formatted(Formatting.AQUA));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int enableMessage(CommandContext<FabricClientCommandSource> context) {
        Integer id = context.getArgument("id", Integer.class);
        List<Message> messages = ConfigSettings.messages;

        Message message = null;
        for (Message m : messages) {
            if (m.ID != id) continue;
            message = m;
            break;
        }

        if (message == null) {
            context.getSource().sendFeedback(Text.literal("Message with ID " + context.getArgument("id", Integer.class) + " not found!").formatted(Formatting.RED));
            return 1;
        }

        message.Enabled = true;
        boolean fileSuccess = setMessageValue(message.ID, "enabled", "true");

        context.getSource().sendFeedback(Text.literal("Message with ID ").formatted(Formatting.AQUA).append(Text.literal(id + "").formatted(Formatting.GOLD)).append(Text.literal(" has been enabled.").formatted(Formatting.AQUA)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int disableMod(CommandContext<FabricClientCommandSource> context) {
        ConfigSettings.enabled = false;
        boolean fileSuccess = setFileValue("enabled", "false");

        context.getSource().sendFeedback(Text.literal("MindfulCraft has been disabled.").formatted(Formatting.AQUA));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }

    public static int disableMessage(CommandContext<FabricClientCommandSource> context) {
        Integer id = context.getArgument("id", Integer.class);
        List<Message> messages = ConfigSettings.messages;

        Message message = null;
        for (Message m : messages) {
            if (m.ID == id) {
                message = m;
                break;
            }
        }

        if (message == null) {
            context.getSource().sendFeedback(Text.literal("Message with ID " + context.getArgument("id", Integer.class) + " not found!").formatted(Formatting.RED));
            return 1;
        }

        message.Enabled = false;
        boolean fileSuccess = setMessageValue(message.ID, "enabled", "false");

        context.getSource().sendFeedback(Text.literal("Message with ID ").formatted(Formatting.AQUA).append(Text.literal(id + "").formatted(Formatting.GOLD)).append(Text.literal(" has been disabled.").formatted(Formatting.AQUA)));

        if(fileSuccess) {
            context.getSource().sendFeedback(Text.literal("Config file has been updated!").formatted(Formatting.GOLD));
        } else {
            context.getSource().sendFeedback(Text.literal("Failed to update config file! Value has still been modified for this session.").formatted(Formatting.RED));
        }

        return 1;
    }
}
