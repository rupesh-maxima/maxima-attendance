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
	

	this.list = function(callback) {
		this.serviceCaller("POST", '/project/list', "", callback);
	};
	
	this.add=function(project,callback){
		this.serviceCaller("POST", '/project/add', "", callback);
	};
	
	this.update=function(existingObj,callback){
		this.serviceCaller("POST", '/project/update', existingObj, callback);
	};
	
	this.deletee=function(id,callback){
		this.serviceCaller("POST", '/project/delete', "", callback);
	};
	
	
	this.getAll=function(ids,callback){
		this.serviceCaller("POST", '/project/getAll', "", callback);
	};
	
	this.search=function(json,callback){
		this.serviceCaller("POST", '/project/search', "", callback);
	};
		
	this.deleteAll=function(ids,callback){
		this.serviceCaller("POST", '/project/deleteAll', "", callback);
	};



});


