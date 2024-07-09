<?php
$title = "Quản lý người dùng";
include("config.php");
include("check_session.php");
include("header.php");
$ql_nguoidung_show = 'active';

?>


  <body>
   

	<?php 
		include("nav.php");
	?>

      

<!-- Main Container -->
<main id="main-container">
<!-- Hero -->
<div class="bg-body-light">
  <div class="content content-full">
	<div class="d-flex flex-column flex-sm-row justify-content-sm-between align-items-sm-center">
	  <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Quản lý người dùng</h1>
	  <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
		<ol class="breadcrumb">
		  <li class="breadcrumb-item">QUẢN LÝ</li>
		  <li class="breadcrumb-item active" aria-current="page">Quản lý người dùng</li>
		</ol>
	  </nav>
	</div>
  </div>
</div>
<!-- END Hero -->

<!-- Page Content -->
<div class="content">
	<div class="col-lg-12">
	  <!-- Block Tabs Animated Fade -->
	  <div class="block block-rounded">
		<ul class="nav nav-tabs nav-tabs-block" role="tablist">
		  <li class="nav-item">
			<button class="nav-link active" id="ql_nguoidung_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_nguoidung_danhsach" role="tab" aria-controls="ql_nguoidung_danhsach" aria-selected="true">Người dùng</button>
		  </li>
		</ul>
		
		<div class="block-content tab-content overflow-hidden">
		<div class="tab-pane fade show active" id="ql_nguoidung_danhsach" role="tabpanel" aria-labelledby="ql_nguoidung_danhsach" tabindex="0">
		  
			<div class="table-responsive-sm">
			  
				<div class="col-lg-12">
					<div class="card-header">
						<div class="mb-4">
						  <div class="input-group">
							<input type="text" class="form-control" name="txtSearch_ql_nguoidung" placeholder="Tìm kiếm người dùng...">
							<button type="button" class="btn btn-primary" id="search_ql_nguoidung">
							  <i class="fa fa-search me-1"></i>
							</button>
						  </div>
						</div>
					</div>
				</div>

				<table class="table table-bordered table-striped table-vcenter" id = "table_ql_nguoidung">
				  <thead>
					<tr class="bg-body-dark" style="text-align: center;">
					  <th>ID</th>
					  <th>Họ và tên</th>
					  <th>Email</th>
					  <th>Tên đăng nhập</th>
					  <th>Đơn vị</th>
					  <th>Chức danh</th>
					  <th>Phân quyền</th>
					  <th>Hành động</th>
					</tr>
				  </thead>
					<tbody class="load-list">
					</tbody>
				</table>
				
				<div class="row">
					<div class="col-lg-4">
						<button type="button" class="btn btn-info" id="tailai_ql_nguoidung">
							  <i class="si si-reload"></i> Tải lại
						</button>
						<button type="button" class="btn btn-primary" id="themmoi_ql_nguoidung" data-bs-toggle="modal" data-bs-target="#modal_add_ql_nguoidung">
							  <i class="fa fa-circle-plus"></i> Thêm mới người dùng
						</button>
						
					</div>
					<div class="col-lg-8">
						<nav id="phantrang_sachbn" class="text-center" aria-label="Page navigation example">
							<ul class="pagination justify-content-end" id="load-pagination">
							</ul>
						</nav>
					</div>
				</div>
			</div>
			  
		</div>
		</div>
	  </div>
	</div> 
</div>
<!-- END Page Content -->
</main>
<!-- END Main Container -->
	  
	  
<div class="modal" id="modal_add_ql_nguoidung" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	  <div class="modal-content">
		<div class="block block-rounded block-themed block-transparent mb-0">
		  <div class="block-header bg-primary-dark">
			<h3 class="block-title">Thêm mới người dùng</h3>
			<div class="block-options">
			  <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</div>
		  </div>
			<div class="block-content">
			<form id="add_ql_nguoidung_form">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Họ và tên
								</span>
								<input type="text" class="form-control" id="themmoi_ql_nguoidung_hovaten" name="themmoi_ql_nguoidung_hovaten">
						  </div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								  <input type="email" class="form-control" id="themmoi_ql_nguoidung_email" name="themmoi_ql_nguoidung_email" placeholder="Email">
								<span class="input-group-text">
								  <i class="far fa-fw fa-envelope"></i>
								</span>
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								  <input type="number" class="form-control" id="themmoi_ql_nguoidung_sdt" name="themmoi_ql_nguoidung_sdt" placeholder="Số điện thoại">
								<span class="input-group-text">
								  <i class="fa fa-phone"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  <i class="far fa-user"></i>
								</span>
								<input type="text" class="form-control" id="themmoi_ql_nguoidung_tendangnhap" name="themmoi_ql_nguoidung_tendangnhap">
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  <i class="far fa-asterisk"></i>
								</span>
								<input type="text" class="form-control" id="themmoi_ql_nguoidung_matkhau" name="themmoi_ql_nguoidung_matkhau">
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Đơn vị
								</span>
								<select class="form-select" id="themmoi_ql_nguoidung_madonvi" name="themmoi_ql_nguoidung_madonvi">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM donvi");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaDV'].'">'.$row['TenDV'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Chức danh
								</span>
								<select class="form-select" id="themmoi_ql_nguoidung_machucdanh" name="themmoi_ql_nguoidung_machucdanh">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM chucdanh");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaCD'].'">'.$row['TenCD'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Phân quyền
								</span>
								<select class="form-select" id="themmoi_ql_nguoidung_maphanquyen" name="themmoi_ql_nguoidung_maphanquyen">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM phanquyen");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaPQ'].'">'.$row['TenPQ'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<textarea class="form-control" id="themmoi_ql_nguoidung_diachi" name="themmoi_ql_nguoidung_diachi" rows="4" placeholder="Địa chỉ"></textarea>
							</div>
						</div>
					
					</div>
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="themmoi_ql_nguoidung_huy" name="themmoi_ql_nguoidung_huy">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="themmoi_ql_nguoidung_xacnhan" name="themmoi_ql_nguoidung_xacnhan">Xác nhận thêm</button>
		  </div>
		</div>
	  </div>
	</div>
</div>





<div class="modal" id="modal_edit" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	  <div class="modal-content">
		<div class="block block-rounded block-themed block-transparent mb-0">
		  <div class="block-header bg-primary-dark">
			<h3 id = "title_modal_edit" class="block-title"></h3>
			<div class="block-options">
			  <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</div>
		  </div>
			<div class="block-content">
			<form id="edit_data_form">
				<input type="hidden" id="MaND" name="MaND">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Họ và tên
								</span>
								<input type="text" class="form-control" id="edit_hovaten" name="edit_hovaten">
						  </div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								  <input type="email" class="form-control" id="edit_email" name="edit_email" placeholder="Email">
								<span class="input-group-text">
								  <i class="far fa-fw fa-envelope"></i>
								</span>
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								  <input type="number" class="form-control" id="edit_sdt" name="edit_sdt" placeholder="Số điện thoại">
								<span class="input-group-text">
								  <i class="fa fa-phone"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  <i class="far fa-user"></i>
								</span>
								<input type="text" class="form-control" id="edit_tendangnhap" name="edit_tendangnhap">
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  <i class="far fa-asterisk"></i>
								</span>
								<input type="text" class="form-control" id="edit_matkhau" name="edit_matkhau">
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Đơn vị
								</span>
								<select class="form-select" id="edit_MaDV" name="edit_MaDV">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM donvi");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaDV'].'">'.$row['TenDV'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Chức danh
								</span>
								<select class="form-select" id="edit_MaCD" name="edit_MaCD">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM chucdanh");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaCD'].'">'.$row['TenCD'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Phân quyền
								</span>
								<select class="form-select" id="edit_MaPQ" name="edit_MaPQ">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM phanquyen");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaPQ'].'">'.$row['TenPQ'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>

					
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<textarea class="form-control" id="edit_DiaChi" name="edit_DiaChi" rows="4" placeholder="Địa chỉ"></textarea>
							</div>
						</div>
					
					</div>
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="huy_edit_data" name="huy_edit_data">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="edit_data" name="edit_data">Xác nhận thêm</button>
		  </div>
		</div>
	  </div>
	</div>
</div>







     <?php include('footer.php'); ?>
    </div>
    <script src="assets/js/dashmix.app.min.js"></script>
	<script src="assets/js/lib/jquery.min.js"></script>


  </body>
</html>


<script>
  
$(document).ready(function () {
	
	
	load_data_nguoidung(null, 1);

	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_nguoidung")[0].value;
		if (txtSearch != "") {
		   load_data_nguoidung(txtSearch, page)
		}
		else {
		   load_data_nguoidung(null, page);
		}
	});
	
	
	
	$("#search_ql_nguoidung").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_nguoidung")[0].value;
		if (txtSearch != "") {
			load_data_nguoidung(txtSearch, 1)
		}
		else {
			load_data_nguoidung(null, 1);
		}
		 
	});
	
	
	$("#tailai_ql_nguoidung").click(function () {
		document.getElementsByName("txtSearch_ql_nguoidung")[0].value = "";
		load_data_nguoidung(null, 1);
	});

	
	$(document).on('click','#themmoi_ql_nguoidung_xacnhan',function(e) {
		var data = $("#add_ql_nguoidung_form").serialize();
		$.ajax({
			url: 'add_data_nguoidung.php',
			type: "POST",
			data: data,
			async: false,
			cache: false,
			success: function (dataResult) {
				var data = JSON.parse(dataResult);
				if (data['code'] == 1) {
					Swal.fire({
					  icon: 'success',
					  title: 'Thông báo!',
					  text: "Thêm mới người dùng thành công",
					}).then(function() {
						$("#modal_add_ql_nguoidung").modal("hide");
						$('#add_ql_nguoidung_form')[0].reset();
						load_data_nguoidung(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					})
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
	});


	$(document).on('click','#themmoi_ql_nguoidung_huy',function(e) {
		$("#modal_add_ql_nguoidung").modal("hide");
		$('#add_ql_nguoidung_form')[0].reset();
	});


	$(document).on('click','#edit_data',function(e) {
		var data = $("#edit_data_form").serialize();
		$.ajax({
			url: 'edit_data_nguoidung.php',
			type: "POST",
			data: data,
			async: false,
			cache: false,
			success: function (dataResult) {
				var data = JSON.parse(dataResult);
				if (data['code'] == 1) {
					Swal.fire({
					  icon: 'success',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_data_nguoidung(null, 1);
						$("#modal_edit").modal("hide");
						$('#edit_data_form')[0].reset();
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					})
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
	});
	
	
	$(document).on('click','#huy_edit_data',function(e) {
		$("#modal_edit").modal("hide");
		$('#edit_data_form')[0].reset();
	});
	
	
	
});


function view_data_nguoidung(MaND){
	var result = get_data_nguoidung_by_MaND(MaND);
	if (result != -1)
	{
		$('#title_modal_edit').text("Xem người dùng");
		$('#edit_hovaten').val(result.data.HoVaTen);
		$('#edit_hovaten').prop('readonly', true);
		$('#edit_email').val(result.data.Email);
		$('#edit_email').prop('readonly', true);
		$('#edit_sdt').val(result.data.SoDienThoai);
		$('#edit_sdt').prop('readonly', true);
		$('#edit_tendangnhap').val(result.data.TenDangNhap);
		$('#edit_tendangnhap').prop('readonly', true);
		$('#edit_matkhau').val(result.data.MatKhau);
		$('#edit_matkhau').prop('readonly', true);
		$('#edit_MaPQ option[value=' + result.data.MaPQ+']').prop('selected', true);
		$("#edit_MaPQ").prop("disabled", true);
		$('#edit_MaDV option[value=' + result.data.MaDV+']').prop('selected', true);
		$("#edit_MaDV").prop("disabled", true);
		$('#edit_MaCD option[value=' + result.data.MaCD+']').prop('selected', true);
		$("#edit_MaCD").prop("disabled", true);
		$('#edit_DiaChi').val(result.data.DiaChi);
		$('#edit_DiaChi').prop('readonly', true);
		$("#modal_edit").modal("show");
		$("#edit_data").hide();
		$("#huy_edit_data").hide();
	}
}


function edit_data_nguoidung(MaND){
	var result = get_data_nguoidung_by_MaND(MaND);
	if (result != -1)
	{
		$('#edit_data_form')[0].reset();
		$('#edit_hovaten').prop('readonly', false);
		$('#edit_email').prop('readonly', false);
		$('#edit_sdt').prop('readonly', false);
		$('#edit_tendangnhap').prop('readonly', false);
		$('#edit_DiaChi').prop('readonly', false);
		$('#edit_tendangnhap').prop('readonly', false);
		$("#edit_MaPQ").prop("disabled", false);
		$("#edit_MaDV").prop("disabled", false);
		$("#edit_MaCD").prop("disabled", false);
		$("#edit_data").show();
		$("#huy_edit_data").show();
		$("#edit_data").text("Xác nhận sửa");
		$("#huy_edit_data").text("Hủy sửa");
		$('#title_modal_edit').text("Chỉnh sửa người dùng");
		$('#MaND').val(result.data.MaND);
		$('#edit_hovaten').val(result.data.HoVaTen);
		$('#edit_email').val(result.data.Email);
		$('#edit_sdt').val(result.data.SoDienThoai);
		$('#edit_tendangnhap').val(result.data.TenDangNhap);
		$('#edit_matkhau').val(result.data.MatKhau);
		$('#edit_MaPQ option[value=' + result.data.MaPQ+']').prop('selected', true);
		$('#edit_MaDV option[value=' + result.data.MaDV+']').prop('selected', true);
		$('#edit_MaCD option[value=' + result.data.MaCD+']').prop('selected', true);
		$('#edit_DiaChi').val(result.data.DiaChi);
		$("#modal_edit").modal("show");
	}
}




function del_data_nguoidung(MaND){
	Swal.fire({
		title: 'Bạn có chắc chắn muốn xóa người dùng này?',
		showDenyButton: true,
		confirmButtonText: 'Có',
		denyButtonText: 'Không',
		customClass: {
		actions: 'my-actions',
		cancelButton: 'order-1 right-gap',
		confirmButton: 'order-2',
		denyButton: 'order-3',
		}
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
			url: 'delete_data_nguoidung.php',
			type: "GET",
			data: { MaND: MaND},
			async: false,
			cache: false,
			success: function (dataResult) {
				var data = JSON.parse(dataResult);
				if (data['code'] == 1) {
					Swal.fire({
					  icon: 'success',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_data_nguoidung(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_data_nguoidung(null, 1);
					});
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
		} else if (result.isDenied) {
			Swal.fire('Hủy xóa người dùng thành công!!', '', 'success')
		}
	})
}


function get_data_nguoidung_by_MaND(MaND){
	var data_return = ""
	$.ajax({
		url: 'load_data_nguoidung_by_MaND.php',
		type: "GET",
		data: { MaND: MaND},
		async: false,
		cache: false,
		success: function (result) {
			data_return = JSON.parse(result);
		},
		error: function (xhr) {
			data_return = -1;
		}
	});
	return data_return;
}



function load_data_nguoidung(txtSearch, page) {
	$.ajax({
		url: 'load_data_nguoidung.php',
		type: "GET",
		data: { txtSearch: txtSearch, page: page},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='7'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				var pagination_string = "";
				$(".load-list").html(str);
				$("#load-pagination").html(pagination_string);
				return 0;
			}
			$.each(result.data, function (index, value) {
				
				str += "<tr style='text-align: center;'>";
				str += "<td>" + value.MaND + "</td>";
				str += "<td style='font-size: 15px;'><span class='badge bg-warning text-light'>" + value.HoVaTen + "</span></td>";
				str += "<td class='fw-semibold'>" + value.Email + "</td>";
				str += "<td style='font-size: 15px;'><span class='badge bg-secondary text-light'>" + value.TenDangNhap + "</span></td>";
				str += "<td class='fw-semibold'>" + value.TenDV + "</td>";
				str += "<td class='fw-semibold'>" + value.TenCD + "</td>";
				str += "<td style='font-size: 15px;'><span class='badge bg-danger text-light'>" + value.TenPQ + "</span></td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" onclick = "view_data_nguoidung('+value.MaND+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem"><i class="fa fa-eye text-warning"></i></button>'
				str += '<button type="button" onclick = "edit_data_nguoidung('+value.MaND+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Sửa"><i class="fa fa-pencil-alt text-primary"></i></button>'
				str += '<button type="button" onclick = "del_data_nguoidung('+value.MaND+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xóa"><i class="fa fa-times text-danger"></i></button></div></td>';	  
				str += "</tr>";

				
				
				var pagination_string = "";
				var pageCurrent = result.pageCurrent;
				var numSize = result.numSize;
				if (pageCurrent > 1) {
					var pagePrevious = pageCurrent - 1;
					pagination_string += '<li class="page-item"><a href="" class="page-link" data-page=' + pagePrevious + '><i class="fa fa-angle-left"></i></a></li>';
				}
				for (i = 1; i <= numSize; i++) {
					if (i == pageCurrent) {
						pagination_string += '<li class="page-item active"><a href="" class="page-link" data-page=' + i + '>' + pageCurrent + '</a></li>';
					} else {
						pagination_string += '<li class="page-item"><a href="" class="page-link" data-page=' + i + '>' + i + '</a></li>';
					}
				}
				//create button next
				if (pageCurrent > 0 && pageCurrent < numSize) {
					var pageNext = pageCurrent + 1;
					pagination_string += '<li class="page-item"><a href="" class="page-link"  data-page=' + pageNext + '><i class="fa fa-angle-right"></i></a></li>';
				}
				//load pagination
				$("#load-pagination").html(pagination_string);
			});
			$(".load-list").html(str);
		}
	});
}



 
</script>