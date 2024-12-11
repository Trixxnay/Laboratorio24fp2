import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random r=new Random();
        Mapa mapa = new Mapa(10, 10);
        ObjectOutputStream fileOut;
        ObjectInputStream fileInObject;
        PrintWriter fileOut1;
        DataInputStream dataIn;
        
        try{
            fileOut=new ObjectOutputStream(new FileOutputStream("Mapa"));
            fileOut.writeObject(mapa);
            fileOut.close();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n");

        System.out.println("En un mundo donde las ambiciones de poder y gloria dominan los corazones de los gobernantes, "
                         + "los antiguos reinos han decidido enfrentar su destino en el campo de batalla.\n"
                         + "Desde las frías tierras de los Reinos Escandinavos, pasando por los férreos Reinos Anglo-Sajones y el noble Reino de Asturias,\n"
                         + "hasta los imponentes dominios del Imperio Bizantino, el Imperio Carolingio y el Reino de los Búlgaros, las tensiones han alcanzado un punto crítico.\n"
                         + "Cada reino busca consolidar su supremacía en un mundo dividido, utilizando estrategias militares, unidades especializadas y el dominio del territorio como sus principales armas.\n"
                         + "Las guerras han comenzado, y solo uno podrá reclamar la hegemonía sobre estas tierras conflictivas.");

        System.out.println("\n");

        String[] reinosSeleccionados = Reino.escogerReinos();
        
        
        try{
            fileOut1=new PrintWriter("Reinos");
            fileOut1.close();
            fileOut1=new PrintWriter(new FileWriter("Reinos",true));
            for (int i=0;i<reinosSeleccionados.length;i++){
                fileOut1.println(reinosSeleccionados[i]);
            }
            fileOut1.close();
            
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        Scanner fileIn;
        try{
            
            fileIn=new Scanner(new FileReader("Reinos"));
            System.out.println("\n\nJugador 1 ha elegido: " + fileIn.nextLine());
            
            System.out.println("------------------------------------------");

            System.out.println("Jugador 2 ha elegido: " + fileIn.nextLine());

            System.out.println("------------------------------------------");
            fileIn.close();
            
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        Ejercito ejercito1 = new Ejercito(reinosSeleccionados[0]);
        ejercito1.generarSoldados(r.nextInt(10)+1);

        Ejercito ejercito2 = new Ejercito(reinosSeleccionados[1]);
        ejercito2.generarSoldados(r.nextInt(10)+1);
        
        ejercito1.mostrarSoldados();
        System.out.println("------------------------------------------");

        ejercito2.mostrarSoldados();
        System.out.println("------------------------------------------");

        String territorio = Territorio.generarTerritorio();
        System.out.println("El territorio actual es: " + territorio);

        ejercito1.aplicarBeneficios(territorio);
        ejercito2.aplicarBeneficios(territorio);
        
        try{
            fileOut=new ObjectOutputStream(new FileOutputStream("Ejercito 1"));
            for (int i=0;i<ejercito1.getSoldados().size();i++){
                fileOut.writeObject(ejercito1.getSoldados().get(i));
            }
            fileOut.close();
            fileOut=new ObjectOutputStream(new FileOutputStream("Ejercito 2"));
            for (int i=0;i<ejercito2.getSoldados().size();i++){
                fileOut.writeObject(ejercito2.getSoldados().get(i));
            }
            fileOut.close();
        } catch (IOException e){
            System.out.println("Error :" + e.getMessage());
        }
        
        for (Soldado soldado : ejercito1.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        for (Soldado soldado : ejercito2.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }
        
        /*Actualizar Mapa*/
        try{
            fileOut=new ObjectOutputStream(new FileOutputStream("Mapa"));
            fileOut.writeObject(mapa);
            fileOut.close();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        JuegoDeBatalla juego = new JuegoDeBatalla(mapa, ejercito1, ejercito2);
        juego.iniciar();

        scanner.close();
    }
}
