<?php
$title = "Load data người dùng";
include("config.php");
include("check_session.php");

$MaND = addslashes($_GET['MaND']);
if ($MaND == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_ND = mysqli_fetch_array(mysqli_query($conn, "Select * FROM nguoidung Where MaND = '$MaND'"))['TenDangNhap'];
$result = mysqli_query($conn, "	DELETE  FROM nguoidung Where nguoidung.MaND = '$MaND'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa người dùng có Tên đăng nhập là: ". $Ten_ND,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa người dùng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa người dùng: " . $conn->error;
}

echo json_encode($array_message);
?>
