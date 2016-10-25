// MODULE



var cvApp = angular.module('cvApp', [ 'ngResource', 'ngCookies' ]);

cvApp.run(function($rootScope, getUserInfos) {
	// var userID = document.getElementById("userDIV").value;



	$rootScope.getUserInfos = function(){
		getUserInfos.req().then(
			function successCallback(response) {


				var data = response.data;
				$rootScope.userInfos = data.user;
				$rootScope.firstName = $rootScope.userInfos.firstName;
				$rootScope.lastName = $rootScope.userInfos.lastName;
				$rootScope.imgHash = $rootScope.userInfos.imgHash;

				$rootScope.fullName = $rootScope.userInfos.firstName + " "
					+ $rootScope.userInfos.lastName;
				$rootScope.description = $rootScope.userInfos.description;
				$rootScope.email = $rootScope.userInfos.email;
				$rootScope.age = $rootScope.userInfos.age;

				$rootScope.objectif = data.objectif;

				$rootScope.workexperiences = data.workexperiences;

				$rootScope.educations = data.educations;

				$rootScope.experiences = data.workexperiences;

				$rootScope.competences = data.competences;
				$rootScope.competencesSize = (12/$rootScope.competences.length);



			}, function errorCallback(response, status) {
				console.log(response);
			});

	}
	$rootScope.getUserInfos();
	//$rootScope.userID = angular.element('#userID')[0].value;

});



cvApp.controller('userInfoController', function($scope,
												editUserInfosService, $rootScope) {


	$scope.editorEnabled = false;

	$scope.enableEditor = function() {
		$scope.editorEnabled = true;
		$scope.editableFirstName = $scope.firstName;
		$scope.editableLastName = $scope.lastName;
		$scope.editableDescription = $scope.description;
		$scope.editableAge = $scope.age;
		$scope.editableEmail = $scope.email;
	};

	$scope.disableEditor = function() {
		$scope.editorEnabled = false;
	};

	$scope.save = function() {


		editUserInfosService.req($rootScope.userInfos.id, $scope.editableEmail, $scope.editableFirstName, $scope.editableLastName, $scope.editableAge, $scope.editableDescription).then(function successCallback(response) {
			$scope.firstName = $scope.editableFirstName;
			$scope.lastName = $scope.editableLastName;
			$scope.fullName = $scope.editableFirstName + ' '
				+ $scope.editableLastName;
			$scope.description = $scope.editableDescription;
			$scope.age = $scope.editableAge;
			$scope.email = $scope.editableEmail;
			console.log(response);

		}, function errorCallback(response, status) {
			console.log(response);
		});

		$scope.disableEditor();
	};




});

cvApp.controller('objectifController', function($scope,
		editObjectifService, $http, $resource) {



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
		$scope.disableEditor();
	};

	$scope.save = function() {
		$scope.title = $scope.editableTitle;

		editObjectifService.req($scope.objectif.id, $scope.objectif.content).then(
				function successCallback(response) {

					console.log(response);

				}, function errorCallback(response, status) {
					console.log(response);
				});

		$scope.disableEditor();
	};

});

cvApp.controller('educationController', function($scope,
												 editEducationService, $http, $resource, addEducationService) {



	$scope.editorEnabled = false;

	$scope.enableEditor = function($clickedElt) {
		console.log("hoho");
		$scope.editorEnabled = true;
		$scope.clickedElt = $clickedElt;
		$scope.editableDescription = $scope.educations[$clickedElt].description;
		$scope.editablePlace = $scope.educations[$clickedElt].place;
		$scope.editableYearFrom =  $scope.educations[$clickedElt].yearFrom;
		$scope.editableYearTo =  $scope.educations[$clickedElt].yearTo ;

	};

	$scope.disableEditor = function() {

		$scope.editorEnabled = false;
	};
	$scope.save = function($desc, $place, $yearFrom, $yearTo) {
		console.log($scope);
		editEducationService.req($scope.educations[$scope.clickedElt].id, $desc, $place,  $yearFrom, $yearTo).then(
			function successCallback(response) {

				console.log(response);

				$scope.educations[$scope.clickedElt].description= $desc ;
				$scope.educations[$scope.clickedElt].place = $place;
				$scope.educations[$scope.clickedElt].yearFrom = $yearFrom;
				$scope.educations[$scope.clickedElt].yearTo = $yearTo  ;
			}, function errorCallback(response, status) {
				console.log(response);
			});

		$scope.disableEditor();
	};



	$scope.newEditorEnabled= false;

	$scope.addNew = function(){
		$scope.newEditorEnabled = true;


	}

	$scope.saveNew =function($newDescription, $newPlace, $newYearFrom, $newYearTo){

		addEducationService.req($newDescription, $newPlace,  $newYearFrom, $newYearTo).then(
			function successCallback(response) {

				console.log(response);

				$scope.educations.push({
					"id" : response.data ,
					"description" : $newDescription,
					"place" : $newDescription,
					"yearFrom" : $newYearFrom,
					"yearTo" : $newYearTo

				})
			}, function errorCallback(response, status) {
				console.log(response);
			});
		$scope.disableNewAddEditor();
	}


	$scope.disableNewAddEditor = function() {

		$scope.newEditorEnabled = false;
	};

});



cvApp.controller('workController', function($scope,
												 editWorkService, $http, $resource, addWorkService) {



	$scope.editorEnabled = false;

	$scope.enableEditor = function($clickedElt) {
		console.log("hoho");
		$scope.editorEnabled = true;
		$scope.clickedElt = $clickedElt;
		$scope.editableTitle = $scope.experiences[$clickedElt].title;
		$scope.editableDescription = $scope.experiences[$clickedElt].description;
		$scope.editablePlace = $scope.experiences[$clickedElt].place;
		$scope.editableYearFrom =  $scope.experiences[$clickedElt].yearFrom;
		$scope.editableYearTo =  $scope.experiences[$clickedElt].yearTo ;

	};

	$scope.disableEditor = function() {

		$scope.editorEnabled = false;
	};
	$scope.save = function($title, $desc, $place, $yearFrom, $yearTo) {
		console.log($scope);
		editWorkService.req($scope.experiences[$scope.clickedElt].id, $title, $desc, $place,  $yearFrom, $yearTo).then(
			function successCallback(response) {

				console.log(response);

				$scope.experiences[$scope.clickedElt].description= $desc ;
				$scope.experiences[$scope.clickedElt].place = $place;
				$scope.experiences[$scope.clickedElt].yearFrom = $yearFrom;
				$scope.experiences[$scope.clickedElt].yearTo = $yearTo  ;
			}, function errorCallback(response, status) {
				console.log(response);
			});

		$scope.disableEditor();
	};

	$scope.newEditorEnabled= false;

	$scope.addNew = function(){
		$scope.newEditorEnabled = true;


	}

	$scope.saveNew =function($newTitle, $newDescription, $newPlace, $newYearFrom, $newYearTo){

		addWorkService.req($newTitle, $newDescription, $newPlace,  $newYearFrom, $newYearTo).then(
			function successCallback(response) {

				console.log(response);

				$scope.experiences.push({
					"id" : response.data ,
					"title" :  $newTitle,
					"description" : $newDescription,
					"place" : $newDescription,
					"yearFrom" : $newYearFrom,
					"yearTo" : $newYearTo

				})
			}, function errorCallback(response, status) {
				console.log(response);
			});
		$scope.disableNewAddEditor();
	}


	$scope.disableNewAddEditor = function() {

		$scope.newEditorEnabled = false;
	};

});




cvApp.controller('competenceController', function($scope, $rootScope,
												  editCompetenceService, editCompetenceEltService, $http, $resource, addCompetenceService, addCompetenceEltService, findAllCompetenceService) {



	$scope.editorCompetenceEnabled = false;

	$scope.enableCompetenceEditor = function($activeCompetence) {
		$scope.editorCompetenceEnabled = true;
		console.log($activeCompetence);
		$scope.activeCompetence = $activeCompetence;
		$scope.editableCompetence = $activeCompetence;


	};

	$scope.disableCompetenceEditor = function() {

		$scope.editorCompetenceEnabled = false;
	};


	$scope.saveComeptence = function($id, $name) {


		editCompetenceService.req($id, $name).then(
			function successCallback(response) {
				$scope.competences.map(function(cmpt){
					if(cmpt.idCmpt == $id)
						cmpt.nameCmpt = $name;
					return cmpt;
				})
				console.log(response);

			}, function errorCallback(response, status) {
				console.log(response);
			});

		$scope.disableCompetenceEditor();
	};



	$scope.editorCompetenceEltEnabled = false;

	$scope.enableCompetenceEltEditor = function($activeEltCompetence) {
		$scope.editorCompetenceEltEnabled = true;
		$scope.activeEltCompetence = $activeEltCompetence;
		$scope.editableEltCompetence = $activeEltCompetence;


	};

	$scope.disableCompetenceEltEditor = function() {

		$scope.editorCompetenceEltEnabled = false;
	};

	$scope.saveComeptenceElt = function($id, $name, $detail) {

		console.log($id, $name, $detail);
		editCompetenceEltService.req($id, $name, $detail).then(
			function successCallback(response) {
				$scope.competences.map(function(cmpt){
					return cmpt.elts.map(function(elt){
						if(elt.idElt == $id)
						{
							elt.nameElt = $name,
							elt.detailElt = $detail;
						}
					})
				})
				console.log(response);

			}, function errorCallback(response, status) {
				console.log(response);
			});

		$scope.disableCompetenceEltEditor();
	};


	$scope.newEditorEnabled= false;

	$scope.addNew = function(){

		$scope.newEditorEnabled = true;
	}

	$scope.saveNew =function($newName){

		addCompetenceService.req($newName).then(
			function successCallback(response) {

				console.log(response);

				$scope.competences.push({
					"id" : response.data ,
					"name" :  $newName
				})
			}, function errorCallback(response, status) {
				console.log(response);
			});
		$scope.disableNewAddEditor();
	}


	$scope.disableNewAddEditor = function() {

		$scope.newEditorEnabled = false;
	};



	$scope.findAll = function(){
		findAllCompetenceService.req().then(
			function successCallback(response) {
				$scope.allCompetences = response.data;
				console.log(response);


			}, function errorCallback(response, status) {
				console.log(response);
			});
	}


	$scope.allCompetences=[];
	$scope.newEditorEltEnabled= false;

	$scope.addNewElt = function(){


		$scope.findAll();
		$scope.newEditorEltEnabled = true;
	}

	$scope.saveNewElt =function($idCmpt, $newName, $newDetail){
		console.log($idCmpt, $newName, $newDetail);
		addCompetenceEltService.req($newName, $newDetail, Number($idCmpt)).then(
			function successCallback(response) {

				console.log(response);

				$rootScope.getUserInfos();
			}, function errorCallback(response, status) {
				console.log(response);
			});
		$scope.disableNewEltAddEditor();
	}


	$scope.disableNewEltAddEditor = function() {

		$scope.newEditorEltEnabled = false;
	};


});


cvApp.controller('SignInController', function($scope,  SignInService, SignUpService, $http, $resource, $window) {

	$scope.signInModel ={};

	$scope.signin = function(){
		SignInService.req($scope.signInModel).then(function successCallback(response) {

			console.log(response);

			$window.location.href = "/";

		}, function errorCallback(response, status) {

			console.log(response);
			$scope.error=response.data.message;
		});


	}

});

cvApp.controller('SignUpController', function($scope,  SignUpService, SignUpService, $http, $resource) {

	$scope.signUpModel ={};

	$scope.signup = function(){
		console.log($scope.signUpModel);
		SignUpService.req($scope.signUpModel).then(function successCallback(response) {

			console.log(response);

		}, function errorCallback(response, status) {

			console.log(response);
			$scope.error=response.data.message;
		});


	}

});

cvApp.controller('logoutController', function($scope, $rootScope, $window, LogOutService) {

		$scope.logout = function(){
			LogOutService.req().then(function(response){
					console.log(response);
				$window.location.reload();
			},function(response){
				console.log(response);
			})



		}
	var delete_cookie = function(name) {
		document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	};

});

