
public class RotX{
    public final static char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï',
        'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };
    public final static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static void main(String[] args){
        System.out.printf("Xifrat\n");
        System.out.printf("---------\n");
        System.out.printf("(0)-ABC                          => %s\n", xifraRotX("ABC",0));
        System.out.printf("(2)-XYZ                          => %s\n", xifraRotX("XYZ",2));
        System.out.printf("(4)-Hola, Mr. Calçot             => %s\n", xifraRotX("Hola, Mr. Calçot",4));
        System.out.printf("(6)-Perdó, per tu què és?        => %s\n", xifraRotX("Perdó, per tu què és?",6));
        System.out.println();
        System.out.printf("Desxifrat\n");
        System.out.printf("---------\n");
        System.out.printf("(0)-ABC                          => %s\n", desxifraRotX("ABC",0));
        System.out.printf("(2)-ZAÁ                          => %s\n", desxifraRotX("ZAÁ",2));
        System.out.printf("(4)-Ïqoc, Óú. Écoèqü             => %s\n", desxifraRotX("Ïqoc, Óú. Écoèqü ",4));
        System.out.printf("(6)-Úiüht, úiü wx ùxì ív?        => %s\n", desxifraRotX("Úiüht, úiü wx ùxì ív?",6));
        System.out.println();

        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }

    public static String xifraRotX(String cadena, int desplaçament){
        StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (Character.isUpperCase(c)){
                for (int j = 0; j < majuscules.length; j++) {
                    char cArray = majuscules[j];
                    if (c==cArray){
                        resultat.append(majuscules[(j + desplaçament) % majuscules.length]);
                    }
                }
            }else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    char cArray = minuscules[j];
                    if (c==cArray){
                        resultat.append(minuscules[(j + desplaçament) % minuscules.length]);
                    }
                }
            }else{
                resultat.append(c);
            }
        }


        return resultat.toString();
    }

    public static String desxifraRotX(String cadena,int desplaçament){
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (Character.isUpperCase(c)){
                for (int j = 0; j < majuscules.length; j++) {
                    char cArray = majuscules[j];
                    if (c==cArray){
                        resultat.append(majuscules[(j - desplaçament +majuscules.length) % majuscules.length]);
                    }
                }
            }else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    char cArray = minuscules[j];
                    if (c==cArray){
                        resultat.append(minuscules[(j - desplaçament + minuscules.length) % minuscules.length]);
                    }
                }
            }
            else{
                resultat.append(c) ;
            }
        }

        return resultat.toString();
    
    }

    public static void forcaBrutaRotX(String cadenaXifrada){
        System.out.printf("Missatge xifrat: %s \n", cadenaXifrada);
        System.out.printf("----------------\n");
        for (int i = 0; i < 40 ; i++) {
            System.out.printf("(%d)->%s\n", i ,desxifraRotX(cadenaXifrada, i));
        }
    }
}
