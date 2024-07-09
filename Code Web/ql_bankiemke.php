<?php
$title = "Quản lý ban kiểm kê";
include("config.php");
include("check_session.php");
include("header.php");
$ql_bankiemke_show = 'active';

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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Quản lý ban kiểm kê</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">QUẢN LÝ</li>
                  <li class="breadcrumb-item active" aria-current="page">Quản lý ban kiểm kê</li>
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
                    <button class="nav-link active" id="ql_bankiemke_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_bankiemke_danhsach" role="tab" aria-controls="ql_bankiemke_danhsach" aria-selected="true">Danh sách</button>
                  </li>
                 
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="ql_bankiemke_danhsach" role="tabpanel" aria-labelledby="ql_bankiemke_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
						<div class="col-lg-12">
							<div class="card-header">
								<div class="mb-4">
								  <div class="input-group">
									<input type="text" class="form-control" name="txtSearch_ql_bankiemke" placeholder="Tìm kiếm...">
									<button type="button" class="btn btn-primary" id="search_ql_bankiemke">
									  <i class="fa fa-search me-1"></i>
									</button>
								  </div>
								</div>
							</div>
						</div>

						<table class="table table-bordered table-striped table-vcenter" id = "table_ql_bankiemke">
						  <thead>
							<tr style="text-align: center;">
							  <th>Mã BKK</th>
							  <th>Họ và tên</th>
							  <th>Đơn vị</th>
							  <th>Chức danh</th>
							  <th>Ngày cập nhật gần nhất</th>
							  <th>Hành động</th>
							</tr>
						  </thead>
							<tbody class="load-list">
							</tbody>
						</table>
						
						<div class="row">
							<div class="col-lg-4">
								<button type="button" class="btn btn-info" id="tailai_ql_bankiemke">
									  <i class="si si-reload"></i> Tải lại
								</button>
								<button type="button" class="btn btn-secondary" id="themmoi_ql_bankiemke" data-bs-toggle="modal" data-bs-target="#modal_add_ql_bankiemke">
									  <i class="fa fa-circle-plus"></i> Thêm mới
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
	  
	  
<div class="modal" id="modal_add_ql_bankiemke" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
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
			<form id="add_ql_bankiemke_form">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
						  <div class="input-group">
								<span class="input-group-text">
									Họ và tên
								</span>
								<input type="text" class="form-control" id="hovaten" name="hovaten">
						  </div>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
									Chức danh
								</span>
								<select class="form-select" id="MaCD" name="MaCD">
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
								  Đơn vị
								</span>
								<select class="form-select" id="MaDV" name="MaDV">
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
					
			
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="themmoi_ql_bankiemke_huy" name="themmoi_ql_bankiemke_huy">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="themmoi_ql_bankiemke_xacnhan" name="themmoi_ql_bankiemke_xacnhan">Xác nhận thêm</button>
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
					<input type="hidden" id="Mabkk" name="Mabkk">
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
	load_data_bankiemke(null, 1);

	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_bankiemke")[0].value;
		if (txtSearch != "") {
		   load_data_bankiemke(txtSearch, page)
		}
		else {
		   load_data_bankiemke(null, page);
		}
	});
	
	
	$("#search_ql_bankiemke").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_bankiemke")[0].value;
		if (txtSearch != "") {
			load_data_bankiemke(txtSearch, 1)
		}
		else {
			load_data_bankiemke(null, 1);
		}
		 
	});
	
	
	$("#tailai_ql_bankiemke").click(function () {
		document.getElementsByName("txtSearch_ql_bankiemke")[0].value = "";
		load_data_bankiemke(null, 1);
	});
	
	
	
	
	$(document).on('click','#themmoi_ql_bankiemke_xacnhan',function(e) {
		var data = $("#add_ql_bankiemke_form").serialize();
		$.ajax({
			url: 'add_data_bankiemke.php',
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
					  text: "Thêm mới người dùng thành công",
					}).then(function() {
						$("#modal_add_ql_bankiemke").modal("hide");
						$('#add_ql_bankiemke_form')[0].reset();
						load_data_bankiemke(null, 1);
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


	$(document).on('click','#themmoi_ql_bankiemke_huy',function(e) {
		$("#modal_add_ql_bankiemke").modal("hide");
		$('#add_ql_bankiemke_form')[0].reset();
	});

	
	$(document).on('click','#edit_data',function(e) {
		var data = $("#edit_data_form").serialize();
		$.ajax({
			url: 'edit_data_bankiemke.php',
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
						load_data_bankiemke(null, 1);
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







function view_data(Mabkk){
	var result = get_data_bankiemke(Mabkk);
	if (result != -1)
	{
		$('#title_modal_edit').text("Xem ban kiểm kê");
		$('#edit_hovaten').val(result.data.HoVaTen);
		$('#edit_hovaten').prop('readonly', true);
		$('#edit_MaCD option[value=' + result.data.MaCD+']').prop('selected', true);
		$("#edit_MaCD").prop("disabled", true);
		$('#edit_MaDV option[value=' + result.data.MaDV+']').prop('selected', true);
		$("#edit_MaDV").prop("disabled", true);
		$("#modal_edit").modal("show");
		$("#edit_data").hide();
		$("#huy_edit_data").hide();
	}
}



function edit_data(Mabkk){
	var result = get_data_bankiemke(Mabkk);
	if (result != -1)
	{
		$('#edit_data_form')[0].reset();
		$("#edit_MaCD").prop("disabled", false);
		$("#edit_MaDV").prop("disabled", false); 
		$('#edit_hovaten').prop('readonly', false);
		$("#edit_data").show();
		$("#huy_edit_data").show();
		$("#edit_data").text("Xác nhận sửa");
		$("#huy_edit_data").text("Hủy sửa");
		$('#title_modal_edit').text("Chỉnh sửa người dùng");
		$('#Mabkk').val(result.data.Mabkk);
		$('#edit_hovaten').val(result.data.HoVaTen);
		$('#edit_MaCD option[value=' + result.data.MaCD+']').prop('selected', true);
		$('#edit_MaDV option[value=' + result.data.MaDV+']').prop('selected', true);
		$("#modal_edit").modal("show");
		
	}
}


function del_data(Mabkk){
	Swal.fire({
		title: 'Bạn có chắc chắn muốn xóa ban kiểm kê này?',
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
			url: 'delete_data_bankiemke.php',
			type: "GET",
			data: { Mabkk: Mabkk},
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
						load_data_bankiemke(null, 1);
					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Thông báo!',
					  text: data['message'],
					}).then(function() {
						load_data_bankiemke(null, 1);
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





function get_data_bankiemke(Mabkk){
	var data_return = ""
	$.ajax({
		url: 'load_data_bankiemke_byMabkk.php',
		type: "GET",
		data: { Mabkk: Mabkk},
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



function load_data_bankiemke(txtSearch, page) {
	$.ajax({
		url: 'load_data_bankiemke.php',
		type: "GET",
		data: { txtSearch: txtSearch, page: page},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='6'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				var pagination_string = "";
				$(".load-list").html(str);
				$("#load-pagination").html(pagination_string);
				return 0;
			}
			$.each(result.data, function (index, value) {
				
				str += "<tr style='text-align: center;'>";
				str += "<td>" + value.Mabkk + "</td>";
				str += "<td class='fw-semibold'><span class='badge bg-warning text-light'>" + value.HoVaTen + "</span></td>";
				str += "<td class='fw-semibold'>" + value.TenDV + "</td>";
				str += "<td class='fw-semibold'>" + value.TenCD + "</td>";
				str += "<td class='fw-semibold'>" + value.NgayCapNhat + "</td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" onclick = "view_data('+value.Mabkk+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem"><i class="fa fa-eye"></i></button>'
				str += '<button type="button" onclick = "edit_data('+value.Mabkk+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Sửa"><i class="fa fa-pencil-alt"></i></button>'
				str += '<button type="button" onclick = "del_data('+value.Mabkk+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xóa"><i class="fa fa-times"></i></button></div></td>';	  
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