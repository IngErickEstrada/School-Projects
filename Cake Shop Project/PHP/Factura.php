<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Factura
	{
		//Variable de menu
		private $_idFactura;
		private $_idUsuario;
		private $_Factura;
		private $_Fecha;

		//Metodos
		public function getidFactura(){return $this->_idFactura;}
		public function getidUsuario(){return $this->_idUsuario;}
		public function getFactura(){return $this->_Factura;}
		public function getFecha(){return $this->_Fecha;}

		public function setidFactura($idFactura){$this->_idFactura = $idFactura;}
		public function setidUsuario($idUsuario){$this->_idUsuario = $idUsuario;}
		public function setFactura($Factura){$this->_Factura = $Factura;}
		public function setFecha($Fecha){$this->_Fecha = $Fecha;}
	}

	if (isset($_POST['btn1'])) 
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_3Chocolates.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn2']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_3Leches.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn3']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_BesosdeFresa.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn4']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Capuchino.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn5']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_CarlosV.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn6']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Chocoflan.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn7']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_FrutasBosque.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn8']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Moka.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn9']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_RedVelvet.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn10']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Philadelphia.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn11']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Yoghurt.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn12']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$Conexion = $objConexion->Conexion_Database();

		session_start();
		$user = $_SESSION['Usuario'];

		$SQL1 = $Conexion->query("SELECT id_Usuario FROM usuarios WHERE Nombre_Usuario = '$user'");
		$Result = mysqli_fetch_assoc($SQL1); 
		$idusuario = $Result['id_Usuario'];
		$factura = $Result['id_Usuario'];

		$objFactura->setidUsuario($idusuario);
		$objFactura->setFactura($factura);

		$SQL2 = "SELECT * FROM facturas WHERE Factura = '$factura'";
		if($SQL2 == true){ header("location:Venta_Zanahoria.php");	}

		$checkfactura = $objDatabase->AgregarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objFactura = new Factura();

		$btn = $_POST['factura'];

		$Conexion = $objConexion->Conexion_Database();
		$objFactura->setidFactura($btn);

		$checkfactura = $objDatabase->BorrarFactura($Conexion,$objFactura);
		mysqli_close($Conexion); 
	}
?>