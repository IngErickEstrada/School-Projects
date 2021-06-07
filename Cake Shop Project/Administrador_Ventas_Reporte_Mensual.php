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
	
	<form name="formulario" id="form" method="post">
		<div class="container Textos">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-12">
					<input type="month" name="Fecha">
					<button class="btn btn-primary" type="submit" name="btnfecha">Reporte Ventas</button>
					<a href="Administrador_Ventas_Reportes.php"><input class="btn btn-danger" value="Regresar"></a>
				</article>			
			</section>	
		</div>
	</form>

	<?php
		if(isset($_POST['btnfecha']))
		{			
	?>
			<form name="formulario" id="form" method="post">
				<div class="container Textos">
					<section class="row">
						<article class="col-xs-12 col-sm-12 col-md-12">
							<div>
								<?php
									$Date = $_POST['Fecha'];
									$Mes = substr($Date,0);

									$objConexion = new ConexionDB();
									$Conexion = $objConexion->Conexion_Database();
									$SQL = $Conexion->query("SELECT Fecha_Venta FROM ventas_realizadas");
									$Result = mysqli_fetch_assoc($SQL);
									$Fecha1 = $Result['Fecha_Venta'];
									$Fecha2 = substr($Fecha1,0,7);

									if($Mes == $Fecha2)
									{ 
								?>	
							</div>

									<div class="table-responsive">
										<table class="table table-warning table-bordered">
											<tr>
												<th>id Venta</th>
												<th>id Pastel</th>
												<th>id Factura</th>
												<th>Cantidad</th>
												<th>Total</th>
												<th>Fecha Venta</th>
											</tr>
											<tr>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL1 = $Conexion->query("SELECT id_Venta FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result1 = mysqli_fetch_array($SQL1))
														{
															$idventa = $Result1['id_Venta'];
															echo $idventa;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL2 = $Conexion->query("SELECT id_Pastel FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result2 = mysqli_fetch_array($SQL2))
														{
															$Ventas2 = $Result2['id_Pastel'];
															echo $Ventas2;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL3 = $Conexion->query("SELECT id_Factura FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result3 = mysqli_fetch_array($SQL3))
														{
															$Ventas3 = $Result3['id_Factura'];
															echo $Ventas3;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL4 = $Conexion->query("SELECT Cantidad FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result4 = mysqli_fetch_array($SQL4))
														{
															$Ventas4 = $Result4['Cantidad'];
															echo $Ventas4;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL6 = $Conexion->query("SELECT Total FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result6 = mysqli_fetch_array($SQL6))
														{
															$Ventas6 = $Result6['Total'];
															echo "$"; echo $Ventas6;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
												<th>
													<?php
														$objConexion = new ConexionDB();
														$Conexion = $objConexion->Conexion_Database();
														$SQL7 = $Conexion->query("SELECT Fecha_Venta FROM ventas_realizadas WHERE '$Mes'='$Fecha2'");
														while($Result7 = mysqli_fetch_array($SQL7))
														{
															$Ventas7 = $Result7['Fecha_Venta'];
															echo $Ventas7;
													?>	
															<br>
													<?php				
														}
													?>
												</th>
											</tr>
										</table>
									</div>
								<?php
								 	}
								 	else
								 	{
								 		$checkreporte = 1;
								 	}
								?>								
						</article>
					</section>
				</div>
			</form>
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