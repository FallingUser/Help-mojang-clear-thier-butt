package primitiveenderdragon.briefrelease.importantbugfix.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnderDragon.class)
public class MixinEnderDragon {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;", ordinal = 0))
    private Vec3 modifyYddAddition(Vec3 instance, double x, double y, double z) {
        return instance.add(x, y * 10.0, z);
    }

    @Inject(method = "knockBack", at = @At("HEAD"))
    private void preventWingPushWhenSitting(ServerLevel serverLevel, List<Entity> entities, CallbackInfo ci) {
        for(Entity entity : entities) {
            entity.hurtMarked = true;
        }
    }
}