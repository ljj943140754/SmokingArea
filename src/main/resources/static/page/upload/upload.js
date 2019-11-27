(function($scope,$http ){
	$scope.uploadfinish = true;
	layui.use([ "element", "form", "jquery", "table" ],function() {
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		let tb_instance = null;
		let input = $("#uploadinput").get(0);
		
		$scope.startupload=()=>{
			if( input.files.length > 0){
				$.$get("resource/policy.action",{srcname:input.value,callbackhost:"http://testforyou.top"},(rs)=>{
					let policy = rs.data;
					let formdata = new FormData();
					formdata.append("policy",policy.policy);//policy 策略
					formdata.append("OSSAccessKeyId","LTAI4FhR1cQLXY2vQRRhkXL8");//OSSAccessKeyId  LTAI4FhR1cQLXY2vQRRhkXL8
					formdata.append("signature",policy.signature);//signature 
					formdata.append("success_action_status",200);//success_action_status 200 
					formdata.append("callback",policy.callback);
					formdata.append("key", policy.key );//key 带有目录了。 
					formdata.append("file",input.files[0]);
					$scope.uploadfinish = false;
					$scope.$apply();
					uploadFile( formdata ,policy.host );
				});
			}else{
				layer.msg("请先选择文件");
			}
			
		}
		 
		let uploadFile=( formdata,serverurl )=>{
			
			$.ajax({
				url:serverurl,
				method:"POST",
				cache: false,
		        data: formdata,
		        processData: false,
		        contentType: false,
		        dataType:"json",
				success(rs){
					layer.msg(rs.success);
					$scope.uploadfinish = true;
					$scope.$apply();
					
					//刷新表格。
					tb_instance.reload();
				},
				error(rs){
					layer.msg("上传失败");
				}
			});
		}
	  
		 tb_instance = table.render({
			elem : '#table',
			url : 'resource/getbypage.action', 
			where:{},
			limit:15,
			limits:[15,25,30,50],
			request: {
			    pageName: 'page' //页码的参数名称，默认：page
			    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
		   },
		   page:true,
		   done: function(rs, curr, count){
			   if(rs.code ==-1){
				   layer.msg(rs.error);
			   }    
		   },
			cols : [ [ //表头
			{
				field : 'rs_url',
				title : '缩略图',
				height:100,
				width:150,
				templet:function(rowdata){
					return "<img src="+rowdata.rs_url+"  style='width:100px;height:100px;' />";
				}
			},
			{
				field : 'rs_url',
				title : '路径' 
			},
			
			{
				field : 'rs_creation',
				title : "创建时间",
				width:200
			}
			] ]
		});
		 
		 

	});
	 
})