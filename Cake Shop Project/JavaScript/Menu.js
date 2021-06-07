/*Funciones para la barra de busqueda*/
var Menu = $('.Menu');
var Barra_Busqueda = $('.Barra');

Barra_Busqueda.focusin(function()
{
	Barra_Busqueda.css('width','20%');
	Menu.css('width','80%');
});

Barra_Busqueda.focusout(function()
{
	Barra_Busqueda.css('width','15%');
	Menu.css('width','85%');
});