package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;



public class ClauPublica {
    public ClauPublica(){}
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        return kpg.generateKeyPair();
        
    }

       

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        byte[] msgBytes = msg.getBytes("UTF-8");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,clauPublica);
        return cipher.doFinal(msgBytes);     

    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,ClauPrivada);
        byte[] originalBytes = cipher.doFinal(msgXifrat);
        return new String(originalBytes, "UTF-8");
    }
}