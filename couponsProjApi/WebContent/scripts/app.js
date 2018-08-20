(function() {

	var app = angular.module("myApp", ["ngRoute"]);
	app.config(function($routeProvider){
		
		$routeProvider
		.when("/", {
	        templateUrl : "login.htm"
	    })
	    .when("/company", {
	        templateUrl : "Company.htm"
	    })
	    .when("/admin", {
	    	templateUrl : "Admin.htm"
	    })
	     .when("/customer", {
	    	templateUrl : "Customer.htm"
	    })
	})
	
	
	app.controller('LoginController', ['$http', '$scope', '$window', function($http, $scope, $window){
		
		
		this.login = function (){
			var user = JSON.stringify($scope.user);		
			$http.post('http://localhost:8080/couponsProjApi/rest/login', user).success(function(){			
				if ($scope.user.type=="ADMIN"){
					$window.location.assign('/couponsProjApi/#admin');	
				} 
				else if($scope.user.type=="COMPANY"){
					$window.location.assign('/couponsProjApi/#company');
				}
				else{
					$window.location.assign('/couponsProjApi/#customer');
				}			
			})
			
		}
	}])
	
app.controller('AdminController', function($scope, $http){
		var activeMethod = 0;
		var baseUrl = "http://localhost:8080/couponsProjApi/rest/site/";
		$scope.isActive = function(currentMethod){
			return $scope.activeMethod === currentMethod;
		};
		$scope.setActive = function(method){
			$scope.activeMethod = method;
		};
		
		$scope.createCompany = function(){
			var company = new Object();
			url = baseUrl +"company/createCompany";
			company.compName = $scope.createName;
			company.password = $scope.createPassword;
			company.email = $scope.createEmail;
			var jsonObj = JSON.stringify(company);
			$http.post(url, jsonObj)
			.then(function(response){
				$scope.temp = angular.fromJson(response.data);
			}, function(response){
				alert(response.data);
			});	
		};
		
		$scope.removeCompany = function(){
		$http({
		    method: 'DELETE',
		    url:  baseUrl + 'company/removeCompany?companyId=' + $scope.removeId
		})
		.then(function(response) {
			$scope.answer = response.data;
		}, function(rejection) {
			alert(response.data);
		});
		};
		
		$scope.updateCompany = function(){
			var company = new Object();
			company.id = $scope.updateId;
			company.compName = $scope.updateName;
			company.password = $scope.updatePassword;
			company.email = $scope.updateEmail;
			url = baseUrl +'company/updateCompany';
			$http.put(url, company);
		};
		
		
		$scope.getCompany = function(){
			url = baseUrl +'company/getCompanyByID?companyId='+$scope.getId;;
			$http.get(url)
			.then(
			function(response){
				$scope.answer = response.data.company;
			}, function(response){
				alert(response.data);
			});
		};
		
		$scope.getAllCompanies = function(){
			url = baseUrl + 'company';
			$http.get(url)
			.then(
			function(response){
				$scope.companies = response.data.company;
			}, function(response){
				alert(response.data);
			});
			
		};
		
		
		$scope.createCustomer = function(){
			var customer = new Object();
			url = baseUrl + 'customer/createCustomer';
			customer.custName = $scope.createNameCustomer;
			customer.password = $scope.createPassowrdCustomer;
			customer.userName = $scope.email;
			var jsonObj = JSON.stringify(customer);
			$http.post(url, jsonObj)
			.then(function(response){
				$scope.temp = angular.fromJson(response.data);
			}, function(response){
				alert(response.data);
			});	
		};
		
		$scope.removeCustomer = function(){
		$http({
		    method: 'DELETE',
		    url:  baseUrl + 'customer/removeCustomer?customerId=' + $scope.removeId
		})
		.then(function(response) {
			$scope.answer = response.data;
		}, function(rejection) {
			alert(response.data);
		});
		};
		
		$scope.updateCustomer = function(){
			var customer = new Object();
			customer.id = $scope.updateIdCustomer;
			customer.custName = $scope.updateNameCustomer;
			customer.password = $scope.updatePasswordCustomer;
			customer.userName = $scope.updateEmailCustomer;
			url = baseUrl + 'customer/updateCustomer';
			$http.put(url, customer);
		};
		
		$scope.getCustomer = function(){
			url = baseUrl + 'customer/getCustomerByID?customerId='+$scope.getIdCustoemr;
			$http.get(url)
			.then(
			function(response){
				$scope.answer = response.data.customer;
			}, function(response){
				alert(response.data);
			});
		};
		
		$scope.getAllCustomers = function(){
			url = baseUrl + 'customer';
			$http.get(url)
			.then( 
			function(response){
				$scope.customers = response.data.customer;
			}, function(response){
				alert(response.data);
			});	
		};
	});
	
	app.controller('CompanyController', function($scope, $http){
		var BaseUrl = 'http://localhost:8080/couponsProjApi/rest/site/';
		$scope.isActiveCompany = function(currentMethod){
			return $scope.activeMethod === currentMethod;
		};
		$scope.setActiveCompany= function(method){
			$scope.activeMethod = method;
		};
	
		$scope.createCoupon = function(){
			var createdCoupon = new Object();
			createdCoupon.title = $scope.createTitle;
			createdCoupon.message = $scope.createMessage;
			createdCoupon.price = $scope.createPrice;
			createdCoupon.image = $scope.createImage;
			createdCoupon.amount = $scope.createAmount;	
			
			var start = new Date($scope.createStartDate).getTime();
			var end = new Date($scope.createEndDate).getTime();
			createdCoupon.startDate = start;
			createdCoupon.endDate = end;
			
			var e = document.getElementById("couponType");
			createdCoupon.type = e.options[e.selectedIndex].value;
			var jsonObj = JSON.stringify(createdCoupon);
			$http.post(companyBaseUrl, jsonObj)
			.then(function(response){
				alert("coupon was created");
			},
			function(response){
				alert(response.data);
			});
		};
		$scope.removeCoupon = function(){
		$http({
		    method: 'DELETE',
		    url:  BaseUrl + 'coupon/removeCoupon?couponId=' + $scope.idRemove
		})
		.then(function(response) {
			$scope.answer = response.data;
		}, function(response) {
			alert(response.data);
		});
		};
		
		$scope.updateCoupon = function(){
			var updatedCoupon = new Object();
			var startUpdate = new Date($scope.updateStartDate).getTime();
			var endUpdate = new Date($scope.updateEndDate).getTime();

			updatedCoupon.id = $scope.idUpdate;
			updatedCoupon.title = $scope.updateTitle;
			updatedCoupon.startDate = startUpdate;
			updatedCoupon.endDate = endUpdate;
			updatedCoupon.amount = $scope.updateAmount;
			updatedCoupon.type = $scope.type;
			updatedCoupon.message = $scope.updateMessage;
			updatedCoupon.price = $scope.updatePrice;
			updatedCoupon.image = $scope.imaage;
			updatedCoupon.companyRef = $scope.companyRef;

			var jsonObjUpdate = JSON.stringify(updatedCoupon);
			alert(jsonObjUpdate);
			$http.put(BaseUrl+'coupon/updateCoupon', jsonObjUpdate);
		};
		
		$scope.getCoupon = function(){
			url = BaseUrl+"coupon/getCouponById?couponId="+$scope.getIdCoupon;
			$http.get(url)
			.then(
			function(response){
				$scope.answerCoupon = response.data;
			}, function(response){
				alert(response.data);
			});
		};
		
		$scope.getAllCoupons = function(){
			$http.get(BaseUrl+'coupon')
			.then(
					function(response){
						$scope.coupons = response.data;
					},
					function(response){
						alert(response.data);
					});
		};
		
		$scope.getCouponsByType = function(){
			var e = document.getElementById("typeCouponType");
			var requestedType = e.options[e.selectedIndex].value;
			url = BaseUrl + "/type/getCouponByType?type=" + requestedType;
			$http.get(url)
			.then(
				function(response){
					$scope.typeAnswer = response.data;
				},
				function(response){
					alert(response.data);
				});
		};
	});
	
	app.controller('CustomerController', function($scope, $http){
		var customerBaseUrl = "http://localhost:8080/couponsProjApi/rest/site/";
		$scope.isActiveCustomer = function(currentMethod){
			return $scope.activeMethod === currentMethod;
		};
		$scope.setActiveCustomer= function(method){
			$scope.activeMethod = method;
		};
		$scope.purchaseCoupon = function(){
			var couponPurchase = new Object();
			couponPurchase.customerId = $scope.customerId;
			couponPurchase.couponId = $scope.couponId;
			var jsonObj = JSON.stringify(couponPurchase);
			alert(jsonObj);
			$http.post(customerBaseUrl+'coupon/buyCoupon', jsonObj)
			.then(
					function(response){
						alert("coupon was purchased");
					},
					function(response){
						alert(response.data);
					}); 
		};
		
		$scope.getPurchasedCoupons = function(){
			$http.get(customerBaseUrl)
			.then(
					function(response){
						$scope.coupons = response.data;
					},
					function(response){
						alert(response);
					});
		};
		
		$scope.getPurchasedCouponsByType = function(){
			var customerType = $scope.getCouponTypeCustomerFilter;
			url = customerBaseUrl + "/" + customerType;
			$http.get(url)
			.then(
				function(response){
					$scope.typeAnswer = response.data;
				},
				function(response){
					alert(response.data);
				});
		};
		
		$scope.getPurchasedCouponsByPrice = function(){
			var filter = $scope.getPriceCouponsFilter;
			url = customerBaseUrl + "/price/" + filter;
			$http.get(url)
			.then(
					function(response){
						$scope.coupons = response.data;
					},
					function(response){
						alert(response);
					});
		};
	});
	

	
})();




