<?php 
	include "Conexion.php";
	include "Database.php";
	error_reporting(0);

	class Usuarios
	{
		//Variables de usuarios
		private $_idUsuario;
		private $_NombreUsuario;
		private $_Contraseña;
		private $_Perfil;
		private $_Nombres;
		private $_Sexo;
		private $_Edad;
		private $_Telefono;
		private $_Correo;

		//Metodos
		public function getidUsuario(){return $this->_idUsuario;}
		public function getNombreUsuario(){return $this->_NombreUsuario;}
		public function getContraseña(){return $this->_Contraseña;}
		public function getPerfil(){return $this->_Perfil;}
		public function getNombres(){return $this->_Nombres;}
		public function getSexo(){return $this->_Sexo;}
		public function getEdad(){return $this->_Edad;}
		public function getTelefono(){return $this->_Telefono;}
		public function getCorreo(){return $this->_Correo;}

		public function setidUsuario($idUsuario){$this->_idUsuario = $idUsuario;}
		public function setNombreUsuario($NombreUsuario){$this->_NombreUsuario = $NombreUsuario;}
		public function setContraseña($Contraseña){$this->_Contraseña = $Contraseña;}
		public function setPerfil($Perfil){$this->_Perfil = $Perfil;}
		public function setNombres($Nombres){$this->_Nombres = $Nombres;}
		public function setSexo($Sexo){$this->_Sexo = $Sexo;}
		public function setEdad($Edad){$this->_Edad = $Edad;}
		public function setTelefono($Telefono){$this->_Telefono = $Telefono;}
		public function setCorreo($Correo){$this->_Correo = $Correo;}
	}

	if(isset($_POST['btn']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objUsuario = new Usuarios();

		$nombreusuario = $_POST['Usuario'];
		$contraseña = $_POST['Contraseña'];
		$perfil = $_POST['Perfil'];
		$nombres = $_POST['Nombres'];
		$sexo = $_POST['Sexo'];
		$edad = $_POST['Edad'];
		$telefono = $_POST['Telefono'];
		$correo = $_POST['Correo'];

		$objUsuario->setNombreUsuario($nombreusuario);
		$objUsuario->setContraseña($contraseña);
		$objUsuario->setPerfil($perfil);
		$objUsuario->setNombres($nombres);
		$objUsuario->setSexo($sexo);
		$objUsuario->setEdad($edad);
		$objUsuario->setTelefono($telefono);
		$objUsuario->setCorreo($correo);

		$Conexion = $objConexion->Conexion_Database();

		$SQL2 = "SELECT Nombre_Usuario FROM usuarios WHERE Nombre_Usuario = '$nombreusuario'";
		$Resultado2 = mysqli_query($Conexion,$SQL2);
		$Filas2 = mysqli_num_rows($Resultado2);

		if($Filas2 > 0){ $checkuser = 1; }
		else{ $checkuser = $objDatabase->AgregarUsuarios($Conexion,$objUsuario); }

		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btn2']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objUsuario = new Usuarios();

		$idusuario = $_POST['idUsuario'];
		$nombreusuario = $_POST['Usuario'];
		$contraseña = $_POST['Contraseña'];
		$perfil = $_POST['Perfil'];
		$nombres = $_POST['Nombres'];
		$sexo = $_POST['Sexo'];
		$edad = $_POST['Edad'];
		$telefono = $_POST['Telefono'];
		$correo = $_POST['Correo'];

		$objUsuario->setidUsuario($idusuario);
		$objUsuario->setNombreUsuario($nombreusuario);
		$objUsuario->setContraseña($contraseña);
		$objUsuario->setPerfil($perfil);
		$objUsuario->setNombres($nombres);
		$objUsuario->setSexo($sexo);
		$objUsuario->setEdad($edad);
		$objUsuario->setTelefono($telefono);
		$objUsuario->setCorreo($correo);

		$Conexion = $objConexion->Conexion_Database();
		$checkuser = $objDatabase->ModificarUsuarios($Conexion,$objUsuario);
		mysqli_close($Conexion); 
	}

	else if(isset($_POST['btnborrar']))
	{
		$objConexion = new ConexionDB();
		$objDatabase = new MetodosDB();
		$objUsuario = new Usuarios();

		$btn = $_POST['usuario'];

		$objUsuario->setNombres($btn);
		$Conexion = $objConexion->Conexion_Database();
		$objUsuario->setidUsuario($btn);

		$checkuser = $objDatabase->BorrarUsuario($Conexion,$objUsuario);
		mysqli_close($Conexion); 
	}
?>