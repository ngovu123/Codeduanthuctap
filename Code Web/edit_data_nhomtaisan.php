<?php
$title = "Sửa nhóm tài sản";
include("config.php");
include("check_session.php");
$MaNTS = addslashes($_POST['MaNTS']);
$TenNTS = addslashes($_POST['edit_TenNTS']);
if ($MaNTS == "" or $TenNTS == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `nhomtaisan` SET `TenNTS` = '".$TenNTS."' where `MaNTS`='".$MaNTS."'");
mysqli_query($conn,"UPDATE `nhomtaisan` SET `NgayCapNhat` = '".$time."' where `MaNTS`='".$MaNTS."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên nhóm tài sản thành: ". $TenNTS . " - Mã NTS: ".$MaNTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa nhóm tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa nhóm tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>