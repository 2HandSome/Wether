$(document).on('pagebeforeshow', '#index', function(){
    $(document).on( "popupafteropen", "#CaseInformationScreen",function( event, ui ) {
        var today = new Date();
        var dd = today.getDate();
        var MM = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();
        var HH = today.getHours();
        var mm = today.getMinutes();
        var ss = today.getSeconds();
        $('#caseDate').val(yyyy + '-' + MM + '-' + dd + " " + HH + ":" + mm + ":" + ss);
    });
});

$(document).ready(function(){
    var i=1;
    $('#add').click(function(){
        i++;
        $('#dynamic_field').append(
            '<tr id="row'+i+'">' +
            '<td>' +
            '<input id = "cityText" type="text" name="city" placeholder="Enter city" class="form-control name_list" minlength="4" maxlength="8" size="10"/>' +
            '<input name="deteTime" min="12:00" max="18:00" id="caseDate" value="" type="datetime-local" class="form-control caseDate_h" />' +
            '</td>' +
            '<td>' +
            '<button type="button" name="remove" id="'+i+'" class="btn btn-danger btn_remove">X</button>' +
            '</td>' +
            '</tr>');
    });

    $(document).on('click', '.btn_remove', function(){
        var button_id = $(this).attr("id");
        $('#row'+button_id+'').remove();
    });
    $('#submit').click(function() {
        var data = jsonFormatFormResponse();
        var settings = {
            "url": "http://localhost:8080/route/viewMap",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(data),
        };
        $.ajax(settings).done(function (response) {

            $.each(response.data.routeMap , function (key, value) {
                $("#tbody").append(
                    ' <tr id = "generated">\n' +
                    '<th>' + value.cityName + '</th> <td >' + value.countryCode + '</td> <td>' + value.temperature + '</td> <td>' + value.clouds + '</td> <td>' + value.time + '</td></tr>'
                );
            });
        });
    });


    $('#cityText').on('keypress keyup keydown', function () {
        if ($('#cityText').val() == "" ) {
            $('#save').prop('disabled', true);
            $('#submit').prop('disabled', true);
        }
        else {
            $('#save').prop('disabled', false);
            $('#submit').prop('disabled', false);
        }
    });


    $('#tagName').on('keypress keyup keydown', function () {
        if ($('#tagName').val() == "" ) {
            $('#tag-form-submit').prop('disabled', true);
        }
        else {
            $('#tag-form-submit').prop('disabled', false);
        }
    });
    $('#save').click(function() {
        $('#mymodal').modal();
    });

    $(document).on('click', '#tag-form-submit', function(){
        var data = jsonFormatModalResponse();
        console.log(data);
        var settings = {
            "url": "http://localhost:8080/route/storeRoute",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(data)};
        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });

    const handleTime = (event) => {
        let data= new Date(event)
        let hrs = data.getHours()
        let mins = data.getMinutes()
        if(hrs<=9)
            hrs = '0' + hrs
        if(mins<10)
            mins = '0' + mins
        const postTime= hrs + ':' + mins
        return postTime
    }

    const timeFormat = (dateTime) =>{
        return dateTime.replace("T", " " );
    }

    function jsonFormatModalResponse() {
        let mapName = $( "#tagName" ).val();
        let formArray = $("#add_name").serializeArray();
        let data = {
            itinerary : [],
            routeMapName : "",
        };
        const itinerary = [];
        let values = {};
        let city ="";
        let dateTime ="";
        $.each(formArray, function(i, pair){
            $.each(pair.name.split(".") ,function(i, item){
                if(item === "city"){
                    city =pair.value;
                }else{
                    dateTime = handleTime(pair.value);
                    console.log(dateTime) //
                    values = {city, dateTime};
                    itinerary.push(values);
                }
            });
        });



        data.routeMapName = mapName;
        data.itinerary = itinerary;

        return data;
    }

    function jsonFormatFormResponse() {
        var formArray = $("#add_name").serializeArray();
        const data = [];
        var values = {};
        var city ="";
        var dateTime ="";
        $.each(formArray, function(i, pair){
            $.each(pair.name.split(".") ,function(i, item){
                if(item === "city"){
                    city =pair.value;
                }else{
                    dateTime = timeFormat(pair.value); //
                    values = {city, dateTime};
                    data.push(values);
                }
            });
        });
        return data;

    }



    $('#tag-history').click(function() {
        var settings = {
            "url": "http://localhost:8080/route/viewAll",
            "method": "GET",
            "timeout": 0,
        };
        $.ajax(settings).done(function (response) {
            $.each(response.data , function (key, value) {
                var str = [];
                $.each(value.itinerary , function (key, value){ str.push(value.city) });
                $("#t-route-body").append(
                '<tr id = "generated"> <th>' + value.routeMapName + '</th><td>' + str.toString() + '</td></tr>' );
            });
        });
    });

    function jsonApplyFormData(){
        const data = [];
        var values = {};
        var city ="";
        var dateTime ="";
        let inputs = $('#time-city-data :input');
        for (let input of inputs) {
            city = input.name;
            dateTime = timeFormat(input.value);
            values={city, dateTime}
            data.push(values);

        }
        return data;
    }
    $('#aplay-form-submit').click(function(){
        var settingsApply = {
            "url": "http://localhost:8080/route/viewMap",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(jsonApplyFormData()),
        };
        $.ajax(settingsApply).done(function (response) {
            console.log(response);
            $.each(response.data.routeMap , function (key, value) {
                $("#tbody").append(
                    ' <tr id = "generated">\n' +
                    '<th>' + value.cityName + '</th> <td >' + value.countryCode + '</td> <td>' + value.temperature + '</td> <td>' + value.clouds + '</td> <td>' + value.time + '</td></tr>'
                );
            });
        });
    });
});



