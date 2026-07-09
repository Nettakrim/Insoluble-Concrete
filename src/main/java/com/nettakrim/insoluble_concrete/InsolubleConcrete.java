package com.nettakrim.insoluble_concrete;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ConcretePowderBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsolubleConcrete implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("insoluble_concrete");

	public static InsolubleConcrete instance;

	@Override
	public void onInitialize() {
		instance = this;
	}

	public void Convert(ItemEntity itemEntity) {
		ItemStack itemStack = itemEntity.getItem();
		Item newItem = ConvertItem(itemStack.getItem());

		if (newItem.equals(itemStack.getItem())) return;

		ItemStack newItemStack = itemStack.transmuteCopy(newItem, itemStack.getCount());

		itemEntity.setItem(newItemStack);
	}

	public Item ConvertItem(Item item) {
		if (item instanceof BlockItem blockItem) {
			if (blockItem.getBlock() instanceof ConcretePowderBlock concretePowderBlock) {
				return ((ConcretePowderBlockAccessor)concretePowderBlock).insolubleConcrete$getHardenedState().asItem();
			}
		}
		return item;
	}
}