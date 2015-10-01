/*global define */

'use strict';

define(function() {

/* Controllers */

var controllers = {};

controllers.postAdd = function($scope, Category) {
    console.log($scope)
    console.log(Category);
}
controllers.postAdd.$inject = [];

controllers.MyCtrl2 = function() {}
controllers.MyCtrl2.$inject = [];

return controllers;

});