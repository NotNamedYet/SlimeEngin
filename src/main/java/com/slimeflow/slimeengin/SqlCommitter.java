package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.database.ICommitter;
import com.slimeflow.slimeengin.database.IEntry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by x9litch on 21/03/2016. - slimeflow.com
 */
public class SqlCommitter implements ICommitter
{
    private final LinkedBlockingQueue<IEntry> m_SharedQueue;
    private SqlConnector m_Connector;
    private SqlConsumer m_Consumer;

    SqlCommitter()
    {
        m_SharedQueue = new LinkedBlockingQueue<>();
        m_Connector = new SqlConnector();
    }

    void start()
    {
        m_Consumer = new SqlConsumer(m_SharedQueue, m_Connector);
        m_Consumer.setRunning(true);

        Thread thread = new Thread(m_Consumer, "CommitterConsumer");
        thread.start();

    }

    void stop()
    {
        if (m_Consumer.isRunning())
        {
            m_Consumer.setRunning(false);
        }
    }

    void restart()
    {
        stop();
        start();
    }

    @Override
    public void submitEntry(IEntry entry)
    {
        if (m_SharedQueue.offer(entry))
        {
            synchronized (m_SharedQueue)
            {
                m_SharedQueue.notify();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return m_Connector.getConnection();
    }
}
