public class Literal {
    final String nombre;
    //Verificamos el estado del valor de la literal, Falso 0, Verdadero 1, Nose 2  
    int valor = 2; 
    Literal negacion;
    
    public Literal(String name) {
        nombre = name;
       
        if(name.charAt(0)!='!'){ negacion = new Literal("!"+name); }
        else{ negacion = null; }
    }
    
    public String getName(){ 
        return nombre; 
    }
    
    public int getValor(){ 
        return valor; 
    }
    
    public int getNOt(){ 
        return negacion.valor; 
    }
    
    public void setValor(int nuevoValor){
        if(nuevoValor>2 || nuevoValor<0){
            System.out.println("Valor no aceptado para atomo");
            valor = 2;
        }
        else{
            //Valor falso para el atomo
            if(nuevoValor == 0){
                System.out.println("Valor de "+nombre+" cambiado a (falso)");
                valor = 0;
            }   
            //Valor verdadero para el atomo
            if(nuevoValor == 1){
                valor = 1;
            }
            //Valor de nose para el atomo
            if(nuevoValor == 2){
                System.out.println("Valor de "+nombre+" cambiado a (no se)");
                valor = 2;
            }
        }
    }
    
     public void setValorNegado(int nuevoValor){
        if(nuevoValor>2 || nuevoValor<0){
            System.out.println("Valor no aceptado para atomo");
            negacion.valor=2;
        }
        else{
            if(nuevoValor == 0){
                System.out.println("Valor de !"+nombre+" cambiado a (falso)");
                negacion.valor=0;
            }   
            if(nuevoValor == 1){
                System.out.println("Valor de !"+nombre+" cambiado a (verdad)");
                negacion.valor = 1; 
            }
            if(nuevoValor == 2){
                System.out.println("Valor de !"+nombre+" cambiado a (no se)");
                negacion.valor=2;
            }
        }
    }
}