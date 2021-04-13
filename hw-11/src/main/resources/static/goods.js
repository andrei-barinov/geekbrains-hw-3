angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/v1';

     $scope.fillTable = function (pageIndex = 1) {
         $http({
             url:contextPath + '/products',
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
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.addToCart = function (id) {
        $http.get(contextPath + '/cart/add/' + id)
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.showCart = function () {
        $http({
            url:contextPath + '/cart',
            method: 'GET',
        })
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.fillTable();
    $scope.showCart();
});