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

import org.redisson.api.HostNatMapper;
import org.redisson.api.HostPortNatMapper;
import org.redisson.api.NatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class SentinelServersConfig extends BaseMasterSlaveServersConfig<SentinelServersConfig> {

    private static final Logger log = LoggerFactory.getLogger(SentinelServersConfig.class);
    private List<String> sentinelAddresses = new ArrayList<>();
    
    private NatMapper natMapper = NatMapper.direct();

    private String masterName;

    private String sentinelUsername;

    private String sentinelPassword;

    /**
     * Database index used for Redis connection
     */
    private int database = 0;
    
    /**
     * Sentinel scan interval in milliseconds
     */
    private int scanInterval = 1000;

    private boolean checkSentinelsList = true;

    private boolean checkSlaveStatusWithSyncing = true;

    private boolean sentinelsDiscovery = true;

    public SentinelServersConfig() {
    }

    SentinelServersConfig(SentinelServersConfig config) {
        super(config);
        setSentinelAddresses(config.getSentinelAddresses());
        setMasterName(config.getMasterName());
        setDatabase(config.getDatabase());
        setScanInterval(config.getScanInterval());
        setNatMapper(config.getNatMapper());
        setCheckSentinelsList(config.isCheckSentinelsList());
        setSentinelUsername(config.getSentinelUsername());
        setSentinelPassword(config.getSentinelPassword());
        setCheckSlaveStatusWithSyncing(config.isCheckSlaveStatusWithSyncing());
        setSentinelsDiscovery(config.isSentinelsDiscovery());
    }

    /**
     * Master server name used by Redis Sentinel servers and master change monitoring task.
     *
     * @param masterName of Redis
     * @return config
     */
    public SentinelServersConfig setMasterName(String masterName) {
        this.masterName = masterName;
        log.warn("[CTEST][SET-PARAM] " + "masterName" + getStackTrace()); //CTEST
        return this;
    }
    public String getMasterName() {
        log.warn("[CTEST][GET-PARAM] " + "masterName"); //CTEST
        return masterName;
    }

    /**
     * Username required by the Redis Sentinel servers for authentication.
     *
     * @param sentinelUsername of Redis
     * @return config
     */
    public SentinelServersConfig setSentinelUsername(String sentinelUsername) {
        this.sentinelUsername = sentinelUsername;
        log.warn("[CTEST][SET-PARAM] " + "sentinelUsername" + getStackTrace()); //CTEST
        return this;
    }

    public String getSentinelUsername() {
        log.warn("[CTEST][GET-PARAM] " + "sentinelUsername"); //CTEST
        return sentinelUsername;
    }

    /**
     * Password required by the Redis Sentinel servers for authentication.
     * Used only if sentinel password differs from master and slave.
     *
     * @param sentinelPassword of Redis
     * @return config
     */
    public SentinelServersConfig setSentinelPassword(String sentinelPassword) {
        this.sentinelPassword = sentinelPassword;
        log.warn("[CTEST][SET-PARAM] " + "sentinelPassword" + getStackTrace()); //CTEST
        return this;
    }
    public String getSentinelPassword() {
        log.warn("[CTEST][GET-PARAM] " + "sentinelPassword"); //CTEST
        return sentinelPassword;
    }


    /**
     * Add Redis Sentinel node address in host:port format. Multiple nodes at once could be added.
     *
     * @param addresses of Redis
     * @return config
     */
    public SentinelServersConfig addSentinelAddress(String... addresses) {
        sentinelAddresses.addAll(Arrays.asList(addresses));
        return this;
    }
    public List<String> getSentinelAddresses() {
        log.warn("[CTEST][GET-PARAM] " + "sentinelAddresses"); //CTEST
        return sentinelAddresses;
    }
    public void setSentinelAddresses(List<String> sentinelAddresses) {
        this.sentinelAddresses = sentinelAddresses;
        log.warn("[CTEST][SET-PARAM] " + "sentinelAddresses" + getStackTrace()); //CTEST
    }

    /**
     * Database index used for Redis connection
     * Default is <code>0</code>
     *
     * @param database number
     * @return config
     */
    public SentinelServersConfig setDatabase(int database) {
        this.database = database;
        log.warn("[CTEST][SET-PARAM] " + "database" + getStackTrace()); //CTEST
        return this;
    }
    public int getDatabase() {
        log.warn("[CTEST][GET-PARAM] " + "database"); //CTEST
        return database;
    }

    public int getScanInterval() {
        log.warn("[CTEST][GET-PARAM] " + "scanInterval"); //CTEST
        return scanInterval;
    }
    /**
     * Sentinel scan interval in milliseconds
     * <p>
     * Default is <code>1000</code>
     *
     * @param scanInterval in milliseconds
     * @return config
     */
    public SentinelServersConfig setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
        log.warn("[CTEST][SET-PARAM] " + "scanInterval" + getStackTrace()); //CTEST
        return this;
    }

    /*
     * Use {@link #setNatMapper(NatMapper)}
     */
    @Deprecated
    public SentinelServersConfig setNatMap(Map<String, String> natMap) {
        HostPortNatMapper mapper = new HostPortNatMapper();
        mapper.setHostsPortMap(natMap);
        this.natMapper = mapper;
        return this;
    }

    public NatMapper getNatMapper() {
        log.warn("[CTEST][GET-PARAM] " + "natMapper"); //CTEST
        return natMapper;
    }

    /**
     * Defines NAT mapper which maps Redis URI object.
     * Applied to all Redis connections.
     *
     * @see HostNatMapper
     * @see HostPortNatMapper
     *
     * @param natMapper - nat mapper object
     * @return config
     */
    public SentinelServersConfig setNatMapper(NatMapper natMapper) {
        this.natMapper = natMapper;
        log.warn("[CTEST][SET-PARAM] " + "natMapper" + getStackTrace()); //CTEST
        return this;
    }

    public boolean isCheckSentinelsList() {
        log.warn("[CTEST][GET-PARAM] " + "checkSentinelsList"); //CTEST
        return checkSentinelsList;
    }

    /**
     * Enables sentinels list check during Redisson startup.
     * <p>
     * Default is <code>true</code>
     *
     * @param checkSentinelsList - boolean value
     * @return config
     */
    public SentinelServersConfig setCheckSentinelsList(boolean checkSentinelsList) {
        this.checkSentinelsList = checkSentinelsList;
        log.warn("[CTEST][SET-PARAM] " + "checkSentinelsList" + getStackTrace()); //CTEST
        return this;
    }

    public boolean isCheckSlaveStatusWithSyncing() {
        log.warn("[CTEST][GET-PARAM] " + "checkSlaveStatusWithSyncing"); //CTEST
        return checkSlaveStatusWithSyncing;
    }

    /**
     * check node status from sentinel with 'master-link-status' flag
     * <p>
     * Default is <code>true</code>
     *
     * @param checkSlaveStatusWithSyncing - boolean value
     * @return config
     */
    public SentinelServersConfig setCheckSlaveStatusWithSyncing(boolean checkSlaveStatusWithSyncing) {
        this.checkSlaveStatusWithSyncing = checkSlaveStatusWithSyncing;
        log.warn("[CTEST][SET-PARAM] " + "checkSlaveStatusWithSyncing" + getStackTrace()); //CTEST
        return this;
    }

    public boolean isSentinelsDiscovery() {
        log.warn("[CTEST][GET-PARAM] " + "sentinelsDiscovery"); //CTEST
        return sentinelsDiscovery;
    }

    /**
     * Enables sentinels discovery.
     * <p>
     * Default is <code>true</code>
     *
     * @param sentinelsDiscovery - boolean value
     * @return config
     */
    public SentinelServersConfig setSentinelsDiscovery(boolean sentinelsDiscovery) {
        this.sentinelsDiscovery = sentinelsDiscovery;
        log.warn("[CTEST][SET-PARAM] " + "sentinelsDiscovery" + getStackTrace()); //CTEST
        return this;
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
