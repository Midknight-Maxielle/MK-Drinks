package com.midknight.mkdrinks.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.container.CrucibleContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.awt.*;

public class CrucibleScreen extends ContainerScreen<CrucibleContainer> {

    private static final ResourceLocation GUI = new ResourceLocation(MKDrinks.MOD_ID, "textures/gui/crucible_gui.png");
    private static final Rectangle PROGRESS_ARROW = new Rectangle(76,44,27,13);
    private static final Rectangle HEAT_ICON = new Rectangle(79,62,16,17);

    public CrucibleScreen(CrucibleContainer crucibleContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(crucibleContainer, playerInventory, title);

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1F,1F,1F,1F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int posX = this.guiLeft;
        int posY = this.guiTop;
        this.blit(matrixStack, posX, posY, 0, 0, this.xSize, this.ySize);

        int smeltWidth = this.container.smeltProgress();

        if(this.container.isHeated()) {
            // renders the Heat Icon //
            this.blit(matrixStack,
                    posX + HEAT_ICON.x,
                    posY + HEAT_ICON.y,
                    176,
                    13,
                    HEAT_ICON.width,
                    HEAT_ICON.height
            );
        }
        // renders the Progress Arrow //
        this.blit(matrixStack,
                posX + PROGRESS_ARROW.x,
                posY + PROGRESS_ARROW.y,
                176,
                0,
                smeltWidth,
                PROGRESS_ARROW.height
        );

    }
}
