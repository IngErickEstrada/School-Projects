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
				<h1 class="Titulos">Cake Shop -> Contacto <?php if($User == NULL){} else {echo "-> "; echo $User;}?></h1>				
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
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14929.719084367274!2d-103.43186131337339!3d20.692765653980704!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x2394ed79e4e60ad1!2sSUSPIROS+PASTELERIAS!5e0!3m2!1ses-419!2smx!4v1526881972077" width="1000" height="450" frameborder="0" style="position: relative; margin-top: 1.5%; margin-left: 4.8%;" allowfullscreen></iframe>
				<h1 class="Subtitulos" style="margin-top: 1%;">Ubícanos en nuestra sucursal.</h1>
				<h1 class="Subtitulos"><u>Gracias por contactarnos, dejanos tus datos.</u></h1>
				<h1 class="Textos" style="margin-top: 3%">Nuestro equipo está capacitado para ayudarte y resolver tus dudas o atender tus comentarios. Llena el formulario, a la brevedad nos pondremos en contacto contigo.</h1>
			</article>
		</section>
	</div>

	<form name="formulario" id="form" method="get">
		<div class="container">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-1"></article>
				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;" for="Nombres"><u>Nombre completo*</u></label>
						<input class="form-control" type="text" id="Nombres" placeholder="Escribe tu nombre completo">
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;" for="Correo"><u>Correo electrónico*</u></label>
						<input class="form-control" type="text" id="Correo" placeholder="Ej: erick@hotmail.com">
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;" for="Telefono"><u>Teléfono (cel/casa)*</u></label>
						<input class="form-control" type="text" id="Telefono" placeholder="Ej: (Lada) + número">
					</div>
				</article>
				<article class="col-xs-12 col-sm-12 col-md-1"></article>
			</section>
		</div>

		<div class="container">
			<section class="row">	
				<article class="col-xs-12 col-sm-12 col-md-1"></article>
				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;" for="Asunto"><u>Asunto*</u></label>
						<select class="form-control" id="Asunto">
						<option>¿Qué asunto nos quieres comentar?</option><option>Comentarios</option><option>Sugerencias</option><option>Quejas</option><option>Pedidos</option><option>Otro</option></select>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;" for="Asunto"><u>Mensaje</u></label>
						<input class="form-control" type="text" placeholder="Escribe tu mensaje">
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="Textos">
						<button class="btn btn-block btn-danger" style="margin-top: 10.5%;" type="reset">Borrar datos</button>
						<button class="btn btn-block btn-primary" style="margin-top: 3%;" type="submit" id="btn">Enviar</button>
					</div>
				</article>
				<article class="col-xs-12 col-sm-12 col-md-1"></article>
			</section>
		</div>		
	</form>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-12">
				<h1 class="Subtitulos" style="margin-top: 2%;">Contactanos</h1>
				<h1 class="Textos"><u>Si lo deseas puedes llamarnos al número (33)3487 7027 o envíanos un correo a: contacto@cakeshop.com.mx</h1></u>
			</article>
		</section>		
	</div>
	
	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Formulario.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>