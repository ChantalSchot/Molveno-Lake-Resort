const api = "http://localhost:8080/controller/guests";

var table;

$(document).ready(function() {
    initDataTable();

    // Enter current guest data into data table (from PostConstruct)
    getData(api);

    // Fetch  guests into datatable (currently not used)
    $("#fetchList").click(function() {
        getData();
    });

    // Clear datatable (currently not used)
    $("#clearList").click(function() {
        clear();
    });

    // Add 'selected' class on row (for selection of data/guest ID)
    $("#dataTable tbody").on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
        }
        else {
          table.$('tr.selected').removeClass('selected');
          $(this).addClass('selected');
        }
      });

    // View guest details: get ID from selected row
     $("#viewGuest").click(function() {
        var row = table.row($('.selected')).data();
        console.log("Viewing guest: " + row.name);
        // Get guest info by ID
        viewGuest(row.guestID);
      });

    // Delete guest that is currently selected
     $("#deleteGuest").click(function() {
        var row = table.row($('.selected')).data();
        console.log("Deleting guest: " + row.name);
        // Delete guest from database & datatable
        deleteGuest(row.guestID);
      });

});

function initDataTable() {
// Create columns (with titles) for datatable: guestID, name, date of birth and city.
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

    // Define new table with above columns
   table = $("#dataTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    } );
}

// Clear all data from table
function clear() {
    table.clear();
    table.columns.adjust().draw();
}

// Fetch guest data from API
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

// View guest details with ID from selected row
function viewGuest(id) {
    // Ensure that guest ID is a number (not string)
    let guestId = +id;

    $.ajax({
        url: api + "/" + id,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            // If guest was retrieved, add guest details to modal
            showGuest(result);
        },
        error: function (error) {
            console.log(error);
        }
    });
};

function showGuest(result){
    // Add guest information to View modal fields
    $("#viewId").html(result.guestID);
    $("#viewGuestName").html(result.name);
    $("#viewBirthDate").html(result.birthDate);
    $("#viewMail").html(result.mail);
    $("#viewPhone").html(result.phone);
    $("#viewPassportNr").html(result.passportNr);
    $("#viewAddress").html(result.address);
    $("#viewCity").html(result.city);

};

// Delete selected guest with ID from datatable & database
function deleteGuest(id) {
    // Ensure that guest ID is a number (not string)
    let guestId = +id;

    // Delete guest, then refresh table with updated data
    $.ajax({
        url: api + "/" + id,
        type:"delete",
        dataType: "json",
        contentType: "application/json",
        success: function() {
            getData();
        },
        error: function (error) {
            console.log(error);
        }
    });
};

