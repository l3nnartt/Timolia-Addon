package de.labymod.lennart.group;

import net.labymod.main.LabyMod;
import net.labymod.user.group.EnumGroupDisplayType;
import net.labymod.user.group.LabyGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import java.awt.*;

public class CustomGroup extends LabyGroup {

    public CustomGroup(int id, String name, char colorChar, Color color) {
        super(id, name.toLowerCase(), name, null, colorChar, null, null, color, EnumGroupDisplayType.BESIDE_NAME);
    }

    public void renderBadge(double x, double y, double width, double height, boolean small) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("icons/timolia/timolia128.png"));
        LabyMod.getInstance().getDrawUtils().drawTexture(x, y, 255.0D, 255.0D, 8.0D, 8.0D, 1.1F);
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.popMatrix();
    }

}
