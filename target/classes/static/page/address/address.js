(function($scope,$http ){
	var $ = layui.jquery;
	layui.use([ "element", "form", "jquery", "table" ],function() {
		var form = layui.form;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		
		
		$scope.insert=function(){
			 $.post("address/insert.action",$scope.insertModel,function(){
				layui.layer.msg("添加成功");
				//表格重载。
				 tb_instance.reload();
			}); 
		 
		}
	 
		 $scope.query=function(){
			 searchBaiduMap( $scope.name_input);
		} 
		
	  
		$scope.selectItem=function(e){
			let li = $(e.target).parents("#r-result li");
			let lis = $("#r-result").find("li");
			for(let i=0;i<lis.length;i++){
				if( li.get(0) == lis.eq(i).get(0) ){
					setItem(i);
					break;
				}
			}
		}
		
			function setItem(i){
				$scope.insertModel={
					name:search_rs[i].title,
					address:search_rs[i].address,
					lat:search_rs[i].point.lat,
					lng:search_rs[i].point.lng
				}
				form.val("addressForm",{
					name:$scope.insertModel.name,
				});
			}
			
		 
		//商品表格。
		 tb_instance = table.render({
			elem : '#addressTable',
			height : 600,
			url : 'address/getall.action', //数据接口
			cols : [ [ //表头
			  {
				field : 'name',
				title : '名称' ,
				width:150
			 
			}, {
				width:200,
				field : 'address',
				title : '详细地址'  
			},
			{
				title : '操作', 
				width:120,
				fixed:"right",
				align:'center',
				toolbar:'#barDemo'
			} ] ]
		});
		 
		//监听工具条 
		 table.on('tool(addresslist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		   var da = obj.data; //获得当前行数据
		   var layEvent = obj.event;
		   if(layEvent === 'detail'){ //查看
			   console.info(da);
			   showMap(da.lng,da.lat);
		   } else if(layEvent === 'del'){ //删除
			   layer.confirm('确认删除吗', function(index){
				   $.post("address/deletebyid.action",{id:da.id},function(){
						layui.layer.msg("删除成功");
						//表格重载。
						 tb_instance.reload();
					});
				   layer.close(index);
			    }); 
		   }  
		 });

	});
	
	
	
	var map = new BMap.Map("baidumap");       
	map.enableScrollWheelZoom(true);//允许滚轮缩放。
	let search_rs = [];
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 18);
	var local = new BMap.LocalSearch(map, {
		 renderOptions: {map: map, panel: "r-result"},
		 onSearchComplete: function(results){
			// 判断状态是否正确
			if (local.getStatus() == BMAP_STATUS_SUCCESS){
				search_rs = [];
				for (var i = 0; i < results.getCurrentNumPois(); i ++){
					let posi = results.getPoi(i);
					search_rs.push(posi);
				}
			}
		}
	});
	function searchBaiduMap(name){
		local.search(name);
	}
	function showMap(lng,lat){
		map.clearOverlays(); 
		var new_point = new BMap.Point(lng, lat);
		var marker = new BMap.Marker(new_point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(new_point);   
	}
})