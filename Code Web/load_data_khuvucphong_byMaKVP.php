<?php
$title = "Load data khu vực phòng";
include("config.php");
include("check_session.php");

$MaKVP = addslashes($_GET['MaKVP']);
if ($MaKVP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select * from khuvucphong Where khuvucphong.MaKVP = '$MaKVP'");

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
