function Validar (elementos)
{
	var texto = /(^[A-Za-z\s]+$)/;
	var texto_numeros = /(^[A-Za-z0-9\s]+$)/;
	var numeros = /(^[0-9]+$)/;

	if(elementos == "Validar_Sesion")
	{
		var Usuario = document.getElementById("Usuario").value;
		var Password = document.getElementById("Password").value;

		if(Usuario == 0)
		{
			alert("Ingrese su nombre de usuario");
			return false;
		}
		else if(Password == 0)
		{
			alert("Ingrese su contraseña");
			return false;
		}
	}

	else if(elementos == "Validar_Usuarios")
	{
		var Nombre_Usuario = document.getElementById("Usuario").value;
		var Nombres = document.getElementById("Nombres").value;
		var Telefono = document.getElementById("Telefono").value;
		var Contraseña = document.getElementById("Contraseña").value;
		var Edad = document.getElementById("Edad").value;
		var Correo = document.getElementById("Correo").value;

		if(Nombre_Usuario == 0) 
		{	
			alert("Completa el campo nombre de usuario para continuar");
			return false;
		}
		else if(!texto_numeros.test(Nombre_Usuario))
		{
			alert("El nombre de usuario solo puede contener letras y números, no se permiten caracteres especiales");
			return false;
		}
		else if(Nombre_Usuario.length > 8 || Nombre_Usuario.length < 8)
		{
			alert("El nombre de usuario debe ser de 8 caracteres");
			return false;
		}
		else if(Nombres == 0)
		{
			alert("Completa el campo nombre para continuar");
			return false;
		}
		else if(!texto.test(Nombres))
		{
			alert("El campo nombre solo puede contener letras, no se permiten números ni caracteres especiales");
			return false;
		}
		else if(Telefono == 0)
		{
			alert("Completa el campo teléfono para continuar");
			return false;
		}
		else if(!numeros.test(Telefono))
		{
			alert("El campo teléfono solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(Telefono.length > 10 || Telefono.length < 10)
		{
			alert("El campo teléfono debe ser de 10 caracteres");
			return false;
		}
		else if(Contraseña == 0)
		{
			alert("Completa el campo contraseña para continuar");
			return false;
		}
		else if(!texto_numeros.test(Contraseña))
		{
			alert("El campo contraseña solo puede contener letras, no se permiten números ni caracteres especiales");
			return false;
		}
		else if(Contraseña.length > 8 || Contraseña.length < 8)
		{
			alert("La contraseña debe ser de 8 caracteres");
			return false;
		}
		else if(Edad == 0)
		{
			alert("Completa el campo edad para continuar");
			return false;
		}
		else if(!numeros.test(Edad))
		{
			alert("El campo edad solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(Edad.length > 2 || Edad.length < 2)
		{
			alert("El campo edad debe ser de 2 caracteres");
			return false;
		}
		else if(Correo == 0)
		{
			alert("Completa el campo correo electrónico para continuar");
			return false;
		}
		else if(hotmail.test(Correo))
		{
			alert("Correo electrónico invalido");
			return false;
		}
		else { return true; }
	}

	else if(elementos == "Validar_Menu")
	{
		var idMenu = document.getElementById("idMenu").value;
		var Categoria = document.getElementById("Categoria").value;
		var Nombre_Pastel = document.getElementById("Pastel").value;
		var Precio = document.getElementById("Precio").value;

		if(idMenu == 0)
		{
			alert("Completa el campo id Menu para continuar");
			return false;
		}
		else if(!numeros.test(idMenu))
		{
			alert("El campo id Menu solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(idMenu.length > 3)
		{
			alert("El campo id menu no puede ser mayor a 3 caracteres");
			return false;
		}
		else if(Categoria == 0)
		{
			alert("Completa el campo categoría para continuar");
			return false;
		}
		else if(!texto.test(Categoria))
		{
			alert("El campo categoría solo puede contener letras, no se permiten números ni caracteres especiales");
			return false;
		}
		else if(Nombre_Pastel == 0)
		{
			alert("Completa el campo nombre pastel para continuar");
			return false;
		}
		else if(!texto_numeros.test(Nombre_Pastel))
		{
			alert("El campo nombre pastel solo puede contener letras y números, no se permiten caracteres especiales");
			return false;
		}
		else if(Precio == 0)
		{
			alert("Completa el campo precio para continuar");
			return false;
		}
		else if(!numeros.test(Precio))
		{
			alert("El campo precio solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else { return true; }
	}

	else if(elementos == "Validar_Almacen")
	{
		var idAlmacen = document.getElementById("idAlmacen").value;
		var Pastel = document.getElementById("Pastel").value;
		var Stock = document.getElementById("Stock").value;

		if(idAlmacen == 0)
		{
			alert("Completa el campo id almacén para continuar");
			return false;
		}
		else if(!numeros.test(idAlmacen))
		{
			alert("El campo id almacén solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(idAlmacen.length > 3)
		{
			alert("El campo id almacén no puede ser mayor a 3 caracteres");
			return false;
		}
		else if(Pastel == 0)
		{
			alert("Completa el campo nombre del pastel para continuar");
			return false;
		}
		else if(!texto_numeros.test(Pastel))
		{
			alert("El campo nombre del pastel solo puede contener letras y números, no se permiten caracteres especiales");
			return false;
		}
		else if(Stock == 0)
		{
			alert("Completa el campo existencia (stock) para continuar");
			return false;
		}
		else if(!numeros.test(Stock))
		{
			alert("El campo existencia (stock) solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(Stock.length > 3)
		{
			alert("El campo existencia (stock) no puede ser mayor a 3 caracteres");
			return false;
		}
		else{ return true; }
	}

	else if(elementos == "Validar_Pasteles")
	{
		var idPastel = document.getElementById("idPastel").value;
		var idMenu = document.getElementById("idMenu").value;
		var idAlmacen = document.getElementById("idAlmacen").value;

		if(idPastel == 0)
		{
			alert("Completa el campo id pastel para continuar");
			return false;
		}
		else if(!numeros.test(idPastel))
		{
			alert("El campo id pastel solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(idPastel.length > 3)
		{
			alert("El campo id pastel no puede ser mayor a 3 caracteres");
			return false;
		}
		else if(idMenu == 0)
		{
			alert("Completa el campo id menu para continuar");
			return false;
		}
		else if(!numeros.test(idMenu))
		{
			alert("El campo id menu solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(idMenu.length > 3)
		{
			alert("El campo id menu no puede ser mayor a 3 caracteres");
			return false;
		}
		else if(idAlmacen == 0)
		{
			alert("Completa el campo id almacén para continuar");
			return false;
		}
		else if(!numeros.test(idAlmacen))
		{
			alert("El campo id almacén solo puede contener números, no se permiten letras ni caracteres especiales");
			return false;
		}
		else if(idAlmacen.length > 3)
		{
			alert("El campo id almacén no puede ser mayor a 3 caracteres");
			return false;
		}
		else{ return true; }
	}
}