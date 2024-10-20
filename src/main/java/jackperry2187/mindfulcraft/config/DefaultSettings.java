package jackperry2187.mindfulcraft.config;

import jackperry2187.mindfulcraft.util.Message;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jackperry2187.mindfulcraft.util.Message.generateSimpleMessage;
import static jackperry2187.mindfulcraft.util.Message.generateUniqueMessage;

public class DefaultSettings {
    public static final int configVersion = 1;
    public static final boolean enabled = true;
    public static final int timeBetweenMessages = 10; // in minutes
    public static final String defaultTitle = "MindfulCraft Message";
    public static final Formatting defaultTitleColor = Formatting.DARK_AQUA;
    public static final Formatting defaultMessageColor = Formatting.AQUA;

    private static final Message initialMessage = new Message(
            MutableText.of(Text.of("MindfulCraft Enabled").getContent()).formatted(defaultTitleColor),
            MutableText.of(Text.of("MindfulCraft is enabled. You will receive mindful messages every " + timeBetweenMessages + " minutes.").getContent()).formatted(defaultMessageColor),
            true
    );

    public static final List<Message> defaultMessages = new ArrayList<>(Arrays.asList(
            initialMessage,
            generateSimpleMessage("Remember to take a break and stretch your legs!"),
            generateSimpleMessage("Don't forget to drink some water!"),
            generateSimpleMessage("Time to take a deep breath and relax!"),
            generateSimpleMessage("Make sure to look away from your screen every once in a while!"),
            generateUniqueMessage("This is a Unique Message", "And you're a wonderfully unique person!", true, Formatting.GOLD, Formatting.YELLOW)
    ));
}
