/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').controller("VoterController", ['$scope', 'VoterService', '$resource', 'notify', VoterController]);

function VoterController($scope, VoterService, $resource, notify) {

    $scope.listAll = listAll;
    $scope.addNewVoter = addNewVoter;
    $scope.voters = {};
    $scope.voter = {};
    init();


    function init() {
        listAll();
    }

    /**
     * List all the voters present in the DB of the application
     */
    function listAll() {
        $scope.voters = VoterService.listAllVoters();
    }

    /**
     * Add a new Voter to the DB of the application, both name and email
     * are required so that the new voter can be stored on the DB
     */
    function addNewVoter() {
        if (!$scope.voter.name || !$scope.voter.email) {
            notify.alert("Campo Obrigatório");
        } else {
            var r = $resource('/app/voter');
            r.save($scope.voter, function (response) {
                notify.successOnSave();
                $scope.voter = {};
            }, function (response) {
                $scope.voter = {};
                notify.danger("Esse email já está sendo usado.");
            });
        }
    }

}
