<?php
$title = "Xóa ban kiểm kê";
include("config.php");
include("check_session.php");

$Mabkk = addslashes($_GET['Mabkk']);
if ($Mabkk == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	DELETE  FROM bankiemke Where bankiemke.Mabkk = '$Mabkk'");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Xóa", "Xóa ban kiểm kê có Mã BKK là: ". $Mabkk." khỏi Mã Phiếu là: ". $result['MaPhieu'],$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Xóa ban kiểm kê thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi xóa ban kiểm kê: " . $conn->error;
}

echo json_encode($array_message);
?>
