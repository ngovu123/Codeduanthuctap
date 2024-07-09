<?php
$title = "Thêm mới nhóm tài sản";
include("config.php");
include("check_session.php");

$tenNTS = addslashes($_POST['themmoi_ql_nhomtaisan_tennts']);
if ($tenNTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM nhomtaisan WHERE TenNTS='$tenNTS'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên nhóm tài sản này đã tồn tại.";
	echo json_encode($array_message);
	exit;
}
	$add_loaiphong = mysqli_query($conn,"INSERT INTO nhomtaisan	(
	MaNTS , TenNTS, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$tenNTS}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới nhóm tài sản có Tên NTS là: " . $tenNTS, $time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới nhóm tài sản thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới nhóm tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>