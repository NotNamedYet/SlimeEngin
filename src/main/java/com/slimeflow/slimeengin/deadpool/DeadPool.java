package com.slimeflow.slimeengin.deadpool;

import com.slimeflow.slimeengin.SlimeEngin;
import com.slimeflow.slimeengin.SlimePlayer;

import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPool implements Comparable<DeadPool>
{
    public UUID m_id;
    public String m_actor;
    public double m_poolAmount;

    public DeadPool(SlimePlayer player)
    {
        m_id = player.getUniqueId();
        m_actor = player.getBukkitPlayer().getDisplayName();
        SlimeEngin.getDeadPools().add(this);
    }

    public DeadPool()
    {
        m_actor = "Unknown";
        m_poolAmount = 0;
    }

    /**
     * Burn the DeadPoolTable of this SlimePlayer (return the bounty amount then reset it)
     */
    public double burn()
    {
        //TODO BURNDEADPOOL EVENT

        double ret = m_poolAmount;
        m_poolAmount = 0;

        return ret;
    }

    /**
     * Add amount (reward) to this deadpool entry
     * @param amount the amount of reward to add
     */
    public void add(double amount)
    {
        //TODO: ADDDEADPOOL EVENT
        m_poolAmount += amount;
    }

    @Override
    public int compareTo(DeadPool o)
    {
        return Double.compare(o.m_poolAmount, this.m_poolAmount);
    }
}
