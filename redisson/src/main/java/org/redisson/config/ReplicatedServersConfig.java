/**
 * Copyright (c) 2013-2022 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Configuration for an Azure Redis Cache or AWS ElastiCache servers. 
 * A replication group is composed of a single master endpoint and multiple read slaves.
 *
 * @author Steve Ungerer
 * @author Nikita Koksharov
 */
public class ReplicatedServersConfig extends BaseMasterSlaveServersConfig<ReplicatedServersConfig> {

    private static final Logger log = LoggerFactory.getLogger(ReplicatedServersConfig.class);
    /**
     * Replication group node urls list
     */
    private List<String> nodeAddresses = new ArrayList<>();

    /**
     * Replication group scan interval in milliseconds
     */
    private int scanInterval = 1000;

    /**
     * Database index used for Redis connection
     */
    private int database = 0;

    public ReplicatedServersConfig() {
    }

    ReplicatedServersConfig(ReplicatedServersConfig config) {
        super(config);
        setNodeAddresses(config.getNodeAddresses());
        setScanInterval(config.getScanInterval());
        setDatabase(config.getDatabase());
    }

    /**
     * Add Redis cluster node address. Use follow format -- <code>host:port</code>
     *
     * @param addresses in <code>host:port</code> format
     * @return config
     */
    public ReplicatedServersConfig addNodeAddress(String... addresses) {
        nodeAddresses.addAll(Arrays.asList(addresses));
        return this;
    }
    public List<String> getNodeAddresses() {
        log.warn("[CTEST][GET-PARAM] " + "nodeAddresses"); //CTEST
        return nodeAddresses;
    }
    public void setNodeAddresses(List<String> nodeAddresses) {
        log.warn("[CTEST][SET-PARAM] " + "nodeAddresses" + getStackTrace()); //CTEST
        this.nodeAddresses = nodeAddresses;
    }

    public int getScanInterval() {
        log.warn("[CTEST][GET-PARAM] " + "scanInterval"); //CTEST
        return scanInterval;
    }
    /**
     * Replication group scan interval in milliseconds
     * <p>
     * Default is <code>1000</code>
     *
     * @param scanInterval in milliseconds
     * @return config
     */
    public ReplicatedServersConfig setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
        log.warn("[CTEST][SET-PARAM] " + "scanInterval" + getStackTrace()); //CTEST
        return this;
    }

    /**
     * Database index used for Redis connection.
     * <p>
     * Default is <code>0</code>
     *
     * @param database number
     * @return config
     */
    public ReplicatedServersConfig setDatabase(int database) {
        this.database = database;
        log.warn("[CTEST][SET-PARAM] " + "database" + getStackTrace()); //CTEST
        return this;
    }
    public int getDatabase() {
        log.warn("[CTEST][GET-PARAM] " + "database"); //CTEST
        return database;
    }
    private static String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(
                    element.getClassName() + "#" + element.getMethodName() + "#" + element.getLineNumber() + "\t"
            );
        }
        return stacktrace;
    }
}
