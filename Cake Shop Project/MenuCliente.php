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
				<h1 class="Titulos">Cake Shop -> Menu -> <?php echo $User;?></h1>				
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
				<article class="col-xs-12 col-sm-12 col-md-3">
					<center><img class="Pasteles" src="Imagenes/Menu/3 Chocolates.png"></center><p><u>Pastel de 3 Chocolates</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$380.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn1">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 8%;" src="Imagenes/Menu/Carlos V.png"></center><p><u>Pastel Carlos V</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$368.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn5">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 10%;" src="Imagenes/Menu/Red Velvet.png"></center><p><u>Pastel Red Velvet</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$348.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn9">Comprar</button>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<center><img class="Pasteles" src="Imagenes/Menu/3 Leches.png"></center><p><u>Pastel de 3 Leches</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$330.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn2">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 8%;" src="Imagenes/Menu/Chocoflan.png"></center><p><u>Pastel Chocoflán</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$330.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn6">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 10.4%;" src="Imagenes/Menu/Rompope con Philadelphia.png"></center><p><u>Pastel de Philadelphia</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$315.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn10">Comprar</button>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<center><img class="Pasteles" src="Imagenes/Menu/Besos de fresa.png"></center><p><u>Pastel Besos de Fresa</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$345.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn3">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 8%;" src="Imagenes/Menu/Frutas del bosque.png"></center><p><u>Pastel Frutas Bosque</u></p>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$365.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn7">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 10%;" src="Imagenes/Menu/Yoghurt con fresas.png"></center><p><u>Pastel de Yoghurt</u></p>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$355.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn11">Comprar</button>
				</article>

				<article class="col-xs-12 col-sm-12 col-md-3">
					<center><img class="Pasteles" src="Imagenes/Menu/Capuchino.png"></center><p><u>Pastel de Capuchino</u></p>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$365.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn4">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 8%;" src="Imagenes/Menu/Moka.png"></center><p><u>Pastel de Moka</p></u>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$335.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn8">Comprar</button>

					<center><img class="Pasteles" style="margin-top: 10%;" src="Imagenes/Menu/Zanahoria.png"></center><p><u>Pastel de Zanahoria</u></p>
					<button class="btn btn-block disabled" style="font-size: 0.8em;">$345.00</button>
					<button class="btn btn-block btn-success" style="font-size: 0.8em; margin: 0;" name="btn12">Comprar</button>
				</article>
			</section>		
		</div>
	</form>

	<div class="container">
		<section class="row">
			<article class="col-xs-12 col-sm-12 col-md-12">
				<p class="Textos">En Cake Shop tenemos una gran variedad de pasteles, elaborados bajo los más altos estándares de calidad. Constantemente renovamos nuestra oferta repostera que propone una cocina innovadora para disfrutar los clásicos como el "Pastel de Zanahoria", el pastel de "Moka" o el pastel "Carlos V" que por mucho tiempo han dado distinción y prestigio a la marca.</p>
			</article>
		</section>
	</div>
	
	<script src="JavaScript/Menu.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		var factura = [<?php echo $checkfactura; ?>];

		if(factura == 1)
		{
			alert("Factura registrada");
		}
		else if(factura == 2)
		{
			alert("Error al intentar registrar la factura");
		}
	</script>
</body>
</html>