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
$myArray = array();

if ($CMD == "GET_DATA_PHONG"){
    $result = mysqli_query($conn, "	SELECT phong.*, khuvucphong.MaKVP, khuvucphong.TenKVP, loaiphong.MaLP, loaiphong.TenLP FROM phong 
    								INNER JOIN khuvucphong ON phong.MaKVP = khuvucphong.MaKVP 
    								INNER JOIN loaiphong ON loaiphong.MaLP = phong.MaLP ");
    if ($conn->error == ""){
    	while ($row = $result->fetch_assoc()) {
    		$myArray[] = $row;
    	}
    }
    echo json_encode($myArray, JSON_NUMERIC_CHECK);
}else if ($CMD == "GET_DATA_TAISAN_BAOLOI_IN_PHONG"){
    $MaP = addslashes($_GET['MaP']);
    if ($MaP == "")
    {
    	echo "[]";
    	exit;
    }
    
    $data = [];

    // Lấy toàn bộ danh sách tài sản có trong phòng học
    $result_phanbo = mysqli_query($conn, '	SELECT phanbo.MaPB, 
                                            taisan.MaTS, loaitaisan.MaLTS, nhomtaisan.MaNTS, phanbo.SoLuong, phong.TenP, taisan.TenTS, taisan.HangSanXuat, taisan.NuocSanXuat, taisan.NamSanXuat,loaitaisan.TenLTS, nhomtaisan.TenNTS, nguoidung.MaND,
                                            phanbo.NgayCapNhat,
                                            phanbo.NgayTao
                                    FROM phanbo 
                                      INNER JOIN nguoidung ON nguoidung.MaND = phanbo.MaND 
                                      INNER JOIN phong ON phong.MaP = phanbo.MaP 
                                      INNER JOIN taisan ON taisan.MaTS = phanbo.MaTS 
                                      INNER JOIN loaitaisan ON taisan.MaLTS = loaitaisan.MaLTS 
                                      INNER JOIN nhomtaisan ON taisan.MaNTS = nhomtaisan.MaNTS 
                                    Where phong.MaP = '.$MaP.'
                                    Order by phanbo.MaPB ASC
                                    ');

    if ($conn->error == ""){
        if (mysqli_num_rows($result_phanbo) == 0) // nếu như không có tài sản nào.
        {
            echo "[]";
            exit();
        }
    	while ($row = $result_phanbo->fetch_assoc()) { // lặp qua phần tử trong bảng PhanBo để lấy MaPB so sánh xem có trong bảng BaoLoi hay không.
    		$phanbo_in_baoloi = mysqli_query($conn, 'SELECT baoloi.* FROM baoloi Where baoloi.MaPB = '.$row["MaPB"].'');
    		if (mysqli_num_rows($phanbo_in_baoloi) <= 0){
    		    $customData = [
                                    "MaTS" => $row['MaTS'], 
                                    "MaLTS" => $row['MaLTS'],
                                    "MaNTS" => $row['MaNTS'],
                                    "MaBL" => 0,
                                    "MaPB" => $row['MaPB'],
                                    "MaND" => $row['MaND'],
                                    "TinhTrang" => 0,
                                    "TrangThai" => 4,
                                    "SoLuong" => $row['SoLuong'],
                                    "TenP" => $row['TenP'],
                                    "TenTS" => $row['TenTS'],
                                    "TenLTS" => $row['TenLTS'],
                                    "TenNTS" => $row['TenNTS'],
                                    "HangSanXuat" => $row['HangSanXuat'],
                                    "NuocSanXuat" => $row['NuocSanXuat'],
                                    "NamSanXuat" => intval($row['NamSanXuat']),
                                    "Mota" => "",
                                    "HinhAnh" => "",
                                    "NgayCapNhat" => $row['NgayCapNhat'],
                                    "NgayTao" => $row['NgayTao']
                                ];
                $data[] = $customData;
    		}else if (mysqli_num_rows($phanbo_in_baoloi) > 0){
    		    while ($row_in = $phanbo_in_baoloi->fetch_assoc()){
                if ($row_in['TrangThai'] > 3) continue;
                $customData = [
                                    "MaTS" => $row['MaTS'], 
                                    "MaLTS" => $row['MaLTS'],
                                    "MaNTS" => $row['MaNTS'],
                                    "MaBL" => $row_in['MaBL'],
                                    "MaPB" => $row_in['MaPB'],
                                    "MaND" => $row['MaND'],
                                    "TinhTrang" => $row_in['TinhTrang'],
                                    "TrangThai" => $row_in['TrangThai'],
                                    "SoLuong" => $row['SoLuong'],
                                    "TenP" => $row['TenP'],
                                    "TenTS" => $row['TenTS'],
                                    "TenLTS" => $row['TenLTS'],
                                    "TenNTS" => $row['TenNTS'],
                                    "HangSanXuat" => $row['HangSanXuat'],
                                    "NuocSanXuat" => $row['NuocSanXuat'],
                                    "NamSanXuat" => intval($row['NamSanXuat']),
                                    "Mota" => $row_in['Mota'],
                                    "HinhAnh" => $row_in['HinhAnh'],
                                    "NgayCapNhat" => $row['NgayCapNhat'],
                                    "NgayTao" => $row['NgayTao']
                                ];
                    $data[] = $customData;
                }
    		}else {
		        echo "[]";
                exit();
    		}
    	}
    }
    echo json_encode($data, JSON_NUMERIC_CHECK);

    
}

