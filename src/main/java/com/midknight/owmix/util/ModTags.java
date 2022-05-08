package com.midknight.owmix.util;

import com.midknight.owmix.OverworldMixology;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;


public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> HEAT_SOURCES = tag("heat_sources");

        public static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(OverworldMixology.MOD_ID, name));
        }

        public static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
