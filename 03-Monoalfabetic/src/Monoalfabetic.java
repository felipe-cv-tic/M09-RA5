import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//03-Monoalfabetic
public class Monoalfabetic{
    private final static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private final static List<Character> alfabetPermutatArrayList = new ArrayList<>();
    private final static char[] alfabetPermutat = permutaAlfabet(alfabet);

    public static char[] permutaAlfabet(char[] alfabet){
        for (char c : alfabet) {
            alfabetPermutatArrayList.add(c);
        }
        Collections.shuffle(alfabetPermutatArrayList);
        char[] permutat = new char[alfabetPermutatArrayList.size()];
        for (int i = 0; i < alfabetPermutatArrayList.size(); i++) {
            permutat[i] = alfabetPermutatArrayList.get(i);
        }
        return permutat;
    }

    
    public static void main(String[] args) {
        
        

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
        for (int i = 0; i < alfabetPermutat.length; i++) {
            System.out.printf("%c ",alfabetPermutat[i]);
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
                        resultat.append(Character.toLowerCase(alfabetPermutat[j]));
                    }else if (c==cArray && !isLowerCase){
                        resultat.append(alfabetPermutat[j]);
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
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (c == alfabetPermutat[j]) {
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
