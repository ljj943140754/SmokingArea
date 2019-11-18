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
		
			$scope.checklogin=function(){
				form.render('select');
				$.get("worker/checklogin.action",function(rs){
					console.info( "faclility_insert中获取的登陆状态" );
					console.info( rs.data );
					formlist.fy_createdby = rs.data.worker.wk_num ;
				});
				
			}
			
			 $scope.insert=()=>{
					let model = form.val("facilityInsertform");
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
					$.post("facility/insert.action",formlist,function(res){
						if(res.code == -1){
							layui.layer.msg(res.error);
						}else{
							layui.layer.msg(res.success);
						}
					});
				}
			
			$scope.checklogin();

	});
})

   