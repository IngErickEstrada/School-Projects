<?php
	include "PHP/Usuario.php";
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
				<h1 class="Titulos">Cake Shop -> Registro de Usuario</h1>				
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

	<form name="formulario" id="form" action="" method="post" onsubmit="return Validar('Validar_Usuarios');">
		<div class="container">
			<section class="row">
				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Nombre Usuario</u></label>
						<input class="form-control" type="text" id="Usuario" name="Usuario" placeholder="Ej: IngErick">

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Nombre</u></label>
						<input class="form-control" type="text" id="Nombres" name="Nombres" placeholder="Ej: Erick Antonio Estrada Vargas">

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Sexo</u></label>
						<select class="form-control" id="Sexo" name="Sexo">
							<option>Hombre</option>
							<option>Mujer</option>
						</select>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Teléfono con lada</u></label>
						<input class="form-control" type="text" id="Telefono" name="Telefono" placeholder="Ej: 3334877027">

						<button class="btn btn-block btn-danger" type="reset" style="margin-top: 10%;">Borrar Datos</button>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<div class="form-group Textos">
						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Contraseña</u></label>
						<input class="form-control" type="password" id="Contraseña" name="Contraseña">

						<label <label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Perfil</u></label>
						<select class="form-control" id="Perfil" name="Perfil">
							<option>Cliente</option>
						</select>

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Edad</u></label>
						<input class="form-control" type="text" id="Edad" name="Edad" placeholder="Ej: 22">

						<label style="background: #FFFFFF; display: block; border-radius: 5px; margin-top: 10%;"><u>Correo electrónico</u></label>
						<input class="form-control" type="text" id="Correo" name="Correo" placeholder="Ej: algo@hotmail.com">

						<button class="btn btn-block btn-primary" type="submit" style="margin-top: 10%;" id="btn" name="btn">Registrar Usuario</button>
					</div>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-6">
					<div class="form-group">
						<img style="width: 500px; margin-top: 2%;" src="Imagenes/Registro.png">
					</div>
				</article>
			</section>
		</div>
	</form>

	<script src="JavaScript/Menu.js"></script>
	<script src="JavaScript/Validaciones.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var user = [<?php echo $checkuser; ?>];

		if(user == 1)
		{
			alert("El nombre de usuario ya esta registrado, utilice otro nombre de usuario");
		}
		else if(user == 2)
		{
			alert("Cliente registrado");
		}
		else if(user == 3)
		{
			alert("Error al intentar registrar el cliente");
		}
	</script>
</body>
</html>