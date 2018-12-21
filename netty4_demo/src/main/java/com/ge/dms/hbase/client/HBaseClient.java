package com.ge.dms.hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HBaseClient {

    private final static Logger log = Logger.getLogger(HBaseClient.class);

    private static Configuration setupHBaseConf() {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.56.103");
        config.set("hbase.zookeeper.property.clientProperty", "2181");
        return config;
    }

    private static void createTable(Configuration conf) throws IOException {
        try (Connection connection = ConnectionFactory.createConnection(conf);
            Admin admin = connection.getAdmin()) {
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf("test_table"));
            table.addFamily(new HColumnDescriptor("DEFAULT_COLUMN_FAMILY"));
            log.info("Creating table...");
            if(admin.tableExists(table.getTableName())) {
                admin.disableTable(table.getTableName());
                admin.deleteTable(table.getTableName());
            }
            admin.createTable(table);
            log.info("Done...");
        }
    }

    public static void main(String[] args) {
        Configuration conf = setupHBaseConf();
        try {
            createTable(conf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
