<?php
include("config.php");
include("check_session.php");
$ql_phong_show = '';
$ql_kvphong_show = "";
$ql_lphong_show = "";

$ql_taisan_show = '';
$ql_nhomtaisan_show = "";
$ql_loaitaisan_show = "";


$ql_phanbo_show = "";
$nv_phanbo_show = "";

$nv_kiemke = "active";

if (!isset($_GET['IDMaPhieu']))
{
	header("Location: nv_kiemke.php");
	exit();
}
$MaPhieu = mysqli_real_escape_string($conn,$_GET['IDMaPhieu']);
$query_phieukiemke = mysqli_query($conn,"SELECT * 	FROM phieukiemke 
													INNER JOIN phong ON phong.MaP = phieukiemke.MaP 
													WHERE phieukiemke.MaPhieu = '$MaPhieu'"); // query vào phieukiemke
$query_chitietphieukiemke = mysqli_query($conn,"SELECT * FROM chitietphieukiemke WHERE MaPhieu = '$MaPhieu'"); // query vào chitietphieukiemke
$row_phieukiemke = mysqli_fetch_array($query_phieukiemke); // chứa dữ liệu phiếu kiểm kê
if (mysqli_num_rows($query_phieukiemke) == 0) // nếu như không tồn tại phiếu kiểm kê này thì thoát ra ngoài
{
	header("Location: nv_kiemke.php");
	exit();
}else if ($row_phieukiemke['TrangThai'] != 0)
{
	header("Location: nv_kiemke.php");
	exit();
}
$title = "Chi tiết phiếu kiểm kê";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Chi tiết phiếu kiểm kê</h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">Nghiệp Vụ</li>
                  <li class="breadcrumb-item active" aria-current="page">Chi tiết phiếu kiểm kê</li>
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
                    <button class="nav-link active" id="nv_kiemke_danhsach-tab" data-bs-toggle="tab" data-bs-target="#nv_kiemke_danhsach" role="tab" aria-controls="nv_kiemke_danhsach" aria-selected="true">
						<i class="fa fa-fw fa-circle-info opacity-50 me-1 d-none d-sm-inline-block"></i> Chi tiết phiếu kiểm
					</button>
                  </li>
				  <li class="nav-item d-md-flex flex-md-column" role="presentation">
							<button class="nav-link text-md-start" id="bankiemke-tab" data-bs-toggle="tab" data-bs-target="#bankiemke" role="tab" aria-controls="bankiemke" aria-selected="true">
								<i class="fa fa-fw fa-arrows-down-to-people opacity-50 me-1 d-none d-sm-inline-block"></i> Ban kiểm kê
							</button>
					</li>
						  <li class="nav-item ms-auto">
							<div class="block-options ps-3 pe-2">
								<button type="submit" id = "hoantat_kk" class="btn rounded-pill btn-hero btn-success">
									<i class="fa fa-fw fa-check"></i> Hoàn tất
								</button>
								<button id = "luutamthoi_kk" type="submit" class="btn rounded-pill btn-hero btn-primary">
									<i class="fa fa-fw fa-check"></i> Lưu tạm thời
								</button>
								<button onclick = "window.location='nv_kiemke.php'" type="submit" class="btn rounded-pill btn-hero btn-info">
									<i class="fa fa-fw fa-backward"></i> Quay lại
								</button>
							</div>
						  </li>
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
					<div class="tab-pane fade show active" id="nv_kiemke_danhsach" role="tabpanel" aria-labelledby="nv_kiemke_danhsach" tabindex="0">
						<div class="table-responsive-sm">
							<div class="col-lg-12">
								<p>Mã phiếu: <?php echo $MaPhieu;?></p>
								<p style="font-size: 16px;">Tên phòng: <span class="badge bg-primary text-light"><?php echo $row_phieukiemke['TenP'];?></span></p>
								<p style="font-size: 16px;">Người dùng đang kiểm kê: <span class="badge bg-secondary text-light"><?php echo $_SESSION['TenDangNhap']; ?></span></p>
							</div>
							<table class="table table-bordered table-striped table-vcenter" id = "table_nv_kiemke">
							  <thead>
								<tr style="text-align: center;">
								  <th>Mã ctpkk</th>
								  <th>Tên tài sản</th>
								  <th>Số lượng - Theo sổ kế toán</th>
								  <th>Số lượng - Theo sổ kiểm kê</th>
								  <th>Còn tốt</th>
								  <th>Kém phẩm chất</th>
								  <th>Mất phẩm chất</th>
								  <th>Ghi chú</th>
								</tr>
							  </thead>
								<tbody class="load-list">
								</tbody>
							</table>
						</div>
						  
					</div>
					
					<div class="tab-pane fade" id="bankiemke" role="tabpanel" aria-labelledby="bankiemke" tabindex="0">
						<div class="table-responsive-sm">
							<div class="row mb-4">
								<div class="col-12">
									<div class="input-group">
										
										<select class="js-select2 form-select" id="MaND" name="MaND" style="width: 100%;" data-placeholder="Chọn ban kiểm kê...">
										<option></option>
											<?php 
												$result = mysqli_query($conn, "	SELECT * FROM nguoidung 
																				INNER JOIN chucdanh ON chucdanh.MaCD = nguoidung.MaCD 
																				INNER JOIN donvi ON donvi.MaDV = nguoidung.MaDV");
												while ($row = $result->fetch_assoc()) {
													echo '<option value="'.$row['MaND'].'">'.$row['HoVaTen'].' - '.$row['TenDV'].' - '.$row['TenCD'].'</option>';
												}
											?>
										</select>
									</div>
								</div>
							</div>
							<div class="row mb-4">
								<div style="text-align:center" class="col-12">
									<button type="submit" id = "add_data_bankiemke" name = "add_data_bankiemke" class="btn btn-primary">Xác nhận thêm</button>
									<button type="submit" id = "huy_data_bankiemke" name = "huy_data_bankiemke" class="btn btn-danger">Hủy thêm</button>
								</div>
								
							</div>

							<table class="table table-bordered table-striped table-vcenter" id = "table_bkk">
							  <thead>
								<tr style="text-align: center;">
								  <th>Mã ban kiểm kê</th>
								  <th>Họ và tên</th>
								  <th>Đơn vị</th>
								  <th>Chức vụ</th>
								  <th>Hành động</th>
								</tr>
							  </thead>
								<tbody class="load-list-bkk">
									
								</tbody>
							</table>
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
	var MaPhieu = <?php echo $_GET['IDMaPhieu']?>;
	
	load_data_chitiet_phieukiemke(MaPhieu)
	load_data_bankiemke(MaPhieu)

	
	
	$(document).on('click','#luutamthoi_kk',function(e) {
			var Arr_MaCTPKK = [];
			var Arr_SLKK = [];
			var Arr_ConTot = [];
			var Arr_KemPC = [];
			var Arr_MatPC = [];
			var Arr_GhiChu = [];
			var result = "";
			var MaCTPKK = "";
		$('#table_nv_kiemke input[name="ConTot_Name"]').each(function(index){
			var get_MaCTPKK = $(this).attr('id');
			MaCTPKK = get_MaCTPKK.split('_')[1];
			var SLKK = $("#SLKK_" + MaCTPKK).val();
            var ConTot = $("#ConTot_" + MaCTPKK).val();
            var KemPC = $("#KemPC_" + MaCTPKK).val();
            var MatPC = $("#MatPC_" + MaCTPKK).val();
            var GhiChu = $("#ghichu_" + MaCTPKK).val();
			Arr_MaCTPKK.push(MaCTPKK);
			Arr_SLKK.push(SLKK);
			Arr_ConTot.push(ConTot);
			Arr_KemPC.push(KemPC);
			Arr_MatPC.push(MatPC);
			Arr_GhiChu.push(GhiChu);
		});
		result = edit_chitietphieukiemke(MaPhieu, Arr_MaCTPKK, "LuuTamThoi", Arr_SLKK, Arr_ConTot, Arr_KemPC, Arr_MatPC, Arr_GhiChu);
		if (result.code == 1)
		{
			Swal.fire({
			  icon: 'success',
			  title: 'Thông báo!',
			  html: result.message,
			}).then(function() {
				location.reload();
			});	
		}else {
			Swal.fire({
			  icon: 'error',
			  title: 'Thông báo!',
			  html: result.message,
			}).then(function() {
				load_data_chitiet_phieukiemke(MaPhieu)
			});	
		}	
	});
	
	
	$(document).on('click','#hoantat_kk',function(e) {
			var Arr_MaCTPKK = [];
			var Arr_SLKK = [];
			var Arr_ConTot = [];
			var Arr_KemPC = [];
			var Arr_MatPC = [];
			var Arr_GhiChu = [];
			var result = "";
			var MaCTPKK = "";
		$('#table_nv_kiemke input[name="ConTot_Name"]').each(function(index){
			var get_MaCTPKK = $(this).attr('id');
			MaCTPKK = get_MaCTPKK.split('_')[1];
			var SLKK = $("#SLKK_" + MaCTPKK).val();
            var ConTot = $("#ConTot_" + MaCTPKK).val();
            var KemPC = $("#KemPC_" + MaCTPKK).val();
            var MatPC = $("#MatPC_" + MaCTPKK).val();
            var GhiChu = $("#ghichu_" + MaCTPKK).val();
			Arr_MaCTPKK.push(MaCTPKK);
			Arr_SLKK.push(SLKK);
			Arr_ConTot.push(ConTot);
			Arr_KemPC.push(KemPC);
			Arr_MatPC.push(MatPC);
			Arr_GhiChu.push(GhiChu);
		});
		result = edit_chitietphieukiemke(MaPhieu, Arr_MaCTPKK, "HoanTat", Arr_SLKK, Arr_ConTot, Arr_KemPC, Arr_MatPC, Arr_GhiChu);
		if (result.code == 2)
		{
			Swal.fire({
			  icon: 'success',
			  title: 'Thông báo!',
			  html: result.message,
			}).then(function() {
				location.reload();
			});	
		}else {
			Swal.fire({
			  icon: 'error',
			  title: 'Thông báo!',
			  html: result.message,
			}).then(function() {
				//load_data_chitiet_phieukiemke(MaPhieu)
			});	
		}
		
		
		
	});





	$(document).on('click','#add_data_bankiemke',function(e) {
		var MaND = document.getElementsByName("MaND")[0].value;
		$.ajax({
		url: 'add_data_bankiemke.php',
		type: "POST",
		data: { 
					MaPhieu: MaPhieu, 
					MaND: MaND
				},
		dataType: 'json',
		async: false,
		cache: false,
		success: function (result) {
			if (result.code == 1)
			{
				Swal.fire({
					icon: 'success',
					title: 'Thông báo!',
					html: result.message,
				}).then(function() {
					load_data_bankiemke(MaPhieu)
				});	
				
			}else {
				Swal.fire({
					icon: 'error',
					title: 'Thông báo!',
					html: result.message,
				}).then(function() {
					load_data_bankiemke(MaPhieu)
				});	
			}
			}
		});
		
	});



});






function edit_chitietphieukiemke(MaPhieu, MaCTPKK, cmd, Arr_SLKK, ConTot, KemPC, MatPC, GhiChu){
	var rt = "";
	$.ajax({
        url: 'edit_nv_chitiet_phieukiemke.php',
        dataType: "json",
        type: "POST",
        data: {MaPhieu:MaPhieu, MaCTPKK:MaCTPKK, cmd:cmd, SLKK: Arr_SLKK, ConTot:ConTot, KemPC:KemPC, MatPC:MatPC, GhiChu:GhiChu},
        async: false,
        cache: false,
        success: function (data) {
            rt = data;
        },
        error: function (xhr) {
            //alert("Error_Load_Data");
        }
    });
	return rt;


}


function load_data_bankiemke(MaPhieu) {
	$.ajax({
		url: 'load_data_bankiemke_byMaPhieu.php',
		type: "GET",
		data: { MaPhieu: MaPhieu},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='5'>Không có dữ liệu</td>"
				str += "</tr>"
				var pagination_string = "";
				$(".load-list-bkk").html(str);
				return 0;
			}
			$.each(result.data, function (index, value) {
				//index++;
				str += "<tr style='text-align: center;'>";
				str += "<td><span class='fw-semibold'>" + value.Mabkk + "</span></td>";
				str += '<td><span class="badge bg-warning text-light">' +  value.HoVaTen + '</span></td>';
				str += "<td><span class='fw-semibold'>" + value.TenDV + "</span></td>";
				str += "<td><span class='fw-semibold'>" + value.TenCD + "</span></td>";
				str += '<td class="text-center"><div class="btn-group">';
				str += '<button type="button" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xóa"><i class="fa fa-times"></i></button></div></td>';	  
				str += "</tr>"; 
			});
			$(".load-list-bkk").html(str);
		}
	});
}



function load_data_chitiet_phieukiemke(MaPhieu) {
	$.ajax({
		url: 'load_data_chitietphieukiemke.php',
		type: "GET",
		data: { MaPhieu: MaPhieu},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='7'>Không có dữ liệu</td>"
				str += "</tr>"
				$(".load-list").html(str);
				return 0;
			}										
			$.each(result.data, function (index, value) {
				str += "<tr style='text-align: center;'>";
				str += "<td class='fw-semibold'>" + value.MaCTPKK + "</td>";
				str += "<td class='fw-semibold'>" + value.TenTS + "</td>";
				str += '<td style="font-size: 18px;"><span class="badge bg-dark text-light">'+value.SoLuong+'</span></td>';
				str += "<td class='fw-semibold'><input class='form-control' name = 'SLKK_Name' id='SLKK_" + value.MaCTPKK + "' value='" + value.SoLuongKiemKe + "' min='0' max='" + value.SoLuongKiemKe + "' type='number' name='number'></td></td>";
				str += "<td class='fw-semibold'><input class='form-control' name = 'ConTot_Name' id='ConTot_" + value.MaCTPKK + "' value='" + value.ConTot + "' min='0' max='" + value.ConTot + "' type='number' name='number'></td></td>";
				str += "<td class='fw-semibold'><input class='form-control' name = 'KemPC_Name' id='KemPC_" + value.MaCTPKK + "' value='" + value.KemPC + "' min='0' max='" + value.KemPC + "' type='number' name='number'></td></td>";
				str += "<td class='fw-semibold'><input class='form-control' name = 'MatPC_Name' id='MatPC_" + value.MaCTPKK + "' value='" + value.MaPC + "' min='0' max='" + value.MaPC + "' type='number' name='number'></td></td>";
				str += "<td class='fw-semibold'><textarea class='form-control' name = 'ghichu_Name' id='ghichu_" + value.MaCTPKK + "' rows='5'>" + value.GhiChu + "</textarea></td>";	  
				str += "</tr>"; 
			});
			$(".load-list").html(str);
		}
	});
}


</script>