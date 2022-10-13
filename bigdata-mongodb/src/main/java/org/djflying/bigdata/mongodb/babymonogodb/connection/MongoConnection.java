package org.djflying.bigdata.mongodb.babymonogodb.connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 */
public class MongoConnection extends MongoClient {

    public MongoConnection(String userName, String password, String host, int port, String databaseName) {
        super(new ServerAddress(host, port), Arrays.asList(MongoCredential.createCredential(userName, databaseName, password.toCharArray())));
    }

    public MongoConnection(String userName, String password, List<ServerAddress> addresses, String databaseName) {
        super(addresses, Arrays.asList(MongoCredential.createCredential(userName, databaseName, password.toCharArray())));
    }

    public MongoConnection(String userName, String password, String addressesStr, String databaseName) {
        super(createServerAddress(addressesStr), Arrays.asList(MongoCredential.createCredential(userName, databaseName, password.toCharArray())));
    }

    public MongoConnection(String host, int port) {
        super(host, port);
    }

    /**
     * 拆开服务地址
     * @param addressStr  127.0.0.1:27017;
     * @return 服务地址
     */
    public static List<ServerAddress> createServerAddress(String addressStr) {
        List<ServerAddress> serverAddressesList = new ArrayList<ServerAddress>();
        String[] addressArray = addressStr.split(";");
        for (String address : addressArray) {
            String[] hostAndPort = address.split(":");
            serverAddressesList.add(new ServerAddress(hostAndPort[0], new Integer(hostAndPort[1])));
        }

        return serverAddressesList;
    }
}
