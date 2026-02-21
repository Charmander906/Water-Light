package com.charmander906.waterlight.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockState.class)
public abstract class WaterSkylightPropagationMixin {

    @Inject(
        method = "propagatesSkylightDown",
        at = @At("HEAD"),
        cancellable = true
    )
    private void waterlight$waterPropagatesSkylight(
            BlockGetter level,
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        BlockState self = (BlockState)(Object)this;

        if (!self.is(Blocks.WATER)) return;

        if (!(level instanceof Level world)) return;

        if (!world.canSeeSky(pos.above())) return;

        cir.setReturnValue(true);
    }
}
