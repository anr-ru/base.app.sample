/**
 * CONTROLLERS: communication between user, browser and services 
 */

var webControllers = angular.module('webControllers', []);

/**
 * Welcome page
 */
webControllers.controller('WelcomeCtrl', [ '$scope', '$rootScope', 'PingService', function($scope, $rootScope, pingService) {

    var r = pingService.get(function(data) {
        
        console.log(data);
        
    });    
    
    $rootScope.pagetitle = 'Welcome to application';

} ]);
