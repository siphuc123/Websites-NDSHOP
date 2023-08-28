app = angular.module('admin-app', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
		.when('/product', {
			templateUrl:
				"/admin/product/page2.html",
			controller: "product-ctrl"
		})
		.when('/authorize', {
			templateUrl:
				"/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when('/account', {
			templateUrl:
				"/admin/account/index.html",
			controller: "account-ctrl"
		})
		.when('/unauthorized', {
			templateUrl:
				"/admin/authority/unauthorized.html",
			controller: "authority-ctrl"
		})
		.when('/inventory-by-category', {
			templateUrl:
				"/admin/report/inventory-by-category.html",
			// controller: "report-ctrl"
		})
		.when('/inventory-by-order', {
			templateUrl:
				"/admin/report/inventory-by-order.html",
			// controller: "report-ctrl"
		})
		.when('/inventory-by-order-month', {
			templateUrl:
				"/admin/report/inventory-by-order-month.html",
			// controller: "report-ctrl"
		})
		.otherwise({
			templateUrl: "/admin/SummaryInfo.html"
		});
})