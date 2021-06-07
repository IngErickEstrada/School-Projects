<?php 
	class MetodosDB
	{
		public function AgregarUsuarios($Conection,$User)
		{
			$Auxnombreusuario = $User->getNombreUsuario();
			$Auxcontraseña = $User->getContraseña();
			$Auxperfil = $User->getPerfil();
			$Auxnombres = $User->getNombres();
			$Auxsexo = $User->getSexo();
			$Auxedad = $User->getEdad();
			$Auxtelefono = $User->getTelefono();
			$Auxcorreo = $User->getCorreo();

			$SQL = "CALL AgregarUsuarios ('','$Auxnombreusuario','$Auxcontraseña','$Auxperfil','$Auxnombres','$Auxsexo','$Auxedad','$Auxtelefono','$Auxcorreo')";

			if($Conection->query($SQL) == true){ return 2; }
			else{ return 3; }
		}

		public function ModificarUsuarios($Conection,$User)
		{
			$Auxidusuario = $User->getidUsuario();
			$Auxnombreusuario = $User->getNombreUsuario();
			$Auxcontraseña = $User->getContraseña();
			$Auxperfil = $User->getPerfil();
			$Auxnombres = $User->getNombres();
			$Auxsexo = $User->getSexo();
			$Auxedad = $User->getEdad();
			$Auxtelefono = $User->getTelefono();
			$Auxcorreo = $User->getCorreo();

			$SQL = "CALL ModificarUsuario ('$Auxidusuario','$Auxnombreusuario','$Auxcontraseña','$Auxperfil','$Auxnombres','$Auxsexo','$Auxedad','$Auxtelefono','$Auxcorreo')";

			if($Conection->query($SQL) == true){ return 4; }
			else{ return 5; }
		}

		public function BorrarUsuario($Conection,$User)
		{
			$Auxidusuario = $User->getidUsuario();

			$SQL = "CALL BorrarUsuario ('$Auxidusuario')";

			if($Conection->query($SQL) == true){ return 6; }
			else{ return 7; }
		}

		public function AgregarMenu($Conection,$Menu)
		{
			$Auxidmenu = $Menu->getidMenu();
			$Auxpastel = $Menu->getPastel();
			$Auxcategoria = $Menu->getCategoria();
			$Auxprecio = $Menu->getPrecio();

			$SQL = "CALL AgregarMenu ('$Auxidmenu','$Auxcategoria','$Auxpastel','$Auxprecio')";

			if($Conection->query($SQL) == true){ return 3; }
			else{ return 4; }
		}

		public function ModificarMenu($Conection,$Menu)
		{
			$Auxidmenu = $Menu->getidMenu();
			$Auxpastel = $Menu->getPastel();
			$Auxcategoria = $Menu->getCategoria();
			$Auxprecio = $Menu->getPrecio();

			$SQL = "CALL ModificarMenu ('$Auxidmenu','$Auxcategoria','$Auxpastel','$Auxprecio')";

			if($Conection->query($SQL) == true){ return 5; }
			else{ return 6; }
		}

		public function BorrarMenu($Conection,$Menu)
		{
			$Auxidmenu = $Menu->getidMenu();

			$SQL = "CALL BorrarMenu ('$Auxidmenu')";

			if($Conection->query($SQL) == true){ return 7; }
			else{ return 8; }
		}

		public function AgregarAlmacen($Conection,$Almacen)
		{
			$Auxidalmacen = $Almacen->getidAlmacen();
			$Auxpastel = $Almacen->getPastel();
			$Auxstock = $Almacen->getStock();

			$SQL = "CALL AgregarAlmacen ('$Auxidalmacen','$Auxpastel','$Auxstock')";

			if($Conection->query($SQL) == true){ return 3; }
			else{ return 4; }
		}

		public function ModificarAlmacen($Conection,$Almacen)
		{
			$Auxidalmacen = $Almacen->getidAlmacen();
			$Auxpastel = $Almacen->getPastel();
			$Auxstock = $Almacen->getStock();

			$SQL = "CALL ModificarAlmacen ('$Auxidalmacen','$Auxpastel','$Auxstock')";

			if($Conection->query($SQL) == true){ return 5; }
			else{ return 6; }
		}

		public function BorrarAlmacen($Conection,$Almacen)
		{
			$Auxidalmacen = $Almacen->getidAlmacen();

			$SQL = "CALL BorrarAlmacen ('$Auxidalmacen')";

			if($Conection->query($SQL) == true){ return 7; }
			else{ return 8; }
		}

		public function AgregarPasteles($Conection,$Pastel)
		{
			$Auxidpastel = $Pastel->getidPastel();
			$Auxidmenu = $Pastel->getidMenu();
			$Auxidalmacen = $Pastel->getidAlmacen();

			$SQL = "CALL AgregarPasteles ('$Auxidpastel','$Auxidmenu','$Auxidalmacen')";

			if($Conection->query($SQL) == true){ return 2; }
			else{ return 3; }
		}

		public function BorrarPasteles($Conection,$Pastel)
		{
			$Auxidpastel = $Pastel->getidPastel();

			$SQL = "CALL BorrarPasteles ('$Auxidpastel')";

			if($Conection->query($SQL) == true){ return 4; }
			else{ return 5; }
		}

		public function AgregarFactura($Conection,$Factura)
		{
			$Auxidusuario = $Factura->getidUsuario();
			$Auxfactura = $Factura->getFactura();

			$SQL = "CALL AgregarFactura ('','$Auxidusuario','$Auxfactura','')";

			if($Conection->query($SQL) == true){ return 1; }
			else{ return 2; }
		}

		public function BorrarFactura($Conection,$Factura)
		{
			$Auxidfactura = $Factura->getidFactura();

			$SQL = "CALL BorrarFactura ('$Auxidfactura')";

			if($Conection->query($SQL) == true){ return 3; }
			else{ return 4; }
		}

		public function AgregarVenta($Conection,$Venta)
		{
			$Auxidpastel = $Venta->getidPastel();
			$Auxidfactura = $Venta->getidFactura();
			$Auxcantidad = $Venta->getCantidad();
			$Auxtotal = $Venta->getTotal();

			$SQL = "CALL AgregarVenta ('','$Auxidpastel','$Auxidfactura','$Auxcantidad','$Auxtotal')";

			if($Conection->query($SQL) == true){ return 1; }
			else{ return 2; }
		}

		public function BorrarVenta($Conection,$Venta)
		{
			$Auxidventa = $Venta->getidVenta();
			
			$SQL = "CALL BorrarVenta ('$Auxidventa')";

			if($Conection->query($SQL) == true){ return 3; }
			else{ return 4; }
		}

		public function VentaAlmacen($Conection)
		{
			$SQL = "CALL Venta_Almacen ()";
			$Conection->query($SQL);
		}

		public function VentaRealizada($Conection,$Venta)
		{
			$Auxidventa = $Venta->getidVenta();

			$SQL = "CALL VentaRealizada ('$Auxidventa')";

			if($Conection->query($SQL) == true){ return 5; }
		}
	}	
?>