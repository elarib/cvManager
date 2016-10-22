// MODULE



var cvApp = angular.module('cvApp', [ 'ngResource' ]);

cvApp.run(function($rootScope) {
	// var userID = document.getElementById("userDIV").value;
	//$rootScope.userID = angular.element('#userID')[0].value;

});

cvApp.controller('userController', function($scope, getUserService,
		editUserService) {

	getUserService.req().then(
			function successCallback(response) {
				console.log(response.data);
				$scope.firstName = response.data.firstName;
				$scope.lastName = response.data.lastName;

				$scope.fullName = response.data.firstName + " "
						+ response.data.lastName;
				$scope.description = response.data.description;
				$scope.email = response.data.email;

			}, function errorCallback(response, status) {
				console.log(response);
			});

	$scope.editorEnabled = false;

	$scope.enableEditor = function() {
		$scope.editorEnabled = true;
		$scope.editablefirstName = $scope.firstName;
		$scope.editablelastName = $scope.lastName;
		$scope.editableDescription = $scope.description;
	};

	$scope.disableEditor = function() {
		$scope.editorEnabled = false;
	};

	$scope.save = function() {
		$scope.title = $scope.editableTitle;
		$scope.firstName = $scope.editablefirstName;
		$scope.lastName = $scope.editablelastName;
		$scope.fullName = $scope.editablefirstName + ' '
				+ $scope.editablelastName;
		$scope.description = $scope.editableDescription;

		$infos = [ $scope.firstName, $scope.lastName, $scope.description ];
		editUserService.req($infos).then(function successCallback(response) {

			console.log(response);

		}, function errorCallback(response, status) {
			console.log(response);
		});

		$scope.disableEditor();
	};

});

cvApp.controller('userInfosController', function($scope, getUserInfosService,
		editUserInfosService) {

	var numberOfInfos = 0;

	getUserInfosService.req().then(function successCallback(response) {

		numberOfInfos = response.data._embedded.userInfos.length;

		$scope.numberOfInfos = numberOfInfos;
		$scope.infos = response.data._embedded.userInfos;

		setIconOfInfo($scope.infos);
		console.log($scope.infos);

	}, function errorCallback(response, status) {
		console.log(response);
	});

	$scope.editorEnabled = false;

	$scope.enableEditor = function() {
		$scope.editorEnabled = true;

	};

	$scope.disableEditor = function() {
		$scope.editorEnabled = false;
	};

	$scope.save = function() {

		angular.forEach($scope.infos, function(info, key) {

			editUserInfosService.req(info.id, info.content).then(
					function successCallback(response) {

					}, function errorCallback(response, status) {
						console.log(response);
					});
		});

		$scope.disableEditor();
	};

});

cvApp.controller('objectifController', function($scope, getObjectifService,
		editObjectifService, $http, $resource) {

	getObjectifService.req().then(function successCallback(response) {

		$scope.title = response.data._embedded.objectifs[0].content;
		$scope.objectifID = response.data._embedded.objectifs[0].id;

	}, function errorCallback(response, status) {
		console.log(response);
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
		$scope.disableEditor();
	};

	$scope.save = function() {
		$scope.title = $scope.editableTitle;

		editObjectifService.req($scope.objectifID, $scope.title).then(
				function successCallback(response) {

					console.log(response);

				}, function errorCallback(response, status) {
					console.log(response);
				});

		$scope.disableEditor();
	};

});

// functions

var setIconOfInfo = function(infos) {

	for (var i = infos.length; i--;) {
		console.log(i + "  " + infos[i].type);
		switch (infos[i].type) {
		case 'HOME':
			infos[i].icon = 'fa fa-home';
			break;
		case 'EMAIL':
			infos[i].icon = 'fa fa-envelope';
			break;
		case 'MOBILEPHONE':
			infos[i].icon = 'fa fa-mobile';
			break;
		case 'FACEBOOK':
			infos[i].icon = 'fa fa-facebook-square';
			break;
		case 'GITHUB':
			infos[i].icon = 'fa fa-github-square';
			break;
		case 'LINKEDIN':
			infos[i].icon = 'fa fa-linkedin-square';
			break;

		default:
			break;
		}

	}

}






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

