function url() {
    let currentUrl = window.location.pathname;
    let url = '/api' + currentUrl;
    return url;
}

function fillCountries(){
    $.get('/api/countries').done(function (data) {
        var dataa = data._embedded.countries;
        $.each(dataa, function(index, value){
            $('#country-input').append(`<option value=${value.id}>${value.name}</option>`);
        });
    })
}

function fillUsers(){
    $.get('/api/users').done(function (data) {
        var dataa = data._embedded.users;
        $.each(dataa, function(index, value){
            $('#user-input').append(`<option value=${value.id}>${value.name}</option>`);
        });
    })
}

function fillAgreementTypes(){
    $.get('/api/agreement_types').done(function (data) {
        var dataa = data._embedded.agreement_types;
        $.each(dataa, function(index, value){
            $('#agreementType-input').append(`<option value=${value.id}>${value.name}</option>`);
        });
    })
}

function fillContractors(){
    $.get('/api/contractors').done(function (data) {
        var dataa = data._embedded.contractors;
        $.each(dataa, function(index, value){
            $('#contractor-input').append(`<option value=${value.id}>${value.name}</option>`);
        });
    })
}

function fillContractorTypes(){
    $.get('/api/contractor_types').done(function (data) {
        var dataa = data._embedded.contractor_types;
        $.each(dataa, function(index, value){
            $('#contractorType-input').append(`<option value=${value.id}>${value.name}</option>`);
        });
    })
}

function fillContractor(){
    $.get(url()).done(function (data) {
        $('#id-input').attr('value', data.id);
        $('#name-input').val(data.name);
        $('#phoneNumber-input').val(data.phoneNumber);
    })

    $.get(url() + "/country").done(function (data) {
            let name = '#country-input option[value=' + data.id + ']';
            $(name).attr('selected', true);
    })

    $.get(url() + "/contractorType").done(function (data) {
            let name = '#contractorType-input option[value=' + data.id + ']';
            $(name).attr('selected', true);
    })
}

function fillAgreement(){
    $.get(url()).done(function (data) {
        $('#id-input').attr('value', data.id);
        $('#startDate-input').val(data.startDate);
        $('#price-input').val(data.price);
    })

    $.get(url() + "/agreementType").done(function (data) {
            let name = '#agreementType-input option[value=' + data.id + ']';
            $(name).attr('selected', true);
    })

    $.get(url() + "/contractor").done(function (data) {
            let name = '#contractor-input option[value=' + data.id + ']';
            $(name).attr('selected', true);
    })

    $.get(url() + "/user").done(function (data) {
                let name = '#user-input option[value=' + data.id + ']';
                $(name).attr('selected', true);
    })
}

function deleteContractor(id){
    let path = url() + "/" + id

    $.ajax({
        url: path,
        type: "DELETE",
        contentType: 'application/json'
    })
}

function saveAgreement(){
    let obj = new Object();
    obj.id = $("#id-input").attr("value");
    obj.agreementType = $("#agreementType-input option:selected").val();
    obj.contractor = $("#contractor-input option:selected").val();
    obj.startDate = $("#startDate-input").val();
    obj.endDate = $("#endDate-input").val();
    obj.price = $("#price-input").val();;

    let json = JSON.stringify(obj);
    $.ajax({
        url: "http://localhost:8080/api/agreements",
        type: "POST",
        data: json,
        contentType: 'application/json',
        success: function(data, status, xhr){
            window.location.replace("/agreements");
        }
    })
}

function saveContractor(){
    let obj = new Object();
    let country = {
        id: $("#country-input option:selected").val(),
        name: $("#country-input option:selected").text()
    };
    let contractorType = {
        id: $("#contractorType-input option:selected").val(),
        name: $("#contractorType-input option:selected").text()
    };
    obj.id = $("#id-input").attr("value");
    obj.name = $("#name-input").val();
    obj.phoneNumber = $("#phoneNumber-input").val();
    obj.country = country;
    obj.contractorType = contractorType;

    let json = JSON.stringify(obj);
    $.ajax({
        url: "http://localhost:8080/api/contractors",
        type: "POST",
        data: json,
        contentType: 'application/json',
        success: function(data, status, xhr){
            window.location.replace("/contractors");
        }
    })
}