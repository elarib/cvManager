cvApp.service('editUserInfosService', function($http, $rootScope) {

	var obj = {};
	obj.req = function($id,$email, $firstName, $lastName, $age, $description) {

		
		var httpObj = {
			method : 'POST',
			url : 'api/userInfo',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				"id" : $id,
				"email" : $email,
				"firstName" : $firstName,
				"lastName" : $lastName,
				"age" : $age,
				"description": $description
			}

		};

		return $http(httpObj);
	}

	return obj;

});