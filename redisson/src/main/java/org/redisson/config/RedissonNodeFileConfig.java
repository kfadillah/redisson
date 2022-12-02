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

import org.redisson.api.RedissonNodeInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Redisson Node file configuration
 * 
 * @author Nikita Koksharov
 *
 */
public class RedissonNodeFileConfig extends Config {

    private int mapReduceWorkers = 0;
    private RedissonNodeInitializer redissonNodeInitializer;
    private Map<String, Integer> executorServiceWorkers = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(RedissonNodeFileConfig.class);
    public RedissonNodeFileConfig() {
        super();
    }

    public RedissonNodeFileConfig(Config oldConf) {
        super(oldConf);
    }

    public RedissonNodeFileConfig(RedissonNodeFileConfig oldConf) {
        super(oldConf);
        this.executorServiceWorkers = new HashMap<>(oldConf.executorServiceWorkers);
        this.redissonNodeInitializer = oldConf.redissonNodeInitializer;
        this.mapReduceWorkers = oldConf.mapReduceWorkers;
    }
    
    /**
     * MapReduce workers amount. 
     * <p>
     * <code>0 = current_processors_amount</code>
     * <p>
     * <code>-1 = disable MapReduce workers</code>
     * 
     * <p>
     * Default is <code>0</code>
     * 
     * @param mapReduceWorkers workers for MapReduce
     * @return config
     */
    public RedissonNodeFileConfig setMapReduceWorkers(int mapReduceWorkers) {
        this.mapReduceWorkers = mapReduceWorkers;
        log.warn("[CTEST][SET-PARAM] " + "mapReduceWorkers" + getStackTrace()); //CTEST
        return this;
    }
    public int getMapReduceWorkers() {
        log.warn("[CTEST][GET-PARAM] " + "mapReduceWorkers"); //CTEST
        return mapReduceWorkers;
    }
    
    /**
     * Executor service workers amount per service name 
     * 
     * @param workers mapping
     * @return config
     */
    public RedissonNodeFileConfig setExecutorServiceWorkers(Map<String, Integer> workers) {
        this.executorServiceWorkers = workers;
        log.warn("[CTEST][SET-PARAM] " + "executorServiceWorkers" + getStackTrace()); //CTEST
        return this;
    }
    public Map<String, Integer> getExecutorServiceWorkers() {
        log.warn("[CTEST][GET-PARAM] " + "executorServiceWorkers"); //CTEST
        return executorServiceWorkers;
    }
    
    /**
     * Redisson node initializer
     * 
     * @param redissonNodeInitializer object
     * @return config
     */
    public RedissonNodeFileConfig setRedissonNodeInitializer(RedissonNodeInitializer redissonNodeInitializer) {
        this.redissonNodeInitializer = redissonNodeInitializer;
        log.warn("[CTEST][SET-PARAM] " + "redissonNodeInitializer" + getStackTrace()); //CTEST
        return this;
    }
    public RedissonNodeInitializer getRedissonNodeInitializer() {
        log.warn("[CTEST][GET-PARAM] " + "redissonNodeInitializer"); //CTEST
        return redissonNodeInitializer;
    }

    /**
     * Read config object stored in JSON format from <code>File</code>
     *
     * @param file object
     * @return config
     * @throws IOException error
     */
    public static RedissonNodeFileConfig fromJSON(File file) throws IOException {
        ConfigSupport support = new ConfigSupport();
        return support.fromJSON(file, RedissonNodeFileConfig.class);
    }

    /**
     * Read config object stored in YAML format from <code>File</code>
     *
     * @param file object
     * @return config
     * @throws IOException error
     */
    public static RedissonNodeFileConfig fromYAML(File file) throws IOException {
        ConfigSupport support = new ConfigSupport();
        return support.fromYAML(file, RedissonNodeFileConfig.class);
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
