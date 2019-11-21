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
				,type: 'datetime'
			});
		// var formlist = {};
		 //fy_createdby , fy_creation , fy_detail ,fy_isdel ,fy_name
		 //fy_lat , fy_lng , fy_res_link , fy_type
		
			$scope.init=function(){
				form.render('select');
			}
			
			 $scope.insert=()=>{
					let model = form.val("workerInsertform");
					console.info( "model" );
					console.info( model );
					$.post("facility/insert.action",model,function(res){
						if(res.code == -1){
							layui.layer.msg(res.error);
						}else{
							layui.layer.msg(res.success);
						}
					});
				}
			
			$scope.init();

	});
})

   