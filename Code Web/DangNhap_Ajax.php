<?php 
include('config.php'); 
if(isset($_SESSION['TenDangNhap'])){
	$array_message['code'] = 0;
	$array_message['message'] = "Bạn đã đăng nhập rồi";
	echo json_encode($array_message);
	exit();   
}
if(isset($_POST['username']) && isset($_POST['password'])){
	$username = mysqli_real_escape_string($conn,$_POST['username']);
	$password = mysqli_real_escape_string($conn,$_POST['password']);
	$query = mysqli_query($conn,"SELECT * FROM nguoidung WHERE TenDangNhap = '$username'");
	if (mysqli_num_rows($query) == 0) 
	{
		$array_message['code'] = 0;
		$array_message['message'] = "Tên đăng nhập này không tồn tại. Vui lòng kiểm tra lại.";
		echo json_encode($array_message);
		exit;
	}
	$row = mysqli_fetch_array($query);
	if ($password != $row['MatKhau']) {
		$array_message['code'] = 0;
		$array_message['message'] = "Tên đăng nhập hoặc mật khẩu không hợp lệ.";
		echo json_encode($array_message);
		exit;
	}
	
	$_SESSION['TenDangNhap'] = $row['TenDangNhap'];	
	_add_data_nkhd($conn, $row['MaND'],"Đăng nhập", $row['TenDangNhap'] . " Vừa đăng nhập vào hệ thống",$time);
	$array_message['code'] = 1;
	$array_message['message'] = "Đăng nhập thành công";
	$array_message['success'] = 'index.php';
	echo json_encode($array_message);
	exit;
}else {
	$array_message['code'] = 0;
	$array_message['message'] = "Thông tin chưa đầy đủ.";
	echo json_encode($array_message);
	exit;
}