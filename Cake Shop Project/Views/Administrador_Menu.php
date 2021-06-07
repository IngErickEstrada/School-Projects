<?php 
	include "PHP/Menu.php";
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
					<div>
						<u>Seleccione una Opción</u>
						<select class="btn" name="Menu">
							<option value="Agregar">Agregar Pastel</option>
							<option value="Modificar">Modificar Pastel</option>
							<option value="Borrar">Borrar Pastel</option>
							<option value="Buscar">Buscar Pastel</option>
						</select>
						<button class="btn btn-primary" name="Enviar">Enviar</button>
					</div>
				</article>
			</section>
		</div>
	</form>

	<?php 
		if(isset($_POST['Enviar']))
		{
			$Modificar_menu = $_POST['Menu'];

			if ($Modificar_menu == "Agregar") 
			{
	?>
				<form name="formulario" id="form" action="" method="post" onsubmit="return Validar('Validar_Menu');">
					<div class="container">
						<section class="row">
							<article class="col-xs-12 col-sm-12 col-md-3">
								<div class="form-group Textos">
									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>id Menu</u></label>
									<input class="form-control" type="text" id="idMenu" name="idMenu" placeholder="Ej: 1">

									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Categoria</u></label>
									<input class="form-control" type="text" id="Categoria" name="Categoria" placeholder="Ej: Delux">

									<button class="btn btn-block btn-danger" type="reset" style="margin-top: 10%;">Borrar Datos</button>
								</div>
							</article>

							<article class="col-xs-12 col-sm-12 col-md-3">
								<div class="form-group Textos">
									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Nombre Pastel</u></label>
									<input class="form-control" type="text" id="Pastel" name="Pastel" placeholder="Ej: Chocolate">

									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Precio</u></label>
									<input class="form-control" type="text" id="Precio" name="Precio" placeholder="Ej: $99.99">

									<button class="btn btn-block btn-primary" type="submit" style="margin-top: 10%;" id="btn" name="btn">Registrar Pastel</button>
								</div>
							</article>

							<article class="col-xs-12 col-sm-12 col-md-6">
								<div class="form-group">
									<img style="width: 535px; height: 255px; margin-top: 5.1%;" src="Imagenes/Menu Administrador/Agregar Menu.jpg">
								</div>
							</article>
						</section>
					</div>
				</form>
	<?php
			}

			else if($Modificar_menu == "Modificar")
			{
				header("location:Administrador_Menu_Modificar.php");	
			}

			else if($Modificar_menu == "Borrar")
			{
	?>
				<form name="formularioio" id="form" action="" method="post">	
					<div class="container">
						<section class="row Textos">
							<article class="col-xs-12 col-sm-12 col-md-12">
								<u>Pastel: </u>
								<select class="btn" name="pastel">
									<option value="0">Seleccione un pastel</option>
									<?php
										$objConexion = new ConexionDB();
										$Conexion = $objConexion->Conexion_Database();
										$SQLM = $Conexion->query("SELECT * FROM menu");
										while ($pasteles = mysqli_fetch_array($SQLM)) 
										{
							 				echo '<option value="'.$pasteles[id_Menu].'">'.$pasteles[Pastel].'</option>';
										} 
									?>						
								</select>
								<button class="btn btn-danger" type="submit" name="btnborrar">Eliminar Pastel</button>
								
								<div>
									<img style="width: 1000px; height: 360px; margin-top: 3%;" src="Imagenes/Menu Administrador/Borrar Menu.jpeg">
								</div>
							</article>
						</section>						
					</div>
				</form>
	<?php	
			}

			else if($Modificar_menu == "Buscar")
			{
				header("location:Administrador_Menu_Buscar.php");
			}
		}

		else
		{
	?>
			<div class="container">
				<section class="row">
					<article class="col-xs-12 col-sm-12 col-md-12">
						<img style="width: 1000px; height: 450px; margin-top: 2%; margin-left: 5%;" src="Imagenes/Menu Administrador/Menu.jpg">
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
		var menu = [<?php echo $checkmenu; ?>];

		if(menu == 1)
		{
			alert("id menu ya registrada, intente con otra id menu");
		}
		else if(menu == 2)
		{
			alert("El pastel que desea registrar ya se encuentra registrado");
		}
		else if(menu == 3)
		{
			alert("Pastel registrado");
		}
		else if(menu == 4)
		{
			alert("Error al intentar registrar el pastel");
		}
		else if(menu == 7)
		{
			alert("Pastel eliminado");
		}
		else if(menu == 8)
		{
			alert("Error al intentar eliminar el pastel seleccionado");
		}
	</script>
</body>
</html>