<?php
$title = "Sửa tài sản";
include("config.php");
include("check_session.php");



$MaTS = addslashes($_POST['MaTS']);
$TenTS = addslashes($_POST['edit_TenTS']);
$MaLTS = addslashes($_POST['edit_MaLTS']);
$MaNTS = addslashes($_POST['edit_MaNTS']);
$GiaTri = addslashes($_POST['edit_GiaTri']);
$HangSanXuat = addslashes($_POST['edit_HangSanXuat']);
$NuocSanXuat = addslashes($_POST['edit_NuocSanXuat']);
$NamSanXuat = addslashes($_POST['edit_NamSanXuat']);
$MoTa = addslashes($_POST['edit_MoTa']);


if ($MaTS == "" or $TenTS == "" or $MaLTS == "" or $MaNTS == "" or $GiaTri == "" or $HangSanXuat == "" or $NuocSanXuat == "" or $NamSanXuat == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

if ($MoTa == "")
{
	$MoTa = "Không có";
}

mysqli_query($conn,"UPDATE `taisan` SET `MaNTS` = '".$MaNTS."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `MaLTS` = '".$MaLTS."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `TenTS` = '".$TenTS."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `GiaTri` = '".$GiaTri."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `HangSanXuat` = '".$HangSanXuat."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `NuocSanXuat` = '".$NuocSanXuat."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `NamSanXuat` = '".$NamSanXuat."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `GhiChu` = '".$MoTa."' where `MaTS`='".$MaTS."'");
mysqli_query($conn,"UPDATE `taisan` SET `NgayCapNhat` = '".$time."' where `MaTS`='".$MaTS."'");




if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Sửa", "Sửa thông tin tài sản có Mã TS: ". $MaTS,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Sửa tài sản thành công!!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi sửa tài sản: " . $conn->error;
}
echo json_encode($array_message);


?>