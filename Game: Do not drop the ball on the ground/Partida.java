package com.example.erickantonio.proyecto3erparcial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class Partida extends ImageView
{
    private int acel;
    private Bitmap pelota, fondo;
    private int tam_pantX, tam_pantY, posX, posY, velX, velY;
    private int tamPelota;
    boolean pelota_sube;

    //Metodo de partida
    public Partida(Context contexto, int nivel_dificultad)
    {
        super(contexto);
        WindowManager manejador_ventana=(WindowManager) contexto.getSystemService(Context.WINDOW_SERVICE);
        Display pantalla=manejador_ventana.getDefaultDisplay();
        Point maneja_coord=new Point(); //Integrar dos coordenadas x, y
        pantalla.getSize(maneja_coord); //determina el tamaño de la pantalla

        //Almacenar el tamaño de la pantalla
        tam_pantX=maneja_coord.x;
        tam_pantY=maneja_coord.y;

        //Construir un layout programatico
        BitmapDrawable dibujo_fondo=(BitmapDrawable) ContextCompat.getDrawable(contexto, R.drawable.paisaje_1);
        fondo=dibujo_fondo.getBitmap(); //Dibujar el fondo en la actividad
        fondo=Bitmap.createScaledBitmap(fondo, tam_pantX, tam_pantY, false); //Crear un escalado del fondo

        BitmapDrawable objetoPelota=(BitmapDrawable)ContextCompat.getDrawable(contexto, R.drawable.pelota_1);
        pelota=objetoPelota.getBitmap(); //Dibujar la pelota en la actividad
        tamPelota=tam_pantY/3; //Declarar el tamaño de la pelota dentro de la pantalla
        pelota=Bitmap.createScaledBitmap(pelota, tamPelota, tamPelota, false);
        posX=tam_pantX/2-tamPelota/2;
        posY=0-tamPelota; //Declarar que la pelota puede salir de las posiciones x, y
        acel=nivel_dificultad*(maneja_coord.y/400); //Velocidad de la pelota
    }

    //Metodo de toque en pantalla
    public boolean toque(int x, int y)
    {
        //Determina donde hemos tocado en la pantalla
        if(y<tam_pantY/3) return false;
        if(velY<=0) return false;
        if(x<posX || x> posX+tamPelota) return false;
        if(y<posY || y>posY+tamPelota) return false;
        velY=-velY;
        double desplX=x-(posX+tamPelota/2);
        desplX=desplX/(tamPelota/2)*velY/2;
        velX+=(int)desplX;
        return true;
    }

    //Metodo movimiento de la pelota
    public boolean movimientoBola()
    {
        //Determinar la posicion de la pelota cuando realicemos un toque en la pelota
        if(posX<0-tamPelota)
        {
            posY=0-tamPelota;
            velY=acel;
        }
        posX+=velX;
        posY+=velY;

        //Nos devuelve un true si la partida a finalizado si no retorna un false
        if(posY>=tam_pantY) return true;
        if(posX+tamPelota<0 || posX>tam_pantX) return true;
        if(velY<0) pelota_sube=true;
        if(velY>0 && pelota_sube) { pelota_sube=false; }
        velY+=acel;
        return false;
    }

    //Metodo para pintar tanto el fondo como la pelota en diferentes posiciones
    protected void onDraw(Canvas lienzo)
    {
        lienzo.drawBitmap(fondo, 0,0, null);
        lienzo.drawBitmap(pelota, posX, posY, null);
    }
}