package com.slimeflow.slimeengin;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class SlimeScore
{
    private SlimePlayer m_Player;
    private long m_blockBreacked;
    private long m_blockPlaced;
    private int m_deathCounter;

    public SlimeScore(SlimePlayer player)
    {
        m_Player = player;
    }

    public void saveScore()
    {
        //TODO: SEND ENTRY
    }
}
