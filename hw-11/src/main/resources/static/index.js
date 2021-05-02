angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/store';
    $scope.authorized = false;

     $scope.fillTable = function (pageIndex = 1) {
         $http({
             url: contextPath + '/api/v1/products',
             method: 'GET',
             params: {
                 title: $scope.filter ? $scope.filter.title : null,
                 min_price: $scope.filter ? $scope.filter.min_price : null,
                 max_price: $scope.filter ? $scope.filter.max_price : null,
                 p: pageIndex
             }
         }).then(function (response) {
             $scope.ProductsPage = response.data;

             let minPageIndex = pageIndex - 2;
             if (minPageIndex < 1) {
                 minPageIndex = 1;
             }

             let maxPageIndex = pageIndex + 2;
             if (maxPageIndex > $scope.ProductsPage.totalPages) {
                 maxPageIndex = $scope.ProductsPage.totalPages;
             }

             $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
         });
     };

     $scope.generatePagesIndexes = function (startPage, endPage){
         let arr = [];
         for(let i = startPage; i<endPage + 1; i++){
             arr.push(i);
         }
         return arr;
      }


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.addToCart = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.showCart = function () {
        $http({
            url:contextPath + '/api/v1/cart',
            method: 'GET',
        })
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.showMyOrders = function () {
        $http({
            url:contextPath + '/api/v1/orders',
            method: 'GET',
        })
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;

                    $localStorage.storeUsername = $scope.user.username;
                    $localStorage.storeTokenWithBearerPrefix = 'Bearer ' + response.data.token;

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.fillTable();
                    $scope.showCart();
                    $scope.showMyOrders();
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    }

    $scope.logout = function () {
        $http.defaults.headers.common.Authorization = null;
        delete $localStorage.storeUsername;
        delete $localStorage.storeTokenWithBearerPrefix;
        $scope.authorized = false;
    };

    $scope.createOrder = function () {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function (response) {
                $scope.showMyOrders();
                $scope.showCart();
            });
    }

     if ($localStorage.storeUsername) {
         $http.defaults.headers.common.Authorization = $localStorage.storeTokenWithBearerPrefix;
         $scope.fillTable();
         $scope.showMyOrders();
         $scope.showCart();
         $scope.authorized = true;
     }
});