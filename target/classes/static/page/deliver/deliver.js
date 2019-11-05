(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table",'laydate' ],function() {
		let table = layui.table;
		var $ = layui.jquery;
		var layer = layui.layer;
		let ordertable = null;
		var laydate = layui.laydate;
		let delivertable = null;
		delivertable = table.render({
			elem : '#deliver_table',
			height : 200,
		    data:[],
			cols : [ [ //表头
				{ type : 'radio',width:40  },
				{field : 'status',title : '状态' ,width:100,
			    	templet:function(d){
			    		let status = d.status;
			    		 if(status == 1)return  "未出库";
						 else if(status == 2)return  "已出库";
						 else  if(status == 3)return  "已入库";
			    }},
				{ field : 'id',title : '发货单号',width:120  },
				{ field : 'goodz_total',title : '货件总数',width:100  },
				{ field : 'route_name',title : '路线'   },
				{ field : 'driver_name',title : '司机',width:100    },
				{ field : 'truck_name',title : '车辆' ,width:150   },
				{ field : 'truck_num',title : '车牌',width:120    },
			    {field : 'sendtime',title : '出库时间',width:120  },
			    {title : '操作', 
					fixed:"right",
					align:'center',
					toolbar:'#barDemo2',
					width:100
				} 
			    ] ]
		});
	//查询发货单详情。
	  $scope.query=function(){
		 let querymodel ={
				 id: $("#query_deliver_id").val(),
				 sendtime:$("#query_sendtime").val()
		 };
		 for(var k in querymodel)if(!querymodel[k])delete querymodel[k];
		 $.get("deliverrecord/getdetail.action",querymodel,function(res){
			 delivertable.reload({
				 data:res.data
			 });
		 })
	  }
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#sendtime' //指定元素
	  });
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#query_sendtime' //指定元素
	  });
	//路线列表(所有)
	$scope.routelist = [];
	$.get("route/all.action",function(res){
		$scope.routelist = res.data;
		console.info(res);
		$scope.$apply();
	})
	//车辆列表
	$scope.trucklist = [];
	$.get("truck/get.action",function(res){
		$scope.trucklist = res.data;
		console.info(res);
		$scope.$apply();
	})
	//驾驶员列表
	$scope.driverlist = [];
	$.get("driver/get.action",function(res){
		$scope.driverlist = res.data;
		console.info(res);
		$scope.$apply();
	})
	$scope.insert=function(){
		if(!$scope.insertmodel ){
			layer.msg("请选择路线");
			return;
		}
		if(!$scope.insertmodel.route_id){
			layer.msg("请选择路线");
			return;
		}
		$scope.insertmodel.sendtime = $("#sendtime").val();
		if(!$scope.insertmodel.sendtime){
			layer.msg("请出库时间");
			return;
		}
		if(!$scope.insertmodel.truck_id){
			layer.msg("请选择车辆");
			return;
		}
		if(!$scope.insertmodel.driver_id){
			layer.msg("请选择驾驶员");
			return;
		}
		console.info($scope.insertmodel );
		$.post("deliverrecord/insert.action",$scope.insertmodel,function(res){
			 if(res.code ==0)layer.msg(res.success);
			 else  if(res.code ==-1)layer.msg(res.error);
		})
	}
	$scope.insertReset=()=>{
		$scope.insertmodel = null;
	}
	
	//出库按钮。
	$scope.startDeliver=function(){
		var checkStatus = table.checkStatus('deliver_table'); //idTest 即为基础参数 id 对应的值
	    let data = checkStatus.data;
	    if(data.length > 0){
	    	data = data[0];
	    	if(data.status !=1 ){
	    		 return;
	    	}
	    	console.info(data);
	    	$.get("deliverrecord/deliver.action?id="+data.id,function(res){
	    		if(res.code ==0 ){
	    			layer.msg(res.success);
	    		}else{
	    			layer.msg(res.error);
	    		}
	    	
	    	});
	    }
	}
	//打印发货单按钮。
	$scope.printDeliver=function(){
		var checkStatus = table.checkStatus('deliver_table'); //idTest 即为基础参数 id 对应的值
	    let data = checkStatus.data;
	    if(data.length > 0){
	    	$scope.printitem = data[0];
	    	 $("#idcode").empty();
			 $("#idcode").qrcode({
					render: "canvas", //渲染方式 table|canvas
					width: 150, //宽度
					height:150, //高度
					text: $scope.printitem.id //内容
			});
			 $('#printeTemplate').show();
			 layer.open({
	    		 shade :0,
	    		 title:"发货单打印预览",
	    		 isOutAnim: true,
	    		 area: ['800px', '400px'],
	    		  type: 1,
	    		  content: $('#printeTemplate') , //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	    		  end(){
	    			  $('#printeTemplate').hide()
	    		  }
	    	 });
	    }
	}
	
	
	//监听订单详情按钮
	 table.on('tool(deliver_table)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
	   var da = obj.data; //获得当前行数据
	   var layEvent = obj.event;
	   if(layEvent === 'detail'){ //查看
		   console.info(  da );
		   //da={id 发货单,route_id 路线id,}
		   //1 查路线详情。
		    $.get("route/byid.action?id="+da.route_id,function(res){
		    	if(res.data ){ 
		    		if(res.data.length > 0 ){
		    			$scope.routesitelist = res.data[0].routesitelist;
		    		}else{
		    			$scope.routesitelist = [];
		    		}
			    	$scope.$apply();
		    	}
		    })
		   //2 查入库记录详情。
		   $.get("recrecord/get.action?deliver_id="+da.id,function(res){
			   $scope.recrecordlist = res.data;
			   $scope.$apply();
		   });
		    
		  //3 订单详情。根据发货单号查询订单列表。
		    $.get("deliverrecord/getorderbyid.action?id="+da.id,function(res){
				   $scope.orderlist = res.data;
				   $scope.$apply();
			   });
	   } 
	 });

	});
	
})