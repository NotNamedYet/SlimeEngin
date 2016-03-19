package com.slimeflow.slimeengine;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimePlayerManager
{
    private ConcurrentHashMap<UUID, SlimePlayer> m_PlayerCollection;

    SlimePlayerManager()
    {
        m_PlayerCollection = new ConcurrentHashMap<>(50);
    }

    public SlimePlayer getSlime(UUID id)
    {
        return m_PlayerCollection.getOrDefault(id, null);
    }

    void addSlime(SlimePlayer player)
    {
        if (m_PlayerCollection.containsKey(player.getUniqueId()))
            return;

        m_PlayerCollection.put(player.getUniqueId(), player);
    }

}
