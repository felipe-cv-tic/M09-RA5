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
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg =desxifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i],msg);
        }
    }

    public static void permutaAlfabet(){
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



         return resultat.toString();
    }
    public static String desxifraPoliAlfa(String msgXifrat){
         StringBuffer resultat = new StringBuffer();



         return resultat.toString();
    }

    public static void initRandom(int seed){
        rnd = new Random(seed);
    }
}
