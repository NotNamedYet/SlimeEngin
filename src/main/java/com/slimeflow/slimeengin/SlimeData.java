package com.slimeflow.slimeengin;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public final class SlimeData implements Serializable
{
    public UUID m_id;

    public SlimeData(UUID id)
    {
        m_id = id;
    }

    public static SlimeData requestData(UUID id)
    {
        return null;
    }
}
