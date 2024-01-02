package com.nettakrim.insoluble_concrete;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		ItemStack itemStack = itemEntity.getStack();
		Item newItem = ConvertItem(itemStack.getItem());

		if (newItem.equals(itemStack.getItem())) return;

		ItemStack newItemStack = new ItemStack(newItem, itemStack.getCount());

		if (itemStack.hasCustomName()) {
			newItemStack.setCustomName(itemStack.getName());
		}

		itemEntity.setStack(newItemStack);
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