<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Pasteles
	{
		//Variable de menu
		private $_idPastel;
		private $_idMenu;
		private $_idAlmacen;

		//Metodos
		public function getidPastel(){return $this->_idPastel;}
		public function getidMenu(){return $this->_idMenu;}
		public function getidAlmacen(){return $this->_idAlmacen;}

		public function setidPastel($idPastel){$this->_idPastel = $idPastel;}
		public function setidMenu($idMenu){$this->_idMenu = $idMenu;}
		public function setidAlmacen($idAlmacen){$this->_idAlmacen = $idAlmacen;}
	}

	if(isset($_POST['btn']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objPastel = new Pasteles();

		$idpastel = $_POST['idPastel'];
		$idmenu = $_POST['idMenu'];
		$idalmacen = $_POST['idAlmacen'];

		$objPastel->setidPastel($idpastel);
		$objPastel->setidMenu($idmenu);
		$objPastel->setidAlmacen($idalmacen);

		$Conexion = $objConexion->Conexion_Database();

		$SQL1 = "SELECT id_Pastel FROM pasteles WHERE id_Pastel = $idpastel";
		$Resultado1 = mysqli_query($Conexion,$SQL1);
		$Filas1 = mysqli_num_rows($Resultado1);

		if($Filas1 > 0){ $checkpasteles = 1; }
		else{ $checkpasteles = $objDatabase->AgregarPasteles($Conexion,$objPastel); }

		mysqli_close($Conexion); 
	}	

	else if(isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objPastel = new Pasteles();

		$btn = $_POST['detalle'];
	
		$Conexion = $objConexion->Conexion_Database();
		$objPastel->setidPastel($btn);	

		$checkpasteles = $objDatabase->BorrarPasteles($Conexion,$objPastel);
		mysqli_close($Conexion); 
	}
?>