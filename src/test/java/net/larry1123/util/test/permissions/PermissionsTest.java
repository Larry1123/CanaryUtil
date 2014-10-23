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
package net.larry1123.util.test.permissions;

import net.larry1123.util.api.abstracts.RemoteServer;
import net.larry1123.util.api.permissions.CanaryPermission;
import net.larry1123.util.api.permissions.PermissionsManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Larry1123
 * @since 10/22/2014 - 6:15 PM
 */
public class PermissionsTest {

    RemoteServer remoteServer = RemoteServer.getServer("TestServ");

    @Test
    public void testStuff() {
        CanaryPermission temp = getCanaryPermission();
        Assert.assertTrue(temp.WARP$SET.isParentOf(temp.WARP$SET$ADMIN));
    }

    private CanaryPermission getCanaryPermission() {
        return PermissionsManager.getManager().getCanaryPermissions(remoteServer);
    }

}
