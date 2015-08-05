'use strict';

angular.module('commishbotApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
