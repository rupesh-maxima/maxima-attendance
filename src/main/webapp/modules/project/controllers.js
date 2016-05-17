'use strict';
angular.module('Project').controller('ProjectController', function($scope, ProjectService) {
/*	$scope.id = 1;*/

	ProjectService.list(function(response) {

        if(response.success) {
        	alert(JSON.stringify(response));
        } else {
        	alert(JSON.stringify(response));
        }
    });
	
});

