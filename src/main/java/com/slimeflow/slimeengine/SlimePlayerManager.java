package com.slimeflow.slimeengine;

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

    public SlimePlayer getSlime(UUID id)
    {
        return m_PlayerCollection.getOrDefault(id, null);
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
            removeName(retVal.getOrigin().getName());
        }

        return retVal;
    }

    private void removeName(String name)
    {
        if(m_NameCollection.containsKey(name))
            m_NameCollection.remove(name);
    }

    private void addName(SlimePlayer player)
    {
        m_NameCollection.putIfAbsent(player.getOrigin().getName(), player.getUniqueId());
    }

    /**
     * Fully re-Synchronize the name collection with the SlimePlayer Collection
     */
    void synchronizeNames()
    {
        HashMap<String, UUID> m_buffer = new HashMap<>(m_PlayerCollection.size());

        for (UUID id : m_PlayerCollection.keySet())
        {
            m_buffer.put(m_PlayerCollection.get(id).getOrigin().getName(), id);
        }

        m_NameCollection.clear();
        m_NameCollection.putAll(m_buffer);
    }

    /**
     * Get a safe collection of SlimePlayer
     * @return Collecton<SlimePlayer>
     */
    public Collection<SlimePlayer> getSlimePlayers()
    {
        return m_PlayerCollection.values();
    }

    public int collectionSize()
    {
        return m_PlayerCollection.size();
    }

    void clear()
    {
        //TODO: ProperClean + save
    }

}
