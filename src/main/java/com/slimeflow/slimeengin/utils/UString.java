package com.slimeflow.slimeengin.utils;

/**
 * Created by x9litch on 21/03/2016. - slimeflow.com
 */
public class UString
{
    public static String stringFromArray(String[] toBuild, char sep)
    {
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < toBuild.length; i++) {

            stb.append(toBuild[i]);

            if (i < toBuild.length - 1)
                stb.append(sep);
        }

        return stb.toString();
    }

    public static String stringFromArray(String[] toBuild)
    {
        return stringFromArray(toBuild, ' ');
    }

    public static boolean isNullOrEmpty(String str)
    {
        return str != null && str.length() > 0;
    }
}
