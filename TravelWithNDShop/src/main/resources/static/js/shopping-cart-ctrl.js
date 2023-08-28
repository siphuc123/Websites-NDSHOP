const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
	$scope.cart = {
		items: [],
		
		add(id) {
			var item = this.items.find(item => item.product.id == id);
			if (item) {
				/*item.quantity++;
				this.saveToLocalStorage();*/
				let i = this.items.indexOf(item);
				$http.get(`/rest/products/${id}`).then(resp => {
					var data = {
						id: item.id,
						quantity: item.quantity + 1,
						username: $("#account").text(),
						product: resp.data
					}
					this.updateCart(i, item.id, data);
				})
			} else {
				/*$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.quantity = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})*/
				$http.get(`/rest/products/${id}`).then(resp => {
					var data = {
						quantity: 1,
						username: $("#account").text(),
						product: resp.data
					}
					this.saveToCart(data);
				})
			}
			alert("Sản phẩm đã được thêm vào giỏ hàng của bạn!");
		},
		remove(id) {
			/*var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();*/
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1)
			$http.delete(`/rest/carts/${id}`).then()
			if (this.items.length == 0) {
				document.querySelector(".clear-cart").setAttribute("disabled", true);
				document.querySelector(".check-order").setAttribute("disabled", true);
			}
		},
		clear() {
			this.items = [];
			$http.delete(`/rest/carts`).then()
			document.querySelector(".clear-cart").setAttribute("disabled", true);
			document.querySelector(".check-order").setAttribute("disabled", true);
			/*this.saveToLocalStorage();*/
		},
		amt_of(item) { },
		get count() {
			return this.items
				.map(item => item.quantity)
				.reduce((total, quantity) => total += quantity, 0);
		},
		get amount() {
			return this.items
				.map(item => item.quantity * item.price)
				.reduce((total, quantity) => total += quantity, 0);
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		},

		saveToCart(data) {
			var url = `/rest/carts`;
			$http.post(url, data).then(resp => {
				this.items.push(resp.data);
			}).catch(error => {
				console.log("Error", error)
			});
		},
		updateCart(i, cartId, data) {
			var url = `/rest/carts/${cartId}`;
			$http.put(url, data).then(resp => {
				this.items.splice(i, 1, resp.data)
				console.log("Sucess", resp)
			}).catch(error => {
				console.log("Error", error)
			});
		},
		
		updateQuantity(id){
			var item = this.items.find(item => item.product.id == id);
			let i = this.items.indexOf(item);
			item.quantity = angular.copy(item.quantity)
				$http.get(`/rest/products/${id}`).then(resp => {
					var data = {
						id: item.id,
						quantity: item.quantity,
						username: $("#account").text(),
						product: resp.data
					}
					this.updateCart(i, item.id, data);
				})
		},
		
		loadCart() {
			var username = $("#account").text();
			$http.get(`/rest/carts/${username}`).then(resp => {
				this.items = resp.data;
				if (this.items.length == 0) {
					document.querySelector(".clear-cart").setAttribute("disabled", true);
					document.querySelector(".check-order").setAttribute("disabled", true);
				}
				console.log('Success', resp)
			}).catch(error => {
				console.log('Error', error)
			})
		}
	}

	/*$scope.cart.loadFromLocalStorage();*/
	$scope.cart.loadCart();

	$scope.order = {
		address: "",
		createDate: new Date(),
		status: "Đang xử lý",
		account: { username: $("#username").text() },
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.product.id, image: item.product.image },
					price: item.product.price,
					quantity: item.quantity,

				}
			});
		},

		purchase() {
			var order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công");
				this.items = [];
				$http.delete(`/rest/carts`).then()
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đã có lỗi khi đặt hàng!")
				console.log(error)
			})

		}

	}

});