<?php 
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
				<h1 class="Titulos">Cake Shop -> Promociones <?php if($User == NULL){} else {echo "-> "; echo $User;}?></h1>				
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

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-12">
				<p class="Textos"><u>Déjate consentir y aprovecha las promociones que Cake Shop tiene para tí y tu familia</u></p>				
			</article>
		</section>
	</div>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-6">
				<h1 class="Subtitulos" style="margin-top: 2%;">Lunes de disfrutar y compartir<img class="Promocion" src="Imagenes/Promociones/Promocion 5.jpg"></h1>
				<h1 class="Subtitulos" style="margin-top: 2%;">Pastel + Gelatina = Momentos<img class="Promocion" src="Imagenes/Promociones/Promocion 2.jpg"></h1>
			</article>
				
			<article class="col-xs-12 col-sm-12 col-md-6">
				<h1 class="Subtitulos" style="margin-top: 2%;">Martes de descuento<img class="Promocion" src="Imagenes/Promociones/Promocion 1.jpg"></h1>
				<h1 class="Subtitulos" style="margin-top: 2%;">Día del cumpleañero<img class="Promocion" src="Imagenes/Promociones/Promocion 4.jpg"></h1>
			</article>
		</section>
	</div>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-3"></article>
			<article class="col-xs-12 col-sm-12 col-md-6">
				<h1 class="Subtitulos" style="margin-top: 2%;">Pedídos Especiales<img class="Promocion" src="Imagenes/Promociones/Promocion 3.jpg"></h1>
			</article>
			<article class="col-xs-12 col-sm-12 col-md-3"></article>
		</section>
	</div>

	<script src="JavaScript/Menu.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>