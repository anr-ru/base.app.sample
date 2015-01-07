/**
 * SERVICES: Main logic implementations 
 */

var restServices = angular.module('restServices', ['ngResource']);

restServices.factory('PingService', ['$resource', function($resource){

    return $resource('api/v1/Ping');
} ]);

restServices.factory('UsersService', ['$resource', function($resource){

    return $resource('api/v1/users');
} ]);

restServices.factory('CreateUserService', ['$resource', function($resource){

    return $resource('api/v1/users',{}, {
       create: {method:'POST', params:{}}
    });
} ]);

restServices.factory('DocumentsService', ['$resource', function($resource){

    return $resource('api/v1/documents',{}, {
       querydocs: {method:'GET', params:{}, isArray:true}
    });
} ]);

restServices.factory('CreateDocumentService', ['$resource', function($resource){
    
    return $resource('api/v1/documents',{}, {
        createDocument: {method:'POST', params:{}}
     });
} ]);
