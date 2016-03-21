package com.slimeflow.slimeengin;

import org.bukkit.Bukkit;

import java.util.Date;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public class SlimeSession
{
    private final SlimePlayer m_Player;
    private long m_FirstRegisteredTime;
    private long m_LastLoginTime;
    private long m_LastLogoutTime;
    private long m_TimePlayed;
    private String m_LastVisitedServer;
    private String m_CurrentWorld;

    private long m_ReferenceTime = 0;

    public SlimeSession(SlimePlayer player)
    {
        m_Player = player;
    }

    public SlimePlayer getSlimePlayer()
    {
        return m_Player;
    }

    public long getFirstRegistered()
    {
        return m_FirstRegisteredTime;
    }

    public void setFirstRegisteredTime(long time)
    {
        m_FirstRegisteredTime = time;
    }

    public long getLastLogin()
    {
        return m_LastLoginTime;
    }

    public long getLastLogout()
    {
        return m_LastLogoutTime;
    }

    public long getTimePlayed()
    {
        return m_TimePlayed;
    }

    public String getCurrentWorld()
    {
        return m_CurrentWorld;
    }

    public void setCurrentWorld(String worldName)
    {
        m_CurrentWorld = worldName;
    }

    public String getLastVisitedServer()
    {
        return m_LastVisitedServer;
    }

    public void updateTimePlayed()
    {
        if (!m_Player.isOnline()) return;

        long now = new Date().getTime();
        long sessionPlayed = now - m_ReferenceTime;
        m_TimePlayed += sessionPlayed;
        m_ReferenceTime = now;
    }

    void onLogin()
    {
        m_LastLoginTime = new Date().getTime();
        m_ReferenceTime = m_LastLoginTime;
    }

    void onLogout()
    {
        updateTimePlayed();
        m_LastLogoutTime = new Date().getTime();
        m_LastVisitedServer = Bukkit.getServer().getServerName();
        saveSession();
    }

    public void saveSession()
    {
        //TODO: session entry
    }

}
