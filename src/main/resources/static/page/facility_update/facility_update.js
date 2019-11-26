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
		 //fy_createdby , fy_creation , fy_detail ,fy_isdel ,fy_name
		 //fy_lat , fy_lng , fy_res_link , fy_type
		 $scope.getFacilityData=function(){
	    	 var facilitydata = JSON.parse(sessionStorage.getItem("facilitydata"));
	    	 console.info( "获取的该行的数据------" );
	    	 console.info( facilitydata );
	    	 $scope.updatemodel =  facilitydata ;
	    	 console.info("$scope.updatemodel------");
	    	 console.info( $scope.updatemodel );
		 }
			$scope.checklogin=function(){
				form.render('select');
				$.get("worker/checklogin.action",function(rs){
					formlist.fy_createdby = rs.data.worker.wk_num ;
				});
				
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
					console.info( "formlist" );
					console.info( formlist );
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

   