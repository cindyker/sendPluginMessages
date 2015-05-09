package com.minecats.sendpluginmessages;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by cindy on 5/7/2015.
 * Test of plugin messaging.
 *
 */
public class SendPluginMessage extends JavaPlugin implements Listener, PluginMessageListener, CommandExecutor {
    @Override
    public void onEnable() {
        super.onEnable();

        getLogger().info(String.format("[%s] - Registering CrossServer Message Channels", getDescription().getName()));

        this.getServer().getMessenger().registerIncomingPluginChannel(this, "TESTMESSAGE", this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "TESTMESSAGE");
        getCommand("sendplug").setExecutor(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

        getLogger().info(String.format("Received: %s ", s));

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        getLogger().info("Sending Plugin Message");

        Player player = (Player)commandSender;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Subchannel");
        out.writeUTF("Argument");
        player.sendPluginMessage(this, "TESTMESSAGE", out.toByteArray());

        return true;
    }
}
