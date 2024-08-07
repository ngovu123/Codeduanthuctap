<?php 
include('config.php'); 
if(isset($_SESSION['TenDangNhap'])){
	header("Location: index.php");
	exit();     
}
?>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <title>ĐĂNG NHẬP</title>

    <meta name="description" content="KIỂM KÊ TÀI SẢN - TDMU">
    <meta name="author" content="pixelcave">
    <meta name="robots" content="noindex, nofollow">

    <!-- Open Graph Meta -->
    <meta property="og:title" content="KIỂM KÊ TÀI SẢN - TDMU">
    <meta property="og:site_name" content="KKTS">
    <meta property="og:description" content="KIỂM KÊ TÀI SẢN - TDMU">
    <meta property="og:type" content="website">
    <meta property="og:url" content="">
    <meta property="og:image" content="">

    <!-- Icons -->
    <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
    <link rel="shortcut icon" href="https://tdmu.edu.vn/hinh/Icon.png">
    <link rel="icon" type="image/png" sizes="192x192" href="https://tdmu.edu.vn/hinh/Icon.png">
    <link rel="apple-touch-icon" sizes="180x180" href="https://tdmu.edu.vn/hinh/Icon.png">
    <!-- END Icons -->

    <!-- Stylesheets -->
    <!-- Dashmix framework -->
    <link rel="stylesheet" id="css-main" href="assets/css/dashmix.min.css">
	<!-- Page JS Plugins CSS -->
    <link rel="stylesheet" href="assets/js/plugins/sweetalert2/sweetalert2.min.css">

    <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
    <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/xwork.min.css"> -->
    <!-- END Stylesheets -->
  </head>

  <body>
    <!-- Page Container -->
    <!--
      Available classes for #page-container:

      GENERIC

        'remember-theme'                            Remembers active color theme and dark mode between pages using localStorage when set through
                                                    - Theme helper buttons [data-toggle="theme"],
                                                    - Layout helper buttons [data-toggle="layout" data-action="dark_mode_[on/off/toggle]"]
                                                    - ..and/or Dashmix.layout('dark_mode_[on/off/toggle]')

      SIDEBAR & SIDE OVERLAY

        'sidebar-r'                                 Right Sidebar and left Side Overlay (default is left Sidebar and right Side Overlay)
        'sidebar-mini'                              Mini hoverable Sidebar (screen width > 991px)
        'sidebar-o'                                 Visible Sidebar by default (screen width > 991px)
        'sidebar-o-xs'                              Visible Sidebar by default (screen width < 992px)
        'sidebar-dark'                              Dark themed sidebar

        'side-overlay-hover'                        Hoverable Side Overlay (screen width > 991px)
        'side-overlay-o'                            Visible Side Overlay by default

        'enable-page-overlay'                       Enables a visible clickable Page Overlay (closes Side Overlay on click) when Side Overlay opens

        'side-scroll'                               Enables custom scrolling on Sidebar and Side Overlay instead of native scrolling (screen width > 991px)

      HEADER

        ''                                          Static Header if no class is added
        'page-header-fixed'                         Fixed Header


      FOOTER

        ''                                          Static Footer if no class is added
        'page-footer-fixed'                         Fixed Footer (please have in mind that the footer has a specific height when is fixed)

      HEADER STYLE

        ''                                          Classic Header style if no class is added
        'page-header-dark'                          Dark themed Header
        'page-header-glass'                         Light themed Header with transparency by default
                                                    (absolute position, perfect for light images underneath - solid light background on scroll if the Header is also set as fixed)
        'page-header-glass page-header-dark'         Dark themed Header with transparency by default
                                                    (absolute position, perfect for dark images underneath - solid dark background on scroll if the Header is also set as fixed)

      MAIN CONTENT LAYOUT

        ''                                          Full width Main Content if no class is added
        'main-content-boxed'                        Full width Main Content with a specific maximum width (screen width > 1200px)
        'main-content-narrow'                       Full width Main Content with a percentage width (screen width > 1200px)

      DARK MODE

        'sidebar-dark page-header-dark dark-mode'   Enable dark mode (light sidebar/header is not supported with dark mode)
    -->
    <div id="page-container">

      <!-- Main Container -->
      <main id="main-container">
        <!-- Page Content -->
        <div class="bg-image" style="background-image: url('assets/media/photos/photo22@2x.jpg');">
          <div class="row g-0 bg-primary-op">
            <!-- Main Section -->
            <div class="hero-static col-md-6 d-flex align-items-center bg-body-extra-light">
              <div class="p-3 w-100">
                <!-- Header -->
                <div class="mb-3 text-center">
                  <a class="link-fx fw-bold fs-1" href="index.html">
                    <span class="text-primary">TDMU</span>
                  </a>
                  <p class="text-uppercase fw-bold fs-sm text-muted">Sign In</p>
                </div>
                <!-- END Header -->

                <!-- Sign In Form -->
                <!-- jQuery Validation (.js-validation-signin class is initialized in js/pages/op_auth_signin.min.js which was auto compiled from _js/pages/op_auth_signin.js) -->
                <!-- For more info and examples you can check out https://github.com/jzaefferer/jquery-validation -->
                <div class="row g-0 justify-content-center">
                  <div class="col-sm-8 col-xl-6">
                    <form class="js-validation-signin" action="" method="POST" id="login">
                      <div class="py-3">
                        <div class="mb-4">
                          <input type="text" class="form-control form-control-lg form-control-alt" id="login-username" name="login-username" placeholder="Username" value="admin">
                        </div>
                        <div class="mb-4">
                          <input type="password" class="form-control form-control-lg form-control-alt" id="login-password" name="login-password" placeholder="Password" value="admin">
                        </div>
                      </div>
                      <div class="mb-4">
                        <button type="submit" class="btn w-100 btn-lg btn-hero btn-primary">
                          <i class="fa fa-fw fa-sign-in-alt opacity-50 me-1"></i> Đăng nhập
                        </button>
                        <p class="mt-3 mb-0 d-lg-flex justify-content-lg-between">
                          <a class="btn btn-sm btn-alt-secondary d-block d-lg-inline-block mb-1" href="#">
                            <i class="fa fa-exclamation-triangle opacity-50 me-1"></i> Quên mật khẩu
                          </a>
						  
                          <a class="btn btn-sm btn-alt-secondary d-block d-lg-inline-block mb-1" href="<?php echo $google_client->createAuthUrl(); ?>">
                            <i class="fab fa-google-plus-g opacity-50 me-1"></i> Đăng nhập bằng Google
                          </a>
                        </p>
                      </div>
                    </form>
                  </div>
                </div>
                <!-- END Sign In Form -->
              </div>
            </div>
            <!-- END Main Section -->

            <!-- Meta Info Section -->
            <div class="hero-static col-md-6 d-none d-md-flex align-items-md-center justify-content-md-center text-md-center">
              <div class="p-3">
                <p class="display-4 fw-bold text-white mb-3">
                  Welcome to the future
                </p>
                <p class="fs-lg fw-semibold text-white-75 mb-0">
                  Copyright &copy; <span data-toggle="year-copy"></span>
                </p>
              </div>
            </div>
            <!-- END Meta Info Section -->
          </div>
        </div>
        <!-- END Page Content -->
      </main>
      <!-- END Main Container -->
    </div>
    <!-- END Page Container -->

    <!--
      Dashmix JS

      Core libraries and functionality
      webpack is putting everything together at assets/_js/main/app.js
    -->
    <script src="assets/js/dashmix.app.min.js"></script>

     <!-- Page JS Plugins -->
    <script src="assets/js/plugins/sweetalert2/sweetalert2.min.js"></script>

    <!-- jQuery (required for jQuery Validation plugin) -->
    <script src="assets/js/lib/jquery.min.js"></script>

    <!-- Page JS Plugins -->
    <script src="assets/js/plugins/jquery-validation/jquery.validate.min.js"></script>

    <!-- Page JS Code -->
    <script src="assets/js/pages/op_auth_signin.min.js"></script>
  </body>
</html>


<script>

$(document).ready(function(){
	$("#login").submit(function(){
		  var _taikhoan = $("input[name='login-username']").val();
		  var _matkhau = $("input[name='login-password']").val();
		  if(_taikhoan=="" || _taikhoan.length==0){
			   Swal.fire({
				  icon: 'error',
				  title: 'Thất bại...',
				  text: "Bạn chưa nhập email!",
				})
		  }
		  else if(_matkhau=="" || _matkhau.length==0){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại...',
				  text: "Bạn chưa nhập password!",
				})
		  }else{
			  $.ajax({
					  type:"POST",
					  url:"DangNhap_Ajax.php",
					  data:{
						  username:	_taikhoan,
						  password:	_matkhau
					  },
					  cache:false,
					  success:function(result){
						var data = JSON.parse(result);
						if(data['code']==1)
						{
							Swal.fire({
							  icon: 'success',
							  title: 'Thành công !!!',
							  text: "Bạn đăng nhập thành công!",
							}).then(function() {
								window.location.href = data['success'];
							});
							

						}else{
						  Swal.fire({
							  icon: 'error',
							  title: 'Thất bại...',
							  text: data['message'],
							})
						}
					  },
					  error: function (request, status, error) {
						  alert(status);
					  }
			  });

		  }

		  return false;
	  });
});

</script>