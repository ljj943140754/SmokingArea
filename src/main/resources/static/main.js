//1 layui初始化
layui.use([ "element"  ], function() { });

//2 angular初始化
const app = angular.module('Express_Sys',['ngRoute']);

//3 页面配置。
const pages={
		config:[
				["/address","page/address/address","addressCtrl"],
			 	["/home","page/home/home","homeCtrl"],
				["/site","page/site/site","siteCtrl"],
				["/worker","page/worker/worker","workerCtrl"],
				["/mailbox","page/mailbox/mailbox","mailboxCtrl"],
				["/route","page/route/route","routeCtrl"],
				["/driver","page/driver/driver","driverCtrl"],
				["/truck","page/truck/truck","truckCtrl"],
				["/order","page/order/order","orderCtrl"],
				["/deliver","page/deliver/deliver","deliverCtrl"] 
			]
};


//4路由配置。
app.config(['$routeProvider', function($routeProvider){
	for(var i=0;i<pages.config.length;i++){
		var conf = pages.config[i];
		(function(){
			let uul = conf[1];
			$routeProvider.when(conf[0],{
		    	templateUrl:conf[1]+".html",
		    	controller:conf[2] ,
		        resolve:{
		        	url:function(){
		        		return uul;
		        	}
		        } 
		    })
		})();
	}
    $routeProvider.otherwise({redirectTo:'/home'});
}]);

//5 Controller配置
for(var i=0;i<pages.config.length;i++){
	var conf = pages.config[i];
	app.controller(conf[2], function( $scope,$http ){
		 //加载一个js  并把$scope传递给它。
		if($scope.$resolve){
			require($scope.$resolve.url+".js",arguments );
		}
	});
}

//6 动态加载js 
function require(path,args){
	 layui.use(["jquery"],function(){
		var jq = layui.jquery;
		jq.getScript(path, function(data, status, jqxhr) {
			 let callback = eval(data);
			 callback.apply(null,args);
			 args[0].$apply();
		});
	});
}

//7 扩展jQuery的ajax请求。实现统一的错误拦截和提示。
let getOption=(args)=>{
	let op={};
	op.url = args[0];
	if(args.length ==2){
		if(typeof args[1] =="function"){
			op.success=args[1];
		}else{
			op.data=args[1];
		}
	}else if(args.length ==3){
		op.data=args[1];
		op.success=args[2];
	}
	return op;
}
let serviceErrorTip=(error)=>{
	 layer.open({
		 title:"提示",
		 type: 1,
		 content:`<div style="margin:20px;max-width:400px;max-height:400px;overflow:auto;">${error}</div>` 
	 });
	 
}
let sendComplete=( option )=>{
	 let _option = option;  
	  //包装一下。                
	  let _op={         
	     success(rs){
	    	 //统一提示错误。
			 if(rs && rs.code ==-1 ){
				 serviceErrorTip(rs.error);
				 return;
			 }
			 //调用开发者定义的回调函数。
			 if(rs && rs.code == 0 &&  typeof _option.success == "function" ){
				 return  _option.success.call(null, rs);
			 }
	     },
	     error(rs){
	    	 //请求异常时的错误提示。
	    	 let error = rs.responseJSON.error;
	    	 let message = rs.responseJSON.message;
	    	 let path = rs.responseJSON.path;
	    	 let status = rs.responseJSON.status;
	    	 let html =`
	    	<table style="margin:40px auto;">
		    	<tr  >
					<td width=60>错误</td>
				    <td   >${error}</td>
				</tr>
				<tr    >
					<td width=60>原因</td>
				    <td    >${message}</td>
				</tr>
				<tr    >
					<td width=60>路径</td>
				    <td  >${path}</td>
				</tr>
				<tr   >
					<td width=60>响应码</td>
				    <td   >${status}</td>
				</tr>
	    	</table>
	    	 `;
	    	 layer.open({
	    		 title:"请求错误提示",
	    		 area: ['400px', '200px'],
	    		  type: 1,
	    		  content: html 
	    	 });
	    	 
	    	 //调用开发者定义的回调函数。
			 if(typeof _option.error == "function" ){
				 return  _option.error.call(null, rs);
			 }
	     } 
	  };
	  //3 合并参数
	  for (var k in option){
	    if (k !="success" && k !="error" ){
	         _op[k] = option[k];
	    }
	  }
	  //发送真实的ajax请求。
	  $.ajax(_op);
}
 
$.extend({
	$post(){
		let op = getOption(arguments);
		op.type = "POST";
		return sendComplete(op);
	},
	$get(){
		let op = getOption(arguments);
		op.type = "GET";
		return sendComplete(op);
	},
	$ajax(op){
	  return sendComplete(op);
	}
});