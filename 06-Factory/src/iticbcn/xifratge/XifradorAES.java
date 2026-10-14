package iticbcn.xifratge;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;


public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) {
        try {
           
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);

            return null;
        }
    }
    public byte[] xifraAES(String msg, String password) throws Exception {
        
        byte[] msgBytes = msg.getBytes("UTF-8");

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = md.digest(password.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        return cipher.doFinal(msgBytes);
        
    }


    public String desxifraAES(byte[] bMsgXifrat, String password)throws Exception{
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = md.digest(password.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] originalBytes = cipher.doFinal(bMsgXifrat);

        return new String(originalBytes, "UTF-8");
    }
}
