<?php
include("../config.php");
$CMD = addslashes($_GET['cmd']);
if ($CMD == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin.";
	echo json_encode($array_message);
	exit;
}

if ($CMD == "ADD_NEW_DATA_CHUCDANH"){
    $TenCD = addslashes($_GET['TenCD']);
    $MoTa = addslashes($_GET['MoTaCD']);
    if ($TenCD == "")
    {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Chưa nhập tên chức danh!!!";
    	echo json_encode($array_message);
    	exit;
    }
    if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM chucdanh WHERE TenCD='$TenCD'")) > 0){
    	$array_message['code'] = 0;
    	$array_message['message'] = "Tên chức danh này đã tồn tại.";
    	echo json_encode($array_message);
    	exit;
    }
    if ($MoTa == "")
    {
    	$MoTa = "Không có";
    }
	$add_loaiphong = mysqli_query($conn,"INSERT INTO chucdanh	(
    	MaCD , TenCD, MoTaCD, NgayTao, NgayCapNhat) 
    		VALUE 
    	('','{$TenCD}', '{$MoTa}', '{$time}', '{$time}')");
    if ($conn->error == ""){
    	$array_message['code'] = 1;
    	$array_message['message'] = "Thêm mới chức danh thành công !!!";
    
    }else {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Có lỗi khi thêm mới chức danh: " . $conn->error;
    }
    echo json_encode($array_message, JSON_NUMERIC_CHECK);
    
}else if ($cmd == "EDIT_DATA_CHUCDANH_BYMACD"){
    $MaCD = addslashes($_GET['MaCD']);
    $TenCD = addslashes($_GET['TenCD']);
    $MoTa = addslashes($_GET['MoTaCD']);
    if ($MaCD == "" or $TenCD == "" )
    {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
    	echo json_encode($array_message);
    	exit;
    }
    mysqli_query($conn,"UPDATE `chucdanh` SET `TenCD` = '".$TenCD."' where `MaCD`='".$MaCD."'");
    mysqli_query($conn,"UPDATE `chucdanh` SET `MoTaCD` = '".$MoTa."' where `MaCD`='".$MaCD."'");
    mysqli_query($conn,"UPDATE `chucdanh` SET `NgayCapNhat` = '".$time."' where `MaCD`='".$MaCD."'");
    if ($conn->error == ""){
    	$array_message['code'] = 1;
    	$array_message['message'] = "Sửa chức danh thành công!!!";
    
    }else {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Có lỗi khi sửa chức danh: " . $conn->error;
    }
    echo json_encode($array_message);
}else if ($cmd == "DELETE_DATA_CHUCDANH"){
    
}


else if ($CMD == "GET_LIST_DATA_CHUCDANH"){
    $myArray = array();
    $result = mysqli_query($conn, "	SELECT * FROM chucdanh ");
    if ($conn->error == ""){
    	while ($row = $result->fetch_assoc()) {
    		$myArray[] = $row;
    	}
    }
    
    echo json_encode($myArray, JSON_NUMERIC_CHECK);
} 



?>