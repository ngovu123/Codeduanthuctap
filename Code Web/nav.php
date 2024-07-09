 <div id="page-container" class="sidebar-o sidebar-dark enable-page-overlay side-scroll page-header-fixed main-content-narrow">
      <nav id="sidebar" aria-label="Main Navigation">
        <!-- Side Header -->
	<div class="smini-hidden">
		<div class="content-header justify-content-lg-center">
			<!-- Logo -->
			<a class="fw-semibold text-white tracking-wide" href="index.php">
			  TD<span class="opacity-75">MU</span>
			  <span class="fw-normal">KKTS</span>
			</a>
			<!-- END Logo -->

			<!-- Options -->
			<div class="d-lg-none">
				<a class="text-white ms-2" data-toggle="layout" data-action="sidebar_close" href="javascript:void(0)">
					<i class="fa fa-times-circle"></i>
				</a>
			</div>
		</div>
	</div>
	
<!-- Sidebar Scrolling -->
<div class="js-sidebar-scroll">

		
		<div class="content-side content-side-full text-center bg-black-10">
			<div class="smini-hide">
				<img class="img-avatar" src="assets/media/avatars/avatar15.jpg" alt="">
				<div class="mt-2 mb-1 fw-semibold"><?php echo $data['HoVaTen']; ?></div>
				
				
				<span class="badge rounded-pill bg-danger"><?php echo $data['TenPQ']; ?></span>
				<a class="text-white-50 me-1" href="thongtin_nguoidung.php">
					<i class="fa fa-fw fa-user-md"></i>
				</a>
				<a class="text-white-50 me-1" href="javascript:void(0)">
					<i class="fa fa-fw fa-cog"></i>
				</a>
				<a class="text-white-50" href="DangXuat.php">
					<i class="fa fa-fw fa-sign-out-alt"></i>
				</a>
            </div>
		</div>
		  
  <!-- Side Navigation -->
	<div class="content-side">
		<ul class="nav-main">
		
		<?php 
	
		if ($data['MaPQ'] == 1)
		{
	?>
			<li class="nav-main-item">
				<a class="nav-main-link <?php echo $tongquan_show; ?>" href="tongquan.php">
					<i class="nav-main-link-icon fa fa-location-arrow"></i>
					<span class="nav-main-link-name">Tổng quan</span>
					<span class="nav-main-link-badge badge rounded-pill bg-primary"><?php echo $count_baoloi; ?></span>
				</a>
			</li>
	 
	
	
	
		<li class="nav-main-heading">Quản lý</li>

		<?php 
			if (isset($ql_nguoidung_show) or isset($ql_donvi_show) or isset($ql_chucdanh_show))
			{
				if ($ql_nguoidung_show == "active" or $ql_donvi_show == "active" or $ql_chucdanh_show == "active")
				{
					echo '<li class="nav-main-item open">';
				}else {
					echo '<li class="nav-main-item">';
				}
			}else {
				echo '<li class="nav-main-item">';
			}
			
		?>
			<a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="true" href="#">
			  <i class="nav-main-link-icon fa fa-user"></i>
			  <span class="nav-main-link-name">Quản lý người dùng</span>
			</a>
			<ul class="nav-main-submenu">
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_nguoidung_show; ?>" href="ql_nguoidung.php">
				  <span class="nav-main-link-name">Danh sách</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_donvi_show; ?>" href="ql_donvi.php">
				  <span class="nav-main-link-name">Đơn vị</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_chucdanh_show; ?>" href="ql_chucdanh.php">
				  <span class="nav-main-link-name">Chức danh</span>
				</a>
			  </li>
			</ul>
		</li>
		
		
		
		<?php 
			if (isset($ql_phong_show) or isset($ql_kvphong_show) or isset($ql_lphong_show))
			{
				if ($ql_phong_show == "active" or $ql_kvphong_show == "active" or $ql_lphong_show == "active")
				{
					echo '<li class="nav-main-item open">';
				}else {
					echo '<li class="nav-main-item">';
				}
			}else {
				echo '<li class="nav-main-item">';
			}
			
		?>
			<a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="true" href="#">
			  <i class="nav-main-link-icon fa fa-border-all"></i>
			  <span class="nav-main-link-name">Quản lý phòng</span>
			</a>
			<ul class="nav-main-submenu">
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_phong_show; ?>" href="ql_phong.php">
				  <span class="nav-main-link-name">Danh sách</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_kvphong_show; ?>" href="ql_kvloaiphong.php">
				  <span class="nav-main-link-name">Khu vực phòng</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_lphong_show; ?>" href="ql_loaiphong.php">
				  <span class="nav-main-link-name">Loại phòng</span>
				</a>
			  </li>
			</ul>
		</li>


		<?php 
			if (isset($ql_taisan_show) or isset($ql_nhomtaisan_show) or isset($ql_loaitaisan_show))
			{
				if ($ql_taisan_show == "active" or $ql_nhomtaisan_show == "active" or $ql_loaitaisan_show == "active")
				{
					echo '<li class="nav-main-item open">';
				}else {
					echo '<li class="nav-main-item">';
				}
			}else {
				echo '<li class="nav-main-item">';
			}
			
		?>
			<a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="true" href="#">
			  <i class="nav-main-link-icon fa fa-computer"></i>
			  <span class="nav-main-link-name">Quản lý tài sản</span>
			</a>
			<ul class="nav-main-submenu">
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_taisan_show; ?>" href="ql_taisan.php">
				  <span class="nav-main-link-name">Danh sách</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_nhomtaisan_show; ?>" href="ql_nhomtaisan.php">
				  <span class="nav-main-link-name">Nhóm tài sản</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_loaitaisan_show; ?>" href="ql_loaitaisan.php">
				  <span class="nav-main-link-name">Loại tài sản</span>
				</a>
			  </li>
			</ul>
		</li>
	 
	 <?php }?>
	 
	 
		<li class="nav-main-heading">Nghiệp vụ</li>
		<?php 
			if (isset($ql_phanbo_show) or isset($nv_phanbo_show))
			{
				if ($ql_phanbo_show == "active" or $nv_phanbo_show == "active")
				{
					echo '<li class="nav-main-item open">';
				}else {
					echo '<li class="nav-main-item">';
				}
			}else {
				echo '<li class="nav-main-item">';
			}
			
		?>
			<a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="true" href="#">
			  <i class="nav-main-link-icon fa fa-briefcase"></i>
			  <span class="nav-main-link-name">Phân bố tài sản</span>
			</a>
			<ul class="nav-main-submenu">
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $ql_phanbo_show; ?>" href="ql_phanbo.php">
				  <span class="nav-main-link-name">Danh sách</span>
				</a>
			  </li>
			  <li class="nav-main-item">
				<a class="nav-main-link <?php echo $nv_phanbo_show; ?>" href="nv_phanbo.php">
				  <span class="nav-main-link-name">Phân bố</span>
				</a>
			  </li>
			</ul>
			
		</li>
		<li class="nav-main-item">
			<a class="nav-main-link <?php echo $nv_kiemke; ?>" href="nv_kiemke.php">
			<i class="nav-main-link-icon fa fa-laptop-file"></i>
				<span class="nav-main-link-name">Kiểm kê</span>
			</a>
		</li>
		

		
		
		<?php 
	
			if ($data['MaPQ'] == 1)
			{
		?>
		<li class="nav-main-item">
			<a class="nav-main-link <?php echo $nkhd_show; ?>" href="ql_nkhd.php">
			<i class="nav-main-link-icon fa fa-clipboard-list"></i>
				<span class="nav-main-link-name">Nhật ký hoạt động</span>
			</a>
		</li>
		 <?php }?>
		
	</ul>
  </div>
  <!-- END Side Navigation -->
</div>
<!-- END Sidebar Scrolling -->
</nav>
<!-- END Sidebar -->
	  
	  
	  
<!-- Header -->
<header id="page-header">
<!-- Header Content -->
<div class="content-header">
  <!-- Left Section -->
  <div class="space-x-1">
	<!-- Toggle Sidebar -->
	<!-- Layout API, functionality initialized in Template._uiApiLayout()-->
	<button type="button" class="btn btn-alt-secondary" data-toggle="layout" data-action="sidebar_toggle">
	  <i class="fa fa-fw fa-bars"></i>
	</button>
	<!-- END Toggle Sidebar -->

	<!-- Open Search Section -->
	<!-- Layout API, functionality initialized in Template._uiApiLayout() -->
	<button type="button" class="btn btn-alt-secondary" data-toggle="layout" data-action="header_search_on">
	  <i class="fa fa-fw opacity-50 fa-search"></i> <span class="ms-1 d-none d-sm-inline-block">Tìm kiếm</span>
	</button>
	<!-- END Open Search Section -->
  </div>
  <!-- END Left Section -->

  <!-- Right Section -->
  <div class="space-x-1">
	<!-- User Dropdown -->
	<div class="dropdown d-inline-block">
	<button type="button" class="btn btn-alt-secondary" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="far fa-fw fa-user-circle"></i>
		<?php echo $data['TenDangNhap']; ?>
		<i class="fa fa-fw fa-angle-down d-none opacity-50 d-sm-inline-block"></i>
  </button>
	  <div class="dropdown-menu dropdown-menu-end p-0" aria-labelledby="page-header-user-dropdown">
		<div class="bg-primary-dark rounded-top fw-semibold text-white text-center p-3">
		  Tùy chọn người dùng
		</div>
		<div class="p-2">
		  <a class="dropdown-item" href="thongtin_nguoidung.php">
			<i class="far fa-fw fa-user me-1"></i> Thông tin người dùng
		  </a>
		 
		  <div role="separator" class="dropdown-divider"></div>

		  <!-- Toggle Side Overlay -->
		  <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
		  <a class="dropdown-item" href="#" data-toggle="layout">
			<i class="far fa-fw fa-building me-1"></i> Cài đặt
		  </a>
		  <!-- END Side Overlay -->

		  <div role="separator" class="dropdown-divider"></div>
		  <a class="dropdown-item" href="DangXuat.php">
			<i class="far fa-fw fa-arrow-alt-circle-left me-1"></i> Đăng xuất
		  </a>
		</div>
	  </div>
	</div>
	<!-- END User Dropdown -->


	<!-- Toggle Side Overlay -->
	<!-- Layout API, functionality initialized in Template._uiApiLayout() -->
  
  
	<!-- END Toggle Side Overlay -->
  </div>
  <!-- END Right Section -->
</div>
<!-- END Header Content -->

<!-- Header Search -->
<div id="page-header-search" class="overlay-header bg-header-dark">
  <div class="bg-white-10">
	<div class="content-header">
	  <form class="w-100" action="#" method="POST">
		<div class="input-group">
		  <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
		  <button type="button" class="btn btn-alt-primary" data-toggle="layout" data-action="header_search_off">
			<i class="fa fa-fw fa-times-circle"></i>
		  </button>
		  <input type="text" class="form-control border-0" placeholder="Search or hit ESC.." id="page-header-search-input" name="page-header-search-input">
		</div>
	  </form>
	</div>
  </div>
</div>
<!-- END Header Search -->

<!-- Header Loader -->
<!-- Please check out the Loaders page under Components category to see examples of showing/hiding it -->
<div id="page-header-loader" class="overlay-header bg-header-dark">
  <div class="bg-white-10">
	<div class="content-header">
	  <div class="w-100 text-center">
		<i class="fa fa-fw fa-sun fa-spin text-white"></i>
	  </div>
	</div>
  </div>
</div>
<!-- END Header Loader -->
</header>
<!-- END Header -->