'use strict';
angular.module('Timelog').controller('TimelogController', function($scope, TimelogService) {
	TimelogService.list(function(response) {

        if(response.success) {
        	alert(JSON.stringify(response));
        } else {
        	alert(JSON.stringify(response));
        }
    });
	
});

