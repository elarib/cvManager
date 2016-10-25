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

cvApp.service('addEducationService', function($http) {

	var obj = {};
	obj.req = function($desc, $place, $yearFrom, $yearTo) {
		var httpObj = {
			method : 'POST',
			url : '/api/neweducation',
			data : {
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
	obj.req = function($id, $title, $desc, $place, $yearFrom, $yearTo) {
		var httpObj = {
			method : 'POST',
			url : '/api/work ',
			data : {
				"id" : $id,
				"title" : $title,
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


cvApp.service('addWorkService', function($http) {

	var obj = {};
	obj.req = function($title, $desc, $place, $yearFrom, $yearTo) {
		var httpObj = {
			method : 'POST',
			url : '/api/newwork',
			data : {
				"title" : $title,
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



cvApp.service('editCompetenceService', function($http) {

	var obj = {};
	obj.req = function($id, $name) {
		var httpObj = {
			method : 'POST',
			url : '/api/competence ',
			data : {
				"id" : $id,
				"name" : $name
			}

		};

		return $http(httpObj);
	}

	return obj;

});


cvApp.service('addCompetenceService', function($http) {

	var obj = {};
	obj.req = function($name) {
		var httpObj = {
			method : 'POST',
			url : '/api/newcompetence',
			data : {
				"name" : $name
			}

		};

		return $http(httpObj);
	}

	return obj;

});


cvApp.service('editCompetenceEltService', function($http) {

	var obj = {};
	obj.req = function($id, $name, $detail) {
		var httpObj = {
			method : 'POST',
			url : '/api/competenceelt ',
			data : {
				"id" : $id,
				"name" : $name,
				"detail" : $detail

			}

		};

		return $http(httpObj);
	}

	return obj;

});



cvApp.service('findAllCompetenceService', function($http) {

	var obj = {};
	obj.req = function($name) {
		var httpObj = {
			method : 'GET',
			url : '/api/competences'

		};

		return $http(httpObj);
	}

	return obj;

});

cvApp.service('addCompetenceEltService', function($http) {

	var obj = {};
	obj.req = function($name,$detail, $idCmpt) {
		var httpObj = {
			method : 'POST',
			url : '/api/newcompetenceelt',
			data : {
				"idCmpt" : $idCmpt,
				"detail" : $detail,
				"name" : $name
			}

		};

		return $http(httpObj);
	}

	return obj;

});



cvApp.service('LogOutService', function($http) {

	var obj = {};
	obj.req = function() {
		var httpObj = {
			method : 'POST',
			url : '/api/logout'
		};

		return $http(httpObj);
	}

	return obj;

});


