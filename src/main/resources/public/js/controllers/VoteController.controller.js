/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').controller("VoteController", ['$scope', 'VoteService', '$resource', 'notify', PollController]);

function PollController($scope, VoteService, $resource, notify) {

    init();

    function init() {
    }
}