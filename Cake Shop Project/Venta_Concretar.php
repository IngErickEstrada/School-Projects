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
				<h1 class="Titulos">Cake Shop -> Ventas <?php if($User == NULL){} else {echo "-> "; echo $User;}?></h1>
			</article>			
		</section>	
	</div>

	<header class="col-xs-12 col-sm-12 col-md-12">	
		<nav>
			<ul class="Menu">		
				<li><a href="Inicio.php">Inicio</a></li>
				<li><a href="MenuCliente.php">Menu</a></li>
				<li><a href="Promociones.php">Promociones</a></li>
				<li><a href="Contacto.php">Contacto</a></li>
				<li><a href="Cerrar_Sesion.php">Cerrar Sesión</a></li>
			</ul>

			<div class="Barra">
				<input type="text" id="Barra" placeholder="Buscador">
			</div>
		</nav>
	</header>
	
	<form name="formulario" id="form" action="" method="post">
		<div class="container Textos">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-12">
					<div class="table-responsive">
						<table class="table table-warning table-bordered">
							<tr>
								<th>Producto(s)</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>SubTotal</th>
							</tr>
							<tr>
								<th>
									<?php
										$objConexion = new ConexionDB();
										$Conexion = $objConexion->Conexion_Database();
										$SQL1 = $Conexion->query("SELECT Pastel FROM menu as m, pasteles as p, ventas as v, facturas as f, usuarios as u WHERE m.id_Menu=p.id_Menu and p.id_Pastel=v.id_Pastel and v.id_Factura=f.id_Factura and f.id_Usuario=u.id_Usuario and u.Nombre_Usuario='$User'");
										while($Result1 = mysqli_fetch_array($SQL1))
										{
											$Pasteles = $Result1['Pastel'];
											echo $Pasteles;
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
										$SQL2 = $Conexion->query("SELECT Precio FROM menu as m, pasteles as p, ventas as v, facturas as f, usuarios as u WHERE m.id_Menu=p.id_Menu and p.id_Pastel=v.id_Pastel and v.id_Factura=f.id_Factura and f.id_Usuario=u.id_Usuario and u.Nombre_Usuario='$User'");
										while($Result2 = mysqli_fetch_array($SQL2))
										{
											$Precio = $Result2['Precio'];
											echo "$"; echo $Precio; echo ".00";
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
										$SQL3 = $Conexion->query("SELECT Cantidad FROM ventas as v, facturas as f, usuarios as u WHERE v.id_Factura=f.id_Factura and f.id_Usuario=u.id_Usuario and u.Nombre_Usuario='$User'");
										while($Result3 = mysqli_fetch_array($SQL3))
										{
											$Cantidad = $Result3['Cantidad'];
											echo $Cantidad;
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
										$SQL4 = $Conexion->query("SELECT Total FROM ventas as v, facturas as f, usuarios as u WHERE v.id_Factura=f.id_Factura and f.id_Usuario=u.id_Usuario and u.Nombre_Usuario='$User'");
										while($Result4 = mysqli_fetch_array($SQL4))
										{
											$Subtotal = $Result4['Total'];
											$Total += $Result4['Total'];
											echo "$"; echo $Subtotal;
									?>		
											<br>
									<?php				
										}
									?>
								</th>
							</tr>
							<tr>
								<th></th>
								<th></th>
								<th>Total</th>
								<th><?php echo "$"; echo $Total; ?></th>
							</tr>
							<tr>
								<th></th>
								<th></th>
								<th><a href="MenuCliente.php"><input class="btn btn-danger" value="Agregar Pastel"></a></th>
								<th><button class="btn btn-success" name="Comprar">Comprar</button></th>
							</tr>
						</table>
					</div>
				</article>
			</section>
		</div>
	</form>

	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Validaciones.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var venta = [<?php echo $checkventa; ?>];

		if(venta == 5)
		{
			alert("Venta realizada");
		}	
	</script>	
</body>