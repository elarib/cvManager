cvApp.service('getObjectifService', function($http, $rootScope) {
	console.log()
	var obj = {};
	obj.req = function() {
		var httpObj = {
			method : 'GET',
			url : 'api/users/'+$rootScope.userID+'/components?projection=detail',
			headers : {
				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
				'Content-Type' : 'application/json'
			}

		};

		return $http(httpObj);
	}

	return obj;

});

cvApp.service('editObjectifService', function($http) {

	var obj = {};
	obj.req = function($id, $newObjectif) {
		var httpObj = {
			method : 'PATCH',
			url : 'api/objectifs/'+$id,
			headers : {
				'Authorization' : 'Basic bWVAZWxhcmliLmNvbTplbGFyaWI=',
				'Content-Type' : 'application/json'
			},
			data : {
				"content" : $newObjectif
			}

		};

		return $http(httpObj);
	}

	return obj;

});