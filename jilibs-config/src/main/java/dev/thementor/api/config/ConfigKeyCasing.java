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

import dev.thementor.api.shared.annotations.*;

/**
 * Enumerates different casing options for configuration keys.
 */
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public enum ConfigKeyCasing
{
    /**
     * No change to the case of configuration keys.
     */
    NO_CHANGE,

    /**
     * Converts configuration keys to all uppercase letters.
     */
    ALL_UPPER_CASE,

    /**
     * Converts configuration keys to all lowercase letters.
     */
    ALL_LOWER_CASE
}