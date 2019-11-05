(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		
		$scope.addresslist = [];
		$.get("address/getall.action",function(res){
			if(res.code == 0){
				$scope.addresslist = res.data;
				//angular重渲染
				$scope.$apply();
				//下拉框重载。
				form.render('select');
			}
		})
		
		$scope.insert=function(){
			 var item = form.val("siteform");
			 $.post("site/insert.action",item,function(){
				layui.layer.msg("添加成功");
				//表格重载。
				tb_instance.reload();
			}); 
			 
		}
		 
		 
		//删除表格。
		$scope.deleteAddr=function(){
			var checkStatus = table.checkStatus('siteTable'); //idTest 即为基础参数 id 对应的值
			if( checkStatus.data.length > 0){
				var da =  checkStatus.data[0];
				layer.confirm("",);
				layer.confirm('确认删?', {icon: 3, title:'提示'}, function(index){
					$.post("site/deletebyid.action",{id:da.id},function(){
						layui.layer.msg("删除成功");
						//表格重载。
						 tb_instance.reload();
					});
					layer.close(index);
				});
			}
		}
		//商品表格。
		 tb_instance = table.render({
			elem : '#siteTable',
			height : 600,
			url : 'site/getall.action', //数据接口
			cols : [ [ //表头
			{
				 type:"radio",
				 width:40
			}, 
			{
				field : 'id',
				title : '地址编号',
				width : 200,
				sort : true 
			}, {
				field : 'name',
				title : '地址' 
			 
			}] ]
		});

	});
})