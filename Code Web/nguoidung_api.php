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

if ($CMD == "ADD_NEW_NGUOIDUNG"){
    $hovaten = addslashes($_GET['HoVaTen']);
    $email = addslashes($_GET['Email']);
    $MaPQ = addslashes($_GET['MaPQ']);
    $MaDV = addslashes($_GET['MaDV']);
    $MaCD = addslashes($_GET['MaCD']);
    $Uid = addslashes($_GET['Uid']);
    $Token = addslashes($_GET['Token']);
    if ($hovaten == "" or $email == "" or $MaPQ == "" or $MaDV == "" or $MaCD == "" or $Uid == ""or $Token == "")
    {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
    	echo json_encode($array_message);
    	exit;
    }
    
    
    if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM nguoidung WHERE Email='$email'")) > 0){ // email này đã tồn tại
    
        mysqli_query($conn,"UPDATE `nguoidung` SET `token` = '".$Token."' where `Email`='".$email."'");
        mysqli_query($conn,"UPDATE `nguoidung` SET `uid` = '".$Uid."' where `Email`='".$email."'");
        mysqli_query($conn,"UPDATE `nguoidung` SET `NgayCapNhat` = '".$time."' where `Email`='".$email."'");
        
    	$array_message['code'] = 1;
    	$array_message['message'] = "Update token - uid người dùng - Đăng nhập thành công !!!";
    	echo json_encode($array_message);
    	exit;
    }
    
    $add_nguoidung = mysqli_query($conn,"INSERT INTO nguoidung	(
	MaND, MaDV, MaCD, MaPQ,  HoVaTen, Email, uid, token, NgayTao, NgayCapNhat) 
		VALUE 
	('','{$MaDV}', '{$MaCD}', '{$MaPQ}', '{$hovaten}', '{$email}', '{$Uid}', '{$Token}','{$time}', '{$time}')");
    if ($conn->error == ""){
    	$array_message['code'] = 1;
    	$array_message['message'] = "Thêm mới người dùng thành công !!!";
    
    }else {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Có lỗi khi thêm mới người dùng: " . $conn->error;
    }
    echo json_encode($array_message);
	exit;
    
}else if ($CMD == "UPDATE_UID_TOKEN_NGUOIDUNG"){
    $email = addslashes($_GET['Email']);
    $Uid = addslashes($_GET['Uid']);
    $Token = addslashes($_GET['Token']);
    if ($email == "" or $Uid == "" or $Token == "")
    {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
    	echo json_encode($array_message);
    	exit;
    }
    if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM nguoidung WHERE Email='$email'")) > 0){
    
        mysqli_query($conn,"UPDATE `nguoidung` SET `token` = '".$Token."' where `Email`='".$email."'");
        mysqli_query($conn,"UPDATE `nguoidung` SET `uid` = '".$Uid."' where `Email`='".$email."'");
        mysqli_query($conn,"UPDATE `nguoidung` SET `NgayCapNhat` = '".$time."' where `Email`='".$email."'");
        
    	$array_message['code'] = 1;
    	$array_message['message'] = "Update token - uid người dùng - Đăng nhập thành công !!!";
    	echo json_encode($array_message);
    	exit;
    }else {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Có lỗi khi update lại người dùng: " . $conn->error;
    	echo json_encode($array_message);
    	exit;
    }
}else if ($CMD == "GET_LIST_DATA_NGUOIDUNG_ADMIN"){
    $myArray = array();
    $MaND = addslashes($_GET['MaND']);
    if ($MaND == "")
    {
    	echo "[{}]";
    	exit;
    }
    $result = mysqli_query($conn, "Select nguoidung.*, chucdanh.TenCD, donvi.TenDV, phanquyen.TenPQ from nguoidung
								INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
								INNER JOIN phanquyen ON nguoidung.MaPQ = phanquyen.MaPQ 
								INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
								Where nguoidung.MaPQ = 1 and nguoidung.MaND != ".$MaND."");
    if ($conn->error == ""){
    	while ($row = $result->fetch_assoc()) {
    		$myArray[] = $row;	
    	}
    }
    echo json_encode($myArray, JSON_NUMERIC_CHECK);
}else if ($CMD == "GET_LIST_DATA_NGUOIDUNG_USER"){
    $myArray = array();
    $MaND = addslashes($_GET['MaND']);
    if ($MaND == "")
    {
    	echo "[{}]";
    	exit;
    }
    $result = mysqli_query($conn, "Select nguoidung.*, chucdanh.TenCD, donvi.TenDV, phanquyen.TenPQ from nguoidung
								INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
								INNER JOIN phanquyen ON nguoidung.MaPQ = phanquyen.MaPQ 
								INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
								Where nguoidung.MaPQ = 2 and nguoidung.MaND != ".$MaND."");
    if ($conn->error == ""){
    	while ($row = $result->fetch_assoc()) {
    		$myArray[] = $row;	
    	}
    }
    echo json_encode($myArray, JSON_NUMERIC_CHECK);
}else if ($CMD == "GET_DATA_NGUOIDUNG_BYEMAIL"){
    $Email = addslashes($_GET['Email']);
    if ($Email == "")
    {
    	echo "[{}]";
    	exit;
    }
    $result = mysqli_query($conn, "Select nguoidung.*, chucdanh.TenCD, donvi.TenDV, phanquyen.TenPQ from nguoidung
								INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
								INNER JOIN phanquyen ON nguoidung.MaPQ = phanquyen.MaPQ 
								INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
								Where nguoidung.Email = '$Email'");
	if ($conn->error != ""){
        echo "{}";
    	exit;
    }else {
        echo json_encode(mysqli_fetch_array($result, MYSQLI_ASSOC), JSON_NUMERIC_CHECK);
    }
}

