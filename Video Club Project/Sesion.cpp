void Cargando(int n) //Funcion para hacer una carga
{
	char punto[3];
	int j=0, X, Y,k=0;
	
	Titulo(); //Llmada a la funcion Titulo
	if (n==1){	gotoxy (1,14);	cout<<"INICIANDO SESION";}
	if (n==2){	gotoxy (2,14);	cout<<"CERRANDO SESION";}
	do
	{
		gotoxy (17,14); //Posiciona lo que esta dentro del bucle en XY(8,14) correspondientemente
		for(int i=0; i<3; i++){	punto[i]=46;	cout<<punto[i];	Sleep(500);} //Bucle par la impresion de puntos de carga
		
		do //Bucle para posicionamiento 
		{
			gotoxy (17+k,14); //Posiciona lo que esta dentro del bucle en XY(8,y+k) correspondientemente, conjunto con el bucl do while
			for(int i=0; i<3; i++) //Bucle par la impresion de de espacios y quitar los puntos
			{
				punto[i]='\0';	cout<<punto[i];
			}
			k++;
		}while(k <= 15);
		k=0;	j++;
	}while(j < 3);
	system("cls");	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def);
}
