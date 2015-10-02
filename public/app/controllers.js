/*global define */

'use strict';

define(function () {

    /* Controllers */

    var controllers = {};

    controllers.postAd = function ($scope, Category, Ad) {
        Category.query(function (data) {
            $scope.categories = data;
            console.log(data);
        });

        $scope.postAd=function(){
            console.log($scope.ad)
            Ad.save($scope.ad)
        }

    }
    controllers.postAd.$inject = ['$scope', 'Category', 'Ad'];

    controllers.MyCtrl2 = function () {
    }
    controllers.MyCtrl2.$inject = [];

    return controllers;

});