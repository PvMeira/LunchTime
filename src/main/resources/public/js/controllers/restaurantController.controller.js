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

    /**
     * List all the restaurants that are armazened on the application DB
     */
    function listAll() {
        $scope.restaurants = RestaurantService.findAll();

    }

    /**
     * Add a new Restaurant to the DataBase, both name and location are required to
     * the restaurant to be saved.
     * Callback success : Return a Success message to the screen
     */
    function addNewRestaurant() {
        if (!$scope.restaurant.name || !$scope.restaurant.location) {
            notify.alert("Campo Obrigatório");
        } else {
            var r = $resource('/app/restaurant');
            r.save($scope.restaurant, function (response) {
                notify.successOnSave();
                $scope.restaurant = {};
            });

        }
    }

}