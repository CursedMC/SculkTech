package dev.cursedmc.sculktech.block.entity

import dev.cursedmc.sculktech.block.SCULK_BATTERY
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos

class SculkBatteryBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(SCULK_BATTERY_TYPE, pos, state) {
    private var storedCharge = 0

    fun incrementCharge(amount: Int): Int {
        var leftOver = 0
        val leftUntilMax = 3600-storedCharge
        if (leftUntilMax < amount) {
            storedCharge += leftUntilMax
            leftOver = amount - leftUntilMax
        } else {
            storedCharge += amount
        }
        markDirty()
        return leftOver
    }
    fun decrementCharge(amount: Int): Int {
        var leftOver = 0
        if (storedCharge < amount) {
            leftOver = amount - storedCharge
            storedCharge = 0
        } else {
            storedCharge -= amount
        }
        markDirty()
        return leftOver
    }
    fun getCharge() = storedCharge

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        storedCharge = nbt.getInt("stored_charge")
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        nbt.putInt("stored_charge", storedCharge)
    }
}

val SCULK_BATTERY_TYPE = FabricBlockEntityTypeBuilder.create(
    { pos, state ->
        SculkBatteryBlockEntity(pos, state)
    },
    SCULK_BATTERY
).build()
