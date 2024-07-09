<?php
$title = "Quản lý phân bố";
include("config.php");
include("check_session.php");
include("header.php");
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = '';
$ql_nhomtaisan_show = "";
$ql_loaitaisan_show = "";


$ql_phanbo_show = "active";
$nv_phanbo_show = "";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Quản lý phân bố</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">QUẢN LÝ</li>
                  <li class="breadcrumb-item active" aria-current="page">Quản lý phân bố</li>
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
                    <button class="nav-link active" id="ql_phanbo_danhsach-tab" data-bs-toggle="tab" data-bs-target="#ql_phanbo_danhsach" role="tab" aria-controls="ql_phanbo_danhsach" aria-selected="true">Danh sách</button>
                  </li>
                 
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
				<div class="tab-pane fade show active" id="ql_phanbo_danhsach" role="tabpanel" aria-labelledby="ql_phanbo_danhsach" tabindex="0">
				  
					<div class="table-responsive-sm">
					  
						<div class="col-lg-12">
							<div class="card-header">
								<div class="mb-4">
								  <div class="input-group">
									<input type="text" class="form-control" name="txtSearch_ql_phanbo" placeholder="Tìm kiếm...">
									<button type="button" class="btn btn-primary" id="search_ql_phanbo">
									  <i class="fa fa-search me-1"></i>
									</button>
								  </div>
								</div>
							</div>
						</div>
						<table class="table table-bordered table-striped table-vcenter" id = "table_ql_phanbo">
						  <thead>
							<tr style="text-align: center;">
							  <th>STT</th>
							  <th>Tên phòng</th>
							  <th>Tổng số TS-CCDC có trong phòng</th>
							  <th>Ngày cập nhật gần nhất</th>
							  <th>Hành động</th>
							</tr>
						  </thead>
							<tbody class="load-list">
							</tbody>
						</table>
						
						<div class="row">
							<div class="col-lg-4">
								<button type="button" class="btn btn-info" id="tailai_ql_phanbo">
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

<div class="modal" id="xemchitiet_phanbo_modals" tabindex="-1" aria-labelledby="modal-block-tabs-alternative" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
	  <div class="modal-content">
	  
		<!-- Block Tabs Alternative Style -->
		<div class="block block-transparent bg-white mb-0">
		
		  <ul class="nav nav-tabs nav-tabs-alt" role="tablist">
			<li class="nav-item" role="presentation">
			  <button class="nav-link active" id = "dsts"  data-bs-toggle="tab" data-bs-target="#danhsachtaisan" role="tab" aria-controls="danhsachtaisan" aria-selected="true">
				Danh sách tài sản
			  </button>
			</li>
			<li class="nav-item" role="presentation">
			  <button class="nav-link" id = "tanggiam" data-bs-toggle="tab" data-bs-target="#taisan_ghigiam_ghitang" role="tab" aria-controls="taisan_ghigiam_ghitang" aria-selected="false" tabindex="-1">
				Ghi tăng giảm
			  </button>
			</li>
			<li class="nav-item ms-auto" role="presentation">
			  <button class="nav-link"  data-bs-dismiss="modal" aria-label="Close" role="tab" aria-controls="btabs-alt-static-settings" aria-selected="false" tabindex="-1">
				<i class="fa fa-fw fa-times"></i>
			  </button>
			</li>
		  </ul>
		  <div class="block-content tab-content">
			<div class="tab-pane active show" id="danhsachtaisan" role="tabpanel" aria-labelledby="danhsachtaisan" tabindex="0">
				<table class="table table-bordered table-striped table-vcenter">
				<thead>
					<tr style="text-align: left;border-right-style:hidden;border-left-style:hidden;border-top-style:hidden">
						<th colspan="12">
							<p>Thông tin trong phòng:	</p>
							<div id="thongtinphong"></div>
						</th>
					</tr>
					<tr style="text-align: center;">
						<td>Mã ts</td>
						<td>Tên tài sản</td>
						<td>Nhóm tài sản</td>
						<td>Thuộc loại tài sản</td>
						<td>Số lượng có trong phòng</td>
						<td>Ghi chú</td>
					</tr>	
				</thead>
				<tbody class="load-list-danhsachtaisan">
				</tbody>
				</table>
			</div>
			<div class="tab-pane" id="taisan_ghigiam_ghitang" role="tabpanel" aria-labelledby="taisan_ghigiam_ghitang" tabindex="0">
				<table class="table table-bordered table-striped table-vcenter" id = "table_ghitang_ghigiam">
				<thead>
					<tr style="text-align: left;border-right-style:hidden;border-left-style:hidden;border-top-style:hidden">
					</tr>
					<tr style="text-align: center;">
						<td>Mã ts</td>
						<td>Tên tài sản</td>
						<td>Nhóm tài sản</td>
						<td>Thuộc loại tài sản</td>
						<td>Số lượng có trong phòng</td>
						<td>Số lượng muốn tăng</td>
						<td>Số lượng muốn giảm</td>
					</tr>	
				</thead>
				<tbody class="load-list-ghigiamghitang">
				</tbody>
				</table>
			</div>
		  </div>
		  <div class="block-content block-content-full text-end bg-body">
			<button type="button" id = "tat" class="btn btn-sm btn-alt-secondary" data-bs-dismiss="modal">Tắt</button>
			<button type="button" id = "hoanthanh" class="btn btn-sm btn-primary">Hoàn thành</button>
		  </div>
		</div>
		<!-- END Block Tabs Alternative Style -->
	  </div>
	</div>
</div>
  
  
  </body>
</html>


<script>
  
$(document).ready(function () {
	
	
	load_data_phanbo(null, 1);

	$("body").on("click",".pagination li a",function (event) {
		event.preventDefault();
		var page = $(this).attr('data-page');
		var txtSearch = document.getElementsByName("txtSearch_ql_phanbo")[0].value;
		if (txtSearch != "") {
		   load_data_phanbo(txtSearch, page)
		}
		else {
		   load_data_phanbo(null, page);
		}
	});
	
	
	$("#search_ql_phanbo").click(function () {
		var txtSearch = document.getElementsByName("txtSearch_ql_phanbo")[0].value;
		if (txtSearch != "") {
			load_data_phanbo(txtSearch, 1)
		}
		else {
			load_data_phanbo(null, 1);
		}
		 
	});
	
	$("#tailai_ql_phanbo").click(function () {
		document.getElementsByName("txtSearch_ql_phanbo")[0].value = "";
		load_data_phanbo(null, 1);
	});
	
	
	$("#dsts").click(function () {
		$("#tat").text("Tắt");
		$("#hoanthanh").text("Xem xong!"); 
	});


	$("#tanggiam").click(function () {
		$("#tat").text("Hủy ghi tăng - ghi giảm");
		$("#hoanthanh").text("Xác nhận ghi tăng - ghi giảm"); 
	});


	$("#hoanthanh").click(function () {
		if ($(this).text() == "Xác nhận ghi tăng - ghi giảm")
		{
			var Arr_MaPB = [];
			var Arr_Tang = [];
			var Arr_Giam = [];
			$('#table_ghitang_ghigiam input[name="Tang"]').each(function(index){
				var get_id_Tang_Giam = $(this).attr('id');
				var MaPB = get_id_Tang_Giam.split('_')[1];
				var SoLuong_Tang = $("#Tang_" + MaPB).val();
				var SoLuong_Giam = $("#Giam_" + MaPB).val();
				Arr_MaPB.push(MaPB);
				Arr_Tang.push(SoLuong_Tang);
				Arr_Giam.push(SoLuong_Giam);
			})
			
			var result = ghigiam_ghitang(Arr_MaPB, Arr_Tang, Arr_Giam);
			
			if (result.code == 1)
			{
				Swal.fire({
				  icon: 'success',
				  title: 'Thông báo !',
				  text: result.message,
				}).then(function() {
					load_data_phanbo(null, 1);
					$('#xemchitiet_phanbo_modals').modal("hide");
				});
			}else {
				Swal.fire({
				  icon: 'error',
				  title: 'Thông báo !',
				  text: result.message,
				})
			}
			
			
			
			
			
		}else {
			$('#xemchitiet_phanbo_modals').modal("hide");
		}
		
	});


});


function ghigiam_ghitang(MaPB, Tang, Giam){
	var rt = "";
	$.ajax({
        url: 'edit_ghitang_ghigiam.php',
        dataType: "json",
        type: "POST",
        data: {MaPB:MaPB, Tang:Tang, Giam:Giam},
        async: false,
        cache: false,
        success: function (data) {
             rt = data;
        },
        error: function (xhr) {
            alert("Error_Load_Data");
        }
    })
	return rt;

}



function get_data(MaP){
	var TenP = ""
	var TongSLTS = 0
	$.ajax({
		url: 'load_data_phanbo_byMaPB.php',
		type: "GET",
		data: { MaP: MaP},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			if (result.code == 0){
				var str = ""
				var tang_giam = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='6'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				tang_giam += "<tr style= 'text-align: center;' class='no-data'>"
				tang_giam += "<td colspan='7'>Không có dữ liệu trong bảng</td>"
				tang_giam += "</tr>"
				$(".load-list-danhsachtaisan").html(str);
				$(".load-list-ghigiamghitang").html(tang_giam);
				return 0;
			}
			$.each(result.data, function (index, value) {
				str += "<tr style='text-align: center;'>";
				str += "<td>" + value.MaTS  + "</td>";
				str += "<td>" + value.TenTS + "</td>";
				str += "<td>" + value.TenNTS + "</td>";
				str += "<td>" + value.TenLTS + "</td>";
				str += "<td>" + value.SoLuong + "</td>";  
				str += "<td>" + value.GhiChu + "</td>";  
				str += "</tr>";
				
				
				tang_giam += "<tr style='text-align: center;'>";
				tang_giam += "<td>" + value.MaTS  + "</td>";
				tang_giam += "<td>" + value.TenTS + "</td>";
				tang_giam += "<td>" + value.TenNTS + "</td>";
				tang_giam += "<td>" + value.TenLTS + "</td>";
				tang_giam += "<td>" + value.SoLuong + "</td>";  
				tang_giam += "<td><input class='form-control' name = 'Tang' id='Tang_" + value.MaPB + "' value='0' min='0'  type='number' name='number'></td></td>";
				tang_giam += "<td><input class='form-control' name = 'Giam' id='Giam_" + value.MaPB + "' value='0' min='0' type='number' name='number'></td></td>";
				tang_giam += "</tr>";
				TongSLTS = value.SoLuongTong;
				TenP = value.TenP;
					
				
		
			});
			$("#thongtinphong").empty().append("<p>- Tên phòng: "+TenP+"</p>");
			$("#thongtinphong").append("<p>- Tổng số lượng tài sản có trong phòng: "+TongSLTS+"</p>");
			$(".load-list-danhsachtaisan").html(str);
			$(".load-list-ghigiamghitang").html(tang_giam);
			

		},
		error: function (xhr) {
			data_return = -1;
		}
	});
	
	
	
	$('#xemchitiet_phanbo_modals').modal("show");


}



function load_data_phanbo(txtSearch, page) {
	$.ajax({
		url: 'load_data_phanbo.php',
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
			var STT = 0;
			$.each(result.data, function (index, value) {
				STT += 1;
				str += "<tr style='text-align: center;'>";
				str += "<td><span class='fw-semibold'>" + STT + "</span></td>";
				str += "<td style='font-size: 16px;'><span class='badge bg-primary text-light'>" + value.TenP + "</span></td>";
				str += "<td style='font-size: 18px;'><span class='badge bg-dark text-light'>" + value.SoLuongTong + "</span></td>";
				str += "<td class='fw-semibold'>" + value.NgayCapNhat + "</td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" onclick = "get_data('+value.MaP+')" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xem chi tiết"><i class="fa fa-eye"></i> Xem chi tiết</button>'
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