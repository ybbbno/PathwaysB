package me.ybbbno.pathwaysb;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.deadybbb.ybmj.LegacyTextHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class PathwaysBCommand implements BasicCommand {
    private PathwaysB plugin;

    public PathwaysBCommand(PathwaysB plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        CommandSender s = source.getSender();

        if (args[0].equals("reload")) {
            plugin.reload();
            LegacyTextHandler.sendFormattedMessage(s, "<green>PathwaysB reloaded.");
            return;
        }

        LegacyTextHandler.sendFormattedMessage(s, "<red>Usage: /pathwaysb reload");
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        return List.of("reload");
    }

    @Override
    public boolean canUse(CommandSender sender) {
        final String permission = this.permission();
        return sender.hasPermission(permission) &&
                sender instanceof Player;
    }

    @Override
    public @Nullable String permission() {
        return "pathwaysb.reload";
    }
}
