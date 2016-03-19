package com.slimeflow.slimeengine;

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
        m_instance = this;
        m_slimePlayerManager = new SlimePlayerManager();
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }

    public static SlimeEngine Main()
    {
        return m_instance;
    }

    public static SlimePlayerManager getSlimeManager()
    {
        return m_instance.m_slimePlayerManager;
    }
}
