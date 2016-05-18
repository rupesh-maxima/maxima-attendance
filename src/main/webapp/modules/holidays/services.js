'use strict';
angular.module('Holidays').service('HolidaysService',function($http) {

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

	this.add = function(holiday, callback) {
		this.serviceCaller("POST", '/holiday/add', "", callback);
	};
	

	this.update = function(existingObj,callback) {
		this.serviceCaller("POST", '/holiday/update', "", callback);
	};
	
	this.get = function(id,callback) {
		this.serviceCaller("POST", '/holiday/get', "id", callback);
	};
	
	this.getAll = function(ids,callback) {
		this.serviceCaller("POST", '/holiday/getAll', "", callback);
	};

	this.list = function(callback) {
		this.serviceCaller("POST", '/holiday/list', "", callback);
	};
	
	this.search = function(json,callback) {
		this.serviceCaller("POST", '/holiday/search', "", callback);
	};
	
	this.deletee = function(id,callback) {
		this.serviceCaller("POST", '/holiday/delete', "", callback);
	};
	
	this.deleteAll = function(ids,callback) {
		this.serviceCaller("POST", '/holiday/deleteAll', "", callback);
	};
	
	
});


