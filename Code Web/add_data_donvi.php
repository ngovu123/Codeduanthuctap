<?php
$title = "Quản lý người dùng";
include("config.php");
include("check_session.php");

$TenDV = addslashes($_POST['donvi_themmoi']);
$MoTaDV = addslashes($_POST['mota_themmoi']);
if ($TenDV == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM donvi WHERE TenDV='$TenDV'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên đơn vị này đã tồn tại.";
	echo json_encode($array_message);
	exit;
}
if ($MoTaDV == "")
{
	$MoTaDV = "Không có";
}
	$add_loaiphong = mysqli_query($conn,"INSERT INTO donvi	(
	MaDV , TenDV, MoTaDV, NgayTao, NgayCapNhat) 
		VALUE 
	('','{$TenDV}', '{$MoTaDV}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới đơn vị có Tên ĐV là: " . $TenDV,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới đơn vị thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới đơn vị: " . $conn->error;
}
echo json_encode($array_message);


?>