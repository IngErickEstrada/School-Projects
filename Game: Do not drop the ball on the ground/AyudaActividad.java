package com.example.erickantonio.proyecto3erparcial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AyudaActividad extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_actividad);
    }

    //Metodo para el boton de volver
    public void volver(View view){ onBackPressed(); }
}
