const api = "http://localhost:8080/api/bookings";

var table;

$(document).ready(function() {
    initDataTable();

    // Enter current guest API into data table (only data from PostConstruct for now)
    getData();
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