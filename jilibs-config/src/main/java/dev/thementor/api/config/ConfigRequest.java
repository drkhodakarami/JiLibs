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

import java.io.File;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.ModifiedBy;
import dev.thementor.api.shared.annotations.Repository;

/**
 * Represents a configuration request for a mod.
 */
@Developer("Magistermaks")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/magistermaks/fabric-simplelibs/blob/master/simple-config/SimpleConfig.java")

public class ConfigRequest
{
    /**
     * The file object representing the configuration file.
     */
    private final File file;

    /**
     * The filename of the configuration file.
     */
    private final String filename;

    /**
     * The IConfigProvider instance for handling the configuration data.
     */
    private IConfigProvider provider;

    /**
     * Constructs a new ConfigRequest with the specified file and filename.
     *
     * @param file The file object representing the configuration file.
     * @param filename The filename of the configuration file.
     */
    public ConfigRequest(File file, String filename)
    {
        this.file = file;
        this.filename = filename;
        this.provider = IConfigProvider::empty;
    }

    /**
     * Retrieves the file object representing the configuration file.
     *
     * @return The file object representing the configuration file.
     */
    public File getFile()
    {
        return this.file;
    }

    /**
     * Retrieves the filename of the configuration file.
     *
     * @return The filename of the configuration file.
     */
    public String getFilename()
    {
        return this.filename;
    }

    /**
     * Sets the IConfigProvider instance for handling the configuration data.
     *
     * @param provider The IConfigProvider instance to use.
     * @return This ConfigRequest object.
     */
    public ConfigRequest provider(IConfigProvider provider)
    {
        this.provider = provider;
        return this;
    }

    /**
     * Creates a new BaseConfig instance with the specified mod ID and casing type.
     *
     * @param modId The unique identifier for the mod.
     * @param casing The case type for handling keys in the configuration file.
     * @return A new BaseConfig instance.
     */
    public BaseConfig request(String modId, ConfigKeyCasing casing)
    {
        return new BaseConfig(modId, this, casing);
    }

    /**
     * Retrieves the configuration content from the IConfigProvider.
     *
     * @return The configuration content.
     */
    public String getConfig()
    {
        return provider.get(filename) + "\n";
    }
}