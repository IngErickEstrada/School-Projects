<?php
	include "PHP/Ventas.php";	
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
		</se	ction>	
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
						<u>Seleccionar reporte por</u>
						<select class="btn" name="Reportes">
							<option value="Dia">Día</option>
							<option value="Semana">Semana</option>
							<option value="Mes">Mes</option>
						</select>
						<button class="btn btn-primary" name="Enviar">Enviar</button>
						<a href="Administrador_Ventas.php"><input class="btn btn-danger" value="Regresar"></a>
					</div>
				</article>
			</section>
		</div>
	</form>

	<?php
		if(isset($_POST['Enviar']))
		{			
			$Modificar_Reporte = $_POST['Reportes'];

			if($Modificar_Reporte == "Dia")
			{
				header("location:Administrador_Ventas_Reporte_Dia.php");
			}

			else if($Modificar_Reporte == "Semana")
			{
				header("location:Administrador_Ventas_Reporte_Semanal.php");
			}

			else if($Modificar_Reporte == "Mes")
			{
				header("location:Administrador_Ventas_Reporte_Mensual.php");
			}
		}

		else
		{
	?>		
			<div class="container">
				<section class="row">
					<article class="col-xs-12 col-sm-12 col-md-12">
						<img style="width: 1000px; height: 400px; margin-top: 3%; margin-left: 5%;" src="Imagenes/Ventas Administrador/Ventas.jpg">
					</article>			
				</section>	
			</div>
	<?php 		
		}
	?>

	<script src="JavaScript/Menu.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var reporte = [<?php echo $checkreporte; ?>];

		if(reporte == 1)
		{
			alert("No hay ventas para la fecha seleccionada");
		}	
	</script>	
</body>
</html>