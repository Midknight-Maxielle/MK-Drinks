package com.midknight.juicebar.util;

import com.midknight.juicebar.Juicebar;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class JuiceTags {

    public static final Tag.Named<Block> HEAT_SOURCE = makeTag("heat_sources");

    private static Tag.Named<Block> makeTag(String name) {
        return BlockTags.bind(Juicebar.MOD_ID + ":" + name);
    }

    public static class BlocksTags extends BlockTagsProvider {

        public BlocksTags(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(generatorIn, modId, existingFileHelper);
        }

        @Override
        protected void addTags() {
            this.registerHeatSources();
        }

        protected void registerHeatSources() {
            tag(HEAT_SOURCE)
                    .add(net.minecraft.world.level.block.Blocks.LAVA)
                    .addTag(net.minecraft.tags.BlockTags.CAMPFIRES)
                    .addTag(net.minecraft.tags.BlockTags.FIRE);
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
