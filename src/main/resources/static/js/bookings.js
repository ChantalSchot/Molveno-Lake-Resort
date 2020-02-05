const api = "http://localhost:8080/api/bookings";

var table;

$(document).ready(function() {
    initDataTable();

    // Enter current guest API into data table (only data from PostConstruct for now)
    getData();
});

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