package com.example.erickantonio.proyecto3erparcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

public class Gestion extends Activity
{
    private Partida partida;
    private int dificultad;
    private int FPS = 30;
    private int botes;
    private Handler temporizador; //Manejador para el uso de los hilos

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        botes = 0;
        Bundle extras = getIntent().getExtras();
        dificultad = extras.getInt("DIFICULTAD"); //Uso de los hilos en dificultad
        partida = new Partida(getApplicationContext(), dificultad); //Instanciar el metodo partida
        setContentView(partida); //Comenzar la partida
        temporizador = new Handler();
        temporizador.postDelayed(elhilo,2000); //Timepo de retardo en comenzar la partida
    }

    //Hilo
    private Runnable elhilo = new Runnable()
    {
        @Override
        public void run()
        {
            if(partida.movimientoBola()){ fin(); }
            else
            {
                partida.invalidate(); //Elimina el contenido de imageView y llama de nuevo a onDrawable
                temporizador.postDelayed(elhilo, 1000/FPS); //Tiempo de retraso
            }
        }
    };

    //Detectar donde esta tocando el usuario
    public boolean onTouchEvent(MotionEvent evento)
    {
        int x = (int)evento.getX();
        int y = (int)evento.getY();
        if(partida.toque(x,y)) botes++; //Incrementamos el score del jugador
        return false;
    }

    //Metodo para destruir la actividad actual
    public void fin()
    {
        temporizador.removeCallbacks(elhilo);
        Intent in = new Intent();
        in.putExtra("PUNTUACION", botes); //Guardamos la informacion del score
        setResult(RESULT_OK, in); //Le decimos que se finalizo con exito la actividad
        finish();
    }
}
