package com.slimeflow.slimeengin.deadpool;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class NewDeadPoolEvent extends Event {

    private final DeadPool m_data;

    public NewDeadPoolEvent(DeadPool data)
    {
        m_data = data;
    }

    public UUID getUniqueId()
    {
        return m_data.m_id;
    }

    public double getAmount()
    {
        return m_data.m_poolAmount;
    }

    public void setAmount(double amount)
    {
        m_data.m_poolAmount = amount;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
