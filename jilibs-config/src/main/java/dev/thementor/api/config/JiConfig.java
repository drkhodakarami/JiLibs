/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package dev.thementor.api.config;

import dev.thementor.api.logger.JiLogger;
import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.ModifiedBy;
import dev.thementor.api.shared.annotations.Repository;

/**
 * Provides a base class for managing configuration settings in Minecraft mods.
 */
@SuppressWarnings("unused")
@Developer("Magistermaks")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/magistermaks/fabric-simplelibs/blob/master/simple-config/SimpleConfig.java")

public abstract class JiConfig
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
    public JiConfig(String modId)
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
        JiLogger logger = new JiLogger(this.modId);

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