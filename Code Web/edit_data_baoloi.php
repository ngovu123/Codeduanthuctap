<?php
$title = "Load data phân bổ";
include("config.php");
include("check_session.php");

if (!isset($_POST))
{
	$array_message['code'] = 0;
	$array_message['message'] = "Lỗi không xác định!!!";
	echo json_encode($array_message);
	exit;
}
$MaBL = $_POST['MaBL'];
$TrangThai = $_POST['TrangThai'];
if ($MaBL == "" or $TrangThai == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa có đầy đủ thông tin để!!!";
	echo json_encode($array_message);
	exit;
}else if ($TrangThai != "0" and $TrangThai != "1")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Trạng thái không hợp lệ";
	echo json_encode($array_message);
	exit;
}




mysqli_query($conn,"UPDATE `baoloi` SET `TrangThai` = '".$TrangThai."' where `MaBL`='".$MaBL."'");
mysqli_query($conn,"UPDATE `baoloi` SET `NgayCapNhat` = '".$time."' where `MaBL`='".$MaBL."'");
if ($conn->error == ""){
if ($TrangThai == "0")
{
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Vừa chỉnh sửa trạng thái thành cần sửa chữa với Mã báo lỗi là: ".$MaBL,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Chỉnh sửa trạng thái báo lỗi thành công !" . $conn->error;
	echo json_encode($array_message);
	exit();
}else if ($TrangThai == "1")
{
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Vừa chỉnh sửa trạng thái thành đã sửa chữa xong với Mã báo lỗi là: ".$MaBL,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Chỉnh sửa trạng thái báo lỗi thành công !" . $conn->error;
	echo json_encode($array_message);
	exit();
}	
}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi chỉnh sửa trạng thái báo lỗi: " . $conn->error;
	echo json_encode($array_message);
	exit();
}