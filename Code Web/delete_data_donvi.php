<?php
$title = "Xóa đơn vị";
include("config.php");
include("check_session.php");

$MaDV = addslashes($_GET['MaDV']);
if ($MaDV == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_DV = mysqli_fetch_array(mysqli_query($conn, "Select * FROM donvi Where MaDV = '$MaDV'"))['TenDV'];
$result = mysqli_query($conn, "	DELETE FROM donvi Where donvi.MaDV = '$MaDV'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa đơn vị có Tên ĐV là: ". $Ten_DV,$time);

	$array_message['code'] = 1;
	$array_message['message'] = "Xóa đơn vị thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa đơn vị: " . $conn->error;
}

echo json_encode($array_message);
?>
