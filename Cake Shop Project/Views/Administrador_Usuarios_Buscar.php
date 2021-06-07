<?php 
	include "PHP/Usuario.php";
	session_start();
	$User = $_SESSION['Usuario'];
?>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
	<link rel="stylesheet" href="CSS/CSS.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<title>Cake Shop</title>
</head>
<body><meta charset="utf-8">
	<img class="Img-Body" src="Imagenes/Body.jpg">	
	
	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-12">
				<h1 class="Titulos">Cake Shop -> Administrador <?php if($User == NULL){} else {echo "-> "; echo $User;}?></h1>		
			</article>			
		</section>	
	</div>

	<header class="col-xs-12 col-sm-12 col-md-12">	
		<nav>
			<ul class="Menu">		
				<li><a href="Administrador_Usuarios.php">Usuarios</a></li>
				<li><a href="Administrador_Menu.php">Menu</a></li>
				<li><a href="Administrador_Almacen.php">Almacén</a></li>
				<li><a href="Administrador_Pasteles.php">Pasteles</a></li>
				<li><a href="Administrador_Facturas.php">Facturas</a></li>
				<li><a href="Administrador_Ventas.php">Ventas</a></li>
				<li><a href="Cerrar_Sesion.php">Cerrar S</a></li>
			</ul>

			<div class="Barra">
				<input type="text" id="Barra" placeholder="Buscador">
			</div>
		</nav>
	</header>
	
	<form name="formulario" id="form" action="" method="post">
		<div class="container">
			<section class="row Textos">
				<article class="col-xs-12 col-sm-12 col-md-12">
					<u>Cliente: </u>
					<select class="btn" name="usuario">
						<option value="0">Seleccione un cliente</option>
						<?php
							$objConexion = new ConexionDB();
							$Conexion = $objConexion->Conexion_Database();
							$SQLU = $Conexion->query("SELECT * FROM usuarios");
							while ($usuarios = mysqli_fetch_array($SQLU)) 
							{
							 	echo '<option value="'.$usuarios[id_Usuario].'">'.$usuarios[Nombre].'</option>';
							} 
						?>						
					</select>	
					<button class="btn btn-primary" type="submit" name="btnuser">Buscar Cliente</button>						
				</article>							
			</section>
		</div>
	</form>
				
	<?php 
		if(isset($_POST['btnuser']))
		{
			$User = $_POST['usuario'];
			$SQLP1 = "CALL BuscarUsuario ($User)";
			$objConexion = new ConexionDB();
			$Conexion = $objConexion->Conexion_Database();
			$SQLP2 = $Conexion->query($SQLP1);
			$Result = mysqli_fetch_assoc($SQLP2);

			$auxidusuario = $Result['id_Usuario'];
			$auxnombreusuario = $Result['Nombre_Usuario'];
			$auxcontraseña = $Result['Contrasena'];
			$auxperfil = $Result['Perfil'];
			$auxnombres = $Result['Nombre'];
			$auxsexo = $Result['Sexo'];
			$auxedad = $Result['Edad'];
			$auxtelefono = $Result['Telefono'];
			$auxcorreo = $Result['Correo'];
		}	
	?>

	<form name="formulario" id="form" action="" method="post">
		<div class="container">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Id Usuario</u></label>
						<input class="form-control" type="text" id="idUsuario" name="idUsuario" value="<?php echo $auxidusuario; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Nombre Usuario</u></label>
						<input class="form-control" type="text" id="Usuario" name="Usuario" value="<?php echo $auxnombreusuario; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Nombre</u></label>
						<input class="form-control" type="text" id="Nombres" name="Nombres" value="<?php echo $auxnombres; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Sexo</u></label>
						<input class="form-control" type="text" id="Sexo" name="Sexo" value="<?php echo $auxsexo; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Teléfono</u></label>
						<input class="form-control" type="text" id="Telefono" name="Telefono" value="<?php echo $auxtelefono; ?>" readonly>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label <label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Perfil</u></label>
						<input class="form-control" type="text" id="Perfil" name="Perfil" value="<?php echo $auxperfil; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Contraseña</u></label>
						<input class="form-control" type="text" id="Contraseña" name="Contraseña" value="<?php echo $auxcontraseña; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Edad</u></label>
						<input class="form-control" type="text" id="Edad" name="Edad" value="<?php echo $auxedad; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Correo electrónico</u></label>
						<input class="form-control" type="text" id="Correo" name="Correo" value="<?php echo $auxcorreo; ?>" readonly>

						<a href="Administrador_Usuarios.php"><input class="btn btn-block btn-danger" style="margin-top: 20%;" value="Regresar"></a>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-6">
					<div class="form-group">
						<img style="width: 530px; height: 500px; margin-top: 0%;" src="Imagenes/Usuarios Administrador/Buscar Usuario.png">
					</div>
				</article>
			</section>
		</div>
	</form>

	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Validaciones.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>