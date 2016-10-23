cvApp.service('getUserInfos', function($http, $rootScope) {

	var obj = {};
	console.log($rootScope.userID);
	obj.req = function($userID) {
		var httpObj = {
			method : 'GET',
			url : '/api/userInfo'
		};

		return $http(httpObj);
	}

	return obj;

});


//cvApp.service('getUserService', function($http, $rootScope) {
//
//	var obj = {};
//
//	obj.req = function($userID) {
//		var httpObj = {
//			method : 'GET',
//			url : 'api/users/'+$rootScope.userID+'?projection=UserDetail',
//
//			headers : {
//				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
//				'Content-Type' : 'application/json'
//			}
//
//		};
//
//		return $http(httpObj);
//	}
//
//	return obj;
//
//});




cvApp.service('editUserService', function($http, $rootScope) {

	var obj = {};
	console.log($rootScope.userID);
	obj.req = function($infos) {
		
		console.log($infos);
		var httpObj = {
			method : 'PATCH',
			url : 'api/users/'+$rootScope.userID+'?projection=UserDetail',

			headers : {
				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
				'Content-Type' : 'application/json'
			},
			data : {
                "description": $infos[2],
                "lastName": $infos[1],
                "firstName": $infos[0]
                
              }

		};

		return $http(httpObj);
	}

	return obj;

});


