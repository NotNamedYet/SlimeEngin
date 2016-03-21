package com.slimeflow.slimeengin;

import com.slimeflow.slimeengin.database.IEntry;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by x9litch on 21/03/2016. - slimeflow.com
 */
public class SqlConsumer implements Runnable
{
    private final SqlConnector m_Connector;
    private final LinkedBlockingQueue<IEntry> m_SharedQueue;

    private AtomicBoolean m_Running;

    SqlConsumer(LinkedBlockingQueue<IEntry> sharedQueue, SqlConnector connector)
    {
        m_Running = new AtomicBoolean();
        m_SharedQueue = sharedQueue;
        m_Connector = connector;
    }

    public void setRunning(boolean running)
    {
        this.m_Running.set(running);
    }

    public boolean isRunning()
    {
        return m_Running.get();
    }

    @Override
    public void run()
    {
        while (isRunning())
        {
            if (m_SharedQueue.isEmpty())
            {
                synchronized (m_SharedQueue)
                {
                    try
                    {
                        m_SharedQueue.wait(10000);
                    }
                    catch (InterruptedException e)
                    {
                        //...
                    }
                }
            }
            else
            {
                //TODO: Process Entry
            }
        }
    }
}
