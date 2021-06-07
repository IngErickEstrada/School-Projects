<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Almacen
	{
		//Variable de menu
		private $_idAlmacen;
		private $_Pastel;
		private $_Stock;

		//Metodos
		public function getidAlmacen(){return $this->_idAlamcen;}
		public function getPastel(){return $this->_Pastel;}
		public function getStock(){return $this->_Stock;}

		public function setidAlmacen($idAlmacen){$this->_idAlamcen = $idAlmacen;}
		public function setPastel($Pastel){$this->_Pastel = $Pastel;}
		public function setStock($Stock){$this->_Stock = $Stock;}
	}

	if(isset($_POST['btn']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objAlmacen = new Almacen();

		$idalmacen = $_POST['idAlmacen'];
		$pastel = $_POST['Pastel'];
		$stock = $_POST['Stock'];

		$objAlmacen->setidAlmacen($idalmacen);
		$objAlmacen->setPastel($pastel);
		$objAlmacen->setStock($stock);

		$Conexion = $objConexion->Conexion_Database();

		$SQL1 = "SELECT id_Almacen FROM almacen WHERE id_Almacen = $idalmacen";
		$Resultado1 = mysqli_query($Conexion,$SQL1);
		$Filas1 = mysqli_num_rows($Resultado1);

		$SQL2 = "SELECT Pastel FROM almacen WHERE Pastel = '$pastel'";
		$Resultado2 = mysqli_query($Conexion,$SQL2);
		$Filas2 = mysqli_num_rows($Resultado2);

		if($Filas1 > 0){ $checkalmacen = 1; }
		else if($Filas2 > 0){ $checkalmacen = 2; }
		else{ $checkalmacen = $objDatabase->AgregarAlmacen($Conexion,$objAlmacen); }

		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn2']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objAlmacen = new Almacen();

		$idalmacen = $_POST['idAlmacen'];
		$pastel = $_POST['Pastel'];
		$stock = $_POST['Stock'];

		$objAlmacen->setidAlmacen($idalmacen);
		$objAlmacen->setPastel($pastel);
		$objAlmacen->setStock($stock);

		$Conexion = $objConexion->Conexion_Database();
		$checkalmacen = $objDatabase->ModificarAlmacen($Conexion,$objAlmacen);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objAlmacen = new Almacen();

		$btn = $_POST['almacen'];

		$objAlmacen->setPastel($btn);
		$Conexion = $objConexion->Conexion_Database();
		$objAlmacen->setidAlmacen($btn);

		$checkalmacen = $objDatabase->BorrarAlmacen($Conexion,$objAlmacen);
		mysqli_close($Conexion); 
	}
?>
