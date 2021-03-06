'use strict';

// declare modules
angular.module('Authentication', []);
angular.module('Home',[]);
angular.module('Project',[]);
angular.module('Holidays',[]);
angular.module('Timelog',[]);
angular.module('workFromHome',[]);
angular.module('Employees',[]);

angular.module('BasicHttpAuthExample', [
    'Authentication',
    'Home',
    'Project',
    'Holidays',
    'Timelog',
    'Employees',
    'workFromHome',
    'ngRoute',
    'ngCookies'
])
 
.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })
        
        .when('/project', {
            controller: 'ProjectController',
            templateUrl: 'modules/project/views/project.html'
        })
 
         .when('/holidays', {
            controller: 'HolidaysController',
            templateUrl: 'modules/holidays/views/holidays.html',
        })
        
        .when('/timelog', {
            controller: 'TimelogController',
            templateUrl: 'modules/timelog/view/timelog.html',
        })
        
          .when('/workFromHome', {
            controller: 'workFromHomeController',
            templateUrl: 'modules/workFromHome/views/workFromHome.html',
        })
        .when('/employees', {
            controller: 'EmployeesController',
            templateUrl: 'modules/employees/view/employees.html',
        })
        
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html',
        })
 
        .otherwise({ redirectTo: '/login' });
}])
 
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authkorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
    }]);

