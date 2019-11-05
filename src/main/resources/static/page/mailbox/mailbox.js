(function($scope,$http ){
	var $ = layui.jquery;
	layui.use([ "element", "form", "jquery", "table" ],function() {
		var form = layui.form;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		
		$scope.sitelist = [];
		$.get("site/getall.action",function(res){
			if(res.code == 0){
				$scope.sitelist = res.data;
				//angular重渲染
				$scope.$apply();
				//下拉框重载。
				form.render('select');
			}
		})
		
		$scope.insert=function(){
			let data = form.val("mailboxForm");
			$.post("mailbox/insert.action",data,function(){
				layui.layer.msg("添加成功");
				//表格重载。
			}); 
		}
		
	  //查询地点下拉框选择事件 
	   form.on('select(querySelect)', function(data){
		   tb_instance.reload({
			   url : 'mailbox/list/'+data.value
		   });
	   });  
	  
		//商品表格。
		 tb_instance = table.render({
			elem : '#addressTable',
			height : 600,
			url : 'mailbox/list/0', //数据接口
			cols : [ [ //表头
				{
					field : 'id',
					title : '邮筒编号' , 
					width:100
				},
			  {
				field : 'name',
				title : '名称' ,
				width:150
			},
			  {
				field : 'lng',
				title : '经度' ,
				width:120
			},
			{
				field : 'lat',
				title : '纬度' ,
				width:120
			},
			{
				title : '操作', 
				fixed:"right",
				align:'center',
				toolbar:'#barDemo' 
				 
			} ] ]
		});
		 
		//监听工具条 
		 table.on('tool(mailboxtable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		   var da = obj.data; //获得当前行数据
		   var layEvent = obj.event;
		   if(layEvent === 'detail'){ //查看
			   console.info(da);
			   showMap(da.lng,da.lat);
		   } else if(layEvent === 'del'){ //删除
			   layer.confirm('确认删除吗', function(index){
				   $.post("mailbox/delete/"+da.id,function(){
						layui.layer.msg("删除成功");
						//表格重载。
						 tb_instance.reload();
					});
				   layer.close(index);
			    }); 
		   } else if(layEvent === "printcode"){
			    $("#printeTemplate").empty();
				 $("#printeTemplate").qrcode({
						render: "canvas", //渲染方式 table|canvas
						width: 150, //宽度
						height:150, //高度
						text: "123" //内容
				});
				 
				 $('#printeTemplate').show();
				 layer.open({
		    		 shade :0,
		    		 title:"打印预览",
		    		 isOutAnim: true,
		    		 area: ['200px', '200px'],
		    		  type: 1,
		    		  content: $('#printeTemplate') , //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		    		  end(){
		    			  $('#printeTemplate').hide()
		    		  }
		    	 });
		   }
		 });

	});
	
	
	
	var map = new BMap.Map("baidumap");       
	map.enableScrollWheelZoom(true);//允许滚轮缩放。
	let search_rs = [];
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 18);
	var local = new BMap.LocalSearch(map, {});
	
	function showInfo(e){
		$scope.lng = e.point.lng;
		$scope.lat = e.point.lat;
		$scope.$apply();
		showMap($scope.lng ,$scope.lat);
	}
	map.addEventListener("click", showInfo);
	
	 
	function showMap(lng,lat){
		map.clearOverlays(); 
		var new_point = new BMap.Point(lng, lat);
		var marker = new BMap.Marker(new_point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(new_point);   
	}
})