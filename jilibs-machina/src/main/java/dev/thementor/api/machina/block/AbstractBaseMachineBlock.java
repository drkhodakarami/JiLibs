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

package dev.thementor.api.machina.block;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import dev.thementor.api.fluid.block.AbstractFluidContainerBlock;
import dev.thementor.api.shared.properties.BlockProperties;

@SuppressWarnings("unused")
public class AbstractBaseMachineBlock extends AbstractFluidContainerBlock
{
    public AbstractBaseMachineBlock(Properties properties, BlockProperties<?> blockProperty)
    {
        super(properties, blockProperty
                .addHorizontalFacing()
                .addGui()
                .facingOpposite()
                .addComparatorOutput()
                .addInventory()
                .addLitProperty()
                .addLockedProperty()
                .addPoweredProperty()
                .addEnabledProperty()
                .addUnstableProperty()
                .tick());

        registerDefaultState(defaultBlockState()
                                .setValue(BlockStateProperties.LIT, false)
                                .setValue(BlockStateProperties.LOCKED, false)
                                .setValue(BlockStateProperties.POWERED, false)
                                .setValue(BlockStateProperties.UNSTABLE, false)
                                .setValue(BlockStateProperties.ENABLED, true));
    }
}