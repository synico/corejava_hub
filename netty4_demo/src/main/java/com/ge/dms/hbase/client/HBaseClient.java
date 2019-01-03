package com.ge.dms.hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
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
            table.addFamily(new HColumnDescriptor("info"));
            log.info("Creating table...");
            if(admin.tableExists(table.getTableName())) {
                admin.disableTable(table.getTableName());
                admin.deleteTable(table.getTableName());
            }
            admin.createTable(table);
            log.info("Done...");
        }
    }

    private static Put saveData2Table(Configuration conf) {
        Table table = null;
        Connection conn = null;
        try {
            conn = ConnectionFactory.createConnection(conf);
            table = conn.getTable(TableName.valueOf("test_table"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        Put p = new Put(Bytes.toBytes("TheRealMT"));
        p.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("Mark Twain"));
        p.addColumn(Bytes.toBytes("info"), Bytes.toBytes("email"), Bytes.toBytes("nick@qq.com"));
        p.addColumn(Bytes.toBytes("info"), Bytes.toBytes("password"), Bytes.toBytes("test"));
        try {
            table.put(p);
            table.close();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return p;
    }

    public static void main(String[] args) {
        Configuration conf = setupHBaseConf();
        try {
            createTable(conf);
            saveData2Table(conf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
