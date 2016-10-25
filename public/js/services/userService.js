cvApp.service('getUserInfos', function($http, $rootScope) {

	var obj = {};
	obj.req = function($userID) {
		var httpObj = {
			method : 'GET',
			url : '/api/userInfo'
		};

		return $http(httpObj);
	}

	return obj;

});


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


cvApp.service('uploadImgService', function($http) {

	var obj = {};
	obj.req = function($picture) {
		var httpObj = {
			method : 'POST',
			url : '/api/uploadImg',
			headers : {
				'Content-Type' :  "multipart/form-data"
			},
			data : {
				"picture" : $picture
			}

		};

		return $http(httpObj);
	}

	return obj;

});
