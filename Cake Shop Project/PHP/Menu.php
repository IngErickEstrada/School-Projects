<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Menu
	{
		//Variable de menu
		private $_idMenu;
		private $_Pastel;
		private $_Categoria;
		private $_Precio;

		//Metodos
		public function getidMenu(){return $this->_idMenu;}
		public function getPastel(){return $this->_Pastel;}
		public function getCategoria(){return $this->_Categoria;}
		public function getPrecio(){return $this->_Precio;}

		public function setidMenu($idMenu){$this->_idMenu = $idMenu;}
		public function setPastel($Pastel){$this->_Pastel = $Pastel;}
		public function setCategoria($Categoria){$this->_Categoria = $Categoria;}
		public function setPrecio($Precio){$this->_Precio = $Precio;}
	}

	if (isset($_POST['btn'])) 
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objMenu = new Menu();

		$idmenu = $_POST['idMenu'];
		$pastel = $_POST['Pastel'];
		$categoria = $_POST['Categoria'];
		$precio = $_POST['Precio'];

		$objMenu->setidMenu($idmenu);
		$objMenu->setPastel($pastel);
		$objMenu->setCategoria($categoria);
		$objMenu->setPrecio($precio);

		$Conexion = $objConexion->Conexion_Database();

		$SQL1 = "SELECT id_Menu FROM menu WHERE id_Menu = $idmenu";
		$Resultado1 = mysqli_query($Conexion,$SQL1);
		$Filas1 = mysqli_num_rows($Resultado1);

		$SQL2 = "SELECT Pastel FROM menu WHERE Pastel = '$pastel'";
		$Resultado2 = mysqli_query($Conexion,$SQL2);
		$Filas2 = mysqli_num_rows($Resultado2);

		if($Filas1 > 0){ $checkmenu = 1; }
		else if($Filas2 > 0){ $checkmenu = 2; }
		else{ $checkmenu = $objDatabase->AgregarMenu($Conexion,$objMenu); }

		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn2']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objMenu = new Menu();

		$idmenu = $_POST['idMenu'];
		$pastel = $_POST['Pastel'];
		$categoria = $_POST['Categoria'];
		$precio = $_POST['Precio'];

		$objMenu->setidMenu($idmenu);
		$objMenu->setPastel($pastel);
		$objMenu->setCategoria($categoria);
		$objMenu->setPrecio($precio);

		$Conexion = $objConexion->Conexion_Database();
		$checkmenu = $objDatabase->ModificarMenu($Conexion,$objMenu);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objMenu = new Menu();

		$btn = $_POST['pastel'];

		$objMenu->setPastel($btn);
		$Conexion = $objConexion->Conexion_Database();
		$objMenu->setidMenu($btn);

		$checkmenu = $objDatabase->BorrarMenu($Conexion,$objMenu);
		mysqli_close($Conexion); 
	}
?>