<?php
if(isset($_POST['Username']) && isset($_POST['Address']) && isset($_POST['City']) && isset($_POST['ZipCode']) && isset($_POST['County']) && isset($_POST['Date']) && isset($_POST['PhoneNumber']) && isset($_POST['FoodLeft'])){
	require_once "connect.php";
	require_once "validate.php";
	$Username = validate($_POST['Username']);
	$Address = validate($_POST['Address']);
	$City = validate($_POST['City']);
	$ZipCode = validate($_POST['ZipCode']);
	$County = validate($_POST['County']);
	$Date = validate($_POST['Date']);
	$PhoneNumber = validate($_POST['PhoneNumber']);
	$FoodLeft = validate($_POST['FoodLeft']);
	$sql = "insert into info values('', '$Username', '$Address', '$City', '$ZipCode', '$County', '$Date', '$PhoneNumber', '$FoodLeft')";
	 if(!$connect->query($sql)){
		echo "failure";
	}else{
		echo "success";
	}
}
?>