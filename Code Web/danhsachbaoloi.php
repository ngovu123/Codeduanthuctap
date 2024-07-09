<?php
$title = "Kiểm kê tài sản TDMU";
include("config.php");



$TongSo_TaiSan = 0;
$query_taisan = mysqli_query($conn, "select * from taisan");
while ($row = $query_taisan->fetch_assoc()) {
	$TongSo_TaiSan += $row['SLNhapVao'];
}
$TongSo_NguoiDung = mysqli_num_rows(mysqli_query($conn, "select * from nguoidung"));
$TongSo_PhongHoc = mysqli_num_rows(mysqli_query($conn, "select * from phong"));


?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $title; ?></title>
    <link rel="stylesheet" href="content/boostrapTDMU.css" />
    <link rel="stylesheet" href="content/login.css" />
    <link rel="stylesheet" href="content/animation.css" />
    <link rel="stylesheet" href="content/BaoLoi.css" />
    <link rel="stylesheet" href="content/Index.css" />
    <link rel="stylesheet" href="content/ThongTinCaNhan.css" />
    <link rel="stylesheet" href="WOW-master/dist/wow.min.js">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


    <link rel="shortcut icon" href="https://tdmu.edu.vn/hinh/Icon.png">
    <link rel="icon" type="image/png" sizes="192x192" href="https://tdmu.edu.vn/hinh/Icon.png">
    <link rel="apple-touch-icon" sizes="180x180" href="https://tdmu.edu.vn/hinh/Icon.png">
</head>
    <div>
        <!--Phần header-->
        <header class="d-flex bd-highlight">
            <div class="container d-flex align-items-center justify-content-between">
                <div id="logo p-2 bd-highlight">
                    <a href="#" class="the-a-full">
                        <img style="height: 100px;" class="img-fluid" src="https://vienktcn.tdmu.edu.vn/img/1/Result/Images/LOGO-CHU-NEW.png" alt>
                    </a>
                </div>
                <form action="#" id="search-box" >
                    <input type="text" id="search-text" placeholder="Bạn muốn tìm gì ?" required />
                    <button id="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
              
            </div>
        </header>
    </div>
    <!--Phần nav-->
    
    <nav class="navbar navbar-expand-sm bg-blue navbar-dark sticky">
        <div class="container">
            <a class="navbar-brand" href="#">Trang Chủ</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul class="navbar-nav h4">
                        <li class="nav-item">
                            <a class="nav-link" href="https://tdmu.edu.vn/gioi-thieu">Giới thiệu</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://tdmu.edu.vn/lien-he">Liên hệ</a>
                        </li>
                    </ul>
                </div>
				
				<?php 
					if(!isset($_SESSION['TenDangNhap'])){
						echo '
							<a href="DangNhap.php" class="nav-link" style="text-decoration: none; color: white; font-size: 1.7rem;">
								<i class="fa-solid fa-user" style="padding-right: 10px; font-size:2rem;"></i>Tài khoản
							</a> 
						';
					}else {
						if ($data['TenPQ'] == "Admin" or $data['TenPQ'] == "KiemKe")
						{
							echo '
							<div class="dropdown">
								<a href="#" class="nav-link" style="text-decoration: none; color: white; font-size: 1.7rem;">
									<i class="fa-solid fa-user" style="padding-right: 10px; font-size:2rem;"></i>Xin chào - '.$data['HoVaTen'].'
								</a>
								<ul class="dropdown-content" style="list-style: none">
									<li><a href="tongquan.php"><i class="fa-regular fa-user"></i>Trang quản lý</a> </li>
									<li><a href="danhsachbaoloi.php"><i class="fa fa-note-sticky"></i>Danh sách báo lỗi</a></li>
									<li><a href="DangXuat.php"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a></li>
								</ul>
							</div>';
						}else {
							echo '
							<div class="dropdown">
								<a href="#" class="nav-link" style="text-decoration: none; color: white; font-size: 1.7rem;">
									<i class="fa-solid fa-user" style="padding-right: 10px; font-size:2rem;"></i>Xin chào - '.$data['HoVaTen'].'
								</a>
								<ul class="dropdown-content" style="list-style: none">
									<li><a href="danhsachbaoloi.php"><i class="fa fa-note-sticky"></i>Danh sách báo lỗi</a></li>
									<li><a href="DangXuat.php"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a></li>
								</ul>
							</div>
						';
						}
						
					}
				
				?>

                <!--  -->

        </div>
    </nav>
    
    <!--Hình ảnh bìa-->
    <div style="position: relative">
        <img src="./img/img-kham-pha-tdmu.jpg" style=" min-width: calc(100%); height: 450px; object-fit: cover; filter: brightness(70%); border: 5px solid transparent; width: 90vw;" alt=""/>
        <div style=" position: absolute; top: 40%; left: 35%; transform: translate(-50%, -50%); text-align: left; color:white;">
            <div>
                <h1 style="font-weight: 700;">Đại học Thủ Dầu Một</h1>
                <h4>TDMU - Thu Dau Mot University</h4><hr />
                <button type="button" class="btn" style="background-color: #e05a0e; font-weight: 600; color: white; ">Tham quan</button>
            </div>

        </div>
    </div>
    <!--Thông tin thiết bị-->
    
    <div class="container-xxl py-5" >
	<center><h3>Danh sách báo lỗi của bạn.</h3></center><br>
        <div class="container" style="border-bottom: 2px solid #ff6a00; padding-bottom: 5px;">
            <div class="row">
                <table class="table table-bordered">
				
					<thead>
					  <tr>
						<th>Mã báo lỗi</th>
						<th>Tên phòng</th>
						<th>Tên tài sản</th>
						<th>Mô tả hư hỏng</th>
						<th>Trạng thái</th>
						<th>Thời gian</th>
					  </tr>
					</thead>
					<tbody>
					<?php 
					
								
								
								
						$result = mysqli_query($conn, "	SELECT baoloi.MaBL, phong.TenP, taisan.TenTS, baoloi.NgayCapNhat, baoloi.TrangThai, baoloi.Mota FROM baoloi 
														INNER JOIN phanbo ON baoloi.MaPB = phanbo.MaPB 
														INNER JOIN phong ON phanbo.MaP = phong.MaP
														INNER JOIN taisan ON taisan.MaTS = phanbo.MaTS
														where baoloi.MaND = '".$data['MaND']."'");
						while ($row = $result->fetch_assoc()) {
							if ($row['TrangThai'] == 0)
							{
								echo '
								<tr>
									<td>'.$row['MaBL'].'</td>
									<td>'.$row['TenP'].'</td>
									<td>'.$row['TenTS'].'</td>
									<td>'.$row['Mota'].'</td>
									<td><span class="badge bg-danger text-light">Chưa sửa chữa</span></td>
									<td>'.$row['NgayCapNhat'].'</td>
								</tr>';
							}else {
								echo '
								<tr>
									<td>'.$row['MaBL'].'</td>
									<td>'.$row['TenP'].'</td>
									<td>'.$row['TenTS'].'</td>
									<td>'.$row['Mota'].'</td>
									<td><span class="badge bg-warning text-light">Đã sửa chữa</span></td>
									<td>'.$row['NgayCapNhat'].'</td>
								</tr>';
							}
							
						}
					?>
						  
					</tbody>
					
				</table>


            </div>
        </div>
    </div>
<!--Thông tin-->

 
 
<!--Báo lỗi-->














<!--Footer-->
<footer style="background-color: #01314c;">
    <div class="container" style="color: #fff;">
        <div class="row" style="margin-top: 30px; margin-bottom: 10px">
            <div class="col-md-4 margin-bottom-5px">
                <h4 style="margin-bottom: 15px;">THÔNG TIN</h4><hr />
                <p>Địa chỉ: Số 06, Trần Văn Ơn, Phú Hòa, Thủ Dầu Một, Bình Dương</p>
                <p>Điện thoại: (0274) 3834512 (Ext 102)</p>
                <p>Hộp thư: khoaktcn@tdmu.edu.vn</p>

                <hr style="border-top: 1px solid rgba(255, 255, 255, 0.48);">
                <div class="row">
                    <div class="col-md-9">
                        <p>© 2022 - Viện Kỹ Thuật - Công Nghệ - Trường Đại Học Thủ Dầu Một</p>
                    </div>
                    <div class="col-md-3">
                        <p style="text-align: right;">
                            <a href="https://www.facebook.com/dhtdm2009" style="color: #ffffff;"><i class="fa-brands fa-facebook" aria-hidden="true" style="font-size: 3rem;"></i></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <h4 style="margin-bottom: 15px;">DANH MỤC</h4><hr />
                <div class="row">
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Trang chủ</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Học vụ</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Khoa học công nghệ</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Tuyển sinh</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Sinh viên</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Đoàn-Hội</a>
                        </p>
                    </div>
                    <div class="col-md-6 margin-bottom-5px">
                        <p>
                            <a style="color: #fff;" href="#">Lịch công tác</a>
                        </p>
                    </div>

                </div>
            </div>
            <div class="col-md-3 margin-bottom-5px">
                <h4 style="margin-bottom: 15px;">THỐNG KÊ</h4><hr />
                <p><i class="fa fa-user" aria-hidden="true"></i> Đang truy cập: 3</p>
                <p><i class="fa fa-sun-o" aria-hidden="true"></i> Hôm nay: 64</p>
                <p><i class="fa fa-calendar" aria-hidden="true"></i> Tháng này: 629</p>
                <p><i class="fa fa-calendar" aria-hidden="true"></i> Tháng trước: </p>
                <p><i class="fa fa-bar-chart" aria-hidden="true"></i> Tổng lượt truy cập: 145.688</p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>








<script>
  
$(document).ready(function () {
	$('#MaP').select2();
	$('#MaTS').select2();
	
	$('#MaP').on('select2:select', function (e) {
        var data = e.params.data;
        if (data.id != "Chọn phòng") {
			get_data(data.id);
		}else {
			$("#MaTS").empty();
			$('#MaTS').val(null).trigger('change');
		}
    });
	
	$("#Gui_BaoLoi").click(function () {
		var MaP = document.getElementById("MaP").value;
		var MaTS = document.getElementById("MaTS").value;
		var MoTa = document.getElementById("MoTa_HuHong").value;
		if (MaP == "Chọn phòng")
		{
			Swal.fire({
			  icon: 'error',
			  title: 'Thông báo!',
			  text: "Vui lòng chọn phòng!!!",
			})
		}else if (MaTS == "Chọn tài sản cần báo lỗi" || MaTS == "")
		{
			Swal.fire({
			  icon: 'error',
			  title: 'Thông báo!',
			  text: "Vui lòng chọn tài sản!!!",
			})
		}else if (MoTa == "")
		{
			Swal.fire({
			  icon: 'error',
			  title: 'Thông báo!',
			  text: "Vui lòng nhập mô tả!!!",
			})
		}
		else {
			post_baoloi(MaP, MaTS,MoTa);
		}
		 
	});
	
	
	
});




function post_baoloi(MaP, MaTS, MoTa){
	$.ajax({
		url: 'add_data_baoloi.php',
		type: "POST",
		data: 	{ 
					MaP: MaP,
					MaTS: MaTS,
					MoTa: MoTa
				},
		async: false,
		cache: false,
		success: function (result) {
			var data = JSON.parse(result);
			if (data['code'] == 1) {
				Swal.fire({
				  icon: 'success',
				  title: 'Thông báo!',
				  text: data['message'],
				})
			} else { 
				Swal.fire({
				  icon: 'error',
				  title: 'Thông báo!',
				  text: data['message'],
				})
			}
		},
		error: function (xhr) {
			data_return = -1;
		}
	});
	
	
}

function get_data(MaP){
	var TenP = "";
	var TongSLTS = 0;
	var str = "";
	$.ajax({
		url: 'load_data_phanbo_byMaPB.php',
		type: "GET",
		data: { MaP: MaP},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			if (result.code == 0){
				return 0;
			}
			$("#MaTS").empty();
			$('#MaTS').val(null).trigger('change');
			$.each(result.data, function (index, value) {
				$('#MaTS').append($('<option>', { value: value.MaTS, text: value.TenTS}));
			});
		},
		error: function (xhr) {
			data_return = -1;
		}
	});
	
	
	

}

</script>