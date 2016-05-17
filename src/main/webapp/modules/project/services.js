'use strict';
 
angular.module('Project').factory('ProjectService',
    ['$http','$rootScope', function ($http,$rootScope) {
        var service = {};
        service.get = function (id,data) {
            $http.post('project/get',  id)
                .success(function (data, status, headers, config) {
                	console.log("result"+JSON.stringify(data));
                	$rootScope.data = data;
                	var response = { success: data !=''};
                	if(!response.success) {
                        response.message = 'Id not found!';
                    }else{
                    	response.data = data.data;
                    }
                }).error(function(data, status, headers, config) {
                	var response = { success: false};
                    response.message = 'Error while connecting to server';
        		}); 
            return data;
        };
        return service;
}]);



