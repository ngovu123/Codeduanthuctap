<?php
$title = "Tổng quan";
include("config.php");
include("check_session.php");
include("header.php");

$tongquan_show = "active";
$TongSo_TaiSan = 0;
$TongSo_PhanBo = 0;
$query_taisan = mysqli_query($conn, "select * from taisan");
while ($row = $query_taisan->fetch_assoc()) {
	$TongSo_TaiSan += $row['SLNhapVao'];
	$TongSo_PhanBo += $row['SLHienCon'];
}
$TongSo_NguoiDung = mysqli_num_rows(mysqli_query($conn, "select * from nguoidung"));
$TongSo_PhanBo = $TongSo_TaiSan - $TongSo_PhanBo;
$TongSo_PhongHoc = mysqli_num_rows(mysqli_query($conn, "select * from phong"));
if ($data['MaPQ'] == 3)
{
	header("Location: ql_phanbo.php");
	exit();
}

?>


  <body>
   

<?php 
	include("nav.php");
?>

<main id="main-container">
        <!-- Page Content -->
        <div class="content">
        

          <!-- Overview -->
          <div class="row">
            <div class="col-md-12">
              <a class="block block-rounded block-link-shadow" href="javascript:void(0)">
                <div class="block-content block-content-full">
                  <div class="py-4 text-center">
                    <div class="mb-3">
                      <i class="fa fa-computer fa-3x text-xinspire"></i>
                    </div>
                    <div class="fs-4 fw-semibold"><?php echo $TongSo_TaiSan; ?> <br> Tổng số lượng tài sản</div>
                  </div>
                </div>
              </a>
            </div>
            <div class="col-md-4">
              <a class="block block-rounded block-link-shadow" href="javascript:void(0)">
                <div class="block-content block-content-full">
                  <div class="py-4 text-center">
                    <div class="mb-3">
                      <i class="fa fa-user-md fa-3x text-xsmooth"></i>
                    </div>
                    <div class="fs-4 fw-semibold"><?php echo $TongSo_NguoiDung; ?> <br> Tổng số người dùng</div>
                  </div>
                </div>
              </a>
            </div>
            <div class="col-md-4">
              <a class="block block-rounded block-link-shadow" href="javascript:void(0)">
                <div class="block-content block-content-full">
                  <div class="py-4 text-center">
                    <div class="mb-3">
                      <i class="fa fa-briefcase fa-3x text-info"></i>
                    </div>
                    <div class="fs-4 fw-semibold"><?php echo $TongSo_PhanBo; ?>  <br> Đã phân bố</div>
                  </div>
                </div>
              </a>
            </div>
			<div class="col-md-4">
              <a class="block block-rounded block-link-shadow" href="javascript:void(0)">
                <div class="block-content block-content-full">
                  <div class="py-4 text-center">
                    <div class="mb-3">
                      <i class="fas fa-chalkboard-teacher fa-3x text-info"></i>
                    </div>
                    <div class="fs-4 fw-semibold"><?php echo $TongSo_PhongHoc; ?>  <br> Tổng số phòng học</div>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <!-- END Overview -->

         

          <!-- Appointments -->
          <div class="block block-rounded">
            <div class="block-header block-header-default">
              <h3 class="block-title">Danh sách các tài sản, thiết bị báo hỏng</h3>
              <div class="block-options">
                <button type="button" class="btn-block-option" data-toggle="block-option" data-action="state_toggle" data-action-mode="demo">
                  <i class="si si-refresh"></i>
                </button>
                <button type="button" class="btn-block-option">
                  <i class="si si-wrench"></i>
                </button>
              </div>
            </div>
            <div class="block-content block-content-full">
              <div class="table-responsive-sm">
                <table class="table table-bordered table-striped table-vcenter" >
                  <thead style="text-align: center;">
                    <tr class="bg-body-dark">
						<th style="width: 150px;">Mã báo lỗi</th>
						<th>Tên phòng</th>
						<th>Tên tài sản</th>
						<th>Mô tả hư hỏng</th>
						<th>Trạng thái</th>
						<th>Thời gian</th>
						<th>Hành động</th>
                    </tr>
                  </thead>
                  <tbody class="load-list">

                  </tbody>
                </table>
					<div class="row">
						
						<div class="col-lg-12">
							<nav id="phantrang_sachbn" class="text-center" aria-label="Page navigation example">
								<ul class="pagination justify-content-end" id="load-pagination">
								</ul>
							</nav>
						</div>
					</div>
              </div>
            </div>
          </div>
          <!-- END Appointments -->

       
        </div>
        <!-- END Page Content -->
      </main>
	  
	       <?php include('footer.php'); ?>
    </div>
    <script src="assets/js/dashmix.app.min.js"></script>
	<script src="assets/js/lib/jquery.min.js"></script>


  </body>
</html>

<script>
  
$(document).ready(function () {
	
	
	load_data_baoloi(null, 1);

	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		load_data_baoloi(null, page);
	});
	
	
	$("#tailai_ql_nkhd").click(function () {
		document.getElementsByName("txtSearch_ql_nkhd")[0].value = "";
		load_data_phanbo(null, 1);
	});
	
	



});




function _edit_tangthai_baoloi(MaBL, TrangThai)
{
	$.ajax({
        url: 'edit_data_baoloi.php',
        dataType: "json",
        type: "POST",
        data: {MaBL:MaBL, TrangThai:TrangThai},
        async: false,
        cache: false,
        success: function (data) {
             if (data.code == 1)
			 {
				Swal.fire({
				  icon: 'success',
				  title: 'Thông báo!',
				  text: data['message'],
				}).then(function() {
					load_data_baoloi(null, 1);
				});
			 }else {
				Swal.fire({
				  icon: 'error',
				  title: 'Thông báo!',
				  text: data['message'],
				}).then(function() {
					load_data_baoloi(null, 1);
				});
			 }
        },
        error: function (xhr) {
            alert("Error_Load_Data");
        }
    })
}


function load_data_baoloi(txtSearch, page) {
	$.ajax({
		url: 'load_data_baoloi.php',
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
				
				
				if (value.TrangThai == 0)
				{
					str += "<tr style='text-align: center;'>";
					str += "<td><span class='fw-semibold'>" + value.MaBL + "</span></td>";
					str += "<td><span class='badge bg-primary text-light'>" + value.TenP + "</span></td>";
					str += "<td><span class='badge bg-success text-light'>" + value.TenTS + "</span></td>";
					str += "<td><span class='fw-semibold'>" + value.Mota + "</span></td>";
					str += "<td><span class='badge bg-danger text-light'>Chưa sửa chữa</span></td>";
					str += "<td>" + value.NgayCapNhat + "</td>";
					str += '<td><button type="button" onclick="_edit_tangthai_baoloi('+value.MaBL+',1)" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Đã sửa chữa"><i class="fa fa-check text-success"></i></button></td>';
					str += "</tr>";
				}else {
					str += "<tr style='text-align: center;'>";
					str += "<td><span class='fw-semibold'>" + value.MaBL + "</span></td>";
					str += "<td><span class='badge bg-primary text-light'>" + value.TenP + "</span></td>";
					str += "<td><span class='badge bg-success text-light'>" + value.TenTS + "</span></td>";
					str += "<td><span class='fw-semibold'>" + value.Mota + "</span></td>";
					str += "<td><span class='badge bg-warning text-light'>Đã sửa chữa</span></td>";
					str += "<td>" + value.NgayCapNhat + "</td>";
					str += '<td><button type="button" onclick="_edit_tangthai_baoloi('+value.MaBL+',0)" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem"><i class="fa fa-times text-danger"></i></button></td>';
					str += "</tr>";
				}
				
				


				
				
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









