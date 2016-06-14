'use strict';
angular.module('Employees').service('EmployeesService',function($http) {

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
	
	this.list = function(callback) {
		this.serviceCaller("POST", '/employee/list', "", callback);
	};
	
	this.add=function(employees,callback){
		this.serviceCaller("POST", '/employee/add', employees, callback);
	};
	
	this.update=function(existingObj,callback){
		this.serviceCaller("POST", '/employee/update', existingObj, callback);
	};

	this.getAll = function(ids, callback) {
		this.serviceCaller("POST", '/employee/get', ids, callback);
	};
	
	this.get = function(id, callback) {
		this.serviceCaller("POST", '/employee/get', id, callback);
	};
	
	this.search = function(json, callback) {
		this.serviceCaller("POST", '/employee/search', json, callback);
	};
	
	this.deletee = function(id, callback) {
		this.serviceCaller("POST", '/employee/delete', id, callback);
	};
	
	this.deleteeAll = function(ids, callback) {
		this.serviceCaller("POST", '/employee/deleteAll', ids, callback);
	};
	
	this.getAllActiveEmployees = function( callback) {
		this.serviceCaller("POST", '/employee/getAllActiveEmployees',"", callback);
	};
	
});


