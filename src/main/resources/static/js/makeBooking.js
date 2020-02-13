let today = new Date();
let ageCheckDate = new Date(today.setMonth(today.getMonth()-216));
//$.format.date(today,"dd-MM-yyyy");

$(document).ready(function() {

    initDatePickers();




});

function initDatePickers() {

    // Datepicker for check-in
    $("#checkInDate").datepicker({
        dateFormat: "dd-mm-yy",
        changeMonth: true,
        changeYear: true,
        maxDate: "+6M",
        minDate: 0,
        constrainInput: false,
        firstDay: 1,
        autoSize: true,
        beforeShowDay: function(date) {
            if(date.getDay() == 6 || date.getDay() == 0) {
                return [true, "Highlighted", ''];
            } else {
                return [true, '', ''];
            }
        }
    });

    // Datepicker for check-out-in
        $("#checkOutDate").datepicker({
            dateFormat: "dd-mm-yy",
            changeMonth: true,
            changeYear: true,
            maxDate: "+6M",
            minDate: 0,
            constrainInput: false,
            firstDay: 1,
            autoSize: true,
            beforeShowDay: function(date) {
                if(date.getDay() == 6 || date.getDay() == 0) {
                    return [true, "Highlighted", ''];
                } else {
                    return [true, '', ''];
                }
            }
        });

        $("#checkInDate").change(function() {

             $("#checkOutDate").datepicker('option', {
                minDate: $("#checkInDate").val()
                });
        });

    // Datepicker for date of birth
    $("#bookingGuestBirthDate").datepicker({
        dateFormat: "dd-mm-yy",
        changeMonth: true,
        changeYear: true,
        maxDate: "-18Y",
        constrainInput: false,
        firstDay: 1,
        autoSize: true,
        beforeShowDay: function(date) {
            if(date.getDay() == 6 || date.getDay() == 0) {
                return [true, "Highlighted", ''];
            } else {
                return [true, '', ''];
            }
        }
    });
}