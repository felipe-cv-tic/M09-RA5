package iticbcn.xifratge;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;;


public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "pirineus";
    public static void main(String[] args) {
       /* 
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        
        }*/
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
