<?php
if(isset($_POST['FirstName']) && isset($_POST['LastName']) && isset($_POST['Email']) && isset($_POST['Username']) && isset($_POST['Password'])){
	require_once "connect.php";
	require_once "validate.php";
	$FirstName = validate($_POST['FirstName']);
	$LastName = validate($_POST['LastName']);
	$Email = validate($_POST['Email']);
	$Username = validate($_POST['Username']);
	$Password = validate($_POST['Password']);
	$sql = "insert into users values('', '$FirstName', '$LastName', '$Email', '$Username', '". md5($Password) ."')";
	if(!$connect->query($sql)){
		echo "failure";
	}else{
		echo "success";
	}
}
?>