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
import dev.thementor.api.shared.annotations.Repository;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Provides a base configuration management system for Minecraft mods using Fabric API.
 */
@SuppressWarnings("unused")
@Developer("Magistermaks")
@CreatedAt("2025-04-18")
@Repository("https://github.com/magistermaks/fabric-simplelibs/blob/master/simple-config/SimpleConfig.java")

public class BaseConfig
{
    /**
     * The configuration map to store key-value pairs.
     */
    private final HashMap<String, String> config = new HashMap<>();

    /**
     * The request object containing file and other details for the configuration.
     */
    private final ConfigRequest request;

    /**
     * Indicates whether the configuration is broken.
     */
    private boolean broken = false;

    /**
     * Logger instance for logging messages.
     */
    private static JiLogger LOGGER;

    /**
     * Case type for handling keys in the configuration file.
     */
    private final ConfigKeyCasing casing;

    /**
     * Case type for handling keys in the configuration file.
     */
    private boolean exists;

    /**
     * Constructs an instance of BaseConfig with the specified mod ID, request object, and key casing.
     *
     * @param modid The unique identifier for the mod.
     * @param request The ConfigRequest object containing configuration details.
     * @param casing The case type for handling keys in the configuration file.
     */
    public BaseConfig(String modid, ConfigRequest request, ConfigKeyCasing casing)
    {
        LOGGER = new JiLogger(modid);
        this.casing = casing;
        this.request = request;
        this.exists = true;
        String identifier = "Config '" + request.getFilename() + "'";

        if (!request.getFile().exists())
        {
            LOGGER.logWarning(identifier + " is missing, generating default one...");

            try
            {
                createConfig();
            }
            catch (IOException e)
            {
                LOGGER.logError(identifier + " failed to generate! " + e.getMessage());
                broken = true;
            }
        }

        if (!broken)
        {
            try
            {
                loadConfig();
            }
            catch (Exception e)
            {
                LOGGER.logError(identifier + " failed to load! " + e.getMessage());
                broken = true;
            }
        }

    }

    /**
     * Creates a ConfigRequest object for the specified mod ID and filename.
     *
     * @param modid The unique identifier for the mod.
     * @param filename The name of the configuration file without extension.
     * @return A new ConfigRequest object.
     */
    public static ConfigRequest of(String modid, String filename)
    {
        Path path = FabricLoader.getInstance().getConfigDir();
        return new ConfigRequest(path.resolve(modid + filename + ".ini").toFile(), modid + filename);
    }

    /**
     * Retrieves the value associated with the specified key from the configuration.
     *
     * @param key The key for which to retrieve the value.
     * @return The value associated with the key, or null if not found.
     */
    public String get(String key)
    {
        if(casing == ConfigKeyCasing.ALL_UPPER_CASE)
            return config.get(key.toUpperCase());
        if(casing == ConfigKeyCasing.ALL_LOWER_CASE)
            return config.get(key.toLowerCase());
        return config.get(key);
    }

    /**
     * Retrieves the value associated with the specified key from the configuration, or returns a default value if not found.
     *
     * @param key The key for which to retrieve the value.
     * @param def The default value to return if the key is not found.
     * @return The value associated with the key, or the provided default value.
     */
    public String getOrDefault(String key, String def)
    {
        String val = get(key);
        if (val == null)
        {
            if(this.exists)
                LOGGER.logError("Failed to get the integer config value for " + key + " | Using default value: " + def);
            return def;
        }
        return val;
    }

    /**
     * Retrieves the integer value associated with the specified key from the configuration, or returns a default value if not found.
     *
     * @param key The key for which to retrieve the value.
     * @param def The default value to return if the key is not found or parsing fails.
     * @return The integer value associated with the key, or the provided default value.
     */
    public int getOrDefault(String key, int def)
    {
        try
        {
            return Integer.parseInt(get(key));
        }
        catch (Exception e)
        {
            if(this.exists)
                LOGGER.logError("Failed to get the integer config value for " + key + " | Using default value: " + def + ". " + e.getMessage());

            return def;
        }
    }

    /**
     * Retrieves the boolean value associated with the specified key from the configuration, or returns a default value if not found.
     *
     * @param key The key for which to retrieve the value.
     * @param def The default value to return if the key is not found or parsing fails.
     * @return The boolean value associated with the key, or the provided default value.
     */
    public boolean getOrDefault(String key, boolean def)
    {
        String val = get(key);

        if (val != null)
        {
            return val.equalsIgnoreCase("true");
        }

        if(this.exists)
            LOGGER.logError("Failed to get the double config value for " + key + " | Using default value: " + def);

        return def;
    }

    /**
     * Retrieves the double value associated with the specified key from the configuration, or returns a default value if not found.
     *
     * @param key The key for which to retrieve the value.
     * @param def The default value to return if the key is not found or parsing fails.
     * @return The double value associated with the key, or the provided default value.
     */
    public double getOrDefault(String key, double def)
    {
        try
        {
            return Double.parseDouble(get(key));
        }
        catch (Exception e)
        {
            if(this.exists)
                LOGGER.logError("Failed to get the double config value for " + key + " | Using default value: " + def + ". " + e.getMessage());

            return def;
        }
    }

    /**
     * Creates a new configuration file with default values.
     *
     * @throws IOException If an error occurs while creating the file or writing to it.
     */
    private void createConfig() throws IOException
    {
        this.exists = false;
        //noinspection ResultOfMethodCallIgnored
        request.getFile().getParentFile().mkdirs();
        Files.createFile(request.getFile().toPath());
        PrintWriter writer = new PrintWriter(request.getFile(), StandardCharsets.UTF_8);
        writer.write(request.getConfig());
        writer.close();
    }

    /**
     * Loads the configuration from an existing file.
     *
     * @throws IOException If an error occurs while reading the file.
     */
    private void loadConfig() throws IOException
    {
        Scanner reader = new Scanner(request.getFile());
        for (int line = 1; reader.hasNextLine(); line++)
            parseConfigEntry(reader.nextLine(), line);
        this.exists = true;
    }

    /**
     * Parses a single configuration entry from the file.
     *
     * @param entry The configuration entry to parse.
     * @param line The line number of the entry in the file.
     */
    private void parseConfigEntry(String entry, int line)
    {
        if (!entry.isEmpty() && !entry.startsWith("#"))
        {
            String[] parts = entry.replace(" ", "").split("=", 2); // Modification by Jiraiyah
            if (parts.length == 2)
                config.put(parts[0], parts[1].split("#")[0]); // Modification by Kaupenjoe
            else
                throw new RuntimeException("Syntax error in config file on line " + line + "!");
        }
    }

    /**
     * Checks if the configuration is broken.
     *
     * @return true if the configuration is broken, false otherwise.
     */
    public boolean isBroken()
    {
        return broken;
    }

    /**
     * Deletes the configuration file and logs a warning message.
     *
     * @return true if the file was successfully deleted, false otherwise.
     */
    public boolean delete()
    {
        LOGGER.logWarning("Config '" + request.getFilename() + "' was removed from existence! Restart the game to regenerate it.");
        return request.getFile().delete();
    }
}