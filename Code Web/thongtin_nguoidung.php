<?php
$title = "Tổng quan";
include("config.php");
if(!isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();     
}
include("header.php");





?>


<?php 
	include("nav.php");
?>

      <!-- Main Container -->
      <main id="main-container">
        <!-- Page Content -->
        <div class="content content-full content-boxed">
          <!-- Hero -->
          <div class="rounded border overflow-hidden push">
            <div class="bg-image pt-9" style="background-image: url('./img/img-kham-pha-tdmu.jpg');"></div>
            <div class="px-4 py-3 bg-body-extra-light d-flex flex-column flex-md-row align-items-center">
              <a class="d-block img-link mt-n5" href="be_pages_generic_profile_v2.html">
                <img class="img-avatar img-avatar128 img-avatar-thumb" src="assets/media/avatars/avatar13.jpg" alt="">
              </a>
              <div class="ms-3 flex-grow-1 text-center text-md-start my-3 my-md-0">
                <h1 class="fs-4 fw-bold mb-1"><?php echo $data['HoVaTen']; ?></h1>
                <h2 class="fs-sm fw-medium text-muted mb-0">
                  Chỉnh sửa tài sản
                </h2>
              </div>
              <div class="space-x-1">
                <a href="tongquan.php" class="btn btn-sm btn-alt-secondary space-x-1">
                  <i class="fa fa-arrow-left opacity-50"></i>
                  <span>Trở về trang Tổng quan</span>
                </a>
              </div>
            </div>
          </div>
          <!-- END Hero -->

          <!-- Edit Account -->
          <div class="block block-bordered block-rounded">
            <ul class="nav nav-tabs nav-tabs-alt" role="tablist">
              <li class="nav-item">
                <button class="nav-link space-x-1 active" id="account-profile-tab" data-bs-toggle="tab" data-bs-target="#account-profile" role="tab" aria-controls="account-profile" aria-selected="true">
                  <i class="fa fa-user-circle d-sm-none"></i>
                  <span class="d-none d-sm-inline">Thông tin cơ bản</span>
                </button>
              </li>
              <li class="nav-item">
                <button class="nav-link space-x-1" id="account-password-tab" data-bs-toggle="tab" data-bs-target="#account-password" role="tab" aria-controls="account-password" aria-selected="false">
                  <i class="fa fa-asterisk d-sm-none"></i>
                  <span class="d-none d-sm-inline">Đổi mật khẩu</span>
                </button>
              </li>
            </ul>
            <div class="block-content tab-content">
              <div class="tab-pane active" id="account-profile" role="tabpanel" aria-labelledby="account-profile-tab" tabindex="0">
                <div class="row push p-sm-2 p-lg-4">
                  <div class="offset-xl-1 col-xl-4 order-xl-1">
                    <p class="bg-body-light p-4 rounded-3 text-muted fs-sm">
						Ở đây sẽ hiển thị các thông tin cơ bản của bạn. Muốn thay đổi các thông tin khác vui lòng liên hệ quản trị viên!
                    </p>
                  </div>
                  <div class="col-xl-6 order-xl-0">
                    <form method="POST" id="capnhat_thongtin_form">
                      <div class="mb-4">
                        <label class="form-label">Họ và tên: </label>
                        <input type="text" class="form-control" id="hovaten" name="hovaten" placeholder="Vui lòng nhập họ và tên.." value="<?php echo $data['HoVaTen']; ?>">
                      </div>
                      <div class="mb-4">
                        <label class="form-label">Số điện thoại: </label>
                        <input type="text" class="form-control" id="sdt" name="sdt" placeholder="Vui lòng nhập số điện thoại.." value="<?php echo $data['SoDienThoai']; ?>">
                      </div>
                      <div class="mb-4">
                        <label class="form-label">Email</label>
                        <input type="text" class="form-control" id="email" name="email" placeholder="Vui lòng nhập email.." value="<?php echo $data['Email']; ?>">
                      </div>
                      <div class="mb-4">
                        <label class="form-label">Địa chỉ</label>
                        <textarea type="text"  rows="6"  class="form-control" id="diachi" name="diachi" placeholder="Vui lòng nhập địa chỉ..." value=""><?php echo $data['DiaChi']; ?></textarea>
                      </div>

                      <button type="button" id = "capnhatthongtin" class="btn btn-alt-primary">
                        <i class="fa fa-check-circle opacity-50 me-1"></i> Cập nhật thông tin
                      </button>
                    </form>
                  </div>
                </div>
              </div>
              <div class="tab-pane" id="account-password" role="tabpanel" aria-labelledby="account-password-tab" tabindex="0">
                <div class="row push p-sm-2 p-lg-4">
                  <div class="offset-xl-1 col-xl-4 order-xl-1">
                    <p class="bg-body-light p-4 rounded-3 text-muted fs-sm">
						Thay đổi mật khẩu đăng nhập của bạn là một cách dễ dàng để giữ an toàn cho tài khoản của bạn.
                    </p>
                  </div>
                  <div class="col-xl-6 order-xl-0">
                    <form method="POST" id = "capnhat_matkhau_form">
                      <div class="mb-4">
                        <label class="form-label" for="dm-profile-edit-password">Mật khẩu hiện tại</label>
                        <input type="password" class="form-control" id="matkhau_hientai" name="matkhau_hientai">
                      </div>
                      <div class="row mb-4">
                        <div class="col-12">
                          <label class="form-label" for="dm-profile-edit-password-new">Mật khẩu mới</label>
                          <input type="password" class="form-control" id="matkhau_moi" name="matkhau_moi">
                        </div>
                      </div>
                      <div class="row mb-4">
                        <div class="col-12">
                          <label class="form-label" for="dm-profile-edit-password-new-confirm">Xác nhận mật khẩu mới</label>
                          <input type="password" class="form-control" id="xacnhan_matkhau_moi" name="xacnhan_matkhau_moi">
                        </div>
                      </div>
                      <button type="button" id = "capnhatmatkhau" class="btn btn-alt-primary">
                        <i class="fa fa-check-circle opacity-50 me-1"></i> Cập nhật mật khẩu
                      </button>
                    </form>
                  </div>
                </div>
              </div>
             
              
            </div>
          </div>
          <!-- END Edit Account -->
        </div>
        <!-- END Page Content -->
      </main>
      <!-- END Main Container -->
	  
	  

     <?php include('footer.php'); ?>
    </div>
    <script src="assets/js/dashmix.app.min.js"></script>
	<script src="assets/js/lib/jquery.min.js"></script>
	
	
	
	
<script>
	$(document).ready(function () {
		
		
		$(document).on('click','#capnhatthongtin',function(e) {
			var data = $("#capnhat_thongtin_form").serialize();
			$.ajax({
				url: 'edit_data_thongtin_nguoidung.php',
				type: "POST",
				data: data,
				async: false,
				cache: false,
				success: function (dataResult) {
					var data = JSON.parse(dataResult);
					if (data['code'] == 1) {
						Swal.fire({
						  icon: 'success',
						  title: 'Cập nhật thành công !',
						  text: data['message'],
						}).then(function() {
							$('#capnhat_thongtin_form')[0].reset();
							location.reload();
						});
						
					} else { 
						Swal.fire({
						  icon: 'error',
						  title: 'Cập nhật thất bại!!!',
						  text: data['message'],
						})
					}
				},
				error: function (xhr) {
					alert("Error_Load_Data");
				}
			})
		});
		
		
		$(document).on('click','#capnhatmatkhau',function(e) {
			var data = $("#capnhat_matkhau_form").serialize();
			$.ajax({
				url: 'edit_data_thongtin_nguoidung_doimatkhau.php',
				type: "POST",
				data: data,
				async: false,
				cache: false,
				success: function (dataResult) {
					var data = JSON.parse(dataResult);
					if (data['code'] == 1) {
						Swal.fire({
						  icon: 'success',
						  title: 'Cập nhật thành công !',
						  text: data['message'],
						}).then(function() {
							$('#capnhat_matkhau_form')[0].reset();
							location.reload();
						});
						
					} else { 
						Swal.fire({
						  icon: 'error',
						  title: 'Cập nhật thất bại!!!',
						  text: data['message'],
						})
					}
				},
				error: function (xhr) {
					alert("Error_Load_Data");
				}
			})
		});
		
		
	})


</script>