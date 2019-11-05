(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table" ],function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		
		
		$scope.sitelist = [];
		$.get("site/getall.action",function(res){
			if(res.code == 0){
				$scope.sitelist = res.data;
				//angular重渲染
				$scope.$apply();
			}
		})
		
		
		$scope.queryModel = [];
		 
		
		let getModel=()=>{
			return {
					name:$scope.name,
				    tel:$scope.tel,
				   
				 };
		}
		//添加
		$scope.insert=function(){
			 let model = getModel();
			 if(!model.name){
				 layer.msg("请填写驾驶员姓名");
				 return;
			 }
			 if(!model.tel){
				 layer.msg("请填写电话号码");
				 return;
			 }
			
			 $.post("driver/insert.action",model,function(res){
			    if( res.code == 0 ){
			    	layui.layer.msg("添加成功");
			    }
			}); 
		}
		
		//查询按钮
		$scope.query=()=>{
			let model = getModel();
			let query_model={};
			for(var k in model ){
				if( model[k] ){
					query_model[k] = model[k];
				}
			}
			console.info( query_model );
			 $.get("driver/get.action",query_model,(data)=>{
				 $scope.queryModel = data.data;
				 $scope.$apply();
			 });
		}
		//删除表格。
		$scope.deleteItem=function(index){
			let item = $scope.queryModel[index];
			$scope.queryModel.splice(index,1);
			deleteById(item.id);
		}
		let deleteById=id=>{
			$.post("driver/delete.action?id="+id,(res)=>{
				if(res.code == 0){
					layer.msg(res.success);
				}
			});
		}
	});
})