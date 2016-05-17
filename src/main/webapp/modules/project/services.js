'use strict';
angular.module('Project').service('ProjectService',function($http) {

	this.serviceCaller = function(method, url, requestBody, callback) {
		$http({
			method : method,
			url : url,
			data : requestBody
		}).success(function(data, status, headers, config) {
			var response = {
				success : true,
				data : data
			};
			callback(response);
		}).error(function(data, status, headers, config) {
			var response = {
				success : false,
				message : 'Error while connecting to server'
			};
			callback(response);
		});
	};

	this.get = function(id, callback) {
		this.serviceCaller("POST", '/project/get', id, callback);
	};
	
	this.list = function(id, callback) {
		this.serviceCaller("POST", '/project/list', "", callback);
	};

});


