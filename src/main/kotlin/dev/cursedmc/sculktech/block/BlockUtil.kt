package dev.cursedmc.sculktech.block

import dev.cursedmc.sculktech.block.entity.initBlockEntities
import dev.cursedmc.sculktech.modid
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings

infix fun Block.register(name: String) = Registry.register(Registry.BLOCK, Identifier(modid, name), this)
infix fun Block.registerWithItem(name: String): Block {
    Registry.register(Registry.ITEM, Identifier(modid, name), BlockItem(this, QuiltItemSettings()))
    return Registry.register(Registry.BLOCK, Identifier(modid, name), this)
}
fun initBlocks() {
    SCULK_BATTERY registerWithItem "sculk_battery"
    initBlockEntities()
}
