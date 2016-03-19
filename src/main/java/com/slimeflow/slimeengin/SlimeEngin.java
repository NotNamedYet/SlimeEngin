package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.commands.DebugCommands;
import com.slimeflow.slimeengin.deadpool.DeadPoolListener;
import com.slimeflow.slimeengin.deadpool.DeadPoolTable;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimeEngin extends JavaPlugin
{
    private static SlimeEngin m_instance;

    private SlimePlayerManager m_slimePlayerManager;
    private DeadPoolTable m_deadPoolTable;

    @Override
    public void onLoad()
    {
        if (m_instance != this)
            m_instance = this;

        m_slimePlayerManager = new SlimePlayerManager();
    }

    @Override
    public void onEnable()
    {
        PluginManager pMan = getServer().getPluginManager();

        registerListeners(pMan);
        registerCommands();

        m_deadPoolTable = new DeadPoolTable();
    }

    @Override
    public void onDisable()
    {
        m_slimePlayerManager.clear();
    }

    private void registerCommands()
    {
        getCommand("debug").setExecutor(new DebugCommands());
    }

    private void registerListeners(PluginManager manager)
    {
        //SlimePlayerListener
        SlimePlayerListener spl = new SlimePlayerListener();
        manager.registerEvents(spl, this);

        //DeadPoolListener
        DeadPoolListener dpl = new DeadPoolListener();
        manager.registerEvents(dpl, this);
    }

    /* STATICS
    ____
     */

    public static SlimeEngin Main()
    {
        return m_instance;
    }

    public static SlimePlayerManager getSlimeManager()
    {
        return m_instance.m_slimePlayerManager;
    }

    public static DeadPoolTable deadPools()
    {
        return m_instance.m_deadPoolTable;
    }
}
