package com.nettakrim.insoluble_concrete;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsolubleConcrete implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("insoluble_concrete");

	public static InsolubleConcrete instance;

	@Override
	public void onInitialize() {
		instance = this;
	}



	public Item Convert(Item item) {
		if (item instanceof BlockItem blockItem) {
			if (blockItem.getBlock() instanceof ConcretePowderBlock concretePowderBlock) {
				return ((ConcretePowderBlockAccessor)concretePowderBlock).insolubleConcrete$getHardenedState().asItem();
			}
		}
		return item;
	}
}