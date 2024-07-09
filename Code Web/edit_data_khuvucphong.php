<?php
$title = "Sửa khu vực phòng";
include("config.php");
include("check_session.php");
$MaKVP = addslashes($_POST['MaKVP']);
$TenKVP = addslashes($_POST['edit_TenKVP']);
if ($MaKVP == "" or $TenKVP == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
mysqli_query($conn,"UPDATE `khuvucphong` SET `TenKVP` = '".$TenKVP."' where `MaKVP`='".$MaKVP."'");
mysqli_query($conn,"UPDATE `khuvucphong` SET `NgayCapNhat` = '".$time."' where `MaKVP`='".$MaKVP."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên khu vực phòng thành: ". $TenKVP . " - Mã KVP: ".$MaKVP,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa khu vực phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa khu vực phòng: " . $conn->error;
}
echo json_encode($array_message);


?>