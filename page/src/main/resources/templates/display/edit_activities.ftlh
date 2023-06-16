
<!--**********************************
    Content body start
***********************************-->
<#include "../header.ftlh">
<div class="content-body">
	<div class="container-fluid">
		<div class="row">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-header">
							<h4 class="card-title">活动编辑</h4>
						</div>
						<div class="card-body">
							<div class="form-validation">
								<form class="needs-validation" novalidate="">
									<div class="row">
										<div class="col-xl-12">
											<div class="mb-3 row">
												<label class="col-lg-4 col-form-label" for="signupNum">报名人数
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="signupNum" placeholder="请输入报名人数" required="" <#if activities??>value="${activities.signupNumber}"</#if>>
													<div class="invalid-feedback">
														请输入报名人数
													</div>
												</div>
											</div>
											<div class="mb-3 row">
												<label class="col-lg-4 col-form-label" for="startTime">开始报名时间 <span class="text-danger">*</span>
												</label>
												<div class="col-xl-3 col-xxl-6 col-md-6 mb-3">
													<input id="startTime" type="text" class="form-control mdate" placeholder="2017-06-04" <#if activities??>value="${activities.startTime?string("yyyy-MM-dd")}"</#if>>
												</div>
												<div class="col-md-6 col-xl-3 col-xxl-6 mb-3">
													<div class="input-group">
														<input class="form-control" id="startTimeHMS" placeholder="Now" <#if activities??>value="${activities.startTime?string("hh:mm")}"</#if>>
														<button type="button" class="btn waves-effect waves-light btn-ft btn-success check-minutes">点击选择时间</button>
													</div>
												</div>
											</div>
											<div class="mb-3 row">
												<label class="col-lg-4 col-form-label" for="endTime">报名截止时间
													<span class="text-danger">*</span>
												</label>
												<#--<div class="col-lg-6">-->
													<div class="col-xl-3 col-xxl-6 col-md-6 mb-3">
														<input id="endTime" type="text" class="form-control mdate" placeholder="2017-06-04" <#if activities??>value="${activities.endTime?string("yyyy-MM-dd")}"</#if>>
													</div>
													<div class="col-md-6 col-xl-3 col-xxl-6 mb-3">
														<div class="input-group">
															<input class="form-control" id="endTimeHMS" placeholder="Now" <#if activities??>value="${activities.endTime?string("hh:mm")}"</#if>>
															<button type="button" class="btn waves-effect waves-light btn-ft btn-success check-minutes">点击选择时间</button>
														</div>
													</div>
												<#--</div>-->
											</div>
											<div class="mb-3 row">
												<label class="col-lg-4 col-form-label" for="validationCustom03">活动内容
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<button type="button" onclick="toEditor()" class="btn btn-primary">编辑活动内容</button>
												</div>
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
</div>
</div>
<!--**********************************
    Content body end
***********************************-->



<!--**********************************
    Footer start
***********************************-->
<div class="footer">

	<div class="copyright">
		<p>Copyright &copy; 2021.Company name All rights reserved.<a target="_blank" href="https://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
	</div>
</div>
<!--**********************************
    Footer end
***********************************-->

<!--**********************************
   Support ticket button start
***********************************-->

<!--**********************************
   Support ticket button end
***********************************-->


</div>




<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<!-- Required vendors -->
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>

<!-- Datatable -->
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/datatables.init.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>







<!-- Daterangepicker -->
<!-- momment js is must -->
<script src="/js/moment.min.js"></script>
<script src="/js/daterangepicker.js"></script>
<!-- clockpicker -->
<script src="/js/bootstrap-clockpicker.min.js"></script>
<!-- asColorPicker -->
<script src="/js/jquery-asColor.min.js"></script>
<script src="/js/jquery-asGradient.min.js"></script>
<script src="/js/jquery-asColorPicker.min.js"></script>
<!-- Material color picker -->
<script src="/js/bootstrap-material-datetimepicker.js"></script>
<!-- pickdate -->
<script src="/js/picker.js"></script>
<script src="/js/picker.time.js"></script>
<script src="/js/picker.date.js"></script>



<!-- Daterangepicker -->
<script src="/js/bs-daterange-picker-init.js"></script>
<!-- Clockpicker init -->
<script src="/js/clock-picker-init.js"></script>
<!-- asColorPicker init -->
<script src="/js/jquery-asColorPicker.init.js"></script>
<!-- Material color picker init -->
<script src="/js/material-date-picker-init.js"></script>
<!-- Pickdate -->
<script src="/js/pickadate-init.js"></script>


<script src="/js/jquery.nice-select.min.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>

<script>
	function toEditor(){
		let displayId;
		let activitiesId;
		<#if activities??>
			displayId = ${activities.displayId};
			activitiesId = ${activities.id};
		</#if>
		let signupNum = document.getElementById("signupNum").value;
		let startTime = document.getElementById("startTime").value;
		let startTimeHMS = document.getElementById("startTimeHMS").value;
		let endTime = document.getElementById("endTime").value;
		let endTimeHMS = document.getElementById("endTimeHMS").value;
		let str = "signupNum="+signupNum+"&startTime="+startTime+" "+startTimeHMS+":00&endTime="+endTime+" "+endTimeHMS+":00&activitiesId="+activitiesId+"&displayId="+displayId;
		window.location.href = "/display/editor?type=学社活动&"+str;
	}
</script>

</body>
</html>