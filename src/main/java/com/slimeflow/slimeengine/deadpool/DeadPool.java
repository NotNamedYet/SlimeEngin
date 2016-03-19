package com.slimeflow.slimeengine.deadpool;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DeadPool
{
    private HashMap<UUID, DeadPoolData> m_Pool;

    public DeadPool()
    {
        m_Pool = new HashMap<>();
    }
}
