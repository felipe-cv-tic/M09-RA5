package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class XifradorMonoalfabetic implements Xifrador{
    private final static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private char[] alfabetPermutat;
    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(alfabet);
    }
    private char[] permutaAlfabet(char[] alfabet){
        List<Character> alfabetPermutatArrayList = new ArrayList<>();
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
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        validaClau(clau);
        return new TextXifrat(xifraMonoAlfa(msg).getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        validaClau(clau);
        if (xifrat == null || xifrat.getBytes() == null) {
            throw new IllegalArgumentException("El text xifrat no pot ser null");
        }
        return desxifraMonoAlfa(new String(xifrat.getBytes(), java.nio.charset.StandardCharsets.UTF_8));
    }
    private void validaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }
    public String xifraMonoAlfa(String cadena){
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
    public String desxifraMonoAlfa(String cadena) {
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