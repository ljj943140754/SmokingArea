(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table","laydate" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		var laydate = layui.laydate;
		  
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1' //指定元素
		  });
		
	 /* $scope.sitelist = [];
	   $.get("worker/getList.action",function(res){
			if(res.code == 0){
				$scope.sitelist = res.data;
				//angular重渲染
				$scope.$apply();
				//下拉框重载。
				form.render('select');
			}

		}) */
		
	 $scope.query=()=>{
		 let model = form.val("workerform");
		 console.info("worker中的model");
		 console.info(model);
			 tb_instance.reload({
				 url: 'worker/getList.action',
				 where: { 
					
				 } 
			 });
	 }
		 
		//删除表格。
	    $scope.deleteById=function(){
			var checkStatus = table.checkStatus('workerTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				console.info("da");
				 console.info(da);
				 layer.confirm('确认删除吗', function(index){
					 console.info("index");
					 console.info(index);
					 $.get("worker/deleteWorker.action",da,function(res){
							layui.layer.msg("删除成功");
							//表格重载。
							 tb_instance.reload();
						});
				}); 
			}
		}
		
		//商品表格。
		 tb_instance = table.render({
			elem : '#workerTable',
			method:"post",
			page: {
				limits: [5,10,15] , 
	            limit: 10 ,
			}, 
          
			url : 'worker/getList.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'wk_num',
				title : '工号',
			},
			{
				field : 'wk_name',
				title : '姓名',
			}, {
				field : 'wk_phone',
				title : "手机号" ,
			}, {
				field : 'wk_creation',
				title : "创建时间",	
			}] ]
		});

	});
})