<?php
$title = "Sửa người dùng";
include("config.php");
include("check_session.php");
$MaND = $data['MaND'];
$hovaten = addslashes($_POST['hovaten']);
$sdt = addslashes($_POST['sdt']);
$email = addslashes($_POST['email']);
$diachi = addslashes($_POST['diachi']);
if ($hovaten == "" or $email == "" or $sdt == "" or $diachi == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

mysqli_query($conn,"UPDATE `nguoidung` SET `HoVaTen` = '".$hovaten."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `Email` = '".$email."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `SoDienThoai` = '".$sdt."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `DiaChi` = '".$diachi."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `NgayCapNhat` = '".$time."' where `MaND`='".$MaND."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa thông tin người dùng có Mã ND: ". $MaND,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Cập nhật thông tin thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi cập nhật thông tin: " . $conn->error;
}
echo json_encode($array_message);


?>