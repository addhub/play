/*global require, requirejs */

'use strict';

requirejs.config({
    paths: {
        'angular': ['../lib/angularjs/angular'],
        'angular-route': ['../lib/angularjs/angular-route'],
        'angular-resource': ['../lib/angularjs/angular-resource'],
        'angular-cookies': ['../lib/angularjs/angular-cookies'],
        'angular-custom-auth': ['../lib/angular-auth-interceptor/http-auth-interceptor'],
        'angular-material': ['../lib/angularjs/angular-material.min'],
        'angular-aria': ['../lib/angularjs/angular-aria'],
        'angular-animate': ['../lib/angularjs/angular-animate'],
        'angular-messages': ['../lib/angularjs/angular-messages'],


        'jquery': ['../lib/jquery/jquery'],
        'dropzone':['../lib/dropzone/dropzone-amd-module']
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
        },'angular-cookies': {
            deps: ['angular'],
            exports: 'angular'
        },'angular-custom-auth': {
            deps: ['angular'],
            exports: 'angular'
        },'angular-aria': {
            deps: ['angular'],
            exports: 'angular'
        },'angular-animate': {
            deps: ['angular'],
            exports: 'angular'
        },'angular-material': {
            deps: ['angular','angular-aria','angular-animate'],
            exports: 'angular'
        }
        ,'angular-messages': {
            deps: ['angular'],
            exports: 'angular'
        }
    }
});

require(['angular', './controllers', './directives', './filters', './services', 'angular-route', 'angular-resource','angular-cookies', 'angular-custom-auth','angular-material','angular-messages', 'jquery'],
    function (angular, controllers) {

        // Declare app level module which depends on filters, and services

        angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives', 'ngRoute', 'ngResource','ngCookies','ngMaterial', 'ngMessages']).
            config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/home', {templateUrl: 'partials/home.html', controller: controllers.home});
                $routeProvider.when('/list', {templateUrl: 'partials/list.html', controller: controllers.listAds});
                $routeProvider.when('/postAd', {templateUrl: 'partials/postAd.html', controller: controllers.postAd, loginRequired:"postAd"});
                $routeProvider.when('/success/:id', {templateUrl: 'partials/success.html', controller: controllers.postAdSuccess});
                $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: controllers.login});
                $routeProvider.when('/register', {templateUrl: 'partials/register.html', controller: controllers.register});
                $routeProvider.when('/myAccount/dashboard/:id', {templateUrl: 'partials/mydashboard.html', controller: controllers.register, loginRequired:true});
                $routeProvider.when('/myAccount/profile/:id', {templateUrl: 'partials/myProfile.html', controller: controllers.userProfile, loginRequired:true});
                $routeProvider.otherwise({redirectTo: '/home'});
            }]).
            run(function($rootScope, $location) {
                $rootScope.$on( "$routeChangeStart", function(event, next, current) {
                    controllers.intercept(event,next,current, $rootScope, $location);
                });
            }).controller("HomeController", controllers.home);

        angular.bootstrap(document, ['myApp']);

    });
