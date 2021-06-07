//CLASE U OBJETO PELICULAS Y VIDEOJUEGOS (PADRE)
class cPelVid
{
	public:
	//VARIABLES
	string Codigo;
	string Nombre;
	string Genero;
	string Estatus;
	string Numero_copia;
	string Precio_venta;
	string Precio_renta;
	
	//METODOS
	cPelVid();
	cPelVid(string, string, string, string, string, string, string);
	~cPelVid(){};
	void Registrar(string);
	virtual void Consultar_Nombre(string);
	virtual void Reporte(string)=0;
};

//METODOS DEL OBJETO
cPelVid::cPelVid() //CONSTRUCTOR SIMPLE
{
	Nombre="NOMBRE";
	Genero="GENERO";
	Estatus="DISPONIBLE";
	Codigo="99999";
	Numero_copia="5";
	Precio_venta="99.99";
	Precio_renta="99.99";
}

//CONSTRUCTOR PASO POR REFERENCIAL
cPelVid::cPelVid(string _Nombre, string _Genero, string _Estatus, string _Codigo, string _Numero_copia, string _Precio_venta, string _Precio_renta)
{
	Nombre=_Nombre;
	Genero=_Genero;
	Estatus=_Estatus;
	Codigo=_Codigo;
	Numero_copia=_Numero_copia;
	Precio_venta=_Precio_venta;
	Precio_renta=_Precio_renta;
}

//LAS FUNCIONES DE CONSULTAR POR NOMBRE ES IGUAL QUE LA DE CLIENTES Y EMPLEADOS, SOLO QUE AQUI SON FUNCIONES VIRTUALES PARA PODER HACER POLIMORFISMO
void cPelVid::Consultar_Nombre(string _nombreArchivo)
{
	ifstream archivo;
	string _Nombre, texto, linea;
	int i=0, cont=0, cont2=0, lineaNombre;
	bool encontro=false;
	
	system("cls");	Titulo();

	archivo.open(_nombreArchivo.c_str(),ios::in);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE EL NOMBRE DE LA PELICULA O VIDEOJUEGO A BUSCAR: ";	fflush(stdin);	getline(cin,_Nombre);
		_Nombre="Nombre: "+_Nombre;

		while(getline(archivo,linea))
		{
			if(linea == _Nombre)
			{
				encontro=true;
				lineaNombre=cont;
			}
			cont++;
		}
	}
	archivo.close();
	cont=0;
	
	if(encontro==true)
	{
		archivo.open(_nombreArchivo.c_str(),ios::in);
		if(archivo.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			system("cls");	Titulo();
			if(_nombreArchivo == "Peliculas.txt"){	gotoxy (1, 13);	cout<<"DATOS DE LA PELICULA";}
			if(_nombreArchivo == "Videojuegos.txt"){	gotoxy (1, 13);	cout<<"DATOS DEL VIDEOJUEGO";}
			
			do
			{
				if(cont == lineaNombre-1)
				{
					if(_nombreArchivo == "Peliculas.txt")
					while(getline(archivo,texto) && cont2<8)
					{
						gotoxy (1, 14+i);	cout<<texto<<endl;
						i++;	cont2++;
					}
					
					if(_nombreArchivo == "Videojuegos.txt")
					while(getline(archivo,texto) && cont2<7)
					{
						gotoxy (1, 14+i);	cout<<texto<<endl;
						i++;	cont2++;
					}
				}
				cont++;
			}while(getline(archivo,linea));
		}
		archivo.close();
	}
	else
	{
		system("cls");	Titulo();
		if(_nombreArchivo == "Peliculas.txt"){	gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PELICULA";}
		if(_nombreArchivo == "Videojuegos.txt"){	gotoxy (1, 13);	cout<<"NO SE ENCONTRO EL VIDEOJUEGO";}
		sndPlaySound("Error.wav",0);	
	}
	cont=0;
	gotoxy (1, 25);	cout<<"PRECIONE UNA TECLA PARA CONTINUAR";
	getch();
}

void cPelVid::Registrar(string _nombreArchivo)
{
	ifstream archivo;
	string palabra;
	stringstream ss;
	int cod=1, cod2;
	bool encontro=false;
	
	system("cls");	Titulo();
	
	archivo.open(_nombreArchivo.c_str(), ios::in);
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE LOS DATOS QUE SE TE PIDEN"<<endl;	fflush(stdin);
		ss<<cod;
		Codigo=ss.str();
		ss.str("");
		
		while(archivo>>palabra)
		{
			if(palabra == "Codigo:")
			{
				archivo>>cod2;
				if(cod2==cod)	cod++;
			}
		}
		ss<<cod;
		Codigo=ss.str();
		archivo.close();
		
		gotoxy (1, 14);	cout<<"NOMBRE: ";			getline(cin,Nombre);
		gotoxy (1, 15);	cout<<"GENERO: ";			getline(cin,Genero);
		gotoxy (1, 16);	cout<<"PRECIO VENTA: ";		getline(cin,Precio_venta);
		gotoxy (1, 17);	cout<<"PRECIO RENTA: ";		getline(cin,Precio_renta);
	}
}

//ESTA FUCNION ABRE LOS ARCHIVOS TXT DE LOS REPORTES CORRESPONDIENTES PARA ESO ES LA VARIABLE STRING POR REFERENCIA, PARA QUE RECIBA EL NOMBRE DEL ARCHIVO A ABRIR
//ESTA FUNCION HACE PRACTICAMENTE LO MISMO QUE EN MOSTRAR TODOS DE LOS OBJETOS DE CLIENTES Y EMPLEADOS (CLASE PADRE)
void cPelVid::Reporte(string _nombreArchivo)
{
	ifstream archivo;
	string texto, texto2="Codigo:";
	int i=0, cont=0;
	bool vacio=false;
	
	system("cls");	Titulo();

	archivo.open(_nombreArchivo.c_str(),ios::in);
	
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		archivo>>texto;	if(texto.compare(texto2)!=0) vacio=true;
		archivo.close();
		
		if(vacio==false)
		{
			archivo.open(_nombreArchivo.c_str(),ios::in);
			if(archivo.fail())
			{
				system("cls");	Titulo();
				gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
				sndPlaySound("Error.wav",0);
			}
			else
			{
				system("cls");	Titulo();
				//AQUI NOMAS SE VERIFICA EL NOMBRE DEL ARCHIVO PARA SABER QUE "SUBTITULO PONER"
				if(_nombreArchivo=="Reporte_P_Rent.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LAS PELICULAS RENTADAS";}
				if(_nombreArchivo=="Reporte_P_Vend.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LAS PELICULAS VENDIDAS";}
				if(_nombreArchivo=="Reporte_P_Disp.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LAS PELICULAS DISPONIBLES";}
				
				if(_nombreArchivo=="Reporte_V_Rent.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LOS VIDEOJUAGOS RENTADOS";}
				if(_nombreArchivo=="Reporte_V_Vend.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LOS VIDEOJUAGOS VENDIDOS";}
				if(_nombreArchivo=="Reporte_V_Disp.txt"){gotoxy (1, 13);	cout<<"REPORTE DE LOS VIDEOJUAGOS DISPONIBLES";}
				
				while(getline(archivo,texto)) //SE SACAN LAS LINEAS
				{
					gotoxy (1, 14+i);	cout<<texto<<endl; //Y SE VAN IMPRIMIENDO EN PANTALLA
					i++;
				}
				archivo.close();
			}
		}
		else
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO ESTA VACIO";
			sndPlaySound("Error.wav",0);
		}
	}
	gotoxy (1, (14+i)+5);	cout<<"PRECIONE UNA TECLA PARA CONTINUAR";
	getch();
}
