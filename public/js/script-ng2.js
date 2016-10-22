// MODULE
var myApp = angular.module('myApp', []);


myApp.controller('mainController', function($scope, $http){
    
	  $scope.title ="";
	  var reqGET = {
		         method: 'GET',
		         url: 'api/objectifs/1',
		        headers: {
		           'Authorization': 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
		           'Content-Type': 'application/json'
		         }
		        
		        };
	  

	  
	  
	  var reqPATCH = {
		         method: 'PATCH',
		         url: 'api/objectifs/1',
		        headers: {
		           'Authorization': 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
		           'Content-Type': 'application/json'
		         },
		         data: { "content": $scope.title }
		        
		        };
	
	
	  $http(reqGET).then(function successCallback(response) {
       	$scope.title = response.data.content;
          console.log("GET GOOD");
          
        }, function errorCallback(response,status) {
          console.log(status );    
          console.log(response );
        });
   
    
      $scope.editorEnabled = false;

      $scope.enableEditor = function() {
        $scope.editorEnabled = true;
        $scope.editableTitle = $scope.title;
      };

      $scope.disableEditor = function() {
        $scope.editorEnabled = false;
      };

      $scope.save = function() {
        $scope.title = $scope.editableTitle;
        
        reqPATCH.data = { "content": $scope.title };
        console.log($scope.title);
        
        
        $http(reqPATCH).then(function successCallback(response) {
           	
              console.log("CHANGED");
              
            }, function errorCallback(response,status) {
              console.log(status );    
              console.log(response );
            });
        $scope.disableEditor();
      };
    
     
    
   
    
 
    
    
});