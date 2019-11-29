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
		  
		  $scope.inint=function(){
			  sessionStorage.removeItem('facilitydata');
			  sessionStorage.removeItem('taskdata');
			}
	 $scope.query=()=>{
		 let model = form.val("workerform");
//		 console.log("model.wk_creation");console.log( model.wk_creation );
//		 if( model.wk_creation =='' ){
//			 console.log("model.wk_creation的值为空字符串");
//		 }
		 if(  model.wk_creation !=''  ){
			 tb_instance.reload({
				 url: 'worker/getList.action',
				 where: { 
					 wk_name:model.wk_name,
					 wk_phone:model.wk_phone,
					 wk_num:model.wk_num,
					 wk_creation:model.wk_creation,
				 } 
			 });
		 }else{
			 tb_instance.reload({
				 url: 'worker/getList.action',
				 where: { 
					 wk_name:model.wk_name,
					 wk_phone:model.wk_phone,
					 wk_num:model.wk_num,
				 } 
			 });
		 }
	 }
		 
		//删除表格。
	    $scope.deleteById=function(){
			var checkStatus = table.checkStatus('workerTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				 layer.confirm('确认删除吗', function(index){
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
			page:{
				limits:[3,5,10],
				groups : 1 ,
				prev : "上一页" ,
				next : "下一页"
			},
			url : 'worker/getList.action', //数据接口
			request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
			parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
				return {
                    "code": res.code, //解析接口状态
//                    "msg": res.msg, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            },
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
		 $scope.inint();
	});
})