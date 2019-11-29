(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table","laydate" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		var laydate = layui.laydate;

		 laydate.render({
				elem: '#test1' //指定元素
			});
		 var formlist = {};
		 $scope.getFacilityData=function(){
	    	 var facilitydata = JSON.parse(sessionStorage.getItem("facilitydata"));
	    	 $scope.updatemodel =  facilitydata ;
	    	 $scope.$apply();
	    	 if(facilitydata==undefined||facilitydata==''){
	    		 layer.msg("数据无效，请在查询页面选择数据并点击编辑按钮");
	    	 }
		 }
			$scope.checklogin=function(){
				form.render('select');
				$.get("worker/checklogin.action",function(rs){
					formlist.fy_createdby = rs.data.worker.wk_num ;
				});
				sessionStorage.removeItem('taskdata');
			}
			
			 $scope.update=()=>{
					let model = form.val("facilityUpdateform");
					formlist.fy_creation = model.fy_creation;
					formlist.fy_detail = model.fy_detail;
					formlist.fy_isdel = model.fy_isdel;
					formlist.fy_name = model.fy_name;
					formlist.fy_lat = model.fy_lat;
					formlist.fy_lng = model.fy_lng;
					formlist.fy_res_link = model.fy_res_link;
					formlist.fy_type = model.fy_type;
					$.post("facility/deleteVR.action",formlist,function(res){
						if(res.code == -1){
							layui.layer.msg(res.error);
						}else{
							layui.layer.msg(res.success);
						}
					});
				}
			
			$scope.checklogin();
			$scope.getFacilityData();
	});
})

   