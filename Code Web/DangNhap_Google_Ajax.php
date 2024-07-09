<?php 
include('config.php'); 
if(isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();   
}

if(isset($_GET["code"]))
{
	$token = $google_client->fetchAccessTokenWithAuthCode($_GET["code"]);
	if(!isset($token['error']))
	{
		$google_client->setAccessToken($token['access_token']);
		$google_service = new Google_Service_Oauth2($google_client);
		$data_1 = $google_service->userinfo->get();
		if(!empty($data_1['name']) and !empty($data_1['email']))
		{
			if (!strstr($data_1['email'],'@student.tdmu.edu.vn')) // email không phải của trường
			{
				header("Location: DangNhap.php");
				exit(); 
			}
			
			$tendangnhap = $data_1['email'];
			$hovaten = $data_1['name'];
			$email = $data_1['email'];
			$matkhau = strval(rand(100000,99999)) . '' . strval($tendangnhap);
			
			if (mysqli_num_rows(mysqli_query($conn,"SELECT * FROM nguoidung WHERE TenDangNhap='$tendangnhap'")) <= 0){
				$add_nguoidung = mysqli_query($conn,"INSERT INTO nguoidung	(
				MaND, MaDV, MaCD, MaPQ, TenDangNhap, MatKhau, HoVaTen, Email, SoDienThoai, DiaChi, NgayTao, NgayCapNhat) 
					VALUE 
				('',5, 7, 2, '{$tendangnhap}', '{$matkhau}', '{$hovaten}', '{$email}', '', '', '{$time}', '{$time}')");
			}
			$_SESSION['TenDangNhap'] = $tendangnhap;
			header("Location: index.php");
			exit(); 
			
			
		}else { // không có name hoặc email
			header("Location: DangNhap.php");
			exit(); 
		}
	}else { // có lỗi token
		header("Location: DangNhap.php");
		exit(); 
	}
}else { // có lỗi code
	header("Location: DangNhap.php");
	exit(); 
}