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

package dev.thementor.api.logger;

import static dev.thementor.api.shared.constants.LoggerConstants.RESET;

import org.slf4j.LoggerFactory;

import net.fabricmc.loader.api.FabricLoader;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.constants.LoggerConstants;

/**
 * Provides a logging utility for mod development in Minecraft using SLF4J.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class Logger
{
    /**
     * The SLF4J logger instance.
     */
    private final org.slf4j.Logger logger;

    /**
     * Indicates if the current environment is a development environment.
     */
    private final boolean debug;

    /**
     * Constructs an instance of JiLogger with the specified mod ID.
     *
     * @param modId The unique identifier for the mod.
     */
    public Logger(String modId)
    {
        this.logger = LoggerFactory.getLogger(modId);
        debug = FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    /**
     * Logs a message related to main initialization.
     */
    public void logMain()
    {
        this.logger.info("\u001B[38;2;" + 255 + ";" + 255 + ";" + 0 + ";48;2;" + 255 + ";" + 0 + ";" + 127 + "m>>> Initializing {}", RESET);
    }

    /**
     * Logs a message related to item registration.
     */
    public void logItemInit()
    {
        this.log("Registering Items");
    }

    /**
     * Logs a message related to item group registration.
     */
    public void logItemGroupInit()
    {
        this.log("Registering Item Groups");
    }

    /**
     * Logs a message related to screen renderer registration.
     */
    public void logScreenRendererInit()
    {
        this.logG("Registering Screen Renderers");
    }

    /**
     * Logs a message related to adding items to item groups.
     */
    public void logItemToGroupAdd()
    {
        this.log("Adding Items to Item Groups");
    }

    /**
     * Logs a message related to block registration.
     */
    public void logBlockInit()
    {
        this.log("Registering Blocks");
    }

    /**
     * Logs a message related to adding blocks to item groups.
     */
    public void logBlockToGroupAdd()
    {
        this.log("Adding Blocks to Item Groups");
    }

    /**
     * Logs a message related to block item registration.
     */
    public void logBlockItemInit()
    {
        this.log("Registering Block Items");
    }

    /**
     * Logs a message related to game rule registration.
     */
    public void logGameRuleInit()
    {
        this.log("Registering Game Rules");
    }

    /**
     * Logs a message related to block entity registration.
     */
    public void logBlockEntityInit()
    {
        this.log("Registering Block Entities");
    }

    /**
     * Logs a message related to command registration.
     */
    public void logCommandInit()
    {
        this.log("Registering Commands");
    }

    /**
     * Logs a message related to event registration.
     */
    public void logEventInit()
    {
        this.log("Registering Events");
    }

    /**
     * Logs a message related to C2S (Client-to-Server) message registration.
     */
    public void logC2SInit()
    {
        this.log("Registering C2S Messages");
    }

    /**
     * Logs a message related to S2C (Server-to-Client) message registration.
     */
    public void logS2CInit()
    {
        this.log("Registering S2C Messages");
    }

    /**
     * Logs a message related to sending messages to client player entities.
     */
    public void logMessageSendClient()
    {
        this.log("Sending a message to client player entities.");
    }

    /**
     * Logs a message related to recipe registration.
     */
    public void logRecipeInit()
    {
        this.log("Registering Recipes");
    }

    /**
     * Logs a message related to screen handler registration.
     */
    public void logScreenHandlerInit()
    {
        this.log("Registering Screen Handlers");
    }

    /**
     * Logs a message related to world generation registration.
     */
    public void logWorldGenerationInit()
    {
        this.log("Registering World Generation");
    }

    /**
     * Logs a message related to tree data registration.
     */
    public void logTreeGenerationInit()
    {
        this.log("Registering Tree Data");
    }

    /**
     * Logs a message related to ore data registration.
     */
    public void logOreGenerationInit()
    {
        this.log("Registering Ore Data");
    }

    /**
     * Logs a message related to geode data registration.
     */
    public void logGeodeGenerationInit()
    {
        this.log("Registering Geode Data");
    }

    /**
     * Logs a message related to flower data registration.
     */
    public void logFlowerGenerationInit()
    {
        this.log("Registering Flower Data");
    }

    /**
     * Logs a message related to entity spawn data registration.
     */
    public void logEntitySpawnInit()
    {
        this.log("Registering Entity Spawn Data");
    }

    /**
     * Logs a message related to custom trades registration.
     */
    public void logTradeInit()
    {
        this.log("Registering Custom Trades");
    }

    /**
     * Logs a message related to tool material registration.
     */
    public void logToolMaterialInit()
    {
        this.log("Registering Tool Materials");
    }

    /**
     * Logs a message related to strippables registration.
     */
    public void logStrippablesInit()
    {
        this.log("Registering Strippables");
    }

    /**
     * Logs a message related to potion recipe registration.
     */
    public void logPotionRecipeInit()
    {
        this.log("Registering Potion Recipes");
    }

    /**
     * Logs a message related to custom portal registration.
     */
    public void logPortalInit()
    {
        this.log("Registering Custom Portals");
    }

    /**
     * Logs a message related to oxidizables registration.
     */
    public void logOxidizableInit()
    {
        this.log("Registering Oxidizables");
    }

    /**
     * Logs a message related to model predicate registration.
     */
    public void logModelPredicateInit()
    {
        this.log("Registering Model Predicates");
    }

    /**
     * Logs a message related to fuel registration.
     */
    public void logFuelInit()
    {
        this.log("Registering Fuel");
    }

    /**
     * Logs a message related to flammables registration.
     */
    public void logFlammablesInit()
    {
        this.log("Registering Flammables");
    }

    /**
     * Logs a message related to effect registration.
     */
    public void logEffectInit()
    {
        this.log("Registering Effects");
    }

    /**
     * Logs a message related to custom data component type registration.
     */
    public void logDataComponentInit()
    {
        this.log("Registering custom data component types");
    }

    /**
     * Logs a message related to compostables registration.
     */
    public void logCompostablesInit()
    {
        this.log("Registering Compostables");
    }

    /**
     * Logs a message related to attribute registration.
     */
    public void logAttributeInit()
    {
        this.log("Registering Attributes");
    }

    /**
     * Logs a message related to armor material registration.
     */
    public void logArmorMaterialInit()
    {
        this.log("Registering Armor Materials");
    }

    /**
     * Logs a message related to client side initialization.
     */
    public void logClient()
    {
        this.logger.info("\u001B[38;2;" + 0 + ";" + 255 + ";" + 255 + ";48;2;" + 0 + ";" + 155 + ";" + 55 + "m>>> Initializing client side {}", RESET);
    }

    /**
     * Logs a message related to data generation.
     */
    public void logDatagen()
    {
        this.logger.info("\u001B[38;2;" + 255 + ";" + 255 + ";" + 0 + ";48;2;" + 0 + ";" + 155 + ";" + 55 + "m>>> Generating Data {}", RESET);
    }

    /**
     * Logs a message related to data generation features registration.
     */
    public void logDatagenFeatures()
    {
        this.logG("Registering Data Generation Features");
    }

    /**
     * Logs a message related to recipe data generation.
     */
    public void logRecipeDatagen()
    {
        this.logG("Generating Recipe Data");
    }

    /**
     * Logs a message related to block model data generation.
     */
    public void logBlockModelDatagen()
    {
        this.logG("Generating Block Model Data");
    }

    /**
     * Logs a message related to item model data generation.
     */
    public void logItemModelDatagen()
    {
        this.logG("Generating Item Model Data");
    }

    /**
     * Logs a message related to loot table data generation.
     */
    public void logLootTableDatagen()
    {
        this.logG("Generating Loot Table Data");
    }

    /**
     * Logs a message related to item tag data generation.
     */
    public void logItemTagDatagen()
    {
        this.logG("Generating Item Tag Data");
    }

    /**
     * Logs a message related to entity tag data generation.
     */
    public void logEntityTagDatagen()
    {
        this.logG("Generating Entity Tag Data");
    }

    /**
     * Logs a message related to en_us language data generation.
     */
    public void logEnLanguageDatagen()
    {
        this.logG("Generating en_us Language Data");
    }

    /**
     * Logs a message related to block tag data generation.
     */
    public void logBlockTagDatagen()
    {
        this.logG("Generating Block Tag Data");
    }

    /**
     * Logs a message related to world features data generation.
     */
    public void logWorldGeneratorDatagen()
    {
        this.logG("Generating World Features Data");
    }

    /**
     * Logs a message related to placed features data generation.
     */
    public void logPlacedDatagenBootstrap()
    {
        this.logG("Generating Placed Features Data");
    }

    /**
     * Logs a message related to configured features data generation.
     */
    public void logFeaturesDatagenBootstrap()
    {
        this.logG("Generating Configured Features Data");
    }

    /**
     * Logs a message at the info level with optional color.
     *
     * @param message The message to log.
     */
    public void log(String message)
    {
        if (this.debug)
            log(message, LoggerConstants.Foreground.BRIGHT_MAGENTA);
    }

    /**
     * Logs a message at the info level with red color.
     *
     * @param message The message to log.
     */
    public void logR(String message)
    {
        if (this.debug)
            log(message, 255, 0, 0);
    }

    /**
     * Logs a message at the info level with green color.
     *
     * @param message The message to log.
     */
    public void logG(String message)
    {
        if (this.debug)
            log(message, 0, 255, 0);
    }

    /**
     * Logs a message at the info level with blue color.
     *
     * @param message The message to log.
     */
    public void logB(String message)
    {
        if (this.debug)
            log(message, 0, 0, 255);
    }

    /**
     * Logs a message at the info level with specified foreground and background colors.
     *
     * @param message The message to log.
     * @param foreground The foreground color in RGB format.
     */
    public void log(String message, String foreground)
    {
        this.logger.info("{}>>> {}" + RESET, foreground, message);
    }

    /**
     * Logs a message at the info level with specified foreground and background colors.
     *
     * @param message The message to log.
     * @param foreground The foreground color in RGB format.
     * @param background The background color in RGB format.
     */
    public void log(String message, String foreground, String background)
    {
        this.logger.info("{}{}>>> {}" + RESET, background, foreground, message);
    }

    /**
     * Logs a message at the info level with specified RGB values for the foreground color.
     *
     * @param message The message to log.
     * @param r Red component of the foreground color (0-255).
     * @param g Green component of the foreground color (0-255).
     * @param b Blue component of the foreground color (0-255).
     */
    public void log(String message, int r, int g, int b)
    {
        if (this.debug)
            this.logger.info("\u001B[38;2;{};{};{}m>>> {}" + RESET, r, g, b, message);
    }

    /**
     * Logs a message at the info level with specified RGB values for the foreground and background colors.
     *
     * @param message The message to log.
     * @param rf Red component of the foreground color (0-255).
     * @param gf Green component of the foreground color (0-255).
     * @param bf Blue component of the foreground color (0-255).
     * @param rb Red component of the background color (0-255).
     * @param gb Green component of the background color (0-255).
     * @param bb Blue component of the background color (0-255).
     */
    public void log(String message, int rf, int gf, int bf, int rb, int gb, int bb)
    {
        if (this.debug)
            this.logger.info("\u001B[38;2;{};{};{};48;2;{};{};{}m>>> {} " + RESET, rf, gf, bf, rb, gb, bb, message);
    }

    /**
     * Logs an error message with optional custom color.
     *
     * @param message The error message to log.
     */
    public void logError(String message)
    {
        logError(message, false);
    }

    /**
     * Logs an error message with option to use a custom color or the default color.
     *
     * @param message The error message to log.
     * @param customColor Whether to use a custom color for the error message.
     */
    public void logError(String message, boolean customColor)
    {
        if (customColor)
            logError(message, LoggerConstants.Foreground.BLACK, LoggerConstants.Background.BRIGHT_RED);
        else
        {
            this.logger.error(message);
            this.logger.trace(message);
        }
    }

    /**
     * Logs an error message with specified foreground color.
     *
     * @param message The error message to log.
     * @param foreground The foreground color in RGB format.
     */
    public void logError(String message, String foreground)
    {
        this.logger.error("{}>>> {}" + RESET, foreground, message);
        this.logger.trace(message);
    }

    /**
     * Logs an error message with specified foreground and background colors.
     *
     * @param message The error message to log.
     * @param foreground The foreground color in RGB format.
     * @param background The background color in RGB format.
     */
    public void logError(String message, String foreground, String background)
    {
        this.logger.error("{}{}>>> {}" + RESET, background, foreground, message);
        this.logger.trace(message);
    }

    /**
     * Logs a warning message with optional custom color.
     *
     * @param message The warning message to log.
     */
    public void logWarning(String message)
    {
        logWarning(message, false);
    }

    /**
     * Logs a warning message with option to use a custom color or the default color.
     *
     * @param message The warning message to log.
     * @param customColor Whether to use a custom color for the warning message.
     */
    public void logWarning(String message, boolean customColor)
    {
        if (this.debug)
        {
            if (customColor)
                logWarning(message, LoggerConstants.Foreground.BLACK, LoggerConstants.Background.BRIGHT_YELLOW);
            else
                this.logger.warn(message);
        }
    }

    /**
     * Logs a warning message with specified foreground color.
     *
     * @param message The warning message to log.
     * @param foreground The foreground color in RGB format.
     */
    public void logWarning(String message, String foreground)
    {
        if (this.debug)
            this.logger.warn("{}>>> {}" + RESET, foreground, message);
    }

    /**
     * Logs a warning message with specified foreground and background colors.
     *
     * @param message The warning message to log.
     * @param foreground The foreground color in RGB format.
     * @param background The background color in RGB format.
     */
    public void logWarning(String message, String foreground, String background)
    {
        if (this.debug)
            this.logger.warn("{}{}>>> {}" + RESET, background, foreground, message);
    }

    /**
     * Logs a normal informational message with optional custom color.
     *
     * @param message The message to log.
     */
    public void logN(String message)
    {
        if (this.debug)
            this.logger.info(">>> {}", message);
    }
}