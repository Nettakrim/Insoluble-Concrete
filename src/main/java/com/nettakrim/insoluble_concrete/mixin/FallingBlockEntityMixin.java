package com.nettakrim.insoluble_concrete.mixin;

import net.minecraft.world.entity.item.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
    // concrete snaps down into water, which erases the water
    // we want concrete to act like sand, so it should just pass through
    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 0), name = "isConcrete")
    boolean stopSnapping(boolean isConcrete) {
        return false;
    }
}
