<?php
$title = "Load data phòng";
include("config.php");
if(!isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();     
}

$MaP = addslashes($_GET['MaP']);
if ($MaP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select phong.MaP, phong.MaLP, phong.MaKVP, phong.TenP, phong.NgayCapNhat, phong.NgayTao from phong
								INNER JOIN loaiphong ON loaiphong.MaLP = phong.MaLP 
								INNER JOIN khuvucphong ON khuvucphong.MaKVP = phong.MaKVP 
								Where phong.MaP = '$MaP'");

if (mysqli_num_rows($result) == 0) 
{
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}else {
	$array_message['code'] = 1;
	$array_message['data'] = mysqli_fetch_array($result);

}

echo json_encode($array_message);
?>
