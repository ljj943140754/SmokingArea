(function($scope,$http ){ 
	layui.use([ "element", "form","layer", "jquery", "table" ],function(){
		let layer = layui.layer;
		$scope.checklogin=function(){
			$.get("worker/checklogin.action",function(rs){
				console.info("检查登陆时返回的rs");
				console.info(rs);
				if(rs.code == 0){
					$scope.userinfo = rs.data.worker;
					if($scope.userinfo.wk_type == "admin"){
						$scope.userinfo.wk_type = "管理员"
					}
					$scope.$apply();
				}
			});
			sessionStorage.removeItem('facilitydata');
			sessionStorage.removeItem('taskdata');
		}
		 
		$scope.loginsys=function(){
			if(!this.login || !this.login.wk_num){
				layer.msg("请输入账号");
				return;
			}
			if(!this.login.wk_password ){
				layer.msg("请输入密码");
				return;
			}
			if(!/^\d{1,}$/.test(this.login.wk_num)){
				layer.msg("账号格式为数字");
				return;
			}
			console.info("this.login")
			console.info(this.login)
			$.post("worker/loginPC.action",this.login,function(rs){
				$scope.userinfo = rs.data.worker;
				if($scope.userinfo.wk_type == "admin"){
					$scope.userinfo.wk_type = "管理员"
				}
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