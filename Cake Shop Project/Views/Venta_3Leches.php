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
						<table class="table">
							<tr>
								<th>Producto</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>IVA</th>
								<th>Subtotal</th>
							</tr>
							<tr>
								<td>
									<img class="Pasteles_Cliente" src="Imagenes/Menu/3 Leches.png">
									<u>3 Leches</u>
								</td>
								<td>
									<?php 
										$objConexion = new ConexionDB();
										$Conexion = $objConexion->Conexion_Database();
										$SQL = $Conexion->query("SELECT Precio FROM menu WHERE id_menu = 2"); 
										$Result = mysqli_fetch_assoc($SQL);
										$Precio = $Result['Precio'];
										echo "$"; echo $Precio; echo ".00";
									?>
								</td>
								<td>
									<input class="btn btn-sm" type="number" name="Cantidad2">
								</td>
								<td>
									<?php
										$P = $Precio;
										$Cantidad = $_POST['Cantidad2'];
										if($Cantidad == 0 || $Cantidad < 0){ $checkventa = 5; }
										else
										{
											$IVA = ($P*$Cantidad)*0.16;
											$Subtotal = ($P*$Cantidad)+$IVA; 
											echo $IVA;
										}
									?>	
								</td>
								<td><?php echo $Subtotal; ?></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><a href="MenuCliente.php"><input class="btn btn-danger" name="regresar" value="Regresar Menu"></a></td>
								<td><input class="btn btn-primary" type="submit" name="btnagregar2" value="Agregar"></td>
								<td><a href="Venta_Concretar.php"><input class="btn btn-success" name="continuar" value="Continuar Compra"></a></td>
							</tr>
						</table>
					</div>
				</article>
			</section>
		</div>
	</form>

	<script src="JavaScript/Menu.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var venta = [<?php echo $checkventa; ?>];

		if(venta == 1)
		{
			alert("Venta agregada");
		}
		else if(venta == 2)
		{
			alert("Error al intentar agregar venta");
		}
		else if(venta == 5)
		{
			alert("La cantidad a comprar debe ser mayor a 0");
		}
	</script>
</body>