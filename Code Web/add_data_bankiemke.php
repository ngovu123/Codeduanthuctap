<?php
include("config.php");
include("check_session.php");


$MaND = addslashes($_POST['MaND']);
$MaPhieu = addslashes($_POST['MaPhieu']);
if ($MaND == "" or $MaPhieu == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
mysqli_query($conn,"INSERT INTO bankiemke	(
	Mabkk , MaND, MaPhieu, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$MaND}', '{$MaPhieu}', '{$time}', '{$time}')");
if ($conn->error == ""){
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới ban kiểm kê thành công!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới ban kiểm kê: " . $conn->error;
}
echo json_encode($array_message);


?>