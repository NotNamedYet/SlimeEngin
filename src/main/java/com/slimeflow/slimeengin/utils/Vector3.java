package com.slimeflow.slimeengin.utils;

import java.io.Serializable;

/**
 * Created by x9litch on 20/03/2016. - slimeflow.com
 */
public class Vector3 implements Serializable
{
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
