/*global define */

'use strict';

define(function () {

    /* Controllers */

    var controllers = {};

    controllers.postAd = function ($scope, Category, Ad, $location) {
        Category.query(function (data) {
            $scope.categories = data;
            console.log(data);
        });

        $scope.postAd = function () {
            console.log($scope.ad)
            Ad.save(
                $scope.ad,
                function (success) {
                    $location.path('success/' + success.id);
                });
        }

    }
    controllers.postAd.$inject = ['$scope', 'Category', 'Ad', '$location'];

    controllers.postAdSuccess = function ($scope, $routeParams) {
        $scope.docId=$routeParams.id;
    }
    controllers.postAdSuccess.$inject = ['$scope', '$routeParams'];


    controllers.home = function ($scope) {

    }
    controllers.home.$inject = ['$scope'];
    return controllers;

});