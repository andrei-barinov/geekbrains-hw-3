angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/hw_8';

     $scope.fillTable = function () {
         $http.get(contextPath + '/products')
             .then(function (response) {
                 console.log(response);
                $scope.ProductsList = response.data;
            });
     };


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http({
            url: contextPath + '/products',
            method: 'DELETE',
            params: {
            id:id
            }
        }).then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});