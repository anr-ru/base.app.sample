/**
 * Application initialization 
 */

/*
 * List of all modules
 */
var app = angular.module('app', [ 'ngRoute', 'webControllers', 'restServices' ]);

app.config([ '$routeProvider', function($routeProvider) {

    $routeProvider.when('/welcome', {
            templateUrl: './templates/Welcome',
            controller: 'WelcomeCtrl'
    }).when('/userinfo', {
        templateUrl: './templates/Userinfo',
        controller: 'UserInfoCtrl'
    }).otherwise({
        redirectTo: '/welcome'
    });
} ]);
