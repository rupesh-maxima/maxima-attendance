'use strict';
angular.module('Home', [ 'ngAnimate', 'ui.bootstrap' ]);
angular.module('Home').controller('HomeController', function($scope) {
	$scope.checked=true;
	$scope.tabs = [ {
		title : 'Dynamic Title 1',
		content : 'Dynamic content 1'
	}, {
		title : 'Dynamic Title 2',
		content : 'Dynamic content 2',
		disabled : true
	} ]
});
