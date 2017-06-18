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

    function listAll() {
        $scope.voters = VoterService.listAllVoters();
    }

    function addNewVoter() {
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
