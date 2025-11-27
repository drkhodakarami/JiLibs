/*
 * Copyright (c) 2025 Alireza Khodakarami
 *
 * Licensed under the MIT, (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/license/mit
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.thementor.api.config;

import dev.thementor.api.logger.Logger;
import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.ModifiedBy;
import dev.thementor.api.shared.annotations.Repository;

/**
 * Provides a base class for managing configuration settings in Minecraft mods.
 */
@SuppressWarnings("unused")
@Developer("Magistermaks")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/magistermaks/fabric-simplelibs/blob/master/simple-config/SimpleConfig.java")

public abstract class ServerConfig
{
    /**
     * The mod ID.
     */
    private final String modId;

    /**
     * The BaseConfig instance for managing configuration data.
     */
    protected BaseConfig config;

    /**
     * The ConfigProvider instance for handling the configuration data.
     */
    protected ConfigProvider provider;

    /**
     * Constructs a new JiConfig with the specified mod ID.
     *
     * @param modId The unique identifier for the mod.
     */
    public ServerConfig(String modId)
    {
        this.modId = modId;
    }

    /**
     * Retrieves the BaseConfig instance for managing configuration data.
     *
     * @return The BaseConfig instance.
     */
    public BaseConfig getConfig()
    {
        return this.config;
    }

    /**
     * Loads the configuration using default casing (ALL_UPPER_CASE).
     */
    public void load()
    {
        load(ConfigKeyCasing.ALL_UPPER_CASE);
    }

    /**
     * Loads the configuration with a specified casing type.
     *
     * @param casing The case type for handling keys in the configuration file.
     */
    public void load(ConfigKeyCasing casing)
    {
        Logger logger = new Logger(this.modId);

        this.provider = new ConfigProvider(casing);
        this.createConfigs();

        logger.log("All " + provider.getConfigList().size() + " config entries have been set properly");

        this.config = BaseConfig.of(this.modId, "_config")
                                .provider(provider)
                                .request(this.modId, casing);

        this.assignConfigValues();

        if(!config.isBroken())
            logger.log("All " + provider.getConfigList().size() + " config entries have been loaded properly");
        else
            logger.logWarning("The loading process is at broken state!");
    }

    /**
     * Subclasses should implement this method to create configuration pairs.
     */
    protected void createConfigs()
    {
    }

    /**
     * Subclasses should implement this method to assign values to configuration keys.
     */
    protected void assignConfigValues()
    {
    }
}