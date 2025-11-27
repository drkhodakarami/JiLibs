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

package dev.thementor.api.shared.annotations;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * Annotation to credit contributors and sources of inspiration.
 *
 * @author TheMentor
 * @since 2025-04-18
 * @see <a href="https://github.com/drkhodakarami/">GitHub Repository</a>
 * @see <a href="https://discord.gg/pmM4emCbuH">Discord Server</a>
 * @see <a href="https://www.youtube.com/@TheMentorCodeLab">YouTube Channel</a>
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@Documented
@Target(value = {TYPE, METHOD, FIELD, CONSTRUCTOR})
public @interface ThanksTo
{
    /**
     * Array of Discord usernames to credit.
     */
    String[] discordUsers() default {};

    /**
     * Array of Minecraft player names to credit.
     */
    String[] mcPlayers() default {};

    /**
     * Array of GitHub usernames to credit.
     */
    String[] githubUsers() default {};

    /**
     * Array of YouTube channel usernames or channel IDs to credit.
     */
    String[] youtubeUsers() default {};
}