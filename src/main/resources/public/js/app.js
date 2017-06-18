(function(){	
	'use strict';

	angular
		.module('app',['ui.bootstrap', 'ngRoute', 'ngResource', 'ngMessages'])
		.controller('Home', ['$http', homeController])
        .factory('GlobalService', ['$location', GlobalService])
        .run(function($rootScope, GlobalService) {
            $rootScope.service = GlobalService;  
        }); 		

	function homeController($http){
		var self = this;
	}
	
    function GlobalService ($location) {
        var service = {
            go : go
        };        
        function go(path) {
    		$location.search({});
            $location.path( path ); 
        }
        return service;    
    } 	
	
})();