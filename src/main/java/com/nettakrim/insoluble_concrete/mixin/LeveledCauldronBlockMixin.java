package com.nettakrim.insoluble_concrete.mixin;

import com.nettakrim.insoluble_concrete.InsolubleConcrete;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.world.level.block.LayeredCauldronBlock.class)
public abstract class LeveledCauldronBlockMixin extends AbstractCauldronBlock {
    @Shadow @Final private Biome.Precipitation precipitationType;

    public LeveledCauldronBlockMixin(Properties settings, CauldronInteraction.Dispatcher interactions) {
        super(settings, interactions);
    }

    @Inject(at = @At("HEAD"), method = "entityInside")
    public void onEntityCollision(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (!this.precipitationType.equals(Biome.Precipitation.RAIN)) return;
        if (!(entity instanceof ItemEntity itemEntity)) return;

        InsolubleConcrete.instance.Convert(itemEntity);
    }
}
