package jackperry2187.mindfulcraft;

import jackperry2187.mindfulcraft.config.ConfigSettings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static jackperry2187.mindfulcraft.util.SendClientMessage.*;

public class MindfulCraftClient implements ClientModInitializer {
	private static int tickCounter = 0;
	@Override
	public void onInitializeClient() {
		// initialize the config settings
		ConfigSettings.initialize();

		// register the client tick event to send messages
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.world == null || !ConfigSettings.enabled) return;

			// triggers as soon as a world is loaded
			if(tickCounter == 0) sendInitialMessage(client);

			// every X seconds send a mindful message
			if(tickCounter != 0 && tickCounter % ConfigSettings.ticksBetweenMessages == 0) {
				sendPeriodicMessage(client);
            }

			// once in a world and the mod is enabled, increment the tick counter
            tickCounter++;
        });
	}
}