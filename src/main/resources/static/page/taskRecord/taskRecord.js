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
		
	  $scope.redact=()=>{
			 var checkStatus = table.checkStatus('facilityTable'); //idTest 即为基础参数 id 对应的值
			 var da =  checkStatus.data[0];
			 goToUrl( "#!/facility_update",'get', JSON.stringify(da) );
		 }
		 
	 function goToUrl(url,method,data){
	        var form = document.createElement("form");
	        form.action = url;
	        form.method = method;
	        form.style.display = "none";
	        document.body.appendChild(form);
	        sessionStorage.setItem( "facilitydata" , data);
	        form.submit();
	        return form;
	    }
	 $scope.query=()=>{
		 let model = form.val("facilityform");
			 tb_instance.reload({
				 url: 'taskrecord/getTaskRecord.action',
				 where: { 
					 tr_worker_leader:model.tr_worker_leader,
					 tr_iscomplete:model.tr_iscomplete,
					 tr_creation:model.tr_creation,
					// fy_creation:model.fy_creation,
				 } 
			 });
	 }
		 
		//删除表格。
	    $scope.deleteById=function(){
			var checkStatus = table.checkStatus('facilityTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				 layer.confirm('确认删除吗', function(index){
					 $.get("facility/delete.action",da,function(res){
							layui.layer.msg("删除成功");
							//表格重载。
							 tb_instance.reload();
						});
				}); 
			}
		}
	    //通过ID获取工作人员姓名
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
	  //通过ID获取巡更任务名称
	    var RecordID = {};
	    $scope.getTSKNameById=function(ID){
	    	var  tsk_name;
	    	RecordID.tsk_id = ID;
	    	$.ajax({
	    		   type: "GET",
	    		   url: "task/getTask.action",
	    		   data: RecordID,
	    		   async : false,
	    		   success: function(res){
		    		if( res.data.list.length != 0 ){
		    			tsk_name =  res.data.list[0].tsk_name;
		    		}
	    		   }
	    		});
	    	return tsk_name;
		 }
		//商品表格。
		 tb_instance = table.render({
			elem : '#taskRecordTable',
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
			url : 'taskrecord/getTaskRecord.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'tr_id',
				title : '任务记录ID',
			},
			{
				field : 'tr_task_id',
				title : '任务名',
				templet:function(d){
				    return $scope.getTSKNameById( d.tr_task_id );
				    }
			}, {
				field : 'tr_worker_leader',
				title : "领队人员" ,
				templet:function(d){
				    return $scope.getNameById( d.tr_worker_leader );
				    }
			}, {
				field : 'tr_creation',
				title : "进度时间",	
			}, {
				field : 'tr_iscomplete',
				title : "完成状态",	
				templet:function(d){
				    if(d.tr_iscomplete =="0")return "未完成";
				    else  if(d.tr_iscomplete =="1")return "已完成";
				    }
			}] ]
		});

	});
})