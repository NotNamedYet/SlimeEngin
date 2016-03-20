package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.commands.DebugCommands;
import com.slimeflow.slimeengin.deadpool.DeadPoolListener;
import com.slimeflow.slimeengin.deadpool.DeadPoolTable;
import com.slimeflow.slimeengin.warper.Warper;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimeEngin extends JavaPlugin
{
    private static SlimeEngin m_instance;
    private SlimePlayerManager m_slimePlayerManager;

    private Warper m_Warper;
    private boolean m_WarperEnable = true;

    private DeadPoolTable m_deadPoolTable;
    private boolean m_deadPoolEnable = true;

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

        //REQUIRED MODULE
        initSlimeManager(pMan);

        //OPTIONAL MODULE
        initWarper(pMan);
        initDeadPoolTable(pMan);
    }

    @Override
    public void onDisable()
    {
        m_slimePlayerManager.clear();
    }

    private void registerCommands()
    {

    }

    /* MODULES
    ____
     */

    private void initSlimeManager(PluginManager pm)
    {
        getLogger().info("Enabling SlimeManager ...");
        SlimePlayerListener spl = new SlimePlayerListener();
        pm.registerEvents(spl, this);
        getCommand("debug").setExecutor(new DebugCommands());
    }

    private void initWarper(PluginManager pm)
    {
        if (m_WarperEnable)
        {
            getLogger().info("Enabling Warper Module ...");
            m_Warper = new Warper();
        }
        else
        {
            getLogger().info("Warper Module not enable.");
        }
    }

    private void initDeadPoolTable(PluginManager pm)
    {
        if (m_deadPoolEnable)
        {
            getLogger().info("Enabling DeadPool Module ...");
            m_deadPoolTable = new DeadPoolTable();
            DeadPoolListener dpl = new DeadPoolListener();
            pm.registerEvents(dpl, this);
        }
        else
        {
            getLogger().info("DeadPool Module not enable.");
        }
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

    public static boolean isWarperEnable() {return m_instance.m_WarperEnable;}
    public static Warper warper()
    {
        return m_instance.m_Warper;
    }

    public static boolean isDeadPoolEnable() {return m_instance.m_deadPoolEnable;}
    public static DeadPoolTable deadPools()
    {
        return m_instance.m_deadPoolTable;
    }
}
