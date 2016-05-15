<html ng-app="myApp">
<head>
<title>Spring MVC + AngularJS Demo</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	app.service('demoService', function($http) {
		this.getFunction = function() {
			return $http({
				method : 'GET',
				url : '/project/list.json'
			});
		};

		this.getDataSeriveExample = function() {
			var promise = $http.get('project/list').success(
					function(data, status, headers, config) {
						return data;
					});

			return promise;
		};
		function studentController($scope,$http) {
            var url = "data.txt";
         
            $http.get(url).success( function(response) {
               $scope.students = response;
            });
         }
	});

	app.controller('demoController', function($scope, demoService) {
		demoService.getDataSeriveExample().then(function(response) {
			$scope.x=response;
			alert(JSON.stringify(response));
		});
	});
</script>
</head>
<body>
	<div ng-controller="demoController">
		<div>
			<ul>
				<li ng-repeat="y in x.data">{{ y.name }}</li>
			</ul>
		</div>
	</div>
</body>
</html>
