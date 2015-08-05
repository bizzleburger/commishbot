'use strict';

angular.module('commishbotApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


