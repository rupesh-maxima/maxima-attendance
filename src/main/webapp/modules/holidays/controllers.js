
//.............
'use strict';
angular.module('Holidays')
		.controller(
				'HolidaysController',
				function($scope, HolidaysService, $http, $filter) {
					
					$scope.data = [];
					HolidaysService.list(function(response) {
						$scope.data = response.data;
					});

					$scope.closeAlert = function(id, index, data) {
						$scope.data.splice(index, 1);
						HolidaysService.deletee(id, function(response) {
							$scope.value = response.value;
							alert("Data deletee");
						});
					};

					$scope.parseDate = function parseDate(input) {
						var parts = input.split('-');
						return new Date(parts[0], parts[1] - 1, parts[2]);
					};

					$scope.edit = function(index) {
						$scope.flag = true;
						$scope.index = index;
						$scope.holidaysDate = $scope
								.parseDate($scope.data[index].date);
						$scope.holidaysAgr = $scope.data[index].description;
					};

					$scope.updateData = function(index, data) {
						$scope.variable = $filter('date')($scope.holidaysDate,
								"yyyy-MM-dd");
						data[index].date = $scope.variable;
						data[index].description = $scope.holidaysAgr;
						HolidaysService.update(data[index], function(response) {
							$scope.value = response.value;
							console.log(response.index);
							alert("Data Updated");
						});
					};

					$scope.add = function(index, data) {
						var jsonValue = {
							date : $filter('date')($scope.holidaysDate,
									"yyyy-MM-dd"),
							description : $scope.holidaysAgr
						};
						if (jsonValue.date != null
								&& jsonValue.description != null) {
							for ( var i in $scope.data) {
								if (jsonValue.date == $scope.data[i].date) {
									$scope.matchFlag = true;
									alert("Date is already added");
									break;
								}
							}
							if (!$scope.matchFlag) {
								HolidaysService.add(jsonValue, function(
										response) {
									HolidaysService.list(function(response) {
										$scope.data = response.data;
										alert("Data add");
									});
								});
							}
						} else {
							alert("plz provide date and description");
						}
					};

					$scope.validation = function(index, data) {
						if ($scope.holidaysDate == undefined
								|| $scope.holidaysAgr == undefined) {
							alert("This field can't be empty");
						} else {
							$scope.updateData(index, data);
						}
					};

					$scope.today = function() {
						$scope.holidaysDate = null;
					};
					$scope.today();

					$scope.clear = function() {
						$scope.holidaysDate = null;
					};

					$scope.open1 = function() {
						$scope.popup1.opened = true;
					};

					$scope.open2 = function() {
						$scope.popup2.opened = true;
					};

					$scope.popup1 = {
						opened : false
					};

					$scope.popup2 = {
						opened : false
					};

				});
