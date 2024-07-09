<?php
$title = "Xem lại phiếu kiểm kê";
include("config.php");
include("check_session.php");
$txtSearch = "";
$myArray = array();
$myArray_bkk = array();
$MaPhieu = addslashes($_GET['MaPhieu']);
if ($MaPhieu == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}

$result = mysqli_query($conn, "	SELECT taisan.MaTS, taisan.TenTS, chitietphieukiemke.SoLuong, chitietphieukiemke.SoLuongKiemKe, chitietphieukiemke.ConTot, chitietphieukiemke.KemPC, chitietphieukiemke.MaPC, chitietphieukiemke.GhiChu FROM chitietphieukiemke 
								INNER JOIN phieukiemke ON phieukiemke.MaPhieu = chitietphieukiemke.MaPhieu 
								INNER JOIN phong ON phieukiemke.MaP = phong.MaP 
								INNER JOIN phanbo ON chitietphieukiemke.MaPB = phanbo.MaPB 
								INNER JOIN taisan ON taisan.MaTS = phanbo.MaTS 
								INNER JOIN loaitaisan ON taisan.MaLTS = loaitaisan.MaLTS
								INNER JOIN nhomtaisan ON nhomtaisan.MaNTS = taisan.MaNTS
								Where chitietphieukiemke.MaPhieu = '$MaPhieu'
								GROUP BY taisan.TenTS
								");
$result_bankiemke = mysqli_query($conn, "SELECT nguoidung.HoVaTen, donvi.TenDV, chucdanh.TenCD FROM bankiemke 
								INNER JOIN nguoidung ON bankiemke.MaND = nguoidung.MaND
								INNER JOIN donvi ON donvi.MaDV = nguoidung.MaDV
								INNER JOIN chucdanh ON chucdanh.MaCD = nguoidung.MaCD
								Where bankiemke.MaPhieu = '$MaPhieu'
								");						
if (mysqli_num_rows($result) == 0) 
{
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}else {
	if ($conn->error == ""){
		while ($row = $result->fetch_assoc()) {
			$myArray[] = $row;
		}
		while ($row = $result_bankiemke->fetch_assoc()) {
			$myArray_bkk[] = $row;
		}
	$array_message['code'] = 1;
	$array_message['data'] = $myArray;
	$array_message['bkk'] = $myArray_bkk;
}else {
	echo $conn->error;
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}
}


echo json_encode($array_message);
?>
