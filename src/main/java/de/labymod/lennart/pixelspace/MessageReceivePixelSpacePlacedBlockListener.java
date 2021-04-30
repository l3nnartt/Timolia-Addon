package de.labymod.lennart.pixelspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.core.LabyModCore;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class MessageReceivePixelSpacePlacedBlockListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String msg) {
        if (msg.contains("Timolia» Du hast einen Block platziert! In 20 Sekunden kannst du den nächsten bauen!")) {

            TimoliaAddon.getINSTANCE().addplacedBlocks();
        } return false;
    }

    public static Block getBlockPosLookingAt(World world){
        try {
            MovingObjectPosition movingObjectPosition = LabyModCore.getMinecraft().getPlayer().rayTrace(10, 1.0F);
            if(movingObjectPosition == null){
                return null;
            }
            BlockPos blockPos = movingObjectPosition.getBlockPos();
            if(blockPos == null){
                return null;
            } return world.getBlockState(blockPos).getBlock();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}