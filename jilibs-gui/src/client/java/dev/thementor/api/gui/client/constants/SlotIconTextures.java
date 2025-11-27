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

package dev.thementor.api.gui.client.constants;

import net.minecraft.resources.ResourceLocation;

import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public class SlotIconTextures
{
    public static class Icon
    {
        public static final TextureData BACK =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/back_slot.png"));
        public static final TextureData BELT =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/belt_slot.png"));
        public static final TextureData BODY =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/body_slot.png"));
        public static final TextureData BRACELET =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/bracelet_slot.png"));
        public static final TextureData BUTTLE =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/buttle_background.png"));
        public static final TextureData CHARM =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/charm_slot.png"));
        public static final TextureData COSMETIC =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/cosmetic_slot.png"));
        public static final TextureData CURIO =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/curio_slot.png"));
        public static final TextureData DUST =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/dust_background.png"));
        public static final TextureData ENERGY =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/energy.png"));
        public static final TextureData FLUID =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/fluids.png"));
        public static final TextureData GAS =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/gases.png"));
        public static final TextureData HANDS =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/hands_slot.png"));
        public static final TextureData HEAD =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/head_slot.png"));
        public static final TextureData HEAT =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/heat.png"));
        public static final TextureData ITEM =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/items.png"));
        public static final TextureData NECKLACE =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/necklace_slot.png"));
        public static final TextureData POWDER =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/powder_background.png"));
        public static final TextureData RING =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/ring_slot.png"));
        public static final TextureData STAT =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/stats.png"));
        public static final TextureData TRASH_CAN =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/trash_can.png"));
        public static final TextureData UPGRADE =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/upgrade.png"));

        public static final TextureData BUCKET =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/bucket.png"));
    }

    public static class SlotType
    {
        public static final TextureData AIR =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/air_slot.png"));
        public static final TextureData CROSS =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/delete_slot.png"));
        public static final TextureData DISABLED =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/disabled_slot.png"));
        public static final TextureData BUCKET =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot_bucket.png"));
        public static final TextureData ENERGY_UPGRADE =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot_energy_upgrade.png"));
        public static final TextureData FLUID_UPGRADE =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot_fluid_upgrade.png"));
        public static final TextureData SPEED_UPGRADE =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot_speed_upgrade.png"));
        public static final TextureData TOXIC =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/toxic_slot.png"));
        public static final TextureData TRASH =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/trash_slot.png"));
        public static final TextureData TRASH_RED =
                new TextureData(0, 0, 0, 0, 18, 18, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/trash_slot_red.png"));
    }

    public static class Slot
    {
        public static final TextureData EMPTY_NORMAL =
                new TextureData(0, 0, 0, 0, 16, 16, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot.png"));
        public static final TextureData EMPTY_HIGHLIGHT_BACK =
                new TextureData(0, 0, 0, 0, 24, 24, 24, 24,
                                ResourceLocation.withDefaultNamespace("textures/gui/sprites/container/slot_highlight_back.png"));
        public static final TextureData EMPTY_HIGHLIGHT_FRONT =
                new TextureData(0, 0, 0, 0, 24, 24, 24, 24,
                                ResourceLocation.withDefaultNamespace("textures/gui/sprites/container/slot_highlight_front.png"));
        public static final TextureData EMPTY_BIG =
                new TextureData(0, 0, 0, 0, 26, 26, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/slot_large.png"));
    }
}