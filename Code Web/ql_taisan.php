<?php
$title = "Quản lý tài sản";
include("config.php");
include("check_session.php");
include("header.php");
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = 'active';
$ql_nhomtaisan_show = "";
$ql_loaitaisan_show = "";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Quản lý tài sản</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">QUẢN LÝ</li>
                  <li class="breadcrumb-item active" aria-current="page">Quản lý tài sản</li>
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
                    <button class="nav-link active" id="ql_taisan_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_taisan_danhsach" role="tab" aria-controls="ql_taisan_danhsach" aria-selected="true">Danh sách</button>
                  </li>
                 
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="ql_taisan_danhsach" role="tabpanel" aria-labelledby="ql_taisan_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
						<div class="col-lg-12">
							<div class="card-header">
								<div class="mb-4">
								  <div class="input-group">
									<input type="text" class="form-control" name="txtSearch_ql_taisan" placeholder="Tìm kiếm...">
									<button type="button" class="btn btn-primary" id="search_ql_taisan">
									  <i class="fa fa-search me-1"></i>
									</button>
								  </div>
								</div>
							</div>
						</div>

						<table class="table table-bordered table-striped table-vcenter" id = "table_ql_taisan">
						  <thead>
							<tr style="text-align: center;">
							  <th>ID</th>
							  <th>Tên tài sản</th>
							  <th>Nhóm tài sản</th>
							  <th>Thuộc loại tài sản</th>
							  <th>Số lượng nhập vào</th>
							  <th>Số lượng hiện còn</th>
							  <th>Ghi chú</th>
							  <th>Hành động</th>
							</tr>
						  </thead>
							<tbody class="load-list">
							</tbody>
						</table>
						
						<div class="row">
							<div class="col-lg-4">
								<button type="button" class="btn btn-info" id="tailai_ql_taisan">
									  <i class="si si-reload"></i> Tải lại
								</button>
								<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modal_add_ql_taisan">
									  <i class="fa fa-circle-plus"></i> Thêm mới tài sản
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
	
<div class="modal" id="modal_add_ql_taisan" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	  <div class="modal-content">
		<div class="block block-rounded block-themed block-transparent mb-0">
		  <div class="block-header bg-primary-dark">
			<h3 class="block-title">Thêm mới tài sản</h3>
			<div class="block-options">
			  <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</div>
		  </div>
			<div class="block-content">
			<form id="add_ql_taisan_form">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Tên tài sản
								</span>
								<input type="text" class="form-control" id="themmoi_ql_taisan_tents" name="themmoi_ql_taisan_tents">
						  </div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  Nhóm TS
								</span>
								<select class="form-select" id="themmoi_ql_taisan_mants" name="themmoi_ql_taisan_mants">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM nhomtaisan");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaNTS'].'">'.$row['TenNTS'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  Loại TS
								</span>
								<select class="form-select" id="themmoi_ql_taisan_malts" name="themmoi_ql_taisan_malts">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM loaitaisan");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaLTS'].'">'.$row['TenLTS'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  $
								</span>
								<input type="number" class="form-control" id="themmoi_ql_taisan_giatri" name="themmoi_ql_taisan_giatri" placeholder="Giá trị">
							</div>
						</div>
						<div class="col-6">
						  <div class="input-group">
								<span class="input-group-text">
									Số lượng
								</span>
								<input type="number" class="form-control" id="themmoi_ql_taisan_soluong" name="themmoi_ql_taisan_soluong">
						  </div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Hãng sản xuất
								</span>
								<input type="text" class="form-control" id="themmoi_ql_taisan_hangsanxuat" name="themmoi_ql_taisan_hangsanxuat">
						  </div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Nước sản xuất
								</span>
								<input type="text" class="form-control" id="themmoi_ql_taisan_nuocsanxuat" name="themmoi_ql_taisan_nuocsanxuat">
						  </div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Năm sản xuất
								</span>
								<input type="number" class="form-control" id="themmoi_ql_taisan_namsanxuat" name="themmoi_ql_taisan_namsanxuat">
						  </div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<textarea class="form-control" id="themmoi_ql_taisan_ghichu" name="themmoi_ql_taisan_ghichu" rows="4" placeholder="Mô tả tài sản"></textarea>
							</div>
						</div>
					
					</div>
					
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="themmoi_ql_taisan_huy" name="themmoi_ql_taisan_huy">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="themmoi_ql_taisan_xacnhan" name="themmoi_ql_taisan_xacnhan">Xác nhận thêm</button>
		  </div>
		</div>
	  </div>
	</div>
</div>



<div class="modal" id="modal_edit" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
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
				<input type="hidden" id="MaTS" name="MaTS">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Tên tài sản
								</span>
								<input type="text" class="form-control" id="edit_TenTS" name="edit_TenTS">
						  </div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  Nhóm TS
								</span>
								<select class="form-select" id="edit_MaNTS" name="edit_MaNTS">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM nhomtaisan");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaNTS'].'">'.$row['TenNTS'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-text">
								  Loại TS
								</span>
								<select class="form-select" id="edit_MaLTS" name="edit_MaLTS">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM loaitaisan");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaLTS'].'">'.$row['TenLTS'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-4">
							<div class="input-group">
								<span class="input-group-text">
								  $ Giá trị
								</span>
								<input type="number" class="form-control" id="edit_GiaTri" name="edit_GiaTri" placeholder="Giá trị">
							</div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Số lượng nhập vào
								</span>
								<input type="number" class="form-control" id="edit_SoLuongNhapVao" name="edit_SoLuongNhapVao">
						  </div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Số lượng hiện còn
								</span>
								<input type="number" class="form-control" id="edit_SoLuongHienCon" name="edit_SoLuongHienCon">
						  </div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Hãng sản xuất
								</span>
								<input type="text" class="form-control" id="edit_HangSanXuat" name="edit_HangSanXuat">
						  </div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Nước sản xuất
								</span>
								<input type="text" class="form-control" id="edit_NuocSanXuat" name="edit_NuocSanXuat">
						  </div>
						</div>
						<div class="col-4">
						  <div class="input-group">
								<span class="input-group-text">
									Năm sản xuất
								</span>
								<input type="number" class="form-control" id="edit_NamSanXuat" name="edit_NamSanXuat">
						  </div>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<textarea class="form-control" id="edit_MoTa" name="edit_MoTa" rows="4" placeholder="Mô tả tài sản"></textarea>
							</div>
						</div>
					
					</div>
					
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary"  id="huy_edit_data" name="huy_edit_data">Hủy thêm</button>
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
	
	load_phantrang(null, 1);


	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_taisan")[0].value;
		if (txtSearch != "") {
		   load_phantrang(txtSearch, page)
		}
		else {
		   load_phantrang(null, page);
		}
	});
	
	
	
	$("#search_ql_taisan").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_taisan")[0].value;
		if (txtSearch != "") {
			load_phantrang(txtSearch, 1)
		}
		else {
			load_phantrang(null, 1);
		}
		 
	});
	
	
	
	$("#tailai_ql_taisan").click(function () {
		document.getElementsByName("txtSearch_ql_taisan")[0].value = "";
		load_phantrang(null, 1);
	});
	
	
	$(document).on('click','#themmoi_ql_taisan_xacnhan',function(e) {
		var data = $("#add_ql_taisan_form").serialize();
		$.ajax({
			url: 'add_data_taisan.php',
			type: "POST",
			data: data,
			async: false,
			cache: false,
			success: function (dataResult) {
				var data = JSON.parse(dataResult);
				if (data['code'] == 1) {
					Swal.fire({
					  icon: 'success',
					  title: 'Thêm mới thành công !',
					  text: "Thêm mới tài sản thành công",
					}).then(function() {
						$("#modal_add_ql_taisan").modal("hide");
						$('#add_ql_taisan_form')[0].reset();
						load_phantrang(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thêm thất bại...',
					  text: data['message'],
					})
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
	});


	$(document).on('click','#themmoi_ql_taisan_huy',function(e) {
		$("#modal_add_ql_taisan").modal("hide");
		$('#add_ql_taisan_form')[0].reset();
	});


	$(document).on('click','#edit_data',function(e) {
		var data = $("#edit_data_form").serialize();
		$.ajax({
			url: 'edit_data_taisan.php',
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
						load_phantrang(null, 1);
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

function del_data(MaTS){
	Swal.fire({
		title: 'Bạn có chắc chắn muốn xóa tài sản này?',
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
			url: 'delete_data_taisan.php',
			type: "GET",
			data: { MaTS: MaTS},
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
						load_phantrang(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_phantrang(null, 1);
					});
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
		} else if (result.isDenied) {
			Swal.fire('Hủy xóa tài sản thành công!!', '', 'success')
		}
	})
}

function edit_data(MaTS){
	var result = get_data(MaTS);
	if (result != -1)
	{
		$('#edit_data_form')[0].reset();
		$("#edit_MaNTS").prop("disabled", false);
		$("#edit_MaLTS").prop("disabled", false);
		$("#edit_data").show();
		$("#huy_edit_data").show();
		$("#edit_data").text("Xác nhận sửa");
		$("#huy_edit_data").text("Hủy sửa");
		$('#title_modal_edit').text("Chỉnh sửa tài sản");
		$('#MaTS').val(result.data.MaTS);
		$('#edit_TenTS').val(result.data.TenTS);
		$('#edit_TenTS').prop('readonly', false);
		$('#edit_GiaTri').val(result.data.GiaTri);
		$('#edit_SoLuongNhapVao').val(result.data.SLNhapVao);
		$('#edit_SoLuongNhapVao').prop('readonly', true);
		$('#edit_SoLuongHienCon').val(result.data.SLHienCon);
		$('#edit_SoLuongHienCon').prop('readonly', true);
		$('#edit_HangSanXuat').val(result.data.HangSanXuat);
		$('#edit_HangSanXuat').prop('readonly', false);
		$('#edit_NuocSanXuat').val(result.data.NuocSanXuat);
		$('#edit_NuocSanXuat').prop('readonly', false);
		$('#edit_NamSanXuat').val(result.data.NamSanXuat);
		$('#edit_NamSanXuat').prop('readonly', false);
		$('#edit_MoTa').val(result.data.GhiChu);
		$('#edit_MoTa').prop('readonly', false);
		$('#edit_MaNTS option[value=' + result.data.MaNTS+']').prop('selected', true);
		$('#edit_MaLTS option[value=' + result.data.MaLTS+']').prop('selected', true);
		$("#modal_edit").modal("show");
	}
}


function view_data(MaTS){
	var result = get_data(MaTS);
	if (result != -1)
	{
		$('#title_modal_edit').text("Xem tài sản");
		$('#edit_TenTS').val(result.data.TenTS);
		$('#edit_TenTS').prop('readonly', true);
		$('#edit_GiaTri').val(result.data.GiaTri);
		$('#edit_GiaTri').prop('readonly', true);
		$('#edit_SoLuongNhapVao').val(result.data.SLNhapVao);
		$('#edit_SoLuongNhapVao').prop('readonly', true);
		$('#edit_SoLuongHienCon').val(result.data.SLHienCon);
		$('#edit_SoLuongHienCon').prop('readonly', true);
		$('#edit_HangSanXuat').val(result.data.HangSanXuat);
		$('#edit_HangSanXuat').prop('readonly', true);
		$('#edit_NuocSanXuat').val(result.data.NuocSanXuat);
		$('#edit_NuocSanXuat').prop('readonly', true);
		$('#edit_NamSanXuat').val(result.data.NamSanXuat);
		$('#edit_NamSanXuat').prop('readonly', true);
		$('#edit_MoTa').val(result.data.GhiChu);
		$('#edit_MoTa').prop('readonly', true);
		$('#edit_MaNTS option[value=' + result.data.MaNTS+']').prop('selected', true);
		$("#edit_MaNTS").prop("disabled", true);
		$('#edit_MaLTS option[value=' + result.data.MaLTS+']').prop('selected', true);
		$("#edit_MaLTS").prop("disabled", true);
		$("#modal_edit").modal("show");
		$("#edit_data").hide();
		$("#huy_edit_data").hide();
	}
}



function get_data(MaTS){
	var data_return = ""
	$.ajax({
		url: 'load_data_taisan_by_MaTS.php',
		type: "GET",
		data: { MaTS: MaTS},
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



function load_phantrang(txtSearch, page) {
	$.ajax({
		url: 'load_data_taisan.php',
		type: "GET",
		data: { txtSearch: txtSearch, page: page},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='8'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				var pagination_string = "";
				$(".load-list").html(str);
				$("#load-pagination").html(pagination_string);
				return 0;
			}
			$.each(result.data, function (index, value) {
				str += "<tr style='text-align: center;'>";
				str += "<td><span class='fw-semibold'>" + value.MaTS  + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-success text-light'>" + value.TenTS + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-danger text-light'>" + value.TenNTS + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-dark text-light'>" + value.TenLTS + "</span></td>";
				str += "<td style='font-size: 18px;'><span class='badge bg-light text-dark'>" + value.SLNhapVao + "</span></td>";
				str += "<td style='font-size: 18px;'><span class='badge bg-light text-dark'>" + value.SLHienCon + "</span></td>";
				str += "<td style='font-size: 14px;'>" + value.GhiChu + "</td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" onclick = "view_data('+value.MaTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem"><i class="fa fa-eye text-warning"></i></button>'
				str += '<button type="button" onclick = "edit_data('+value.MaTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Sửa"><i class="fa fa-pencil-alt text-primary"></i></button>'
				str += '<button type="button" onclick = "del_data('+value.MaTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xóa"><i class="fa fa-times text-danger"></i></button></div></td>';	  
				str += "</tr>";

				
				
				//create pagination
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
			//load str to class="load-list"
			$(".load-list").html(str);

		}
	});
}



 
</script>