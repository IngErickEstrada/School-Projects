//CLASE U OBJETO PERSONA (PADRE)
class cPersona
{
	//VARIABLES
	string Nombre;
	string RFC;
	string Domicilio;
	string Codigo;
	string Telefono;
	
	public:
	//METODOS
	cPersona();
	cPersona(string,string,string,string,string);
	~cPersona(){};
	int Registrar(string);
	void Consultar_Nombre(string);
	void Consultar_Codigo(string);
	void Mostrar_todos(string);
	
	friend int crearTXT(cPersona *, string);
};

//METODOS DEL OBJETO
cPersona::cPersona()	//CONSTRUCTOR SIMPLE
{
	Nombre="Nombre";
	RFC="RFC";
	Domicilio="Domicilio";
	Codigo="Codigo";
	Telefono="Telefono";
}

cPersona::cPersona(string _Nombre, string _RFC, string _Domicilio, string _Codigo, string _Telefono)	//CONSTRUCTTOR PASO PO REFERENCIA
{
	Nombre=_Nombre;
	RFC=_RFC;
	Domicilio=_Domicilio;
	Codigo=_Codigo;
	Telefono=_Telefono;
}

int cPersona::Registrar(string _nombreArchivo)
{
	system("cls");
	Titulo();
	
	ifstream archivo;
	string palabra;
	stringstream ss;
	int cod=1, cod2, tecla;
	bool encontro=false, band=false;
	
	archivo.open(_nombreArchivo.c_str(), ios::in);	//AQUI ABROUN ARCHIVO PARA SABER SI YA UN CODIGO INGRESADO
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE LOS DATOS QUE SE TE PIDEN"<<endl;
		ss<<cod;
		Codigo=ss.str();
		ss.str("");
		
		while(archivo>>palabra)	//SACO POR PALABRAS HASTA LLEGAR A LA PALABRA CODIGO:
		{
			if(palabra == "Codigo:")	//CUANDO LA ENCUENTRO
			{
				archivo>>cod2;	//SACO EL NUMERO
				if(cod2==cod)	cod++;	//LO AUMENTO EN UNO
			}
		}
		ss<<cod;
		Codigo=ss.str();	//Y LO METO EN MI VARIABLE CODIGO DE MI OBJETO
		archivo.close();
		
		//METO OS DEMAS DATOS
		gotoxy (1, 14);	cout<<"NOMBRE: ";		getline(cin,Nombre);
		gotoxy (1, 15);	cout<<"RFC: ";			getline(cin,RFC);
		gotoxy (1, 16);	cout<<"DOMICILIO: ";	getline(cin,Domicilio);
		gotoxy (1, 17);	cout<<"TELEFONO: ";		getline(cin,Telefono);
		
		gotoxy (1, 20);	cout<<"ESTA SEGURO DE CONTINUAR CON EL REGISTRO ENTER CONTINUAR / ESC CANCELAR";
		tecla=getch();
		if(tecla==13)	return 0;
		if(tecla==27)	return 1;
	}
}

void cPersona::Consultar_Nombre(string _nombreArchivo)	
{
	ifstream archivo;
	string _Nombre, texto, linea;
	int i=0, cont=0, cont2=0, lineaNombre;
	bool encontro=false;
	
	system("cls");	Titulo();

	archivo.open(_nombreArchivo.c_str(),ios::in);	//PRIMERO ABRO MI ARCHIVO PARA BUSCAR EL NOMBRE
	if(archivo.fail())
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
		sndPlaySound("Error.wav",0);
	}
	else
	{
		gotoxy (1, 13);	cout<<"INGRESE EL NOMBRE DE LA PERSONA A BUSCAR: ";	fflush(stdin);	getline(cin,_Nombre);
		_Nombre="Nombre: "+_Nombre;

		while(getline(archivo,linea)) //SACO LINEAS PARA VERIFICAR CON EL IF
		{
			if(linea == _Nombre) //SI ENCUENTRO EL NOMBRE ME GUARDE EL NUMERO DE ESA LINEA, Y MI BANDERA SE VUELVE VERDADERA PARA SABER QUE SI ENCONTRO EL NOMBRE QUE BUSCABA
			{
				encontro=true;
				lineaNombre=cont;
			}
			cont++;
		}
	}
	archivo.close();
	cont=0;
	
	if(encontro==true)	//VERIFICO SI ENCONTRO EL NOMBRE Y SI SI CONTINUO
	{
		archivo.open(_nombreArchivo.c_str(),ios::in); //VUELVO A HABRIR MI ARCHIVO
		if(archivo.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"DATOS DE LA PERSONA";
			do //UTILIZO UN DO-WHILE POR QUE LA PRIMER LINEA SERIA 0, Y MI CONTADOR INICIA EN CERO, EN CASO DE QUE MI CLIENTE I CMPLEADO SEA EL DEL CODIGO 1
			{
				if(cont == lineaNombre-1) //SI MI CONTADOR ES IGUAL A LA LINEA DEL NOMBRE MENOS UNO, QUE ES DONDE ESTARIA EL CODIGO, ENTONCES ME IMPRIMI DESDE ESA LINEA
				{
					if(_nombreArchivo == "Clientes.txt") //REVISO CUAL ES EL ARCHIVO QUE QUIERO ABRIR SI EL DEL CLIENTE O EMPLEADO 
					while(getline(archivo,texto) && cont2<5)	//SACO SOLO LAS LINEAS QUE NECESITO PARA ESO EL CONT2
					{
						gotoxy (1, 14+i);	cout<<texto<<endl;	//IMPRIMO, Y MI POSICION LA VOY BAJANDO 1 EN X
						i++;	cont2++;
					}
					
					if(_nombreArchivo == "Empleados.txt")
					while(getline(archivo,texto) && cont2<7)
					{
						gotoxy (1, 14+i);	cout<<texto<<endl;
						i++;	cont2++;
					}
				}
				cont++;	//ESTE CONTADOR ES QUE SE AUMENTA EN CASO DE QUE EL NOMBRE NO SEA EL PRIMERO
			}while(getline(archivo,linea));
		}
		archivo.close();
	}
	else
	{
		system("cls");	Titulo();
		gotoxy (1, 13);	cout<<"NO SE ENCONTRO A LA PERSONA";
		sndPlaySound("Error.wav",0);	
	}
	cont=0;
	gotoxy (1, 22);	cout<<"PRECIONE UNA TECLA PARA CONTINUAR";
	getch();
}

void cPersona::Consultar_Codigo(string _nombreArchivo)
{
	ifstream archivo;
	string _Codigo, texto, linea;
	int i=0, cont=0, cont2=0, lineaCodigo;
	bool band=false, encontro=false;
	
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
		//AQUI INGRESO EL CODIGO QUE QUIERO BUSCAR
		gotoxy (1, 13);	cout<<"INGRESE EL CODIGO DE LA PERSONA A BUSCAR: ";	fflush(stdin);	getline(cin,_Codigo);
		_Codigo="Codigo: "+_Codigo;
		
		while(getline(archivo,linea))	if(linea == _Codigo) encontro=true; //CON EL WHILE Y EL IF VERIFICO SI SE ENCONTRO
	}
	archivo.close();
	
	if(encontro==true) //SI SE ENCONTRO
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
			//VERIFICO DE CUAL QUIERO BUSCAR SI CLIENTE O EMPLEADO PARA PONER UN "SUBTITULO"
			if(_nombreArchivo == "Clientes.txt"){	gotoxy (1, 13);	cout<<"DATOS DEL CLIENTE"<<endl;}
			if(_nombreArchivo == "Empleados.txt"){	gotoxy (1, 13);	cout<<"DATOS DEL EMPLEADO"<<endl;}
			
			while(getline(archivo,linea)) //SACO LAS LINEAS
			{
				if(linea == _Codigo) //SI LA LINEA QUE SAQUE ES LA MISMA QUE LA DEL CODIGO QUE BUUSCO
				{
					gotoxy (1, 14);	cout<<linea<<endl; //IMPRIMO ESA LINEA
					if(_nombreArchivo == "Clientes.txt")	//VUELVO A FERIFICAR SI ES CLIENTE O EMPLEADO, ESTA PARTE SE VERIFICA POR QUE EMPLEADOS TIENE MAS LINEAS QUE CLIENTES
					while(getline(archivo,texto) && cont<4) //AQUI SE NOTA QUE ES HASTA UNA CANTIDAD MENOR QUE SI FUERA LA DE EMPLEADOS QUE ES LA DE ABAJO
					{
						gotoxy (1, 15+i);	cout<<texto<<endl;
						i++;	cont++;
					}
					
					if(_nombreArchivo == "Empleados.txt")
					while(getline(archivo,texto) && cont<6)
					{
						gotoxy (1, 15+i);	cout<<texto<<endl;
						i++;	cont++;
					}
				}	
			}
			archivo.close();
		}
	}
	else
	{
		system("cls");	Titulo();
		if(_nombreArchivo == "Clientes.txt"){	gotoxy (1, 13);	cout<<"NO SE ENCONTRO AL CLIENTE"<<endl;}
		if(_nombreArchivo == "Empleados.txt"){	gotoxy (1, 13);	cout<<"NO SE ENCONTRO AL EMPLEADO";}
		sndPlaySound("Error.wav",0);
	}
	gotoxy (1, 26);	cout<<"PRECIONE UNA TECLA PARA CONTINUAR";
	getch();
}

void cPersona::Mostrar_todos(string _nombreArchivo)
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
		archivo>>texto;	if(texto.compare(texto2)!=0) vacio=true; //AQUI VERIFICO SI MI ARCHIVO ESTA VACIO O NO
		archivo.close();
		
		if(vacio==false) //SI NO ESTA VACIO 
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
				gotoxy (1, 13);	cout<<"DATOS DE LAS PERSONAS";
				while(getline(archivo,texto)) //QUE ME SAQUE CADA LINEA Y ME LAS IMPRIMA TODAS
				{
					gotoxy (1, 14+i);	cout<<texto<<endl;
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

int crearTXT(cPersona *Persona, string _nombreArchivo) //ESTA ES LA FUNCION AMIGA DEL OBJETO PERSONA, SE LE MANDA EL OBJETO, Y TAMBIEN RECIBE EL NOMBRE DEL ARCHIVO QUE SE VA A HABRIR EMPLEADOS/CLIENTES
{
	ofstream archivo;
	int n;
	
	//MANDO A LLAMAR AL METODO DE REGISTRAR PARA METER LOS DATOS AL OBJETO
	n=Persona->Registrar( _nombreArchivo);
	
	if(n==0)
	{
		archivo.open(_nombreArchivo.c_str(),ios::app);
		if(archivo.fail())
		{
			system("cls");	Titulo();
			gotoxy (1, 13);	cout<<"EL ARCHIVO NO ESTA O NO SE ENCUENTRA";
			sndPlaySound("Error.wav",0);
		}
		else
		{
			//AQUI SACO LOS DATOS DEL OBJETO Y SE SACA COMO SI FUERA UN UBJETO, YA QUE ES UNA FUNCION AMIGA, Y LAS VARIABLES SON PRIVADAS
			archivo<<"Codigo: "+Persona->Codigo<<endl;
			archivo<<"Nombre: "+Persona->Nombre<<endl;
			archivo<<"RFC: "+Persona->RFC<<endl;
			archivo<<"Domicilio: "+Persona->Domicilio<<endl;
			if(_nombreArchivo=="Clientes.txt")	archivo<<"Telefono: "+Persona->Telefono<<endl<<endl; //CUANDO ES CLIENTES SE DA UN DOBLE SALTO PARA QUE EL SIGUIENTE REGISTRO NO QUEDE PEGADO
			if(_nombreArchivo=="Empleados.txt")	archivo<<"Telefono: "+Persona->Telefono<<endl; //SI ES EMPLEADOS SOLO SE DA UN SALTO DE LINEA POR QUE DESPUES DEL TELEFONO SE LE AGREGA EL PAGO, Y LAS COMISIONES
			sndPlaySound("Notificacion.wav",0);
			archivo.close();
		}
		
		return 0; //ME RETORNA UN CERO LE SIRVE A EMPLEADOS, PARA SABER SI METE O NO LOS DATOS FALTANTES QUE SON EL PAGO Y LAS COMISIONES
	}
	else
	{
		system("cls");	Titulo();
		sndPlaySound("Error.wav",0);
		gotoxy (1, 13);	cout<<"SE CANCELO LA SOLICITUD"; getch();
	}
}






