// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
var table = document.getElementById('table')
var selected = table.getElementsByClassName('selected');

let constClass = "";
let id;

function getFormData($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function fillTable() {
    $.ajax({
        type: "GET",
        url: "http://localhost:60950/api/contact",
        context: document.body
    }).done(function (msg) {
        var table = document.getElementById('table');

        var rl = table.rows.length;

        for (var i = rl - 1; i > 0; i--) {
            table.deleteRow(i);
        }

        msg.forEach(function (rowData) {
            var row = table.insertRow(1);

            row.className = rowData.id;

            var newCell = row.insertCell(0);
            newCell.appendChild(document.createTextNode(rowData.name));

            newCell = row.insertCell(1);
            newCell.appendChild(document.createTextNode(rowData.number));
        });
    });
}

function addNew() {
    if ($(".divclass").css('visibility') == 'visible') {
        clearActionContent();
    } else {
        $(".divclass").attr("style", "visibility: visible")
        $("#actionButton").attr("onclick", "addNewSave()");
        $("#actionButton").html('Add new contact');
    }
}

function addNewSave() {
    $.ajax({
        type: "POST",
        url: "http://localhost:60950/api/contact",
        data:JSON.stringify(getFormData($("form"))),
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function () {
            fillTable();
            clearActionContent();
        }
    });
}

function update() {
    if (id != null) {
        if ($(".divclass").css('visibility') == 'visible') {
            clearActionContent();
        } else {
            $(".divclass").attr("style", "visibility: visible")

            $("#actionButton").attr("onclick", "updateSave()");
            $("#actionButton").html('Save changes');

            loadContactInfo();
        }
    } else {
        alert("choise contact");
    }
}

function updateSave() {
    if (id != null) {
        $.ajax({
            type: "PUT",
            url: "http://localhost:60950/api/contact/" + id,
            data: JSON.stringify(getFormData($("form"))),
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function () {
                fillTable();
                clearActionContent();
            }
        });
    } else {
        alert("choise contact");
    }
}

function deleteContact() {
    if (id != null) {
        clearActionContent();
        $.ajax({
            type: "DELETE",
            url: "http://localhost:60950/api/contact/" + id,
            success: function () {
                fillTable();
            }
        });
    } else {
        alert("choise contact");
    }
}

function loadContactInfo() {
    $.ajax({
        type: "GET",
        url: "http://localhost:60950/api/contact/" + id,
        success: function (data) {
            var inputs = $('input');

            inputs[0].value = data.name;
            inputs[1].value = data.number;
        }
    });
}

function clearActionContent() {
    $(".divclass").attr("style", "visibility: hidden")

    $('form').find("input[type=text], textarea").val("");
    $("#actionButton").attr("onclick", "");
    $("#actionButton").html('');
}

function highlight(e) {
    if (selected[0])
        selected[0].className = constClass;

    id = e.target.parentNode.className;
    constClass = e.target.parentNode.className;
    e.target.parentNode.className = 'selected';

    if ($(".divclass").css('visibility') == 'visible') {
        loadContactInfo();
    }
}

window.onload = function () {
    $(".divclass").attr("style", "visibility: hidden")
    fillTable();

    table.onclick = highlight;
}