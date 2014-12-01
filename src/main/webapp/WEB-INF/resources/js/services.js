/**
 * SERVICES: Main logic implementations 
 */

var restServices = angular.module('restServices', ['ngResource']);

restServices.factory('UsersService', ['$resource', function($resource){

    return $resource('api/v1/users');
} ]);
