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

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;

/**
 * Provides a contract for handling configuration data.
 */
@Developer("Magistermaks")
@CreatedAt("2025-04-18")
@Repository("https://github.com/magistermaks/fabric-simplelibs/blob/master/simple-config/SimpleConfig.java")

public interface IConfigProvider
{
    /**
     * Retrieves an empty string as the configuration content.
     *
     * @param ignoredNamespace The namespace parameter is not used in this implementation.
     * @return An empty string representing the configuration content.
     */
    static String empty(String ignoredNamespace)
    {
        return "";
    }

    /**
     * Retrieves the configuration content for a given namespace.
     *
     * @param namespace The namespace for which to retrieve the configuration content.
     * @return The configuration content as a string.
     */
    String get(String namespace);
}