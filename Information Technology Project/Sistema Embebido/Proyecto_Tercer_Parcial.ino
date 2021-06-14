/*
 * Proyecto Tercer Parcial - Sistema Embebido
 * Erick Antonio Estrada Vargas 17110079 8°M
 * Materia: Tecnologias Emergentes
 * Profesor: Jose Francisco Perez Reyes
*/

//Librerias a utilizar
#include "pitches.h"

// Definimos los pines de nuestros leds, el motor DC
// y la alarma junto con el sensor de movimiento
int alarma = 4, trig = 7, echo = 6;
int encendido = 3, apagado = 2;

int recamara1 = 29, recamara2 = 33, bano = 37;
int cocina = 41, comedor = 45, sala = 49;
int cochera = 53;

int enable = 9, giroA = 10, giroB = 11;

void setup() {
  pinMode(recamara1, OUTPUT);
  pinMode(recamara2, OUTPUT);
  pinMode(bano, OUTPUT);
  pinMode(sala, OUTPUT);
  pinMode(cocina, OUTPUT);
  pinMode(comedor, OUTPUT);
  pinMode(cochera, OUTPUT);

  pinMode(alarma, OUTPUT);
  pinMode(encendido, OUTPUT);
  pinMode(apagado, OUTPUT);
  pinMode(trig, OUTPUT);
  pinMode(echo, INPUT);
  digitalWrite(apagado, HIGH);

  pinMode(enable, OUTPUT);
  pinMode(giroA, OUTPUT);
  pinMode(giroB, OUTPUT);
  
  Serial.begin(9600);
}

void loop() {  
  if (Serial.available()){
    char c = Serial.read();
    long duracion, distancia;
    float senVal; int tono;

    //Inicializamos el sensor de movimiento
    digitalWrite(trig, LOW); delay(2);
    digitalWrite(trig, HIGH); delay(5);
    digitalWrite(trig, LOW);

    //Recamara Principal
    if (c == 'P'){ digitalWrite(recamara1, HIGH); } 
    else if(c == 'Q'){ digitalWrite(recamara1, LOW); }

    //Recamara Secundaria
    if (c == 'S'){ digitalWrite(recamara2, HIGH); } 
    else if(c == 'T'){ digitalWrite(recamara2, LOW); }

    //Baño
    if (c == 'B'){ digitalWrite(bano, HIGH); } 
    else if(c == 'A'){ digitalWrite(bano, LOW); }

    //Sala
    if (c == 'E'){ digitalWrite(sala, HIGH); } 
    else if(c == 'F'){ digitalWrite(sala, LOW); }

    //Cocina
    if (c == 'C'){ digitalWrite(cocina, HIGH); } 
    else if(c == 'D'){ digitalWrite(cocina, LOW); }

    //Comedor
    if (c == 'M'){ digitalWrite(comedor, HIGH); } 
    else if(c == 'N'){ digitalWrite(comedor, LOW); }

    //Cochera
    if (c == 'J'){ digitalWrite(cochera, HIGH); } 
    else if(c == 'K'){ digitalWrite(cochera, LOW); }

    //Alarma con sensor ultrasonico
    if (c == 'H'){ 
      digitalWrite(apagado, LOW); 
      digitalWrite(encendido, HIGH);
      
      duracion = pulseIn(echo, HIGH);
      distancia = ( duracion / 2 ) * 0.0343;      
      
      if ( distancia < 50 ){
        for(int x = 0; x < 180; x++){
          senVal = ( sin ( x * ( 3.1412 / 180 ) ) );
          tono = 2000 + ( int ( senVal * 1000 ) );
          tone(alarma, tono); 
          delay(2);
        }
      }
    }
    else if(c == 'L'){
      digitalWrite(apagado, HIGH); 
      digitalWrite(encendido, LOW);
      noTone(alarma);
    }

    //Control sobre motor DC giro
    //Velocidad 1
    if (c == 'V' || c == 'X'){
      digitalWrite(enable, 32);
      digitalWrite(giroA, HIGH);
      digitalWrite(giroB, LOW);
    }
    //Velocidad 2
    else if (c == 'Y'){
      digitalWrite(enable, 128);
      digitalWrite(giroA, HIGH);
      digitalWrite(giroB, LOW);
    }
    //Velocidad 3
    else if (c == 'Z'){
      digitalWrite(enable, 255);
      digitalWrite(giroA, HIGH);
      digitalWrite(giroB, LOW);
    }
    //Apagar motor
    else if(c == 'U'){
      digitalWrite(enable, 0);
      digitalWrite(giroA, LOW);
      digitalWrite(giroB, LOW);
    }
  }
}
