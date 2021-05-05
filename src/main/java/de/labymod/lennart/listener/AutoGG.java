package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

public class AutoGG implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        //1vs1
        if (TimoliaAddon.getInstance().isEnabledAutoGG1vs1()) {
            if (s1.contains("1vs1") && s1.contains("»")) {
                if (s1.contains("in Folge") || s1.contains("hat den Kampf gegen")) return false;
                if (s1.contains("gewonnen")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getWin1vs1());
                    //TimoliaAddon.getInstance().setKillstreak(+1);
                } else if (s1.contains("verloren")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getLose1vs1());
                    //TimoliaAddon.getInstance().setKillstreak(0);
                }
            }
        }

        //4rena
        if (TimoliaAddon.getInstance().isArena()) {
            if (!Minecraft.getMinecraft().thePlayer.inventory.hasItem(Item.getItemById(345)) || Minecraft.getMinecraft().thePlayer.inventory.hasItem(Item.getItemById(347))) {
                if (TimoliaAddon.getInstance().isEnabledAutoGG4rena()) {
                    //2x2
                    if (s1.contains("4rena") && s1.contains("»")) {
                        if (s1.contains("Team") && s1.contains("gewinnt diese Runde")) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("@" + TimoliaAddon.getInstance().getMatch4rena());
                        }
                        else if (s1.contains("gewinnt diese Runde")) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getMatch4rena());
                        }
                    }
                    //4x1
                    if (TimoliaAddon.getInstance().isArena()) {
                        if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGame4rena());
                        }
                    }
                }
            }
        }

        //Brainbow
        if (TimoliaAddon.getInstance().isEnabledAutoGGBrainbow()) {
            if (TimoliaAddon.getInstance().isBrainbow()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameBrainbow());
                }
            }
        }

        //Castles
        if (TimoliaAddon.getInstance().isEnabledAutoGGCastles()) {
            if (TimoliaAddon.getInstance().isCastles()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameCastles());
                }
            }
        }

        //DNA
        if (TimoliaAddon.getInstance().isEnabledAutoGGDNA()) {
            if (TimoliaAddon.getInstance().isDna()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameDNA());
                }
            }
        }

        //InTime
        if (TimoliaAddon.getInstance().isEnabledAutoGGInTime()) {
            if (TimoliaAddon.getInstance().isIntime()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameInTime());
                }
            }
        }

        //Mineception
        if (TimoliaAddon.getInstance().isEnabledAutoGGMineception()) {
            if (TimoliaAddon.getInstance().isMineception()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameMineception());
                }
            }
        }

        //Splun
        if (TimoliaAddon.getInstance().isEnabledAutoGGSplun()) {
            if (TimoliaAddon.getInstance().isSplun()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameSplun());
                }
            }
        }

        //Suspicious
        if (TimoliaAddon.getInstance().isEnabledAutoGGSuspicious()) {
            if (TimoliaAddon.getInstance().isSuspicious()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameSuspicious());
                }
            }
        }

        //TSpiele
        if (TimoliaAddon.getInstance().isEnabledAutoGGTSpiele()) {
            if (TimoliaAddon.getInstance().isTspiele()) {
                if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameTSpiele());
                }
            }
        } return false;
    }
}