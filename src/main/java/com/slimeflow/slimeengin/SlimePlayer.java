package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.deadpool.DeadPoolData;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represent the Player 'extension' of the SlimeEngin system
 * <br>
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public final class SlimePlayer
{
    //Base fields
    private final Player m_base;
    private final UUID m_uuid;
    private boolean m_IsOnline;

    //BookKeeping
    private SlimeScore m_score;
    private DeadPoolData m_deadPoolData;

    SlimePlayer(Player player)
    {
        m_base = player;
        m_uuid = player.getUniqueId();
        m_score = new SlimeScore();
        m_deadPoolData = new DeadPoolData();
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

    /*
        DEADPOOL
     */

    /**
     * True if this SlimePlayer has a DeadPool bounty on his head
     */
    public boolean isOnDeadPool()
    {
        return m_deadPoolData.m_poolAmount > 0;
    }

    public DeadPoolData getDeadPoolData()
    {
        return m_deadPoolData;
    }



    /**
     * Shortcut access to the SlimePlayer Manager
     * @return SlimePlayerManager instance
     */
    public static SlimePlayerManager Manager()
    {
        return SlimeEngin.getSlimeManager();
    }

}
