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
    private final Player m_BukkitPlayer;
    private final UUID m_Uuid;
    private boolean m_IsOnline;

    //BookKeeping
    private SlimeSession m_Session;
    private SlimeScore m_Score;
    private DeadPool m_DeadPool;

    SlimePlayer(Player player)
    {
        m_BukkitPlayer = player;
        m_Uuid = player.getUniqueId();
        m_Session = new SlimeSession(this);
        m_Score = new SlimeScore();
    }

    /**
     * Get the unique java identifier of this crafter (should match with the default Bukkit Player UUID)
     * @return UUID of this crafter
     */
    public UUID getUniqueId()
    {
        return m_Uuid;
    }

    /**
     * Get the Original CraftBukkit Player interface
     * @return Player
     */
    public Player getBukkitPlayer()
    {
        return m_BukkitPlayer;
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
            m_Session.onLogin();
        }
        else
        {
            onLineSlimes--;
            m_Session.onLogout();
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
     * Get the session data of this SlimePlayer, containing connection data, time related data.
     * @return the session data of this SlimePlayer
     */
    public SlimeSession getSession()
    {
        return m_Session;
    }

    /**
     * Update the DeadPool data of this SlimePlayer.
     * If there is no DeadPool linked, try to get it back from the current DeadPoolTable,
     * and if there is no entry in the table, a fresh one is created.
     */
    public void updateDeadPool()
    {
        if (m_DeadPool == null)
            m_DeadPool = SlimeEngin.deadPools().getEntryFor(this);
    }

    /**
     * True if this SlimePlayer has a DeadPoolTable bounty on his head
     */
    public boolean hasDeadPool()
    {
        return m_DeadPool.m_poolAmount > 0;
    }

    /**
     * get the DeadPoolTable data attached to this SlimePlayer
     * @return DeadPool
     */
    public DeadPool getDeadPool()
    {
        return m_DeadPool;
    }

    /**
     * Get the Score data attached to this SlimePlayer
     * @return the Score data attached to this SlimePlayer
     */
    public SlimeScore getScore()
    {
        return m_Score;
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
     * (recommended for best accuracy)
     * <br>
     * Delegate method. Static way to get a SlimePlayer by ID if it exists during this session.
     * null if there is no mapping for the given key.
     *
     * @param id get by UUID
     * @return a SlimePlayer instance, or null if no mapping for this key.
     */
    public static SlimePlayer getSlimePlayer(UUID id)
    {
        return Manager().getSlimePlayer(id);
    }

    /**
     * Delegate method. Static way to get a SlimePlayer by name if it exists during this session.
     * null if there is no mapping for the given key.
     * <br>
     * Getting by name require to check into two specific map,
     * the name map first to get the UUID mapping key, then the main SlimePlayer collection. Those two maps are synchronized
     * as mush as possible but keep in mind that a Minecraft username can be changed by the user.
     * <br>
     * Use this procedure only for
     * gameplay's needs like economy, player ingame interaction etc...
     *
     * @param name get by String name
     * @return a SlimePlayer instance, or null if no mapping for this name.
     */
    public static SlimePlayer getSlimePlayer(String name)
    {
        return Manager().getSlimePlayer(name);
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
