package de.labymod.lennart.pxlspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.core.LabyModCore;
import net.labymod.main.LabyMod;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MessageReceivePixelSpacePlacedBlockListener implements MessageReceiveEvent {

    private String farbe = null;

    @Override
    public boolean onReceive(String s, String msg) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (msg.contains("Timolia» Du hast einen Block platziert! In 20 Sekunden kannst du den nächsten bauen!")) {
                TimoliaAddon.getInstance().addplacedBlocks();
                getBlockColor();
                if (TimoliaAddon.getInstance().getAuthenticator().authenticate()) {
                    TimoliaAddon.getInstance().getExService().execute(() -> {
                        try {
                            HttpURLConnection con = (HttpURLConnection) (new URL("http://karmatop.de/addon/pxl.php?name=" + LabyMod.getInstance().getLabyModAPI().getPlayerUsername() + "&color=" + farbe + "&uuid=" + LabyMod.getInstance().getLabyModAPI().getPlayerUUID())).openConnection();
                            con.setRequestProperty("User-Agent",
                                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                            con.connect();
                            int code = con.getResponseCode();
                            if (code == 200) {
                                System.out.println("Timolia Addon » Block added");
                            } else {
                                System.out.println(code);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } else {
            return false;
        } return false;
    }

    private void getBlockColor() {
        MovingObjectPosition movingObjectPosition = LabyModCore.getMinecraft().getPlayer().rayTrace(10, 1.0F);
        BlockPos blockPos = movingObjectPosition.getBlockPos();
        IBlockState iblockstate = Minecraft.getMinecraft().theWorld.getBlockState(blockPos);

        for (Map.Entry<IProperty, Comparable> entry : iblockstate.getProperties().entrySet()) {
            String s = entry.getValue().toString();

            if (entry.getValue() == Boolean.TRUE) {
                s = EnumChatFormatting.GREEN + s;
            } else if (entry.getValue() == Boolean.FALSE) {
                s = EnumChatFormatting.RED + s;
            }
            farbe = s;
        }
    }

}