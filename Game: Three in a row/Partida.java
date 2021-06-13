package com.example.erickantonio.proyecto2doparcial;

import java.util.Random;

public class Partida
{
    public final int dificultad;
    public int jugador;
    private int [] casillas;
    private final int [][] combinaciones = {{0, 1, 2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Partida(int dificultad)
    {
        this.dificultad = dificultad;
        jugador = 1;

        //Despues de iniciar la partida el array casillas valdra 0 en todas las posiciones
        casillas = new int[9];
        for(int i=0; i<9; i++){ casillas[i] = 0; }
    }

    //Metodo para evaluar la casillas si esta libre o si esta ocupada
    public boolean comprobar_casilla(int casilla)
    {
        if(casillas[casilla] != 0){ return false; }
        else{ casillas[casilla] = jugador; }
        return true;
    }

    //Metodo para elegir el turno del jugador y la maquina o contra otro jugador
    public int turno()
    {
        boolean empate = true;
        boolean ultimo_movimiento = true;

        //Recorremos el array combinaciones para poder saber cuales casillas ya se encuentran ocupadas
        for(int i=0; i<combinaciones.length; i++)
        {
            for(int pos:combinaciones[i])
            {
                //Si algun jugador consigue 3 lineas iguales ultimo_movimiento = false
                if(casillas[pos] != jugador){ ultimo_movimiento = false; }

                //Si al terminar de recorrer las casillas hay algun ganador entonces empate es falso
                if(casillas[pos] == 0){ empate = false; }
            }
            //Devuelve el juagor que consigue hacer las 3 lineas iguales
            if(ultimo_movimiento){ return jugador; }
            ultimo_movimiento = true;
        }

        //Si empate es true nos retorna un 3 donde indicamos que la partida termino en empate
        if(empate){ return 3; }

        jugador ++;
        if(jugador >2){ jugador = 1; }
        return 0; //Si no encuentra algun ganador o empate, sera entonces ninguna de las anteriores y retornamos un 0
    }

    //Metodo para devolver la casilla clabe para hacer tres en raya
    public int dosenraya(int jugador_en_turno)
    {
        int casilla = -1; //Iniciamos la variable en una posicion que no exista
        int cuantas_lleva = 0; //Variable para evaluar si el jugador lleva 2 en raya

        for(int i=0; i<combinaciones.length; i++)
        {
            for(int pos:combinaciones[i])
            {
                //Si se encuentran dos posiciones marcadas haciendo dos en raya nos incrementa la variable cuantas_lleva
                if(casillas[pos] == jugador_en_turno){ cuantas_lleva ++; }
                //Si no se encuentra dos posiciones nuestro valor entonces cera 0
                if(casillas[pos] == 0){ casilla = pos; }
            }
            //Si se cumple la condicion de que haya dos posiciones marcadas y ademas casilla encuentra una casilla
            //nos retornada la casilla que se encuentre vacia para completar el tres en raya.
            if(cuantas_lleva == 2 && casilla != -1){ return  casilla; }

            //Reseteamos los valores de las variables
            casilla = -1;
            cuantas_lleva = 0;
        }
        return -1;
    }

    //Metodo de inteligencia artificial para jugar contra la maquina
    public int IA()
    {
        int casilla;

        casilla = dosenraya(2); //Evaluamos si la maquina puede hacer 2 en raya y guardamos el valor
        if(casilla != -1){ return casilla; }

        //Dificultad Normal, la maquina puede taparnos si queremos hacer 3 en raya
        if(dificultad >0)
        {
            casilla = dosenraya(1); //Evaluamos si el usuario puede hacer 2 en raya y guardamos el valor
            if(casilla != -1){ return casilla; }
        }

        //Dificultad Imposible, la maquina inhabilita la posibilidad de poder ganar el juego marcando las esquinas
        if(dificultad == 2)
        {
            if(casillas[0] == 0){ return 0; }
            if(casillas[2] == 0){ return 2; }
            if(casillas[6] == 0){ return 6; }
            if(casillas[8] == 0){ return 8; }
        }

        //Genera una casilla al azar
        Random maquina = new Random();
        casilla = maquina.nextInt(9);
        return casilla;
    }
}
