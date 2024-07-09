<?php
$title = "Xóa phòng";
include("config.php");
include("check_session.php");

$MaP = addslashes($_GET['MaP']);
if ($MaP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_Phong = mysqli_fetch_array(mysqli_query($conn, "Select * FROM phong Where MaP = '$MaP'"))['TenP'];

$result = mysqli_query($conn, "	DELETE FROM phong Where phong.MaP = '$MaP'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa phòng có Tên Phòng là: ". $Ten_Phong,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa phòng: " . $conn->error;
}

echo json_encode($array_message);
?>
