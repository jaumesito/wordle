/*
Programa partida que se utilizará para la realización de partidas
 */
package wordle;

public class partida {
    
    public static void main(String [] args) throws Exception {
        new partida().metodoPrincipal();
    }
    
    private void metodoPrincipal() throws Exception {
        //variable booleana que indica que la partida se sigue jugando
        boolean continua=true;
        //variable int modoJuego que indica qué modo de juego elige el usuario
        int modoJuego;
        
        Wordle partida=new Wordle();
        
        //visualización mensaje de bienvenida
        System.out.println("Bienvenido a Wordle.");
        
        //bucle que muestra las opciones del juego
        while(continua) {
            //mensaje que pide al usuario próxima acción
            System.out.print("\nMenú principal:\n"
                    + "1. Jugar partida de Wordle.\n"
                    + "2. Ver las estadísticas de las partidas.\n"
                    + "3. Salir.\n"
                    + "Introduce la opción que quieras: ");
            modoJuego=LT.readInt();
            System.out.println();
            //switch para todos los casos
            switch(modoJuego){
                case 1 -> partida.jugarPartida();
                case 2 -> Estadisticas.verEstadisticas();
                case 3 -> continua=false;
            }
        }
        
        //visualización mensaje despedida
        System.out.println("¡Hasta luego!");
    }
    
    
    
}
