import java.util.Arrays;

public class Reglas {
    Literal[] Antecedentes;
    Literal  Consecuencias;
    
    public Reglas(Literal[] antecedentes, Literal consecuencias) {
        Antecedentes = new Literal[antecedentes.length];
        for(int i=0; i<=Antecedentes.length-1; i++){
            Antecedentes[i] = antecedentes[i];
        }
        Consecuencias = consecuencias;
        infoReglas();        
    }
    
    public void infoReglas(){
        System.out.print("Antecedentes: ");
            for(int i=0; i<=Antecedentes.length-1; i++){
                System.out.print(Antecedentes[i].nombre+" ");
            }
        System.out.println("\nConsecuencia: "+Consecuencias.nombre);
        System.out.println("");
    }
    
    public boolean resolve(){
        boolean nose = false;
        boolean verdad = true;
        
        for (int i=0; i<Antecedentes.length; i++) {
            if(Antecedentes[i].valor == 2){
                nose = true;
            }
            else if(!(Antecedentes[i].valor == 1))
                verdad = false;
        }
        if(!nose){
            System.out.print("");
            System.out.print("Se encontro que ");
            for(int i=0; i<=Antecedentes.length-1; i++){
                System.out.print(Antecedentes[i].nombre+" ");
            };
            System.out.println("son hechos, y se infirio que "+Consecuencias.nombre+" es un hecho. ¿Desea continuar?.");
            return true;
        }
        return false;
    }
}