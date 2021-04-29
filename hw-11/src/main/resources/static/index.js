angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/store';
    $scope.authorized = false;

     $scope.fillTable = function (pageIndex = 1) {
         $http({
             url:contextPath + '/api/v1/products',
             method: 'GET',
             params: {
                 title: $scope.filter ? $scope.filter.title: null,
                 p: pageIndex
             }
         })
             .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages)
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
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.fillTable();
                    $scope.showCart();
                   // window.alert("Авторизация прошла успешно");

                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    }

    //$scope.fillTable();
    //$scope.showCart();
});