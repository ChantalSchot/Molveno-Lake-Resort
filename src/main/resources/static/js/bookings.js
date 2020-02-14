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

    // Save editted information
    $("#saveBookingButton").click(function() {
        saveBooking(bookingTable.row($('.selected')).data());
    });
});

function initDataTable() {
// Create columns (with titles) for datatable
    columns = [
        { "title":  "Booking Number",
        "data": "id",
        "width": "10%"},
        { "title":  "Guest Name",
        "data": "guest.name" },
        { "title":  "Check-In",
        "data": "checkInDate",
        "render": function(data) {
            return $.format.date(data,"E dd-MM-yyyy");
        }},
        { "title":  "Check-Out",
        "data": "checkOutDate",
        "render": function(data) {
            return $.format.date(data,"E dd-MM-yyyy");
        }}
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
    $("#viewBookingGuest").html(result.guest.name);
    $("#viewBookingTotalGuests").html(result.totalGuests);
    $("#viewBookingRooms").html(getBookedRooms(result));
    $("#viewBookingInvoice").html(getInvoiceTotal(result));
    $("#viewBookingStatus").html(result.status);
    $("#viewBookingCheckIn").html($.format.date(result.checkInDate,"E dd-MM-yyyy"));
    $("#viewBookingCheckOut").html($.format.date(result.checkOutDate,"E dd-MM-yyyy"));
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
        $("#editBookingGuest").val(booking.guest.name);
        $("#editBookingTotalGuests").val(booking.totalGuests);
        $("#editBookingRooms").val(getBookedRooms(booking));
        $("#editBookingInvoice").val(booking.invoice);
        $("#editBookingStatus").val(getBookingStatus(booking));
        $("#editBookingCheckIn").val($.format.date(booking.checkInDate,"E dd-MM-yyyy"));
        $("#editBookingCheckOut").val($.format.date(booking.checkOutDate,"E dd-MM-yyyy"));
    }
};

function saveBooking(booking) {
    let bookingObject = {
        id: booking.id,
        guest: booking.guest,
        totalGuests: $("#editBookingTotalGuests").val(),
        status: getRadioValue(),
        checkInDate: booking.checkInDate,
        checkOutDate: booking.checkOutDate,
        bookedRooms: booking.bookedRooms,
        invoice: booking.invoice
    };
    console.log("New bookingObject: " + bookingObject);

    var jsonObject = JSON.stringify(bookingObject);
    console.log("new jsonObject: " + jsonObject)

    $.post({
        url: bookingApi,
        dataType: "json",
        data: jsonObject,
        contentType: "application/json",
        success: function(result) {
            console.log("Booking posted / edited: " + result);
            getData();
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function getGuestByBookingId(data) {
    $.ajax({
        url: bookingApi + "/" + data + "/guest",
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            console.log("regel 201 " + result.name);
            return result.name;
        },
        error: function (error) {
           console.log(error);
        }
    });
}

function renderDateTime(data) {
    return($.format.date(data, "E yyyy/MM/dd"));
}

function getInvoiceTotal(booking) {
    if(booking.bookedRooms != undefined) {
        var totalprice = 0;
        $.each(booking.bookedRooms, function(index, value) {
            console.log("value.price = " + value.price + " getTotalDays = " + getTotalDays(booking.checkInDate, booking.checkOutDate));
            totalprice += value.price * (getTotalDays(booking.checkInDate, booking.checkOutDate));
            console.log(totalprice);
        });
        return totalprice;
    } else {
        return 0;
    }
}

function getTotalDays(date1, date2) {
    var priceCheckInDate = new Date(date1);
    var priceCheckOutDate = new Date(date2);
    var priceDifferenceInTime = priceCheckOutDate.getTime() - priceCheckInDate.getTime();
    return priceDifferenceInTime / (1000 * 3600 * 24);
}

function getBookedRooms(booking) {
    var roomsString = "";
    $.each(booking.bookedRooms, function(index, value) {
        roomsString += value.roomNumber;
        if (!(index === booking.bookedRooms.length - 1)) {
            roomsString += ", ";
        }
    });
    return roomsString;
}

function getBookingStatus(booking) {
    if(booking.status == "booked"){
        document.getElementById('editBookingStatusBooked').checked = true;
        document.getElementById('editBookingStatusReserved').checked = false;
        document.getElementById('editBookingStatusCanceled').checked = false;
    } else if (booking.status == "reserved") {
        document.getElementById('editBookingStatusBooked').checked = false;
        document.getElementById('editBookingStatusReserved').checked = true;
        document.getElementById('editBookingStatusCanceled').checked = false;
    } else {
        document.getElementById('editBookingStatusBooked').checked = false;
        document.getElementById('editBookingStatusReserved').checked = false;
        document.getElementById('editBookingStatusCanceled').checked = true;
    }
}

function getRadioValue() {
    var radios = document.getElementsByName('editBookingStatus');
    for (var i = 0, length = radios.length; i < length; i++) {
      if (radios[i].checked) {
        return radios[i].value;
      }
    }
}