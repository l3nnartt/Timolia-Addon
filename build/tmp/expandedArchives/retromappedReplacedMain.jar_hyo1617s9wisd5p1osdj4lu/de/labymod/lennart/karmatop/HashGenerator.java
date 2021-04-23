package de.labymod.lennart.karmatop;

import net.minecraft.client.Minecraft;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashGenerator {

    private static Random random;

    public static String generateServerHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(bytes);
        return new BigInteger(1, crypt.digest()).toString(16);
    }

}