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
     //获取想要编辑的值
     $scope.getTaskData=function(){
    	 var taskdata = JSON.parse(sessionStorage.getItem("taskdata"));
    	 console.info( taskdata );
    	 console.info( taskdata.tsk_name );
	 }
     
	 $scope.saveTaskInfo=()=>{
		 console.info("$scope.updatemodel---------");
		 console.info($scope.updatemodel);
		 
	 }
	    var formlist = {};
	    //查询设施
	    $scope.searchtaskbykeywords=(e)=>{
	    	if( $scope.search_fy_name == undefined)return;
	    	formlist.fy_name = $scope.search_fy_name;
	    	 $.get("facility/findFacility.action",formlist,function(res){
	    		 for(var i = 0;i < res.data.length;i++){
	    			 console.info(res.data[i]);
	    			 if(res.data[i].fy_type == '1'){
	    				 res.data[i].fy_type = "吸烟亭"
		    		 }else if(res.data[i].fy_type == '2'){
		    			 res.data[i].fy_type = "吸烟柱"
		    		 }else if(res.data[i].fy_type == '3'){
		    			 res.data[i].fy_type = "售烟点"
		    		 }
	    		 }
	    		 $scope.facilityModel = res.data;
			});
		 }
	    //查询工作人员
	    $scope.searchworkerbykeywords=()=>{
	    	if( $scope.search_wk_name == undefined)return;
	    	formlist.wk_name = $scope.search_wk_name;
	    	 $.post("worker/getList.action",formlist,function(res){
	    		 console.info(res.data);
	    		 $scope.workerModel = res.data;
			});
		 }
	    $scope.getTaskData(); 
	});
})