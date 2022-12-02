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

import org.redisson.api.NameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * 
 * @author Nikita Koksharov
 *
 * @param <T> config type
 */
public class BaseConfig<T extends BaseConfig<T>> {
    
    private static final Logger log = LoggerFactory.getLogger("config");

    /**
     * If pooled connection not used for a <code>timeout</code> time
     * and current connections amount bigger than minimum idle connections pool size,
     * then it will closed and removed from pool.
     * Value in milliseconds.
     *
     */
    private int idleConnectionTimeout = 10000;

    /**
     * Timeout during connecting to any Redis server.
     * Value in milliseconds.
     *
     */
    private int connectTimeout = 10000;

    /**
     * Redis server response timeout. Starts to countdown when Redis command was succesfully sent.
     * Value in milliseconds.
     *
     */
    private int timeout = 3000;

    private int retryAttempts = 3;

    private int retryInterval = 1500;

    /**
     * Password for Redis authentication. Should be null if not needed
     */
    private String password;

    private String username;

    /**
     * Subscriptions per Redis connection limit
     */
    private int subscriptionsPerConnection = 5;

    /**
     * Name of client connection
     */
    private String clientName;

    private boolean sslEnableEndpointIdentification = true;
    
    private SslProvider sslProvider = SslProvider.JDK;
    
    private URL sslTruststore;
    
    private String sslTruststorePassword;
    
    private URL sslKeystore;
    
    private String sslKeystorePassword;

    private String[] sslProtocols;

    private int pingConnectionInterval = 30000;

    private boolean keepAlive;
    
    private boolean tcpNoDelay = true;

    private NameMapper nameMapper = NameMapper.direct();

    
    BaseConfig() {
    }

    BaseConfig(T config) {
        setPassword(config.getPassword());
        setUsername(config.getUsername());
        setSubscriptionsPerConnection(config.getSubscriptionsPerConnection());
        setRetryAttempts(config.getRetryAttempts());
        setRetryInterval(config.getRetryInterval());
        setTimeout(config.getTimeout());
        setClientName(config.getClientName());
        setConnectTimeout(config.getConnectTimeout());
        setIdleConnectionTimeout(config.getIdleConnectionTimeout());
        setSslEnableEndpointIdentification(config.isSslEnableEndpointIdentification());
        setSslProvider(config.getSslProvider());
        setSslTruststore(config.getSslTruststore());
        setSslTruststorePassword(config.getSslTruststorePassword());
        setSslKeystore(config.getSslKeystore());
        setSslKeystorePassword(config.getSslKeystorePassword());
        setSslProtocols(config.getSslProtocols());
        setPingConnectionInterval(config.getPingConnectionInterval());
        setKeepAlive(config.isKeepAlive());
        setTcpNoDelay(config.isTcpNoDelay());
        setNameMapper(config.getNameMapper());
    }

    /**
     * Subscriptions per Redis connection limit
     * <p>
     * Default is <code>5</code>
     *
     * @param subscriptionsPerConnection amount
     * @return config
     */
    public T setSubscriptionsPerConnection(int subscriptionsPerConnection) {
        this.subscriptionsPerConnection = subscriptionsPerConnection;
        log.warn("[CTEST][SET-PARAM] " + "subscriptionsPerConnection" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getSubscriptionsPerConnection() {
        log.warn("[CTEST][GET-PARAM] " + "subscriptionsPerConnection"); //CTEST
        return subscriptionsPerConnection;
    }

    /**
     * Password for Redis authentication. Should be null if not needed.
     * <p>
     * Default is <code>null</code>
     *
     * @param password for connection
     * @return config
     */
    public T setPassword(String password) {
        this.password = password;
        log.warn("[CTEST][SET-PARAM] " + "password" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String getPassword() {
        log.warn("[CTEST][GET-PARAM] " + "password"); //CTEST
        return password;
    }

    /**
     * Username for Redis authentication. Should be null if not needed
     * <p>
     * Default is <code>null</code>
     * <p>
     * Requires Redis 6.0+
     *
     * @param username for connection
     * @return config
     */
    public T setUsername(String username) {
        this.username = username;
        log.warn("[CTEST][SET-PARAM] " + "username" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String getUsername() {
        log.warn("[CTEST][GET-PARAM] " + "username"); //CTEST
        return username;
    }

    /**
     * Error will be thrown if Redis command can't be sent to Redis server after <code>retryAttempts</code>.
     * But if it sent successfully then <code>timeout</code> will be started.
     * <p>
     * Default is <code>3</code> attempts
     *
     * @see #timeout
     * @param retryAttempts - retry attempts
     * @return config
     */
    public T setRetryAttempts(int retryAttempts) {
        log.warn("[CTEST][SET-PARAM] " + "retryAttempts" + getStackTrace()); //CTEST
        this.retryAttempts = retryAttempts;
        return (T) this;
    }

    public int getRetryAttempts() {
        log.warn("[CTEST][GET-PARAM] " + "retryAttempts"); //CTEST
        return retryAttempts;
    }

    /**
     * Defines time interval for another one attempt send Redis command 
     * if it hasn't been sent already.
     * <p>
     * Default is <code>1500</code> milliseconds
     *
     * @param retryInterval - time in milliseconds
     * @return config
     */
    public T setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
        log.warn("[CTEST][SET-PARAM] " + "retryInterval" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getRetryInterval() {
        log.warn("[CTEST][GET-PARAM] " + "retryInterval"); //CTEST
        return retryInterval;
    }

    /**
     * Redis server response timeout. Starts to countdown when Redis command has been successfully sent.
     * <p>
     * Default is <code>3000</code> milliseconds
     *
     * @param timeout in milliseconds
     * @return config
     */
    public T setTimeout(int timeout) {
        log.warn("[CTEST][SET-PARAM] " + "timeout" + getStackTrace()); //CTEST
        this.timeout = timeout;
        return (T) this;
    }

    public int getTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "timeout"); //CTEST
        return timeout;
    }

    /**
     * Setup connection name during connection init
     * via CLIENT SETNAME command
     * <p>
     * Default is <code>null</code>
     *
     * @param clientName - name of client
     * @return config
     */
    public T setClientName(String clientName) {
        this.clientName = clientName;
        log.warn("[CTEST][SET-PARAM] " + "clientName" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String getClientName() {
        log.warn("[CTEST][GET-PARAM] " + "clientName"); //CTEST
        return clientName;
    }

    /**
     * Timeout during connecting to any Redis server.
     * <p>
     * Default is <code>10000</code> milliseconds.
     * 
     * @param connectTimeout - timeout in milliseconds
     * @return config
     */
    public T setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        log.warn("[CTEST][SET-PARAM] " + "connectTimeout" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getConnectTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "connectTimeout"); //CTEST
        return connectTimeout;
    }

    /**
     * If pooled connection not used for a <code>timeout</code> time
     * and current connections amount bigger than minimum idle connections pool size,
     * then it will closed and removed from pool.
     * <p>
     * Default is <code>10000</code> milliseconds.
     *
     * @param idleConnectionTimeout - timeout in milliseconds
     * @return config
     */
    public T setIdleConnectionTimeout(int idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
        log.warn("[CTEST][SET-PARAM] " + "idleConnectionTimeout" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getIdleConnectionTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "idleConnectionTimeout"); //CTEST
        return idleConnectionTimeout;
    }

    public boolean isSslEnableEndpointIdentification() {
        log.warn("[CTEST][GET-PARAM] " + "sslEnableEndpointIdentification"); //CTEST
        return sslEnableEndpointIdentification;
    }

    /**
     * Enables SSL endpoint identification.
     * <p>
     * Default is <code>true</code>
     * 
     * @param sslEnableEndpointIdentification - boolean value
     * @return config
     */
    public T setSslEnableEndpointIdentification(boolean sslEnableEndpointIdentification) {
        this.sslEnableEndpointIdentification = sslEnableEndpointIdentification;
        log.warn("[CTEST][SET-PARAM] " + "sslEnableEndpointIdentification" + getStackTrace()); //CTEST
        return (T) this;
    }

    public SslProvider getSslProvider() {
        log.warn("[CTEST][GET-PARAM] " + "sslProvider"); //CTEST
        return sslProvider;
    }

    /**
     * Defines SSL provider used to handle SSL connections.
     * <p>
     * Default is <code>JDK</code>
     * 
     * @param sslProvider - ssl provider 
     * @return config
     */
    public T setSslProvider(SslProvider sslProvider) {
        this.sslProvider = sslProvider;
        log.warn("[CTEST][SET-PARAM] " + "sslProvider" + getStackTrace()); //CTEST
        return (T) this;
    }

    public URL getSslTruststore() {
        log.warn("[CTEST][GET-PARAM] " + "sslTruststore"); //CTEST
        return sslTruststore;
    }

    /**
     * Defines path to SSL truststore 
     * <p>
     * Default is <code>null</code>
     *
     * @param sslTruststore - path
     * @return config
     */
    public T setSslTruststore(URL sslTruststore) {
        this.sslTruststore = sslTruststore;
        log.warn("[CTEST][SET-PARAM] " + "sslTruststore" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String getSslTruststorePassword() {
        log.warn("[CTEST][GET-PARAM] " + "sslTruststorePassword"); //CTEST
        return sslTruststorePassword;
    }

    /**
     * Defines password for SSL truststore.
     * SSL truststore is read on each new connection creation and can be dynamically reloaded.
     * <p>
     * Default is <code>null</code>
     *
     * @param sslTruststorePassword - password
     * @return config
     */
    public T setSslTruststorePassword(String sslTruststorePassword) {
        this.sslTruststorePassword = sslTruststorePassword;
        log.warn("[CTEST][SET-PARAM] " + "sslTruststorePassword" + getStackTrace()); //CTEST
        return (T) this;
    }

    public URL getSslKeystore() {
        log.warn("[CTEST][GET-PARAM] " + "sslKeystore"); //CTEST
        return sslKeystore;
    }

    /**
     * Defines path to SSL keystore.
     * SSL keystore is read on each new connection creation and can be dynamically reloaded.
     * <p>
     * Default is <code>null</code>
     *
     * @param sslKeystore - path to keystore
     * @return config
     */
    public T setSslKeystore(URL sslKeystore) {
        this.sslKeystore = sslKeystore;
        log.warn("[CTEST][SET-PARAM] " + "sslKeystore" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String getSslKeystorePassword() {
        log.warn("[CTEST][GET-PARAM] " + "sslKeystorePassword"); //CTEST
        return sslKeystorePassword;
    }

    /**
     * Defines password for SSL keystore
     * <p>
     * Default is <code>null</code>
     *
     * @param sslKeystorePassword - password
     * @return config
     */
    public T setSslKeystorePassword(String sslKeystorePassword) {
        this.sslKeystorePassword = sslKeystorePassword;
        log.warn("[CTEST][SET-PARAM] " + "sslKeystorePassword" + getStackTrace()); //CTEST
        return (T) this;
    }

    public String[] getSslProtocols() {
        log.warn("[CTEST][GET-PARAM] " + "sslProtocols"); //CTEST
        return sslProtocols;
    }

    /**
     * Defines SSL protocols.
     * Example values: TLSv1.3, TLSv1.2, TLSv1.1, TLSv1
     * <p>
     * Default is <code>null</code>
     *
     * @param sslProtocols - protocols
     * @return config
     */
    public T setSslProtocols(String[] sslProtocols) {
        this.sslProtocols = sslProtocols;
        log.warn("[CTEST][SET-PARAM] " + "sslProtocols" + getStackTrace()); //CTEST
        return (T) this;
    }

    public int getPingConnectionInterval() {
        log.warn("[CTEST][GET-PARAM] " + "pingConnectionInterval"); //CTEST
        return pingConnectionInterval;
    }

    /**
     * Defines PING command sending interval per connection to Redis.
     * <code>0</code> means disable.
     * <p>
     * Default is <code>30000</code>
     * 
     * @param pingConnectionInterval - time in milliseconds
     * @return config
     */
    public T setPingConnectionInterval(int pingConnectionInterval) {
        this.pingConnectionInterval = pingConnectionInterval;
        log.warn("[CTEST][SET-PARAM] " + "pingConnectionInterval" + getStackTrace()); //CTEST
        return (T) this;
    }

    public boolean isKeepAlive() {
        log.warn("[CTEST][GET-PARAM] " + "keepAlive"); //CTEST
        return keepAlive;
    }

    /**
     * Enables TCP keepAlive for connection
     * <p>
     * Default is <code>false</code>
     * 
     * @param keepAlive - boolean value
     * @return config
     */
    public T setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
        log.warn("[CTEST][SET-PARAM] " + "keepAlive" + getStackTrace()); //CTEST
        return (T) this;
    }

    public boolean isTcpNoDelay() {
        log.warn("[CTEST][GET-PARAM] " + "tcpNoDelay"); //CTEST
        return tcpNoDelay;
    }

    /**
     * Enables TCP noDelay for connection
     * <p>
     * Default is <code>true</code>
     * 
     * @param tcpNoDelay - boolean value
     * @return config
     */
    public T setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        log.warn("[CTEST][SET-PARAM] " + "tcpNoDelay" + getStackTrace()); //CTEST
        return (T) this;
    }


    public NameMapper getNameMapper() {
        log.warn("[CTEST][GET-PARAM] " + "nameMapper"); //CTEST
        return nameMapper;
    }

    /**
     * Defines Name mapper which maps Redisson object name.
     * Applied to all Redisson objects.
     *
     * @param nameMapper - name mapper object
     * @return config
     */
    public T setNameMapper(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
        log.warn("[CTEST][SET-PARAM] " + "nameMapper" + getStackTrace()); //CTEST
        return (T) this;
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
