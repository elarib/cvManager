cvApp.service('editObjectifService', function($http) {

	var obj = {};
	obj.req = function($id, $newObjectif) {
		var httpObj = {
			method : 'POST',
			url : '/api/objectif ',
			data : {
				"id" : $id,
				"content" : $newObjectif
			}

		};

		return $http(httpObj);
	}

	return obj;

});

cvApp.service('editEducationService', function($http) {

	var obj = {};
	obj.req = function($id, $desc, $place, $yearFrom, $yearTo) {
		var httpObj = {
			method : 'POST',
			url : '/api/education ',
			data : {
				"id" : $id,
				"description" : $desc,
				"place" : $place,
				"yearFrom" : $yearFrom,
				"yearTo" : $yearTo
			}

		};

		return $http(httpObj);
	}

	return obj;

});


cvApp.service('editWorkService', function($http) {

	var obj = {};
	obj.req = function($id, $desc, $place, $yearFrom, $yearTo) {
		var httpObj = {
			method : 'POST',
			url : '/api/work ',
			data : {
				"id" : $id,
				"description" : $desc,
				"place" : $place,
				"yearFrom" : $yearFrom,
				"yearTo" : $yearTo
			}

		};

		return $http(httpObj);
	}

	return obj;

});