package jackperry2187.mindfulcraft.util;

import jackperry2187.mindfulcraft.MindfulCraft;
import jackperry2187.mindfulcraft.config.ConfigSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;

import java.util.ArrayList;
import java.util.List;

public class SendClientMessage {
    private static int lastMessageIndex = -1;
    private static final List<Integer> idsOfSentMessages = new ArrayList<>();

    public static void sendInitialMessage(MinecraftClient client) {
        if(lastMessageIndex != -1) {
            MindfulCraft.LOGGER.error("Initial message has already been sent!");
            return;
        }
        Message intialMessage = ConfigSettings.messages.getFirst();

        client.getToastManager().add(SystemToast.create(
                client,
                SystemToast.Type.PERIODIC_NOTIFICATION,
                intialMessage.Title,
                intialMessage.Description
        ));

        idsOfSentMessages.add(intialMessage.ID);
        lastMessageIndex = 0;
    }

    public static void sendPeriodicMessage(MinecraftClient client) {
        if(lastMessageIndex == -1) {
            MindfulCraft.LOGGER.error("Initial message has not been sent!");
            return;
        }

        if(idsOfSentMessages.size() == ConfigSettings.messages.size() - 1) {
            MindfulCraft.LOGGER.info("All messages have been sent! Resetting sent messages.");
            idsOfSentMessages.clear();
        }

        // generate a number between ConfigSettings.lowestID and ConfigSettings.highestID
        int nextMessageIndex = (int) (Math.random() * (ConfigSettings.highestID - ConfigSettings.lowestID + 1) + ConfigSettings.lowestID);
        while(idsOfSentMessages.contains(nextMessageIndex) || nextMessageIndex == lastMessageIndex) {
            nextMessageIndex = (int) (Math.random() * (ConfigSettings.highestID - ConfigSettings.lowestID + 1) + ConfigSettings.lowestID);
        }

        Message nextMessage = ConfigSettings.messages.get(nextMessageIndex);

        client.getToastManager().add(SystemToast.create(
                MinecraftClient.getInstance(),
                SystemToast.Type.PERIODIC_NOTIFICATION,
                nextMessage.Title,
                nextMessage.Description
        ));

        idsOfSentMessages.add(nextMessage.ID);
        lastMessageIndex = nextMessageIndex;
    }
}
