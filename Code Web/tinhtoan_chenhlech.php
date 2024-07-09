<?php 
$title = "Thông kê - tính toán chênh lệch";
include("config.php");
if(!isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();     
}

$MaPhieu = $_GET['MaPhieu'];
if ($MaPhieu == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}


if (mysqli_num_rows(mysqli_query($conn, "select * from phieukiemke Where MaPhieu = '$MaPhieu'")) == 0)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Không tồn tại mã phiếu này!!";
	echo json_encode($array_message);
	exit;
}else if (mysqli_num_rows(mysqli_query($conn, "select * from phieukiemke Where MaPhieu = '$MaPhieu' and TrangThai = 1")) == 0)
{
	$array_message['code'] = 0;
	$array_message['message'] = "Phiếu này chưa hoàn tất!!!";
	echo json_encode($array_message);
	exit;
}
$TongSo_TB_CoTrongPhong = 0;
$TongSo_TB_ChuaDuocKiemKe = 0;
$TongSo_TB_BiMat = 0;
$TongSo_TB_KiemKe = 0;
$TongSo_TB_ConTot = 0;
$TongSo_TB_KemPC = 0;
$TongSo_TB_MatPC = 0;
$arr_themmoi = array();
$arr_thieu = array();

$row_phieukiemke = mysqli_fetch_array(mysqli_query($conn, "select * from phieukiemke Where MaPhieu = '$MaPhieu' and TrangThai = 1"));
$query_phanbo = mysqli_query($conn, "select * from phanbo Where MaP = '".$row_phieukiemke['MaP']."'");

while ($row = $query_phanbo->fetch_assoc()) {

	$query_chitiet_phieukiemke = mysqli_query($conn, "select * from chitietphieukiemke Where MaPhieu = '$MaPhieu' and MaPB = '".$row['MaPB']."'");
	$row_taisan = mysqli_fetch_array(mysqli_query($conn, "select * from taisan Where MaTS = '".$row['MaTS']."'"));
	$TongSo_TB_CoTrongPhong += intval($row['SoLuong']);
	
	if (mysqli_num_rows($query_chitiet_phieukiemke) == 0)
	{
		$TongSo_TB_ChuaDuocKiemKe += intval($row['SoLuong']);
		$arr_themmoi[] = [$row_taisan['MaTS'],$row_taisan['TenTS'], intval($row['SoLuong'])];
		//echo $row_taisan['TenTS'] . " đã được thêm với số lượng1: " . intval($row['SoLuong']) . "<br>";
	}else {

		$row_chitiet_phieukiemke = mysqli_fetch_array(mysqli_query($conn, "select * from chitietphieukiemke Where MaPhieu = '$MaPhieu' and MaPB = '".$row['MaPB']."'"));
		$SoLuong_ChenhLech = intval($row_chitiet_phieukiemke['SoLuongKiemKe']) - intval($row['SoLuong']);
		$TongSo_TB_KiemKe += intval($row_chitiet_phieukiemke['SoLuongKiemKe']);
		$TongSo_TB_ConTot += intval($row_chitiet_phieukiemke['ConTot']);
		$TongSo_TB_KemPC += intval($row_chitiet_phieukiemke['KemPC']);
		$TongSo_TB_MatPC += intval($row_chitiet_phieukiemke['MaPC']);
		
		
		
		if (intval($SoLuong_ChenhLech) < 0)
		{
			
			
			$TongSo_TB_BiMat += ($SoLuong_ChenhLech * -1);
			$arr_thieu[] = [$row_taisan['MaTS'],$row_taisan['TenTS'],  intval($SoLuong_ChenhLech * -1)];
		}else if (intval($SoLuong_ChenhLech) >= 1)
		{
			
			$TongSo_TB_ChuaDuocKiemKe += $SoLuong_ChenhLech;
			$arr_themmoi[] = [$row_taisan['MaTS'],$row_taisan['TenTS'], intval($SoLuong_ChenhLech)];
			
		}else{
			//echo $row_taisan['TenTS'] . " đủ" . "<br>";
		}
		
	}



}


/*
echo "Tổng số các thiết bị trong phòng: ".$TongSo_TB_CoTrongPhong . "<br>";

echo "Tổng số các thiết bị kiểm kê: ".$TongSo_TB_KiemKe . "<br>";

echo "Tổng số các thiết bị chưa được kiểm kê: ".$TongSo_TB_ChuaDuocKiemKe . "<br>";

echo "Tổng số các thiết bị mất hoặc thiếu: ".$TongSo_TB_BiMat . "<br>";


echo "Tổng số các thiết bị còn tốt: ".$TongSo_TB_ConTot . "<br>";

echo "Tổng số các thiết bị kém pc: ".$TongSo_TB_KemPC . "<br>";

echo "Tổng số các thiết bị mất pc: ".$TongSo_TB_MatPC . "<br>";


echo json_encode($arr_themmoi);
echo "<br>";
echo json_encode($arr_thieu);
*/

$array_message['code'] = 1;
$array_message['tongso_thietbi_trongphong'] = $TongSo_TB_CoTrongPhong;
$array_message['tongso_thietbi_kiemke'] = $TongSo_TB_KiemKe;
$array_message['tongso_thietbi_chuakiemke'] = $TongSo_TB_ChuaDuocKiemKe;
$array_message['tongso_thietbi_thieu_mat'] = $TongSo_TB_BiMat;
$array_message['tongso_thietbi_contot'] = $TongSo_TB_ConTot;
$array_message['tongso_thietbi_kempc'] = $TongSo_TB_KemPC;
$array_message['tongso_thietbi_matpc'] = $TongSo_TB_MatPC;
$array_message['thietbithemmoi'] = $arr_themmoi;
$array_message['thietbithieu'] = $arr_thieu;
echo json_encode($array_message);








?>