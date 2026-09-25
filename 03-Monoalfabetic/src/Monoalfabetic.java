import java.util.ArrayList;
import java.util.Collections;
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

        String[] frases = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String[] frasesPermutades = {"","",""};

        for (int i = 0; i < alfabet.length; i++) {
            System.out.printf("%c ",alfabet[i]);
        }
        System.out.println();
        for (int i = 0; i < alfabetPermutat.size(); i++) {
            System.out.printf("%c ",alfabetPermutat.get(i));
        }
        System.out.println();
        System.out.printf("Xifratge: \n");
        for (int i = 0; i < frases.length; i++) {
            System.out.printf("%s -> %s\n",frases[i],xifraMonoAlfa(frases[i]));
            frasesPermutades[i] = xifraMonoAlfa(frases[i]);
        
        }
        System.out.printf("Desxifratge: \n");
        for (int i = 0; i < frasesPermutades.length; i++) {
            System.out.printf("%s -> %s\n",frasesPermutades[i],desxifraMonoAlfa(frasesPermutades[i]));        
        }

    }

    public static String xifraMonoAlfa(String cadena){
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean isLowerCase = false;
            if (Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
                isLowerCase = true;
            }

            if (Character.isLetter(c)){
                for (int j = 0; j < alfabet.length; j++) {
                    char cArray = alfabet[j];
                    if (c==cArray && isLowerCase){
                        resultat.append(Character.toLowerCase(alfabetPermutat.get(j)));
                    }else if (c==cArray && !isLowerCase){
                        resultat.append(alfabetPermutat.get(j));
                    }
                }
            }else{
                        resultat.append(c);
            }
        }
        return resultat.toString();
    }
    public static String desxifraMonoAlfa(String cadena) {
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean isLowerCase = Character.isLowerCase(c);

            if (isLowerCase) {
                c = Character.toUpperCase(c);
            }

            if (Character.isLetter(c)) {
                for (int j = 0; j < alfabetPermutat.size(); j++) {
                    if (c == alfabetPermutat.get(j)) {
                        char targetChar = alfabet[j]; 
                        if(isLowerCase){
                             resultat.append(Character.toLowerCase(targetChar));
                        }else{
                             resultat.append(targetChar);

                        }
                    }
                }
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }
}