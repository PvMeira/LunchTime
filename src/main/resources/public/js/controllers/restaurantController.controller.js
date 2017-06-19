/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').controller("RestaurantController", ['$scope', 'RestaurantService', '$resource', 'notify', RestaurantController]);

function RestaurantController($scope, RestaurantService, $resource, notify) {

    $scope.listAll = listAll;
    $scope.addNewRestaurant = addNewRestaurant;
    $scope.restaurants = {};
    $scope.restaurant = {};
    init();


    function init() {
        listAll();
    }

    function listAll() {
        $scope.restaurants = RestaurantService.findAll();

    }

    function addNewRestaurant() {
        var r = $resource('/app/restaurant');
        r.save($scope.restaurant, function (response) {
            notify.successOnSave();
            $scope.restaurant = {};
        });

    }

}