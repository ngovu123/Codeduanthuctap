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
            <a class="navbar-brand" href="index.php">Trang Chủ</a>
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
        <div class="container" style="border-bottom: 2px solid #ff6a00; padding-bottom: 5px; ">
            <div class="row">
                <div class="col-lg-4 col-sm-6 wow fadeInUp"data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: fadeInUp;">
                    <div class="service-item text-center pt-3">
                        <div class="p-4">
                            <i class="fa fa-4x fa-computer text-primary mb-4"></i>
                            <h5 class="mb-3">Tổng số lượng tài sản</h5>
                            <p><?php echo $TongSo_TaiSan; ?></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6  wow fadeInUp"data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: fadeInUp;">
                    <div class="service-item text-center pt-3">
                        <div class="p-4">
                            <i class="fa-4x fas fa-chalkboard-teacher fa-solid fa-chair text-primary mb-4"></i>
                            <h5 class="mb-3">Tổng số phòng học</h5>
                            <p><?php echo $TongSo_PhongHoc; ?></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp"data-wow-delay="0.5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInUp;">
                    <div class="service-item text-center pt-3 ">
                        <div class="p-4">
                            <i class="fa fa-4x fa-user-md text-primary mb-4"></i>
                            <h5 class="mb-3">Tổng số người dùng</h5>
                            <p><?php echo $TongSo_NguoiDung; ?></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!--Thông tin-->

    <div class="container-xxl py-5" style="background-color: #d2d2d23d">
        <div class="container">
            <div class="text-center">
                <h6 class="section-title text-center text-primary px-3">Trường Đại học Thủ Dầu Một</h6>
                <h1 class="mb-5">Tổng quan</h1>
            </div>
            <div class="row g-3">
                <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: zoomIn; padding-bottom: 10px;">
                    <div class="team-item bg-light">
                        <div class="overflow-hidden" >
                            <img class="img-fluid img-thumbnail" src="./img/tn2-8506.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.2s" style="visibility: visible; animation-delay: 0.2s; animation-name: zoomIn; padding-bottom: 10px;">
                    <div class="team-item bg-light">
                        <div class="overflow-hidden">
                            <img class="img-fluid img-thumbnail" src="./img/PhongMay.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="team-item bg-light wow zoomIn" data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: zoomIn;padding-bottom: 10px;">
                        <div class="overflow-hidden">
                            <img class="img-fluid img-thumbnail" src="./img/PhongMay2.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-xxl py-5" style="background-color: #01314c;">
        <div class="container">
            <div class="text-center">
                <h1 class="mb-5 text-white">Thông tin mới nhất</h1>
            </div>
                <ul class="list-group" >
                    <li class="list-group-item  justify-content-between align-items-center" style="padding-left: 15px; border-left: none; border-right: none;">
                        <a href="#">Các thiết bị mới năm 2023</a>
                        <span class="badge badge-pill badge-danger" style="float: unset;">New</span>
                        <br>
                    </li>
                    <li class="list-group-item  justify-content-between align-items-center" style="padding-left: 15px; border-left: none; border-right: none;">
                        <a href="#">Tổng tài sản năm 2022</a>
                        <span class="badge badge-pill badge-danger" style="float: unset;"></span>
                        <br>
                    </li>
                    <li class="list-group-item  justify-content-between align-items-center" style="padding-left: 15px;  border-left: none; border-right: none;">
                        <a href="#">Tổng tài sản năm 2022</a>
                        <span class="badge badge-pill badge-danger" style="float: unset;"></span>
                        <br>
                    </li>
                </ul>
                <div class="text-center pt-5" style="padding-bottom: 3.7rem">
                    <a href="#" class="btn btn-success btn-lg bg-cam">
                        Xem thêm thông tin 
                    </a>
                </div>
        </div>
    </div>
<!--Báo lỗi-->
  <?php 
  if(isset($_SESSION['TenDangNhap'])){
	     ?>

     <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center">
                <h1 class="mb-5 text-danger ">Báo hỏng thiết bị</h1>
            </div>
                <div class="row">
                    <div class="col-lg-4 col-md-3 wow zoomIn" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: zoomIn; padding-bottom: 10px;">
                        <div class="team-item bg-light">
                            <div class="overflow-hidden" >
                                <img class="img-fluid img-thumbnail" src="./img/hinh_E1.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-3">
                        <div class="form-floating pb-4">
                            <label class="pr-3">Chọn phòng: </label>
							<select onchange="" id="MaP" name="MaP" class="form-control select2" data-toggle="select2">
                            <option selected>Chọn phòng</option>
							<?php 
								$result = mysqli_query($conn, "	SELECT * FROM phong");
								while ($row = $result->fetch_assoc()) {
									echo ' <option value = "'.$row['MaP'].'">'.$row['TenP'].'</option>';
								}
							?>
                            </select>
                        </div>
                        <div class="form-floating pb-3">
                            <label class="pr-3">Chọn tài sản: </label>
							<select onchange="" id="MaTS" name="MaTS" class="form-control select2" data-toggle="select2">
                            <option selected>Chọn tài sản cần báo lỗi</option>
                            </select>
                        </div>
                        <div class="form-floating pb-5">
                            <label>Mô tả</label>
                            <textarea class="form-control" rows="6" cols="60" placeholder="Mô tả chi tiết thiết bị hư những gì?" id="MoTa_HuHong"></textarea>
                          </div>
                          <button type="button" id = "Gui_BaoLoi" class="btn btn-success float-right">Gửi</button>
                    </div>
                </div>
            </div>
        </div>
  
  <?php } ?>
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