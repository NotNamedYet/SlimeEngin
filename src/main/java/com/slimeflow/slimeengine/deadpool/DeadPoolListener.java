package com.slimeflow.slimeengine.deadpool;

import com.slimeflow.slimeengine.SlimePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPoolListener implements Listener
{

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent evt)
    {
        Player source = evt.getEntity();
        SlimePlayer sPlayer = SlimePlayer.Manager().getSlime(source.getUniqueId());

        if(sPlayer == null)
            return;

        if (sPlayer.isOnDeadPool())
        {
            double amount = sPlayer.burnDeadPool();
            evt.setDeathMessage("DeadPool reward for " + source.getDisplayName() + " goes to " + source.getKiller().getDisplayName());
            //TODO: Reward Killer...
        }
    }
}
