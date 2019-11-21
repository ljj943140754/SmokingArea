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
			$scope.checklogin=function(){
				form.render('select');
				$.get("worker/checklogin.action",function(rs){
					console.info( "faclility_insert中获取的登陆状态" );
					console.info( rs.data );
					formlist.tsk_createdby = rs.data.worker.wk_num ;
				});
				
			}
			
			 $scope.insert=()=>{
					let model = form.val("taskInsertForm");
					formlist.tsk_name = model.tsk_name;
					formlist.tsk_time = model.tsk_time;
					$.post("task/insert.action",formlist,function(res){
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

   