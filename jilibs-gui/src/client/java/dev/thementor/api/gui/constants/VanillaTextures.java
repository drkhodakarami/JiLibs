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

package dev.thementor.api.gui.constants;

import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public class VanillaTextures
{
    public static class BossBar
    {
        public static class Background
        {
            public static final TextureData BLUE =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/blue_background.png"));
            public static final TextureData GREEN =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/green_background.png"));
            public static final TextureData NOTCH_6 =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_6_background.png"));
            public static final TextureData NOTCH_10 =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_10_background.png"));
            public static final TextureData NOTCH_12 =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_12_background.png"));
            public static final TextureData NOTCH_20 =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_20_background.png"));
            public static final TextureData PINK =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/pink_background.png"));
            public static final TextureData PURPLE =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/purple_background.png"));
            public static final TextureData RED =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/red_background.png"));
            public static final TextureData WHITE =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/white_background.png"));
            public static final TextureData YELLOW =
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/yellow_background.png"));
        }
        
        public static class Progress
        {
            public static final TextureData BLUE = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/blue_progress.png"));
            public static final TextureData GREEN = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/green_progress.png"));
            public static final TextureData NOTCH_6 = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_6_progress.png"));
            public static final TextureData NOTCH_10 = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_10_progress.png"));
            public static final TextureData NOTCH_12 = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_12_progress.png"));
            public static final TextureData NOTCH_20 = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/notched_20_progress.png"));
            public static final TextureData PINK = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/pink_progress.png"));
            public static final TextureData PURPLE = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/purple_progress.png"));
            public static final TextureData RED = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/red_progress.png"));
            public static final TextureData WHITE = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/white_progress.png"));
            public static final TextureData YELLOW = 
                    new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                    Identifier.ofVanilla("textures/gui/sprites/boss_bar/yellow_progress.png"));
        }
    }

    public static class Buttons
    {
        public static class Book
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 20, 18, 20, 18,
                                    Identifier.ofVanilla("textures/gui/sprites/recipe_book/button.png"));
            public static final TextureData HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 20, 18, 20, 18,
                                    Identifier.ofVanilla("textures/gui/sprites/recipe_book/button_highlighted.png"));
        }

        public static class Page
        {
            public static class Left
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 1, 0, 11, 17, 12, 17,
                                        Identifier.ofVanilla("textures/gui/sprites/recipe_book/page_backward.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 1, 0, 11, 17, 12, 17,
                                        Identifier.ofVanilla("textures/gui/sprites/recipe_book/page_backward_highlighted.png"));
            }

            public static class Right
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 0, 0, 11, 17, 12, 17,
                                        Identifier.ofVanilla("textures/gui/sprites/recipe_book/page_forward.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 0, 0, 11, 17, 12, 17,
                                        Identifier.ofVanilla("textures/gui/sprites/recipe_book/page_forward_highlighted.png"));
            }

            public static class Backward
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 3, 2, 18, 10, 23, 13,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/page_backward.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 3, 2, 18, 10, 23, 13,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/page_backward_highlighted.png"));
            }

            public static class Forward
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 3, 2, 18, 10, 23, 13,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/page_forward.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 3, 2, 18, 10, 23, 13,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/page_forward_highlighted.png"));
            }
        }

        public static class Move
        {
            public static class Down
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 18, 20, 11, 7, 32, 32,
                                        Identifier.ofVanilla("textures/gui/sprites/transferable_list/move_down.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 18, 20, 11, 7, 32, 32,
                                        Identifier.ofVanilla("textures/gui/sprites/transferable_list/move_down_highlighted.png"));
            }

            public static class Up
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 18, 5, 11, 7, 32, 32,
                                        Identifier.ofVanilla("textures/gui/sprites/transferable_list/move_up.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 18, 5, 11, 7, 32, 32,
                                        Identifier.ofVanilla("textures/gui/sprites/transferable_list/move_up_highlighted.png"));
            }
        }

        public static class Alert
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/social_interactions/report_button.png"));
            public static final TextureData DISABLED =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/social_interactions/report_button_disabled.png"));
            public static final TextureData HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/social_interactions/report_button_highlighted.png"));
        }

        public static class Checkbox
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/checkbox.png"));
            public static final TextureData HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/checkbox_highlighted.png"));
            public static final TextureData SELECTED =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/checkbox_selected.png"));
            public static final TextureData SELECTED_HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/checkbox_selected_highlighted.png"));
        }

        public static class Cross
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 14, 14, 14, 14,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/cross_button.png"));
            public static final TextureData HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 14, 14, 14, 14,
                                    Identifier.ofVanilla("textures/gui/sprites/widget/cross_button_highlighted.png"));
        }

        public static class Lock
        {
            public static class Close
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/locked_button.png"));
                public static final TextureData DISABLED =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/locked_button_disabled.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/locked_button_highlighted.png"));
            }

            public static class Open
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/unlocked_button.png"));
                public static final TextureData DISABLED =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/unlocked_button_disabled.png"));
                public static final TextureData HIGHLIGHTED =
                        new TextureData(0, 0, 0, 0, 20, 20, 20, 20,
                                        Identifier.ofVanilla("textures/gui/sprites/widget/unlocked_button_highlighted.png"));
            }
        }
    }

    public static class Container
    {
        public static class Button
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 22, 22, 22, 22,
                                    Identifier.ofVanilla("textures/gui/sprites/container/beacon/button.png"));
            public static final TextureData DISABLED =
                    new TextureData(0, 0, 0, 0, 22, 22, 22, 22,
                                    Identifier.ofVanilla("textures/gui/sprites/container/beacon/button_disabled.png"));
            public static final TextureData HIGHLIGHTED =
                    new TextureData(0, 0, 0, 0, 22, 22, 22, 22,
                                    Identifier.ofVanilla("textures/gui/sprites/container/beacon/button_highlighted.png"));
            public static final TextureData SELECTED =
                    new TextureData(0, 0, 0, 0, 22, 22, 22, 22,
                                    Identifier.ofVanilla("textures/gui/sprites/container/beacon/button_selected.png"));
        }

        public static class Dot
        {
            public static final TextureData BLUE =
                    new TextureData(0, 0, 0, 0, 6, 6, 6, 6,
                                    Identifier.ofVanilla("textures/gui/sprites/container/bundle/bundle_progressbar_fill.png"));
            public static final TextureData RED =
                    new TextureData(0, 0, 0, 0, 6, 6, 6, 6,
                                    Identifier.ofVanilla("textures/gui/sprites/container/bundle/bundle_progressbar_full.png"));
        }

        public static class RedstoneArrow
        {
            public static final TextureData OFF = new
                    TextureData(0, 0, 0, 3, 16, 11, 16, 16,
                                Identifier.ofVanilla("textures/gui/sprites/container/crafter/unpowered_redstone.png"));
            public static final TextureData ON = new
                    TextureData(0, 0, 0, 3, 16, 11, 16, 16,
                                Identifier.ofVanilla("textures/gui/sprites/container/crafter/powered_redstone.png"));
        }

        public static class Scroller
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 12, 15, 12, 15,
                                    Identifier.ofVanilla("textures/gui/sprites/container/creative_inventory/scroller.png"));
            public static final TextureData DISABLED =
                    new TextureData(0, 0, 0, 0, 12, 15, 12, 15,
                                    Identifier.ofVanilla("textures/gui/sprites/container/creative_inventory/scroller_disabled.png"));
        }

        public static class Xp
        {
            public static class Enabled
            {
                public static final TextureData ONE =
                        new TextureData(0, 0, 3, 3, 12, 9, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_1.png"));
                public static final TextureData TWO =
                        new TextureData(0, -1, 3, 2, 12, 11, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_2.png"));
                public static final TextureData THREE =
                        new TextureData(-1, -1, 2, 2, 13, 11, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_3.png"));
            }

            public static class Disabled
            {
                public static final TextureData ONE =
                        new TextureData(0, 0, 4, 3, 11, 9, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_1_disabled.png"));
                public static final TextureData TWO =
                        new TextureData(0, 0, 4, 3, 11, 9, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_2_disabled.png"));
                public static final TextureData THREE =
                        new TextureData(-1, 0, 3, 3, 12, 9, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/container/enchanting_table/level_3_disabled.png"));
            }
        }

        public static final TextureData CANCEL =
                new TextureData(0, 0, 2, 3, 13, 13, 18, 18,
                                Identifier.ofVanilla("textures/gui/sprites/container/beacon/cancel.png"));
        public static final TextureData CONFIRM =
                new TextureData(0, 0, 1, 4, 14, 12, 18, 18,
                                Identifier.ofVanilla("textures/gui/sprites/container/beacon/confirm.png"));
    }

    public static class Effect
    {
        public static class Background
        {
            public static final TextureData SMALL =
                    new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                    Identifier.ofVanilla("textures/gui/sprites/container/inventory/effect_background_small.png"));
            public static final TextureData LARGE =
                    new TextureData(0, 0, 0, 0, 120, 32, 120, 32,
                                    Identifier.ofVanilla("textures/gui/sprites/container/inventory/effect_background_large.png"));
        }

        public static class Icon
        {
            public static final TextureData ABSORPTION =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/absorption.png"));
            public static final TextureData BAD_OMEN =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/bad_omen.png"));
            public static final TextureData BLINDNESS =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/blindness.png"));
            public static final TextureData CONDUIT_POWER =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/conduit_power.png"));
            public static final TextureData DARKNESS =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/darkness.png"));
            public static final TextureData DOLPHINS_GRACE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/dolphins_grace.png"));
            public static final TextureData FIRE_RESISTANCE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/fire_resistance.png"));
            public static final TextureData GLOWING =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/glowing.png"));
            public static final TextureData HASTE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/haste.png"));
            public static final TextureData HEART_BOOST =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/health_boost.png"));
            public static final TextureData HERO_OF_THE_VILLAGE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/hero_of_the_village.png"));
            public static final TextureData HUNGER =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/hunger.png"));
            public static final TextureData INFESTED =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/infested.png"));
            public static final TextureData INSTANT_DAMAGE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/instant_damage.png"));
            public static final TextureData INSTANT_HEALTH =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/instant_health.png"));
            public static final TextureData INVISIBILITY =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/invisibility.png"));
            public static final TextureData JUMP_BOOST =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/jump_boost.png"));
            public static final TextureData LEVITATION =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/levitation.png"));
            public static final TextureData LUCK =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/luck.png"));
            public static final TextureData MINING_FATIGUE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/mining_fatigue.png"));
            public static final TextureData NAUSEA =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/nausea.png"));
            public static final TextureData NIGHT_VISION =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/night_vision.png"));
            public static final TextureData OOZING =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/oozing.png"));
            public static final TextureData POISON =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/poison.png"));
            public static final TextureData RAID_OMEN =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/raid_omen.png"));
            public static final TextureData REGENERATION =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/regeneration.png"));
            public static final TextureData RESISTANCE =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/resistance.png"));
            public static final TextureData SATURATION =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/saturation.png"));
            public static final TextureData SLOW_FALLING =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/slow_falling.png"));
            public static final TextureData SLOWNESS =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/slowness.png"));
            public static final TextureData SPEED =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/speed.png"));
            public static final TextureData STRENGTH =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/strength.png"));
            public static final TextureData TRIAL_OMEN =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/trial_omen.png"));
            public static final TextureData UNLUCK =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/unluck.png"));
            public static final TextureData WATER_BREATHING =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/water_breathing.png"));
            public static final TextureData WEAKNESS =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/weakness.png"));
            public static final TextureData WEAVING =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/weaving.png"));
            public static final TextureData WIND_CHARGED =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/wind_charged.png"));
            public static final TextureData WITHER =
                    new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                    Identifier.ofVanilla("textures/mob_effect/wither.png"));
        }
    }

    public static class HUD
    {
        public static class Screen
        {
            public static class Air
            {
                public static final TextureData NORMAL =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/air.png"));
                public static final TextureData BURSTING =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/air_bursting.png"));
                public static final TextureData EMPTY =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/air_empty.png"));
            }

            public static class Armor
            {
                public static final TextureData EMPTY = new
                        TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                    Identifier.ofVanilla("textures/gui/sprites/hud/armor_empty.png"));
                public static final TextureData FULL =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/armor_full.png"));
                public static final TextureData HALF =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/armor_half.png"));
            }

            public static class Crosshair
            {
                public static final TextureData ICON =
                        new TextureData(0, 0, 0, 0, 15, 15, 15, 15,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/crosshair.png"));
                public static final TextureData ATTACK_BACKGROUND =
                        new TextureData(0, 0, 0, 0, 16, 4, 16, 4,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/crosshair_attack_indicator_background.png"));
                public static final TextureData ATTACK_FULL =
                        new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/crosshair_attack_indicator_full.png"));
                public static final TextureData ATTACK_PROGRESS =
                        new TextureData(0, 0, 0, 0, 16, 4, 16, 4,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/crosshair_attack_indicator_progress.png"));
            }

            public static class Xp
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/experience_bar_background.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 182, 5, 182, 5,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/experience_bar_progress.png"));
            }

            public static class Food
            {
                public static final TextureData EMPTY =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_empty.png"));
                public static final TextureData EMPTY_HUNGER =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_empty_hunger.png"));
                public static final TextureData FULL =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_full.png"));
                public static final TextureData FULL_HUNGER =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_full_hunger.png"));
                public static final TextureData HALF =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_half.png"));
                public static final TextureData HALF_HUNGER =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/food_half_hunger.png"));
            }

            public static class Hotbar
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 182, 22, 182, 22,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar.png"));
                public static final TextureData ATTACK_BACKGROUND =
                        new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_attack_indicator_background.png"));
                public static final TextureData ATTACK_PROGRESS =
                        new TextureData(0, 0, 0, 0, 18, 18, 18, 18,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_attack_indicator_progress.png"));
                public static final TextureData OFFHAND_LEFT =
                        new TextureData(0, 0, 0, 1, 22, 22, 29, 24,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_offhand_left.png"));
                public static final TextureData OFFHAND_RIGHT =
                        new TextureData(0, 0, 7, 1, 22, 22, 29, 24,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_offhand_right.png"));
                public static final TextureData SELECTION =
                        new TextureData(0, 0, 0, 0, 24, 23, 24, 23,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_selection.png"));
            }
        }

        public static class Heart
        {
            public static class Normal
            {
                public static class Simple
                {
                    public static final TextureData ABSORBING_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_full.png"));
                    public static final TextureData ABSORBING_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_half.png"));
                    public static final TextureData CONTAINER =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/container.png"));
                    public static final TextureData FROZEN_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_full.png"));
                    public static final TextureData FROZEN_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_half.png"));
                    public static final TextureData FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/full.png"));
                    public static final TextureData HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/half.png"));
                    public static final TextureData POISONED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_full.png"));
                    public static final TextureData POISONED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_half.png"));
                    public static final TextureData WITHERED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_full.png"));
                    public static final TextureData WITHERED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_half.png"));
                }

                public static class Blinking
                {
                    public static final TextureData ABSORBING_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_full_blinking.png"));
                    public static final TextureData ABSORBING_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_half_blinking.png"));
                    public static final TextureData CONTAINER =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/container_blinking.png"));
                    public static final TextureData FROZEN_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_full_blinking.png"));
                    public static final TextureData FROZEN_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_half_blinking.png"));
                    public static final TextureData FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/full_blinking.png"));
                    public static final TextureData HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/half_blinking.png"));
                    public static final TextureData POISONED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_full_blinking.png"));
                    public static final TextureData POISONED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_half_blinking.png"));
                    public static final TextureData WITHERED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_full_blinking.png"));
                    public static final TextureData WITHERED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_half_blinking.png"));
                }
            }

            public static class Hardcore
            {
                public static class Simple
                {
                    public static final TextureData ABSORBING_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_hardcore_full.png"));
                    public static final TextureData ABSORBING_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_hardcore_half.png"));
                    public static final TextureData CONTAINER =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/container_hardcore.png"));
                    public static final TextureData FROZEN_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_hardcore_full.png"));
                    public static final TextureData FROZEN_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_hardcore_half.png"));
                    public static final TextureData FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/hardcore_full.png"));
                    public static final TextureData HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/hardcore_half.png"));
                    public static final TextureData POISONED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_hardcore_full.png"));
                    public static final TextureData POISONED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_hardcore_half.png"));
                    public static final TextureData WITHERED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_hardcore_full.png"));
                    public static final TextureData WITHERED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_hardcore_half.png"));
                }
                
                public static class Blinking
                {
                    public static final TextureData ABSORBING_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_hardcore_full_blinking.png"));
                    public static final TextureData ABSORBING_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/absorbing_hardcore_half_blinking.png"));
                    public static final TextureData CONTAINER =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/container_hardcore_blinking.png"));
                    public static final TextureData FROZEN_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_hardcore_full_blinking.png"));
                    public static final TextureData FROZEN_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/frozen_hardcore_half_blinking.png"));
                    public static final TextureData FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/hardcore_full_blinking.png"));
                    public static final TextureData HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/hardcore_half_blinking.png"));
                    public static final TextureData POISONED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_hardcore_full_blinking.png"));
                    public static final TextureData POISONED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/poisoned_hardcore_half_blinking.png"));
                    public static final TextureData WITHERED_FULL =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_hardcore_full_blinking.png"));
                    public static final TextureData WITHERED_HALF =
                            new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                            Identifier.ofVanilla("textures/gui/sprites/hud/heart/withered_hardcore_half_blinking.png"));
                }
            }

            public static class Vehicle
            {
                public static final TextureData VEHICLE_CONTAINER =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/heart/vehicle_container.png"));
                public static final TextureData VEHICLE_FULL =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/heart/vehicle_full.png"));
                public static final TextureData VEHICLE_HALF =
                        new TextureData(0, 0, 0, 0, 9, 9, 9, 9,
                                        Identifier.ofVanilla("textures/gui/sprites/hud/heart/vehicle_half.png"));
            }
        }
    }

    public static class Icon
    {
        public static class Slot
        {
            public static final TextureData AMETHYST_SHART =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/amethyst_shard.png"));
            public static final TextureData AXE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/axe.png"));
            public static final TextureData BANNER =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/banner.png"));
            public static final TextureData BANNER_PATTERN =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/banner_pattern.png"));
            public static final TextureData BOOTS =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/boots.png"));
            public static final TextureData BREWING_FUEL =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/brewing_fuel.png"));
            public static final TextureData CHESTPLATE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/chestplate.png"));
            public static final TextureData DIAMOND =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/diamond.png"));
            public static final TextureData DYE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/dye.png"));
            public static final TextureData EMERALD =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/emerald.png"));
            public static final TextureData HELMET =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/helmet.png"));
            public static final TextureData HOE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/hoe.png"));
            public static final TextureData HORSE_ARMOR =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/horse_armor.png"));
            public static final TextureData INGOT =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/ingot.png"));
            public static final TextureData LAPIS_LAZULI =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/lapis_lazuli.png"));
            public static final TextureData LEGGINGS =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/leggings.png"));
            public static final TextureData LLAMA_ARMOR =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/llama_armor.png"));
            public static final TextureData PICKAXE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/pickaxe.png"));
            public static final TextureData POTION =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/potion.png"));
            public static final TextureData QUARTZ =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/quartz.png"));
            public static final TextureData REDSTONE_DUST =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/redstone_dust.png"));
            public static final TextureData SADDLE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/saddle.png"));
            public static final TextureData SHIELD =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/shield.png"));
            public static final TextureData SHOVEL =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/shovel.png"));
            public static final TextureData SMITHING_TRIM_TEMPLATE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/smithing_template_armor_trim.png"));
            public static final TextureData SMITHING_UPGRADE_TEMPLATE =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/smithing_template_netherite_upgrade.png"));
            public static final TextureData SWORD =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/container/slot/sword.png"));
        }

        public static class Ping
        {
            public static final TextureData PING_1 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_1.png"));
            public static final TextureData PING_2 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_2.png"));
            public static final TextureData PING_3 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_3.png"));
            public static final TextureData PING_4 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_4.png"));
            public static final TextureData PING_5 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_5.png"));
            public static final TextureData PING_UNKNOWN =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/ping_unknown.png"));
            public static final TextureData PINGING_1 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/server_list/pinging_1.png"));
            public static final TextureData PINGING_2 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/server_list/pinging_2.png"));
            public static final TextureData PINGING_3 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/server_list/pinging_3.png"));
            public static final TextureData PINGING_4 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/server_list/pinging_4.png"));
            public static final TextureData PINGING_5 =
                    new TextureData(0, 0, 0, 1, 10, 7, 10, 8,
                                    Identifier.ofVanilla("textures/gui/sprites/server_list/pinging_5.png"));
        }

        public static class Misc
        {
            public static final TextureData SEARCH =
                    new TextureData(0, 0, 0, 0, 12, 12, 12, 12,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/search.png"));
            public static final TextureData UNSEEN_NOTIFICATION =
                    new TextureData(0, 0, 0, 0, 10, 10, 10, 10,
                                    Identifier.ofVanilla("textures/gui/sprites/icon/unseen_notification.png"));

            public static final TextureData DISABLED =
                    new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/spectator/close.png"));
            public static final TextureData SCROLL_LEFT =
                    new TextureData(0, 0, 1, 2, 7, 11, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/spectator/scroll_left.png"));
            public static final TextureData SCROLL_RIGHT =
                    new TextureData(0, 0, 8, 2, 7, 11, 16, 16,
                                    Identifier.ofVanilla("textures/gui/sprites/spectator/scroll_right.png"));

            public static final TextureData ERROR =
                    new TextureData(0, 0, 6, 5, 6, 22, 32, 32,
                                    Identifier.ofVanilla("textures/gui/sprites/world_list/error.png"));
            public static final TextureData ERROR_HIGHLIGHTED_RED =
                    new TextureData(0, 0, 6, 5, 6, 22, 32, 32,
                                    Identifier.ofVanilla("textures/gui/sprites/world_list/error_highlighted.png"));
            public static final TextureData ERROR_HIGHLIGHTED_ORANGE =
                    new TextureData(0, 0, 6, 5, 6, 22, 32, 32,
                                    Identifier.ofVanilla("textures/gui/sprites/world_list/warning_highlighted.png"));
        }
    }

    public static class Marker
    {
        public static class Banner
        {
            public static final TextureData BLACK =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/black_banner.png"));
            public static final TextureData BLUE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/blue_banner.png"));
            public static final TextureData BROWN =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/brown_banner.png"));
            public static final TextureData CYAN =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/cyan_banner.png"));
            public static final TextureData GRAY =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/gray_banner.png"));
            public static final TextureData GREEN =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/green_banner.png"));
            public static final TextureData LIGHT_BLUE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/light_blue_banner.png"));
            public static final TextureData LIGHT_GRAY =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/light_gray_banner.png"));
            public static final TextureData LIME =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/lime_banner.png"));
            public static final TextureData MAGENTA =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/magenta_banner.png"));
            public static final TextureData ORANGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/orange_banner.png"));
            public static final TextureData PINK =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/pink_banner.png"));
            public static final TextureData PURPLE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/purple_banner.png"));
            public static final TextureData RED =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/red_banner.png"));
            public static final TextureData WHITE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/white_banner.png"));
            public static final TextureData YELLOW =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/yellow_banner.png"));
        }

        public static class Target
        {
            public static final TextureData BLUE_MARKER =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/blue_marker.png"));
            public static final TextureData RED_MARKER =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/red_marker.png"));
            public static final TextureData FRAME =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/frame.png"));
            public static final TextureData RED_X =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/red_x.png"));
            public static final TextureData TARGET_POINT =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/target_point.png"));
            public static final TextureData TARGET_X =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/target_x.png"));
        }

        public static class Building
        {
            public static final TextureData DESERT_VILLAGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/desert_village.png"));
            public static final TextureData JUNGLE_TEMPLE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/jungle_temple.png"));
            public static final TextureData OCEAN_MONUMENT =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/ocean_monument.png"));
            public static final TextureData PLAINS_VILLAGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/plains_village.png"));
            public static final TextureData SAVANNA_VILLAGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/savanna_village.png"));
            public static final TextureData SNOWY_VILLAGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/snowy_village.png"));
            public static final TextureData SWAMP_HUT =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/swamp_hut.png"));
            public static final TextureData TAIGA_VILLAGE =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/taiga_village.png"));
            public static final TextureData TRIAL_CHAMBER =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/trial_chambers.png"));
            public static final TextureData WOODLAND_MANSION =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/woodland_mansion.png"));
        }

        public static class Player
        {
            public static final TextureData PLAYER =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/player.png"));
            public static final TextureData PLAYER_OFF_LIMITS =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/player_off_limits.png"));
            public static final TextureData PLAYER_OFF_MAP =
                    new TextureData(0, 0, 0, 0, 8, 8, 8, 8,
                                    Identifier.ofVanilla("textures/map/decorations/player_off_map.png"));
        }
    }
}