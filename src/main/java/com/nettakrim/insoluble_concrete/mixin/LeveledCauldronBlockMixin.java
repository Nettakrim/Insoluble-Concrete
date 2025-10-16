package com.nettakrim.insoluble_concrete.mixin;

import com.mojang.serialization.MapCodec;
import com.nettakrim.insoluble_concrete.InsolubleConcrete;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(at = @At("HEAD"), method = "onEntityCollision")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl, CallbackInfo ci) {
        if (!this.precipitation.equals(Biome.Precipitation.RAIN)) return;
        if (!(entity instanceof ItemEntity itemEntity)) return;

        InsolubleConcrete.instance.Convert(itemEntity);
    }
}
