<?php
include("../config.php");


$TongSo_TaiSan = 0;
$query_taisan = mysqli_query($conn, "select * from taisan");
while ($row = $query_taisan->fetch_assoc()) {
	$TongSo_TaiSan += $row['SLNhapVao'];
}
$TongSo_NguoiDung = mysqli_num_rows(mysqli_query($conn, "select * from nguoidung"));
$TongSo_PhongHoc = mysqli_num_rows(mysqli_query($conn, "select * from phong"));

$array_message['TongSo_TaiSan'] = $TongSo_TaiSan;
$array_message['TongSo_NguoiDung'] = $TongSo_NguoiDung;
$array_message['TongSo_PhongHoc'] = $TongSo_PhongHoc;

echo json_encode($array_message);

	
?>