package dev.cursedmc.sculktech

import dev.cursedmc.sculktech.block.initBlocks
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

val modid = "sculktech"

class SculkTech : ModInitializer {
    override fun onInitialize(mod: ModContainer?) {
        initBlocks()
    }
}
