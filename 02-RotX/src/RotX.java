
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
        String[] paraules = {"ABC","XYZ","Hola, Mr. Calçot","Perdó, per tu què és?"};
        int[] valors = {0,2,4,6};
        String[] paraulesXifrades = new String[paraules.length];
        System.out.printf("Xifrat\n");
        System.out.printf("---------\n");

        for (int i = 0; i < paraules.length; i++) {

            System.out.printf("(%d)-%s             => %s\n",valors[i],paraules[i],xifraRotX(paraules[i],valors[i]));
            paraulesXifrades[i] = xifraRotX(paraules[i],valors[i]);

        }
        System.out.println();
        System.out.printf("Desxifrat\n");
        System.out.printf("---------\n");

        for (int i = 0; i < paraulesXifrades.length; i++) {

            System.out.printf("(%d)-%s             => %s\n",valors[i],paraulesXifrades[i],desxifraRotX(paraulesXifrades[i],valors[i]));

        }

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
