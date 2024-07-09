<?php
$title = "Sửa loại phòng";
include("config.php");
include("check_session.php");
$MaLP = addslashes($_POST['MaLP']);
$TenLP = addslashes($_POST['edit_TenLP']);
if ($MaLP == "" or $TenLP == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `loaiphong` SET `TenLP` = '".$TenLP."' where `MaLP`='".$MaLP."'");
mysqli_query($conn,"UPDATE `loaiphong` SET `NgayCapNhat` = '".$time."' where `MaLP`='".$MaLP."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên loại phòng thành: ". $TenLP . " - Mã LP: ".$MaLP,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa khu vực phòng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa khu vực phòng: " . $conn->error;
}
echo json_encode($array_message);


?>