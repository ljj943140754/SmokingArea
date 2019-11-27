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
		  $scope.checklogin=function(){
				form.render('select');
				$.get("worker/checklogin.action",function(rs){
					
				});
				
			}
		
	 $scope.query=()=>{
		 let model = form.val("taskform");
//		 console.info("task中的model");
//		 console.info(model);
		 tb_instance.reload({
			 url: 'task/getTask.action',
			 where: { 
				 tsk_name: model.tsk_name
			 } 
		 });
	 }
	 $scope.redact=()=>{
		 var checkStatus = table.checkStatus('taskTable'); //idTest 即为基础参数 id 对应的值
		 var da =  checkStatus.data[0];
		 goToUrl( "#!/task_update",'get', JSON.stringify(da) );
	 }
	 
	 function goToUrl(url,method,data){
	        var form = document.createElement("form");
	        form.action = url;
	        form.method = method;
	        form.style.display = "none";
	        document.body.appendChild(form);
	        sessionStorage.setItem( "taskdata" , data);
	        form.submit();
	        return form;
	    }
	 
		//删除表格。
	    $scope.deleteById=function(){
			var checkStatus = table.checkStatus('taskTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				 layer.confirm('确认删除吗', function(index){
					 $.post("task/delete.action",da,function(res){
							layui.layer.msg("删除成功");
							//表格重载。
							 tb_instance.reload();
						});
				}); 
			}
		}
		
		//商品表格。
		 tb_instance = table.render({
			elem : '#taskTable',
			page: {
				limits: [5,10,15] , 
	            limit: 10 ,
	            groups: 1
			}, 
			request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
//                console.log(res);
  				return {
                      "code": res.code, //解析接口状态
                      "count": res.data.total,
                      "data": res.data.list //解析数据列表
                  };
              },
			url : 'task/getTask.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'tsk_name',
				title : '任务名称',
			},
			{
				field : 'tsk_time',
				title : '任务时间',
			}, {
				field : 'tsk_creation',
				title : "创建时间" ,
			}, {
				title : "操作",
				width:75,
				toolbar: '#barDemo'
			}] ]
		});
		 
		 table.on('tool(tasklist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			  var data = obj.data; //获得当前行数据
			  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
			 
			  if(layEvent === 'detail'){
					  if( data.tsk_time != undefined || data.tsk_time !=''){
						  console.info( "data.tsk_time" );
						  console.info( data.tsk_time );
						  var startnum = data.tsk_time.indexOf(":");
						  var endnum = data.tsk_time.indexOf(",");
						  var starttime = data.tsk_time.substring(startnum+1 , endnum);
						  var endtime = data.tsk_time.substring(endnum+5 , data.tsk_time.length);
						  data.tsk_time = "开始时间："+starttime+",结束时间:"+endtime
					  }
					  $scope.taskmodel = data ;
				  $.get("task-worker-detail/getWorkerDetail.action",data,function(res){
			    		 $scope.workerModel = res.data.list;
			    		 $scope.$apply();
				  });
				  $.get("task-route-detail/getRouteDetail.action",data,function(res){
					  	$scope.routemodel = res.data.list;
					  	$scope.$apply();
				  });
				  $scope.$apply();
				  console.log("");
			  } 
			});
		 $scope.checklogin();
	});
})