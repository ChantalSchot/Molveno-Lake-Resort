$(document).ready(function() {
        
        initRoomTable();
        getRoomList();
});

var roomtable;

function initRoomTable() {
        columns = [
            { "title":  "Room ID",
            "data": "id" },
            { "title":  "Room Name",
            "data": "roomNumber" },
            { "title":  "Availability",
            "data": "available" },
        ];
    
        // Define new table with above columns
        roomtable = $("#roomTable").DataTable( {
            "order": [[ 0, "asc" ]],
            "columns": columns
        } );
    }



function getRoomList() {
    let api = "/api/rooms";
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data) {

                roomtable.clear();
                roomtable.rows.add(data);
                roomtable.columns.adjust().draw();
            }
        },

        error: function (error) {
            console.log(error);
        }
    });
}

function roomCreate() {
    window.location.replace("/Admin/roomCreate.html");
}


