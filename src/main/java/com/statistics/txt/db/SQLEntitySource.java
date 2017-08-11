package com.statistics.txt.db;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.statistics.txt.entity.LineStatistics;
import com.statistics.txt.entity.TextFileStatistics;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class SQLEntitySource {
    private Properties dbProps;
    private BoneCP pool;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    public SQLEntitySource(Properties dbProps) {
        this.dbProps = dbProps;
    }

    private void init() {
        synchronized (initialized) {
            try {
                Class.forName(dbProps.getProperty("db.driver"));
            } catch (ClassNotFoundException e) {
                return;
            }

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(dbProps.getProperty("db.url"));
            config.setUsername(dbProps.getProperty("db.login"));
            config.setPassword(dbProps.getProperty("db.password"));

            try {
                pool = new BoneCP(config);
                initialized.set(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void putFileStatistics(TextFileStatistics statistics) {
        if (!initialized.get()) {
            init();
        }
        Connection connection = null;
        PreparedStatement fileStatement = null;
        PreparedStatement lineStatement = null;
        try {
            connection = pool.getConnection();
            fileStatement = connection.prepareStatement("INSERT INTO" +
                    " files(file_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            fileStatement.setString(1, statistics.getName());
            fileStatement.executeUpdate();
            ResultSet rs = fileStatement.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            lineStatement = connection.prepareStatement("INSERT INTO " +
                    "line_stats(file_id, line_content, longest_word, shortest_word, line_length, average_word_length)" +
                    "VALUES (?,?,?,?,?,?)");
            for (LineStatistics stats : statistics.getLineStatistics()) {
                lineStatement.setInt(1, id);
                lineStatement.setString(2, stats.getLine());
                lineStatement.setString(3, stats.getLongestWord());
                lineStatement.setString(4, stats.getShortestWord());
                lineStatement.setInt(5, stats.getLength());
                lineStatement.setDouble(6, stats.getAverageWordLength());
                lineStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, fileStatement, lineStatement);
        }
    }

    private void close(AutoCloseable... entities) {
        for (AutoCloseable entity : entities) {
            if (entity != null) {
                try {
                    entity.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
