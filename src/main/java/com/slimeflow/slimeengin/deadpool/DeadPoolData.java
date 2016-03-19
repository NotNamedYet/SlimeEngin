package com.slimeflow.slimeengin.deadpool;

import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPoolData
{
    public UUID m_id;
    public double m_poolAmount;

    /**
     * Burn the DeadPool of this SlimePlayer (return the bounty amount then reset it)
     */
    public double burn()
    {
        double ret = m_poolAmount;
        m_poolAmount = 0;

        return ret;
    }

    public void add(double amount)
    {
        m_poolAmount += amount;
    }
}
