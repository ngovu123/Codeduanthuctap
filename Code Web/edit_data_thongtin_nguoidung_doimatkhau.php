<?php
$title = "Sửa người dùng";
include("config.php");
include("check_session.php");
$MaND = $data['MaND'];
$matkhau_hientai = addslashes($_POST['matkhau_hientai']);
$matkhau_moi = addslashes($_POST['matkhau_moi']);
$xacnhan_matkhau_moi = addslashes($_POST['xacnhan_matkhau_moi']);
if ($matkhau_hientai == "" or $matkhau_moi == "" or $xacnhan_matkhau_moi == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

if ($matkhau_hientai != $data['MatKhau'])
{
	$array_message['code'] = 0;
	$array_message['message'] = "Mật khẩu hiện tại không chính xác!!!";
	echo json_encode($array_message);
	exit;
}else if ($matkhau_moi != $xacnhan_matkhau_moi)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Mật khẩu mới và xác nhận mật khẩu mới phải giống nhau!";
	echo json_encode($array_message);
	exit;
}else if (strlen($matkhau) <= 4)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Mật khẩu phải trên 5 ký tự";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `nguoidung` SET `MatKhau` = '".$matkhau_moi."' where `MaND`='".$MaND."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Đổi mật khẩu có Mã ND: ". $MaND,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Đổi mật khẩu thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi đổi mật khẩu: " . $conn->error;
}
echo json_encode($array_message);


?>