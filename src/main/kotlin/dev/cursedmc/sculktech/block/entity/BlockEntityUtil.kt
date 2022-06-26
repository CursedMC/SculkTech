package dev.cursedmc.sculktech.block.entity

import dev.cursedmc.sculktech.modid
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

infix fun <T : BlockEntity> BlockEntityType<T>.register(name: String) = Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(modid, name), this)

fun initBlockEntities() {
    SCULK_BATTERY_TYPE register "sculk_battery"
}
