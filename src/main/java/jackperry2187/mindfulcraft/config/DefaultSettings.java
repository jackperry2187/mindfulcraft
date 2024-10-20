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
            generateSimpleMessage("Now would be a good time to drink some water!"),
            generateSimpleMessage("Time to take a deep breath and relax!"),
            generateSimpleMessage("Make sure to look away from your screen every once in a while!"),
            generateSimpleMessage("Check your posture - sit up nice and tall!"),
            generateSimpleMessage("A healthy snack can boost your energy - grab one if you can!"),
            generateSimpleMessage("Looking at something far away can be useful every once and a while - try it now!"),
            generateSimpleMessage("Smile! It's good for you!"),
            generateSimpleMessage("Sometimes it's good to take a moment to appreciate the little things!"),
            generateSimpleMessage("Roll your shoulders back and down to relieve tension!"),
            generateSimpleMessage("Take a moment to refocus and recenter yourself - what's your next goal?"),
            generateSimpleMessage("Unclench your jaw and relax your face - you might be holding tension there!"),
            generateSimpleMessage("Now would be a good time to refill your water bottle!"),
            generateSimpleMessage("Stand tall and take a deep breath - you're doing great!"),
            generateSimpleMessage("A few ankle rolls can help keep your feet happy!"),
            generateSimpleMessage("Enjoy a moment to reflect on your progress - you've come so far!"),
            generateSimpleMessage("Time to ask yourself - are you staying hydrated?"),
            generateSimpleMessage("Take a moment to appreciate the beauty around you - it's all around!"),
            generateUniqueMessage("You're doing great!", "Keep up the good work!", true, Formatting.GREEN, Formatting.DARK_GREEN),
            generateUniqueMessage("You're amazing!", "Don't forget it!", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("Have a wonderful day!", "You deserve it!", true, Formatting.BLUE, Formatting.DARK_BLUE),
            generateUniqueMessage("Take on the world!", "You're unstoppable!", true, Formatting.RED, Formatting.DARK_RED),
            generateUniqueMessage("Shine bright today!", "You're a star!", true, Formatting.YELLOW, Formatting.GOLD),
            generateUniqueMessage("Embrace the journey!", "Every moment counts!", true, Formatting.BLUE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a champion!", "Keep up the good work!", true, Formatting.RED, Formatting.DARK_RED),
            generateUniqueMessage("You're a rockstar!", "You're doing amazing!", true, Formatting.RED, Formatting.GREEN),
            generateUniqueMessage("Feel the flow of progress!", "Trust the process!", true, Formatting.AQUA, Formatting.BLUE),
            generateUniqueMessage("Look at how talented you are!", "You've created something amazing!", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("You're a creative genius!", "Your ideas are incredible!", true, Formatting.GREEN, Formatting.GOLD),
            generateUniqueMessage("Believe in your magic!", "You're capable of anything!", true, Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a work of art!", "You're beautiful inside and out!", true, Formatting.BLUE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You are enough!", "Just as you are.", true, Formatting.WHITE, Formatting.GRAY),
            generateUniqueMessage("Stay true to you!", "You're on the right path!", true, Formatting.DARK_GRAY, Formatting.WHITE),
            generateUniqueMessage("Your hard work is paying off!", "Keep it up!", true, Formatting.DARK_GREEN, Formatting.GREEN),
            generateUniqueMessage("You're a ray of sunshine!", "Brightening the world!", true, Formatting.YELLOW, Formatting.GOLD),
            generateUniqueMessage("You're a breath of fresh air!", "Refreshing the world!", true, Formatting.AQUA, Formatting.BLUE),
            generateUniqueMessage("You're a guiding light!", "Illuminating the world!", true, Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a force of nature!", "Changing the world!", true, Formatting.GREEN, Formatting.RED),
            generateUniqueMessage("This is a Unique Message", "And you're a wonderfully unique person!", true, Formatting.GOLD, Formatting.YELLOW)
    ));
}
