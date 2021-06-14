import java.util.regex.Pattern;

public class Arbol {
    String nodo;
    Arbol derecha=null;
    Arbol izquierda=null;
    boolean operando=false;
    boolean literal=false;
    String texto;
    String letraInicial="[A-Z]"; // son usados para expreciones regulares
    String variable = "-|<|&|[|]|>";
    
    public Arbol(String atomo){
        texto=atomo;
        
        if(!parentesisInesesarios()){  // quita parentesis inesesarios
            //System.out.println(texto);
        }
        // la fincion cut corta si puede cuando encuetra operandos fuera de parentesis ejm (A->Z)->(B|C) se vuelve (A->Z) ,->,(B|C) pero no podra cortar : !A, !(Z&B),H
        if(!cut()){  
            if(texto.charAt(0)=='!' ){// si el primero es una negacion entonses toma el primero y envia lo demas como su derecha
                kowalski("!","null"," "+texto.substring(1,texto.length()));
                nodo="!";
                operando=true;
                derecha=new Arbol(texto.substring(1,texto.length()));
            }
            if(Pattern.matches(letraInicial,String.valueOf(texto.charAt(0)))){ // solo puede entrar a este if cuando sea solo una variable sin parentesis
                kowalski(""+texto,"null","null");
                nodo=texto;
                literal=true;
            }   
        }
}
    public Arbol(String nodoact,Arbol i,Arbol d){
        nodo=nodoact;
        derecha=d;
        izquierda=i;
        if(nodo.equals("|") || nodo.equals("&") || nodo.equals("!") || nodo.equals("->")){
            operando=true;
        }else{
            literal=true;
        }

    }
    
    public boolean cut(){
        int corteInicial=0;// los cortes determinan donde empiesa el operando y donde termina para cortarlo con substring 
        int cortefinal=0;
        int parentesis=0;
        int posicion=0;
        boolean seguir=false;
        while(true){
            if(posicion<texto.length()-1){
                if(texto.charAt(posicion)=='(' ) parentesis++;
                if(texto.charAt(posicion)==')' ) parentesis--;
                if(Pattern.matches(variable,String.valueOf(texto.charAt(posicion))) && parentesis==0){
                    switch(texto.charAt(posicion)){
                        case '&':// aparentente & puede entrar al de la |    ps: java es estupido
                        case '|':
                            corteInicial=posicion;
                            cortefinal=posicion+1;
                            kowalski(texto.substring(corteInicial,cortefinal),texto.substring(0,corteInicial),texto.substring(cortefinal,texto.length()));
                            nodo=texto.substring(corteInicial,cortefinal); 
                            operando=true;
                            izquierda=new Arbol(texto.substring(0,corteInicial));
                            derecha=new Arbol(texto.substring(cortefinal,texto.length()));
                            return true;
                        case '-':// la razon por que estos no se les llama hasta el final es por que la disyuncion y la conjucion tienen mayor prioridad y sirve para que A->B&C sea corrado como A->B,&,C 
                            if(corteInicial==0){
                                corteInicial=posicion;
                                cortefinal=posicion+2;// se le suma 2 por que es el - y >
                                
                            }
                        case '<':
                            if(corteInicial==0){
                                corteInicial=posicion;
                                cortefinal=posicion+3;// se le suma 3 por que es el < ,- y >
                            }
                    }
                }
            }else{
                
                break;
            }
            posicion++;
        } 
        if(corteInicial!=0  ){// aqui es donde se definen los de derecha e izquierdapara -> y <-> si no se encontro | o & 
            kowalski(texto.substring(corteInicial,cortefinal),texto.substring(0,corteInicial),texto.substring(cortefinal,texto.length()));
            operando=true;
            nodo=texto.substring(corteInicial,cortefinal);  
            izquierda=new Arbol(texto.substring(0,corteInicial));
            derecha=new Arbol(texto.substring(cortefinal,texto.length()));
            return true;
        }
        return false;
    }
    
    
    public void kowalski(String A,String B, String C){
        /*
        System.out.println("Nodo "  +A);
        System.out.println("Izquierdo "  +B);
        System.out.println("Derecha "    +C);
        System.out.println(" " );
*/
    }
    

    @SuppressWarnings("empty-statement")
    public void formaNormal(){
        dobleImplicacion();
        Implicacion();
        passNegacion();
        noseque();
        //System.out.println("done" );
    
        
    }
    
    public void dobleImplicacion(){
        if(nodo.equals("<->")){
            nodo="&";
            Arbol tempDerecha=  new Arbol("->",derecha,izquierda);
            Arbol tempIzquierda=new Arbol("->",izquierda,derecha);
            izquierda=tempIzquierda;
            derecha=tempDerecha;
        }else{
            if(derecha!=null){
                derecha.dobleImplicacion();
            }
            if(izquierda!=null){
                izquierda.dobleImplicacion();
            }
        }
    }
    public void Implicacion(){
        if(nodo.equals("->")){
            nodo="|";
            izquierda=new Arbol("!",null,izquierda);
        }    
        if(derecha!=null){
            derecha.Implicacion();
        }
        if(izquierda!=null){
            izquierda.Implicacion();
        }
        
    }
    public void passNegacion(){
        if(nodo.equals("!")){
            if(derecha.literal){
                nodo="!"+derecha.nodo;
                literal=true;
                operando=false;
                derecha=null;
            }
            if(derecha!=null){
                if(derecha.nodo.equals("|") || derecha.nodo.equals("&") || derecha.nodo.equals("!")){
                    if(derecha.nodo.equals("!")){
                        nodo=derecha.derecha.nodo;
                        izquierda=derecha.derecha.izquierda;
                        derecha=derecha.derecha.derecha;
                    }else{
                        if(derecha.nodo.equals("&")){//AND 
                            nodo="|";
                            izquierda=new Arbol("!",null,derecha.izquierda);
                            derecha=new Arbol("!",null,derecha.derecha);
                            
                        }if(derecha.nodo.equals("|")){// OR
                            nodo="&";
                            izquierda=new Arbol("!",null,derecha.izquierda);
                            derecha=new Arbol("!",null,derecha.derecha);
                            
                        }
                    }
                }
            }
        }
        if(izquierda!=null){
            izquierda.passNegacion();
        } 
        if(derecha!=null){
            derecha.passNegacion();
            }

    }
    
    public void noseque(){
        Arbol tempDerecha,tempIzquierda,tempLiteral;
       if(derecha!=null && izquierda!=null && nodo.equals("|")){
            if(((derecha.literal && izquierda.nodo.equals("&")) || (derecha.nodo.equals("&") && izquierda.literal))){
                if((derecha.literal && izquierda.operando)){
                    tempDerecha=derecha;
                    derecha=izquierda;
                    izquierda=tempDerecha;
                }
                tempLiteral=izquierda;
                String operando=derecha.nodo;
                    
                izquierda=new Arbol(nodo,tempLiteral,derecha.izquierda);
                derecha=new Arbol(nodo,tempLiteral,derecha.derecha);
                nodo=operando;
            }
            if(derecha.nodo.equals("&") && izquierda.nodo.equals("&")){
                Arbol temp1=new Arbol("|",izquierda.izquierda,derecha.izquierda);
                Arbol temp2=new Arbol("|",izquierda.izquierda,derecha.derecha);
                Arbol temp3=new Arbol("|",izquierda.derecha,derecha.izquierda);
                Arbol temp4=new Arbol("|",izquierda.derecha,derecha.derecha);
                
                derecha=new Arbol("&",temp3,temp4);
                izquierda=new Arbol("&",temp1,temp2);
                
                nodo="&";
                
            }
        }
        if(derecha!=null)
            derecha.noseque();
        
        if(izquierda!=null)
            izquierda.noseque();
        
    }
    
    public void inOrden(){
        if(izquierda!=null)
            izquierda.inOrden();
        System.out.print(nodo);
        if(derecha!=null)
            derecha.inOrden();
    }
    
    public String inordenString(String arbol){
        if(izquierda!=null)
            arbol=izquierda.inordenString(arbol);
        arbol+=nodo;
        if(derecha!=null)
            arbol=derecha.inordenString(arbol);
        return arbol;
    }
    public void postOrden(){
        
        if(izquierda!=null)
            izquierda.postOrden();
        if(derecha!=null)
            derecha.postOrden();
        System.out.print(nodo);
    }
    public void preOrden(){
        System.out.print(nodo);
        if(izquierda!=null)
            izquierda.preOrden();
        
        if(derecha!=null)
            derecha.preOrden();
    }
    
    
    
    public boolean parentesisInesesarios(){
        int parentesis=0;
        int posicion=0;
        boolean salir=true;
        boolean retorno=false;
        while(salir){// lo hara varias veses en caso de que sea (((A))) y quite los 3
            posicion=0;
            parentesis=0;
            if(texto.charAt(0)=='(' && texto.charAt(texto.length()-1)==')'){ // checa si los extremos tienen parentesis 
                while(true){
                    if(posicion<texto.length()-1){
                        if(texto.charAt(posicion)=='(' ) parentesis++;
                        if(texto.charAt(posicion)==')' ) parentesis--;
                        
                        if(posicion+1<texto.length()-1 ) {
                            if(parentesis==0 ){ // si los patentesis llegan a balance osea 0 y hay algo despues se sale: (A->B)->(B) al detectar la implicacion  se sale 
                                salir=false;
                                break;
                            }    
                        }else{
                            texto=texto.substring(1,texto.length()-1);
                            //System.out.println("new "    +texto);
                            retorno=true;
                        }
                    }else{
                        break;
                    }
                    posicion++;
                }   
            }else{
                salir=false;
            }  
        }     
        return retorno;
    }
    
    
    
}