package com.nettakrim.insoluble_concrete.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.nettakrim.insoluble_concrete.ConcretePowderBlockAccessor;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(net.minecraft.block.ConcretePowderBlock.class)
public class ConcretePowderBlockMixin implements ConcretePowderBlockAccessor {
	@Shadow @Final private Block hardenedState;

	@ModifyReturnValue(at = @At("RETURN"), method = "shouldHarden")
	private static boolean init(boolean original) {
		return false;
	}

	@Override
	public Block insolubleConcrete$getHardenedState() {
		return hardenedState;
	}
}