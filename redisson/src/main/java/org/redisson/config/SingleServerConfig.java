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
/**
 * 
 * @author Nikita Koksharov
 *
 */
public class SingleServerConfig extends BaseConfig<SingleServerConfig> {

    private static final Logger log = LoggerFactory.getLogger(SingleServerConfig.class);
    /**
     * Redis server address
     *
     */
    private String address;

    /**
     * Minimum idle subscription connection amount
     */
    private int subscriptionConnectionMinimumIdleSize = 1;

    /**
     * Redis subscription connection maximum pool size
     *
     */
    private int subscriptionConnectionPoolSize = 50;

    /**
     * Minimum idle Redis connection amount
     */
    private int connectionMinimumIdleSize = 24;

    /**
     * Redis connection maximum pool size
     */
    private int connectionPoolSize = 64;

    /**
     * Database index used for Redis connection
     */
    private int database = 0;

    /**
     * Interval in milliseconds to check DNS
     */
    private long dnsMonitoringInterval = 5000;

    SingleServerConfig() {
    }

    SingleServerConfig(SingleServerConfig config) {
        super(config);
        setAddress(config.getAddress());
        setConnectionPoolSize(config.getConnectionPoolSize());
        setSubscriptionConnectionPoolSize(config.getSubscriptionConnectionPoolSize());
        setDnsMonitoringInterval(config.getDnsMonitoringInterval());
        setSubscriptionConnectionMinimumIdleSize(config.getSubscriptionConnectionMinimumIdleSize());
        setConnectionMinimumIdleSize(config.getConnectionMinimumIdleSize());
        setDatabase(config.getDatabase());
    }

    /**
     * Redis connection pool size
     * <p>
     * Default is <code>64</code>
     *
     * @param connectionPoolSize - pool size
     * @return config
     */
    public SingleServerConfig setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
        log.warn("[CTEST][SET-PARAM] " + "connectionPoolSize" + getStackTrace()); //CTEST
        return this;
    }
    public int getConnectionPoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "connectionPoolSize"); //CTEST
        return connectionPoolSize;
    }

    /**
     * Redis subscription-connection pool size limit
     * <p>
     * Default is <code>50</code>
     *
     * @param subscriptionConnectionPoolSize - pool size
     * @return config
     */
    public SingleServerConfig setSubscriptionConnectionPoolSize(int subscriptionConnectionPoolSize) {
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionConnectionPoolSize" + getStackTrace()); //CTEST
        return this;
    }
    public int getSubscriptionConnectionPoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionConnectionPoolSize"); //CTEST
        return subscriptionConnectionPoolSize;
    }

    /**
     * Set server address. Use follow format -- host:port
     *
     * @param address of Redis
     * @return config
     */
    public SingleServerConfig setAddress(String address) {
        if (address != null) {
            this.address = address;
            log.warn("[CTEST][SET-PARAM] " + "address" + getStackTrace()); //CTEST
        }
        return this;
    }
    public String getAddress() {
        log.warn("[CTEST][GET-PARAM] " + "address"); //CTEST
        return address;
    }

    /**
     * Interval in milliseconds to check the endpoint's DNS<p>
     * Applications must ensure the JVM DNS cache TTL is low enough to support this.<p>
     * Set <code>-1</code> to disable.
     * <p>
     * Default is <code>5000</code>.
     *
     * @param dnsMonitoringInterval time
     * @return config
     */
    public SingleServerConfig setDnsMonitoringInterval(long dnsMonitoringInterval) {
        this.dnsMonitoringInterval = dnsMonitoringInterval;
        log.warn("[CTEST][SET-PARAM] " + "dnsMonitoringInterval" + getStackTrace()); //CTEST
        return this;
    }
    public long getDnsMonitoringInterval() {
        log.warn("[CTEST][GET-PARAM] " + "dnsMonitoringInterval"); //CTEST
        return dnsMonitoringInterval;
    }

    /**
     * Minimum idle subscription connection amount.
     * <p>
     * Default is <code>1</code>
     * 
     * @param subscriptionConnectionMinimumIdleSize - connections amount
     * @return config
     *
     */
    public SingleServerConfig setSubscriptionConnectionMinimumIdleSize(int subscriptionConnectionMinimumIdleSize) {
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionConnectionMinimumIdleSize" + getStackTrace()); //CTEST
        return this;
    }
    public int getSubscriptionConnectionMinimumIdleSize() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionConnectionMinimumIdleSize"); //CTEST
        return subscriptionConnectionMinimumIdleSize;
    }

    /**
     * Minimum idle Redis connection amount.
     * <p>
     * Default is <code>24</code>
     *
     * @param connectionMinimumIdleSize - connections amount
     * @return config
     */
    public SingleServerConfig setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
        log.warn("[CTEST][SET-PARAM] " + "connectionMinimumIdleSize" + getStackTrace()); //CTEST
        return this;
    }
    public int getConnectionMinimumIdleSize() {
        log.warn("[CTEST][GET-PARAM] " + "connectionMinimumIdleSize"); //CTEST
        return connectionMinimumIdleSize;
    }

    /**
     * Database index used for Redis connection
     * Default is <code>0</code>
     *
     * @param database index
     * @return config
     */
    public SingleServerConfig setDatabase(int database) {
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
