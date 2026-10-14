package iticbcn.xifratge;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorPolialfabetic implements Xifrador {
    private final static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private char[] alfabetPermutat = new char[alfabet.length];
    private Random rnd;
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        String msgXifrat = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraPoliAlfa(msgXifrat);
    }
    private void permutaAlfabet(){
        List<Character> alfabetPermutatArrayList = new ArrayList<>();
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
    public String xifraPoliAlfa(String msg){
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
    public String desxifraPoliAlfa(String msgXifrat){
         StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            permutaAlfabet();
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

    public void initRandom(long seed){
        rnd = new Random(seed);
    }
}
