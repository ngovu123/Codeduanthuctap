<?php
$title = "Xóa chức danh";
include("config.php");
include("check_session.php");

$MaCD = addslashes($_GET['MaCD']);
if ($MaCD == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_CD = mysqli_fetch_array(mysqli_query($conn, "Select * FROM chucdanh Where MaCD = '$MaCD'"))['TenCD'];
$result = mysqli_query($conn, "	DELETE FROM chucdanh Where MaCD = '$MaCD'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa chức danh có Tên CD là: ". $Ten_CD,$time);

	$array_message['code'] = 1;
	$array_message['message'] = "Xóa chức danh thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa chức danh: " . $conn->error;
}

echo json_encode($array_message);
?>
