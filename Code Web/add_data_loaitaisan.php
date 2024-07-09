<?php
$title = "Add data loại tài sản";
include("config.php");
include("check_session.php");

$tenLTS = addslashes($_POST['themmoi_ql_loaitaisan_tenlts']);
if ($tenLTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM loaitaisan WHERE TenLTS='$tenLTS'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Tên loại tài sản này đã tồn tại.";
	echo json_encode($array_message);
	exit;
}
	$add_loaiphong = mysqli_query($conn,"INSERT INTO loaitaisan	(
	MaLTS , TenLTS, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$tenLTS}', '{$time}', '{$time}')");
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới loại tài sản có Tên LTS là: " . $tenLTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới loại tài sản thành công !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới loại tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>