(function($scope,$http ){ 
	layui.use([ "element", "form","layer", "jquery", "table" ],function(){
		let layer = layui.layer;
		$scope.checklogin=function(){
			$.$get("worker/checklogin.action",function(rs){
				$scope.userinfo = rs.data;
				$scope.$apply();
			});
		}
		
		$scope.formateWorkerType=function(type){
			if(type ==1)return "快递员";
			else if(type == 2)return "仓库管理员";
			else if( type ==3 )return "调度员";
		}
		 
		$scope.loginsys=function(){
			if(!this.login || !this.login.wk_num){
				layer.msg("管理员账号");
				return;
			}
			if(!this.login.wk_password ){
				layer.msg("请输入密码");
				return;
			}
			if(!/^\d{1,}$/.test(this.login.wk_num)){
				layer.msg("工号格式为数字");
				return;
			}
			$.$post("worker/login.action",this.login,function(rs){
				$scope.userinfo = rs.data;
				$scope.$apply();
			})
		}
		
		$scope.loginout=function(){
			$.get("worker/logout.action",function(rs){
				$scope.userinfo = null;
				$scope.$apply();
			})
		}
		
		$scope.checklogin();
	 
		
	});
 
	

    
})