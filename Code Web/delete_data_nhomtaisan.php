<?php
$title = "Xóa nhóm tài sản";
include("config.php");
include("check_session.php");

$MaNTS = addslashes($_GET['MaNTS']);
if ($MaNTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_NTS = mysqli_fetch_array(mysqli_query($conn, "Select * FROM nhomtaisan Where MaNTS = '$MaNTS'"))['TenNTS'];
$result = mysqli_query($conn, "	DELETE FROM nhomtaisan Where nhomtaisan.MaNTS = '$MaNTS'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa nhóm tài sản có Tên NTS là: ". $Ten_NTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa nhóm tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa nhóm tài sản: " . $conn->error;
}

echo json_encode($array_message);
?>
