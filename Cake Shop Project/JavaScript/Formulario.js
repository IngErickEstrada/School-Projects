//Funciones para validar el formumlario de Contactos
(function()
{
	var formulario = document.getElementById('form'); //Creacion de variable formulario y tomamos los id del documento formulario que hicimos
	elementos = formulario.elements, //Creacion de varibale elementos para poder hacer a los elementos (id) del formulario
	boton = document.getElementById('btn'); //Creacion de variable boton, vinculada con el id del boton enviar para poder hacer las validaciones antes de enviar
 
	var validarNombres = function(e) //Funcion para validar el campo nombre dentro del formulario
	{
		if (formulario.Nombres.value == 0) //Condicional para saber si el campo nombres se encuentra lleno o no
		{
			alert("Complete el campo nombre para continuar"); //Mandamos una alerta si no se ha completado el campo nombre
			e.preventDefault(); //Funcion para no reiniciar la pagina a menos que se encuentren los campos llenos
		};
	};

	var validarCorreo = function(e) //Funcion para validar el campo correo dentro del formulario
	{
		if (formulario.Correo.value == 0) //Condicional para saber si el campo correo se encuentra lleno o no
		{
			alert("Complete el campo correo para continuar"); //Mandamos una alerta si no se ha completado el campo correo
			e.preventDefault(); //Funcion para no reiniciar la pagina a menos que se encuentren los campos llenos
		};
	};

	var validarTelefono = function(e) //Funcion para validar el campo telefono dentro del formulario
	{
		if (isNaN(formulario.Telefono.value) || formulario.Telefono.value == 0) //Condicional para saber si el campo telefono se encuentra lleno y para saber si se lleno con un dato numerico
		{
			alert("Complete el campo teléfono para continuar"); //Mandamos una alerta si no se ha completado el campo telefono
			e.preventDefault(); //Funcion para no reiniciar la pagina a menos que se encuentren los campos llenos
		};
	};

	var validarAsunto = function(e) //Funcion para validar el campo asunto dentro del formulario
	{
		if (formulario.Asunto.selectedIndex == null || formulario.Asunto.selectedIndex == 0) //Condicional para saber si el campo asunto se encuentra lleno
		{
			alert("Seleccione un asunto para continuar"); //Mandamos una alerta si no se ha completado el campo asunto
			e.preventDefault(); //Funcion para no reiniciar la pagina a menos que se encuentren los campos llenos
		};
	};

	var validar = function(e) //Creacion de funciones a validar
	{
		validarNombres(e); //Funcion para validar el campo nombres
		validarCorreo(e); //Funcion para valdidar el campo correo
		validarTelefono(e); //Funcion para validar el campo telefono
		validarAsunto(e); //Funcion para validar el campo asunto
	};

	formulario.addEventListener("submit",validar); //Funcion para abilitar las validaciones al momento de enviar los datos por medio del boton enviar
}())
