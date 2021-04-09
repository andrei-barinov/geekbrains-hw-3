angular.module('app', []).controller('userService', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/v1';

    $scope.fillListOfUsers = function (pageIndex = 1) {
        $http({
            url:contextPath + '/persons',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.UsersPage = response.data;
                $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.UsersPage.totalPages)
            });
    };

    $scope.generatePagesIndexes = function (startPage, endPage){
        let arr = [];
        for(let i = startPage; i<endPage + 1; i++){
            arr.push(i);
        }
        return arr;
    }


    $scope.deleteUserById = function (id) {
        $http.delete(contextPath + '/persons/' + id)
            .then(function (response) {
                $scope.fillListOfUsers()
            });
    };

    $scope.submitCreateNewUser = function () {
        $http.post(contextPath + '/persons/user_service', $scope.newUser)
            .then(function (response) {
                $scope.fillListOfUsers();
            });
    };

    $scope.fillListOfUsers();
});