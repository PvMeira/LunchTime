/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').factory('VoteService', voteService);
voteService.$inject = ['AppResource'];
function voteService(AppResource) {
    return AppResource('restaurant/:id', {
        id: '@id'
    }, {
        addNewVote:{
            url:'/app/vote/new/:email/:idRestaurant',
            params:{
                email:"@email",
                idRestaurant:"@idRestaurant"
            },
            method:'POST'
        }

    });
}