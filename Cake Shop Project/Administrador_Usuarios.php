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

	<form name="Formulario" id="form" action="" method="post">
		<div class="container Textos">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-12">
					<u>Seleccione una Opción</u>
					<select class="btn" name="User">
						<option value="Modificar">Modificar Usuario</option>
						<option value="Borrar">Borrar Usuario</option>
						<option value="Buscar">Buscar Usuario</option>
					</select>
					<button class="btn btn-primary" name="Enviar">Enviar</button>
				</article>
			</section>
		</div>
	</form>

	<?php 
		if(isset($_POST['Enviar']))
		{
			$Modificar_User = $_POST['User'];

			if($Modificar_User == "Modificar")
			{
				header("location:Administrador_Usuarios_Modificar.php");
			}
			
			else if($Modificar_User == "Borrar")
			{
	?>
				<form name="formularioio" id="form" action="" method="post">	
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
								<button class="btn btn-danger" type="submit" name="btnborrar">Eliminar Cliente</button>
								
								<div>
									<img style="width: 530px; height: 350px; margin-top: 3%;" src="Imagenes/Usuarios Administrador/Borrar Usuario.png">
								</div>
							</article>
						</section>						
					</div>
				</form>
	<?php
			}

			else if($Modificar_User == "Buscar")
			{
				header("location:Administrador_Usuarios_Buscar.php");
			}
		}

		else
		{
	?>	
			<div class="container">
				<section class="row">
					<article class="col-xs-12 col-sm-12 col-md-12">
						<img style="width: 1000px; height: 450px; margin-top: 2%; margin-left: 5%;" src="Imagenes/Usuarios Administrador/Usuarios.jpg">
					</article>			
				</section>
			</div>
	<?php	
		}
	?>

	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Validaciones.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var user = [<?php echo $checkuser; ?>];

		if(user == 6)
		{
			alert("Cliente eliminado");
		}
		else if(user == 7)
		{
			alert("Error al intentar borrar cliente");
		}
	</script>
</body>
</html>