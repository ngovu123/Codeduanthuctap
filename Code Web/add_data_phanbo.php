<?php
include("config.php");
include("check_session.php");

$MaTS = addslashes($_POST['MaTS']);
$MaND = addslashes($_POST['MaND']);
$MaP = addslashes($_POST['MaP']);
$SoLuong = addslashes($_POST['SoLuongCanThem']);
$GhiChu = addslashes($_POST['GhiChuThem']);

if ($MaTS == "" or $MaND == "" or $MaP == "" or $SoLuong == "" )
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if ($GhiChu == "")
{
	$GhiChu = "Không có";
}
$query_taisan = mysqli_query($conn,"SELECT * FROM taisan WHERE MaTS='$MaTS'");
if (mysqli_num_rows($query_taisan) == 0) 
{
	$array_message['code'] = 0;
	$array_message['message'] = "Không có tài sản này";
	echo json_encode($array_message);
	exit;
}else if (mysqli_fetch_array($query_taisan)['SLHienCon'] - $SoLuong < 0)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Số lượng cần thêm không hợp lệ!";
	echo json_encode($array_message);
	exit;
}



if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM phanbo WHERE MaTS='$MaTS' and MaP='$MaP'")) > 0){
	$query_phong = mysqli_fetch_array(mysqli_query($conn,"SELECT * FROM phong WHERE MaP='$MaP'"));
	$query_taisan = mysqli_fetch_array(mysqli_query($conn,"SELECT * FROM taisan WHERE MaTS='$MaTS'"));
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới - ".$query_taisan['TenTS']." - Số lượng: ".$SoLuong." - Vào phòng có Tên phòng là: " . $query_phong['TenP'],$time);
	mysqli_query($conn,"UPDATE `phanbo` SET `MaND` = '".$MaND."' where `MaTS`='".$MaTS."' and MaP='$MaP'");
	mysqli_query($conn,"UPDATE `phanbo` SET `SoLuong` =  `SoLuong` +  '".$SoLuong."' where `MaTS`='".$MaTS."' and MaP='$MaP'");
	mysqli_query($conn,"UPDATE `phanbo` SET `GhiChu` = '".$GhiChu."' where `MaTS`='".$MaTS."' and MaP='$MaP'");
	mysqli_query($conn,"UPDATE `phanbo` SET `NgayCapNhat` = '".$time."' where `MaTS`='".$MaTS."' and MaP='$MaP'");
	mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` -  '".$SoLuong."' where `MaTS`='".$MaTS."'");
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm dữ liệu TS-CCDC thành công!!!";
	echo json_encode($array_message);
	exit;
}
	$add_loaiphong = mysqli_query($conn,"INSERT INTO phanbo	(
	MaPB , MaTS	, MaND, MaP, SoLuong, GhiChu, NgayCapNhat, NgayTao) 
		VALUE 
	('','{$MaTS}', '{$MaND}', '{$MaP}', '{$SoLuong}', '{$GhiChu}', '{$time}', '{$time}')");
	mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` -  '".$SoLuong."' where `MaTS`='".$MaTS."'");
if ($conn->error == ""){
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm dữ liệu TS-CCDC thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới dữ liệu: " . $conn->error;
}
echo json_encode($array_message);


?>