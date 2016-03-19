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
     * try to get a SlimePlayer if it exists in the collection, null if there is no mapping for this UUID
     * @param id UUID of a player
     * @return a SlimePlayer if it exists in the collection, null if there is no mapping for this UUID
     */
    public SlimePlayer getSlime(UUID id)
    {
        return (m_PlayerCollection.containsKey(id))? m_PlayerCollection.get(id) : null;
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
        if (m_NameCollection.containsKey(player.getOrigin().getName()))
            return;

        m_NameCollection.put(player.getOrigin().getName(), player.getUniqueId());
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
