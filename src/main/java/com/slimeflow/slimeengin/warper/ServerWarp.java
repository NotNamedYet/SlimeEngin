package com.slimeflow.slimeengin.warper;

import com.slimeflow.slimeengin.SlimePlayer;
import org.bukkit.inventory.ItemStack;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public class ServerWarp extends Warp
{
    public String m_Destination;

    public ServerWarp(int slotId, String serverLocation, ItemStack stack)
    {
        super(slotId, stack);
        m_Destination = serverLocation;
    }

    @Override
    public void warp(SlimePlayer player)
    {
        //TODO bungee teleport...
    }

    public String getDestination()
    {
        return m_Destination;
    }

    public void setDestination(String serverName)
    {
        m_Destination = serverName;
    }
}
