package com.slimeflow.slimeengin.deadpool;

import com.slimeflow.slimeengin.SlimePlayer;

import java.util.*;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPoolTable
{
    private HashMap<UUID, DeadPool> m_Pool;
    private List<DeadPool> m_topTen;
    private boolean obsoleteTopTen;

    public DeadPoolTable()
    {
        m_Pool = new HashMap<>();
        m_topTen = new ArrayList<>(10);
    }

    public void add(DeadPool data)
    {
        if (m_Pool.containsKey(data.m_id))
            return;

        m_Pool.put(data.m_id, data);
        obsoleteTopTen = true;
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
        obsoleteTopTen = true;

        return created;
    }

    public List<DeadPool> getTopTen()
    {
        if (obsoleteTopTen)
            updateTopTen();

        return m_topTen;
    }

    private void updateTopTen()
    {
        List<DeadPool> buffer = new ArrayList<>(m_Pool.values());
        Collections.sort(buffer);

        m_topTen.clear();
        m_topTen.addAll(buffer.subList(0, 10));
        obsoleteTopTen = false;
    }

}
