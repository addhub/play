/*global require, requirejs */

'use strict';

requirejs.config({
    paths: {
        'angular': ['../lib/angularjs/angular'],
        'angular-route': ['../lib/angularjs/angular-route'],
        'angular-resource': ['../lib/angularjs/angular-resource'],
        'jquery': ['../lib/jquery/jquery']
    },
    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-route': {
            deps: ['angular'],
            exports: 'angular'
        },'angular-resource': {
            deps: ['angular'],
            exports: 'angular'
        }
    }
});

require(['angular', './controllers', './directives', './filters', './services', 'angular-route', 'angular-resource', 'jquery'],
    function (angular, controllers) {

        // Declare app level module which depends on filters, and services

        angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives', 'ngRoute', 'ngResource']).
            config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/home', {templateUrl: 'partials/home.html', controller: controllers.home});
                $routeProvider.when('/postAd', {templateUrl: 'partials/postAd.html', controller: controllers.postAd});
                $routeProvider.when('/success/:id', {templateUrl: 'partials/success.html', controller: controllers.postAdSuccess});
                $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: controllers.login});
                $routeProvider.when('/register', {templateUrl: 'partials/register.html', controller: controllers.register});
                $routeProvider.when('/myAccount/dashboard/:id', {templateUrl: 'partials/mydashboard.html', controller: controllers.register});
                $routeProvider.when('/myAccount/profile/:id', {templateUrl: 'partials/myProfile.html', controller: controllers.register});
                $routeProvider.when('/myAccount/:noid', {templateUrl: 'partials/login.html', controller: controllers.register});
                $routeProvider.otherwise({redirectTo: '/home'});
            }]);

        angular.bootstrap(document, ['myApp']);

    });
