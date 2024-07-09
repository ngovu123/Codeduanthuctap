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

if ($CMD == "GET_DATA_BAOHONG_BY_MAND"){
    $myArray = array();
    $MaND = addslashes($_GET['MaND']);
    if ($MaND == "")
    {
        echo json_encode($myArray, JSON_NUMERIC_CHECK);
    	exit;
    }
    $result = mysqli_query($conn, "	SELECT baoloi.*, phong.TenP, taisan.TenTS FROM baoloi 
								INNER JOIN phanbo ON baoloi.MaPB = phanbo.MaPB 
								INNER JOIN phong ON phanbo.MaP = phong.MaP
								INNER JOIN taisan ON taisan.MaTS = phanbo.MaTS
							    Where baoloi.MaND = '$MaND'
							    ORDER BY baoloi.TrangThai ASC");
    if ($conn->error == ""){
    	while ($row = $result->fetch_assoc()) {
    		$myArray[] = $row;	
    	}
    }
    echo json_encode($myArray, JSON_NUMERIC_CHECK);

    
}else if ($CMD == "ADD_NEW_BAOHONG"){
    $MaPB = addslashes($_GET['MaPB']);
    $MaND = addslashes($_GET['MaND']);
    $TinhTrang = addslashes($_GET['TinhTrang']);
    $MoTa = addslashes($_GET['MoTa']);
    $HinhAnh = addslashes($_GET['HinhAnh']);
    if ($MaPB == "" or $MaND == "" or $TinhTrang == "" or $MoTa == "" )
    {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
    	echo json_encode($array_message);
    	exit;
    }
    $query_baoloi = mysqli_query($conn,"SELECT * FROM baoloi WHERE MaPB = '".$MaPB."' and TrangThai <= 3");
    if (mysqli_num_rows($query_baoloi) >= 1){
		$array_message['code'] = 0;
		$array_message['message'] = "Tài sản này đã được báo hỏng rồi!!!";
		echo json_encode($array_message);
		exit;
    }
    
    $add_baoloi = mysqli_query($conn,"INSERT INTO baoloi(
    MaBL, MaPB, MaND, TinhTrang, Mota, HinhAnh, TrangThai, NgayCapNhat, NgayTao) 
    	VALUE 
    ('','{$MaPB}', '{$MaND}','{$TinhTrang}', '{$MoTa}','{$HinhAnh}', 1, '{$time}', '{$time}')");
    if ($conn->error == ""){
	    $array_message['code'] = 1;
        $array_message['message'] = "Thông tin báo lỗi của bạn đã được nhà trường ghi nhận. Vui lòng đợi chúng tôi ghi nhận và khắc phục!!!";
    }else {
    	$array_message['code'] = 0;
    	$array_message['message'] = "Có lỗi khi báo lỗi: " . $conn->error;
    }
    echo json_encode($array_message);

}

