<?php
$title = "Load data loại phòng";
include("config.php");
include("check_session.php");

$MaLP = addslashes($_GET['MaLP']);
if ($MaLP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select * from loaiphong Where loaiphong.MaLP = '$MaLP'");

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
