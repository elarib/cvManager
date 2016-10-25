cvApp.service('SignUpService', function($http) {
    var obj = {};
    obj.req = function($signUpModel) {
        console.log($signUpModel);
        var httpObj = {
            method : 'POST',
            url : '/api/signup',
            headers : {
                'Content-Type' : 'application/json'
            },
            data : $signUpModel

        };

        return $http(httpObj);
    }

    return obj;

});

cvApp.service('SignInService', function($http) {

    var obj = {};
    obj.req = function($signInModel) {
        var httpObj = {
            method : 'POST',
            url : '/api/signin',
            headers : {
                'Content-Type' : 'application/json'
            },
            data : $signInModel
        };

        return $http(httpObj);
    }

    return obj;

});





