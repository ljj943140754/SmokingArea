(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table","laydate" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		var laydate = layui.laydate;
		  
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1' //指定元素
		  });
		

	 $scope.insert=()=>{
		 let model = form.val("facilityform");
		 console.info("model---------------");
		 console.info(model);
		 $.post("facility/insert.action",model,function(res){
			    if( res.code == 0 ){
			    	layui.layer.msg("添加成功");
			    }
		 }); 
	 }
		

	});
})