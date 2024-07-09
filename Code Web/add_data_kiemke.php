<?php
$title = "Thêm mới phiếu kiểm kê";
include("config.php");
include("check_session.php");

$MaP = addslashes($_POST['themmoi_nv_kiemke_map']);
$GhiChu = addslashes($_POST['themmoi_nv_kiemke_ghichu']);
$MaND = $data['MaND'];
if ($MaP == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
if ($GhiChu == "")
{
	$GhiChu = "Không có";
}

if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM phieukiemke WHERE TrangThai=0 and MaP = '$MaP'")) > 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Phòng này bạn còn 1 phiếu kiểm kê chưa hoàn thành, vui lòng hoàn thành để thêm mới phiếu kiểm kê khác!!!";
	echo json_encode($array_message);
	exit;
}

if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM phanbo WHERE MaP = '$MaP'")) == 0){
	$array_message['code'] = 0;
	$array_message['message'] = "Phòng này hiện chưa có TS-CCDC. Để kiểm kê bạn cần thêm TS-CCDC vào phòng này!";
	echo json_encode($array_message);
	exit;
}


$add_kiemke = mysqli_query($conn,"INSERT INTO phieukiemke(MaPhieu, MaP, MaND, GhiChu, TrangThai, NgayCapNhat, NgayTao) VALUE ('','{$MaP}','{$MaND}', '{$GhiChu}', 0, '{$time}', '{$time}')");
if ($conn->error != ""){
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới phiếu kiểm kê: " . $conn->error;
	echo json_encode($array_message);
	exit();
}

$MaPhieu = $conn->insert_id;
	
$query_phanbo_taisan = mysqli_query($conn, "select * from phanbo Where MaP = '".$MaP."'");
while ($row = $query_phanbo_taisan->fetch_assoc()) {
	mysqli_query($conn,"INSERT INTO chitietphieukiemke  
						(MaCTPKK, MaPhieu, MaPB, SoLuong, ConTot, KemPC, MaPC, GhiChu, NgayCapNhat, NgayTao) 
							VALUE 
						('','{$MaPhieu}', '".$row['MaPB']."', '".$row['SoLuong']."', 0, 0, 0, 'Không có','{$time}', '{$time}')");
}
if ($conn->error == ""){
	_add_data_nkhd($conn, $data['MaND'],"Thêm","Thêm mới phiếu kiểm kê có Mã phiếu là: " . $MaPhieu,$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Thêm mới phiếu kiểm kê !!!";

}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Có lỗi khi thêm mới phiếu kiểm kê: " . $conn->error;
}
echo json_encode($array_message);


?>