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

package dev.thementor.api.fluid.block;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import dev.thementor.api.base.block.AbstractBaseBlock;
import dev.thementor.api.fluid.utils.FluidHelper;
import dev.thementor.api.shared.interfaces.IWrench;
import dev.thementor.api.shared.properties.BlockProperties;

@SuppressWarnings("unused")
public class AbstractFluidContainerBlock extends AbstractBaseBlock
{
    public AbstractFluidContainerBlock(Properties properties, BlockProperties<?> blockProperties)
    {
        super(properties, blockProperties);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if(FluidHelper.interactWithBlock(level, pos, player, hand))
            return InteractionResult.SUCCESS;

        if(stack.getItem() instanceof IWrench wrench &&
           player.isCrouching() &&
           wrench.breakBlock(level, pos, state, player, stack))
                return InteractionResult.SUCCESS;

        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }
}