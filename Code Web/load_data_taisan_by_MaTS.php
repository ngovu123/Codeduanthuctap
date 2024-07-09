<?php
$title = "Load data tài sản";
include("config.php");
include("check_session.php");

$MaTS = addslashes($_GET['MaTS']);
if ($MaTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	SELECT taisan.*, nhomtaisan.TenNTS, nhomtaisan.MaNTS, loaitaisan.TenLTS, loaitaisan.MaLTS FROM taisan 
								INNER JOIN nhomtaisan ON taisan.MaNTS = nhomtaisan.MaNTS 
								INNER JOIN loaitaisan ON taisan.MaLTS = loaitaisan.MaLTS
								Where taisan.MaTS = '$MaTS'");

if (mysqli_num_rows($result) == 0) 
{
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}else {
	$array_message['code'] = 1;
	$array_message['data'] = mysqli_fetch_array($result);

}

echo json_encode($array_message);
?>
