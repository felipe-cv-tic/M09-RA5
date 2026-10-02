import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polialfabetic {
    private final static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private final static List<Character> alfabetPermutatArrayList = new ArrayList<>();
    private static char[] alfabetPermutat = new char[alfabet.length];
    private static Random rnd;
    private static int clauSecreta = 123;
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòlivia"};

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i]=xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n",msgs[i],msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgsXifrats.length; i++) {
            initRandom(clauSecreta);
            String msg =desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i],msg);
        }
    }

    public static void permutaAlfabet(){
        alfabetPermutatArrayList.clear();
        for (char c : alfabet) {
            alfabetPermutatArrayList.add(c);
        }
        Collections.shuffle(alfabetPermutatArrayList, rnd);
        char[] permutat = new char[alfabetPermutatArrayList.size()];
        for (int i = 0; i < alfabetPermutatArrayList.size(); i++) {
            permutat[i] = alfabetPermutatArrayList.get(i);
        }
        alfabetPermutat = permutat ;
    }
    public static String xifraPoliAlfa(String msg){
        StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            permutaAlfabet();

            char cUpper = Character.toUpperCase(c);
            int pos = -1;
            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == cUpper) {
                    pos = j;
                    break;
                }
            }

            if (pos != -1) {
                char cifrado = alfabetPermutat[pos];
                resultat.append(Character.isLowerCase(c) ? Character.toLowerCase(cifrado) : cifrado);
            } else {
                resultat.append(c);
            }
        }


         return resultat.toString();
    }
    public static String desxifraPoliAlfa(String msgXifrat){
         StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            permutaAlfabet(); // 1. Permutar en el mismo orden usando la misma semilla[cite: 1]
            char cUpper = Character.toUpperCase(c);
            int pos = -1;
            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == cUpper) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                char descifrado = alfabet[pos];
                resultat.append(Character.isLowerCase(c) ? Character.toLowerCase(descifrado) : descifrado);
            } else {
                resultat.append(c);
            }
        }


         return resultat.toString();
    }

    public static void initRandom(int seed){
        rnd = new Random(seed);
    }
}
