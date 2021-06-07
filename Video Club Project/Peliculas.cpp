//CLASE U OBJETO PELICULA (HIJA)
class cPelicula: public cPelVid
{
	public:
	//VARIABLES
	string Director;
	
	//METODOS
	cPelicula();
	cPelicula(string, string, string, string, string, string, string, string);
	~cPelicula(){};
	void Consultar_Nombre(string);
	void creartxt(string);
	void Reporte(string);
};

//METODOS DEL OBJETO
cPelicula::cPelicula() : cPelVid()
{
	Director="Director";
}

cPelicula::cPelicula(string _Nombre, string _Genero, string _Estatus, string _Codigo, string _Numero_copia, string _Precio_venta, string _Precio_renta, string _Director) : cPelVid(_Nombre, _Genero, _Estatus, _Codigo, _Numero_copia, _Precio_venta, _Precio_renta)
{
	Nombre=_Nombre;
	Genero=_Genero;
	Estatus=_Estatus;
	Codigo=_Codigo;
	Numero_copia=_Numero_copia;
	Precio_venta=_Precio_venta;
	Precio_renta=_Precio_renta;
	Director=_Director;
}

//CONSULTAR POR NOMBRE Y REPORTE LLAMAN A LAS FUNCIONES DE LA CLASE PADRE PARA HACER EL POLIMORFISMO
void cPelicula::Consultar_Nombre(string _nombreArchivo){	cPelVid::Consultar_Nombre(_nombreArchivo);}

void cPelicula::creartxt(string _nombreArchivo)
{
	ofstream archivo, archivo2;
	int tecla, n;
	
	cPelVid::Registrar(_nombreArchivo);
	
	gotoxy (1, 18);	cout<<"DIRECTOR: ";		getline(cin,Director);
	
	gotoxy (1, 21);	cout<<"ESTA SEGURO DE CONTINUAR CON EL REGISTRO ENTER CONTINUAR / ESC CANCELAR";
	tecla=getch();
	
	if(tecla==13)
	{
		archivo.open(_nombreArchivo.c_str(),ios::app);
		archivo2.open("Reporte_P_Disp.txt",ios::app);
		if(archivo.fail() || archivo2.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			archivo<<"Codigo: "+Codigo<<endl;
			archivo<<"Nombre: "+Nombre<<endl;
			archivo<<"Director: "+Director<<endl;
			archivo<<"Genero: "+Genero<<endl;
			archivo<<"Estatus: "+Estatus<<endl;
			archivo<<"Numero Copia: "+Numero_copia<<endl;
			archivo<<"Precio Venta: "+Precio_venta<<endl;
			archivo<<"Precio Renta: "+Precio_renta<<endl<<endl;
			
			archivo2<<"Codigo: "+Codigo<<endl;
			archivo2<<"Nombre: "+Nombre<<endl;
			archivo2<<"Estatus: "+Estatus<<endl;
			archivo2<<"Cantidad: "+Numero_copia<<endl;
			archivo2<<"Estatus: RENTADA"<<endl;
			archivo2<<"Cantidad: 0"<<endl;
			archivo2<<"Estatus: VENDIDA"<<endl;
			archivo2<<"Cantidad: 0"<<endl<<endl;
			
			sndPlaySound("Notificacion.wav",0);
			archivo.close();
			archivo2.close();
		}
	}
	if(tecla==27)
	{
		system("cls");	Titulo();
		sndPlaySound("Error.wav",0);
		gotoxy (1, 13);	cout<<"SE CANCELO LA SOLICITUD"; getch();
	}
}

void cPelicula::Reporte(string _nombreArchivo){cPelVid::Reporte(_nombreArchivo);}

//FUNCIONES QUE NO ESTAN DENTRO DEL OBJETOV
void sobreescribirtxtPeliculas(int n)
{
	ifstream archivo;
	ofstream archivo2;
	int tecla;
	string buscar, modificar, contenido, linea;
	char c1[]="Peliculas.txt", c2[]="Peliculas2.txt";
	bool encontro=false, continuar=false;
	
	system("cls");	Titulo();
	
	archivo.open("Peliculas.txt",ios::in);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE EL DATO DE LA PELICULA A MODIFICAR: ";
		getline(cin,buscar);
		if(n==1)	buscar="Nombre: "+buscar;
		if(n==2)	buscar="Director: "+buscar;
		if(n==3)	buscar="Genero: "+buscar;
		if(n==4)	buscar="Numero Copia: "+buscar;
		if(n==5)	buscar="Precio Venta: "+buscar;
		if(n==6)	buscar="Precio Renta: "+buscar;
		while(getline(archivo,linea))	if(linea == buscar) encontro=true;
	}
	archivo.close();
	
	if(encontro == true)
	{
		archivo.open("Peliculas.txt",ios::in);
		archivo2.open("Peliculas2.txt",ios::out);
		
		if(archivo.fail() || archivo2.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"INGRESE EL NUEVO DATO: ";
			getline(cin,modificar);
			if(n==1)	modificar="Nombre: "+modificar;
			if(n==2)	modificar="Director: "+modificar;
			if(n==3)	modificar="Genero: "+modificar;
			if(n==4)	modificar="Numero Copia: "+modificar;
			if(n==5)	modificar="Precio Venta: "+modificar;
			if(n==6)	modificar="Precio Renta: "+modificar;
			
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
				remove("Peliculas.txt");
				rename(c2,c1);
			}
			else
			{
				sndPlaySound("Error.wav",0);
				archivo.close();
				archivo2.close();
				remove("Peliculas2.txt");
			}
		}
	}
	else
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PELICULA";
		sndPlaySound("Error.wav",0);
		getch();
	}
}

int Menu_Mod_Peliculas()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU PELICULAS";
	gotoxy (2, 15);	cout<<"1.- NOMBRE";
	gotoxy (2, 17);	cout<<"2.- DIRECTOR";
	gotoxy (2, 19);	cout<<"3.- GENERO";
	gotoxy (2, 21);	cout<<"4.- NUMERO COPIA";
	gotoxy (2, 23);	cout<<"5.- PRECIO VENTA";
	gotoxy (2, 25);	cout<<"6.- PRECIO RENTA";
	gotoxy (2, 27);	cout<<"7.- REGRESAR";
	
		
		gotoxy(3,31);
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
										if(Cursor_Pos <15) Cursor_Pos = 27;
										break;
					
					case Tecla_Abajo:	if(Cursor_Pos <= 27) Cursor_Pos += 2;
										if(Cursor_Pos >27) Cursor_Pos = 15;
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
                        	case 27: return 7; break;
                    	}
                	}
                	break;
                	case Tecla_Esc:	return 7;	break;
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
	return 7;
}

void main_mod_pelicula()
{
	int X, Y;
	bool band=false;
	
	for(;band==false;)
	{
		switch (Menu_Mod_Peliculas())
    	{
	       	case 1:	sobreescribirtxtPeliculas(1);	break;
           	case 2:	sobreescribirtxtPeliculas(2);	break;
        	case 3:	sobreescribirtxtPeliculas(3);	break;
       		case 4:	sobreescribirtxtPeliculas(4);	break;
       		case 5:	sobreescribirtxtPeliculas(5);	break;
       		case 6:	sobreescribirtxtPeliculas(6);	break;
       		case 7:	band=true; break;
    	}
	}
}

int Menu_Peliculas()
{
	int tecla, X, Y, Cursor_Pos=15;
	
	Titulo();
	
	gotoxy (1, 13);	cout<<"MENU PELICULAS"<<endl<<endl;
	gotoxy (2, 15);	cout<<"1.- REGISTRAR"<<endl;
	gotoxy (2, 17);	cout<<"2.- CONSULTAR POR NOMBRE"<<endl;
	gotoxy (2, 19);	cout<<"3.- MODIFICAR"<<endl;
	gotoxy (2, 21);	cout<<"4.- REPORTE DE PELICULAS RENTADOS"<<endl;
	gotoxy (2, 23);	cout<<"5.- REPORTE DE PELICULAS VENDIDOS"<<endl;
	gotoxy (2, 25);	cout<<"6.- REPORTE DE PELICULAS DISPONIBLES"<<endl;
	gotoxy (2, 27);	cout<<"7.- REGRESAR"<<endl;
	
		
		gotoxy(3,31);
    	cout << "UTILICE LAS TECLAS DE SELECCION Y ENTER PARA SELECCIONAR UNA OPCION, ESC PARA REGRESAR" << endl;
		
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
										if(Cursor_Pos <15) Cursor_Pos = 27;
										break;
					
					case Tecla_Abajo:	if(Cursor_Pos <= 27) Cursor_Pos += 2;
										if(Cursor_Pos >27) Cursor_Pos = 15;
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
                        	case 27: return 7; break;
                    	}
                	}
                	break;
                	case Tecla_Esc:	return 7;	break;
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
	return 7;
}

void main_pelicula()
{
	int X, Y;
	bool band=false;
	
	cPelicula *objPelicula;
	
	for(;band==false;)
	{
		switch (Menu_Peliculas())
    	{
	       	case 1:	objPelicula=new cPelicula("NOMBRE", "GENERO", "DISPONIBLE", "99999", "5", "99.99", "99.99", "DIRECTOR");
			   		objPelicula->creartxt("Peliculas.txt");	delete objPelicula;	break;
           	case 2:	objPelicula=new cPelicula();	objPelicula->Consultar_Nombre("Peliculas.txt");	delete objPelicula;	break;
        	case 3:	main_mod_pelicula();	break;
       		case 4:	objPelicula=new cPelicula();	objPelicula->Reporte("Reporte_P_Rent.txt");	delete objPelicula;	break;
       		case 5:	objPelicula=new cPelicula();	objPelicula->Reporte("Reporte_P_Vend.txt");	delete objPelicula;	break;
       		case 6:	objPelicula=new cPelicula();	objPelicula->Reporte("Reporte_P_Disp.txt");	delete objPelicula;	break;
       		case 7:	band=true; break;
    	}
	}
}
