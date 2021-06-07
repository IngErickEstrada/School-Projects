<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Venta
	{
		//Variable de ventas
		private $_idVenta;
		private $_idPastel;
		private $_idFactura;
		private $_Cantidad;
		private $_Subtotal;
		private $_Total;

		//Metodos
		public function getidVenta(){return $this->_idVenta;}
		public function getidPastel(){return $this->_idPastel;}
		public function getidFactura(){return $this->_idFactura;}
		public function getCantidad(){return $this->_Cantidad;}
		public function getTotal(){return $this->_Total;}

		public function setidVenta($idVenta){$this->_idVenta = $idVenta;}
		public function setidPastel($idPastel){$this->_idPastel = $idPastel;}
		public function setidFactura($idFactura){$this->_idFactura = $idFactura;}
		public function setCantidad($Cantidad){$this->_Cantidad = $Cantidad;}
		public function setTotal($Total){$this->_Total = $Total;}
	}

	if(isset($_POST['btnagregar1']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 1");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 1"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad1']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);
		}  
	}

	else if(isset($_POST['btnagregar2']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 2");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 2"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad2']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);
		}  
	}

	else if(isset($_POST['btnagregar3']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 3");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 3"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad3']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion); 
		} 
	}

	else if(isset($_POST['btnagregar4']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 4");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 4"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad4']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{	
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);
			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion); 
		 }
	}

	else if(isset($_POST['btnagregar5']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 5");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 5"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad5']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar6']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 6");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 6"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad6']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar7']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 7");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 7"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad7']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar8']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 8");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 8"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad8']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar9']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 9");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 9"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad9']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar10']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 10");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 10"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad10']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar11']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 11");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 11"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad11']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if(isset($_POST['btnagregar12']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Pastel FROM pasteles WHERE id_Pastel = 12");
		$Result1 = mysqli_fetch_assoc($SQL1); 
		$idpastel = $Result1['id_Pastel'];

		$SQL2 = $Conexion->query("SELECT id_Factura FROM usuarios as u, facturas as f WHERE u.id_Usuario = f.id_Usuario and u.Nombre_Usuario = '$user'");
		$Result2 = mysqli_fetch_assoc($SQL2);
		$idfactura = $Result2['id_Factura'];

		$SQL3 = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 12"); 
		$Result3 = mysqli_fetch_assoc($SQL3);

		$Precio = $Result3['Precio'];
		$Cantidad = $_POST['Cantidad12']; 
		$IVA = ($Precio*$Cantidad)*0.16;
		$Total = ($Precio*$Cantidad)+$IVA;

		if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
		else
		{
			$objVenta->setidPastel($idpastel);
			$objVenta->setidFactura($idfactura);
			$objVenta->setCantidad($Cantidad);
			$objVenta->setTotal($Total);

			$checkventa = $objDatabase->AgregarVenta($Conexion,$objVenta);
			mysqli_close($Conexion);  
		}
	}

	else if (isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$btn = $_POST['venta'];

		$Conexion = $objConexion->Conexion_Database();
		$objVenta->setidVenta($btn);

		$checkventa = $objDatabase->BorrarVenta($Conexion,$objVenta);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['Comprar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objVenta = new Venta();

		$Conexion = $objConexion->Conexion_Database();

		$checkstock = $objDatabase->VentaAlmacen($Conexion);

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL = $Conexion->query("SELECT id_Venta FROM ventas as v, facturas as f, usuarios as u WHERE v.id_Factura=f.id_Factura and f.id_Usuario=u.id_Usuario and u.Nombre_Usuario='$user'");
		while($Result = mysqli_fetch_array($SQL))
		{	
			$idventa = $Result['id_Venta'];
			$objVenta->setidVenta($idventa);
			$checkventa = $objDatabase->VentaRealizada($Conexion,$objVenta);
		}
	}
?>