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
				sessionStorage.removeItem('facilitydata');
				sessionStorage.removeItem('taskdata');
			}
		  $scope.checklogin();
	 $scope.query=()=>{
		 let model = form.val("taskReportform");
			 tb_instance.reload({
				 url: 'report/getReport.action',
				 where: { 
					// rp_creation:model.rp_creation,
					 rp_worker_id:model.rp_worker_id,
					 rp_state:model.rp_state,
				 } 
			 });
	 }
		 
		//删除表格。
	    $scope.deleteById=function(){
			var checkStatus = table.checkStatus('taskReportTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				 layer.confirm('确认删除吗', function(index){
					 $.get("report/deleteReport.action",da,function(res){
							layui.layer.msg("删除成功");
							//表格重载。
							 tb_instance.reload();
						});
				}); 
			}
		}
	    var reportID = {};
	    $scope.getNameById=function(ID){
	    	var  wk_name;
	    	reportID.wk_num = ID;
	    	$.ajax({
	    		   type: "POST",
	    		   url: "worker/getList.action",
	    		   data: reportID,
	    		   async : false,
	    		   success: function(res){
		    		if( res.data.list.length != 0 ){
		    			 wk_name =  res.data.list[0].wk_name;
		    		}
	    		   }
	    		});
	    	return wk_name;
		 }
		//商品表格。
		 tb_instance = table.render({
			elem : '#taskReportTable',
			page: {
				limits: [5,10,15] , 
	            limit: 10 ,
			}, 
			request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
  				return {
                      "code": res.code, //解析接口状态
                      "count": res.data.total,
                      "data": res.data.list //解析数据列表
                  };
              },
			url : 'report/getReport.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'rp_id',
				title : '任务报表ID',
			},
			{
				field : 'rp_title',
				title : '报告标题',
			}, {
				field : 'rp_content',
				title : "报告内容" ,
			}, {
				field : 'rp_vedio',
				title : "报告图片/VR",
				
			}, {
				field : 'rp_worker_id',
				title : "工作人员",
				templet:function(d){
				    return $scope.getNameById( d.rp_worker_id );
				    }
			}, {
				field : 'rp_creation',
				title : "报告时间",	
			}, {
				field : 'rp_state',
				title : "处理状态",	
				templet:function(d){
				    if(d.rp_state =="1")return "已处理";
				    else  if(d.rp_state =="0")return "未处理";
				    }
			}] ]
		});

	});
})