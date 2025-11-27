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

package dev.thementor.api.shared.constants;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * A class containing constants representing direction suffixes.
 *
 * @author TheMentor
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class DirectionSuffix {

    /**
     * Constant representing the north direction suffix.
     */
    public static final String NORTH = ".north";

    /**
     * Constant representing the south direction suffix.
     */
    public static final String SOUTH = ".south";

    /**
     * Constant representing the west direction suffix.
     */
    public static final String WEST = ".west";

    /**
     * Constant representing the east direction suffix.
     */
    public static final String EAST = ".east";

    /**
     * Constant representing the top direction suffix.
     */
    public static final String TOP = ".top";

    /**
     * Constant representing the bottom direction suffix.
     */
    public static final String BOTTOM = ".bottom";

    /**
     * Constant representing no direction suffix.
     */
    public static final String NO_DIRECTION = ".no.direction";

    // Private constructor to prevent instantiation
    DirectionSuffix() {
        Exceptions.throwCtorAssertion();
    }
}