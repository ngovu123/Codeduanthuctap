<?php
$title = "Quản lý người dùng";
include("config.php");
include("check_session.php");

$tenphong = addslashes($_POST['themmoi_ql_phong_tenp']);
$makvp = addslashes($_POST['themmoi_ql_phong_makvp']);
$malp = addslashes($_POST['themmoi_ql_phong_malp']);
if ($tenphong == "" or $makvp == "" or $malp == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM phong WHERE TenP='$tenphong'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên phòng này đã tồn tại. Vui lòng chọn tên phòng khác!";
	echo json_encode($array_message);
	exit;
}
	$add_phong = mysqli_query($conn,"INSERT INTO phong	(
	MaP, MaLP, MaKVP, TenP, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$malp}', '{$makvp}', '{$tenphong}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới phòng có Tên: " . $tenphong,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới phòng thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới phòng: " . $conn->error;
}
echo json_encode($array_message);


?>