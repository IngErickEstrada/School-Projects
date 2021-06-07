<?php 
	include "PHP/Login.php";
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
				<h1 class="Titulos">Cake Shop -> Ventas</h1>				
			</article>			
		</section>	
	</div>

	<header class="col-xs-12 col-sm-12 col-md-12">	
		<nav>
			<ul class="Menu">		
				<li><a href="Inicio.html">Inicio</a></li>
				<li><a href="Menu.html">Menu</a></li>
				<li><a href="Venta.php">Ventas</a></li>
				<li><a href="Promociones.html">Promociones</a></li>
				<li><a href="Contacto.html">Contacto</a></li>
			</ul>

			<div class="Barra">
				<input type="text" id="Barra" placeholder="Buscador">
			</div>
		</nav>
	</header>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-6">
				<h1 class="Subtitulos" style="margin-top: 30%;"><u>Ya tienes una cuenta de Cake Shop?</u></h1>
				<a href="Venta.php"><button class="btn btn-block btn-success Textos" style="margin-top: 5%;">Si, tengo una cuenta activa</button></a>
				<a href="Registro Usuario.php"><button class="btn btn-block btn-primary Textos">No, soy un cliente nuevo</button></a>
			</article>

			<article class="col-xs-12 col-sm-12 col-md-6">
				<center>
				<form name="formulario" id="form" action="" method="post" onclick="return Validar('Validar_Sesion');">
					<div class="form-group">
						<h1 class="Subtitulos" style="margin-top: 25%;">Inicio de Sesión</h1>	
	
						<label class="Textos" for="Usuario"><u>Ingresa tu usuario:</u></label>
						<input class="Textos" style="display: block; border-radius: 5px; margin: 0;" type="text" id="Usuario" name="Usuario">

						<label class="Textos" for="Password"><u>Ingresa tu contraseña:</u></label>
						<input class="Textos" style="display: block; border-radius: 5px; margin: 0;" type="password" id="Password" name="Password">

						<button class="btn btn-danger Textos" name="Login" style="display: block; margin-top: 3%;">Ingresar a mi cuenta</button>
					</div>
				</form>
				</center>
			</article>
		</section>
	</div>
	
	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Validaciones.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var sesion = [<?php echo $checksesion; ?>];

		if(sesion == 1)
		{
			alert("El id usuario o contraseña es incorrecto");
		}
	</script>
</body>
</html>