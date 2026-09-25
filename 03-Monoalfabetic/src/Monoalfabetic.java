import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Monoalfabetic{
    private final static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private final static List<Character> alfabetPermutat = new ArrayList<>();

    public static void main(String[] args) {
        
        for (char c : alfabet) {
            alfabetPermutat.add(c);
        }
        Collections.shuffle(alfabetPermutat);

        HashMap<Character,Character> alfabetNoXifrat = new HashMap<Character,Character>();
        HashMap<Character,Character> alfabetSiXifrat = new HashMap<Character,Character>();

        for (int i = 0; i < alfabet.length; i++) {
            alfabetNoXifrat.put(Character.valueOf(alfabet[i]),alfabetPermutat.get(i));
            alfabetSiXifrat.put(alfabetPermutat.get(i),Character.valueOf(alfabet[i]));
        }

        String[] frases = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String[] frasesPermutades = {"","",""};

        for (int i = 0; i < alfabet.length; i++) {
            System.out.printf("%c ",alfabet[i]);
        }
        System.out.printf("\n");
        for (int i = 0; i < alfabetPermutat.size(); i++) {
            System.out.printf("%c ",alfabetPermutat.get(i));
        }
        System.out.printf("\n");
        System.out.printf("Xifratge: \n");
        for (int i = 0; i < frases.length; i++) {
            System.out.printf("%s -> %s\n",frases[i],transforma(frases[i],alfabetNoXifrat));
            frasesPermutades[i] = transforma(frases[i],alfabetNoXifrat);
        
        }
        System.out.printf("\n");

        System.out.printf("Desxifratge: \n");
        for (int i = 0; i < frasesPermutades.length; i++) {
            System.out.printf("%s -> %s\n",frasesPermutades[i],transforma(frases[i],alfabetSiXifrat));        
        }

    }

    public static String transforma(String cadena, HashMap<Character,Character> mapa){
        StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean isLowerCase = false;
            if (Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
                isLowerCase = true;
            }

            if (Character.isLetter(c)){
                char upperC = Character.toUpperCase(c);

                if (mapa.containsKey(upperC)) {
                    char substitució = mapa.get(upperC);
                    resultat.append(isLowerCase ? Character.toLowerCase(substitució) : substitució);
                }
            }else{
                        resultat.append(c);
            }
        }
        return resultat.toString();
    }
}