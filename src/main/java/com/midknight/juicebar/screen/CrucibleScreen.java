package com.midknight.juicebar.screen;

import com.midknight.juicebar.Juicebar;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.midknight.juicebar.container.CrucibleContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class CrucibleScreen extends ContainerScreen<CrucibleContainer> {

    // Fields
    private static final ResourceLocation GUI = new ResourceLocation(Juicebar.MOD_ID, "textures/gui/crucible_gui.png");
    private static final Rectangle PROGRESS_ARROW = new Rectangle(76,44,27,13);
    private static final Rectangle HEAT_ICON = new Rectangle(79,62,16,17);

    //Constructor Method
    public CrucibleScreen(CrucibleContainer crucibleContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(crucibleContainer, playerInventory, title);

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1F,1F,1F,1F);
        this.minecraft.getTextureManager().bind(GUI);
        int posX = this.leftPos;
        int posY = this.topPos;
        this.blit(matrixStack, posX, posY, 0, 0, this.getXSize(), this.getYSize());
        int smeltWidth = this.menu.smeltProgress();

        if(this.menu.isHeated()) {
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
