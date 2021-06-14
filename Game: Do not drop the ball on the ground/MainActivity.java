package com.example.erickantonio.proyecto3erparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    int record;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo para que se muestre texto en el boton de ayuda
    public void ayuda(View vista)
    {
        Intent intencion = new Intent(this, AyudaActividad.class);
        startActivity(intencion);
    }

    //Metodo para seleccionar la dificultad
    public void dificultad(View vista)
    {
        String dific = (String)((Button)vista).getText(); //Almacenamos el texto pulsado de dificultad
        int dificultad = 1;
        if(dific.equals(getString(R.string.medio))) dificultad = 2; //Aumentar la velocidad de la dificultad
        if(dific.equals(getString(R.string.dificil))) dificultad = 3; //Aumentar la velocidad de la dificultad

        Intent in = new Intent(this, Gestion.class);
        in.putExtra("DIFICULTAD", dificultad);
        //startActivity(in);
        startActivityForResult(in,1); //Peticion para empezar una activity
    }

    //Metodo para guardar el score del jugador
    protected void onActivityResult(int peticion, int codigo, Intent puntuacion)
    {
        if(peticion != 1 || codigo != RESULT_OK) return;
        int resultado = puntuacion.getIntExtra("PUNTUACION",0);
        //Si el resultado de la partida es mayor al score actual nos guarda el score nuevo
        if(resultado > record)
        {
            String puntuacion_partida = "Puntuación: " + resultado;
            Toast toast = Toast.makeText(this, puntuacion_partida, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

            record = resultado; //Guardar el record en la variable resultado
            TextView caja = (TextView)findViewById(R.id.record);
            caja.setText("Record: " + record);
            guardarrecord();
        }
        //Si no, nos muestra el puntaje alcanzado
        else
        {
            String puntuacion_partida = "Puntuación: " + resultado;
            Toast toast = Toast.makeText(this, puntuacion_partida, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    //Mandar a llamar metodos
    public void onResume()
    {
        super.onResume();
        leerecord();
    }

    //Metodo para guardar el record
    private void guardarrecord()
    {
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mieditor = datos.edit();
        mieditor.putInt("RECORD", record);
        mieditor.apply();
    }

    //Metodo para leer el record
    private void leerecord()
    {
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        record = datos.getInt("RECORD", 0);
        TextView caja = (TextView)findViewById(R.id.record);
        caja.setText("Record: " + record);
    }
}