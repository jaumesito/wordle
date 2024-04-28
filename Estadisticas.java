/*
Clase Estadisticas que introduce las estadisticas deseadas en el fichero deseado
*/

package wordle;


public class Estadisticas {
    
    //DECLARACIONES ATRIBUTOS
    //declaracion atributo de clase tipo String para poder almacenar el nombre 
    //del fichero 
    private static final String nombreFichero="estadisticas.txt";
    
    //METODO CONSTRUCTOR
    //declaracion del metodo constructor que contiene el metodo verEstadisticas
    public Estadisticas() throws Exception {
        verEstadisticas();  
    }
    
    public static void verEstadisticas() throws Exception {
        LineaFicherosLectura fichero=new LineaFicherosLectura(nombreFichero);
        Linea linea=new Linea();
        
        while(fichero.hayLineas()){
            linea=fichero.lectura();
            System.out.println(linea.toString());
        }
        
        System.out.print("Pulsar return para continuar > ");
        LT.readChar();
        fichero.cierre();
    }
    
    public static void escribirEstadisticas(Linea linea) throws Exception {
        LineaFicherosEscritura fichero=new LineaFicherosEscritura(nombreFichero,true);
        fichero.escritura(linea);
        fichero.escrituraSaltoDeLinea();
        fichero.cierre();
    }
    
}
