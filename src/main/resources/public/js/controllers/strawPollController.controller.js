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

    function listCurrentPoll() {
        $scope.currentPoll = PollService.findCurrentPool();
    }

    function newPollAvaliable() {
        PollService.newPollAvaliable({}, function (response) {
            $scope.showForm = true;
        }, function (response) {
            $scope.showForm = false;
        })
    }

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

    function listAvaliableRestaurant() {
        $scope.avaliableRestaurant = RestaurantService.findAllRestaurants();
    }

    function addNewVote() {
        if (!$scope.newVote.email || !$scope.newVote.idRestaurant) {
            notify.alert("Campo Obrigatório");
        } else {
            VoteService.addNewVote({
                email: $scope.newVote.email,
                idRestaurant: $scope.newVote.idRestaurant
            }, function (response) {
                console.log(response)
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