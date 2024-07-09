<?php
$title = "Quản lý người dùng";
include("config.php");
include("check_session.php");

$hovaten = addslashes($_POST['themmoi_ql_nguoidung_hovaten']);
$email = addslashes($_POST['themmoi_ql_nguoidung_email']);
$sdt = addslashes($_POST['themmoi_ql_nguoidung_sdt']);
$tendangnhap = addslashes($_POST['themmoi_ql_nguoidung_tendangnhap']);
$matkhau = addslashes($_POST['themmoi_ql_nguoidung_matkhau']);
$MaPQ = addslashes($_POST['themmoi_ql_nguoidung_maphanquyen']);
$MaDV = addslashes($_POST['themmoi_ql_nguoidung_madonvi']);
$MaCD = addslashes($_POST['themmoi_ql_nguoidung_machucdanh']);
$DiaChi = addslashes($_POST['themmoi_ql_nguoidung_diachi']);
if ($hovaten == "" or $email == "" or $sdt == "" or $tendangnhap == "" or $matkhau == "" or $MaPQ == "" or $MaDV == "" or $DiaChi == "" or $MaCD == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (strlen($tendangnhap) <= 4)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Tên đăng nhập phải trên 5 ký tự";
	echo json_encode($array_message);
	exit;	
}else if (strlen($matkhau) <= 4)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Mật khẩu phải trên 5 ký tự";
	echo json_encode($array_message);
	exit;
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM nguoidung WHERE TenDangNhap='$tendangnhap'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên đăng nhập này đã có người dùng. Vui lòng chọn tên đăng nhập khác.";
	echo json_encode($array_message);
	exit;
}
	$add_nguoidung = mysqli_query($conn,"INSERT INTO nguoidung	(
	MaND, MaDV, MaCD, MaPQ, TenDangNhap, MatKhau, HoVaTen, Email, SoDienThoai, DiaChi, NgayTao, NgayCapNhat) 
		VALUE 
	('','{$MaDV}', '{$MaCD}', '{$MaPQ}', '{$tendangnhap}', '{$matkhau}', '{$hovaten}', '{$email}', '{$sdt}', '{$DiaChi}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới người dùng có Tên đăng nhập là: " . $tendangnhap, $time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới người dùng thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới người dùng: " . $conn->error;
}
echo json_encode($array_message);


?>