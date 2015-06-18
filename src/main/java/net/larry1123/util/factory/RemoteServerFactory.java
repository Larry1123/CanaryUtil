package net.larry1123.util.factory;

import net.larry1123.elec.util.factorys.Factory;
import net.larry1123.util.api.abstracts.RemoteServer;

import java.util.HashMap;

/**
 * @author Larry1123
 * @since 5/21/2015
 */
public class RemoteServerFactory extends Factory {

    private final HashMap<String, RemoteServer> serverHashMap = new HashMap<String, RemoteServer>();

    public RemoteServerFactory(String name) {
        super(name);
    }

    public RemoteServer getServer(String name) {
        RemoteServer remoteServer;
        if (!contains(name)) {
            remoteServer = new RemoteServer(name);
            serverHashMap.put(remoteServer.getServerName(), remoteServer);
        }
        else {
            remoteServer = serverHashMap.get(name);
        }
        return remoteServer;
    }

    public RemoteServer getALLServerObject() {
        return getServer("ALL");
    }

    public boolean contains(String name) {
        return serverHashMap.containsKey(name);
    }

    public boolean contains(RemoteServer remoteServer) {
        return serverHashMap.containsValue(remoteServer);
    }

}
