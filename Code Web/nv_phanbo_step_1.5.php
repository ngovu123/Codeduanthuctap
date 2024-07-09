<?php 
include('config.php');
include("check_session.php");
if (!isset($_GET['IDP']))
{
	header("Location: nv_phanbo.php");
	exit();
}else if(isset($_SESSION['PhanBo_Setup2'])){
	header("Location: nv_phanbo_step2.php?IDP=".$_SESSION['PhanBo_Setup2']."");
	exit();     
}
$MaP = mysqli_real_escape_string($conn,$_GET['IDP']);
$query = mysqli_query($conn,"SELECT * FROM phong WHERE MaP = '$MaP'");
if (mysqli_num_rows($query) == 0) 
{
	header("Location: nv_phanbo.php");
	exit();
}
$TenP = mysqli_fetch_array($query)['TenP'];
$title = "Phân bố: " . $TenP;
$_SESSION['PhanBo_Setup2'] = $_GET['IDP'];
header("Location: nv_phanbo_step2.php?IDP=".$_SESSION['PhanBo_Setup2']."");
?>
