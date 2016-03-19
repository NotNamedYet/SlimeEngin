package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.deadpool.DeadPool;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represent the Player 'extension' of the SlimeEngin system
 * <br>
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public final class SlimePlayer
{
    //Statics
    private static int onLineSlimes;

    //Base fields
    private final Player m_base;
    private final UUID m_uuid;
    private boolean m_IsOnline;

    //BookKeeping
    private SlimeScore m_score;
    private DeadPool m_deadPool;

    SlimePlayer(Player player)
    {
        m_base = player;
        m_uuid = player.getUniqueId();
        m_score = new SlimeScore();
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
     * Is this player is Connected to the server ?
     * @return false if offline;
     */
    public boolean isOnline()
    {
        return m_IsOnline;
    }

    /**
     * Set the online state of this SlimePlayer
     * @param value true for online, false for offline
     */
    void setOnline(boolean value)
    {
        m_IsOnline = value;

        if(value)
        {
            onLineSlimes++;
        }
        else
        {
            onLineSlimes--;
        }

    }

    /**
     * Load Data from DAO
     * @param data Sql DAO
     */
    void loadData(SlimeData data)
    {
        //...
    }

    /**
     * Update the DeadPool data of this SlimePlayer.
     * If there is no DeadPool linked, try to get it back from the current DeadPoolTable,
     * and if there is no entry in the table, a fresh one is created.
     */
    public void updateDeadPool()
    {
        if (m_deadPool == null)
            m_deadPool = SlimeEngin.deadPools().getEntryFor(this);
    }

    /**
     * True if this SlimePlayer has a DeadPoolTable bounty on his head
     */
    public boolean hasDeadPool()
    {
        return m_deadPool.m_poolAmount > 0;
    }

    /**
     * get the DeadPoolTable data attached to this SlimePlayer
     * @return DeadPool
     */
    public DeadPool getDeadPool()
    {
        return m_deadPool;
    }

    /**
     * Shortcut access to the SlimePlayer Manager
     * @return SlimePlayerManager instance
     */
    public static SlimePlayerManager Manager()
    {
        return SlimeEngin.getSlimeManager();
    }

    /**
     * Get the total number of online Slimes on this server
     * @return total online Slime as Integer
     */
    public static int totalOnline()
    {
        return onLineSlimes;
    }
}
