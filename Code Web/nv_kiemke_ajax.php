<?php
$title = "Kiểm kê";
include("config.php");
include("check_session.php");
$txtSearch = "";
$myArray = array();
$database = "";
if (isset($_GET['phongSearch']) And isset($_GET['timeTu']) And isset($_GET['timeDen']) And isset($_GET['TrangThai']))
{
	$phongSearch = $_GET['phongSearch'];
	$timeTu = $_GET['timeTu'];
	$timeDen = $_GET['timeDen'];
	$TrangThai = $_GET['TrangThai'];
	if ($phongSearch == "" and $timeTu == "" and $timeDen == "" and $TrangThai == "" )
	{
		$result = mysqli_query($conn, 'select count(MaPhieu) as total from phieukiemke INNER JOIN phong ON phong.MaP = phieukiemke.MaP');
	}else if ($phongSearch != "" and $timeTu == "" and $timeDen == "" and $TrangThai == "")
	{
		$database = "Where phong.MaP = '$phongSearch'";
	}else if ($phongSearch == "" and $timeTu != "" and $timeDen != "" and $TrangThai == "")
	{
		$database = "Where (DATE(phieukiemke.NgayCapNhat) >= '$timeTu' and DATE(phieukiemke.NgayCapNhat) <= '$timeDen')";
	}else if ($phongSearch == "" and $timeTu == "" and $timeDen == "" and $TrangThai != "")
	{
		$database = "Where phieukiemke.TrangThai = '$TrangThai'";
	}else if ($phongSearch != "" and $timeTu != "" and $timeDen != "" and $TrangThai == "")
	{
		$database = "Where phong.MaP = '$phongSearch' and (DATE(phieukiemke.NgayCapNhat) >= '$timeTu' and DATE(phieukiemke.NgayCapNhat) <= '$timeDen')";
	}else if ($phongSearch != "" and $timeTu == "" and $timeDen == "" and $TrangThai != "")
	{
		$database = "Where phong.MaP = '$phongSearch' and phieukiemke.TrangThai = '$TrangThai'";
	}else if ($phongSearch == "" and $timeTu != "" and $timeDen != "" and $TrangThai != "")
	{
		$database = "Where (DATE(phieukiemke.NgayCapNhat) >= '$timeTu' and DATE(phieukiemke.NgayCapNhat) <= '$timeDen') and phieukiemke.TrangThai = '$TrangThai'";
	}else if ($phongSearch != "" and $timeTu != "" and $timeDen != "" and $TrangThai != "")
	{
		$database = "Where phong.MaP = '$phongSearch' and (DATE(phieukiemke.NgayCapNhat) >= '$timeTu' and DATE(phieukiemke.NgayCapNhat) <= '$timeDen') and phieukiemke.TrangThai = '$TrangThai'";
	}
	$result = mysqli_query($conn, "select count(MaPhieu) as total from phieukiemke 
									INNER JOIN phong ON phong.MaP = phieukiemke.MaP 
									".$database."");
}

$row = mysqli_fetch_assoc($result);
$total_records = $row['total'];

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
$result = mysqli_query($conn, "	SELECT phieukiemke.MaPhieu, phieukiemke.TrangThai, phieukiemke.NgayCapNhat, phieukiemke.GhiChu , phong.TenP FROM phieukiemke 
						INNER JOIN phong ON phong.MaP = phieukiemke.MaP 
						".$database."
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
