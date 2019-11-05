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
				if(res.data && res.data.length > 0 ){
					$scope.query_site_id = res.data[0].id;
				}
				//angular重渲染
				$scope.$apply();
				//下拉框重载。
				form.render('select');
			}
		})
		 
		
	  //查询地点下拉框选择事件 
	   form.on('select(querySelect)', function(data){
		   $scope.query_site_id = data.value;
	   });  
	   
	   $scope.query_site_id = null;
	   $scope.query=function(){
		   if($scope.query_site_id){
			   tb_instance.reload({
				   url : 'route/all.action'
			   });
		   }
	   };
	   
	   //1路线表格初始化
		 tb_instance = table.render({
			elem : '#routeTable',
			height : 300,
			url : 'route/all.action', //数据接口
			cols : [ [ //表头
			   {field : 'name',
				title : '路线名称'  
			   },
			   {field : 'count',
				title : '站点个数' ,
				width:120,
				templet:function(d){
				    if(d.routesitelist){
				    	return d.routesitelist.length;
				    }
				    return 0;
				}
			   },
			   {title : '操作', 
				fixed:"right",
				align:'center',
				toolbar:'#barDemo',
				width:160
				} ] ]
		});
		
		 $scope.routesitelist = [];
		//监听路线表格工具条 
		 table.on('tool(routetable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		   var da = obj.data; //获得当前行数据
		   var layEvent = obj.event;
		   if(layEvent === 'detail'){ //查看
			   console.info( da.routesitelist );
			   $scope.routesitelist  = da.routesitelist ;
			   $scope.$apply();
		   } else if(layEvent === 'del'){ //删除
			   layer.confirm('确认删除吗', function(index){
				   $.get("route/delete.action",{id:da.id},
						   function(res){
						   location.reload();
					});
				   layer.close(index);
			    }); 
		   }  
		 });
		 
		 
		//监听路线详情工具条 
		 $scope.showDetail=(lng,lat)=>{
			 showMap(lng,lat);
		 }
		
		 $scope.insertModel=[];
		 //加入路线。
		 $scope.joinList=function(){
			//序号和站点id。
			//routeForm
			 let data = form.val("routeForm");
			 //加上name
			 for(var k in $scope.sitelist){
				 if($scope.sitelist[k].id == data.site_id){
					 data.name = $scope.sitelist[k].name;
					 break;
				 }
			 }
			 $scope.insertModel.push(data);
		 }
		 
		 $scope.deleteItem= index =>{
			 $scope.insertModel.splice( index ,1);
		 }
		 //保存添加路线
		 $scope.insert=function(){
			//console.info( this.insertModel );
			//console.info( this.name);
			let insertMode={
				name: this.name,
				routesitelist:[]
			};
			 this.insertModel.map((it)=>{
				 insertMode.routesitelist.push({site_id:it.site_id,seq:it.seq});
			 });
			 $.ajax({
				 url:"route/insert.action",
				 method:"POST",
				 contentType:"application/json",
				 data:JSON.stringify( insertMode ),
				 success(res){
					  if(res.code ==0 ){
						  layer.msg(res.success);
						  location.reload();
					  }else{
						  layer.msg(res.error);
					  }
				 }
			 });
		 }
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