/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').factory('VoterService', voterService);
voterService.$inject = ['AppResource'];
function voterService(AppResource) {
    return AppResource('voter/:id', {
        id: '@id'
    }, {
        listAllVoters:{
            url:'/app/voter',
            method:'GET',
            isArray:true
        }
    });
}
