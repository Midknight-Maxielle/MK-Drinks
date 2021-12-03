package com.midknight.juicebar.util;

import com.midknight.juicebar.Juicebar;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class JuiceTags {

    public static class Blocks extends BlockTagsProvider {

        public Blocks(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(generatorIn, modId, existingFileHelper);
        }

        @Override
        protected void addTags() {
            this.registerHeatSources();
        }

        protected void registerHeatSources() {
            tag(JuiceTags.Blocks.HEAT_SOURCES)
                    .add(net.minecraft.world.level.block.Blocks.LAVA)
                    .addTag(net.minecraft.tags.BlockTags.CAMPFIRES)
                    .addTag(net.minecraft.tags.BlockTags.FIRE);
        }

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Juicebar.MOD_ID, name));
        }

        private static final Tags.IOptionalNamedTag<Block> HEAT_SOURCES =
                createTag("heat_sources");

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> METALLIZED_SOURCES =
                createTag("metallized_sources");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Juicebar.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
