/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').controller("PollController", ['$scope', 'PollService', '$resource', 'notify', 'RestaurantService', 'VoteService', PollController]);

function PollController($scope, PollService, $resource, notify, RestaurantService, VoteService) {

    $scope.listCurrentPoll = listCurrentPoll;
    $scope.listAvaliableRestaurant = listAvaliableRestaurant;
    $scope.addNewPoll = addNewPoll;
    $scope.addNewVote = addNewVote;
    $scope.currentPoll = {};
    $scope.newVote = {};
    $scope.showForm = true;
    $scope.newPollName = "nova votação";
    $scope.avaliableRestaurant = {};
    init();

    function init() {
        listCurrentPoll();
        listAvaliableRestaurant();

    }

    function listCurrentPoll() {
        $scope.currentPoll = PollService.findCurrentPool();
    }

    function addNewPoll() {
        PollService.addNewPoll({
            name: $scope.newPollName
        }, function (response) {
            notify.successOnSave();
            $scope.showForm = false;
            init();
        });
    }

    function listAvaliableRestaurant() {
        $scope.avaliableRestaurant = RestaurantService.findAllRestaurants();
    }

    function addNewVote() {
        VoteService.addNewVote({
            email: $scope.newVote.email,
            idRestaurant: $scope.newVote.idRestaurant
        }, function (response) {
            console.log(response)
            $scope.newVote = {};
            notify.successOnSave();
        }, function (response) {
            $scope.newVote = {};
            if (response.status === 400) {
                notify.danger("Erro ao votar : Votante já votou hoje");
            }
        });
    }
}