<?php
include("config.php");
include("check_session.php");

$MaP = addslashes($_POST['MaP']);
$MaTS = addslashes($_POST['MaTS']);
$MoTa = addslashes($_POST['MoTa']);
if ($MaP == "" or $MaTS == "" or $MoTa == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

$query_phanbo = mysqli_query($conn,"SELECT * FROM phanbo WHERE MaP = '$MaP' and MaTS = '$MaTS'");
if (mysqli_num_rows($query_phanbo) == 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Không có tài sản này!!!";
	echo json_encode($array_message);
	exit;
}
$row_phanbo = mysqli_fetch_array(mysqli_query($conn,"SELECT * FROM phanbo WHERE MaP = '$MaP' and MaTS = '$MaTS'"));
$query_baoloi = mysqli_query($conn,"SELECT * FROM baoloi WHERE MaPB = '".$row_phanbo['MaPB']."'");

if (mysqli_num_rows($query_baoloi) == 1){
	$row_baoloi = mysqli_fetch_array(mysqli_query($conn,"SELECT * FROM baoloi WHERE MaPB = '".$row_phanbo['MaPB']."'"));
	if ($row_baoloi['TrangThai'] == 0)
	{
		$array_message['code'] = 0;
		$array_message['message'] = "Tài sản ở phòng này đã được ghi nhận báo lỗi. Nhà trường đang tiến hành khắc phục và sửa chữa!!!";
		echo json_encode($array_message);
		exit;
	}
}


$MaND = $data["MaND"];
$add_baoloi = mysqli_query($conn,"INSERT INTO baoloi	(
MaBL, MaPB, MaND, Mota, TrangThai, NgayCapNhat, NgayTao) 
	VALUE 
('','".$row_phanbo['MaPB']."', '{$MaND}','{$MoTa}', 0,'{$time}', '{$time}')");

if ($conn->error == ""){
	$array_message['code'] = 1;
$array_message['message'] = "Thông tin báo lỗi của bạn đã được nhà trường ghi nhận. Vui lòng đợi chúng tôi ghi nhận và khắc phục!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi báo lỗi: " . $conn->error;
}


echo json_encode($array_message);
