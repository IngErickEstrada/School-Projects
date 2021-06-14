/*
    Erick Antonio Estrada Vargas 17110079 6°M
    Sistema Experto: Area Medica
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SistemaExperto {
    public static void main(String[] args) throws IOException {
        SistemaExperto se = new SistemaExperto();
    }

    public SistemaExperto() throws FileNotFoundException, IOException {
        String cadena = leerArchivo("C:/Users/ErickEstradaVargas/Desktop/SistemaExperto/Atomos.txt");
        cadena = cadena.replaceAll("\\s+", "");
        String[] texto = cadena.split(";");
        
        Automata automata = new Automata();
        Set<String> temporal = null;
        Set<String> variables = new HashSet();
        String[] arr;
        
        for(int i=0; i<=texto.length-1; i++){
           temporal = automata.Start(texto[i]);
           if(temporal == null ){ break; }
           temporal.forEach((element) -> { variables.add(element); });
        }
        
        if(temporal != null){
            //Mostramos las variables que hay en los atomos
            System.out.println("Variables: "+variables+"\n");
            Set atomos = new HashSet();
            variables.forEach((element) -> { atomos.add(new Literal((String) element)); });   
            
            //Juntamos todos los atomos de manera que sea una sola cadena con &
            cadena = "";
            for(int i = 0; i < texto.length; i++) {
                cadena += "("+texto[i]+")&";
            }            
            cadena = cadena.substring(0,cadena.length()-1);
            System.out.print("Formulas: "+cadena+"\n");
            System.out.println("No hay errores en las formulas bien formadas \n");
            
            //Literales para Foward Chaining
            Literal[] literalesArray = new Literal[variables.size()];
            String[] Aux = new String[variables.size()];
            int k = 0;
            for (Object i:variables){
                literalesArray[k] = new Literal(i.toString());
                k++;
            }
            
            /////////////Arbol binario/////////////
            Arbol tree = new Arbol(cadena); //Primer formula del arbol
            
            //InOrden
            //System.out.println(texto[0]);
            System.out.print("inOrden:   ");
            tree.inOrden();
            System.out.println("");
            
            //PosOrden
            System.out.print("postOrden: ");
            tree.postOrden();
            System.out.println("");
            
            //PreOrden
            System.out.print("preOrden:  ");
            tree.preOrden();
            System.out.println("\n");
            ///////////Fin Arbol binario///////////
            
            ////////Forma Normal Conjuntiva////////
            tree.formaNormal();
            String name = tree.inordenString("");
            System.out.println("Forma Normal Conjuntiva: "+name+"\n");
            
            /////////Reglas de Inferencia/////////
            String[] aux = name.split("&");
            String[][] literales = new String[aux.length][];
        
            for (int i=0; i<=aux.length-1; i++){
                literales[i] = aux[i].split("\\|");
            }
            System.out.println("Literales: "+Arrays.deepToString(literales));

            List<Literal> antecedentes; 
            Set<Reglas> reglas = new HashSet<>();
            String[] item;  
            Literal[] item2;
            Literal consecuente = null;
       
            for(int i=0; i<=literales.length-1; i++){
                for(int j=0; j<=literales[i].length-1; j++){
                    antecedentes = new ArrayList(Arrays.asList(literales[i]));
                    antecedentes.remove(literales[i][j]);
                    item = (String[]) antecedentes.toArray(new String[antecedentes.size()]);
                    antecedentes = null;
                    
                    //Negacion de antescedentes y vinculacion con literales
                    k = 0;
                    item2 = new Literal[item.length];
                    for(String items: item){
                        if(items.charAt(0) == '!'){
                            items = items.substring(1,items.length());
                        }
                        else{
                            items = "!"+items;
                        }
                        for (int l = 0; l < literalesArray.length; l++) {
                            if(literalesArray[l].negacion.nombre.equals(items)){
                                item2[k]=literalesArray[l].negacion;
                            }  
                            if(literalesArray[l].nombre.equals(items)){
                                item2[k]=literalesArray[l];
                            }
                        }
                        k++;
                    }
                    
                    //Consecuente 
                    for(int l = 0; l<literalesArray.length; l++) {
                        if(literalesArray[l].negacion.nombre.equals(literales[i][j])){
                                consecuente = literalesArray[l].negacion;
                        }
                        else if(literalesArray[l].nombre.equals(literales[i][j])){
                                consecuente = literalesArray[l];
                        }
                    }
                    reglas.add(new Reglas(item2,consecuente));
                }
            }
            
            //////Lectura y escritura de hechos//////
            cadena = leerArchivo("C:/Users/ErickEstradaVargas/Desktop/SistemaExperto/Hechos.txt"); 
            cadena = cadena.replaceAll("\\s+", "");
            String[] verdad=cadena.split(",");
            
            for (String hechos: verdad){
                for (Literal literalesArray1: literalesArray){
                    if (literalesArray1.nombre.equals(hechos)){
                        literalesArray1.setValor(1);
                    }
                    if(literalesArray1.negacion.nombre.equals(hechos)){
                        literalesArray1.setValorNegado(1);
                    }
                }
            }            
            forwardChaining(literalesArray,reglas);
        }
    }   

    public String leerArchivo(String archivo) throws FileNotFoundException, IOException {
        String cadena, texto = "";
        FileReader file = new FileReader(archivo);
        BufferedReader buffered = new BufferedReader(file);
        while((cadena = buffered.readLine())!=null) {
            texto += cadena;
        }
        buffered.close();        
        return texto;
    }
    
    //Motor de inferencia
    public void forwardChaining(Literal[] literalesArray, Set<Reglas> reglas){
        int cambio = reglas.size();
        Iterator<Reglas> i;
        boolean salir = true;
        //Itereador hasta que ya no se pueda disparar otro hecho
        do{ 
            i = reglas.iterator();
            cambio = reglas.size();
            
            while(i.hasNext()){
                Reglas actual = i.next();
                //Si ya se sabe que una negacion es un hecho quitalo
                if(actual.Consecuencias.nombre.charAt(0) != '!'){
                    //Cuando su valor no es 2 significa que ya se sabe que es hecho
                    if(actual.Consecuencias.negacion.valor != 2){
                        i.remove(); 
                        break;
                    }
                }
                //Cuando su valor no es 2 significa que ya se sabe que es hecho 
                if (actual.Consecuencias.valor != 2){
                    i.remove();  
                }
                else{
                    //Si todos sus antecedentes son hechos regresara verdad
                    if(actual.resolve()){
                        for(Literal literalesArray1: literalesArray){
                            if(literalesArray1.nombre.equals(actual.Consecuencias.nombre)||
                                    literalesArray1.negacion.nombre.equals(actual.Consecuencias.nombre)){
                                if(literalesArray1.nombre.equals(actual.Consecuencias.nombre)){ 
                                    literalesArray1.setValor(1);
                                }
                                if(literalesArray1.negacion.nombre.equals(actual.Consecuencias.nombre)){
                                    literalesArray1.negacion.setValor(1);
                                }
                                
                                Scanner entrada = new Scanner(System.in); 
                                System.out.println("Si desea continuar presiones 'ENTER' "
                                            + "de lo contrario escriba 'salir' y despues 'ENTER'");
                                String continuar = entrada.nextLine();  
                                    
                                if(!(continuar.equals(""))){
                                    salir = false;
                                    break;
                                }
                                else if(continuar.equals("SALIR ") || continuar.equals("salir ")){
                                    salir = true;
                                    break;
                                }    
                            }
                        }
                        i.remove();
                    }
                }    
            }
            
        }while(cambio>reglas.size() && salir);
        
        ArrayList<String>  hechos = new ArrayList<String>();
        //Obtener todas las literales que tengan valor 1 (literales que sean hechos)
        for(Literal literalesArray1: literalesArray){
            if(literalesArray1.valor == 1){
                hechos.add(literalesArray1.nombre);
            }
            if(literalesArray1.negacion.valor == 1){
                hechos.add(literalesArray1.negacion.nombre);
            }
        }
        System.out.print("Hechos:");
        for (String hechos1: hechos) {
            System.out.print(hechos1+",");
        }
        System.out.println("");   
    }
}