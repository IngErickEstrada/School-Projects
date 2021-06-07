void Titulo() //Funcion para hacer un titulo e imprimirlo
{
	system("cls");
	char L1[]  = {" --------------------------------------------------------------------------------------------------------------  "};
    char L2[]  = {"| VV         VV  IIIIIIIII  DDDDD       EEEEEEEE  OOOOOOOOOOOO  CCCCCCCCCC  LL        UU        UU  BBBBBB     | "};
    char L3[]  = {"| VV         VV     III     DD   DD     EE        OO        OO  CC          LL        UU        UU  BB    BB   | "};
    char L4[]  = {"|  VV       VV      III     DD    DD    EE        OO        OO  CC          LL        UU        UU  BB     BB  | "};
    char L5[]  = {"|  VV       VV      III     DD     DD   EE        OO        OO  CC          LL        UU        UU  BB    BB   | "};
    char L6[]  = {"|   VV     VV       III     DD      DD  EEEEEE    OO        OO  CC          LL        UU        UU  BBBBBBBBB  | "};
    char L7[]  = {"|   VV     VV       III     DD      DD  EE        OO        OO  CC          LL        UU        UU  BB    BB   | "};
    char L8[]  = {"|    VV   VV        III     DD      DD  EE        OO        OO  CC          LL        UU        UU  BB     BB  | "};
    char L9[]  = {"|    VV   VV        III     DD     DD   EE        OO        OO  CC          LL        UU        UU  BB     BB  | "};
    char L10[] = {"|     VV VV         III     DD   DD     EE        OO        OO  CC          LL        UU        UU  BB    BB   | "};
    char L11[] = {"|      VVV       IIIIIIIII  DDDDD       EEEEEEEE  OOOOOOOOOOOO  CCCCCCCCCC  LLLLLLLL  UUUUUUUUUUUU  BBBBBB     | "};
    char L12[] = {" --------------------------------------------------------------------------------------------------------------  "};
    
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Verde); //Color al texto verde
    cout<<L1<<endl;
    cout<<L2<<endl;
    cout<<L3<<endl;
    cout<<L4<<endl;
    cout<<L5<<endl;
    cout<<L6<<endl;
    cout<<L7<<endl;
    cout<<L8<<endl;
    cout<<L9<<endl;
    cout<<L10<<endl;
    cout<<L11<<endl;
    cout<<L12<<endl;
    
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), Color_Def); //Color al texto por defecto
    cout<<endl<<endl;
}
