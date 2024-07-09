<?php
$title = "Load data ban kiểm kê";
include("config.php");
include("check_session.php");

$MaPhieu = addslashes($_GET['MaPhieu']);
if ($MaPhieu == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select nguoidung.HoVaTen, donvi.TenDV, chucdanh.TenCD, bankiemke.Mabkk from bankiemke
								INNER JOIN nguoidung ON bankiemke.MaND = nguoidung.MaND 		
								INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
								INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
								Where bankiemke.MaPhieu = '$MaPhieu'");
$myArray = array();

if (mysqli_num_rows($result) == 0) 
{
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}else {
	while ($row = $result->fetch_assoc()) {
		$myArray[] = $row;
	}
	$array_message['code'] = 1;
	$array_message['data'] = $myArray;

}

echo json_encode($array_message);
?>
