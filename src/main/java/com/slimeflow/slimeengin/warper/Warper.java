package com.slimeflow.slimeengin.warper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public class Warper
{
    private HashMap<Integer, Warp> m_WarpMap;
    private Inventory m_InventoryGui;

    public Warper()
    {
        m_InventoryGui = Bukkit.createInventory(null, InventoryType.CHEST, "Warp Book");
        m_WarpMap = new HashMap<>(m_InventoryGui.getSize());
    }

    /**
     * Get the warp inventory GUI
     * @return the warp inventory GUI
     */
    public Inventory getInventoryGui()
    {
        return m_InventoryGui;
    }

    /**
     * Try to add a warp to the map, return false if the slot is already taken by another Warp.
     *
     * @param warp to Warp to map.
     * @return true if operation succeed, false if the target slot is already taken by another Warp
     */
    public boolean addWarp(Warp warp)
    {
        if (!m_WarpMap.containsKey(warp.getSlotId()))
        {
            m_WarpMap.put(warp.getSlotId(), warp);
            refreshGui();
            return true;
        }

        return false;
    }

    /**
     * Remove and return a warp mapped to the given key, or returns null if there is not mapping for this key
     * @param slotId the Integer key
     * @return a warp mapped to the given key, or returns null if there is not mapping for this key
     */
    public Warp removeWarp(int slotId)
    {
        return (m_WarpMap.containsKey(slotId))? m_WarpMap.remove(slotId): null;
    }

    /**
     * Refresh the GUI inventory according to the Warp mapping
     */
    public void refreshGui()
    {

    }

    /**
     * Load mapping from disk.
     */
    private void loadWarps()
    {

    }

    /**
     * Save the mapping on disk
     */
    private void serialize()
    {

    }

    /**
     * Factory class to dynamic create warps.
     */
    public static class Builder
    {
        public static Warp create(int slotId, Location destination, ItemStack stack)
        {
            return new LocalWarp(slotId, destination, stack);
        }

        public static Warp create(int slotId, String serverName, ItemStack stack)
        {
            return new ServerWarp(slotId, serverName, stack);
        }
    }
}
