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
$Arr_MaPB = $_POST['MaPB'];
$Arr_Tang = $_POST['Tang'];
$Arr_Giam = $_POST['Giam'];
if (count($Arr_MaPB) != count($Arr_Tang) or count($Arr_MaPB) != count($Arr_Giam) or count($Arr_Tang) != count($Arr_Giam))
{
	$array_message['code'] = 0;
	$array_message['message'] = "Số liệu không đúng, vui lòng kiểm tra lại!!!";
	echo json_encode($array_message);
	exit;
}

for ($i = 0; $i < count($Arr_MaPB); $i++) {
	if ($Arr_MaPB[$i] < 0 or $Arr_Tang[$i] < 0 or $Arr_Giam[$i] < 0)
	{
		$array_message['code'] = 0;
		$array_message['message'] = "Các số liệu không được bé hơn 0 !!!";
		echo json_encode($array_message);
		exit;
	}
}

for ($i = 0; $i < count($Arr_MaPB); $i++) {
	$TongSL_Tang_Giam = 0;
	$result_pb = mysqli_fetch_array(mysqli_query($conn, "	select taisan.MaTS, taisan.SLNhapVao, taisan.SLHienCon, phanbo.SoLuong from phanbo 
														INNER JOIN taisan ON phanbo.MaTS = taisan.MaTS
														Where phanbo.MaPB = '$Arr_MaPB[$i]'"));
														
														
	if ($Arr_Tang[$i] >= 1 and $Arr_Giam[$i] == 0)													
	{
		if (intval($Arr_Tang[$i]) > intval($result_pb['SLHienCon']))
		{
			$array_message['code'] = 0;
			$array_message['message'] = "Hệ thống không dủ tài sản để thêm vào phòng cho bạn, vui lòng điều chỉnh lại số lượng muốn tăng!!!";
			echo json_encode($array_message);
			exit;
		}else if ( (intval($Arr_Tang[$i]) + intval($result_pb['SoLuong'])) >  $result_pb['SLHienCon'])
		{
			$array_message['code'] = 0;
			$array_message['message'] = "Tổng số lượng tài sản phòng này đã vượt quá số lượng hiện còn của tài sản này, vui lòng điều chỉnh lại số lượng muốn tăng!!!";
			echo json_encode($array_message);
			exit;
		}
	}else if ($Arr_Tang[$i] == 0 and $Arr_Giam[$i] >= 1)
	{
		if ((intval($result_pb['SoLuong']) - intval($Arr_Giam[$i])) < 0)
		{
			$array_message['code'] = 0;
			$array_message['message'] = "SL có trong phòng trừ SL muốn giảm phải lớn hơn hoặc bằng 0, vui lòng điều chỉnh lại!!!";
			echo json_encode($array_message);
			exit;
		}
	}else if (intval($Arr_Tang[$i]) >= 1 and intval($Arr_Giam[$i]) >= 1 And intval($Arr_Tang[$i]) != intval($Arr_Giam[$i]))
	{
		
		if (intval($Arr_Tang[$i]) > intval($Arr_Giam[$i]))
		{
			$TongSL_Tang_Giam = intval($Arr_Tang[$i]) - intval($Arr_Giam[$i]);
			if ( (intval($TongSL_Tang_Giam) + intval($result_pb['SoLuong'])) > $result_pb['SLHienCon'])
			{
				$array_message['code'] = 0;
				$array_message['message'] = "Tổng số lượng tài sản phòng này đã vượt quá số lượng hiện còn của tài sản này, vui lòng điều chỉnh lại số lượng muốn tăng!!!";
				echo json_encode($array_message);
				exit;
			}	
		}else if (intval($Arr_Tang[$i]) < intval($Arr_Giam[$i]))
		{
			$TongSL_Tang_Giam = intval($Arr_Giam[$i]) - intval($Arr_Tang[$i]);
			if ( (intval($TongSL_Tang_Giam) + intval($result_pb['SLHienCon'])) > $result_pb['SLNhapVao'])
			{
				$array_message['code'] = 0;
				$array_message['message'] = "Tổng số lượng tài sản hiện còn của phòng này đã lớn hơn số lượng nhập vào, vui lòng xem lại số liệu đã nhập !!!";
				echo json_encode($array_message);
				exit;
			}else if (intval($TongSL_Tang_Giam) > intval($result_pb['SoLuong']))
			{
				$array_message['code'] = 0;
				$array_message['message'] = "Số lượng muốn giảm đi không hợp lệ!!!";
				echo json_encode($array_message);
				exit;
			}
		}	
	}	

}













for ($i = 0; $i < count($Arr_MaPB); $i++) {
	$TongSL_Tang_Giam = 0;
	$result_pb = mysqli_fetch_array(mysqli_query($conn, "	select phong.TenP, taisan.TenTS, taisan.MaTS, taisan.SLNhapVao, taisan.SLHienCon, phanbo.SoLuong from phanbo 
														INNER JOIN taisan ON phanbo.MaTS = taisan.MaTS
														INNER JOIN phong ON phanbo.MaP = phong.MaP
														Where phanbo.MaPB = '$Arr_MaPB[$i]'"));		
														
	$Ten_TS = $result_pb['TenTS'];
	$Ten_P = $result_pb['TenP'];
	$SoLuongHienCon_TaiSan =  intval($result_pb['SLHienCon']);
	$SoLuong_PhanBo =  intval($result_pb['SoLuong']);
	if ($Arr_Tang[$i] >= 1 and $Arr_Giam[$i] == 0)													
	{
		_add_data_nkhd($conn, $data['MaND'],"Sửa", "Ghi tăng - ghi giảm Phòng: ".$Ten_P." - Tên TS: ".$Ten_TS." - Số lượng cũ: ".$SoLuong_PhanBo." - Số lượng mới: ".intval($SoLuong_PhanBo+intval($Arr_Tang[$i])),$time);
		$edit_taisan_ghitang = mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` - ".intval($Arr_Tang[$i])." where `MaTS`='".$result_pb['MaTS']."'");
		$edit_phanbo_ghitang = mysqli_query($conn,"UPDATE `phanbo` SET `SoLuong` = `SoLuong` + ".intval($Arr_Tang[$i])." where `MaPB` = '$Arr_MaPB[$i]'");

	}else if ($Arr_Tang[$i] == 0 and $Arr_Giam[$i] >= 1)
	{
		if ((intval($result_pb['SoLuong']) - intval($Arr_Giam[$i])) == 0)
		{
			
			$edit_taisan_ghigiam = mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` + ".intval($Arr_Giam[$i])." where `MaTS`='".$result_pb['MaTS']."'");
			if ($conn->error == ""){
				if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM chitietphieukiemke WHERE MaPB = '$Arr_MaPB[$i]'")) == 0)
				{
					_add_data_nkhd($conn, $data['MaND'],"Sửa", "Ghi tăng - ghi giảm Phòng: ".$Ten_P." - Tên TS: ".$Ten_TS." - Số lượng cũ: ".$SoLuong_PhanBo." - Số lượng mới: 0",$time);
					$xoa_phanbo = mysqli_query($conn, "	DELETE FROM phanbo Where phanbo.MaPB = '$Arr_MaPB[$i]'");
				}else {
					$array_message['code'] = 1;
					$array_message['message'] = "Mã phân bố này đã tồn tại trong 1 phiếu kiểm kê nào đó nên bạn không thể đặt nó về 0 và xóa, vui lòng điều chỉnh lại số liệu !!!";
					echo json_encode($array_message);
					exit();
				}
				
			}
		}else {
			_add_data_nkhd($conn, $data['MaND'],"Sửa", "Ghi tăng - ghi giảm Phòng: ".$Ten_P." - Tên TS: ".$Ten_TS." - Số lượng cũ: ".$SoLuong_PhanBo." - Số lượng mới: ".intval($SoLuong_PhanBo-intval($Arr_Giam[$i])),$time);
			$edit_taisan_ghigiam = mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` + ".intval($Arr_Giam[$i])." where `MaTS` = '".$result_pb['MaTS']."'");
			$edit_phanbo_ghigiam = mysqli_query($conn,"UPDATE `phanbo` SET `SoLuong` = `SoLuong` - ".intval($Arr_Giam[$i])." where `MaPB` = '$Arr_MaPB[$i]'");
		}
		
	}else if (intval($Arr_Tang[$i]) >= 1 and intval($Arr_Giam[$i]) >= 1 And intval($Arr_Tang[$i]) != intval($Arr_Giam[$i]))
	{
		if (intval($Arr_Tang[$i]) > intval($Arr_Giam[$i]))
		{
			$TongSL_Tang_Giam = intval($Arr_Tang[$i]) - intval($Arr_Giam[$i]);
			_add_data_nkhd($conn, $data['MaND'],"Sửa", "Ghi tăng - ghi giảm Phòng: ".$Ten_P." - Tên TS: ".$Ten_TS." - Số lượng cũ: ".$SoLuong_PhanBo." - Số lượng mới: ".intval($SoLuong_PhanBo+intval($TongSL_Tang_Giam)),$time);
			$edit_phanbo_ghitang_ghigiam = mysqli_query($conn,"UPDATE `phanbo` SET `SoLuong` = `SoLuong` + ".intval($TongSL_Tang_Giam)." where `MaPB` = '$Arr_MaPB[$i]'");
			$edit_taisan_ghigiam = mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` - ".intval($TongSL_Tang_Giam)." where `MaTS` = '".$result_pb['MaTS']."'");
		}else if (intval($Arr_Tang[$i]) < intval($Arr_Giam[$i]))
		{
			$TongSL_Tang_Giam = intval($Arr_Giam[$i]) - intval($Arr_Tang[$i]);
			_add_data_nkhd($conn, $data['MaND'],"Sửa", "Ghi tăng - ghi giảm Phòng: ".$Ten_P." - Tên TS: ".$Ten_TS." - Số lượng cũ: ".$SoLuong_PhanBo." - Số lượng mới: ".intval($SoLuong_PhanBo-intval($TongSL_Tang_Giam)),$time);
			$edit_phanbo_ghitang_ghigiam = mysqli_query($conn,"UPDATE `phanbo` SET `SoLuong` = `SoLuong` - ".intval($TongSL_Tang_Giam)." where `MaPB` = '$Arr_MaPB[$i]'");
			$edit_taisan_ghigiam = mysqli_query($conn,"UPDATE `taisan` SET `SLHienCon` = `SLHienCon` + ".intval($TongSL_Tang_Giam)." where `MaTS` = '".$result_pb['MaTS']."'");
		}	
	}	
}

if ($conn->error == ""){
	$array_message['code'] = 1;
	$array_message['message'] = "Thực hiện ghi tăng - ghi giảm thành công !!!";
	echo json_encode($array_message);
	exit();
}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thực hiện ghi tăng - ghi giảm phân bổ: " . $conn->error;
	echo json_encode($array_message);
	exit();
}
?>
