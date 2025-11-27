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

package dev.thementor.api.shared.enumerations;

import dev.thementor.api.shared.annotations.*;

/**
 * Enum representing different statuses of a tick operation.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public enum TickStatus
{
    /**
     * Indicates that the tick operation was successful.
     */
    SUCCESS,

    /**
     * Indicates that the tick operation failed.
     */
    FAILURE,

    /**
     * Indicates that the tick operation should continue to the next step.
     */
    CONTINUE,

    /**
     * Indicates that the tick operation should stop.
     */
    STOP,

    /**
     * Indicates that the tick operation is currently running.
     */
    RUNNING,

    /**
     * Indicates that an error occurred during the tick operation.
     */
    ERROR,

    /**
     * Indicates that there is no specific status to report.
     */
    NONE
}