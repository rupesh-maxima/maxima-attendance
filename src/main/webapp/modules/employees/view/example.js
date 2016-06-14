angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('TimepickerDemoCtrl', function ($scope, $log) {
  $scope.mytime = new Date();

  $scope.hstep = 1;
  $scope.mstep = 15;

  $scope.options = {
    hstep: [1, 2, 3],
    mstep: [1, 5, 10, 15, 25, 30]
  };

  $scope.ismeridian = true;
  $scope.toggleMode = function() {
    $scope.ismeridian = ! $scope.ismeridian;
  };
 /* $scope.update = function() {
    var d = new Date();
    d.setHours( 14 );
    d.setMinutes( 0 );
    $scope.logTime = d;
    $scope.logout = d;
  };*/
  
    $scope.updateLog = function() {
    var d = new Date();
    d.setHours( 14 );
    d.setMinutes( 0 );
    $scope.logTime = d;
  };
  
  $scope.changedLogin = function () {
    $log.log('Time changed to: ' + $scope.logTime);
  };
  
   $scope.updateLogout = function() {
    var d = new Date();
    d.setHours( 14 );
    d.setMinutes( 0 );
    $scope.logout = d;
  };
  
   $scope.changedLogOut = function () {
    $log.log('Time changed to: ' + $scope.logout);
  };
  
   

 /* $scope.changed = function () {
    $log.log('Time changed to: ' + $scope.mytime);
  };*/

 /* $scope.clear = function() {
    $scope.logout = null;
    $scope.logTime = null;
  }*/;
  
  $scope.clear = function() {
	    $scope.logout = null;
	    $scope.logTime = null;

	  };
	  
  $scope.reset=function(){
	  $scope.logout = null;
	    $scope.logTime = null;
	  
  }	;  
                    $scope.today = function() {
					  
					};
					
					$scope.today();

					$scope.clear = function() {
						$scope.holidaysDate = null;
					};

					$scope.open2 = function() {
						$scope.popup2.opened = true;
						  $scope.flag=true;
					};

					$scope.popup2 = {
						opened : false,
						
					};
  
});