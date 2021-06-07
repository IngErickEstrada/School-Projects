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
				<h1 class="Titulos">Cake Shop -> Inicio <?php if($User == NULL){} else {echo "-> "; echo $User;}?></h1>				
			</article>			
		</section>	
	</div>

	<header class="col-xs-12 col-sm-12 col-md-12">	
		<nav>
			<ul class="Menu">		
				<li><a href="Inicio.php">Inicio</a></li>
				<li><a href="MenuCliente.php">Menu</a></li>
				<li><a href="Venta.php">Ventas</a></li>
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
				<h1 class="Titulos">Deléitate</h1>
				<img class="Inicio" src="Imagenes/Inicio.jpg">
				<h1 class="Subtitulos" style="margin-top: 1%"><u>Hacemos tus dias felices</h1></u>
				<p class="Textos" style="margin-top: 1%">Cake Shop es una empresa orgullosamente mexicana, en la que trabajamos para ofrecerte productos alimenticios de la más alta calidad (con certificación de la Planta ISO22000:2005), con el mejor servicio, rodeado de un entorno agradable y cálido en nuestras instalaciones. A través de nuestros productos en línea y de temporada, así como de nuestras constantes promociones, en Cake Shop hacemos tus días felices.
				Nuestro servicio de excelencia está disponible en más de 35 sucursales en el occidente del país, abiertas y con servicio a domicilio los 365 días del año, para que nuestra marca siempre te acompañe en los momentos de celebración más especiales de tu vida.</p>
			</article>
		</section>		
	</div>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-6">
				<h1 class="Subtitulos" style="margin-top: 5%;"><u>Síguenos en nuestras redes</h1></u>
			</article>

			<article class="col-xs-12 col-sm-12 col-md-6">
				<center>
					<a href="https://www.facebook.com/okpasteleria"><img class="Iconos" src="Imagenes/Iconos/Faceebok.png"></a>
					<a href="https://twitter.com/Pasteleriaok"><img class="Iconos" style="width: 75px; margin-top: 5%; margin-left: 30px;" src="Imagenes/Iconos/Twitter.png"></a>
					<a href="http://www.instagram.com/pasteleriaok"><img class="Iconos" style="width: 90px; margin-top: 5.5%; margin-left: 30px;" src="Imagenes/Iconos/Instagram.png"></a>
				</center>
			</article>
		</section>
	</div>
	
	<script src="JavaScript/Menu.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>