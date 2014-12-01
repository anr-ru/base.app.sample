/**
 * Application initialization 
 */

/*
 * List of all modules
 */
var app = angular.module('app', [ 'ngRoute', 'webControllers', 'restServices' ]);

app.config([ '$routeProvider', function($routeProvider) {

    $routeProvider.when('/welcome', {
            templateUrl: './templates/welcome',
            controller: 'WelcomeCtrl'
    }).otherwise({
        redirectTo: '/welcome'
    });
} ]);
