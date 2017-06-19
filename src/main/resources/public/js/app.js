angular.module('app', ['ui.bootstrap', 'ngRoute', 'ngResource', 'ngMessages']).controller('Home', ['$http'])
    .factory('GlobalService', ['$location', GlobalService]).run(function ($rootScope, GlobalService) {
    $rootScope.service = GlobalService;
});
function GlobalService($location) {
    var service = {
        go: go
    };

    function go(path) {
        $location.search({});
        $location.path(path);
    }

    return service;
}