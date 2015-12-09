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


            require(['dropzone'], function (Dropzone) {
                window.dropzone = Dropzone;
                var myDropzone = new Dropzone("div#upload", {
                    url: "/upload/ad/img",
                    previewTemplate: $('#template').html(),
                    clickable: '.dz-clickable'
                });
            })

        };
        controllers.postAd.$inject = ['$scope', 'Category', 'Ad', '$location'];

        controllers.listAds = function ($scope, Category, Ad, AdExport, $location, $http) {
            Category.query(function (data) {
                $scope.categories = data;
                console.log(data);
            });
            var adUrl = "/api/ad";
            console.log("list ads")
            $scope.adList = $http.get(adUrl, {params: {category: "Vehicle", subCat: "Cars"}}).then(
                function (success) {
                    $scope.adList = success.data;
                });

            $scope.exportAd = function (category, id, to) {
                AdExport.save({category: category, id: id, to: to}, {category: category, id: id})
            }

        };
        controllers.listAds.$inject = ['$scope', 'Category', 'Ad', 'AdExport', '$location', '$http'];

        controllers.postAdSuccess = function ($scope, $routeParams) {
            $scope.docId = $routeParams.id;
        };
        controllers.postAdSuccess.$inject = ['$scope', '$routeParams'];


        controllers.home = function ($scope, $rootScope, $cookies) {
            if (!$cookies.get("username")) {
                delete $rootScope.currentUser;
            } else if ($cookies.get("username") && !$rootScope.currentUser) {
                $rootScope.currentUser = {
                    username: $cookies.get("username"),
                    displayName: $cookies.get("displayName"),
                    pictureUrl: $cookies.get("pictureUrl")
                }
            }
        };
        controllers.home.$inject = ['$scope', '$rootScope', '$cookies'];


        controllers.login = function ($rootScope, $scope, $routeParams, $http, $window, $route, $location, $cookies) {
            $scope.message = $routeParams.message;
            var redirect = $routeParams.redirect;
            redirect = redirect || "/";
            var url = "/auth/login?redirect=" + redirect;

            if ($cookies.get("socialLogin")) {
                $location.path(redirect);
                $route.reload();
            }
            function onLoginSuccess(response) {
                $location.path(redirect);
                $route.reload();
                delete $rootScope.currentUser;
                $rootScope.currentUser = response.data
            }

            $scope.authenticate = function () {
                var user = $scope.user;
                $http.post(url, user).then(
                    function success(response) {
                        onLoginSuccess(response);
                    },
                    function error(response) {
                        $scope.message = response;
                    }
                );
            }


        }
        controllers.login.$inject = ['$rootScope', '$scope', '$routeParams', '$http', '$window', '$route', '$location', '$cookies'];

        controllers.register = function ($rootScope, $scope, $location, $http) {
            $scope.signUp = function () {
                var url = "/auth/signup";
                var user = $scope.user;
                user.email = user.username;
                $http.post(url, user).then(
                    function success(response) {
                        delete $rootScope.currentUser;
                        $rootScope.currentUser = response.data
                        $location.path('mydashboard/' + response.data.id);
                    },
                    function error(error) {
                        $scope.message = error;
                    }
                );
            };
        }
        controllers.register.$inject = ['$rootScope', '$scope', '$location', '$http'];


        controllers.userProfile = function ($scope, User) {
            $scope.viewMode = true;

            // Switch between view mode and edit mode
            $scope.switchMode = function () {
                return $scope.viewMode = !$scope.viewMode;
            };

            // Save the changes
            $scope.saveChanges = function () {
                /* Validate the input
                 Save the changes */
            };

            // User data
            User.get({username: $scope.currentUser.username}, function (user, getResponseHeaders) {
                console.log(user);
                $scope.user = {
                    name: 'Admin C.John',
                    nickname: 'johnny@addhub',
                    address: 'No.123 45th Street, Brokelynn',
                    location: 'New York City',
                    company: 'Addhub',
                    workPhone: '404-123-5678',
                    mobilePhone: '404-987-6543',
                    alias: 'admin.john@addhub.com',
                    otherInfo: 'Don not disturb during week time!'
                }
            });
        }
        controllers.userProfile.$inject = ['$scope', 'User'];


        controllers.adDetail = function ($scope, Ad) {

            // Ad detail
            Ad.get({category: 'Vehicle', id: 'abc'}, function (ad, getResponseHeaders) {
                console.log(ad);
                $scope.ad = ad;

            });
        }
        controllers.adDetail.$inject = ['$scope', 'Ad'];

        /**
         * Interceptors for the controllers
         */

        controllers.intercept = function (event, next, current, $rootScope, $location) {
            //weak frontend security, just so that user does not gets rejected by backend all after he has filled the forms.
            if (next.loginRequired) {
                if (!$rootScope.currentUser) {
                    var msg = null;
                    var nextPath = '/#/' + $location.path()
                    $location.path('login').search({'redirect': nextPath, 'message': msg});
                }
            }


        }

        return controllers;

    }
)
;





















