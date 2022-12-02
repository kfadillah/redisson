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

import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Nikita Koksharov
 *
 * @param <T> config type
 */
public class BaseMasterSlaveServersConfig<T extends BaseMasterSlaveServersConfig<T>> extends BaseConfig<T> {

    /**
     * Сonnection load balancer for multiple Redis slave servers
     */
    private LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
    private static final Logger log = LoggerFactory.getLogger(BaseMasterSlaveServersConfig.class);
    /**
     * Redis 'slave' node minimum idle connection amount for <b>each</b> slave node
     */
    private int slaveConnectionMinimumIdleSize = 24;

    /**
     * Redis 'slave' node maximum connection pool size for <b>each</b> slave node
     */
    private int slaveConnectionPoolSize = 64;

    private int failedSlaveReconnectionInterval = 3000;
    
    private int failedSlaveCheckInterval = 180000;
    
    /**
     * Redis 'master' node minimum idle connection amount for <b>each</b> slave node
     */
    private int masterConnectionMinimumIdleSize = 24;

    /**
     * Redis 'master' node maximum connection pool size
     */
    private int masterConnectionPoolSize = 64;

    private ReadMode readMode = ReadMode.SLAVE;
    
    private SubscriptionMode subscriptionMode = SubscriptionMode.MASTER;
    
    /**
     * Redis 'slave' node minimum idle subscription (pub/sub) connection amount for <b>each</b> slave node
     */
    private int subscriptionConnectionMinimumIdleSize = 1;

    /**
     * Redis 'slave' node maximum subscription (pub/sub) connection pool size for <b>each</b> slave node
     */
    private int subscriptionConnectionPoolSize = 50;

    private long dnsMonitoringInterval = 5000;
    
    public BaseMasterSlaveServersConfig() {
    }

    BaseMasterSlaveServersConfig(T config) {
        super(config);
        setLoadBalancer(config.getLoadBalancer());
        setMasterConnectionPoolSize(config.getMasterConnectionPoolSize());
        setSlaveConnectionPoolSize(config.getSlaveConnectionPoolSize());
        setSubscriptionConnectionPoolSize(config.getSubscriptionConnectionPoolSize());
        setMasterConnectionMinimumIdleSize(config.getMasterConnectionMinimumIdleSize());
        setSlaveConnectionMinimumIdleSize(config.getSlaveConnectionMinimumIdleSize());
        setSubscriptionConnectionMinimumIdleSize(config.getSubscriptionConnectionMinimumIdleSize());
        setReadMode(config.getReadMode());
        setSubscriptionMode(config.getSubscriptionMode());
        setDnsMonitoringInterval(config.getDnsMonitoringInterval());
        setFailedSlaveCheckInterval(config.getFailedSlaveCheckInterval());
        setFailedSlaveReconnectionInterval(config.getFailedSlaveReconnectionInterval());
    }

    /**
     * Redis 'slave' servers connection pool size for <b>each</b> slave node.
     * <p>
     * Default is <code>64</code>
     * <p>
     * @see #setSlaveConnectionMinimumIdleSize(int)
     *
     * @param slaveConnectionPoolSize - size of pool
     * @return config
     */
    public T setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
        log.warn("[CTEST][SET-PARAM] " + "slaveConnectionPoolSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getSlaveConnectionPoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "slaveConnectionPoolSize"); //CTEST
        return slaveConnectionPoolSize;
    }
    
    /**
     * Interval of Redis Slave reconnection attempt when
     * it was excluded from internal list of available servers.
     * <p>
     * On every such timeout event Redisson tries
     * to connect to disconnected Redis server.
     * <p>
     * Default is 3000
     *
     * @param failedSlavesReconnectionTimeout - retry timeout in milliseconds
     * @return config
     */

    public T setFailedSlaveReconnectionInterval(int failedSlavesReconnectionTimeout) {
        this.failedSlaveReconnectionInterval = failedSlavesReconnectionTimeout;
        log.warn("[CTEST][SET-PARAM] " + "failedSlaveReconnectionInterval" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getFailedSlaveReconnectionInterval() {
        log.warn("[CTEST][GET-PARAM] " + "failedSlaveReconnectionInterval"); //CTEST
        return failedSlaveReconnectionInterval;
    }

    
    /**
     * Redis Slave node failing to execute commands is excluded from the internal list of available nodes
     * when the time interval from the moment of first Redis command execution failure
     * on this server reaches <code>slaveFailsInterval</code> value.
     * <p>
     * Default is <code>180000</code>
     *
     * @param slaveFailsInterval - time interval in milliseconds
     * @return config
     */
    public T setFailedSlaveCheckInterval(int slaveFailsInterval) {
        this.failedSlaveCheckInterval = slaveFailsInterval;
        log.warn("[CTEST][SET-PARAM] " + "failedSlaveCheckInterval" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getFailedSlaveCheckInterval() {
        log.warn("[CTEST][GET-PARAM] " + "failedSlaveCheckInterval"); //CTEST
        return failedSlaveCheckInterval;
    }

    /**
     * Redis 'master' server connection pool size.
     * <p>
     * Default is <code>64</code>
     *
     * @see #setMasterConnectionMinimumIdleSize(int)
     * 
     * @param masterConnectionPoolSize - pool size
     * @return config
     *
     */
    public T setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
        log.warn("[CTEST][SET-PARAM] " + "masterConnectionPoolSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getMasterConnectionPoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "masterConnectionPoolSize"); //CTEST
        return masterConnectionPoolSize;
    }

    /**
     * Сonnection load balancer to multiple Redis slave servers.
     * Uses Round-robin algorithm by default
     *
     * @param loadBalancer object
     * @return config
     *
     * @see org.redisson.connection.balancer.RandomLoadBalancer
     * @see org.redisson.connection.balancer.RoundRobinLoadBalancer
     * @see org.redisson.connection.balancer.WeightedRoundRobinBalancer
     */
    public T setLoadBalancer(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
        log.warn("[CTEST][SET-PARAM] " + "loadBalancer" + getStackTrace()); //CTEST
        return (T) this;
    }
    public LoadBalancer getLoadBalancer() {
        log.warn("[CTEST][GET-PARAM] " + "loadBalancer"); //CTEST
        return loadBalancer;
    }

    /**
     * Maximum connection pool size for subscription (pub/sub) channels
     * <p>
     * Default is <code>50</code>
     * <p>
     * @see #setSubscriptionConnectionMinimumIdleSize(int)
     * 
     * @param subscriptionConnectionPoolSize - pool size
     * @return config
     */
    public T setSubscriptionConnectionPoolSize(int subscriptionConnectionPoolSize) {
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionConnectionPoolSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getSubscriptionConnectionPoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionConnectionPoolSize"); //CTEST
        return subscriptionConnectionPoolSize;
    }

    
    /**
     * Minimum idle connection pool size for subscription (pub/sub) channels
     * <p>
     * Default is <code>24</code>
     * <p>
     * @see #setSlaveConnectionPoolSize(int)
     * 
     * @param slaveConnectionMinimumIdleSize - pool size
     * @return config
     */
    public T setSlaveConnectionMinimumIdleSize(int slaveConnectionMinimumIdleSize) {
        this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
        log.warn("[CTEST][SET-PARAM] " + "slaveConnectionMinimumIdleSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getSlaveConnectionMinimumIdleSize() {
        log.warn("[CTEST][GET-PARAM] " + "slaveConnectionMinimumIdleSize"); //CTEST
        return slaveConnectionMinimumIdleSize;
    }

    /**
     * Redis 'master' node minimum idle connection amount for <b>each</b> slave node
     * <p>
     * Default is <code>24</code>
     * <p>
     * @see #setMasterConnectionPoolSize(int)
     * 
     * @param masterConnectionMinimumIdleSize - pool size
     * @return config
     */
    public T setMasterConnectionMinimumIdleSize(int masterConnectionMinimumIdleSize) {
        this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
        log.warn("[CTEST][SET-PARAM] " + "masterConnectionMinimumIdleSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getMasterConnectionMinimumIdleSize() {
        log.warn("[CTEST][GET-PARAM] " + "masterConnectionMinimumIdleSize"); //CTEST
        return masterConnectionMinimumIdleSize;
    }

    /**
     * Redis 'slave' node minimum idle subscription (pub/sub) connection amount for <b>each</b> slave node.
     * <p>
     * Default is <code>1</code>
     * <p>
     * @see #setSubscriptionConnectionPoolSize(int)
     * 
     * @param subscriptionConnectionMinimumIdleSize - pool size
     * @return config
     */
    public T setSubscriptionConnectionMinimumIdleSize(int subscriptionConnectionMinimumIdleSize) {
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionConnectionMinimumIdleSize" + getStackTrace()); //CTEST
        return (T) this;
    }
    public int getSubscriptionConnectionMinimumIdleSize() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionConnectionMinimumIdleSize"); //CTEST
        return subscriptionConnectionMinimumIdleSize;
    }

    
    /**
     * Set node type used for read operation.
     * <p>
     * Default is <code>SLAVE</code>
     *
     * @param readMode param
     * @return config
     */
    public T setReadMode(ReadMode readMode) {
        this.readMode = readMode;
        log.warn("[CTEST][SET-PARAM] " + "readMode" + getStackTrace()); //CTEST
        return (T) this;
    }
    public ReadMode getReadMode() {
        log.warn("[CTEST][GET-PARAM] " + "readMode"); //CTEST
        return readMode;
    }
    
    public boolean checkSkipSlavesInit() {
        return getReadMode() == ReadMode.MASTER && getSubscriptionMode() == SubscriptionMode.MASTER;
    }

    /**
     * Set node type used for subscription operation.
     * <p>
     * Default is <code>MASTER</code>
     *
     * @param subscriptionMode param
     * @return config
     */
    public T setSubscriptionMode(SubscriptionMode subscriptionMode) {
        this.subscriptionMode = subscriptionMode;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionMode" + getStackTrace()); //CTEST
        return (T) this;
    }
    public SubscriptionMode getSubscriptionMode() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionMode"); //CTEST
        return subscriptionMode;
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
    public T setDnsMonitoringInterval(long dnsMonitoringInterval) {
        this.dnsMonitoringInterval = dnsMonitoringInterval;
        log.warn("[CTEST][SET-PARAM] " + "dnsMonitoringInterval" + getStackTrace()); //CTEST
        return (T) this;
    }
    public long getDnsMonitoringInterval() {
        log.warn("[CTEST][GET-PARAM] " + "dnsMonitoringInterval"); //CTEST
        return dnsMonitoringInterval;
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
