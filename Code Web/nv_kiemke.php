<?php
$title = "Kiểm kê TS-CCDC";
include("config.php");
include("check_session.php");
include("header.php");
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = '';
$ql_nhomtaisan_show = "";
$ql_loaitaisan_show = "";


$ql_phanbo_show = "";
$nv_phanbo_show = "";

$nv_kiemke = "active";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Kiểm kê</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">Nghiệp Vụ</li>
                  <li class="breadcrumb-item active" aria-current="page">Kiểm kê</li>
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
                    <button class="nav-link active" id="nv_kiemke_danhsach-tab" data-bs-toggle="tab" data-bs-target="#nv_kiemke_danhsach" role="tab" aria-controls="nv_kiemke_danhsach" aria-selected="true">Danh sách</button>
                  </li>
			
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="nv_kiemke_danhsach" role="tabpanel" aria-labelledby="nv_kiemke_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
					  
						<div class="col-lg-12">
						<div class="mb-4">
							<div class="row items-push">
								<div class="col-3">
								  <label class="form-label" for="example-select">Tìm theo phòng:</label>
								  <select class="form-select" id="phong_search" name="phong_search">
									<option value="">Tất cả</option>
									<?php 
											$result = mysqli_query($conn, "	SELECT * FROM phong");
											while ($row = $result->fetch_assoc()) {
												echo '<option value="'.$row['MaP'].'">'.$row['TenP'].'</option>';
											}
										?>
								  </select>
								</div>
								<div class="col-4">
									<label class="form-label" for="example-select">Tìm theo thời gian:</label>
									  <div class="input-daterange input-group js-datepicker-enabled" data-date-format="mm/dd/yyyy" data-week-start="1" data-autoclose="true" data-today-highlight="true">
										<input type="date" class="form-control" id="time_search_1" name="time_search_1" placeholder="From" data-week-start="1" data-autoclose="true" data-today-highlight="true">
										<span class="input-group-text fw-semibold">
										  <i class="fa fa-fw fa-arrow-right"></i>
										</span>
										<input type="date" class="form-control" id="time_search_2" name="time_search_2" placeholder="To" data-week-start="1" data-autoclose="true" data-today-highlight="true">
									  </div>
								</div>
								<div class="col-3">
								  <label class="form-label" for="example-select">Tìm trạng thái:</label>
								  <select class="form-select" id="trangthai_search" name="trangthai_search">
									<option value="">Tất cả</option>
									<option value="0">Chưa hoàn thành</option>
									<option value="1">Đã hoàn thành</option>
								  </select>
								</div>
								<div class="col-2">
									<label class="form-label" for="example-select"> <br></label>
									<button type="submit" id = "search_nv_kiemke" name = "search_nv_kiemke" class="btn btn-primary w-100">Tìm kiếm</button>
								</div>
							</div>
						</div>
						
							
							
						</div>

						<table class="table table-bordered table-striped table-vcenter" id = "table_nv_kiemke">
						  <thead>
							<tr style="text-align: center;">
							  <th>Mã phiếu</th>
							  <th>Tên phòng</th>
							  <th>Trạng thái</th>
							  <th>Ngày cập nhật gần nhất</th>
							  <th>Ghi chú</th>
							  <th>Hành động</th>
							</tr>
						  </thead>
							<tbody class="load-list">
							</tbody>
						</table>
						
						<div class="row">
							<div class="col-lg-4">
								<button type="button" class="btn btn-info" id="tailai_nv_kiemke">
									  <i class="si si-reload"></i> Tải lại
								</button>
								<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modal_add_nv_kiemke">
									  <i class="fa fa-circle-plus"></i> Thêm mới phiếu kiểm kê
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
<div class="modal" id="modal_add_nv_kiemke" tabindex="-1" aria-labelledby="modal-block-large" style="display: none;" aria-hidden="true">
	<div class="modal-dialog" role="document">
	  <div class="modal-content">
		<div class="block block-rounded block-themed block-transparent mb-0">
		  <div class="block-header bg-primary-dark">
			<h3 class="block-title">Thêm mới phiếu kiểm kê</h3>
			<div class="block-options">
			  <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</div>
		  </div>
			<div class="block-content">
			<form id="add_nv_kiemke_form">
				<div class="row">
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<span class="input-group-text">
								  Phòng
								</span>
								<select class="form-select" id="themmoi_nv_kiemke_map" name="themmoi_nv_kiemke_map">
									<?php 
										$result = mysqli_query($conn, "	SELECT * FROM phong");
										while ($row = $result->fetch_assoc()) {
											echo '<option value="'.$row['MaP'].'">'.$row['TenP'].'</option>';
										}
									?>
								</select>
							</div>
						</div>
					</div>
					
				
				
					
					<div class="row mb-4">
						<div class="col-12">
							<div class="input-group">
								<textarea class="form-control" id="themmoi_nv_kiemke_ghichu" name="themmoi_nv_kiemke_ghichu" rows="4" placeholder="Ghi chú"></textarea>
							</div>
						</div>
					
					</div>
					
				</div>
			</form>
			</div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" class="btn btn-sm btn-alt-secondary" id="themmoi_nv_kiemke_huy" name="themmoi_nv_kiemke_huy">Hủy thêm</button>
			<button type="button" class="btn btn-sm btn-primary" id="themmoi_nv_kiemke_xacnhan" name="themmoi_nv_kiemke_xacnhan">Xác nhận thêm</button>
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
	
	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var phongSearch = document.getElementsByName("phong_search")[0].value;
		var timeTu = document.getElementsByName("time_search_1")[0].value;
		var timeDen = document.getElementsByName("time_search_2")[0].value;
		var trangthaiSearch = document.getElementsByName("trangthai_search")[0].value;
		if (timeTu == "" && timeDen != "")
		{
			alert("Vui lòng chọn đầy đủ thời gian, hoặc không chọn!!!");
			return;
		}
		load_phantrang(phongSearch, timeTu, timeDen, trangthaiSearch, 1)

	});
	
	
	
	$("#search_nv_kiemke").click(function () {
		var phongSearch = document.getElementsByName("phong_search")[0].value;
		var timeTu = document.getElementsByName("time_search_1")[0].value;
		var timeDen = document.getElementsByName("time_search_2")[0].value;
		var trangthaiSearch = document.getElementsByName("trangthai_search")[0].value;
		if (timeTu == "" && timeDen != "")
		{
			alert("Vui lòng chọn đầy đủ thời gian, hoặc không chọn!!!");
			return;
		}
		
		load_phantrang(phongSearch, timeTu, timeDen, trangthaiSearch, 1)

		 
	});
	
	
	$("#tailai_nv_kiemke").click(function () {
		load_phantrang(null,null,null,null, 1);
	});
	load_phantrang(null,null,null,null, 1);
	
	
	
	$(document).on('click','#themmoi_nv_kiemke_xacnhan',function(e) {
		var data = $("#add_nv_kiemke_form").serialize();
		$.ajax({
			url: 'add_data_kiemke.php',
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
					  text: "Thêm mới phiếu kiểm kê thành công",
					}).then(function() {
						$("#modal_add_nv_kiemke").modal("hide");
						$('#add_nv_kiemke_form')[0].reset();
						load_phantrang(null,null,null,null, 1);
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
	
	$(document).on('click','#themmoi_nv_kiemke_huy',function(e) {
		$("#modal_add_nv_kiemke").modal("hide");
		$('#add_nv_kiemke_form')[0].reset();
	});





});





function load_phantrang(phongSearch, timeTu, timeDen, TrangThai, page) {
	$.ajax({
		url: 'nv_kiemke_ajax.php',
		type: "GET",
		data: 	{ 
					phongSearch: phongSearch, 
					timeTu: timeTu,
					timeDen: timeDen,
					TrangThai: TrangThai,
					page: page
					
				},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				var str_button = ""
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
				str += "<td><span class='fw-semibold'>" + value.MaPhieu  + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-primary text-light'>" + value.TenP + "</span></td>";
				if (value.TrangThai==0)
				{
					str_button = '<button type="button" onclick="location.href=\'nv_chitietphieukiemke.php?IDMaPhieu='+value.MaPhieu+'\'"  class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Thực hiện kiểm kê"><i class="fa fa-indent"></i> Chi tiết phiếu kiểm kê</button>';
					str += "<td style='font-size: 15px;'><span class='badge bg-danger text-light'>Chưa hoàn thành</span></td>";
				}else
				{
					str_button = '<button type="button" onclick="location.href=\'nv_kiemke_hoantat.php?IDMaPhieu='+value.MaPhieu+'\'"  class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Thực hiện kiểm kê"><i class="fa fa-print"></i> Xem lại</button>';
					str += "<td style='font-size: 15px;'><span class='badge bg-success text-light'>Đã hoàn thành</span></td>";
				}
				str += '<td class="fw-semibold">' + value.NgayCapNhat + '</td>';
				str += "<td class='fw-semibold'>" + value.GhiChu + "</td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += str_button;
				str += '</td>';	  
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