<?php
$title = "Load data người dùng";
include("config.php");
include("check_session.php");

$MaTS = addslashes($_GET['MaTS']);
if ($MaTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$query = mysqli_query($conn,"SELECT * FROM phanbo WHERE MaTS = '$MaTS'");
if (mysqli_num_rows($query) != 0) 
{
	$array_message['code'] = 0;
	$array_message['message'] = "Tài sản này hiện tại đang được phân bổ, bạn không thể xóa.";
	echo json_encode($array_message);
	exit;
}
$Ten_TS = mysqli_fetch_array(mysqli_query($conn, "Select * FROM taisan Where MaTS = '$MaTS'"))['TenTS'];




$result = mysqli_query($conn, "	DELETE  FROM taisan Where taisan.MaTS = '$MaTS'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa tài sản có Tên tài sản là: ". $MaTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa tài sản: " . $conn->error;
}

echo json_encode($array_message);
?>
