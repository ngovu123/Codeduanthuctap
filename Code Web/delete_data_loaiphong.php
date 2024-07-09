<?php
$title = "Xóa loại phòng";
include("config.php");
include("check_session.php");

$MaLP = addslashes($_GET['MaLP']);
if ($MaLP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$Ten_LP = mysqli_fetch_array(mysqli_query($conn, "Select * FROM loaiphong Where MaLP = '$MaLP'"))['TenLP'];
$result = mysqli_query($conn, "	DELETE FROM loaiphong Where loaiphong.MaLP = '$MaLP'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa loại phòng có Tên LP là: ". $Ten_LP,$time);

	$array_message['code'] = 1;
	$array_message['message'] = "Xóa loại phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa loại phòng: " . $conn->error;
}

echo json_encode($array_message);
?>
