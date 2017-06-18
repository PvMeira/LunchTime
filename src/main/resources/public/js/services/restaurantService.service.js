/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').factory('RestaurantService', restaurantService);
restaurantService.$inject = ['AppResource'];
function restaurantService(AppResource) {
    return AppResource('restaurant/:id', {
        id: '@id'
    }, {

        findAllRestaurants: {
            url: '/app/restaurant/listAllAvaliable',
            method: 'GET',
            isArray: true
        },
        saveRestaurant:{
            url:'/app/restaurant',
            method:'POST',
            params:{
                dto:"dto"
            }
        }
    });
}