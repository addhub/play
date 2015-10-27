/*global define */

'use strict';

define(function () {


    /* Controllers */

    var controllers = {};

    controllers.postAd = function ($scope, Category, Ad, $location) {
        _Auth();
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


    controllers.home = function ($scope, $rootScope, $cookies) {
        delete $rootScope.currentUser;
        if ($cookies.get("user-name")) {
            $rootScope.currentUser = {name: $cookies.get("user-name")}
        }
    };
    controllers.home.$inject = ['$scope', '$rootScope', '$cookies'];


    controllers.login = function ($rootScope, $scope, $routeParams, $http, $window, $route, $location) {
        $scope.message = $routeParams.message;
        var redirect = $routeParams.redirect;
        redirect = redirect || "/";
        var url = "/auth/login?redirect=" + redirect;
        $scope.authenticate = function () {
            var user = $scope.user;
            $http.post(url, user).then(
                function success(response) {
                    $location.path(redirect);
                    $route.reload();
                    delete $rootScope.currentUser;
                    $rootScope.currentUser = {name: response.data.name}
                },
                function error(error) {
                    $scope.message = error;
                }
            );
        }

    }
    controllers.login.$inject = ['$rootScope', '$scope', '$routeParams', '$http', '$window', '$route', '$location'];

    controllers.register = function ($scope, $location, $http) {
        $scope.signUp = function () {
            var url = "/auth/signup";
            var user = $scope.user;
            $http.post(url, user).then(
                function (success) {
                    $location.path('mydashboard/' + success.id);
                },
                function error(error) {
                    $scope.message = error;
                }
            );
        };
    }
    controllers.register.$inject = ['$scope', '$location', '$http'];


    /**
     * Interceptors for the controllers
     */

    controllers.intercept = function (event, next, current, $rootScope, $location) {


        if (next.loginRequired) {
            if (!$rootScope.currentUser) {
                var msg = null;
                var nextPath = '/#/' + $location.path()
                $location.path('login/').search({'redirect': nextPath, 'message': msg});
            }
        }


    }

    return controllers;

});





















