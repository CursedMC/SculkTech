package dev.cursedmc.sculktech.block

import dev.cursedmc.sculktech.block.entity.SculkBatteryBlockEntity
import dev.proxyfox.sculkapi.api.SculkInteractable
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldAccess
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings

class SculkBatteryBlock(settings: QuiltBlockSettings) : BlockWithEntity(settings), SculkInteractable {
    override fun onInteract(pos: BlockPos, world: WorldAccess, charge: Int): Int {
        if (world !is ServerWorld) return 0
        val power = world.getReceivedRedstonePower(pos)
        if (power != 0) return 0
        val entity = world.getBlockEntity(pos) as SculkBatteryBlockEntity
        return charge - entity.incrementCharge(charge)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return SculkBatteryBlockEntity(pos, state)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }
}

val SCULK_BATTERY = SculkBatteryBlock(QuiltBlockSettings.of(Material.SCULK))
