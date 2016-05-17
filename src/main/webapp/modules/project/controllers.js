'use strict';
	angular.module('Project').controller('ProjectController', function($scope, ProjectService) {
	$scope.checked=true;
	$scope.id = 1;
	$scope.data = ProjectService.get($scope.id);
	console.log('data: '+ $scope.data);
});
