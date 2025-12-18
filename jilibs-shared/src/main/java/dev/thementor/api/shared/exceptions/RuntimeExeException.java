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

package dev.thementor.api.shared.exceptions;

import dev.thementor.api.shared.annotations.*;

/**
 * This exception class is used to prevent instantiation of the {@link RuntimeException} class.
 * <p>
 * The purpose of this exception is to ensure that the {@link RuntimeException} class should not be instantiated, which can lead to unexpected behavior or security issues if it were allowed.
 * </p>
 *
 * @author Alireza Khodakarami (TheMentor)
 * @version 1.0
 * @since 2025-04-18
 */
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class RuntimeExeException extends RuntimeException
{
    /**
     * The default message thrown by the {@link RuntimeException}.
     */
    public static String RUNTIME_ERROR_TEXT = "Error executing code!";

    /**
     * Constructor that throws the {@link RuntimeException} with a default message.
     */
    public RuntimeExeException()
    {
        super(RUNTIME_ERROR_TEXT);
    }
}