/*global define */

'use strict';

define(['angular'], function (angular) {

    /* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
    angular.module('myApp.services', []).
        value('version', '0.21')
        .factory('Category', function ($resource) {
            console.out("hi")
            return $resource('/api/category/:name', {name:'all'})
        })
        .factory('Advertisement',function(){

        });

});