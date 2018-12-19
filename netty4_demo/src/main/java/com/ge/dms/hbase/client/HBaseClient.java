package com.ge.dms.hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public class HBaseClient {

    private static Configuration setupHBaseConf() {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.56.101");
        config.set("hbase.zookeeper.property.clientProperty", "2181");
        return config;
    }

    private static void createTable(Configuration conf) throws IOException {
        try (Connection connection = ConnectionFactory.createConnection(conf);
            Admin admin = connection.getAdmin()) {
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf("testtable"));
            tableDescriptorBuilder.build();
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
