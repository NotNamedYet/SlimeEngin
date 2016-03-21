package com.slimeflow.slimeengin;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by x9litch on 21/03/2016. - slimeflow.com
 */
public class SqlConnector
{

    private HikariDataSource m_source;
    private HikariConfig m_config;

    SqlConnector()
    {
        loadConfig();
        m_source = new HikariDataSource(m_config);
    }

    private void loadConfig()
    {

    }

    Connection getConnection() throws SQLException
    {
        return m_source.getConnection();
    }
}
