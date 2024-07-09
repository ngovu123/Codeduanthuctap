<?php
$title = "Load data phân bổ";
include("config.php");
include("check_session.php");

if (!isset($_POST))
{
	$array_message['code'] = 0;
	$array_message['message'] = "Lỗi không xác định!!!";
	echo json_encode($array_message);
	exit;
}
$cmd = $_POST['cmd'];
$MaPhieu = $_POST['MaPhieu'];
$MaCTPKK = $_POST['MaCTPKK'];
$SLKK = $_POST['SLKK'];
$ConTot = $_POST['ConTot'];
$KemPC = $_POST['KemPC'];
$MatPC = $_POST['MatPC'];
$GhiChu = $_POST['GhiChu'];
if ($MaPhieu == "" or $cmd == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Số liệu không đúng, vui lòng kiểm tra lại!!!";
	echo json_encode($array_message);
	exit;
}else if ($cmd != "HoanTat" and $cmd != "LuuTamThoi")
{
	$array_message['code'] = 0;
	$array_message['message'] = "CMD không hợp lệ";
	echo json_encode($array_message);
	exit;
}
$Tong_PhamChat = 0;



for ($i = 0; $i < count($ConTot); $i++) {
	
	$Tong_PhamChat = intval($ConTot[$i]) + intval($KemPC[$i]) + intval($MatPC[$i]);
	$data_chitiet_phieukiemke = mysqli_query($conn, "select chitietphieukiemke.SoLuong from chitietphieukiemke 
									INNER JOIN phieukiemke ON phieukiemke.MaPhieu = chitietphieukiemke.MaPhieu 
									INNER JOIN phanbo ON phanbo.MaPB = chitietphieukiemke.MaPB 
									Where chitietphieukiemke.MaPhieu = '$MaPhieu' and chitietphieukiemke.MaCTPKK = '$MaCTPKK[$i]'");
	if (mysqli_num_rows($data_chitiet_phieukiemke) == 0) 
	{
		$array_message['code'] = 0;
		$array_message['message'] = "Không thể lấy dữ liệu từ bảng chi tiết phiếu kiểm kê!!!";
		echo json_encode($array_message);
		exit;
	}
	$row_chitiet_phieukiemke = mysqli_fetch_array($data_chitiet_phieukiemke);
	
	if ($cmd == "HoanTat")
	{
		if (intval($Tong_PhamChat) != intval($SLKK[$i]))	//intval($row_chitiet_phieukiemke['SoLuong'])
		{
			$array_message['code'] = 0;
			$array_message['message'] = "Mã ctpkk: " .$MaCTPKK[$i]. " - Thông số kiểm kê không đúng, vui lòng kiểm tra lại.";
			echo json_encode($array_message);
			exit;
		}else if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM bankiemke WHERE MaPhieu='$MaPhieu'")) == 0){
			$array_message['code'] = 0;
			$array_message['message'] = "Phiếu này chưa có ban kiểm kê, vui lòng thêm ban kiểm kê!";
			echo json_encode($array_message);
			exit;
		}
		/*
		else if (intval($SLKK[$i]) > intval($row_chitiet_phieukiemke['SoLuong']))
		{
			$array_message['code'] = 0;
			$array_message['message'] = "Số lượng - Theo sổ kiểm kê không được lơn hơn số lượng - Theo sổ kế toán !!!";
			echo json_encode($array_message);
			exit;
		}
		*/
	}
	
	else if ($cmd == "LuuTamThoi")
	{
		/*
		if ($Tong_PhamChat > intval($row_chitiet_phieukiemke['SoLuong']))	
		{
			$array_message['code'] = 0;
			$array_message['message'] = "Mã ctpkk: " .$MaCTPKK[$i]. " - Thông số kiểm kê không đúng, vui lòng kiểm tra lại. \n";
			echo json_encode($array_message);
			exit;
		}
		*/
	}
	
	else {
		$array_message['code'] = 0;
		$array_message['message'] = "CMD không hợp lệ!!!";
		echo json_encode($array_message);
		exit;
	}
}


for ($i = 0; $i < count($ConTot); $i++) {
	if ($cmd == "HoanTat")
	{
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `SoLuongKiemKe` = '".$SLKK[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `ConTot` = '".$ConTot[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `KemPC` = '".$KemPC[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `MaPC` = '".$MatPC[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `GhiChu` = '".$GhiChu[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");	
	}else if ($cmd == "LuuTamThoi")
	{
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `SoLuongKiemKe` = '".$SLKK[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `ConTot` = '".$ConTot[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `KemPC` = '".$KemPC[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `MaPC` = '".$MatPC[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
		mysqli_query($conn,"UPDATE `chitietphieukiemke` SET `GhiChu` = '".$GhiChu[$i]."' where `MaPhieu`='".$MaPhieu."' and MaCTPKK='".$MaCTPKK[$i]."'");
	}
}

if ($conn->error == ""){
	if ($cmd == "HoanTat")
	{
		_add_data_nkhd($conn, $data['MaND'],"Sửa", "Hoàn tất kiểm kê với Mã Phiếu là: ".$MaPhieu,$time);
		mysqli_query($conn,"UPDATE `phieukiemke` SET `TrangThai` = '1' where `MaPhieu`='".$MaPhieu."'");
		$array_message['code'] = 2;
		$array_message['message'] = "Hoàn tất phiếu kiểm kê !!!" . $conn->error;
		echo json_encode($array_message);
		exit();
	}else if ($cmd == "LuuTamThoi")
	{
		_add_data_nkhd($conn, $data['MaND'],"Sửa", "Lưu tạm thời với Mã Phiếu là: ".$MaPhieu,$time);
		$array_message['code'] = 1;
		$array_message['message'] = "Lưu tạm thành công !!!" . $conn->error;
		echo json_encode($array_message);
		exit();
	}
	
	
	
	
}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thực hiện kiểm kê: " . $conn->error;
	echo json_encode($array_message);
	exit();
}