app.controller("account-ctrl", function($scope, $http) {
	// alert("Quản lý tài khoản")
	$scope.items = [];
	$scope.roles = [];
	$scope.form = {};
	
	$scope.initialize = function() {
		// load accounts
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		});
	}

	// Xóa form
	$scope.reset = function() {
		$scope.form = {};
	}
	
	// Hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	// Thêm tài khoản mới 
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
			alert("Thêm mới tài khoản thành công!");
		}).catch(error => {
			alert("Lỗi thêm mới !");
			console.log("Error", error);
		});
	}
	// Cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			$scope.initialize();
			alert("Update thành công!");
		}).catch(error => {
			alert("Lỗi update !");
			console.log("Error", error);
		});
	}
	// Xóa sản phẩm
	$scope.delete = function(item) {
		item = $scope.items.find(acc => acc.username == item.username);
		item.activated = 0;
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			//$scope.items.splice(index, 1);
			$scope.items[index] = item;
			alert("Xóa thành công!");
			$scope.reset();
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi Xóa !");
			console.log("Error", error);
		});
	}
	// Upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity, headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh"); console.log("Error", error);
		})
	}

	/* Phân trang bằng angularjs */
	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			/* Tổng số trang = tổng số phần tử(sp) chia cho kích thước mỗi trang */
			// nhân thêm 1.0 -> trách 2 số nguyên chia nhau cho 1 số nguyên
			return Math.ceil(1.0 * $scope.items.length / this.size)
		},
		first() {
			this.page = 0;
		},
		last() {
			this.page = this.count - 1;
		},
		previous() {
			if (this.page <= 0) {
				this.last();
			} else {
				this.page--;
			}
		},
		next() {
			if (this.page >= this.count - 1) {
				this.first();
			} else {
				this.page++;
			}
		}
	}
	// khởi động
	$scope.initialize();
});