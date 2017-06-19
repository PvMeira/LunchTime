/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').factory('PollService', pollService);
pollService.$inject = ['AppResource'];
function pollService(AppResource) {
    return AppResource('restaurant/:id', {
        id: '@id'
    }, {
        findCurrentPool: {
            url: '/app/strawPoll/getResultCurrentPoll',
            method: 'GET',
            isArray: false
        },
        addNewPoll: {
            url: '/app/strawPoll/new/:name',
            params: {
                name: "@name"
            },
            method: 'POST'
        },
        newPollAvaliable: {
            url: '/app/strawPoll/newPollAvaliable',
            method: 'GET'

        },
        avaliable: {
            url: '/app/strawPoll/avaliable',
            method: 'GET'

        }

    });
}