/*
CLASE Wordle. Contiene todas las funcionalidades y necesidades para jugar una
partida de Wordle
 */
package wordle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Wordle {
    
    //declaración método que genere una palabra aleatoria objetivo
    public Palabra palabraObjetivo(String idioma) throws Exception {
        Palabra palabra=new Palabra();
        PalabraFicherosLectura fichero=new PalabraFicherosLectura("wordle_"+idioma+"_solucions.txt");
        int numeroPalabra,aux=0,palabrasFichero;
        Random generador=new Random();
        
        palabrasFichero=fichero.GetNumeroPalabras();
        
        numeroPalabra=generador.nextInt(0,palabrasFichero);
        
        while(fichero.hayPalabras() && aux!=numeroPalabra) {
            palabra=fichero.lectura();
            aux++;
        }
        
        fichero.cerrarEnlaceFichero();
        return palabra;
    }
    
    //declaración método que lleve a cabo la partida
    public void jugarPartida() throws Exception {
        //declaración objeto Linea que contiene las estadisticas de la partida
        Linea estadisticas=new Linea();
        //declaración variable tipo String que almacena el idioma de juego
        String idioma=eleccionIdioma();
        //declaración variable de tipo int que contiene los intentos que lleva
        //el jugador
        int intentos=0;
        //declaración variable String que contiene la fecha actual
        String fecha=new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
        //declaración variable booleana que indica si se ha encontrado la palabra
        //objetivo
        boolean partidaGanada=false;
        //declaración variable de tipo String que guarda el nombre del jugador
        String nombre;
        //visualización mensaje de inicio de la partida
        System.out.print("Comenzaremos una partida de Wordle en "+idioma+"\n"+
                "\nIntroduce tu nombre: ");
        nombre=LT.readLine();
        //escribimos en el fichero de estadísticas la fecha y el nombre
        estadisticas.adicion(fecha+" "+nombre);
        
        //inicialización variable Palabra que contiene la palabra objetivo de la
        //partida y array que contiene todas las palabras introducidas para la
        //correcta visualización
        Palabra [] palabras=new Palabra[6];
        Palabra objetivo=new Palabra();
        objetivo=palabraObjetivo(idioma);
        
        //escribimos en el fichero de estdadísticas la palabra objetivo
        estadisticas.adicion(" "+objetivo.toString());
        
        System.out.println("******Introducir palabras de 5 letras******");

        //emmpieza el bucle de la partida y sigue mientras no se hayan excedido
        //los intentos y no se haya encontrado la palabra objetivo
        while(!partidaGanada && intentos<6){
            Palabra palabraPartida=new Palabra();
            System.out.print("Intento "+(intentos+1)+"/6\n> ");
            while(Palabra.hayPalabras()){
                palabraPartida.lectura();
            }
            //bucle que verifica que la palabra tiene 5 letras y que está en el 
            //diccionario, con sus mensajes correspondientes
            while(palabraPartida.getNumeroCaracteres()!=5 || !Palabra.revisarPalabra(palabraPartida,idioma)){
                if(palabraPartida.getNumeroCaracteres()!=5){
                    System.err.println("Las palabras deben ser de 5 letras. Volver "
                        + "a introducir");
                    System.out.print("> ");
                    while(Palabra.hayPalabras()){palabraPartida.lectura();}
                }
                else{
                    System.err.println("Palabra inválida. Volver a introducir");
                    System.out.print("> ");
                    while(Palabra.hayPalabras()){palabraPartida.lectura();}
                }
            }
            
            //almacenamos la palabra introducida en el array de palabras de la 
            //partida y la escribimos en el fichero
            palabras[intentos]=palabraPartida;
            estadisticas.adicion(" "+palabraPartida.toString());
            
            //si la palabra es la que buscamos mostramos el mensaje correspondiente
            if(Palabra.iguales(objetivo, palabraPartida)){
                partidaGanada=true;
                Palabra.visualizacionPalabra(palabras,objetivo,intentos);
                System.out.println("Enhorabuena!! Has adivinado la palabra en "+
                        (intentos+1)+" intentos!");
            }
            else{
                Palabra.visualizacionPalabra(palabras,objetivo,intentos);
            }
            
            intentos++;
        }
        //si la partida ha acabado por el numero de intentos mostramos el 
        //mensaje correspondiente
        if(partidaGanada==false){
            System.out.println("La palabra era *"+objetivo.toString()+
                    "*. Buena suerte en la próxima!");
        }
        //introducimos un salto de linea en el fichero
        Estadisticas.escribirEstadisticas(estadisticas);
        
    }
    
    private String eleccionIdioma() {
        String idioma="";
        int eleccion;
        System.out.print("------ Elegir idioma -------\n"
                + "1. Castellano\n2. Catalán\n3. Inglés\n> ");
        eleccion=LT.readInt();
        switch(eleccion){
            case 1-> idioma="castellano";
            case 2-> idioma="catala";
            case 3-> idioma="english";
        }
        return idioma;
    }

}
