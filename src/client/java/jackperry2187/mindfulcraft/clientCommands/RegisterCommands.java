package jackperry2187.mindfulcraft.clientCommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import jackperry2187.mindfulcraft.MindfulCraft;
import jackperry2187.mindfulcraft.clientCommands.customArguments.FormattingArgument;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;

import static jackperry2187.mindfulcraft.clientCommands.ArgumentInit.getCommandsWithArguments;

@Environment(value = EnvType.CLIENT)
public class RegisterCommands {
    public static void registerClientCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            LiteralArgumentBuilder<FabricClientCommandSource> commands = getCommandsWithArguments();
            dispatcher.register(commands);
        });
    }

    public static void registerArguments() {
        ArgumentTypeRegistry.registerArgumentType(
                MindfulCraft.id("formatting"),
                FormattingArgument.class,
                ConstantArgumentSerializer.of(FormattingArgument::new)
        );
    }

}
