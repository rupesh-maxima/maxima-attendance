'use strict';
angular.module('Holidays').controller('HolidaysController', function($scope, HolidaysService) {
	HolidaysService.list(function(response) {
        if(response.success) {
        	alert(JSON.stringify(response));
        } else {
        	alert(JSON.stringify(response));
        }
    });
	
});