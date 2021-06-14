import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Automata  {
    int actual;
    int linea;
    int parentesis = 0;
    String texto;
    Set Variables = new HashSet();
    boolean error = false;
    String letraInicial = "[A-Z]";
    String variable = "[A-Za-z1-9_]";
   
    public Set<String> Start(String atomo){
        parentesis = 0;
        texto = atomo;
        actual = 0;
        int valor = 0;

        if(Pattern.matches(letraInicial,String.valueOf(texto.charAt(actual)))){
            valor = 1;
        }      
        if(texto.charAt(actual) == '!'){
            valor = 2;
        }
        if(texto.charAt(actual) == '('){
            valor = 3;
        }       
        actual++;
           
        switch(valor){   
            //Verificamos si al inicio del atomo no hay una mayuscula
            case 1:
                variable();                 
            break;
            //Verificamos si despues del conector negacion no se encuentra un atomo
            case 2:
                negacion();
            break;
            //Verificamos si despues de abrir un parentesis no se encuentra un atomo    
            case 3:
                abrirParentesis();
            break;
            //Verificamos si no se encuentra un atomo iniciado correctamente
            case 0:    
                System.out.println("Error sintaxis en la posicion: "+actual+", del atomo: "+atomo+"\n"
                        +"Solamente puede nombrarse a un atomo si la primera letra es mayuscula");
                Variables = null;    
            break;                
        }
        
        if(parentesis==0 || Variables==null){
            return Variables;   
        }
        else{
            System.out.println("Error sintaxis en la posicion: "+actual+", del atomo: "+atomo+"\n"
                    +"Falta cerrar o abrir un parentesis");
            return null;   
        }  
    }
   
    //Verificar el estado de la variable
    public void variable(){
        String atomo = String.valueOf(texto.charAt(actual-1));

        while(actual <= texto.length()-1){
            if(Pattern.matches(variable,String.valueOf(texto.charAt(actual)))){
                atomo += String.valueOf(texto.charAt(actual));
                actual++;
            }
            else{ break;}
        }
        
        Variables.add(atomo);
        if(actual>texto.length()-1){ return; }
        
        int valor = 0;
        if(texto.charAt(actual) == '&'){
            valor = 1;
        }        
        if(texto.charAt(actual) == '|'){
            valor = 2;
        }
        if(texto.charAt(actual) == '-'){
            valor = 3;
        }        
        if(texto.charAt(actual) == '<'){
            valor = 4;
        }
        if(texto.charAt(actual) == ')'){
            valor = 5;
        }
        actual++;

        switch(valor){
            //Verificamos el estado de la conjuncion '&'
            case 1:
                conjuncion();
            break;
            //Verificamos el estado de la disyuncion '|'    
            case 2:
                disyuncion();
            break;
            //Verificamos el estado de la implicacion '->'
            case 3:
                implicacion();
            break;
            //Verificamos el estado de la doble implicacion '<->'
            case 4:// doble implicacion
                dobleimplicacion ();
            break;
            //Verificamos los parentesis a cerrar
            case 5:
                cerrarParentesis();
                break;
            //Verificamos el estado de la negacion '!'
            case 0:
                System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                        +"Se encontro una negacion antes o despues del conector");
                Variables =null;
            break;
        }
    }
    
    public void abrirParentesis(){
        parentesis++;
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                    +"No se encontro un atomo despues de abrir parentesis");
            Variables = null;
            return;
        }
        else{ operador(); }
    }
    
    public void cerrarParentesis(){
        parentesis--;
        if(actual > texto.length()-1)
            return;
        
        int valor=0;
        if(texto.charAt(actual) == '&'){
            valor = 1;
        }        
        if(texto.charAt(actual) == '|'){
            valor = 2;
        }
        if(texto.charAt(actual) == '-'){
            valor = 3;
        }        
        if(texto.charAt(actual) == '<'){
            valor = 4;
        }
        if(texto.charAt(actual) == ')'){
            valor = 5;
        }
        actual++;
           
        switch(valor){
            //Verificamos el estado de la conjuncion '&'
            case 1:
                conjuncion();
            break;
            //Verificamos el estado de la disyuncion '|'
            case 2:
                disyuncion();
            break;
            //Verificamos el estado de la implicacion '->'
            case 3:
                implicacion();
            break;
            //Verificamos el estado de la doble implicacion '<->'
            case 4:
                dobleimplicacion();
            break;
            //Verificamos los parentesis a cerrar
            case 5:// Parentesis
                cerrarParentesis();
            break;
            //Verificamos cualquier otro error que se puediera sucitar
            case 0:
                System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo "+texto+"\n"
                        +"Error desconocido");
                Variables = null; 
            break;        
        }
    }
       
    public void negacion(){
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                    +"No se encontro un atomo despues de la negacion '!'");
            Variables = null;
            return;
        }
        else{ operador(); }
    }
   
    public void conjuncion(){
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                    +"No se encontro un atomo despues del conector '&'");
            Variables = null;
            return;
        }
        else{ operador(); }
    }
   
    public void disyuncion(){
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                    +"No se encontro un atomo despues del conector '|'");
            Variables = null;
            return;
        }
        operador();
    }
    
    public void implicacion(){
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo "+texto+"\n"
                    +"El conector implicacion se encuentra incompleto '->'");
            Variables = null;
            return;
        }
        else{
            if(texto.charAt(actual) == '>'){
                actual++;
                operador();
            }else{
                System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo "+texto+"\n"
                        +"El conector implicacion se encuentra incompleto '->'");
                Variables =null;
            }
        }
    }
   
    public void dobleimplicacion (){
        if(actual > texto.length()-1){
            System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                    +"El conector doble implicacion se encuentra incompleto '<->'");
            Variables = null;
            return;
        }
        else{
            if(texto.charAt(actual) == '-'){
                actual++;
                
                if(texto.charAt(actual) == '>'){
                    actual++;
                    operador();
                }
                else{
                    System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                            +"El conector doble implicacion se encuentra incompleto '<->'");
                    Variables = null;
                }
            }
            else{
                System.out.println("Error sintaxis en la posicion: "+(actual)+", del atomo: "+texto+"\n"
                        +"El conector doble implicacion se encuentra incompleto '<->'");
                Variables = null;
            }
        }
    }
   
    public void operador(){
        int valor = 0;
        if(Pattern.matches(letraInicial,String.valueOf(texto.charAt(actual)))){
            valor = 1;
        }
        if(texto.charAt(actual)=='!'){
            valor = 2;
        }
        if(texto.charAt(actual)=='('){
            valor = 3;
        }
        actual++;

        switch(valor){
            //Verificamos el estado del atomo
            case 1:
                variable();
            break;
            //Verificamos el estado de la negacion '!'
            case 2:
               negacion();
            break;
            //Verificamos los parentesis al abrir que se encuentre un atomo
            case 3:
                abrirParentesis();
            break;
            //Verificamos si se encuentra otro conector enseguida de otro
            case 0:
                System.out.println("Error sintaxis en la posicion: "+(actual-1)+", del atomo: "+texto+"\n"
                        +"Se encontro doble conector antes del siguiente atomo");
                Variables = null;
            break;
        }        
    }
}