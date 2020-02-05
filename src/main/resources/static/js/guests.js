const api = "http://localhost:8080/api/guests";

var table;

$(document).ready(function() {
    initDataTable();

    // Enter current guest API into data table (only data from PostConstruct for now)
    getData();

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
          emptyModals();
        }
        else {
          table.$('tr.selected').removeClass('selected');
            emptyModals();
            $(this).addClass('selected');
        }
      });

    // View guest details: get ID from selected row
     $("#viewGuest").click(function() {
        // Get guest info by ID
        viewGuest(table.row($('.selected')).data());
        // console.log("Viewing guest: " + row.name);
      });

    // View guest details: get ID from selected row
    $(".editGuest").click(function() {
        // Get guest info by ID
        editGuestModal(table.row($('.selected')).data());
        //console.log("Editing guest: " + row.name);
    });

    $("#saveGuest").click(function() {
        saveGuest();
        getData();
    });

    $("#deleteGuest").click(function() {
        // Check if guest is selected
        if (table.row($('.selected')).data() == undefined) {
            $("#noGuestSelectedModal").modal("show");
        } else {
            $("#deleteModal").modal("show");
        }
    });

    // Delete guest that is currently selected
     $("#deleteGuestConfirm").click(function() {
        // Delete guest from database & datatable
        deleteGuest(table.row($('.selected')).data());
        // console.log("Deleting guest: " + row.name);
      });

     // When a modal is closed, the fields inside should be emptied
    $(".close-button").click(function() {
        // Empty modal fields
        emptyModals();
    });

});

function initDataTable() {
// Create columns (with titles) for datatable: id, name, date of birth and city.
    columns = [
        { "title":  "Guest ID",
        "data": "id" },
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
function viewGuest(guest) {
    // Ensure that guest ID is a number (not string)
    // let guestId = +id;
    if (guest == undefined) {
        $("#noGuestSelectedModal").modal("show");
    } else {
        $("#viewModal").modal("show");
        $.ajax({
            url: api + "/" + guest.id,
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
    }

};

// Enter guest information in the 'view guest' modal
function showGuest(result){
    // Add guest information to View modal fields
    $("#viewId").html(result.id);
    $("#viewGuestName").html(result.name);
    $("#viewBirthDate").html(result.birthDate);
    $("#viewMail").html(result.mail);
    $("#viewPhone").html(result.phone);
    $("#viewPassportNr").html(result.passportNr);
    $("#viewAddress").html(result.address);
    $("#viewCity").html(result.city);
};


function editGuestModal(guest) {
    // Check if guest is selected
    if (guest == undefined) {
        $("#noGuestSelectedModal").modal("show");
    } else {
        $("#editModal").modal("show");
        // Fill in guest to modal fields
        $("#editId").val(guest.id);
        $("#editGuestName").val(guest.name);
        $("#editBirthDate").val(guest.birthDate);
        $("#editMail").val(guest.mail);
        $("#editPhone").val(guest.phone);
        $("#editPassportNr").val(guest.passportNr);
        $("#editAddress").val(guest.address);
        $("#editCity").val(guest.city);
    }
}

function saveGuest() {
    let guestObject = {
        id: +$("#editId").val(),
        name: $("#editGuestName").val(),
        birthDate: $("#editBirthDate").val(),
        mail: $("#editMail").val(),
        phone: $("#editPhone").val(),
        passportNr: $("#editPassportNr").val(),
        address: $("#editAddress").val(),
        city: $("#editCity").val()
    };
    console.log("New guestObject: " + guestObject);

    var jsonObject = JSON.stringify(guestObject);
    console.log("new jsonObject: " + jsonObject)

    $.ajax({
        url: api,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "put",
        dataType: "json",
        data: jsonObject,
        contentType: "application/json",
        success: function(result) {
            console.log("Guest posted / edited: " + result);
            emptyModals();
            getData();
        },
        error: function (error) {
            console.log(error);
            getData();
        }
    });


}


// Delete selected guest with ID from datatable & database
function deleteGuest(guest) {


        $("#deleteModal").modal("show");
        $.ajax({
            url: api,
            type: "delete",
            dataType: "json",
            data: JSON.stringify(guest),
            contentType: "application/json",
            success: function () {
                getData();
            },
            error: function (error) {
                console.log(error);
                getData();
            }
        });
};

// Empty modals after closing
function emptyModals() {
    // Remove guest information from View modal fields
    $("#viewId").html("No guest selected.");
    $("#viewGuestName").empty();
    $("#viewBirthDate").empty();
    $("#viewMail").empty();
    $("#viewPhone").empty();
    $("#viewPassportNr").empty();
    $("#viewAddress").empty();
    $("#viewCity").empty();

    // Remove guest information from Edit modal fields
    $("#editId").val("");
    $("#editGuestName").val("");
    $("#editBirthDate").val("");
    $("#editMail").val("");
    $("#editPhone").val("");
    $("#editPassportNr").val("");
    $("#editAddress").val("");
    $("#editCity").val("");
};