'use strict';
angular.module('Project').controller('ProjectController', function($scope, ProjectService) {
	 $scope.months = ['January','February','March','April','May','June','July','August','September','October','November','December'];
	 $scope.closeAlert=function(index){
		$scope.months.splice(index,1); 
		alert("Deleted");
	 };
	 
	 $scope.edit=function($event){
		alert("Edited");
	 };
	 
	 
	ProjectService.list(function(response) {
        if(response.success) {
        	 $scope.months=response;
        	alert(JSON.stringify(response));
        } else {
        	alert(JSON.stringify(response));
        }
    });
	
});

