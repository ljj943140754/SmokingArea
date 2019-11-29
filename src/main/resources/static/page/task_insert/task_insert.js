(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table","laydate" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		var laydate = layui.laydate;

		 laydate.render({
				elem: '#test1' //指定元素
			});
		 var formlist = {};
			$scope.checklogin=function(){
				$.get("worker/checklogin.action",function(rs){
					console.info( "faclility_insert中获取的登陆状态" );
					console.info( rs.data );
					formlist.tsk_createdby = rs.data.worker.wk_num ;
				});
				sessionStorage.removeItem('facilitydata');
				sessionStorage.removeItem('taskdata');
			}
			
			//搜索领队 
			 var leaderName = {};
			 $scope.searchworker=(value)=>{
				 $scope.getIDByName( value , false);
			 }
			 //通过领队名字模糊查询并获取ID
			 $scope.getIDByName=function(value,bo){
				 leaderName.wk_name = value ;
				 $.post("worker/getList.action",leaderName,function(res){
		    		 $scope.searchworkerlist = res.data.list;
		    		 $scope.$apply();
				});
				 if(bo){
					 $scope.insertmodel.tsk_leader_id = $scope.searchworkerlist[0].wk_num;
				 }
			 }
			
			 //选中下拉框中的值将值赋值到输入框中
			 $scope.getselect=()=>{
				if($scope.insertmodel != undefined ){
					var inputvalue = $scope.insertmodel.tsk_leader_name ;
					var selectvalue = $("#searchworkerselect").val();
					if( inputvalue!=undefined || inputvalue!=''){
						if( selectvalue!= ''){
							$scope.insertmodel.tsk_leader_name = selectvalue;
							$scope.getIDByName( selectvalue , true);
						}else{
							$scope.getIDByName( inputvalue , false);
						}
					}
				}
				}  
			
			
			 $scope.insert=()=>{
					$.post("task/insert.action",$scope.insertmodel,function(res){
						if(res.code == -1){
							layui.layer.msg(res.error);
						}else{
							layui.layer.msg(res.success);
						}
					});
				}
			$scope.checklogin();
	});
})

   