'use strict';
angular.module('Timelog').service('EmployeeService',function($http) {

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
	
	this.add = function(employee,callback) {
		this.serviceCaller("POST", '/employee/add',employee, callback);
	};
	
	this.update = function(existingObj,callback) {
		this.serviceCaller("POST", '/employee/update',existingObj, callback);
	};
	
	this.get = function(id,callback) {
		this.serviceCaller("POST", '/employee/get',id, callback);
	};
	
	this.getAll = function(ids,callback) {
		this.serviceCaller("POST", '/employee/getAll',ids, callback);
	};
	
	this.search = function(json,callback) {
		this.serviceCaller("POST", '/employee/search',json, callback);
	};
	
	this.deletee = function(id,callback) {
		this.serviceCaller("POST", '/employee/delete',id, callback);
	};
	
	this.deleteAll = function(ids,callback) {
		this.serviceCaller("POST", '/employee/deleteAll',ids, callback);
	};
	
	this.getAllActiveEmployees = function(callback) {
		this.serviceCaller("POST", '/employee/getAllActiveEmployees',"", callback);
	};
	
	this.getAllActiveEmployeesInProject = function(projectId,callback) {
		this.serviceCaller("POST", '/employee/getAllActiveEmployeesInProject',projectId, callback);
	};
	
});
