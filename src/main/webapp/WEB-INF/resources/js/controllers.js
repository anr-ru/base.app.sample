/**
 * CONTROLLERS: communication between user, browser and services 
 */

var webControllers = angular.module('webControllers', []);

/**
 * Welcome page Controller
 */
webControllers.controller('WelcomeCtrl', [ '$scope', '$rootScope', 'PingService', function($scope, $rootScope, pingService) {

    var r = pingService.get(function(data) {
        
        console.log(data);
        $scope.labelregister='You can register user';
        $scope.labellogin='or You can login';
    });    
    $rootScope.pagetitle = 'Welcome to application';

} ]);

/**
 * UserInfo page Conntorller
 */
webControllers.controller('UserInfoCtrl', [ '$scope', '$rootScope', 'UsersService', 'DocumentsService', function($scope, $rootScope, usersService, documentsService) {

    var r = usersService.get(function(data) {
        
        console.log(data);
        $scope.user=data;
    });    
    
    $scope.documents=documentsService.querydocs({'page':0, 'per_page': 3});
    $scope.documentspage=1;
    
    $rootScope.pagetitle = 'User cabinet';

} ]);

/**
 * Documents Controller
 */
webControllers.controller('GetDocumentsCtrl', [ '$scope', '$rootScope', 'DocumentsService', function($scope, $rootScope, documentsService) {
    $scope.getDocuments=function(){
        console.log($scope.documentspage);
        documentsService.querydocs({'page':$scope.documentspage, 'per_page': 3},
            function(data){
                 //Пока не нашел другово способа добавить сразу все элементы без перебора
                 data.forEach(function(doc, index) {
                    $scope.$parent.documents.push(doc);
                 }); 
                 if(data.length<3){
                     $scope.endList='No more count';
                     $scope.disabled='false';
                 }
        });
        $scope.$parent.documentspage=$scope.$parent.documentspage+1;
    }
} ]);

/**
 * Create User or Document Controller
 */
webControllers.controller('CreateCtrl', [ '$scope', '$rootScope', 'CreateUserService', 'CreateDocumentService', 'DocumentsService', function($scope, $rootScope, createUserService, createDocumentService, documentsService) {
    
    $scope.createUser=function(){
        var u = createUserService.create($scope.user, function (data) {
            $scope.$parent.user = data;
            console.log(data.code);
            if(angular.equals($scope.$parent.user.code, 0)){
               $scope.$parent.labellogin='';
               $scope.$parent.labelloginsuccess='You registration success! You can login!';
            }else{
                $scope.$parent.labellogin='Error registration';
                $scope.$parent.labelloginsuccess='';
            }
        });
        console.log($scope.user);
        console.log($scope.user.full_name);
        console.log($scope.user.code);
    }
    
    $scope.createDocument=function(){
        var doc = createDocumentService.createDocument($scope.newdocument);
        $scope.$parent.documents.push(doc);
        console.log($scope.$parent.documents);
    }
  
} ]);
