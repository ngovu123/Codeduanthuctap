<?php
$title = "Quản lý tài sản";
include("config.php");
include("check_session.php");

$TenTS = addslashes($_POST['themmoi_ql_taisan_tents']);
$MaNTS = addslashes($_POST['themmoi_ql_taisan_mants']);
$MaLTS = addslashes($_POST['themmoi_ql_taisan_malts']);
$GiaTri = addslashes($_POST['themmoi_ql_taisan_giatri']);
$SoLuong = addslashes($_POST['themmoi_ql_taisan_soluong']);
$HangSanXuat = addslashes($_POST['themmoi_ql_taisan_hangsanxuat']);
$NuocSanXuat = addslashes($_POST['themmoi_ql_taisan_nuocsanxuat']);
$NamSanXuat = addslashes($_POST['themmoi_ql_taisan_namsanxuat']);
$GhiChu = addslashes($_POST['themmoi_ql_taisan_ghichu']);
if ($TenTS == "" or $MaNTS == "" or $MaLTS == "" or $SoLuong == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Vui lòng nhập đầy đủ thông tin!!!";
	echo json_encode($array_message);
	exit;
}
if ($SoLuong <= 0)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Số lượng nhập vào phải lớn hơn 0";
	echo json_encode($array_message);
	exit;	
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM taisan WHERE TenTS='$TenTS'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên tài sản này đã được dùng. Vui lòng chọn tên tài sản khác.";
	echo json_encode($array_message);
	exit;
}
if ($GhiChu == ""){
	$GhiChu = "Không có";
}
if ($HangSanXuat == ""){
	$HangSanXuat = "Không có";
}
if ($NuocSanXuat == ""){
	$NuocSanXuat = "Không có";
}
	$add_nguoidung = mysqli_query($conn,"INSERT INTO taisan	(
	MaTS, MaNTS, MaLTS, TenTS, GiaTri, SLNhapVao, SLHienCon, HangSanXuat, NuocSanXuat, NamSanXuat, GhiChu, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$MaNTS}', '{$MaLTS}', '{$TenTS}', '{$GiaTri}', '{$SoLuong}', '{$SoLuong}', '{$HangSanXuat}', '{$NuocSanXuat}', '{$NamSanXuat}', '{$GhiChu}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới tài sản có Tên là: " . $TenTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới tài sản thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>