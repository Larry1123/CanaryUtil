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
package net.larry1123.util.api.task;

/**
 * @author Larry1123
 * @since 10/28/2014 - 10:29 PM
 */
public interface TaskHandler {

    /**
     * Starts the task if it should be started
     */
    public boolean startTask();

    /**
     * Stop the task
     */
    public void endTask();

    /**
     * Will stop the task and restart the task if it can
     */
    public boolean reloadTask();

}
