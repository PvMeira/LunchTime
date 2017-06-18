/**
 * Created by pvmeira on 18/06/17.
 */
angular.module('app').factory('AppResource', [ '$resource', function($resource) {
    return function(url, params, methods) {
        var defaults = {
            update : {
                method : 'put',
                isArray : false
            },
            create : {
                method : 'post'
            }
        };

        methods = angular.extend(defaults, methods);

        var resource = $resource("/app/" + url, params, methods);

        resource.prototype.$save = function() {
            if (angular.isUndefined(this.id)) {
                return this.$create();
            } else {
                return this.$update();
            }
        };

        return resource;
    };
} ]);