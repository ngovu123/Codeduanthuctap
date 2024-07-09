<?php
$title = "Phân bố TS-CCDC";
include("config.php");
include("check_session.php");
if(isset($_SESSION['PhanBo_Setup2'])){
	header("Location: nv_phanbo_step2.php?IDP=".$_SESSION['PhanBo_Setup2']."");
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
$nv_phanbo_show = "active";
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
              <h1 class="flex-grow-1 fs-3 fw-semibold my-2 my-sm-3">Phân bố TS-CCDC</h1>
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
			<div class="row justify-content-center text-center">
				<div class="col-md-6">
				  <div class="block block-rounded">
						<div class="block-content">
						   <form action="nv_phanbo_step_1.5.php" method="GET" >
								<div class="row">
								  
								  <div class="col-lg-12 col-xl-12">
									<div class="mb-4">
									<label class="form-label">Chọn phòng cần phân bố: </label>
									  <select class="js-select2 form-select" id="IDP" name="IDP" style="width: 100%;" data-placeholder="Chọn phòng...">
										<option></option>
										<?php 
											$result = mysqli_query($conn, "	SELECT * FROM phong");
											while ($row = $result->fetch_assoc()) {
												echo '<option value="'.$row['MaP'].'">'.$row['TenP'].'</option>';
											}
										?>
									  </select>
									</div>
								  </div>
								  <div class="col-lg-12 col-xl-12">
									<div class="mb-4">
									  <button type="submit" class="btn btn-success me-1 mb-3">
										<i class="fa fa-fw fa-check"></i> Tiếp theo
									  </button>
									</div>
								  </div>
								</div>
						  </form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		
		</div> 
	</div>
</main>














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

