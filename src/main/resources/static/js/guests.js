const api = "http://localhost:8080/controller/guests";

var table;

$(document).ready(function() {
    initDataTable();

    getData(api);

    $("#fetchList").click(function() {
        getData();
    });

    $("#clearList").click(function() {
        clear();
    });

    // Row selection
    $("#dataTable tbody").on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
        }
        else {
          table.$('tr.selected').removeClass('selected');
          $(this).addClass('selected');
        }
      });

    //
     $("#viewGuest").click(function() {
        var row = table.row($('.selected')).data();
        console.log("Viewing guest: " + row.name);
        viewGuest(row.guestID);
      });

    // Delete guest --- STILL NEED TO INCLUDE CONFIRMATION MODAL!!!
     $("#deleteGuest").click(function() {
        var row = table.row($('.selected')).data();
        console.log("Deleting guest: " + row.name);
        $("#view-guest").empty();
        deleteGuest(row.guestID);
      });

});

function initDataTable() {

    columns = [
        { "title":  "Guest ID",
        "data": "guestID" },
        { "title":  "Guest Name",
        "data": "name" },
        { "title":  "Date of Birth",
        "data": "birthDate" },
        { "title":  "City",
        "data": "city"},
    ];

   table = $("#dataTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    } );
}

function clear() {
    table.clear();
    table.columns.adjust().draw();
}

function getData() {
    $.get(
        {
            url: api,
            dataType: "json",
            success: function (data) {
                if (data) {
                    table.clear();
                    table.rows.add(data);
                    table.columns.adjust().draw();
                }
            }
        }
    );
}

function viewGuest(id) {
    let guestId = +id;

    $.ajax({
        url: api + "/" + id,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            showGuest(result);
        },
        error: function (error) {
            console.log(error);
        }
    });
};

function showGuest(result){
    let guest =
        "Guest name: " + result.name +
        "<br>Birth date: " + result.birthDate +
        "<br>E-mail: " + result.mail +
        "<br>Phone number: " + result.phone +
        "<br>Passport Number: " + result.passportNr +
        "<br>Home address: " + result.address +
        "<br>City: " + result.city;
    $("#view-guest").html(guest);
};


function deleteGuest(id) {
    let guestId = +id;

    $.ajax({
        url: api + "/" + id,
        type:"delete",
        dataType: "json",
        contentType: "application/json",
        success: function() {
            getData();
        }
    });
};

