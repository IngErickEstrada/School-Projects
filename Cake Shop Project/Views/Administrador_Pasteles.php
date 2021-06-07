<?php
	include "PHP/Pasteles.php";
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
						<select class="btn" name="Pasteles">
							<option value="Agregar">Agregar Detalle Pasteles</option>
							<option value="Borrar">Borrar Detalle Pasteles</option>
							<option value="Buscar">Buscar Detalle Pasteles</option>
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
			$Modificar_pasteles = $_POST['Pasteles'];

			if ($Modificar_pasteles == "Agregar") 
			{
	?>
				<form name="formulario" id="form" action="" method="post" onsubmit="return Validar('Validar_Pasteles');">
					<div class="container">
						<section class="row">
							<article class="col-xs-12 col-sm-12 col-md-3">
								<div class="form-group Textos">
									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 40%;"><u>id Pastel</u></label>
									<input class="form-control" type="text" id="idPastel" name="idPastel" placeholder="Ej: 1">
									
									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>id Menu</u></label>
									<input class="form-control" type="text" id="idMenu" name="idMenu" placeholder="Ej: 1">
								</div>
							</article>

							<article class="col-xs-12 col-sm-12 col-md-3">
								<div class="form-group Textos">
									<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 40%;"><u>id Almacén</u></label>
									<input class="form-control" type="text" id="idAlmacen" name="idAlmacen" placeholder="Ej: 1">

									<button class="btn btn-block btn-danger" type="reset" style="margin-top: 10%;">Borrar Datos</button>

									<button class="btn btn-block btn-primary" type="submit" style="margin-top: 4%;" id="btn" name="btn">Agregar Pastel</button>
								</div>
							</article>

							<article class="col-xs-12 col-sm-12 col-md-6">
								<div class="form-group">
									<img style="width: 480px; margin-top: 5%;" src="Imagenes/Pasteles Administrador/Agregar Pasteles.jpg">
								</div>
							</article>
						</section>
					</div>
				</form>
	<?php
			}

			else if($Modificar_pasteles == "Borrar")
			{
	?>			
				<form name="formulario" id="form" action="" method="post">
					<div class="container">
						<section class="row Textos">
							<article class="col-xs-12 col-sm-12 col-md-12">
								<u>Detalle Pastel: </u>
								<select class="btn" name="detalle">
								<option value="0">Seleccione un detalle pastel</option>
								<?php
									$objConexion = new ConexionDB();
									$Conexion = $objConexion->Conexion_Database();
									$SQLA = $Conexion->query("SELECT * FROM pasteles as p, menu as m WHERE m.id_menu=p.id_menu");
									while ($pasteles = mysqli_fetch_array($SQLA)) 
									{
									 	echo '<option value="'.$pasteles[id_Pastel].'">'.$pasteles[Pastel].'</option>';
									} 
								?>						
								</select>	
								<button class="btn btn-danger type="submit" name="btnborrar">Eliminar Detalle Pastel</button>

								<div>
									<img style="width: 1000px; height: 360px; margin-top: 3%;" src="Imagenes/Pasteles Administrador/Borrar Pasteles.jpg">
								</div>						
							</article>							
						</section>
					</div>
				</form>
	<?php			
			}

			else if($Modificar_pasteles == "Buscar")
			{
				header("location:Administrador_Pasteles_Buscar.php");
			}
		}

		else
		{
	?>		
			<div class="container">
				<section class="row">
					<article class="col-xs-12 col-sm-12 col-md-12">
						<img style="width: 1000px; height: 450px; margin-top: 2%; margin-left: 5%;" src="Imagenes/Pasteles Administrador/Pasteles.jpg">
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
		var pastel = [<?php echo $checkpasteles; ?>];

		if(pastel == 1)
		{
			alert("id pastel ya registrada, intente con otra id pastel");
		}
		else if(pastel == 2)
		{
			alert("Producto registrado");
		}
		else if(pastel == 3)
		{
			alert("Error al intentar registrar el producto");
		}
		else if(pastel == 4)
		{
			alert("Detalle pastel eliminado");
		}
		else if(pastel == 5)
		{
			alert("Error al intentar eliminar el detalle pastel seleccionado");
		}
	</script>
</body>
</html>