<?php
$title = "Load data nhóm tài sản";
include("config.php");
include("check_session.php");

$MaNTS = addslashes($_GET['MaNTS']);
if ($MaNTS == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select * from nhomtaisan Where nhomtaisan.MaNTS = '$MaNTS'");

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
