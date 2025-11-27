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
 * Contains custom exceptions for the Jibase project.
 */
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class Exceptions
{
    /**
     * Private constructor to prevent instantiation.
     */
    public Exceptions()
    {
        throwCtorAssertion();
    }

    /**
     * Throws an assertion error if the constructor is invoked directly.
     *
     * @throws InitCtorException always, as this method is a singleton and should not be instantiated
     */
    public static void throwCtorAssertion()
    {
        throw new InitCtorException();
    }
}