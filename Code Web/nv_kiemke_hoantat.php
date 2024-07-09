<?php
$title = "Xem lại phiếu kiểm kê";
include("config.php");
include("check_session.php");

$MaPhieu = mysqli_real_escape_string($conn,$_GET['IDMaPhieu']);
$query = mysqli_query($conn,"	SELECT chitietphieukiemke.*, phong.TenP FROM chitietphieukiemke 
								INNER JOIN phieukiemke ON phieukiemke.MaPhieu = chitietphieukiemke.MaPhieu 
								INNER JOIN phong ON phong.MaP = phieukiemke.MaP 
								WHERE chitietphieukiemke.MaPhieu = '$MaPhieu'");
if (mysqli_num_rows($query) == 0) 
{
	header("Location: nv_kiemke.php");
	exit();
}
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
$query_chitiet = mysqli_fetch_array($query);




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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Xem lại phiếu kiểm kê của: <span ><?php echo $query_chitiet['TenP']; ?></span></h1>
              <nav class="flex-shrink-0 my-2 my-sm-0 ms-sm-3" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">NGHIỆP VỤ</li>
                  <li class="breadcrumb-item active" aria-current="page">Xem lại phiếu kiểm kê</li>
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
                    <button class="nav-link active" id="nv_kiemke_hoantat_danhsach-tab" data-bs-toggle="tab" data-bs-target="#nv_kiemke_hoantat_danhsach" role="tab" aria-controls="nv_kiemke_hoantat_danhsach" aria-selected="true">Thông tin</button>
                  </li>
                 	<li class="nav-item">
                    <button class="nav-link" id="nv_kiemke_thongke_chechlech-tab" data-bs-toggle="tab" data-bs-target="#nv_kiemke_thongke_chechlech" role="tab" aria-controls="nv_kiemke_thongke_chechlech" aria-selected="true">Thống kê chêch lệch</button>
                  </li>
                 
                </ul>
				
                <div class="block-content tab-content overflow-hidden">
				
				
					<div class="tab-pane fade show active" id="nv_kiemke_hoantat_danhsach" role="tabpanel" aria-labelledby="nv_kiemke_hoantat_danhsach" tabindex="0">
						<div class="table-responsive-sm">
							<div style="padding: 10px;">
							  <button id = "exportBtn" style="display: inline-block; margin-right: 10px; background-color: #4CAF50; color: white; padding: 8px 16px; border: none; border-radius: 4px;">
								<i class="fas fa-file-excel"></i> Xuất Excel
							  </button>
							 
							</div>
							<table class="table table-bordered table-striped table-vcenter" id = "table_nv_kiemke_hoantat">
							  <thead>
								<tr style="text-align: left;border-right-style:hidden;border-left-style:hidden;border-top-style:hidden">
									<th colspan="12">
										<p>Ban kiểm kê gồm:	<p>
										<p id = "bankiemke"><p>										
										<p>Đã kiểm kê TSCĐ, kết quả như sau:<p>
									</th>
								</tr>			
								<tr style="text-align: center;">
									<td rowspan="2">STT</td>
									<td rowspan="2">Tên tài sản</td>
									<td rowspan="2">Mã số tài sản</td>
									<td rowspan="2">Năm sử dụng</td>
									<td colspan="2">Theo sổ kế toán</td>
									<td colspan="2">Theo sổ kiểm kê</td>
									<td colspan="3">Phẩm chất</td>
									<td rowspan="2">Ghi chú</td>
								</tr>
								<tr style="text-align: center;">
									<td>Đơn vị tính</td>
									<td>Số lượng</td>
									<td>Đơn vị tính</td>
									<td>Số lượng</td>
									<td>Còn tốt</td>
									<td>Kém phẩm chất</td>
									<td>Mất phẩm chất</td>
								</tr>
							  </thead>
								<tbody class="load-list-kiemke">
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="tab-pane fade" id="nv_kiemke_thongke_chechlech" role="tabpanel" aria-labelledby="nv_kiemke_thongke_chechlech" tabindex="0">
						<p><strong>Thông tin sau khi thống kê chênh lệch giữa phiếu kiểm kê này với phòng: <span style="font-size: 15px;" class="badge bg-primary text-light"><?php echo $query_chitiet['TenP']; ?></span> thu được kết quả như sau:</strong> <p>
						<ul class="fa-ul list-icons">
							<li><span class="fa-li text-primary"><i class="fas fa-plus"></i></span>Tổng số thiết bị có trong phòng: <div style="display:inline;" id = "TongSo_TB" class="fw-semibold">40</div></li>
							<li><span class="fa-li text-primary"><i class="fas fa-plus"></i></span>Tổng số thiết bị đã kiểm kê: <div style="display:inline;" id = "TongSo_TB_DaKK" class="fw-semibold">40</div></li>
							<li><span class="fa-li text-primary"><i class="fas fa-plus"></i></span>Tổng số thiết bị chưa được kiểm kê (các thiết bị mới): <div style="display:inline;" id = "TongSo_TB_ChuaDuocKiemKe" class="fw-semibold">0</div></li>
							<li><span class="fa-li text-primary"><i class="fas fa-plus"></i></span>Tổng số thiết bị mất hoặc thiếu: <div style="display:inline;" id = "TongSo_TB_MatThieu" class="fw-semibold">0</div></li>
							<li><span class="fa-li text-primary"><i class="far fa-grin-alt"></i></span>Tổng số thiết bị còn tốt: <div style="display:inline;" id = "TongSo_TB_Tot" class="fw-semibold">40</div></li>
							<li><span class="fa-li text-primary"><i class="far fa-frown-open"></i></span>Tổng số thiết bị kém phẩm chất: <div style="display:inline;" id = "TongSo_TB_KemPC" class="fw-semibold">3</div></li>
							<li><span class="fa-li text-primary"><i class="far fa-frown"></i></span>Tổng số thiết bị mất phẩm chất: <div style="display:inline;" id = "TongSo_TB_MatPC" class="fw-semibold">0</div></li>
						</ul>
						
						<table class="table table-bordered table-striped table-vcenter" id = "table_nv_kiemke_hoantat">
						  <thead>			
							<tr style="text-align: center;">
								<td >STT</td>
								<td >Tên tài sản</td>
								<td>Mã số tài sản</td>
								<td >Số lượng thiếu mất</td>
								<td>Số lượng được thêm mới</td>
								<td >Trạng thái</td>
							</tr>

						  </thead>
							<tbody class="load-list-thongke_chenhlech">
							</tbody>
						</table>
							
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
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<!-- TableExport plugin -->
	<script src="https://cdn.jsdelivr.net/npm/tableexport@5.2.0/dist/js/tableexport.min.js"></script>

	<!-- js-xlsx library -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.core.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>

<script lang="javascript" src="dist/xlsx.bundle.js"></script>


  </body>
</html>


<script>
  
$(document).ready(function () {
	
	
	
	load_data_xemlai_phieukiemke(<?php echo $MaPhieu; ?>);
	load_data_thongke_chenhlech(<?php echo $MaPhieu; ?>);

	data_chitiet_phieukiemke = get_data_chitiet_phieukiemke(<?php echo $MaPhieu; ?>);



	$('#exportBtn').click(function() {
		const workbook = XLSX.utils.book_new();
		const worksheet = XLSX.utils.aoa_to_sheet([
					  ["","","",""],
					]);
		
		var merges = [
				{s: {r: 4, c: 0}, e: {r: 4, c: 11}}, 
				{s: {r: 12, c: 0}, 	e: {r: 13, 	c: 0}}, 
				{s: {r: 12, c: 1}, e: { r: 13, 	c: 1 }},
				{s: {r: 12, c: 2}, e: { r: 13, 	c: 2 }},
				{s: {r: 12, c: 3}, e: { r: 13, 	c: 3 }},
				{s: {r: 12, c: 11}, e: { r: 13, c: 11 }},
				{s: {r: 12, c: 4}, e: { r: 12, 	c: 5 }}, 
				{s: {r: 12, c: 6}, e: { r: 12, 	c: 7 }},
				{s: {r: 12, c: 8}, e:{r: 12, c: 10 }}, 
			];

		worksheet['!merges'] = merges;
		worksheet['!cols'] = [
							{ width: 20 },
							{ width: 30 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 }, 
							{ width: 20 },
							{ width: 20 },
							{ width: 20 },							
							];
		XLSX.utils.sheet_add_aoa(worksheet, [[""],[""],[""]], {origin:'A1'});
		worksheet['A1'] = { v: 'Đơn vị: Trường Đại Học Thủ Dầu Một', t: 's', s: {font: {name: "Times New Roman", bold: true, sz: 11}} };
		worksheet['A2'] = { v: 'Bộ phận: ...................................', t: 's', s: {font: {name: "Times New Roman", bold: true, sz: 11},} };
		worksheet['A3'] = { v: 'Mã đơn vị SDNS: …………….', t: 's', s: {font: {name: "Times New Roman", bold: true, sz: 11},} };
		XLSX.utils.sheet_add_aoa(worksheet, [[""]], {origin:'A5'});
		worksheet['A5'] = { v: 'BIÊN BẢN KIỂM KÊ TÀI SẢN', t: 's', s: {font: {name: "Times New Roman", bold: true, sz: 12},alignment: {horizontal: "center"}} };
		XLSX.utils.sheet_add_aoa(worksheet, [["Số: ………………"]], {origin:'J6'});
		
		
		XLSX.utils.sheet_add_aoa(worksheet,[[""],[""],[""],[""],[""],[""],], {origin:'B7'});
		worksheet['B7'] = { v: 'Ban kiểm kê gồm:', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11}} };


		var B = 8
		$.each(data_chitiet_phieukiemke.bkk, function (index, value) {
			if (B <= 11)
			{
				worksheet['B' + B] = { v: '- Ông/Bà: '+value.HoVaTen+'; Chức vụ: '+value.TenCD+'; Đại diện: '+value.TenDV+'', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11},} };
			}
			B++;
		});
		worksheet['B12'] = { v: 'Đã kiểm kê tài sản, kết quả như sau:', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11},} };
		//worksheet['B9'] = { v: '- Ông/Bà: Lê Thanh Tâm Chức vụ :Chuyên viên  Đại diện Phòng Cơ Sở Vật Chất Ủy viên', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11},} };
		//worksheet['B10'] = { v: '- Ông/Bà: ......................... Chức vụ .................. Đại diện ........................... Ủy viên', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11},} };
		//worksheet['B11'] = { v: '- Ông/Bà: ......................... Chức vụ .................. Đại diện ........................... Ủy viên', t: 's', s: {font: {name: "Times New Roman", bold: false, sz: 11},} };


		XLSX.utils.sheet_add_aoa(worksheet, [["Số TT","Tên tài sản cố định","Mã số TSCĐ","Năm sử dụng"]], {origin:'A13'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Theo sổ kế toán"]], {origin:'E13'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Theo sổ kiểm kê"]], {origin:'G13'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Phẩm chất"]], {origin:'I13'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Ghi chú"]], {origin:'L13'});
		
		XLSX.utils.sheet_add_aoa(worksheet, [["Đơn vị tính"]], {origin:'E14'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Số lượng"]], {origin:'F14'});
		
		
		XLSX.utils.sheet_add_aoa(worksheet, [["Đơn vị tính"]], {origin:'G14'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Số lượng"]], {origin:'H14'});

		XLSX.utils.sheet_add_aoa(worksheet, [["Còn tốt"]], {origin:'I14'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Kém phẩm chất"]], {origin:'J14'});
		XLSX.utils.sheet_add_aoa(worksheet, [["Mất phẩm chất"]], {origin:'K14'});

		worksheet["A13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["B13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["C13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["D13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["E13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["G13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["I13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["L13"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["E14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["F14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["G14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["H14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["I14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["J14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		worksheet["K14"].s = {font: {name: "Times New Roman", bold: false, sz: 11},alignment: {horizontal: "center", vertical: "center"}};
		for (var i = 12; i < 26; i++) {
			for (var j = 0; j < 12; j++) {
			var cell_ref = XLSX.utils.encode_cell({ r: i, c: j });
			var cell_style = worksheet[cell_ref] ? worksheet[cell_ref].s : {};
				cell_style.border = {
					top: { style: "thin", color: { auto: 1 } },
					bottom: { style: "thin", color: { auto: 1 } },
					left: { style: "thin", color: { auto: 1 } },
					right: { style: "thin", color: { auto: 1 } }
				};
				worksheet[cell_ref] = { v: worksheet[cell_ref] ? worksheet[cell_ref].v : "", s: cell_style };
			}
		}
		
		
		var A = 15;
		var STT = 1;
		var Tong_SL = 0;
		var Tong_ConTot = 0;
		var Tong_KemPC = 0;
		var Mat_PC = 0;
		var Tong_SLKK = 0;
		$.each(data_chitiet_phieukiemke.data, function (index, value) {
			XLSX.utils.sheet_add_aoa(worksheet, [[STT, value.TenTS, value.MaTS, '', 'Cái', value.SoLuong, 'Cái', value.SoLuongKiemKe, value.ConTot, value.KemPC, value.MaPC, value.GhiChu]], {origin: 'A' + A});
			Tong_SL += parseInt(value.SoLuong);
			Tong_ConTot += parseInt(value.ConTot);
			Tong_KemPC += parseInt(value.KemPC);
			Mat_PC += parseInt(value.MaPC);
			Tong_SLKK += parseInt(value.SoLuongKiemKe);
			// Đặt border all cho các ô trong hàng 15
			const range = XLSX.utils.decode_range('A'+A+':L'+A+'');
			for (let col = range.s.c; col <= range.e.c; col++) {
			  const cell = XLSX.utils.encode_cell({r: range.s.r, c: col});
			  worksheet[cell].s = {
				border: {
				  top: {style: 'thin', color: {auto: 1}},
				  bottom: {style: 'thin', color: {auto: 1}},
				  left: {style: 'thin', color: {auto: 1}},
				  right: {style: 'thin', color: {auto: 1}}
				},
				font: {
					name: "Times New Roman", 
					bold: false, sz: 11
				},
				alignment: {
					horizontal: "center", 
					vertical: "center"
				}
			  };
			}
			A++;
			STT++;
		});
			

		
		XLSX.utils.sheet_add_aoa(worksheet, [['', 'Cộng', '', '', '', Tong_SL, '', Tong_SLKK, Tong_ConTot, Tong_KemPC, Mat_PC, '']], {origin: 'A' + A});
			// Đặt border all cho các ô trong hàng 15
			const range = XLSX.utils.decode_range('A'+A+':L'+A+'');
			for (let col = range.s.c; col <= range.e.c; col++) {
			  const cell = XLSX.utils.encode_cell({r: range.s.r, c: col});
			  worksheet[cell].s = {
				border: {
				  top: {style: 'thin', color: {auto: 1}},
				  bottom: {style: 'thin', color: {auto: 1}},
				  left: {style: 'thin', color: {auto: 1}},
				  right: {style: 'thin', color: {auto: 1}}
				},
				font: {
					name: "Times New Roman", 
					bold: false, sz: 11
				},
				alignment: {
					horizontal: "center", 
					vertical: "center"
				}
			  };
			}
		
		
		
		
		
		
		

		XLSX.utils.book_append_sheet(workbook, worksheet, 'BIENBANKIEMKE_<?php echo $MaPhieu; ?>');
		XLSX.writeFile(workbook, 'phieukiemke_<?php echo $MaPhieu . "_" . $time; ?>.xlsx');
  });
  
 


	
});


// Hàm chuyển đổi chuỗi sang ArrayBuffer
function s2ab(s) {
  var buf = new ArrayBuffer(s.length);
  var view = new Uint8Array(buf);
  for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
  return buf;
}





function get_data_chitiet_phieukiemke(MaPhieu){
	var rt = "";
	$.ajax({
		url: 'load_data_hoantat_kiemke.php',
		type: "GET",
		async: false,
		cache: false,
		data: { MaPhieu: MaPhieu},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			rt = result;
		}
	});
	return rt;
}





function load_data_thongke_chenhlech(MaPhieu) {
	var i = 0;
	$.ajax({
		url: 'tinhtoan_chenhlech.php',
		type: "GET",
		data: { MaPhieu: MaPhieu},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				var str_bkk = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='1'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				$(".load-list-thongke_chenhlech").html(str);
				return 0;
			}
		
			$("#TongSo_TB").text(result.tongso_thietbi_trongphong);
			$("#TongSo_TB_DaKK").text(result.tongso_thietbi_kiemke);
			$("#TongSo_TB_ChuaDuocKiemKe").text(result.tongso_thietbi_chuakiemke);
			$("#TongSo_TB_MatThieu").text(result.tongso_thietbi_thieu_mat);
			$("#TongSo_TB_Tot").text(result.tongso_thietbi_contot);
			$("#TongSo_TB_KemPC").text(result.tongso_thietbi_kempc);
			$("#TongSo_TB_MatPC").text(result.tongso_thietbi_matpc);
			
			$.each(result.thietbithemmoi, function (index, value) {
				i += 1;
				str += "<tr style='text-align: center;'>";
				str += "<td>" + i + "</td>";
				str += "<td>" + value[1] + "</td>";
				str += "<td>" + value[0] + "</td>";
				str += "<td>0</td>";
				str += "<td>"+value[2]+"</td>";
				str += '<td style="font-size: 15px;"><span class="badge bg-success text-light">Được thêm</span></td>';
				str += "</tr>";
			});
			$.each(result.thietbithieu, function (index, value) {
				i += 1;
				str += "<tr style='text-align: center;'>";
				str += "<td>" + i + "</td>";
				str += "<td>" + value[1] + "</td>";
				str += "<td>" + value[0] + "</td>";
				str += "<td>" + value[2] + "</td>";
				str += "<td>0</td>";
				str += '<td style="font-size: 15px;"><span class="badge bg-danger text-light">Thiếu</span></td>';
				str += "</tr>";
			});
			$(".load-list-thongke_chenhlech").html(str);
		}
	});
}


function load_data_xemlai_phieukiemke(MaPhieu) {
	var i = 0;
	$.ajax({
		url: 'load_data_hoantat_kiemke.php',
		type: "GET",
		data: { MaPhieu: MaPhieu},
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		success: function (result) {
			
			if (result.code == 0){
				var str = ""
				var str_bkk = ""
				str += "<tr style= 'text-align: center;' class='no-data'>"
				str += "<td colspan='12'>Không có dữ liệu trong bảng</td>"
				str += "</tr>"
				$(".load-list-kiemke").html(str);
				return 0;
			}
			$.each(result.bkk, function (index, value) {
				$("#bankiemke").append("<p>- Ông/Bà: "+value.HoVaTen+"; Chức vụ: "+value.TenCD+"; Đại diện: "+value.TenDV+"</p>");
			});
			
			$.each(result.data, function (index, value) {
				i += 1;
				str += "<tr style='text-align: center;'>";
				str += "<td>" + i + "</td>";
				str += "<td>" + value.TenTS + "</td>";
				str += "<td>" + value.MaTS + "</td>";
				str += "<td></td>";
				str += "<td>Cái</td>";
				str += "<td>" + value.SoLuong + "</td>";
				str += "<td>Cái</td>";
				str += "<td>" + value.SoLuongKiemKe + "</td>";
				str += "<td>" + value.ConTot + "</td>";
				str += "<td>" + value.KemPC + "</td>";
				str += "<td>" + value.MaPC + "</td>";
				str += "<td>" + value.GhiChu + "</td>";
				str += "</tr>";
			});
			$(".load-list-kiemke").html(str);
		}
	});
}






 
</script>
