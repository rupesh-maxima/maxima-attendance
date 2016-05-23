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
	
	this.list = function(callback) {
		this.serviceCaller("POST", '/timelog/list', "", callback);
	};
	

	this.search = function(json,callback) {
		this.serviceCaller("POST", '/timelog/search', "", callback);
	};
	
	this.setWorkfromHome=function(json,callback){
		this.serviceCaller("POST", '/timelog/setWorkfromHome', "", callback);
	};
	
	this.getWorkfromHome=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getWorkfromHome', "", callback);
	};
	
	this.getOnLeave=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getOnLeave', "", callback);
	};
	
	
	this.setOnLeave=function(json,callback){
		this.serviceCaller("POST", '/timelog/setOnLeave', "", callback);
	};
	
	this.login=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/login', "employeeId", callback);
	};
		
	this.logout=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/logout', "employeeId", callback);
	};
	
	this.getAllForMonth=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getAllForMonth', "", callback);
	};
	
	this.getAllForDate=function(recordDate,callback){
		this.serviceCaller("POST", '/timelog/getAllForDate', "", callback);
	};
	
	this.getForUserForCurrentDate=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/getForUserForCurrentDate', "employeeId", callback);
	};

	this.getAllTimeLogsForEmployeesForMonth=function(json,callback){
		this.serviceCaller("POST", '/timelog/getAllTimeLogsForEmployeesForMonth', "", callback);
	};
	
	this.getAllTimeLogsForEmployeesForDate=function(json,callback){
		this.serviceCaller("POST", '/timelog/getAllTimeLogsForEmployeesForDate', "", callback);
	};
	
	this.getForUserForDate=function(json,callback){
		this.serviceCaller("POST", '/timelog/getForUserForDate', "", callback);
	};
	
	this.getLastLoggedInRecord=function(employeeId,callback){
		this.serviceCaller("POST", '/timelog/getLastLoggedInRecord', "employeeId", callback);
	};

});


