package com.slimeflow.slimeengine;

import com.slimeflow.slimeengine.commands.DebugCommands;
import com.slimeflow.slimeengine.deadpool.DeadPoolListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimeEngine extends JavaPlugin
{
    private static SlimeEngine m_instance;

    private SlimePlayerManager m_slimePlayerManager;

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

    public static SlimeEngine Main()
    {
        return m_instance;
    }

    public static SlimePlayerManager getSlimeManager()
    {
        return m_instance.m_slimePlayerManager;
    }

}
