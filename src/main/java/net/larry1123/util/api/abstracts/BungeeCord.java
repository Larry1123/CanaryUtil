/*
 * Copyright 2014 ElecEntertainment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.larry1123.util.api.abstracts;

import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;

import java.util.LinkedList;

/**
 * @author Larry1123
 * @since 10/29/2014 - 1:26 AM
 */
public interface BungeeCord {

    @Deprecated
    String getRealPlayerIp(Player player);

    int getServerPlayerCount(RemoteServer server);

    LinkedList<OfflinePlayer> getServerPlayerList(RemoteServer server);

    LinkedList<RemoteServer> getServerList();

    RemoteServer getCurrentServer();

    boolean sendPlayerToServer(Player player, RemoteServer server);

    boolean sendMessageToServer(RemoteServer server, String subCnannel, String data);

    boolean sendMessageToServerAsAllPlayers(RemoteServer server, String subCnannel, String data);

    boolean sendMessageToServerAsPlayer(RemoteServer server, String subCnannel, String data, Player player);

}
