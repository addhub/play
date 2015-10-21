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
        if($cookies.get("user-name")){
            $rootScope.currentUser={name:$cookies.get("user-name")}
        }
    };
    controllers.home.$inject = ['$scope','$rootScope', '$cookies' ];


    controllers.login = function ($scope, $routeParams) {
        $scope.message=$routeParams.message;
        $scope.redirect=$routeParams.redirect;
        $scope.action="user/login?redirect="+$routeParams.redirect;

    }
    controllers.login.$inject = ['$scope','$routeParams'];

    controllers.register = function ($scope, $location, User) {
        $scope.createUser = function () {
            var user = $scope.user;
            User.save(user, function (success) {
                $location.path('mydashboard/' + success.id);
            });
        };
    }
    controllers.register.$inject = ['$scope','$location','User'];






    /**
     * Interceptors for the controllers
     */

    controllers.intercept= function(event, next, current, $rootScope, $location){


        if(next.loginRequired){
            if(!$rootScope.currentUser ){
                var msg=null;
                var nextPath='/#/'+$location.path()
                $location.path('login/').search({'redirect':nextPath, 'message': msg});
            }
        }







    }

    return controllers;

});





















