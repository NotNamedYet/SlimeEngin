package com.slimeflow.slimeengin.deadpool;

import com.slimeflow.slimeengin.SlimePlayer;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPoolTable
{
    private HashMap<UUID, DeadPool> m_Pool;

    public DeadPoolTable()
    {
        m_Pool = new HashMap<>();
    }

    public void add(DeadPool data)
    {
        if (m_Pool.containsKey(data.m_id))
            return;

        m_Pool.put(data.m_id, data);
    }

    /**
     * Returns the DeadPool for SlimePlayer if it exists, creates new one otherwise
     * @param player The concerned SlimePlayer
     * @return a DeadPool entry (never null)
     */
    public DeadPool getEntryFor(SlimePlayer player)
    {
        if (m_Pool.containsKey(player.getUniqueId()))
        {
            return m_Pool.get(player.getUniqueId());
        }

        DeadPool created = new DeadPool(player);
        add(created);

        return created;
    }
}
