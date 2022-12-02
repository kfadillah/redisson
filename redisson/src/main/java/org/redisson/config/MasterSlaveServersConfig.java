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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class MasterSlaveServersConfig extends BaseMasterSlaveServersConfig<MasterSlaveServersConfig> {

    /**
     * Redis slave servers addresses
     */
    private Set<String> slaveAddresses = new HashSet<String>();
    private static final Logger log = LoggerFactory.getLogger(MasterSlaveServersConfig.class);
    /**
     * Redis master server address
     */
    private String masterAddress;

    /**
     * Database index used for Redis connection
     */
    private int database = 0;

    public MasterSlaveServersConfig() {
    }

    MasterSlaveServersConfig(MasterSlaveServersConfig config) {
        super(config);
        setLoadBalancer(config.getLoadBalancer());
        setMasterAddress(config.getMasterAddress());
        setSlaveAddresses(config.getSlaveAddresses());
        setDatabase(config.getDatabase());
    }

    /**
     * Set Redis master server address. Use follow format -- host:port
     *
     * @param masterAddress of Redis
     * @return config
     */
    public MasterSlaveServersConfig setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
        log.warn("[CTEST][SET-PARAM] " + "masterAddress" + getStackTrace()); //CTEST
        return this;
    }
    public String getMasterAddress() {
        log.warn("[CTEST][GET-PARAM] " + "masterAddress"); //CTEST
        return masterAddress;
    }

    /**
     * Add Redis slave server address. Use follow format -- host:port
     *
     * @param addresses of Redis
     * @return config
     */
    public MasterSlaveServersConfig addSlaveAddress(String... addresses) {
        slaveAddresses.addAll(Arrays.asList(addresses));
        return this;
    }
    public MasterSlaveServersConfig addSlaveAddress(String slaveAddress) {
        slaveAddresses.add(slaveAddress);
        return this;
    }
    public Set<String> getSlaveAddresses() {
        log.warn("[CTEST][GET-PARAM] " + "slaveAddresses"); //CTEST
        return slaveAddresses;
    }
    public void setSlaveAddresses(Set<String> readAddresses) {
        log.warn("[CTEST][SET-PARAM] " + "slaveAddresses" + getStackTrace()); //CTEST
        this.slaveAddresses = readAddresses;
    }

    /**
     * Database index used for Redis connection
     * Default is <code>0</code>
     *
     * @param database number
     * @return config
     */
    public MasterSlaveServersConfig setDatabase(int database) {
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
