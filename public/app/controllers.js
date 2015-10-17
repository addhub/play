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
            var ad = $scope.ad;
            ad.subCat = ad.category.split('-')[1]
            ad.category = ad.category.split('-')[0]
            console.log(ad);
            Ad.save(
                ad,
                function (success) {
                    $location.path('success/' + success.id);
                });
        }

    };
    controllers.postAd.$inject = ['$scope', 'Category', 'Ad', '$location'];

    controllers.postAdSuccess = function ($scope, $routeParams) {
        $scope.docId = $routeParams.id;
    };
    controllers.postAdSuccess.$inject = ['$scope', '$routeParams'];


    controllers.home = function ($scope) {
    };
    controllers.home.$inject = ['$scope'];


    controllers.login = function ($scope) {
    }
    controllers.login.$inject = ['$scope'];

    controllers.register = function ($scope, $location, User) {
        $scope.createUser = function () {
            var user = $scope.user;
            User.save(user, function (success) {
                $location.path('mydashboard/' + success.id);
            });
        };
    }
    controllers.register.$inject = ['$scope','$location','User'];

    return controllers;

});