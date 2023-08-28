app.controller("product-ctrl", function($scope, $http) {
	// alert("Quản lý hàng hóa")
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.form.image = '../../images/uploadIMG.png';
	
	$scope.initialize = function() {
		// load products
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		// load categories
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
	}
	
	// Xóa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: '../../images/uploadIMG.png',
			availiable: true
		};
	}
	// Hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	// Thêm sản phẩm mới 
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
			alert("Thêm mới sản phẩm thành công!");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm!");
			console.log("Error", error);
		});
	}
	// Cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.initialize();
			alert("Update sản phẩm thành công!");
		}).catch(error => {
			alert("Lỗi update sản phẩm!");
			console.log("Error", error);
		});
	}
	// Xóa sản phẩm
	$scope.delete = function(item) {
		item = $scope.items.find(p => p.id == item.id);
		item.available = 0;
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			// $scope.items.splice(index, 1);
			$scope.items[index] = item;
			alert("Xóa sản phẩm thành công!");
			$scope.initialize();
			$scope.reset();
		}).catch(error => {
			alert("Đang có đơn cần xử, không thể xóa!");
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
			$scope.form.image = resp.data.name;
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