package com.nettakrim.insoluble_concrete.mixin;

import com.mojang.serialization.MapCodec;
import com.nettakrim.insoluble_concrete.InsolubleConcrete;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.block.LeveledCauldronBlock.class)
public class LeveledCauldronBlockMixin extends AbstractCauldronBlock {
    @Shadow @Final private Biome.Precipitation precipitation;

    public LeveledCauldronBlockMixin(Settings settings, CauldronBehavior.CauldronBehaviorMap behaviorMap) {
        super(settings, behaviorMap);
    }

    @Override
    public MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return null;
    }

    @Override
    public boolean isFull(BlockState state) {
        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!this.precipitation.equals(Biome.Precipitation.RAIN)) return;
        if (!this.isEntityTouchingFluid(state, pos, entity)) return;
        if (!(entity instanceof ItemEntity itemEntity)) return;

        InsolubleConcrete.instance.Convert(itemEntity);
    }
}
