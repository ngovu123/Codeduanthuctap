<?php
$title = "Quản lý nhóm tài sản";
include("config.php");
if(!isset($_SESSION['TenDangNhap'])){
	header("Location: DangNhap.php");
	exit();     
}else if ($data['MaPQ'] != 1)
{
	header("Location: index.php");
	exit();
}
include("header.php");
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = '';
$ql_nhomtaisan_show = "active";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Quản lý nhóm tài sản</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">QUẢN LÝ</li>
                  <li class="breadcrumb-item active" aria-current="page">Quản lý nhóm tài sản</li>
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
                    <button class="nav-link active" id="ql_nhomtaisan_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_nhomtaisan_danhsach" role="tab" aria-controls="ql_nhomtaisan_danhsach" aria-selected="true">Danh sách</button>
                  </li>
                 
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="ql_nhomtaisan_danhsach" role="tabpanel" aria-labelledby="ql_nhomtaisan_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
						<div class="col-lg-12">
							<div class="card-header">
								<div class="mb-4">
								  <div class="input-group">
									<input type="text" class="form-control" name="txtSearch_ql_nhomtaisan" placeholder="Tìm kiếm...">
									<button type="button" class="btn btn-primary" id="search_ql_nhomtaisan">
									  <i class="fa fa-search me-1"></i>
									</button>
								  </div>
								</div>
							</div>
						</div>

						<table class="table table-bordered table-striped table-vcenter" id = "table_ql_nhomtaisan">
						  <thead>
							<tr style="text-align: center;">
							  <th>ID</th>
							  <th>Tên nhóm tài sản</th>
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
								<button type="button" class="btn btn-success" id="themmoi_ql_nguoidung" data-bs-toggle="modal" data-bs-target="#modal_add_ql_nhomtaisan">
									  <i class="fa fa-circle-plus"></i> Thêm mới nhóm tài sản
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
<div class="modal" id="modal_add_ql_nhomtaisan" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	  <div class="modal-content">
		<div class="block block-rounded block-themed block-transparent mb-0">
		  <div class="block-header bg-primary-dark">
			<h3 class="block-title">Thêm mới nhóm tài sản</h3>
			<div class="block-options">
			  <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</div>
		  </div>
			<div class="block-content">
			<form id="add_ql_nhomtaisan_form">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Tên nhóm tài sản
								</span>
								<input type="text" class="form-control" id="themmoi_ql_nhomtaisan_tennts" name="themmoi_ql_nhomtaisan_tennts">
						  </div>
						</div>
					</div>
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="themmoi_ql_nhomtaisan_huy" name="themmoi_ql_nhomtaisan_huy">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="themmoi_ql_nhomtaisan_xacnhan" name="themmoi_ql_nhomtaisan_xacnhan">Xác nhận thêm</button>
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
				<div class="row">
					<input type="hidden" id="MaNTS" name="MaNTS">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Tên nhóm tài sản
								</span>
								<input type="text" class="form-control" id="edit_TenNTS" name="edit_TenNTS">
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
	
	
	load_data_nhomtaisan(null, 1);


	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_nhomtaisan")[0].value;
		if (txtSearch != "") {
		   load_data_nhomtaisan(txtSearch, page)
		}
		else {
		   load_data_nhomtaisan(null, page);
		}
	});
	
	
	$("#search_ql_nhomtaisan").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_nhomtaisan")[0].value;
		if (txtSearch != "") {
			load_data_nhomtaisan(txtSearch, 1)
		}
		else {
			load_data_nhomtaisan(null, 1);
		}
		 
	});
	
	
	$("#tailai_ql_nhomtaisan").click(function () {
		document.getElementsByName("txtSearch_ql_nhomtaisan")[0].value = "";
		load_data_nhomtaisan(null, 1);
	});

	
	$(document).on('click','#themmoi_ql_nhomtaisan_xacnhan',function(e) {
		var data = $("#add_ql_nhomtaisan_form").serialize();
		$.ajax({
			url: 'add_data_nhomtaisan.php',
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
					  text: "Thêm mới nhóm tài sản thành công",
					}).then(function() {
						$("#modal_add_ql_nhomtaisan").modal("hide");
						$('#add_ql_nhomtaisan_form')[0].reset();
						load_data_nhomtaisan(null, 1);
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



	$(document).on('click','#themmoi_ql_nhomtaisan_huy',function(e) {
		$("#modal_add_ql_nhomtaisan").modal("hide");
		$('#add_ql_nhomtaisan_form')[0].reset();
	});


	$(document).on('click','#edit_data',function(e) {
		var data = $("#edit_data_form").serialize();
		$.ajax({
			url: 'edit_data_nhomtaisan.php',
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
						$("#modal_edit").modal("hide");
						$('#edit_data_form')[0].reset();
						load_data_nhomtaisan(null, 1);
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



function view_data(MaNTS){
	var result = get_data(MaNTS);
	if (result != -1)
	{
		$('#title_modal_edit').text("Xem nhóm tài sản");
		$('#edit_TenNTS').val(result.data.TenNTS);
		$('#edit_TenNTS').prop('readonly', true);
		$("#edit_data").hide();
		$("#huy_edit_data").hide();
		$("#modal_edit").modal("show");
	}
}


function edit_data(MaLTS){
	var result = get_data(MaLTS);
	if (result != -1)
	{
		$('#title_modal_edit').text("Chỉnh sửa nhóm tài sản");
		$('#MaNTS').val(result.data.MaNTS);
		$('#edit_TenNTS').val(result.data.TenNTS);
		$('#edit_TenNTS').prop('readonly', false);
		$("#edit_data").show();
		$("#huy_edit_data").show();
		$("#edit_data").text("Xác nhận sửa");
		$("#huy_edit_data").text("Hủy sửa");
		$("#modal_edit").modal("show");
		
	}
}


function del_data(MaNTS){
	Swal.fire({
		title: 'Bạn có chắc chắn muốn xóa nhóm tài sản này?',
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
			url: 'delete_data_nhomtaisan.php',
			type: "GET",
			data: { MaNTS: MaNTS},
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
						load_data_nhomtaisan(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_data_nhomtaisan(null, 1);
					});
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
		} else if (result.isDenied) {
			Swal.fire('Hủy xóa nhóm tài sản thành công!!', '', 'success')
		}
	})
}



function get_data(MaNTS){
	var data_return = ""
	$.ajax({
		url: 'load_data_nhomtaisan_byMaNTS.php',
		type: "GET",
		data: { MaNTS: MaNTS},
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



function load_data_nhomtaisan(txtSearch, page) {
	$.ajax({
		url: 'load_data_nhomtaisan.php',
		type: "GET",
		data: { txtSearch: txtSearch, page: page},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='3'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				var pagination_string = "";
				$(".load-list").html(str);
				$("#load-pagination").html(pagination_string);
				return 0;
			}
			$.each(result.data, function (index, value) {
				
				str += "<tr style='text-align: center;'>";
				str += "<td><span class='fw-semibold'>" + value.MaNTS + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-danger text-light'>" + value.TenNTS + "</span></td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" onclick = "view_data('+value.MaNTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem"><i class="fa fa-eye text-warning"></i></button>'
				str += '<button type="button" onclick = "edit_data('+value.MaNTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Sửa"><i class="fa fa-pencil-alt text-primary"></i></button>'
				str += '<button type="button" onclick = "del_data('+value.MaNTS+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xóa"><i class="fa fa-times text-danger"></i></button></div></td>';	  
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