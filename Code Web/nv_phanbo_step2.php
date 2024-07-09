<?php
include("config.php");
include("check_session.php");

if(!isset($_SESSION['PhanBo_Setup2'])){
	header("Location: nv_phanbo.php");
	exit();     
}
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = '';
$ql_nhomtaisan_show = "";
$ql_loaitaisan_show = "";


$ql_phanbo_show = "";
$nv_phanbo_show = "active";

if (!isset($_GET['IDP']))
{
	header("Location: nv_phanbo.php");
	exit();
}
$MaP = mysqli_real_escape_string($conn,$_GET['IDP']);
$query = mysqli_query($conn,"SELECT * FROM phong WHERE MaP = '$MaP'");
if (mysqli_num_rows($query) == 0) 
{
	header("Location: nv_phanbo.php");
	exit();
}
$TenP = mysqli_fetch_array($query)['TenP'];
$title = "Phân bố: " . $TenP;
include("header.php");

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
              <h3 class="fw-light">Phòng đang phân bổ: <small><?php echo $TenP; ?></small></h3>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">Nghiệp vụ</li>
                  <li class="breadcrumb-item active" aria-current="page">Phân bố</li>
                </ol>
              </nav>
            </div>
          </div>
        </div>
        <!-- END Hero -->

        <!-- Page Content -->
        <div class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="block block-rounded">
						<ul class="nav nav-tabs nav-tabs-block align-items-center" role="tablist">
						<li class="nav-item d-md-flex flex-md-column" role="presentation">
							<button class="nav-link text-md-start active" id="them_ts_ccdc-tab" data-bs-toggle="tab" data-bs-target="#them_ts_ccdc" role="tab" aria-controls="them_ts_ccdc" aria-selected="true">
								<i class="fa fa-fw fa-square-plus opacity-50 me-1 d-none d-sm-inline-block"></i> TS-CCDC
							</button>
						</li>
						<li class="nav-item d-md-flex flex-md-column" role="presentation">
							<button class="nav-link text-md-start" id="thong_tin_ts_ccdc_phong-tab" data-bs-toggle="tab" data-bs-target="#thong_tin_ts_ccdc_phong" role="tab" aria-controls="thong_tin_ts_ccdc_phong" aria-selected="true">
								<i class="fa fa-fw fa-circle-info opacity-50 me-1 d-none d-sm-inline-block"></i> Thông tin TS-CCDC của phòng
							</button>
						</li>
						  <li class="nav-item ms-auto">
							<div class="block-options ps-3 pe-2">
								<button onclick = "window.location='hoantat_pb.php'" type="submit" class="btn rounded-pill btn-hero btn-success">
									<i class="fa fa-fw fa-check"></i> Hoàn tất
								</button>
							</div>
						  </li>
						</ul>
						<div class="block-content tab-content">
							<div class="tab-pane active show" id="them_ts_ccdc" role="tabpanel" aria-labelledby="them_ts_ccdc-tab" tabindex="0">
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
										  <th>Tên tài sản</th>
										  <th>Nhóm tài sản</th>
										  <th>Thuộc loại tài sản</th>
										  <th>Số lượng hiện còn</th>
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
							
						
							
							
							<div class="tab-pane" id="thong_tin_ts_ccdc_phong" role="tabpanel" aria-labelledby="thong_tin_ts_ccdc_phong-tab" tabindex="0">
								<div class="table-responsive-sm">
									<div class="col-lg-12">
										<div class="card-header">
											<div class="mb-4">
											  <div class="input-group">
												<input type="text" class="form-control" name="txtSearch_tt_taisan" placeholder="Tìm kiếm...">
												<button type="button" class="btn btn-primary" id="search_tt_taisan">
												  <i class="fa fa-search me-1"></i>
												</button>
											  </div>
											</div>
										</div>
									</div>

									<table class="table table-bordered table-striped table-vcenter" id = "table_tt_taisan">
									  <thead>
										<tr style="text-align: center;">
										  <th>ID</th>
										  <th>Tên tài sản</th>
										  <th>Nhóm tài sản</th>
										  <th>Thuộc loại tài sản</th>
										  <th>Số lượng hiện có trong phòng</th>
										  <th>Ghi chú</th>
										</tr>
									  </thead>
										<tbody class="load-list-tt_taisan">
										</tbody>
									</table>
									
									<div class="row">
										<div class="col-lg-4">
											<button type="button" class="btn btn-info" id="tailai_tt_taisan">
												  <i class="si si-reload"></i> Tải lại
											</button>
										</div>
										<div class="col-lg-8">
											<nav class="text-center" aria-label="Page navigation example">
												<ul class="pagination justify-content-end" id="load-pagination-tt-taisan">
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
		</div> 
	</div>
</main>
<div class="modal" id="them_ts_ccdc_modal" tabindex="-1" aria-labelledby="modal-block-select2" style="display: none;" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="block block-rounded block-transparent mb-0">
<div class="block-header block-header-default">
<h3 class="block-title">Thêm TS-CCDC: <?php echo $TenP; ?></h3>
<div class="block-options">
<button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
<i class="fa fa-fw fa-times"></i>
</button>
</div>
</div>
<div class="block-content">
<!-- Select2 is initialized at the bottom of the page -->
<form action="#" method="POST" onsubmit="return false;" id ="form_themmoi_pb">
<div class="mb-4">
 <div class="input-group">
<span class="input-group-text">
Số lượng cần thêm
</span>
<input type="number" class="form-control" id="SoLuongCanThem" name="SoLuongCanThem">
</div>
</div>
<div class="mb-4">
<div class="input-group">
<textarea class="form-control" id="GhiChuThem" name="GhiChuThem" rows="4" placeholder="Ghi chú"></textarea>
</div>				
</div>
<input type="hidden" id="MaP" name="MaP" value = "<?php echo $MaP; ?>">
<input type="hidden" id="MaND" name="MaND" value = "<?php echo $data['MaND']?>">
<input type="hidden" id="MaTS" name="MaTS">
</form>
</div>
<div class="block-content block-content-full text-end bg-body">
<button type="button" class="btn btn-sm btn-alt-secondary me-1" id="huy_pb_ts_ccdc" name="huy_pb_ts_ccdc">Hủy thêm</button>
<button type="button" class="btn btn-sm btn-primary" id="themmoi_pb_ts_ccdc" name="themmoi_pb_ts_ccdc">Xác nhận thêm</button>
</div>

</div>
</div>
</div>
</div>








     <?php include('footer.php'); ?>
    </div>
    <script src="assets/js/dashmix.app.min.js"></script>
	
    <!-- Page JS Plugins -->
    <script src="assets/js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="assets/js/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>
    <script src="assets/js/plugins/select2/js/select2.full.min.js"></script>
    <script src="assets/js/plugins/ion-rangeslider/js/ion.rangeSlider.min.js"></script>
    <script src="assets/js/plugins/jquery.maskedinput/jquery.maskedinput.min.js"></script>
    <script src="assets/js/plugins/dropzone/min/dropzone.min.js"></script>
    <script src="assets/js/plugins/pwstrength-bootstrap/pwstrength-bootstrap.min.js"></script>
    <script src="assets/js/plugins/flatpickr/flatpickr.min.js"></script>
	<script>Dashmix.helpersOnLoad(['js-flatpickr', 'jq-datepicker', 'jq-maxlength', 'jq-select2', 'jq-rangeslider', 'jq-masked-inputs', 'jq-pw-strength']);</script>
  </body>
</html>




<script>
  
$(document).ready(function () {
	
	$("body").on("click","#load-pagination li a",function (event) {
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

	$("body").on("click","#load-pagination-tt-taisan li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_tt_taisan")[0].value;
		if (txtSearch != "") {
		   load_phantrang_tt_taisan(txtSearch, page)
		}
		else {
		   load_phantrang_tt_taisan(null, page);
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
	load_phantrang(null, 1);
	
	$(document).on('click','#huy_pb_ts_ccdc',function(e) {
		$("#them_ts_ccdc_modal").modal("hide");
		$('#form_themmoi_pb')[0].reset();
	});
	$(document).on('click','#themmoi_pb_ts_ccdc',function(e) {
		var data = $("#form_themmoi_pb").serialize();
		$.ajax({
			url: 'add_data_phanbo.php',
			type: "POST",
			data: data,
			async: false,
			cache: false,
			success: function (dataResult) {
				var data = JSON.parse(dataResult);
				if (data['code'] == 1) {
					Swal.fire({
					  icon: 'success',
					  title: 'Phân bố thành công !',
					  text: "Phân bố thành công TS-CCDC",
					}).then(function() {
						$("#them_ts_ccdc_modal").modal("hide");
						$('#form_themmoi_pb')[0].reset();
						load_phantrang(null, 1);
						load_phantrang_tt_taisan(null, 1);

					});
					
				} else { 
					Swal.fire({
					  icon: 'error',
					  title: 'Phân bố thất bại...',
					  text: data['message'],
					})
				}
			},
			error: function (xhr) {
				alert("Error_Load_Data");
			}
		})
	});





	$("#search_tt_taisan").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_tt_taisan")[0].value;
		if (txtSearch != "") {
			load_phantrang_tt_taisan(txtSearch, 1)
		}
		else {
			load_phantrang_tt_taisan(null, 1);
		}
		 
	});
	$("#tailai_tt_taisan").click(function () {
		document.getElementsByName("txtSearch_tt_taisan")[0].value = "";
		load_phantrang_tt_taisan(null, 1);
	});
	load_phantrang_tt_taisan(null, 1);
	



});








function xacnhan_them(idp)
{
	$("#MaTS").val(idp);
	$("#them_ts_ccdc_modal").modal("show");
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
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-success text-light'>" + value.TenTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-danger text-light'>" + value.TenNTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-dark text-light'>" + value.TenLTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-warning text-light'>" + value.SLHienCon + "</span></td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" id = "Them_'+value.MaTS+'" onclick = "xacnhan_them('+value.MaTS+')" class="btn btn-sm rounded-pill btn-info"><i class="fa fa-fw fa-plus me-1"></i> Thêm vào phòng</button>'
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


function load_phantrang_tt_taisan(txtSearch, page) {
	$.ajax({
		url: 'nv_phanbo_step2_ajax.php?IDP=<?php echo $MaP; ?>',
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
				$(".load-list-tt_taisan").html(str);
				$("#load-pagination-tt-taisan").html(pagination_string);
				return 0;
			}
			$.each(result.data, function (index, value) {
				str += "<tr style='text-align: center;'>";
				str += "<td><span style='font-size: 13px;' class='fw-semibold'>" + value.MaTS  + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-success text-light'>" + value.TenTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-danger text-light'>" + value.TenNTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-dark text-light'>" + value.TenLTS + "</span></td>";
				str += "<td style='font-size: 16px;' class='fw-semibold'><span class='badge bg-warning text-light'>" + value.SoLuong + "</span></td>";
				str += "<td style='font-size: 13px;' class='fw-semibold'>" + value.GhiChu + "</td>";

				
				
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
				$("#load-pagination-tt-taisan").html(pagination_string);
			});
			//load str to class="load-list"
			$(".load-list-tt_taisan").html(str);

		}
	});
}

 
</script>