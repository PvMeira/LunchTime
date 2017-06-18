angular.module("app").config(['$routeProvider', '$httpProvider', r]);
function r($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'Home as controller'
        })
        .when('/restaurant', {
            templateUrl: 'views/restaurant-list.html',
            controller: 'RestaurantController as controller'
        })
        .when('/restaurant-add', {
            templateUrl: 'views/restaurant-add.html',
            controller: 'RestaurantController as controller'
        })
        .when('/strawPoll', {
            templateUrl: 'views/strawPoll.html',
            controller: 'PollController as controller'
        })
        .when('/voter-list', {
            templateUrl: 'views/voter-list.html',
            controller: 'VoterController as controller'
        })
        .when('/voter-add', {
            templateUrl: 'views/voter-add.html',
            controller: 'VoterController as controller'
        }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}