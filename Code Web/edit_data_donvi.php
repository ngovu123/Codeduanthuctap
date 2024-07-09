<?php
$title = "Sửa loại phòng";
include("config.php");
include("check_session.php");
$MaDV = addslashes($_POST['MaDV']);
$TenDV = addslashes($_POST['donvi_chinhsua']);
$MoTa = addslashes($_POST['mota_chinhsua']);
if ($MaDV == "" or $TenDV == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `donvi` SET `TenDV` = '".$TenDV."' where `MaDV`='".$MaDV."'");
mysqli_query($conn,"UPDATE `donvi` SET `MoTaDV` = '".$MoTa."' where `MaDV`='".$MaDV."'");
mysqli_query($conn,"UPDATE `donvi` SET `NgayCapNhat` = '".$time."' where `MaDV`='".$MaDV."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên đơn vị thành: ". $TenDV . " - Mã DV: ".$MaDV,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa đơn vị thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa đơn vị: " . $conn->error;
}
echo json_encode($array_message);


?>