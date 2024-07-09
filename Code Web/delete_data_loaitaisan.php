<?php
$title = "Xóa loại tài sản";
include("config.php");
include("check_session.php");

$MaLTS = addslashes($_GET['MaLTS']);
if ($MaLTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_LTS = mysqli_fetch_array(mysqli_query($conn, "Select * FROM loaitaisan Where MaLTS = '$MaLTS'"))['TenLTS'];
$result = mysqli_query($conn, "	DELETE FROM loaitaisan Where loaitaisan.MaLTS = '$MaLTS'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa loại tài sản có Tên LTS là: ". $Ten_LTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa loại tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa loại tài sản: " . $conn->error;
}

echo json_encode($array_message);
?>
