function url() {
    let currentUrl = window.location.pathname;
    let url = '/api' + currentUrl;
    return url;
}

function url2() {
    let currentUrl = window.location.pathname;
    let url = '/api/pagination' + currentUrl + '/payments';
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

function fillPayment(){
    $.get(url()).done(function (data) {
        $('#id-input').attr('value', data.id);
        $('#name-input').val(data.name);
        let date = new Date(Date.parse(data.date));
        let payDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
        $('#date-input').val(payDate);
        $('#amount-input').val(data.amount);

        $.get("http://localhost:8080/api/payments/3/agreement").done(function (data) {
                    $('#agreement-input').val(data.id);
            })
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
        $('#name-input').val(data.name);
        let date = new Date(Date.parse(data.startDate));
        let startDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
        $('#startDate-input').val(startDate);
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

function deleteEntity(id){
    let path = url() + "/" + id;
    $.ajax({
        url: path,
        type: "DELETE",
        contentType: 'application/json'
    })
}

function deletePayment(id){
    let path = "http://localhost:8080/api/payments/" + id;
    $.ajax({
        url: path,
        type: "DELETE",
        contentType: 'application/json'
    })
}

function saveAgreement(){
    let obj = new Object();
    obj.id = $("#id-input").attr("value");
    obj.name = $("#name-input").val();
    obj.agreementType = $("#agreementType-input option:selected").val();
    obj.contractor = $("#contractor-input option:selected").val();
    obj.startDate = $("#startDate-input").val();
    obj.user = $("#user-input option:selected").val();
    obj.price = $("#price-input").val();

    if(obj.name == '' || obj.startDate == '' || obj.price == ''){
            alert('Не заполнены обязательные атрибуты');
            return;
    }

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

function savePayment(){
    let obj = new Object();
    obj.id = $("#id-input").attr("value");
    obj.agreement = $("#agreement-input").val();
    obj.name = $("#name-input").val();
    obj.date = $("#date-input").val();
    obj.amount = $("#amount-input").val();

    if(obj.name == '' || obj.date == '' || obj.amount == ''){
        alert('Не заполнены обязательные атрибуты');
        return;
    }

    let json = JSON.stringify(obj);
    $.ajax({
        url: "http://localhost:8080/api/payments",
        type: "POST",
        data: json,
        contentType: 'application/json',
        success: function(data, status, xhr){
            window.location.replace("/agreements/" + obj.agreement);
        }
    })
}

function saveContractor(){
    let obj = new Object();
    obj.id = $("#id-input").attr("value");
    obj.name = $("#name-input").val();
    obj.phoneNumber = $("#phoneNumber-input").val();
    obj.country = $("#country-input option:selected").val();
    obj.contractorType = $("#contractorType-input option:selected").val();

    if(obj.name == '' || obj.phoneNumber == '') {
        alert('Не заполнены обязательные атрибуты');
        return;
    }

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

function parseUrl() {
    let url = window.location.pathname;
    let pathArray = url.split("/");
    $('#agreement-input').val(pathArray[2]);
}