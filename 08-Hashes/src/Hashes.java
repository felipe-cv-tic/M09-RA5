import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class Hashes {
    public int npass = 0;

    public String getSHA512AmbSalt (String pw, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return generatedPassword;
    }

    public String getPBKDF2AmbSalt(String pw, String salt){
        String generatedPassword = null;
        try {
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 512);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] bytes = factory.generateSecret(spec).getEncoded();
            generatedPassword = HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return generatedPassword;
    }

    public String forcaBruta(String alg, String hash, String salt){
        String charset = "abcdefABCDEF1234567890!";
        npass = 0;
        StringBuilder pswIntent = new StringBuilder();
        for (int i1 = 0; i1 < charset.length(); i1++) {
            char c1 = charset.charAt(i1);
            for (int i2 = 0; i2 < charset.length(); i2++) {
                char c2 = charset.charAt(i2);
                for (int i3 = 0; i3 < charset.length(); i3++) {
                    char c3 = charset.charAt(i3);
                    for (int i4 = 0; i4 < charset.length(); i4++) {
                        char c4 = charset.charAt(i4);
                        for (int i5 = 0; i5 < charset.length(); i5++) {
                            char c5 = charset.charAt(i5);
                            for (int i6 = 0; i6 < charset.length(); i6++) {
                                char c6 = charset.charAt(i6);
                                char[] chars = {c1,c2,c3,c4,c5,c6};
                                pswIntent.setLength(0);
                                pswIntent.append(chars);
                                npass++;
                                if (alg.equals("SHA-512")){
                                    if (getSHA512AmbSalt(pswIntent.toString(), salt).equals(hash)){
                                        return pswIntent.toString();
                                    }

                                }else{
                                    if (getPBKDF2AmbSalt(pswIntent.toString(),salt).equals(hash)){
                                        return pswIntent.toString();
                                    }
                                }                                
                          }
                        }
                    }
                }
            }
        }

        return "";
    }
    public String getInterval(long t1, long t2){
        long millisTotal = t2 - t1;

        long millis = millisTotal % 1000;
        long totalSegons = millisTotal / 1000;
        long segons = totalSegons % 60;
        long totalMinuts = totalSegons / 60;
        long minuts = totalMinuts % 60;
        long totalHores = totalMinuts / 60;
        long hores = totalHores % 24;
        long dies = totalHores / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",dies, hores, minuts, segons, millis);
    }

    

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("--------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("--------------------------\n\n");
        }
    }
   
}