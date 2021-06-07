<?php 
	class ConexionDB
	{
		private $Host = "localhost";
		private $User = "root";
		private $Pass = "";
		private $Name_DB = "cake_shop";

		public function Conexion_Database()
		{
			$Conect = new mysqli($this->Host,$this->User,$this->Pass,$this->Name_DB) or die ("Fallo la conexion con la base de datos");
			return $Conect;		
		}
	}	
?>