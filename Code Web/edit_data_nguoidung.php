<?php
$title = "Sửa người dùng";
include("config.php");
include("check_session.php");
$MaND = addslashes($_POST['MaND']);
$hovaten = addslashes($_POST['edit_hovaten']);
$email = addslashes($_POST['edit_email']);
$sdt = addslashes($_POST['edit_sdt']);
$matkhau = addslashes($_POST['edit_matkhau']);
$MaPQ = addslashes($_POST['edit_MaPQ']);
$MaDV = addslashes($_POST['edit_MaDV']);
$DiaChi = addslashes($_POST['edit_DiaChi']);
if ($MaND == "" or $hovaten == "" or $email == "" or $sdt == "" or $matkhau == "" or $MaPQ == "" or $MaDV == "" or $DiaChi == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (strlen($matkhau) <= 4)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Mật khẩu phải trên 5 ký tự";
	echo json_encode($array_message);
	exit;
}


mysqli_query($conn,"UPDATE `nguoidung` SET `MaDV` = '".$MaDV."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `MaPQ` = '".$MaPQ."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `MatKhau` = '".$matkhau."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `HoVaTen` = '".$hovaten."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `Email` = '".$email."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `SoDienThoai` = '".$sdt."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `DiaChi` = '".$DiaChi."' where `MaND`='".$MaND."'");
mysqli_query($conn,"UPDATE `nguoidung` SET `NgayCapNhat` = '".$time."' where `MaND`='".$MaND."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa thông tin người dùng có Mã ND: ". $MaND,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa người dùng thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa người dùng: " . $conn->error;
}
echo json_encode($array_message);


?>