//CLASE U OBJETO EMPLEADO (HIJA)
class cEmpleado: public cPersona
{
	//VARIABLES
	string Salario;
	string Comisiones;
	
	public:
	//METODOS
	cEmpleado();
	~cEmpleado(){};
	friend void agregartxt(cEmpleado *);
};

//METODOS DEL OBJETO
cEmpleado::cEmpleado() :cPersona() //CONSTRUCTOR SIMPLE
{
	Salario="4000";
	Comisiones="0";
}

//FUNCIONES QUE NO ESTAN DENTRO DEL OBJETO
void sobreescribirtxtEmpleado(int n) //ESTA FUNCION FUNCIONA IGUAL QUE LA DEL CLIENTE
{
	ifstream archivo;
	ofstream archivo2;
	int tecla;
	string buscar, modificar, contenido, linea;
	char c1[]="Empleados.txt", c2[]="Empleados2.txt";
	bool encontro=false, continuar=false;
	
	system("cls");	Titulo();
	
	archivo.open("Empleados.txt",ios::in);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE EL DATO DE LA PERSONA A MODIFICAR: ";
		getline(cin,buscar);
		if(n==1)	buscar="Nombre: "+buscar;
		if(n==2)	buscar="RFC: "+buscar;
		if(n==3)	buscar="Domicilio: "+buscar;
		if(n==4)	buscar="Telefono: "+buscar;
		while(getline(archivo,linea))	if(linea == buscar) encontro=true;
	}
	archivo.close();
	
	if(encontro == true)
	{
		archivo.open("Empleados.txt",ios::in);
		archivo2.open("Empleados2.txt",ios::out);
		
		if(archivo.fail() || archivo2.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"INGRESE EL NUEVO DATO: ";
			getline(cin,modificar);
			if(n==1)	modificar="Nombre: "+modificar;
			if(n==2)	modificar="RFC: "+modificar;
			if(n==3)	modificar="Domicilio: "+modificar;
			if(n==4)	modificar="Telefono: "+modificar;
			
			gotoxy (1, 16);	cout<<"ESTA SEGURO DE CONTINUAR CON EL REGISTRO ENTER CONTINUAR / ESC CANCELAR";
			tecla=getch();
			if(tecla==13)	continuar=true;
			if(tecla==27)	continuar=false;
			
			if(continuar==true)
			{
				while(getline(archivo,contenido))
				{
					if(contenido == buscar)	contenido=modificar;
					archivo2<<contenido<<endl;
				}
				archivo.close();
				archivo2.close();
				remove("Empleados.txt");
				rename(c2,c1);
			}
			else
			{
				sndPlaySound("Error.wav",0);
				archivo.close();
				archivo2.close();
				remove("Empleados2.txt");
			}
		}
	}
	else
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
		sndPlaySound("Error.wav",0);
		getch();
	}
}

void agregartxt(cEmpleado *E) //ESTA FUNCION ES AMIGA DEL OBJETO EMPLEADO YA QUE LO DEL PAGO Y LAS COMISIONES TAMBIEN SON PRIVADAS
{
	ofstream archivo;
	
	archivo.open("Empleados.txt",ios::app);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		//AQUI SE AGREGAN ESOS DOS DATOS GUARDADOS EN EL OBJETO CON EL CONSTRUCTOR
		archivo<<"Salario: "+E->Salario<<endl;
		archivo<<"Comisiones: "+E->Comisiones<<endl<<endl;
		archivo.close();
	}
}

int Menu_Mod_Empleado()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU MODIFICAR CLIENTE";
	gotoxy (2, 15);	cout<<"1.- NOMBRE";
	gotoxy (2, 17);	cout<<"2.- RFC";
	gotoxy (2, 19);	cout<<"3.- DOMICILIO";
	gotoxy (2, 21);	cout<<"4.- TELEFONO";
	gotoxy (2, 23);	cout<<"5.- REGRESAR";
		
		gotoxy(3,27);
    	cout<<"UTILICE LAS TECLAS DE SELECCION Y ENTER PARA SELECCIONAR UNA OPCION, ESC PARA REGRESAR";
		
		gotoxy (0, Cursor_Pos);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Verde);
		cout<<(char)175;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def);
		gotoxy(0,39);
		
		do
		{
			if(kbhit()) //kbhit() sirve para saber si se presiono alguna tecla
			{
				tecla = getch();
				
				switch(tecla)
				{
					case Tecla_Arriba:	if(Cursor_Pos >= 15) Cursor_Pos -= 2;
										if(Cursor_Pos <15) Cursor_Pos = 23;
										break;
					
					case Tecla_Abajo:	if(Cursor_Pos <= 23) Cursor_Pos += 2;
										if(Cursor_Pos >23) Cursor_Pos = 15;
										break;
					
					case Tecla_Enter:
                	{
                    	switch (Cursor_Pos)
                    	{
                        	case 15: return 1; break;
                        	case 17: return 2; break;
                        	case 19: return 3; break;
                        	case 21: return 4; break;
                        	case 23: return 5; break;
                    	}
                	}
                	break;
                	case Tecla_Esc:	return 5;	break;
				}
				
				for (int i = 0; i<13 ; i+=2)
            	{
                	gotoxy (0,15+i);
                	cout << (char) 0;
            	}
            	
            	gotoxy (0, Cursor_Pos);
		 		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Verde);
				cout<<(char)175;
				SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def);
				gotoxy(0,39);
				
			}
			
		}while(tecla != 27);
	return 5;
}

void main_mod_empleado()
{
	int X, Y;
	bool band=false;
	
	for(;band==false;)
	{
		switch (Menu_Mod_Cliente())
    	{
	       	case 1:	sobreescribirtxtEmpleado(1);	break;
           	case 2:	sobreescribirtxtEmpleado(2);	break;
        	case 3:	sobreescribirtxtEmpleado(3);	break;
       		case 4:	sobreescribirtxtEmpleado(4);	break;
	       	case 5: band=true;	break;
    	}
	}
}

int Menu_Empleado()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU EMPLEADOS";
	gotoxy (2, 15);	cout<<"1.- REGISTRAR";
	gotoxy (2, 17);	cout<<"2.- CONSULTAR POR NOMBRE";
	gotoxy (2, 19);	cout<<"3.- CONSULTAR POR CODIGO DE EMPLEADO";
	gotoxy (2, 21);	cout<<"4.- MODIFICAR";
	gotoxy (2, 23);	cout<<"5.- LISTA DE EMPLEADOS";
	gotoxy (2, 25);	cout<<"6.- REGRESAR";
	
		
		gotoxy(3,29);
    	cout<<"UTILICE LAS TECLAS DE SELECCION Y ENTER PARA SELECCIONAR UNA OPCION, ESC PARA REGRESAR";
		
		gotoxy (0, Cursor_Pos);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Verde);
		cout<<(char)175;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def);
		gotoxy(0,39);
		
		do
		{
			if(kbhit()) //kbhit() sirve para saber si se presiono alguna tecla
			{
				tecla = getch();
				
				switch(tecla)
				{
					case Tecla_Arriba:	if(Cursor_Pos >= 15) Cursor_Pos -= 2;
										if(Cursor_Pos <15) Cursor_Pos = 25;
										break;
					
					case Tecla_Abajo:	if(Cursor_Pos <= 25) Cursor_Pos += 2;
										if(Cursor_Pos >25) Cursor_Pos = 15;
										break;
					
					case Tecla_Enter:
                	{
                    	switch (Cursor_Pos)
                    	{
                        	case 15: return 1; break;
                        	case 17: return 2; break;
                        	case 19: return 3; break;
                        	case 21: return 4; break;
                        	case 23: return 5; break;
                        	case 25: return 6; break;
                    	}
                	}
                	break;
                	case Tecla_Esc:	return 6;	break;
				}
				
				for (int i = 0; i<13 ; i+=2)
            	{
                	gotoxy (0,15+i);
                	cout << (char) 0;
            	}
            	
            	gotoxy (0, Cursor_Pos);
		 		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Verde);
				cout<<(char)175;
				SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def);
				gotoxy(0,39);
				
			}
			
		}while(tecla != 27);
	return 6;
}

void main_empleado()
{
	int X, Y, n;
	bool band=false;
	
	//SE CREAN LO OBJETOS COMO PUNTEROS, YA QUE SE VA A UTILIZAR MEMORIA DINAMICA
	cEmpleado *objEmpleado;
	cPersona *objPersonaE;
	
	for(;band==false;)
	{
		switch (Menu_Empleado())
    	{
	       	case 1:	objPersonaE=new cPersona();
					n=crearTXT(objPersonaE,"Empleados.txt"); //SI MI CREARTXT ME RETORNA UN CERO ES POR QUE SI SE HIZO EL REGISTRO
					
					if(n==0) //ENTONCES AQUI EN ESTE IF SE VERIFICA SI SE HIZO O NO
					{
						//SI SE HIZO ENTONCES SE LLAMA A LA FUNCION AGREGARTXT PARA AGREGAR LOS DATOS FALTANTES QUE SON PAGO Y COMISIONES
						objEmpleado=new cEmpleado();
						agregartxt(objEmpleado);
						delete objEmpleado;
					}
					
					delete objPersonaE;
					break;
           	case 2:	objEmpleado=new cEmpleado();	objEmpleado->Consultar_Nombre("Empleados.txt");	delete objEmpleado;	break;
        	case 3:	objEmpleado=new cEmpleado();	objEmpleado->Consultar_Codigo("Empleados.txt");	delete objEmpleado;	break;
       		case 4:	main_mod_empleado();	break;
			case 5:	objEmpleado=new cEmpleado();	objEmpleado->Mostrar_todos("Empleados.txt");	delete objEmpleado; break;
       		case 6:	band=true; break;
    	}
	}
}
