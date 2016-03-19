package com.slimeflow.slimeengine;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represent the Player 'extension' of the SlimeEngin system
 * <br>
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimePlayer
{
    private final Player m_base;
    private final UUID m_uuid;

    SlimePlayer(Player player)
    {
        m_base = player;
        m_uuid = player.getUniqueId();
    }

    /**
     * Get the unique java identifier of this crafter (should match with the default Bukkit Player UUID)
     * @return UUID of this crafter
     */
    public UUID getUniqueId()
    {
        return m_uuid;
    }

    /**
     * Get the Original CraftBukkit Player interface
     * @return Player
     */
    public Player getOrigin()
    {
        return m_base;
    }

    /**
     * Load Data from DAO
     * @param data Sql DAO
     */
    public void loadData(SlimeData data)
    {
        //...
    }

    /**
     * Shortcut access to the SlimePlayer Manager
     * @return SlimePlayerManager instance
     */
    public static SlimePlayerManager Manager()
    {
        return SlimeEngine.getSlimeManager();
    }

}
