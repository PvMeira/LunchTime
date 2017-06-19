/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').controller("PollController", ['$scope', 'PollService', '$resource', 'notify', 'RestaurantService', 'VoteService', PollController]);

function PollController($scope, PollService, $resource, notify, RestaurantService, VoteService) {

    $scope.listCurrentPoll = listCurrentPoll;
    $scope.listAvaliableRestaurant = listAvaliableRestaurant;
    $scope.addNewPoll = addNewPoll;
    $scope.addNewVote = addNewVote;
    $scope.newPollAvaliable = newPollAvaliable;
    $scope.getWinner = getWinner;
    $scope.winnerButton = winnerButton;
    $scope.currentPoll = {};
    $scope.newVote = {};
    $scope.newPoll = {};
    $scope.winner = {};
    $scope.showForm = false;
    $scope.avaliableRestaurant = {};
    init();

    function init() {
        $scope.winner.name = "Não definido ainda.";
        $scope.winner.votes = 0;
        listCurrentPoll();
        listAvaliableRestaurant();
        newPollAvaliable();
        winnerButton();

    }

    /**
     * Get the winner restaurant from the current StrawPoll
     * The buttom that show the winner is only visible if the
     * time is more than 11:00 AM
     */
    function getWinner() {
        PollService.avaliable({}, function (response) {
            if (!$scope.currentPoll.restaurantList) {

            } else {
                $scope.winner.name = $scope.currentPoll.restaurantList[0].name;
                $scope.winner.votes = $scope.currentPoll.restaurantList[0].totalVotes;
            }
        }, function (response) {
            $scope.winner.name = "Não definido ainda."

        })
    }

    function winnerButton() {
        $scope.show = false;
        PollService.avaliable({}, function (response) {
            $scope.show = true;
        });
    }

    /**
     * Get the current strawPoll
     */
    function listCurrentPoll() {
        $scope.currentPoll = PollService.findCurrentPool();
    }

    /**
     * Verify if a new StrawPoll can be created, by following 3 conditions :
     * 1º-If the Current Poll has the NEW filed Boolean.FALSE
     * 2º-If the Current Poll is null
     * 3º-If the Time to Show the winner come up
     */
    function newPollAvaliable() {
        PollService.newPollAvaliable({}, function (response) {
            $scope.showForm = true;
        }, function (response) {
            $scope.showForm = false;
        })
    }

    /**
     * Create a new StrawPoll and added to the DB, name is required to
     * the poll to be saved
     */
    function addNewPoll() {
        if (!$scope.newPoll.name) {
            notify.alert("Campo Obrigatorio");
        } else {
            PollService.addNewPoll({
                name: $scope.newPoll.name
            }, function (response) {
                notify.successOnSave();
                $scope.showForm = false;
                init();
            });
        }
    }

    /**
     * List all available Restaurants according to the rules on the rest end-point
     */
    function listAvaliableRestaurant() {
        $scope.avaliableRestaurant = RestaurantService.findAllRestaurants();
    }

    /**
     * Add a new Vote to the current StrawPoll, both email and idRestaurant are required, so
     * that the vote can be register in the DB.
     */
    function addNewVote() {
        if (!$scope.newVote.email || !$scope.newVote.idRestaurant) {
            notify.alert("Campo Obrigatório");
        } else {
            if ($scope.show === true) {
                notify.alert("A votação está encerrada por hoje");
                $scope.newVote = {};
            } else {
                VoteService.addNewVote({
                    email: $scope.newVote.email,
                    idRestaurant: $scope.newVote.idRestaurant
                }, function (response) {
                    $scope.newVote = {};
                    notify.successOnSave();
                    init();
                }, function (response) {
                    $scope.newVote = {};
                    if (response.status === 400) {
                        notify.danger("Erro ao votar : Votante já votou hoje");
                    }
                });
            }

        }

    }
}