/*
CLASE Palabra
AGLUTINA LA DESCRIPCIÓN (ESTADO) Y FUNCIONALIDADES (COMPORTAMIENTO) QUE DEFINEN
A UN OBJETO Palara
*/
package wordle;


public class Palabra {
    //DECLARACIONES ATRIBUTOS DE LA CLASE
    //declaración atributo de clase constante entera para representar
    //el número máximo de caracteres que puede tener un objeto Palabra
    private static final int MAXIMO_NUMERO_CARACTERES=20;
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control SALTO DE LINEA
    private static final int SALTO_LINEA=(int) '\n';
    //declaración atributo de clase constante caracter para representar
    //el caracter que identifica el final de una secuencia de caracteres
    //a introducir por teclado
    private static final char FIN_SECUENCIA='.';
    //declaración atributo de clase constante caracter para representar
    //el caracter espacio 
    private static final char ESPACIO=' ';
    //declaración atributo de clase variable char para almacenar el último
    //caracter leído de la secuencia de caracteres introducida por teclado
    private static char caracter=ESPACIO;
    
    //declaración atributo de objeto array de componentes caracteres
    private char [] caracteres=new char[MAXIMO_NUMERO_CARACTERES];
    //declaración atributode objeto variable entera para almacenar el número
    //de caracteres de un objeto Palabra
    private int numeroCaracteres;
    
    //MÉTODOS CONSTRUCTORES
    //declaración método constructor sin parámetros
    public Palabra() {
        //inicialización atributo numeroCaracteres a 0
        numeroCaracteres=0;
    }
    
    public Palabra(String tmp) {
        caracteres=tmp.toCharArray();
        numeroCaracteres=tmp.length();
    }
    
    //MÉTODOS FUNCIONALES
    //declaración método de clase hayPalabras que verifica si hay alguna palabra
    //por leer en la secuencia de caracteres introducida por teclado
    //método de clase  boleano hayPalabras que verifica si en la secuencia de caracteres hay
    //al menos una palabra para leer
    public static boolean hayPalabras() throws Exception{
        buscarPalabra();
        if ((caracter!=FIN_SECUENCIA)&&(caracter!=SALTO_LINEA)) {
            return true;
        }
        else {
            //vaciar buffer de entrada
            if (caracter==FIN_SECUENCIA) {
                LT.skipLine();
            }
            //inicializar el atributo caracter a espacio para poder estar preparados
            //para la lectura de la palabras desde una nueva secuencia de caracteres
            //introducida por teclado
            caracter=ESPACIO;
            //devolver false como resultado de este método
            return false;
        }  
    }
    
//    //declaración método privado buscarPalabra que lleva cabo la búsqueda
//    //de una palabra en la secuencia de caracteres introducida por teclado
//    private static void buscarPalabra() throws Exception {
//        while (caracter==ESPACIO) {
//            caracter=LT.readChar();
//        }
//    }
    
    //declaración método privado buscarPalabra que lleva cabo la búsqueda
    //de una palabra en la secuencia de caracteres introducida por teclado
    private static void buscarPalabra() throws Exception {
        while ((!(((caracter>='a')&&(caracter<='z'))||((caracter>='A')&&(caracter<='Z'))))
                &&(caracter!=FIN_SECUENCIA)&&(caracter!=SALTO_LINEA)){
            caracter=LT.readChar();
        }
    }
    
    //declaración método de objeto lectura que lleva a cabo la lectura, caracter
    //a caracter, de un objeto Palabra desde la secuencia de caracteres introducida
    //por teclado
    public void lectura() throws Exception {
        //inicialización atributo numeroCaracteres a 0 para incializar el
        //objeto Palabra donde vamos a almacenar la palabra a leer desde
        //la secuencia de caracteres
        numeroCaracteres=0;
        //bucle lectura de la palabra caracter a caracter
        while ((caracter!=FIN_SECUENCIA)&&(caracter!=ESPACIO)&&(caracter!=SALTO_LINEA)) {
            //almacenar el caracter leído en la componente correspondiente
            //del atributo caracteres
            caracteres[numeroCaracteres]=caracter;
            //incrementar atributo numeroCaracteres
            numeroCaracteres++;
            //lectura siguiente caracter de la secuencia de caracteres
            caracter=LT.readChar();
        }
    }  
    
    //declaración método de objeto toSTring que lleva a cabo la conversión
    //de un objeto Palabra a String
    @Override
    public String toString() {
        //DECLARACIONES
        //declaración variable String para almacenar a través de la operación
        //de concatenación los diferentes caracteres del objeto Palabra
        //correspondiente
        String resultado="";
        
        //bucle de concatenación para almacenar en el String resultado
        //los caracteres del objeto Palabra correspondiente
        for (int indice=0;indice<numeroCaracteres;indice++) {
            //concatenación en resultado del caracter del objeto
            //Palabra correspondiente a la iteración indice-ésima
            resultado=resultado+caracteres[indice];
        }
        //Devolución resultado
        return resultado;   
    }
    
    //delcaración método de objeto getNumeroCaracteres que devuelve el número
    //de caracteres de un objeto Palabra
    public int getNumeroCaracteres() {
        return numeroCaracteres;
    }
    
    
    //declaración método de objeto función tieneLasCincoVocales que verifica si la
    //el objeto Palabra correspondiente tiene las 5 vocales entre sus caracteres
    public boolean tieneLasCincoVocales() {
        //DECLARACIONES
        //declaraciónvaribale array de componentes booleanas para almacenar
        //la aparación o no de cada una de las vocales en el array dado
        //que contiene la palabra
        boolean [] apariciones={false,false,false,false,false};
        
        //ACCIONES
        //bucle para detectar las apariciones de las vocales en la palabra
        for (int indice=0;indice<numeroCaracteres;indice++) {
            //si el caracter es vocal actualizar componente correspondiente del
            //array apariciones
            switch (caracteres[indice]) {
                case 'a' -> apariciones[0]=true;
                case 'e' -> apariciones[1]=true;
                case 'i' -> apariciones[2]=true;
                case 'o' -> apariciones[3]=true;
                case 'u' -> apariciones[4]=true;
            }
        }
        
        //devolver el resultado de verificar si las 5 vocales han aparecido
        //en la palabra
        return verificar(apariciones);
    }
    

    //declaración método privado función verificar que lleva a cabo la verificación
    //de si el array booleano dado todas sus componentes son true
    private boolean verificar(boolean [] datos) {
        //bucle verificación por componentes
        for (int indice=0;indice<datos.length;indice++) {
            if (datos[indice]!=true) {
                return false;
            }
        }
        
        //devolución del valor true porque se ha comprobado que todas
        //las componentes del array dado son true
        return true;
    }
    
    //declaración método de objeto numerVocales que lleva a cabo la obtención
    //del número de caracteres vocales que el objeto Palabra correspodiente
    //contiene
    public int numeroVocales() {
        //DECLARACIONES
        //declaración variable entera para almacenar el número de vocales
        int numeroVocales=0;
        
        //ACCIONES
        //bucle para contar el número de vocales
        for (int indice=0;indice<numeroCaracteres;indice++) {
            //verificar si el caracter indice-ésimo del objeto Palabra
            //es vocal
            if (esVocal(caracteres[indice])) {
                //incrementar contador 
                numeroVocales++; 
            }
        }
        //DEVOLUCIÓN CONTADOR
        return numeroVocales;
    }
    
    //declaración método privado función esVocal que verifica si el caracter
    //dado por parámetro es una vocal
    private boolean esVocal(char car) {
        return ((car=='a')||(car=='e')||(car=='i')||(car=='o')||(car=='u'));
    }
    
    //declaración método de objeto terminacionEn que verifica si el objeto Palabra
    //correspondiente termina con el caracter dado por teclado
    public boolean terminacionEn(char car) {
        return (caracteres[numeroCaracteres-1]==car);
    }
    
    //declaración método de objeto comienzoEn que verifica si el objeto Palabra
    //correspondiente comienza con el caracter dado por teclado
    public boolean comienzoEn(char car) {
        return (caracteres[0]==car);
    }
    
    //MÉTODO adicionCaracter LLEVA A CABO LA ADICIÓN DE UN CARACTER DADO POR PARÁMETRO
    //EN UN OBJETO Palabra
    public void adicionCaracter(char caracter) {
        //almacenar el caracter dado en la primera componente libre del atributo
        //caracteres del objeto Palabra. El índice de dicha componente coincide
        //con el valor del atributo numeroCaracteres del mismo objeto Palabra
        caracteres[numeroCaracteres]=caracter;
        //incrementar el atributo numeroCaracteres del objeto Palabra para que
        //tenga en cuenta el caracter que acabamos de añadir en la palabra
        numeroCaracteres++;
    }
    
    //MÉTODO obtenerCaracter LLEVA A CABO LA OBTENCIÓN DEL CARACTER DE UNA
    //PALABRA QUE ESTÁ ALMACENADO EN LA POSICIÓN DADA DENTRO DEL ATRIBUTO
    //caracteres DE DICHO OBJETO Palabra
    public char obtenerCaracter(int posicion) {
        //devolver el caracter del objeto Palabra que está en la posición
        //dada dentro del atributo array caracteres
        return (caracteres[posicion]);
    } 
    
    //declaración método objeto numeroCaracteresPar que verifica si el número
    //de caracteres del objeto Palabra correspondiente es par
    public boolean numeroCaracteresPar() {
        return ((numeroCaracteres%2)==0);
    }
    
    //declaración método de clase iguales que verifica si los dos objetos Palabra
    //dados por parámetros son iguales
    public static boolean iguales(Palabra palabra1, Palabra palabra2) {
        //verificar si tienen el mismo número de caracteres
        if (palabra1.numeroCaracteres==palabra2.numeroCaracteres) {
            //verificar si son iguales caracter a caracter
            for (int indice=0;indice<palabra1.numeroCaracteres;indice++) {
                if (palabra1.caracteres[indice]!=palabra2.caracteres[indice]) {
                    //devolver false porque los objetos Palabra no son iguales
                    return false;
                }
            }
        }
        else {
            //devolver false porque los objetos Palabra no son iguales
            return false;
        }
        //devolver true porque los objetos Palabra son iguales
        return true;
    }
    
    //declaación métodoque lleva a cabo la copia de un objeto Palabra dado
    //por parámetro en otro objeto palabra también dado por parámetro
    public static void copiar(Palabra palabra1, Palabra palabra2) {
        //asignar al atributo numeroCaracteres del objeto Palabra palabra2
        //el contenido del atributo numeroCaracteres del objeto Palabra
        //palabra1
        palabra2.numeroCaracteres=palabra1.numeroCaracteres;
        //bucle de copiar, caracter a caracter, los caracteres del objeto
        //Palabra palabra1 en el objeto Palabra palabra2
        for (int indice=0;indice<palabra1.numeroCaracteres;indice++) {
            palabra2.caracteres[indice]=palabra1.caracteres[indice];
        }
    }
    
    //declaración método que cuenta cuantas veces aparece un caracter en un
    //objeto Palabra
    public int contarCaracter(char caracter) {
        int contador=0;
        for(int indice=0;indice<numeroCaracteres;indice++){
            if(caracteres[indice]==caracter){
                contador++;
            }
        }
        return contador;
    }
    
    //declaración método que cuenta cuantas veces aparece un caracter en un 
    //objeto Palabra dentro de un límite
    public int contarCaracteresAparecidos(char caracter, int indiceMax) {
        int contador=0;
        for(int indice=0;indice<indiceMax;indice++){
            if(caracteres[indice]==caracter){
                contador++;
            }
        }
        return contador;
    }
    
    //declaración método que cuenta cuantas veces aparece el caracter introducido
    //en verde en la visualización
    public int contarVerdes(char caracter, char [] datos){
        int contador=0;
        for(int indice=0;indice<datos.length;indice++){
            if(datos[indice]==caracter){
                contador++;
            }
        }
        return contador;
    }
    
    //declaración subprograma que lleva a cabo la visualización carácter a
    //carácter de la palabra introducida en sus respectivos colores
    public static void visualizacionPalabra(Palabra []palabras, Palabra objetivo, int intentos) throws Exception {
        //declaración de los array que contienen los colores
        final int verde=1;
        final int amarillo=2;
        final int blanco=6;
        int [] colores=new int[5];
        //array que contiene las letras que aparecerán en verde posteriormente
        char [] verdes=new char[5];
        //bucle para recorrer todas las palabras introducidas
        for(int i=0;i<=intentos;i++){
            System.out.println();
            inicializarArray(verdes);
            //condición para que las palabras ya en mayúsculas vuelvan a
            //estar en minúsculas para poder comparar bien
            if(i<intentos){
                palabras[i].palabraMinusculas();
            }
            //bucle para evitar el problema de que nos salga una letra y después vuelva a salir bien colocada
            for(int indice=0;indice<objetivo.getNumeroCaracteres();indice++){
                if(palabras[i].obtenerCaracter(indice)==objetivo.obtenerCaracter(indice)){
                    verdes[indice]=palabras[i].obtenerCaracter(indice);
                }
            }
            //bucle de tratamiento de cada letra
            for(int indice=0;indice<objetivo.getNumeroCaracteres();indice++){
                //si la palabra está en la posición correcta
                if(palabras[i].obtenerCaracter(indice)==objetivo.obtenerCaracter(indice)){
                    colores[indice]=verde;
                }
                //si no está en la posición correcta
                else{
                    boolean aparece=false;
                    //bucle para identificar si aparece la letra en la palabra objetivo
                    for(int indice2=0;indice2<objetivo.getNumeroCaracteres() && !aparece;indice2++){
                        if(palabras[i].obtenerCaracter(indice)==objetivo.obtenerCaracter(indice2)){
                            //miramos si la letra que toca ya ha salido las veces necesarias
                            if(palabras[i].contarCaracteresAparecidos(palabras[i].obtenerCaracter(indice), (indice-1))+
                                    palabras[i].contarVerdes(palabras[i].obtenerCaracter(indice), verdes)<
                                    objetivo.contarCaracter(palabras[i].obtenerCaracter(indice))){
                                colores[indice]=amarillo;
                            }
                            //si ya ha salido las veces que hace falta la ponemos en blanco
                            else{
                                colores[indice]=blanco;
                            }
                            aparece=true;
                        }
                    }
                    //si no aparece en la palabra la ponemos en blanco
                    if(aparece==false){
                        colores[indice]=blanco;
                    }
                }
                    
            }
            //poner la palabra en mayúsculas
            palabras[i].palabraMayusculas();
            LT.visualizadorColorFondo(palabras[i].toString(), colores);
            
        }
        System.out.println("\n");
    }
    
    //declaración de método que concluye si la palabra introducida en la partida
    //aparece en el diccionario
    public static boolean revisarPalabra(Palabra palabra, String idioma) throws Exception {
        PalabraFicherosLectura fichero=new PalabraFicherosLectura("wordle_"+idioma+"_diccionari.txt");
        Palabra palabraFichero=new Palabra();
        while(fichero.hayPalabras()){
            palabraFichero=fichero.lectura();
            if(Palabra.iguales(palabra, palabraFichero)){
                return true;
            }
        }
        return false;
    }
    
    //declaración método que devuelve la palabra en mayusculas
    private void palabraMayusculas() throws Exception {
        for(int indice=0;indice<numeroCaracteres;indice++){
            caracteres[indice]=(char)(caracteres[indice]-32);
        }
    }
    
    //declaración método que devuelve la palabra en mayusculas
    private void palabraMinusculas() throws Exception {
        for(int indice=0;indice<numeroCaracteres;indice++){
            caracteres[indice]=(char)(caracteres[indice]+32);
        }
    }
    
    //declaración método que inicializa array
    private static void inicializarArray(char [] datos) {
        for(int indice=0;indice<datos.length;indice++){
            datos[indice]=' ';
        }
    }
}
