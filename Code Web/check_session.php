<?php
if(!isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();     
}else if ($data['MaPQ'] == 2)
{
	header("Location: index.php");
	exit();
}
?>