<?php
$title = "Nhật ký hoạt động";
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

$nkhd_show = "active";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Nhật ký hoạt động</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">QUẢN LÝ</li>
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
                    <button class="nav-link active" id="ql_nkhd_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_nkhd_danhsach" role="tab" aria-controls="ql_nkhd_danhsach" aria-selected="true">Danh sách</button>
                  </li>
                 
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="ql_nkhd_danhsach" role="tabpanel" aria-labelledby="ql_nkhd_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
						<div class="col-lg-12">
							<div class="card-header">
								<div class="mb-4">
								  <div class="input-group">
									<input type="text" class="form-control" name="txtSearch_ql_nkhd" placeholder="Tìm kiếm...">
									<button type="button" class="btn btn-primary" id="search_ql_nkhd">
									  <i class="fa fa-search me-1"></i>
									</button>
								  </div>
								</div>
							</div>
						</div>
						<table class="table table-bordered table-striped table-vcenter" id = "table_ql_nkhd">
						  <thead>
							<tr style="text-align: center;">
							  <th>Mã NKHĐ</th>
							  <th>Họ và tên</th>
							  <th>Tên đăng nhập</th>
							  <th>Hành động</th>
							  <th>Chi tiết hành động</th>
							  <th>Thời gian</th>
							</tr>
						  </thead>
							<tbody class="load-list">
							</tbody>
						</table>
						
						<div class="row">
							<div class="col-lg-4">
								<button type="button" class="btn btn-info" id="tailai_ql_nkhd">
									  <i class="si si-reload"></i> Tải lại
								</button>
								
							</div>
							<div class="col-lg-8">
								<nav id="phantrang_sachbn" class="text-center" aria-label="Page navigation example">
									<ul class="pagination justify-content-end" id="load-pagination">
									</ul>
								</nav>
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














     <?php include('footer.php'); ?>
    </div>
    <script src="assets/js/dashmix.app.min.js"></script>
	<script src="assets/js/lib/jquery.min.js"></script>


  
  
  </body>
</html>


<script>
  
$(document).ready(function () {
	
	
	load_data_nkhd(null, 1);

	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_nkhd")[0].value;
		if (txtSearch != "") {
		   load_data_nkhd(txtSearch, page)
		}
		else {
		   load_data_nkhd(null, page);
		}
	});
	
	
	$("#search_ql_nkhd").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_nkhd")[0].value;
		if (txtSearch != "") {
			load_data_nkhd(txtSearch, 1)
		}
		else {
			load_data_nkhd(null, 1);
		}
		 
	});
	
	$("#tailai_ql_nkhd").click(function () {
		document.getElementsByName("txtSearch_ql_nkhd")[0].value = "";
		load_data_phanbo(null, 1);
	});
	
	



});






function load_data_nkhd(txtSearch, page) {
	$.ajax({
		url: 'load_data_nkhd.php',
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
				str += "<td><span class='fw-semibold'>" + value.MaNKHD + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-warning text-light'>" + value.HoVaTen + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-danger text-light'>" + value.TenDangNhap + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-success text-light'>" + value.HanhDong + "</span></td>";
				str += "<td style='font-size: 14px;'><strong>" + value.ChiTietHanhDong + "</strong></td>";
				str += "<td class='fw-semibold'>" + value.Time + "</td>";  
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