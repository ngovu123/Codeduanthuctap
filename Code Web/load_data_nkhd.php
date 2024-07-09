<?php
$title = "load data nkhd";
include("config.php");
include("check_session.php");
$txtSearch = "";
$myArray = array();

if (isset($_GET['txtSearch']))
{
	$txtSearch = $_GET['txtSearch'];
	$result = mysqli_query($conn, "select nguoidung.HoVaTen, nguoidung.TenDangNhap, nhatkyhoatdong.* from nhatkyhoatdong
								INNER JOIN nguoidung ON nguoidung.MaND = nhatkyhoatdong.MaND 
								Where nguoidung.HoVaTen like '%$txtSearch%' or nguoidung.TenDangNhap like '%$txtSearch%' or nhatkyhoatdong.ChiTietHanhDong like '%$txtSearch%' or nhatkyhoatdong.HanhDong like '%$txtSearch%' or nhatkyhoatdong.Time like '%$txtSearch%'");

}else {
	$result = mysqli_query($conn, 'select * as total from nhatkyhoatdong');
}
$total_records = mysqli_num_rows($result);

$current_page = isset($_GET['page']) ? $_GET['page'] : 1;
$limit = 5;

$total_page = ceil($total_records / $limit);

if ($current_page > $total_page){
    $current_page = $total_page;
}
else if ($current_page < 1){
    $current_page = 1;
}

$start = ($current_page - 1) * $limit;

$result = mysqli_query($conn, "	select nguoidung.HoVaTen, nguoidung.TenDangNhap, nhatkyhoatdong.* from nhatkyhoatdong
								INNER JOIN nguoidung ON nguoidung.MaND = nhatkyhoatdong.MaND 
								Where nguoidung.HoVaTen like '%$txtSearch%' or nguoidung.TenDangNhap like '%$txtSearch%' or nhatkyhoatdong.ChiTietHanhDong like '%$txtSearch%' or nhatkyhoatdong.HanhDong like '%$txtSearch%' or nhatkyhoatdong.Time like '%$txtSearch%'
								Order by nhatkyhoatdong.Time DESC
								LIMIT $start, $limit");
if ($conn->error == ""){
	while ($row = $result->fetch_assoc()) {
		$myArray[] = $row;
	}
	$array_message['code'] = 1;
	$array_message['numSize'] = $total_page;
	$array_message['pageCurrent'] = intval($current_page);
	$array_message['data'] = $myArray;
}else {
	$array_message['code'] = 0;
	$array_message['numSize'] = 1;
	$array_message['pageCurrent'] = 1;
	$array_message['data'] = NULL;
}

echo json_encode($array_message);
?>