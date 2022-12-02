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
public class ClusterServersConfig extends BaseMasterSlaveServersConfig<ClusterServersConfig> {

    private NatMapper natMapper = NatMapper.direct();
    private static final Logger log = LoggerFactory.getLogger(ClusterServersConfig.class);
    /**
     * Redis cluster node urls list
     */
    private List<String> nodeAddresses = new ArrayList<>();

    /**
     * Redis cluster scan interval in milliseconds
     */
    private int scanInterval = 5000;

    private boolean checkSlotsCoverage = true;

    public ClusterServersConfig() {
    }

    ClusterServersConfig(ClusterServersConfig config) {
        super(config);
        setNodeAddresses(config.getNodeAddresses());
        setScanInterval(config.getScanInterval());
        setNatMapper(config.getNatMapper());
        setCheckSlotsCoverage(config.isCheckSlotsCoverage());
    }

    /**
     * Add Redis cluster node address. Use follow format -- <code>host:port</code>
     *
     * @param addresses in <code>host:port</code> format
     * @return config
     */
    public ClusterServersConfig addNodeAddress(String... addresses) {
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
     * Redis cluster scan interval in milliseconds
     * <p>
     * Default is <code>5000</code>
     *
     * @param scanInterval in milliseconds
     * @return config
     */
    public ClusterServersConfig setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
        log.warn("[CTEST][SET-PARAM] " + "scanInterval" + getStackTrace()); //CTEST
        return this;
    }

    public boolean isCheckSlotsCoverage() {
        log.warn("[CTEST][GET-PARAM] " + "checkSlotsCoverage"); //CTEST
        return checkSlotsCoverage;
    }

    /**
     * Enables cluster slots check during Redisson startup.
     * <p>
     * Default is <code>true</code>
     *
     * @param checkSlotsCoverage - boolean value
     * @return config
     */
    public ClusterServersConfig setCheckSlotsCoverage(boolean checkSlotsCoverage) {
        this.checkSlotsCoverage = checkSlotsCoverage;
        log.warn("[CTEST][SET-PARAM] " + "checkSlotsCoverage" + getStackTrace()); //CTEST
        return this;
    }

    /*
     * Use {@link #setNatMapper(NatMapper)}
     */
    @Deprecated
    public ClusterServersConfig setNatMap(Map<String, String> natMap) {
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
    public ClusterServersConfig setNatMapper(NatMapper natMapper) {
        this.natMapper = natMapper;
        log.warn("[CTEST][SET-PARAM] " + "natMapper" + getStackTrace()); //CTEST
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
