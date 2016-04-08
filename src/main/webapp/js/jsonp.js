var jsonpApp = angular.module('jsonpApp', ['ui']);

jsonpApp.controller('JsonpController', function ChatController($scope, $http) {

    $scope.connect1 = function(){
        var url = "http://localhost:8089/api/heren-plugin/test";
        $.ajax({
            type: "GET",
            url: url,
            data: {},
            async:true,
            contentType: "application/json; charset=utf-8",
            dataType: "jsonp",
            success: function(data) {
                alert(data)
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('error');
            }
        });
    }

    $scope.connect = function(){
        var url = "http://localhost:8089/api/heren-plugin/test?callback=JSON_CALLBACK";
        $http.jsonp(url)
            .success(function(data){
                alert(data);
            })
            ;
    }

});