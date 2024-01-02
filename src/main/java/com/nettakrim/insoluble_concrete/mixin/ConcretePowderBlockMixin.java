package com.nettakrim.insoluble_concrete.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(net.minecraft.block.ConcretePowderBlock.class)
public class ConcretePowderBlockMixin {
	@ModifyReturnValue(at = @At("RETURN"), method = "shouldHarden")
	private static boolean init(boolean original) {
		return false;
	}
}