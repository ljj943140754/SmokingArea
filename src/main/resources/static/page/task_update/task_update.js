(function($scope,$http ){
	layui.use([ "element", "form", "jquery", "table","laydate" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		var laydate = layui.laydate;
		var thedata = {};
		var insertdata = {};
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1' //指定元素
		  });
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
			 $scope.updatemodel.tsk_leader_id = $scope.searchworkerlist[0].wk_num;
		 }
	 }
	 //通过领队ID获取领队名字
	 var leaderID = {};
	 $scope.getNameById=function(){
		 leaderID.wk_num = $scope.updatemodel.tsk_leader_id;
		 $.post("worker/getList.action",leaderID,function(res){
			 $scope.updatemodel.wk_name = res.data.list[0].wk_name;
    		 $scope.$apply();
		});
	 }
	 //选中下拉框中的值将值赋值到输入框中
	 $scope.getselect=()=>{
		var selectvalue = $("#searchworkerselect").val();
		if($scope.updatemodel != undefined ){
			$scope.updatemodel.wk_name = selectvalue;
		}
		}  
     //获取想要编辑的值
     $scope.getTaskData=function(){
    	 sessionStorage.removeItem('facilitydata');
    	 var taskdata = JSON.parse(sessionStorage.getItem("taskdata"));
    	 console.log("需要修改的巡更任务");
    	 console.log( taskdata );
    	 thedata = taskdata;
    	 $scope.updatemodel = thedata ;
    	 console.log("当前的数据task");
    	 console.log( $scope.updatemodel );
    	 $scope.$apply();
    	 if(taskdata==undefined||taskdata==''){
    		 layer.msg("数据无效，请在查询页面选择数据并点击编辑按钮");
    	 }
    	 $scope.getworker();
    	 $scope.getRoute();
     	 $scope.getNameById();
	 }
     //获取当前任务工作人员
     $scope.getworker=()=>{
    	 $.get("task-worker-detail/getWorkerDetail.action",thedata,function(res){
    		 $scope.workermembersModel = res.data.list;
    		 insertdata.wdt_worker_leader = res.data.list[0].wdt_worker_leader;
    		 $scope.$apply();
		});
     }
	   //添加任务工作人员
     $scope.addWorker=(item)=>{
    	 insertdata.wdt_worker_id = item.wk_num;
    	 insertdata.wdt_worker_name = item.wk_name;
    	 $.post("task-worker-detail/addTaskWorker.action",insertdata,function(res){
    		 $scope.getworker();
		});
     }
	     //查询现有的工作人员
    $scope.searchworkerbykeywords=()=>{
    	if( $scope.search_wk_name == undefined ||$scope.search_wk_name == '' ){
    		$scope.workerModel = undefined ;
    		return;
    }
    	formlist.wk_name = $scope.search_wk_name;
    	 $.post("worker/getList.action",formlist,function(res){
    		 $scope.workerModel = res.data.list;
    		 $scope.$apply();
		});
	 }
    
   //删除任务工作人员
     $scope.removeWorker=(item)=>{
    	 $.post("task-worker-detail/delTaskWorker.action",item,function(res){
    		 $scope.getworker();
		});
     }
     
     //更新按钮
	 $scope.saveTaskInfo=()=>{
		 $scope.getIDByName( $scope.updatemodel.wk_name , true);
		 console.log("获取更新后的巡更数据");
		 console.log($scope.updatemodel);
//    	 $.post("task/update.action",$scope.updatemodel,function(res){
//    		
//		});
	 }
	    var formlist = {};
	    //查询设施
	    $scope.searchtaskbykeywords=(e)=>{
	    	if( $scope.search_fy_name == undefined || $scope.search_fy_name ==''){
	    		$scope.facilityModel = undefined;
	    		return;
	    	}
	    	formlist.fy_name = $scope.search_fy_name;
	    	 $.get("facility/findFacility.action",formlist,function(res){
	    		 console.log("输入框搜索出的设施");
	    		 console.log(res);
	    		 for(var i = 0;i < res.data.list.length;i++){
	    			 //console.info(res.data.list[i]);
	    			 if(res.data.list[i].fy_type == '1'){
	    				 res.data.list[i].fy_type = "吸烟亭"
		    		 }else if(res.data.list[i].fy_type == '2'){
		    			 res.data.list[i].fy_type = "吸烟柱"
		    		 }else if(res.data.list[i].fy_type == '3'){
		    			 res.data.list[i].fy_type = "售烟点"
		    		 }
	    		 }
	    		 $scope.facilityModel = res.data.list;
	    		 $scope.$apply();
			});
		 }
	     //获取当前任务路线
	     $scope.getRoute=()=>{
	    	 $.get("task-route-detail/getRouteDetail.action",thedata,function(res){
	    		 $scope.routemodel = res.data.list;
	    		 $scope.$apply();
	    		 console.log("执行$scope.$apply()后");
			});
	     }
	     //添加任务路线
	     var RouteData = {};
	     $scope.addRoute=(index)=>{
	    	 var route = $scope.facilityModel[ index ];
	    	 RouteData.fy_name = route.fy_name;
	    	 RouteData.rdt_tsk_seq = route.seq;
	    	 RouteData.rdt_tsk_id = thedata.tsk_id;
	    	 RouteData.rdt_fac_id = route.fy_id;
	    	 $.ajax({
	    		   type: "POST",
	    		   url: "task-route-detail/addTaskRoute.action",
	    		   data: RouteData,
	    		   success: function(res){
	    			   $scope.getRoute();
	    		   }
	    		});
	     }
	     //删除任务路线
	     $scope.deleteRoute=(item)=>{
	    	 console.log("需要删除的任务路线");
	    	 console.log(item);
	    	 $.post("task-route-detail/delTaskRoute.action",item,function(res){
	    		 $scope.getRoute();
			});
	     }
	    //$scope.$apply();
	    $scope.getTaskData(); 
	});
})