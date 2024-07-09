<?php
$title = "Xóa khu vực phòng";
include("config.php");
include("check_session.php");

$MaKVP = addslashes($_GET['MaKVP']);
if ($MaKVP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_KVP = mysqli_fetch_array(mysqli_query($conn, "Select * FROM khuvucphong Where MaKVP = '$MaKVP'"))['TenKVP'];
$result = mysqli_query($conn, "	DELETE FROM khuvucphong Where khuvucphong.MaKVP = '$MaKVP'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa khu vực phòng có Tên KVP là: ". $Ten_KVP,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa khu vực phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa khu vực phòng: " . $conn->error;
}

echo json_encode($array_message);
?>
