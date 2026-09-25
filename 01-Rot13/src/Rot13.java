
public class Rot13{
    public final static char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï',
        'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };
    public final static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    
    public static void main(String[] args){
        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println("ABC                          => "+ xifraRot13("ABC"));
        System.out.println("XYZ                          => "+ xifraRot13("XYZ"));
        System.out.println("Hola, Mr. Calçot             => "+ xifraRot13("Hola, Mr. Calçot"));
        System.out.println("Perdó, per tu què és?        => "+ xifraRot13("Perdó, per tu què és?"));
        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        System.out.println("IÏJ                          => "+ desxifraRot13("IÏJ"));
        System.out.println("FGH                          => "+ desxifraRot13("FGH"));
        System.out.println("Òwúi, Ùá. Jiúkwb             => "+ desxifraRot13("Òwúi, Ùá. Jiúkwb"));
        System.out.println("Zmálx, zmá bc acñ nà?        => "+ desxifraRot13("Zmálx, zmá bc acñ nà?"));
        
    }

    public static String xifraRot13(String cadena){
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (Character.isUpperCase(c)){
                for (int j = 0; j < majuscules.length; j++) {
                    char cArray = majuscules[j];
                    if (c==cArray){
                        resultat += majuscules[(j + 13) % majuscules.length];
                    }
                }
            }else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    char cArray = minuscules[j];
                    if (c==cArray){
                        resultat += minuscules[(j + 13) % minuscules.length];
                    }
                }
            }else{
                resultat += c;
            }
        }


        return resultat;
    }

    public static String desxifraRot13(String cadena){
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (Character.isUpperCase(c)){
                for (int j = 0; j < majuscules.length; j++) {
                    char cArray = majuscules[j];
                    if (c==cArray){
                        resultat += majuscules[(j - 13 +majuscules.length) % majuscules.length];
                    }
                }
            }else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    char cArray = minuscules[j];
                    if (c==cArray){
                        resultat += minuscules[(j - 13 + minuscules.length) % minuscules.length];
                    }
                }
            }
            else{
                resultat += c;
            }
        }

        return resultat;
    
    }
}
