<?php
$title = "Chi tiết phiếu kiểm kê";
include("config.php");
include("check_session.php");
$myArray = array();
$array_message = array();
$MaPhieu = addslashes($_GET['MaPhieu']);
if ($MaPhieu == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

$result = mysqli_query($conn, "	SELECT chitietphieukiemke.MaCTPKK, taisan.TenTS, chitietphieukiemke.SoLuong, chitietphieukiemke.SoLuongKiemKe, chitietphieukiemke.ConTot, chitietphieukiemke.KemPC, chitietphieukiemke.MaPC,chitietphieukiemke.GhiChu  FROM chitietphieukiemke 
								INNER JOIN phanbo ON phanbo.MaPB = chitietphieukiemke.MaPB
								INNER JOIN phong ON phanbo.MaP = phong.MaP
								INNER JOIN taisan ON phanbo.MaTS = taisan.MaTS
								where MaPhieu = '".$MaPhieu."'");
if ($conn->error == ""){
	while ($row = $result->fetch_assoc()) {
		$myArray[] = $row;
	}
	$array_message['code'] = 1;
	$array_message['data'] = $myArray;
}else {
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}

echo json_encode($array_message);


?>