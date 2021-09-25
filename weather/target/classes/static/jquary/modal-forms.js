
$(document).ready(function(){
    let id ="";
    let keySize = 0;

    //Activate modal windows
    activeteModalForm('#find-apply-btn', '#apply-modal');
    activeteModalForm('#dekete-btn', '#delete-modal');
    activeteModalForm('#find-update-btn', '#update-modal');



    //buttans desable
    $('#delete-name').on('keypress keyup keydown', function () {
        if ($('#delete-name').val() == "" ) {
            $('#delete-form-submit').prop('disabled', true);
        }
        else {
            $('#delete-form-submit').prop('disabled', false);
        }
    });

    $('#tag-data').on('keypress keyup keydown', function () {
        if ($('#tag-data').val() == "" ) {
            console.log("hi")
            $('#aplay-form-submit').prop('disabled', true);
        }
        else {
            console.log("hi2")
            $('#aplay-form-submit').prop('disabled', false);
        }
    });

    $('#find-tag').on('keypress keyup keydown', function () {
        if ($('#find-tag').val() == "" ) {
            $('#find-update-btn').prop('disabled', true);
        }
        else {
            $('#find-update-btn').prop('disabled', false);
        }
    });

    $('#apply-tag').on('keypress keyup keydown', function () {
        if ($('#apply-tag').val() == "" ) {
            $('#find-apply-btn').prop('disabled', true);
        }
        else {
            $('#find-apply-btn').prop('disabled', false);
        }
    });

    $('#route-date').on('keypress keyup keydown', function () {
        if ($('#route-date').val() == "" ) {
            $('#update-form-submit').prop('disabled', true);
        }
        else {
            $('#update-form-submit').prop('disabled', false);
        }
    });


    $(document).on('click', '#delete-form-submit', function(){
        let mapName = $("#delete-name").val();
        let urlValue = "http://localhost:8080/route/deleteRoute/";
        let url = urlValue+mapName;

        let settings = {
            "url": url,
            "method": "DELETE",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });

    $('#update-modal').on('hidden.bs.modal', function(event) {
        $('#body-update-form').empty();
    });
    $('#apply-modal').on('hidden.bs.modal', function(event) {
        $('#apply-body').empty();
    });

    $('#find-update-btn').click(function(){

        let mapName = $("#find-tag").val();
        console.log(mapName);
        let urlValue = "http://localhost:8080/route/viewByRouteName/";
        let url = urlValue + mapName;
        var settings = {
            "url": url,
            "method": "GET",
            "timeout": 0,
            "headers": {
                "routeName": ""
            },
        };

        $.ajax(settings).done(function (response) {
            id = response.data.id;
            $.each(response.data.itinerary , function (key, value) {
                keySize = key;
                $("#inputs-body").append( '<div class = "md-3" id ="generated-bodu">' +
                    '<label for="new-city-name" class="fst-normal">Ensert new City name: </label>'+
                    '<input id = "new-city-name'+key+'" value="'+ value.city +'" name = "array[]" />'+
                    '</div>');
            });

            $("#body-update-form").append(
                '<div class = "md-3">' +
                '<label for="new-rout-name" class="fst-normal">Ensert new route name: </label>'+
                '<input id = "new-rout-name" value="'+response.data.routeMapName+'" />'+
                '</div>'
            );

        });

    });



    $(document).on('click', '#add-city-form-submit', function(){
            $("#inputs-body").append( '<div class = "md-3">' +
                '<label for="new-city-name" class="fst-normal">Ensert new City name: </label>'+
                '<input id = "new-city-name" name = "array[]" />'+
                '</div>');
    });

    $(document).on('click', '#update-form-submit', function(){
        var settings = {
            "url": "http://localhost:8080/route/updateRoute",
            "method": "PUT",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(jsonFormatFormResponse()),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });

    function jsonFormatFormResponse() {
        let routeName = $("#new-rout-name").val();
        const itinerary = [];
        var values ={};
        var imputs = document.getElementsByName("array[]");
        for(var i = 0; i < imputs.length; i++){
            var k = imputs[i];
            var city = k.value;
            if(!(city == "")){
                values = {city};
                itinerary.push(values);
            }

        }
        var data = {
            "id": id,
            "itinerary": itinerary,
            "routeMapName": routeName
        };
        return data;

    }

    $('#find-apply-btn').click(function(){

        let mapName = $("#apply-tag").val();
        let urlValue = "http://localhost:8080/route/viewByRouteName/";
        let url = urlValue + mapName;
        var settings = {
            "url": url,
            "method": "GET",
            "timeout": 0,
            "headers": {
                "routeName": ""
            },
        };


        $.ajax(settings).done(function (response) {
            id = response.data.id;
            $("#apply-body").append(
                '<div class = "md-3">' +
                '<p style="text-align:center"> Map: '+ response.data.routeMapName +'</p>'+
                '</div>'
            );
            $.each(response.data.itinerary , function (key, value) {
                $("#time-city-data").append( '<div class = "md-3" id ="generated-body">' +
                    '<label for="new-date-name" class="fst-normal">City: '+ value.city +' </label>'+
                    '<input id = "new-date-name" class="date-time-apply"type="datetime-local" name ="'+value.city+'"/>'+
                    '</div>');
            });
        });

    });


});

