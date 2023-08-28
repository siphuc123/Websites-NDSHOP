app.controller("dangky-ctrl", function($scope, $http, $window) {
	// alert("Quản lý tài khoản")
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		// load accounts
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		});
	}

	// Xóa form
	$scope.reset = function() {
		$scope.form = {
			photo: ""
		};
	}

	// Thêm tài khoản mới 
	$scope.create = function() {
		var item = angular.copy($scope.form);
		item.photo = 'user.png';
		item.activated = 1;
		if ($scope.items.find(p => p.username == item.username)) {
			alert("Tài khoản đã tồn tại.");
		} else {
			$http.post(`/rest/accounts`, item).then(resp => {
				$scope.items.push(resp.data);
				alert("Đăng ký thành công!");
				$window.location.href = 'http://localhost:8080/security/login';
				$scope.reset();
			}).catch(error => {
				alert("Lỗi đăng ký!");
				console.log("Error", error);
			});
		}
	}

	// khởi động
	$scope.initialize();
});