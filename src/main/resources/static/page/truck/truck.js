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
					site_id:$scope.site_id,
				    num:$scope.num,
				    description:$scope.description
				 };
		}
		//添加
		$scope.insert=function(){
			 let model = getModel();
			 if(!model.name){
				 layer.msg("请填写车辆名称");
				 return;
			 }
			 if(!model.num){
				 layer.msg("请填写车牌号");
				 return;
			 }
			 if(!model.site_id){
				 layer.msg("请选择站点");
				 return;
			 }
			
			 $.post("truck/insert.action",model,function(res){
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
			 $.get("truck/get.action",model,(data)=>{
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
			$.post("truck/delete.action?id="+id,(res)=>{
				if(res.code == 0){
					layer.msg(res.success);
				}
			});
		}
	});
})