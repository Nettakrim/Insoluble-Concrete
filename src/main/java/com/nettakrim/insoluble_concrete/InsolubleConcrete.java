package com.nettakrim.insoluble_concrete;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
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
		return Items.GOLD_BLOCK;
	}
}