package com.slimeflow.slimeengin.warper;

import com.slimeflow.slimeengin.SlimePlayer;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public class LocalWarp extends Warp
{
    private Location m_Destination;

    public LocalWarp(int slotId, Location location, ItemStack stack)
    {
        super(slotId, stack);
        m_Destination = location;
    }

    /**
     * Send the player to the destination of this warp.
     * @param player the SlimePlayer to warp
     */
    @Override
    public void warp(SlimePlayer player)
    {
        player.getBukkitPlayer().teleport(m_Destination, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    /**
     * return the destination of this Warp;
     * @return Location instance
     */
    public Location getDestination()
    {
        return m_Destination;
    }

    /**
     * set the destination of this warp
     * @param location Location instance
     */
    public void setDestination(Location location)
    {
        m_Destination = location;
    }
}
