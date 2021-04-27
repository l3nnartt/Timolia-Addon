package de.labymod.lennart.karmatop;

import com.mojang.authlib.exceptions.AuthenticationException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class Authenticator {
    public boolean authenticate() {
        Minecraft mc = Minecraft.getMinecraft();
        Session session = mc.getSession();
        if (session == null) {
            return false;
        }
        try {
            mc.getSessionService().joinServer(session.getProfile(), session.getToken(), "26c142208fc4cb3e6ed4ebc598d989b4848786ed");
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
    }
}