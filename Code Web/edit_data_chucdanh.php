<?php
$title = "Sửa loại phòng";
include("config.php");
include("check_session.php");
$MaCD = addslashes($_POST['MaCD']);
$TenCD = addslashes($_POST['chucdanh_chinhsua']);
$MoTa = addslashes($_POST['mota_chinhsua']);
if ($MaCD == "" or $TenCD == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `chucdanh` SET `TenCD` = '".$TenCD."' where `MaCD`='".$MaCD."'");
mysqli_query($conn,"UPDATE `chucdanh` SET `MoTaCD` = '".$MoTa."' where `MaCD`='".$MaCD."'");
mysqli_query($conn,"UPDATE `chucdanh` SET `NgayCapNhat` = '".$time."' where `MaCD`='".$MaCD."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên chức danh thành: ". $TenCD . " - Mã CD: ".$MaCD,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa chức danh thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa chức danh: " . $conn->error;
}
echo json_encode($array_message);


?>