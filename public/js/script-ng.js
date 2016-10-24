// MODULE



var cvApp = angular.module('cvApp', [ 'ngResource' ]);

cvApp.run(function($rootScope, getUserInfos) {
	// var userID = document.getElementById("userDIV").value;



	getUserInfos.req().then(
		function successCallback(response) {
			console.log(response.data);

			var data = response.data;
			$rootScope.userInfos = data.user;
			$rootScope.firstName = $rootScope.userInfos.firstName;
			$rootScope.lastName = $rootScope.userInfos.lastName;

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
			console.log($rootScope.objectif);

		}, function errorCallback(response, status) {
			console.log(response);
		});

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
//
//cvApp.controller('userInfosController', function($scope, getUserInfosService,
//		editUserInfosService) {
//
//	var numberOfInfos = 0;
//
//	getUserInfosService.req().then(function successCallback(response) {
//
//		numberOfInfos = response.data._embedded.userInfos.length;
//
//		$scope.numberOfInfos = numberOfInfos;
//		$scope.infos = response.data._embedded.userInfos;
//
//		setIconOfInfo($scope.infos);
//		console.log($scope.infos);
//
//	}, function errorCallback(response, status) {
//		console.log(response);
//	});
//
//	$scope.editorEnabled = false;
//
//	$scope.enableEditor = function() {
//		$scope.editorEnabled = true;
//
//	};
//
//	$scope.disableEditor = function() {
//		$scope.editorEnabled = false;
//	};
//
//	$scope.save = function() {
//
//		angular.forEach($scope.infos, function(info, key) {
//
//			editUserInfosService.req(info.id, info.content).then(
//					function successCallback(response) {
//
//					}, function errorCallback(response, status) {
//						console.log(response);
//					});
//		});
//
//		$scope.disableEditor();
//	};
//
//});
//
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
												 editEducationService, $http, $resource) {



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

});

//
//// functions
//
//var setIconOfInfo = function(infos) {
//
//	for (var i = infos.length; i--;) {
//		console.log(i + "  " + infos[i].type);
//		switch (infos[i].type) {
//		case 'HOME':
//			infos[i].icon = 'fa fa-home';
//			break;
//		case 'EMAIL':
//			infos[i].icon = 'fa fa-envelope';
//			break;
//		case 'MOBILEPHONE':
//			infos[i].icon = 'fa fa-mobile';
//			break;
//		case 'FACEBOOK':
//			infos[i].icon = 'fa fa-facebook-square';
//			break;
//		case 'GITHUB':
//			infos[i].icon = 'fa fa-github-square';
//			break;
//		case 'LINKEDIN':
//			infos[i].icon = 'fa fa-linkedin-square';
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//}






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

