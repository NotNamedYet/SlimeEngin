package com.slimeflow.slimeengin;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public final class SlimePlayerManager
{

    private HashMap<UUID, SlimePlayer> m_PlayerCollection;
    private HashMap<String, UUID> m_NameCollection;

    SlimePlayerManager()
    {
        m_PlayerCollection = new HashMap<>(50);
        m_NameCollection = new HashMap<>(50);
    }

    /**
     * <span style="color:red;">(recommended for best accuracy)</span>
     * <br>
     * Get a SlimePlayer by UUID if it exists during this session.
     * null if there is no mapping for the given key.
     *
     * @param id get by UUID
     * @return a SlimePlayer instance, or null if no mapping for this key.
     */
    public SlimePlayer getSlimePlayer(UUID id)
    {
        return (m_PlayerCollection.containsKey(id))? m_PlayerCollection.get(id) : null;
    }

    /**
     * Get a SlimePlayer by name if it exists during this session.
     * null if there is no mapping for the given key.
     * <br>
     * Getting by name require to check into two specific map,
     * the name map first to get the UUID mapping key, then the main SlimePlayer collection. Those two maps are synchronized
     * as mush as possible but keep in mind that a Minecraft username can be changed by the user.
     * <br>
     * <span style="color:red;">(Use this procedure only for
     * gameplay's needs like economy, player ingame interaction etc...</span>
     *
     * @param name get by String name
     * @return a SlimePlayer instance, or null if no mapping for this name.
     */
    public SlimePlayer getSlimePlayer(String name)
    {
        if (m_NameCollection.containsKey(name))
        {
            UUID id = m_NameCollection.get(name);
            return getSlimePlayer(id);
        }

        return null;
    }

    /**
     * Add A SlimeCraft to the Collection
     * @param player SlimePlayer
     */
    void addSlime(SlimePlayer player)
    {
        if (m_PlayerCollection.containsKey(player.getUniqueId()))
            return;

        m_PlayerCollection.put(player.getUniqueId(), player);
        addName(player);
    }

    /**
     * Remove and return a SlimePlayer (if it exists, null otherwise) from the collection
     * @param id UUID as key
     * @return Removed SlimePlayer instance
     */
    SlimePlayer removeSlime(UUID id)
    {
        SlimePlayer retVal = m_PlayerCollection.get(id);

        if(retVal != null)
        {
            removeName(retVal.getBukkitPlayer().getName());
        }

        return retVal;
    }

    /**
     * Remove a name of the name's collection
     * @param name String name of a player
     */
    private void removeName(String name)
    {
        if(m_NameCollection.containsKey(name))
            m_NameCollection.remove(name);
    }

    /**
     * Add a name to name's collection
     * @param player SlimePlayer instance
     */
    private void addName(SlimePlayer player)
    {
        if (m_NameCollection.containsKey(player.getBukkitPlayer().getName()))
            return;

        m_NameCollection.put(player.getBukkitPlayer().getName(), player.getUniqueId());
    }

    /**
     * Fully re-Synchronize the name collection with the SlimePlayer Collection
     */
    void synchronizeNames()
    {
        HashMap<String, UUID> m_buffer = new HashMap<>(m_PlayerCollection.size());

        for (UUID id : m_PlayerCollection.keySet())
        {
            m_buffer.put(m_PlayerCollection.get(id).getBukkitPlayer().getName(), id);
        }

        m_NameCollection.clear();
        m_NameCollection.putAll(m_buffer);
    }

    /**
     * Get a collection of SlimePlayer
     * @return Collection<SlimePlayer>
     */
    public Collection<SlimePlayer> getSlimePlayers()
    {
        return m_PlayerCollection.values();
    }

    /**
     * Get the SlimePlayer collection size
     * @return the SlimePlayer collection size
     */
    public int collectionSize()
    {
        return m_PlayerCollection.size();
    }

    void clear()
    {
        //TODO: ProperClean + save
    }

}
