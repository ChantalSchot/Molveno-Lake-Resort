const guestApi = "http://localhost:8080/api/guests";

var guestTable;

$(document).ready(function() {
    initGuestTable();

    getGuestData();

    $("#fetchGuestList").click(function() {
        getGuestData();
    });

    $("#clearGuestList").click(function() {
        clearGuestTable();
    });

    // Add 'selected' class on row (for selection of data/guest ID)
    $("#guestTable tbody").on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
          emptyGuestModals();
        }
        else {
          guestTable.$('tr.selected').removeClass('selected');
            emptyGuestModals();
            $(this).addClass('selected');
        }
      });

    // View guest details: get ID from selected row
     $("#viewGuestButton").click(function() {
        viewGuestModal(guestTable.row($('.selected')).data());
      });

    // Edit guest details: get ID from selected row
    $(".editGuestButton").click(function() {
        editGuestModal(guestTable.row($('.selected')).data());
    });

    // Save guest details after editing/adding new
    $("#saveGuestButton").click(function() {
        saveGuest();
        getGuestData();
    });

    //Open 'delete confirmation' modal
    $("#deleteGuestButton").click(function() {
        // Check if guest is selected
        if (guestTable.row($('.selected')).data() == undefined) {
            $("#noGuestSelectedModal").modal("show");
        } else {
            // If guest is selected, open modal to confirm deletion
            $("#deleteGuestModal").modal("show");
        }
    });

    // Confirm deletion of guest that is currently selected
     $("#deleteGuestConfirmButton").click(function() {
        deleteGuest(guestTable.row($('.selected')).data());
      });

     // When a modal is closed, the fields inside should be emptied
    $(".guest-close-button").click(function() {
        emptyGuestModals();
    });

});

function initGuestTable() {
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
   guestTable = $("#guestTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    } );
}

// Clear all data from table
function clearGuestTable() {
    guestTable.clear();
    guestTable.columns.adjust().draw();
}

// Fetch guest data from API
function getGuestData() {
    $.get(
        {
            url: guestApi,
            dataType: "json",
            success: function (data) {
                if (data) {
                    guestTable.clear();
                    guestTable.rows.add(data);
                    guestTable.columns.adjust().draw();
                }
            }
        }
    );
}

// View guest details with ID from selected row
function viewGuestModal(guest) {
    // Ensure that guest ID is a number (not string)
    // let guestId = +id;
    if (guest == undefined) {
        $("#noGuestSelectedModal").modal("show");
    } else {
        $("#viewGuestModal").modal("show");
        $.ajax({
            url: guestApi + "/" + guest.id,
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                // If guest was retrieved, add guest details to modal
                showGuestDetails(result);
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

};

// Enter guest information in the 'view guest' modal
function showGuestDetails(result){
    // Add guest information to View modal fields
    $("#viewGuestId").html(result.id);
    $("#viewGuestName").html(result.name);
    $("#viewGuestBirthDate").html(result.birthDate);
    $("#viewGuestMail").html(result.mail);
    $("#viewGuestPhone").html(result.phone);
    $("#viewGuestPassportNr").html(result.passportNr);
    $("#viewGuestAddress").html(result.address);
    $("#viewGuestCity").html(result.city);
};


function editGuestModal(guest) {
    // Check if guest is selected
    if (guest == undefined) {
        $("#noGuestSelectedModal").modal("show");
    } else {
        $("#editGuestModal").modal("show");
        // Fill in guest to modal fields
        $("#editGuestId").val(guest.id);
        $("#editGuestName").val(guest.name);
        $("#editGuestBirthDate").val(guest.birthDate);
        $("#editGuestMail").val(guest.mail);
        $("#editGuestPhone").val(guest.phone);
        $("#editGuestPassportNr").val(guest.passportNr);
        $("#editGuestAddress").val(guest.address);
        $("#editGuestCity").val(guest.city);
    }
}

function saveGuest() {

    let guestObject = {
        id: +$("#editGuestId").val(),
        name: $("#editGuestName").val(),
        birthDate: $("#editGuestBirthDate").val(),
        mail: $("#editGuestMail").val(),
        phone: $("#editGuestPhone").val(),
        passportNr: $("#editGuestPassportNr").val(),
        address: $("#editGuestAddress").val(),
        city: $("#editGuestCity").val()
    };
    console.log("New guestObject: " + guestObject);

    var jsonObject = JSON.stringify(guestObject);
    console.log("new jsonObject: " + jsonObject)

    $.ajax({
        url: guestApi,
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
            emptyGuestModals();
            getGuestData();
        },
        error: function (error) {
            console.log(error);
            getGuestData();
        }
    });


}


// Delete selected guest with ID from datatable & database
function deleteGuest(guest) {


        $("#deleteGuestModal").modal("show");
        $.ajax({
            url: guestApi,
            type: "delete",
            dataType: "json",
            data: JSON.stringify(guest),
            contentType: "application/json",
            success: function () {
                getGuestData();
            },
            error: function (error) {
                console.log(error);
                getGuestData();
            }
        });
};

// Empty modals after closing
function emptyGuestModals() {
    // Remove guest information from View modal fields
    $("#viewGuestId").html("No guest selected.");
    $("#viewGuestName").empty();
    $("#viewGuestBirthDate").empty();
    $("#viewGuestMail").empty();
    $("#viewGuestPhone").empty();
    $("#viewGuestPassportNr").empty();
    $("#viewGuestAddress").empty();
    $("#viewGuestCity").empty();

    // Remove guest information from Edit modal fields
    $("#editGuestId").val("");
    $("#editGuestName").val("");
    $("#editGuestBirthDate").val("");
    $("#editGuestMail").val("");
    $("#editGuestPhone").val("");
    $("#editGuestPassportNr").val("");
    $("#editGuestAddress").val("");
    $("#editGuestCity").val("");
};