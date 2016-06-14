
'use strict';
angular
		.module('Holidays')
		.controller(
				'HolidaysController',
				function($scope, HolidaysService, $http, $filter, $timeout) {
					$scope.flags = false;
					$scope.data = [];

					$scope.showAlert = '';
					$scope.class = '';

					HolidaysService.list(function(response) {
						$scope.data = response.data;
					});

					$scope.clearAlert = function() {
						$timeout(function() {
							$scope.showAlert = '';
							$scope.class = '';
						}, 3000);
					};

					$scope.closeAlert = function(data, index) {
						HolidaysService
								.deletee(
										$scope.data[index].id,
										function(response) {
											if (response.success) {
												HolidaysService
														.list(function(response) {
															$scope.data = response.data;
															$scope.holidaysDate = '';
															$scope.holidaysAgr = '';
															$scope.showAlert = "Data deleted successfully";
															$scope.class = "alert-success";
															$scope.clearAlert();
														});
											} else {
												$scope.showAlert = "Data Not deleted";
												$scope.class = "alert-success";
												$scope.clearAlert();
											}
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
						$scope.flags =true;
					};

					$scope.updateData = function(index, data) {
						if ($scope.holidaysDate == ''
								&& $scope.holidaysAgr == '') {
							$scope.showAlert = "please fill out the field";
							$scope.class = "alert-warning";
							$scope.clearAlert();
						
						} else {
							$scope.variable = $filter('date')(
									$scope.holidaysDate, "yyyy-MM-dd");
							data[index].date = $scope.variable;
							data[index].description = $scope.holidaysAgr;
							HolidaysService.update(data[index], function(
									response) {
								$scope.value = response.value;
								
								setTimeout(function(){
								$scope.flags = false;
								$scope.flag = false;
								$scope.buttonDisabled = false;}, 3000);
								
								$scope.holidaysDate = '';
							    $scope.holidaysAgr = '';
							    $scope.buttonDisabled = true;
							    $scope.showAlert = "Update data successfully";
								$scope.class = "alert-success";
								$scope.clearAlert();
								
								
							});
						}
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
									setTimeout(function(){
										$scope.flags = false;
										$scope.flag = false;
										$scope.buttonDisabled = false;}, 3000);
									$scope.holidaysDate = '';
									$scope.holidaysAgr = '';
									$scope.buttonDisabled = true;
									$scope.showAlert = "Date is already added";
									$scope.class = "alert-danger";
									$scope.clearAlert();
									break;
								}
							}
							if (!$scope.matchFlag) {
								HolidaysService
										.add(
												jsonValue,
												function(response) {
													HolidaysService
															.list(function(
																	response) {
																setTimeout(function(){
																	$scope.flags = false;
																 $scope.flag = false;
																 $scope.buttonDisabled = false;}, 3000);
																$scope.data = response.data;
																$scope.holidaysDate = '';
																$scope.holidaysAgr = '';
																$scope.buttonDisabled = true;
																$scope.showAlert = "Date add successfully";
																$scope.class = "alert-success";
																$scope
																		.clearAlert();
															});
												});
							}
						} else {

							$scope.showAlert = "Please provide date and description";
							$scope.class = "alert-danger";
							$scope.clearAlert();
						}
					};
				
					$scope.validation = function(index, data) {
						if ($scope.holidaysDate == undefined
								|| $scope.holidaysAgr == undefined) {

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

					$scope.open2 = function() {
						$scope.popup2.opened = true;
					};

					$scope.popup2 = {
						opened : false
					};
					$scope.show = function() {
						$scope.flags = true;
					};

				});
