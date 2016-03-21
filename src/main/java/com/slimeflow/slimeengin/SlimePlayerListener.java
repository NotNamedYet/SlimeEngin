package com.slimeflow.slimeengin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimePlayerListener implements Listener
{

    @EventHandler
    void onPlayerPreLogin(AsyncPlayerPreLoginEvent evt)
    {
        Bukkit.getLogger().info(evt.getUniqueId().toString());
    }

    @EventHandler
    void onPlayerLogin(PlayerLoginEvent evt)
    {
        SlimePlayer player = SlimePlayer.getSlimePlayer(evt.getPlayer().getUniqueId());

        if (player == null)
        {
            player = new SlimePlayer(evt.getPlayer());
            player.setOnline(true);
            SlimePlayer.Manager().addSlime(player);
        }

    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent evt)
    {
        SlimePlayer player = SlimePlayer.Manager().getSlimePlayer(evt.getPlayer().getUniqueId());

        if (player != null)
        {
            player.setOnline(false);
        }
    }
}
