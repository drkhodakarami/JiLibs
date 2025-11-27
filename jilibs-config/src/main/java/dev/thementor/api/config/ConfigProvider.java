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

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.ModifiedBy;

/**
 * Provides a provider for managing configuration data.
 */
@Developer("Kaupenjoe")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")

@SuppressWarnings("unused")
public class ConfigProvider implements IConfigProvider
{
    /**
     * The content of the configuration.
     */
    private String configContent;

    /**
     * The list of configuration pairs.
     */
    private final List<Pair<String, ?>> configsList;

    /**
     * The casing type for handling keys in the configuration file.
     */
    private final ConfigKeyCasing casing;

    /**
     * Constructs a new ConfigProvider with default settings (NO_CHANGE casing).
     */
    public ConfigProvider()
    {
        this(ConfigKeyCasing.NO_CHANGE);
    }

    /**
     * Constructs a new ConfigProvider with the specified casing type.
     *
     * @param casing The case type for handling keys in the configuration file.
     */
    public ConfigProvider(ConfigKeyCasing casing)
    {
        this.configContent = "";
        this.configsList = new ArrayList<>();
        this.casing = casing;
    }

    /**
     * Retrieves the list of configuration pairs.
     *
     * @return The list of configuration pairs.
     */
    public List<Pair<String, ?>> getConfigList()
    {
        return configsList;
    }

    /**
     * Returns the content of the configuration.
     *
     * @param namespace Not used in this implementation.
     * @return The content of the configuration.
     */
    @Override
    public String get(String namespace)
    {
        return this.configContent;
    }

    /**
     * Adds a key-value pair with a comment and option to add a new line to the configuration.
     *
     * @param pair The key-value pair to add.
     * @param comment The comment for the configuration key.
     */
    public void addPair(Pair<String, ?> pair, String comment)
    {
        addPair(pair, comment, true, false);
    }

    /**
     * Adds a key-value pair with option to add a new line to the configuration.
     *
     * @param pair The key-value pair to add.
     */
    public void addPair(Pair<String, ?> pair)
    {
        addPair(pair, true, false);
    }

    /**
     * Adds a key-value pair with a comment and option to add a new line to the configuration.
     *
     * @param pair The key-value pair to add.
     * @param comment The comment for the configuration key.
     * @param addNewLine Whether to add a new line after the pair.
     */
    public void addPair(Pair<String, ?> pair, String comment, boolean addNewLine)
    {
        addPair(pair, comment, addNewLine, false);
    }

    /**
     * Adds a key-value pair with option to add a new line to the configuration.
     *
     * @param pair The key-value pair to add.
     * @param addNewLine Whether to add a new line after the pair.
     */
    public void addPair(Pair<String, ?> pair, boolean addNewLine)
    {
        addPair(pair, addNewLine, false);
    }

    /**
     * Adds a key-value pair with a comment, option to add a new line, and option to indicate if it's the last entry.
     *
     * @param pair The key-value pair to add.
     * @param comment The comment for the configuration key.
     * @param addNewLine Whether to add a new line after the pair.
     * @param isLast Whether this is the last entry in the list.
     */
    public void addPair(Pair<String, ?> pair, String comment, boolean addNewLine, boolean isLast)
    {
        configsList.add(pair);
        configContent += "#The default value is: " + pair.getSecond() + " | " + pair.getSecond().getClass().getSimpleName() + "\n";
        configContent +=
                (this.casing == ConfigKeyCasing.NO_CHANGE
                 ? pair.getFirst()
                 : this.casing == ConfigKeyCasing.ALL_UPPER_CASE
                   ? pair.getFirst().toUpperCase()
                   : pair.getFirst().toLowerCase())
                + " = " + pair.getSecond() + " #" + comment;
        if (addNewLine)
            configContent += "\n\n";
        else if (!isLast)
            configContent += "\n";
    }

    /**
     * Adds a key-value pair with option to add a new line and option to indicate if it's the last entry.
     *
     * @param pair The key-value pair to add.
     * @param addNewLine Whether to add a new line after the pair.
     * @param isLast Whether this is the last entry in the list.
     */
    public void addPair(Pair<String, ?> pair, boolean addNewLine, boolean isLast)
    {
        configsList.add(pair);
        configContent += "#The default value is: " + pair.getSecond() + " | " + pair.getSecond().getClass().getSimpleName() + "\n";
        configContent +=
                (this.casing == ConfigKeyCasing.NO_CHANGE
                 ? pair.getFirst()
                 : this.casing == ConfigKeyCasing.ALL_UPPER_CASE
                   ? pair.getFirst().toUpperCase()
                   : pair.getFirst().toLowerCase())
                + " = " + pair.getSecond();
        if (addNewLine)
            configContent += "\n\n";
        else if (!isLast)
            configContent += "\n";
    }

    /**
     * Adds a comment to the configuration.
     *
     * @param comment The comment to add.
     */
    public void addComment(String comment)
    {
        configContent += "#" + comment + "\n";
    }

    /**
     * Adds an empty line to the configuration.
     */
    public void addNewLine()
    {
        configContent += "\n";
    }
}