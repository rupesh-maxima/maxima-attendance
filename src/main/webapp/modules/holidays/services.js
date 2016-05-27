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
		this.serviceCaller("POST", '/holiday/add', holiday, callback);
	};
	

	this.update = function(existingObj,callback) {
		this.serviceCaller("POST", '/holiday/update', existingObj, callback);
	};
	
	this.get = function(id,callback) {
		this.serviceCaller("POST", '/holiday/get', id, callback);
	};
	
	this.getAll = function(ids,callback) {
		this.serviceCaller("POST", '/holiday/getAll', ids, callback);
	};

	this.list = function(callback) {
		this.serviceCaller("POST", '/holiday/list', "", callback);
	};
	
	this.search = function(json,callback) {
		this.serviceCaller("POST", '/holiday/search', json, callback);
	};
	
	this.deletee = function(id,callback) {
		this.serviceCaller("POST", '/holiday/delete', id, callback);
	};
	
	this.deleteAll = function(ids,callback) {
		this.serviceCaller("POST", '/holiday/deleteAll', ids, callback);
	};
	
	
});


