'use strict';
angular.module('Timelog').service('TimelogService',function($http) {

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

	this.get = function(callback) {
		this.serviceCaller("POST", '/timelog/list', "", callback);
	};
	

	this.list = function(json,callback) {
		this.serviceCaller("POST", '/timelog/search', "", callback);
	};
	
	this.add=function(json,callback){
		this.serviceCaller("POST", '/timelog/setWorkfromHome', "", callback);
	};
	
	this.update=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getWorkfromHome', "", callback);
	};
	
	this.deletee=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getOnLeave', "", callback);
	};
	
	
	this.getAll=function(json,callback){
		this.serviceCaller("POST", '/timelog/setOnLeave', "", callback);
	};
	
	this.search=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/login', "employeeId", callback);
	};
		
	this.deleteAll=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/logout', "employeeId", callback);
	};
	
	this.deleteAll=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getAllForMonth', "", callback);
	};
	
	this.deleteAll=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getAllForDate', "", callback);
	};
	
	this.deleteAll=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/getForUserForCurrentDate', "employeeId", callback);
	};

	this.deleteAll=function(json,callback){
		this.serviceCaller("POST", '/timelog/getAllTimeLogsForEmployeesForMonth', "", callback);
	};
	
	this.deleteAll=function(json,callback){
		this.serviceCaller("POST", '/timelog/getAllTimeLogsForEmployeesForDate', "", callback);
	};
	
	this.deleteAll=function(json,callback){
		this.serviceCaller("POST", '/timelog/getForUserForDate', "", callback);
	};
	
	this.deleteAll=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/getLastLoggedInRecord', "employeeId", callback);
	};

});


