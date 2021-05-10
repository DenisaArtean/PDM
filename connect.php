<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "proiect_pdm";
$connect = new mysqli($server, $username, $password, $database);
if($connect->connect_error){
	die("Connection failed: ". $connect->connect_error);
}
?>