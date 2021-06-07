<?php  
	include "Conexion.php";
	error_reporting(0);

	if(isset($_POST['Login']))
	{
		$User = $_POST['Usuario'];
		$Password = $_POST['Password'];

		$objConexion = new ConexionDB();
		$Conexion = $objConexion->Conexion_Database();

		$SQL1 = "SELECT * FROM usuarios WHERE Nombre_Usuario = '$User' and Contrasena = '$Password' and Perfil = 'Administrador'";
		$Resultado1 = mysqli_query($Conexion,$SQL1);
		$Filas1 = mysqli_num_rows($Resultado1);

		$SQL2 = "SELECT * FROM usuarios WHERE Nombre_Usuario = '$User' and Contrasena = '$Password' and Perfil = 'Cliente'";
		$Resultado2 = mysqli_query($Conexion,$SQL2);
		$Filas2 = mysqli_num_rows($Resultado2);

		if($Filas1 == 0)
		{
			$checksesion = 1;	
			if($Filas2 > 0)
			{
				session_start();
				$_SESSION['Usuario'] = $User;
				header("location:Inicio.php");
			}
		}
		else if($Filas1 > 0)
		{	
			session_start();
			$_SESSION['Usuario'] = $User;
			header("location:Administrador_Usuarios.php");
		}
	}
?>