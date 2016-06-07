'use strict';
angular.module('Timelog').controller('TimelogController', function($scope, $timeout, TimelogService,EmployeeService) {
	$scope.flag = true;
	$scope.leave = false;
	$scope.FromHome=false;
	$scope.loginStatus=false;
	
	TimelogService.getTimelogsForToday(function(response) {
        if(response.success) {
        	$scope.data=response.data;
        	for(var i in $scope.data) {
        		if ($scope.data[i].loggedIn == true) {
        			$scope.data[i].alertType = 'success';
        		} else if ($scope.data[i].loggedOut == true) {
        			$scope.data[i].alertType = 'danger';
        		} else if ($scope.data[i].workFromHome == true) {
         			$scope.data[i].alertType = 'info';
         		}  else if ($scope.data[i].onLeave == true) {
         			 $scope.data[i].alertType = 'warning';
        		}else {
         			$scope.data[i].alertType = 'danger';
        		}
        		
        		if ($scope.data[i].loggedIn || $scope.data[i].loggedOut || $scope.data[i].workFromHome || $scope.data[i].onLeave)
        			$scope.data[i].dayCount = true;
        		else
        			$scope.data[i].dayCount = false;
        		
        	}
        } 
    });
		
	
	$scope.edit = function(index) {
		$timeout.cancel($scope.promise);
		$scope.promise= $timeout(function () { $scope.flag = true; 
		$timeout.cancel($scope.promise);	
		}, 9000); 
		
			$scope.flag = false;
			$scope.index = index;
			$scope.employeeName = $scope.data[index].firstName + ' ' + $scope.data[index].lastName;
			$scope.message = $scope.data[index].status[0];
			$scope.logoutMessage=$scope.data[index].status[1];
			
			if ($scope.data[index].loggedIn || $scope.data[index].loggedOut || $scope.data[index].workFromHome || $scope.data[index].onLeave){
				$scope.data[index].dayCount = true;
			}
    		else{
    			$scope.data[index].dayCount = false;
    			}
			
	};
	
	
	$scope.login= function(){
		var index = $scope.index;
		TimelogService.login($scope.data[index].employeeId, function(response){
			 if(response.success) {
				 $scope.data[index]=response.data;
				 $scope.loginStatus = true;
				 $scope.data[index].dayCount = true;
				 $scope.message = $scope.data[index].status[0];
				 $scope.data[index].alertType = 'success';
	        }
		});
	};
	
	$scope.logout=function(){
		var index = $scope.index;
		TimelogService.logout($scope.data[$scope.index].employeeId,function(response) {
	        if(response.success) {
	        	 $scope.data[index]=response.data;
	        	 $scope.data[index].dayCount = true;
	        	 $scope.logoutMessage=$scope.data[index].status[1];
	        	 $scope.data[index].alertType = 'danger';
	        	
	        } else {
	        	alert("Logout Failed");
	        }
	    });
		
	};
	
	$scope.workFromHome=function(){
		var index = $scope.index;
		var d=new Date();
		var date = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2)+ "/" + d.getFullYear();
		var jsonValue = {
				recordDate : date,
				employeeIds :[$scope.data[index].employeeId],
				isWorkFromHome:true
			};
		
		TimelogService.setWorkfromHome(jsonValue,function(response) {
	        if(response.success) {
	        	TimelogService.getTimelogsForToday(function(response) {
	                if(response.success) {
	                	$scope.data=response.data;
	                	$scope.data[index].dayCount = true;
	                	$scope.message = $scope.data[index].status[0];
	                	$scope.FromHome=true;
	                	for (var a in $scope.data) {
	                		 if ($scope.data[a].loggedIn == true) {
	                 			$scope.data[a].alertType = 'success';
	                 		} else if ($scope.data[a].loggedOut == true) {
	                 			$scope.data[a].alertType = 'danger';
	                 		} else if ($scope.data[a].workFromHome == true) {
	                  			$scope.data[a].alertType = 'info';
	                  		}  else if ($scope.data[a].onLeave == true) {
	                  			 $scope.data[a].alertType = 'warning';
	                 		}else {
	                  			$scope.data[a].alertType = 'danger';
	                 		} 
	                	 }
	                	$scope.data[index].alertType = 'info';
	                } else {
	                	alert("No List found");
	                }
	            });
	        } else {
	        	alert(JSON.stringify(response));
	        }
	    });
		
	};
	
	
	$scope.onLeave=function(){
		var index = $scope.index;
		var d=new Date();
		var date = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2)+ "/" + d.getFullYear();
		var jsonValue = {
				recordDate : date,
				employeeIds :[$scope.data[index].employeeId],
				isOnLeave:true
			};
		TimelogService.setOnLeave(jsonValue,function(response) {
	        if(response.success) {
	        	TimelogService.getTimelogsForToday(function(response) {
	                if(response.success) {
	                	$scope.data=response.data;
	                	$scope.data[index].dayCount = true;
	                	 $scope.message = $scope.data[index].status[0];
	                	 $scope.leave = true;
	                	 for (var a in $scope.data) {
	                		 if ($scope.data[a].loggedIn == true) {
	                 			$scope.data[a].alertType = 'success';
	                 		} else if ($scope.data[a].loggedOut == true) {
	                 			$scope.data[a].alertType = 'danger';
	                 		} else if ($scope.data[a].workFromHome == true) {
	                  			$scope.data[a].alertType = 'info';
	                  		}  else if ($scope.data[a].onLeave == true) {
	                  			 $scope.data[a].alertType = 'warning';
	                 		}else {
	                  			$scope.data[a].alertType = 'danger';
	                 		} 
	                	 }
	                	$scope.data[index].alertType = 'warning';
	                } else {
	                	alert("No List found");
	                }
	            });
	        } else {
	        	alert(JSON.stringify(response));
	        }
	    });
	};
});

