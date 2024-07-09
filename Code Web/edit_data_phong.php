<?php
$title = "Sửa phòng";
include("config.php");
include("check_session.php");
$MaP = addslashes($_POST['MaP']);
$TenP = addslashes($_POST['edit_TenPhong']);
$MaKVP = addslashes($_POST['edit_MaKVP']);
$MaLP = addslashes($_POST['edit_MaLP']);
if ($MaP == "" or $TenP == "" or $MaKVP == "" or $MaLP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `phong` SET `MaKVP` = '".$MaKVP."' where `MaP`='".$MaP."'");
mysqli_query($conn,"UPDATE `phong` SET `MaLP` = '".$MaLP."' where `MaP`='".$MaP."'");
mysqli_query($conn,"UPDATE `phong` SET `TenP` = '".$TenP."' where `MaP`='".$MaP."'");
mysqli_query($conn,"UPDATE `phong` SET `NgayCapNhat` = '".$time."' where `MaP`='".$MaP."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa thông tin phòng có Mã Phòng: ".$MaP,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa phòng: " . $conn->error;
}
echo json_encode($array_message);


?>