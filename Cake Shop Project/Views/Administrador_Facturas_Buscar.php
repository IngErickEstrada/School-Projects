<?php 
	include "PHP/Factura.php";
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
					<u>Factura: </u>
					<select class="btn" name="factura">
						<option value="0">Seleccione una factura</option>
						<?php
							$objConexion = new ConexionDB();
							$Conexion = $objConexion->Conexion_Database();
							$SQLF = $Conexion->query("SELECT * FROM facturas as f, usuarios as u WHERE f.id_Usuario=u.id_Usuario");
							while ($facturas = mysqli_fetch_array($SQLF)) 
							{
							 	echo '<option value="'.$facturas[id_Factura].'">'.$facturas[Nombre].'</option>';
							} 
						?>						
					</select>	
					<button class="btn btn-primary" type="submit" name="btnfactura">Enviar</button>						
				</article>							
			</section>
		</div>
	</form>
				
	<?php 
		if(isset($_POST['btnfactura']))
		{
			$Factura = $_POST['factura'];
			$SQLP1 = "CALL BuscarFactura ('$Factura')";
			$objConexion = new ConexionDB();
			$Conexion = $objConexion->Conexion_Database();
			$SQLP2 = $Conexion->query($SQLP1);
			$Result = mysqli_fetch_assoc($SQLP2);

			$auxidfactura = $Result['id_Factura'];
			$auxidusuario = $Result['id_Usuario'];
			$auxfactura = $Result['Factura'];
			$auxfecha = $Result['Fecha'];
		}	
	?>

	<form name="formulario" id="form" action="" method="post">
		<div class="container">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>id Factura</u></label>
						<input class="form-control" type="text" id="idFactura" name="idFactura" value="<?php echo $auxidfactura; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>id Usuario</u></label>
						<input class="form-control" type="text" id="idUsuario" name="idUsuario" value="<?php echo $auxidusuario; ?>" readonly>

						<a href="Administrador_Facturas.php"><input class="btn btn-block btn-danger" style="margin-top: 10%;" value="Regresar"></a>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Factura</u></label>
						<input class="form-control" type="text" id="Factura" name="Factura" value="<?php echo $auxfactura; ?>" readonly>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Fecha</u></label>
						<input class="form-control" type="text" id="Fecha" name="Fecha" value="<?php echo $auxfecha; ?>" readonly>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-6">
					<div class="form-group">
						<img style="width: 535px; height: 300px; margin-top: 5.1%;" src="Imagenes/Factura Administrador/Buscar Factura.jpg">
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