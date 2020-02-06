const bookingApi = "http://localhost:8080/api/bookings";

var bookingTable;

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
    $("#bookingTable tbody").on('click', 'tr', function () {
        if($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            emptyModals();
        } else {
            bookingTable.$('tr.selected').removeClass('selected');
            emptyModals();
            $(this).addClass('selected');
        }
    });

    // View booking details: get ID from selected row
    $("#viewBookingButton").click(function() {
        viewBookingModal(bookingTable.row($('.selected')).data());
    });

    // Edit booking details: get ID from selected row
    $("#editBookingButton").click(function() {
        editBookingModal(bookingTable.row($('.selected')).data());
    });
});

function initDataTable() {
// Create columns (with titles) for datatable
    columns = [
        { "title":  "Booking ID",
        "data": "id" },
        { "title":  "Guest Name",
        "data": "guest" },
        { "title":  "No. of guests",
        "data": "totalGuests" },
        { "title":  "Check-In",
        "data": "checkInDate"},
        { "title":  "Check-Out",
        "data": "checkOutDate"}
    ];

    // Define new table with above columns
   bookingTable = $("#bookingTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    } );
}

// Fetch guest data from API
function getData() {
    $.get(
        {
            url: bookingApi,
            dataType: "json",
            success: function (data) {
                if (data) {
                    bookingTable.clear();
                    bookingTable.rows.add(data);
                    bookingTable.columns.adjust().draw();
                }
            }
        }
    );
}

// Clear all data from table
function clear() {
    bookingTable.clear();
    bookingTable.columns.adjust().draw();
}

// Empty modals after closing
function emptyModals() {
    // Remove guest information from View modal fields
    $("#viewBookingId").html("No booking selected.");
    $("#viewGuestName").empty();
    $("#viewBirthDate").empty();
    $("#viewMail").empty();
    $("#viewPhone").empty();
    $("#viewPassportNr").empty();
    $("#viewAddress").empty();
    $("#viewCity").empty();
};

// Enter booking information in the 'view booking' modal
function showBooking(result){
    $("#viewBookingId").html(result.id);
    $("#viewBookingGuest").html(result.guest);
    $("#viewBookingTotalGuests").html(result.totalGuests);
    $("#viewBookingRooms").html(result.bookedRooms);
    $("#viewBookingInvoice").html(result.invoice);
    $("#viewBookingStatus").html(result.status);
    $("#viewBookingCheckIn").html(result.checkInDate);
    $("#viewBookingCheckOut").html(result.checkOutDate);
};

// View booking details with ID from selected row
function viewBookingModal(booking) {
    // Ensure that booking ID is a number (not string)
    if (booking == undefined) {
        $("#noBookingSelectedModal").modal("show");
    } else {
        $("#viewBookingModal").modal("show");
        $.ajax({
            url: bookingApi + "/" + booking.id,
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                showBooking(result);
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
};

//Modal for editing selected booking in bookingTable
function editBookingModal(booking) {
    // Check if guest is selected
    if (booking == undefined) {
        $("#noBookingSelectedModal").modal("show");
    } else {
        $("#editBookingModal").modal("show");
        // pre-fill booking to modal fields
        $("#editBookingId").val(booking.id);
        $("#editBookingGuest").val(booking.guest);
        $("#editBookingTotalGuests").val(booking.totalGuests);
        $("#editBookingRooms").val(booking.bookedRooms);
        $("#editBookingInvoice").val(booking.invoice);
        $("#editBookingStatus").val(booking.status);
        $("#editBookingCheckIn").val(booking.checkInDate);
        $("#editBookingCheckOut").val(booking.checkOutDate);
    }
};

function saveBooking() {
    let bookingObject = {
        id: +$("#editBookingId").val(),
        totalGuests: $("#editBookingTotalGuests").val()
    };
    console.log("New bookingObject: " + bookingObject);

    var jsonObject = JSON.stringify(guestObject);
    console.log("new jsonObject: " + jsonObject)

    $.ajax({
        url: bookingApi,
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