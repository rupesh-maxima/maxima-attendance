'use strict';
angular.module('Employees').filter('unique', function() {
	   return function(collection, keyname) {
		      var output = [],keys = [];

		      angular.forEach(collection, function(item) {
		          var key = item[keyname];
		          if(keys.indexOf(key) === -1) {
		              keys.push(key);
		              output.push(item);
		          }
		      });
		      return output;
		   };
		});
	
angular.module('Employees').controller('EmployeesController',function($scope,ProjectService, EmployeesService,$http,$timeout) {
	$scope.data=[];
	$scope.flags = false;
	$scope.showAlerts = '';
	$scope.class='';
	EmployeesService.list(function(response){
		$scope.data=response.data;
	});
	
	$scope.clearAlert = function() {
		$timeout(function() {
			$scope.showAlerts = '';
			$scope.class='';
		}, 3000);
	};
	
	
	ProjectService.list(function(response) {
		$scope.projectList = response.data;
		});


	$scope.edit = function(index) {
		$scope.flag = true;
		$scope.index = index;
		$scope.id=$scope.data[index].id;
		$scope.name=$scope.data[index].name;
		$scope.firstName = $scope.data[index].firstName;
		$scope.lastName = $scope.data[index].lastName;
		$scope.projectName = $scope.data[index].project.name;
		$scope.employeeId = $scope.data[index].employeeId;
		$scope.active = $scope.data[index].active;
		$scope.flags =true;
	};
   
	
	    $scope.updateData = function(index, data) {
	    	
		$scope.tempData = {"id":$scope.id, "firstName":$scope.firstName, "lastName":$scope.lastName,"employeeId": $scope.employeeId
				,"active":$scope.active,"project":{"name":$scope.projectName}};
		alert(JSON.stringify($scope.tempData));
		EmployeesService.update($scope.tempData, function(response) {
				EmployeesService.list(function(response){
					$scope.data=response.data;
					$scope.value = response.value;
					setTimeout(function(){
						$scope.flags = false;
						$scope.buttonDisabled = false;
					 $scope.flag = false; }, 3000);
					 $scope.firstName = '';
						$scope.lastName = '';
						$scope.projectName = '';
						$scope.employeeId = '';
						$scope.active = 'null';
						
						$scope.buttonDisabled = true;
					 $scope.showAlerts = "Employee update successfully";
					 $scope.class="alert-success";
					 $scope.clearAlert();
				});
		  });
		};
	
	$scope.add = function(index, data) {
		var jsonValue = {
			firstName : $scope.firstName,
			lastName:$scope.lastName,
			employeeId:$scope.employeeId,
			active :$scope.active,
			project: {name: $scope.projectName}
		};
		alert(JSON.stringify(jsonValue));
		if (jsonValue.active != null && jsonValue.firstName != null && jsonValue.lastName!=null && jsonValue.employeeId!=null && jsonValue.project!=null) {
			for ( var i in $scope.data) {
				if (jsonValue.employeeId == $scope.data[i].employeeId) {
					$scope.matchFlag = true;
					setTimeout(function(){
						$scope.flags = false;
						$scope.buttonDisabled = false;
					 $scope.flag = false; }, 3000);
					 $scope.firstName = '';
						$scope.lastName = '';
						$scope.projectName = '';
						$scope.employeeId = '';
						$scope.active = 'null';
					$scope.buttonDisabled = true;
					 $scope.showAlerts = "Employee id exist";
					 $scope.class="alert-danger";
					 $scope.clearAlert();
					break;
				}
			}
			if (!$scope.matchFlag) {
				EmployeesService.add(jsonValue, function(
						response) {
					     EmployeesService.list(function(response) {
						 $scope.data = response.data;
						 setTimeout(function(){
								$scope.flags = false;
							 $scope.flag = false; }, 3000);
						 $scope.firstName = '';
							$scope.lastName = '';
							$scope.projectName = '';
							$scope.employeeId = '';
							$scope.active = 'null';
						 $scope.showAlerts = "Employee added Successfully";
						 $scope.class="alert-success";
						 $scope.clearAlert();
					});
				});
			}
		} else {
			 $scope.showAlerts = "Please fill the all fields";
			 $scope.class="alert-danger";
			 $scope.clearAlert();
		}
	};

	$scope.validation = function(index, data) {
		
		if ($scope.active == undefined || $scope.firstName == undefined || $scope.lastName == undefined || $scope.employeeId == undefined || $scope.projectName==undefined) {
			 $scope.showAlerts = "Please fill the fields";
			 $scope.class="alert-danger";
			 $scope.clearAlert();
		} else {
			$scope.updateData(index, data);
		}
	};
	
	$scope.show = function() {
		$scope.flags = true;
	};

	});
