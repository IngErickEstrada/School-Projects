package com.example.erickantonio.proyecto2doparcial;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private int jugadores;
    private int[] casillas;
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el array casillas
        casillas = new int[9];
        casillas[0] = R.id.a1;  casillas[1] = R.id.a2;  casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;  casillas[4] = R.id.b2;  casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;  casillas[7] = R.id.c2;  casillas[8] = R.id.c3;
    }

    //Metodo para empezar a jugar seleccionando 1 jugador o 2 jugadores
    public void aJugar (View vista)
    {
        ImageView imagen;
        RadioGroup configDificultad = (RadioGroup) findViewById(R.id.configD);

        //Limpiamos el tablero cada vez que se pulse en un boton para jugar
        for(int cadacasilla:casillas)
        {
            imagen = (ImageView) findViewById((cadacasilla));
            imagen.setImageResource(R.drawable.casilla);
        }

        //Habilitamos la seleccion de 1 jugador o 2 jugadores
        jugadores = 1;
        if(vista.getId()==R.id.dosjug){ jugadores = 2; }

        //Habilitamos la dificultad a seleccionar entre facil, normal e imposible
        int id = configDificultad.getCheckedRadioButtonId();
        int dificultad = 0;
        if(id == R.id.normal){ dificultad = 1; }
        else if(id == R.id.imposible){ dificultad = 2; }

        //Mandamos la dificultad seleccionada a nuestro archivo Partida.java para empezar a jugar
        partida = new Partida(dificultad);

        //Una ves empeza la paritda inhabilitamos los botones para que no se puede empezar otra partida
        ((Button)findViewById(R.id.unjug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);
        ((Button)findViewById(R.id.dosjug)).setEnabled(false);
    }

    //Metodo para poder marcar las casillas con O o X
    public void toque (View vista)
    {
        if(partida == null){ return; } //Condicional para hacer que el usuario escoga primero una dificultad

        //Recorrer las casillas para saber cual se esta pulsando
        int casilla = 0;
        for(int i = 0; i<9; i++)
        {
            if(casillas[i] == vista.getId())
            {
                casilla = i;
                break;
            }
        }

        //Si la casilla donde va a dibujar esta ocupada returna al programa para no dibujar en ella
        if(partida.comprobar_casilla(casilla) == false){ return; }

        marca(casilla); //Nos dibuja una X al ser el primer jugador
        int resultado = partida.turno(); //Manda a llamar el metodo de turno para el jugador 1
        //Comprobamos si ha ganado algun jugador o ha quedado en empate
        if(resultado > 0)
        {
            termina(resultado);
            return;
        }
        if(jugadores == 1)
        {
            casilla = partida.IA(); //Manda a llamar el metodo inteligencia artificial
            while (partida.comprobar_casilla(casilla) != true){ casilla = partida.IA(); } //Comprueba la casilla marcada
            marca(casilla); //Manda un numero de casilla aleatorio para dibujar
            resultado = partida.turno(); //Manda a llamar el metodo de turno para el jugador 2
            if (resultado > 0){ termina(resultado); }
        }
    }

    //Metodo para especificar si ha ganado el usuario, la maquina o termino en empate
    private void termina(int resultado)
    {
        if(resultado == 1)
        {
            Toast toast = Toast.makeText(this, "¡The Win X!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else if(resultado == 2)
        {
            Toast toast = Toast.makeText(this, "¡The Win O!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(this, "¡Tie!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

        partida = null; //Mandamos a terminar la partida
        //Volvemos habilitar los botones para empezar una nueva partida
        ((Button)findViewById(R.id.unjug)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);
        ((Button)findViewById(R.id.dosjug)).setEnabled(true);
    }

    //Metodo para marcar la casilla con un O o una X
    private void marca (int casilla)
    {
        //Si el usuario inicia una partida contra la maquina se le asignara una X al usuario y un O a la maquina
        ImageView imagen = (ImageView) findViewById(casillas[casilla]);

        if(partida.jugador == 1){ imagen.setImageResource(R.drawable.aspa); }
        else{ imagen.setImageResource(R.drawable.circulo); }
    }
}
