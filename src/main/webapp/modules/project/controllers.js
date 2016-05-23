'use strict';
angular.module('Project').controller('ProjectController', function($scope, ProjectService,$http) {
	$scope.data=[];
	ProjectService.list(function(response){
		$scope.data=response.data;
	});
	
	
	$scope.closeAlert=function(index){
		$scope.data.splice(index,1); 
		alert("Deleted");	
	 };
	 
	 $scope.edit=function(index){
		 	$scope.index=index;
		    $scope.projectName=$scope.data[index].name;
		    $scope.projectAgr=$scope.data[index].agreement;
	 };
	  
	 
	 $scope.updateData=function(index,data){
		 data[index].name=$scope.projectName;
		 data[index].agreement=$scope.projectAgr;
		   ProjectService.update(data[index],function(response){
				$scope.value=response.value;
				alert("Data Updated");
			});
	 };
	 
});

