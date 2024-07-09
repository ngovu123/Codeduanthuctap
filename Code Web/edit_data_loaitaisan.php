<?php
$title = "Sửa loại tài sản";
include("config.php");
include("check_session.php");
$MaLTS = addslashes($_POST['MaLTS']);
$TenLTS = addslashes($_POST['edit_tenlts']);
if ($MaLTS == "" or $TenLTS == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}



mysqli_query($conn,"UPDATE `loaitaisan` SET `TenLTS` = '".$TenLTS."' where `MaLTS`='".$MaLTS."'");
mysqli_query($conn,"UPDATE `loaitaisan` SET `NgayCapNhat` = '".$time."' where `MaLTS`='".$MaLTS."'");

if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa tên loại tài sản thành: ". $TenLTS . " - Mã LTS: ".$MaLTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa loại tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa loại tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>