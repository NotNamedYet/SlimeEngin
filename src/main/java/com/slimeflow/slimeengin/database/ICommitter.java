package com.slimeflow.slimeengin.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by x9litch on 21/03/2016. - slimeflow.com
 */
public interface ICommitter
{
    void submitEntry(IEntry committable);

    Connection getConnection() throws SQLException;
}
