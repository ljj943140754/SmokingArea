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
		
		$scope.sitelist = [];
		$.get("facility/findFacility.action",function(res){
			if(res.code == 0){
				$scope.sitelist = res.data;
				//angular重渲染
				$scope.$apply();
				//下拉框重载。
				form.render('select');
			}

		})
		
	/*	$scope.query=function(){
			 var model = form.val("facilityform");
			 console.info("model");
			 console.info(model);
			 if(!model.name){
				 layer.msg("请填写员工姓名");
				 return;
			 }
			 if(!model.tel){
				 layer.msg("请填写联系方式");
				 return;
			 }
			 $.get("facility/findFacility.action",model,function(){
				layui.layer.msg("添加成功");
				//表格重载。
				//tb_instance.reload();
			}); 
			  
		}*/
	 $scope.query=()=>{
		 let model = form.val("facilityform");
			 tb_instance.reload({
				 url: 'facility/findFacility.action',
				 where: { 
					 fy_isdel: model.fy_isdel,
					 fy_name: model.fy_name,
					 fy_type: model.fy_type
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
					 $.get("facility/delete.action",da,function(){
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
			page: {
				limits: [5,10,15] , 
	            limit: 10 ,
			}, 
          
			url : 'facility/findFacility.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'fy_createdby',
				title : '创建者id',
			},
			{
				field : 'fy_lat',
				title : '维度',
			}, {
				field : 'fy_lng',
				title : "经度" ,
			}, {
				field : 'fy_type',
				title : "类型",	
			    templet:function(d){
			    	if(d.fy_type =="1")return "吸烟亭";
			    	else  if(d.fy_type =="2")return "吸烟柱";
			    	else  if(d.fy_type =="3")return "售烟点";
			    }
			}, 
			{
				field : 'fy_name',
				title : "位置名称",	
			}, 
			{
				field : 'fy_detail',
				title : "位置明细",	 
			}, 
			{
				field : 'fy_res_link',
				title : "VR/封面",	 
			}, 
			{
				field : 'fy_creation',
				title : "创建时间",	 
			}, 
			{
				field : 'fy_lastupdated',
				title : "最后更新时间",
				width :120,
			},
			{
				field : 'fy_lastupdatedby',
				title : "最后更新人",
				width :120,	
			},{
				field : 'fy_isdel',
				title : "是否启用",
				templet:function(d){
			    	if(d.fy_isdel =="1")return "启用";
			    	else  if(d.fy_isdel =="0")return "未启用";
			    }
			}] ]
		});

	});
})