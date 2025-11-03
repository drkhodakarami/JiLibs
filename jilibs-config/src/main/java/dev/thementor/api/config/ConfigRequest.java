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

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.ModifiedBy;
import dev.thementor.api.shared.annotations.Repository;

import java.io.File;

/**
 * Represents a configuration request for a mod.
 */
@Developer("Magistermaks")
@ModifiedBy("Jiraiyah")
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