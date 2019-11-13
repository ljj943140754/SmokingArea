(function($scope,$http ){ 
	layui.use([ "element", "form","layer", "jquery", "table" ],function(){
		let layer = layui.layer;
		$scope.checklogin=function(){
			$.get("worker/checklogin.action",function(rs){
				$scope.userinfo = rs.data;
				$scope.$apply();
			});
		}
		 
		$scope.loginsys=function(){
			if(!this.login || !this.login.id){
				layer.msg("请输入账号");
				return;
			}
			if(!this.login.password ){
				layer.msg("请输入密码");
				return;
			}
			if(!/^\d{1,}$/.test(this.login.id)){
				layer.msg("账号格式为数字");
				return;
			}
			console.info("this.login")
			console.info(this.login)
			$.get("worker/login.action",this.login,function(rs){
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