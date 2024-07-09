<?php
$title = "Quản lý phân bố";
include("config.php");
include("check_session.php");
$txtSearch = "";
$myArray = array();

if (isset($_GET['txtSearch']))
{
	$txtSearch = $_GET['txtSearch'];
	$result = mysqli_query($conn, "select phanbo.MaPB, phanbo.MaP, count(phanbo.SoLuong) as total from phanbo
								INNER JOIN phong ON phong.MaP = phanbo.MaP 
								Where phong.TenP like '%$txtSearch%'
								GROUP BY phanbo.MaP");

}else {
	$result = mysqli_query($conn, 'select count(MaP) as total from phanbo GROUP BY phanbo.MaP');
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

$result = mysqli_query($conn, "	SELECT phanbo.MaPB, phanbo.MaP, phong.TenP, phanbo.GhiChu, phanbo.NgayCapNhat, SUM(phanbo.SoLuong) as 'SoLuongTong' FROM phanbo 
								INNER JOIN phong ON phong.MaP = phanbo.MaP 
								Where phong.TenP like '%$txtSearch%'
								GROUP BY phanbo.MaP
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
