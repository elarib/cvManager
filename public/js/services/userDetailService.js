cvApp.service('getUserInfosService', function($http, $rootScope) {

	var obj = {};
	console.log($rootScope.userID);
	obj.req = function($userID) {
		var httpObj = {
			method : 'GET',
			url : 'api/users/' + $rootScope.userID
					+ '/infos?projection=UserDetail',
			headers : {
				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
				'Content-Type' : 'application/json'
			}

		};

		return $http(httpObj);
	}

	return obj;

});

cvApp.service('editUserInfosService', function($http, $rootScope) {

	var obj = {};
	console.log($rootScope.userID);
	obj.req = function($id,$content) {

		
		var httpObj = {
			method : 'PATCH',
			url : 'api/userInfos/' + $id,
			headers : {
				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
				'Content-Type' : 'application/json'
			},
			data : {
				"content" : $content
			}

		};

		return $http(httpObj);
	}

	return obj;

});