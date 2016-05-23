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
		 	//alert("Data"+JSON.stringify($scope.data));
		 	//alert("Data"+angular.toJson($scope.data));
		 	$scope.index=index;
		    $scope.projectName=$scope.data[index].name;
		    $scope.projectAgr=$scope.data[index].agreement;
	 };
	 
	 
	 $scope.updateData=function(index,data){
		 alert(JSON.stringify(data[index]));
		data[index].name=$scope.projectName;
		data[index].agreement=$scope.projectAgr;
		//$scope.passData = {"id": index, "name": data[index].name, "agreement": data[index].agreement};
		alert("changed"+angular.toJson(data));
		   ProjectService.update(data,function(response){
				alert(JSON.stringify(response));
				$scope.data=response.data;
			});
	 };
	 
});

