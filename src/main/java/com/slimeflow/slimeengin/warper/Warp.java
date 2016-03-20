package com.slimeflow.slimeengin.warper;

import com.slimeflow.slimeengin.SlimePlayer;
import org.bukkit.inventory.ItemStack;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public abstract class Warp
{
    private int m_SlotId;
    public ItemStack m_StackVisual;

    public Warp(int slotId, ItemStack stack)
    {
        m_SlotId = slotId;
        m_StackVisual = stack;
    }

    public int getSlotId()
    {
        return m_SlotId;
    }

    public ItemStack getStackVisual()
    {
        return m_StackVisual;
    }

    public abstract void warp(SlimePlayer player);
}
