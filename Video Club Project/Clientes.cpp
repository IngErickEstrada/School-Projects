//CLASE U OBJETO CLIENTE (HIJA)
class cCliente: public cPersona
{
	public:
	//METODOS
	cCliente();
	~cCliente(){};
};

//METODOS DEL OBJETO
cCliente::cCliente() : cPersona(){} //ESTE SOLO TIENE UN CONSTRUCTOR SIMPLE POR QUE LOS DATOS QUE ESTAN EL EL OBJETO PADRE SON PRIVADAS

//FUNCIONES QUE NO ESTAN DENTRO DEL OBJETO
void sobreescribirtxtClientes(int n)
{
	ifstream archivo;
	ofstream archivo2;
	int tecla;
	string buscar, modificar, contenido, linea;
	char c1[]="Clientes.txt", c2[]="Clientes2.txt";
	bool encontro=false, continuar=false;
	
	system("cls");	Titulo();
	
	archivo.open("Clientes.txt",ios::in);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		//EN EL MENU DE MODIFICACIONES DEPENDIENDO DE LO QUE SE QUIERA MODIFICAR MANDA UN NUMERO QUE SE GUARDA EN LA VARIABLE DE REFERENCIA 'N'
		//SI ES EL NOMBRE N==1, Y ASI CON LOS DEMAS, INGRESO LO QUE QUIERO BUSCAR PARA MODIFICAR
		gotoxy (1, 13);	cout<<"INGRESE EL DATO DE LA PERSONA A MODIFICAR: ";
		getline(cin,buscar);
		if(n==1)	buscar="Nombre: "+buscar;
		if(n==2)	buscar="RFC: "+buscar;
		if(n==3)	buscar="Domicilio: "+buscar;
		if(n==4)	buscar="Telefono: "+buscar;
		while(getline(archivo,linea))	if(linea == buscar) encontro=true; //Y CON ESTE WHILE-IF REVISO SI SE ENCONTRO LO QUE SE BUSCABA
	}
	archivo.close();
	
	if(encontro == true) //SI SE ENCONTRO
	{
		archivo.open("Clientes.txt",ios::in);	//ABRO MI ARCHIVO ORIGINL DE LECTURA, Y UN AUXILIAR DE ESCRITURA
		archivo2.open("Clientes2.txt",ios::out);
		
		if(archivo.fail() || archivo2.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			system("cls");	Titulo();
			//SE PIDE EL DATO NUEVO
			gotoxy (1, 13);	cout<<"INGRESE EL NUEVO DATO: ";
			getline(cin,modificar);
			if(n==1)	modificar="Nombre: "+modificar;
			if(n==2)	modificar="RFC: "+modificar;
			if(n==3)	modificar="Domicilio: "+modificar;
			if(n==4)	modificar="Telefono: "+modificar;
			
			//SE HACE UNA PREGUNTA PARA SABER SI SE QUIERE CONTINUAR
			gotoxy (1, 16);	cout<<"ESTA SEGURO DE CONTINUAR CON EL REGISTRO ENTER CONTINUAR / ESC CANCELAR";
			tecla=getch();
			if(tecla==13)	continuar=true; //SI SE CONTINUAR NUESTRA BANDERA SE HACE VERDADERA
			if(tecla==27)	continuar=false; //SINO FALSA
			
			if(continuar==true) //SI SE QUIERE CONTINUAR
			{
				while(getline(archivo,contenido)) //SE SACA LAS LINEAS HASTA ENCONTRAR LA QUE SE QUIERE MODIFICAR
				{
					if(contenido == buscar)	contenido=modificar; //SI SE ENCONTRO SE CAMBIA LO QUE ESTABA POR LO NUEVO
					archivo2<<contenido<<endl; //Y SE METE LA LINEA NUEVA EN LA MISMA POSICION
				}
				archivo.close();
				archivo2.close();
				remove("Clientes.txt"); //SE BORRA Y SE RENOMBRA EL ARCHIVO
				rename(c2,c1);
			}
			else //SI SE CANCELA SE BORRA EL ARCHIVO AUXILIAR
			{
				sndPlaySound("Error.wav",0);
				archivo.close();
				archivo2.close();
				remove("Clientes2.txt");
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

int Menu_Mod_Cliente()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU MODIFICAR CLIENTE";
	gotoxy (2, 15);	cout<<"1.- NOMBRE";
	gotoxy (2, 17);	cout<<"2.- RFC";
	gotoxy (2, 19);	cout<<"3.- DOMICILIO";
	gotoxy (2, 21);	cout<<"4.- TELEFONO";
	gotoxy (2, 23);	cout<<"5.- REGRESAR";
		
		gotoxy(5,27);
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

void main_mod_cliente()
{
	int X, Y;
	bool band=false;
	
	for(;band==false;)
	{
		switch (Menu_Mod_Cliente())
    	{
	       	case 1:	sobreescribirtxtClientes(1);	break;
           	case 2:	sobreescribirtxtClientes(2);	break;
        	case 3:	sobreescribirtxtClientes(3);	break;
       		case 4:	sobreescribirtxtClientes(4);	break;
	       	case 5: band=true;	break;
    	}
	}
}

int Menu_Cliente()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU CLIENTES";
	gotoxy (2, 15);	cout<<"1.- REGISTRAR";
	gotoxy (2, 17);	cout<<"2.- CONSULTAR POR NOMBRE";
	gotoxy (2, 19);	cout<<"3.- CONSULTAR POR CODIGO DE CLIENTE";
	gotoxy (2, 21);	cout<<"4.- MODIFICAR";
	gotoxy (2, 23);	cout<<"5.- LISTA DE CLIENTES";
	gotoxy (2, 25);	cout<<"6.- REGRESAR";
		
		gotoxy(3,29);	cout<<"UTILICE LAS TECLAS DE SELECCION Y ENTER PARA SELECCIONAR UNA OPCION, ESC PARA REGRESAR";
		
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

void main_cliente()
{
	int X, Y;
	bool band=false;
	
	//SE CREAN LO OBJETOS COMO PUNTEROS, YA QUE SE VA A UTILIZAR MEMORIA DINAMICA
	cCliente *objCliente;
	cPersona *objPersonaC;
	
	for(;band==false;)
	{
		switch (Menu_Cliente())
    	{
	       	case 1:	objPersonaC=new cPersona();
					crearTXT(objPersonaC,"Clientes.txt");
					delete objPersonaC;
					break;
           	case 2:	objCliente=new cCliente();	objCliente->Consultar_Nombre("Clientes.txt");	delete objCliente;	break;
        	case 3:	objCliente=new cCliente();	objCliente->Consultar_Codigo("Clientes.txt");	delete objCliente;	break;
       		case 4:	main_mod_cliente();	break;
	       	case 5: objCliente=new cCliente();	objCliente->Mostrar_todos("Clientes.txt");	delete objCliente;	break;
	       	case 6: band=true;	break;
    	}
	}
}
