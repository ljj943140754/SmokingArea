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
			$scope.init=function(){
				form.render('select');
				sessionStorage.removeItem('facilitydata');
				sessionStorage.removeItem('taskdata');
			}
			
			 $scope.insert=()=>{
					let model = form.val("workerInsertform");
					$.post("worker/insert.action",model,function(res){
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

   