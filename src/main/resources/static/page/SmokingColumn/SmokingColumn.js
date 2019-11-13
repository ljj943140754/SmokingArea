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
			 var model = form.val("workform");
			 if(!model.name){
				 layer.msg("请填写员工姓名");
				 return;
			 }
			 if(!model.tel){
				 layer.msg("请填写联系方式");
				 return;
			 }
			 $.post("worker/insert.action",model,function(){
				layui.layer.msg("添加成功");
				//表格重载。
				tb_instance.reload();
			}); 
			  
		}
		 
		 
		//删除表格。
		$scope.deleteById=function(){
			var checkStatus = table.checkStatus('workerTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				 layer.confirm('确认删除吗', function(index){
					 $.post("worker/deletebyid.action",{id:da.id},function(){
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
			height : 600,
			url : 'worker/all/detail.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'id',
				title : '创建者id',
				width : 140 
			},
			{
				field : 'name',
				title : '位置名称',
				width : 140 
			}, {
				field : 'tel',
				title : "位置详细" 
			 
			}, {
				field : 'site_id',
				title : "创建时间",
				templet:function(d){
				return d.site.name;
				}
			}, 
			{
				field : 'site_id',
				title : "最后更新时间",
				templet:function(d){
				return d.site.name;
				}
			},
			{
				field : 'site_id',
				title : "最后更新人",
				templet:function(d){
				return d.site.name;
				}
			},{
				field : 'type',
				title : "是否启用",
				templet:function(d){
			    if(d.type =="1")return "启用";
			    else  if(d.type =="2")return "删除";
			    }
			}] ]
		});

	});
})