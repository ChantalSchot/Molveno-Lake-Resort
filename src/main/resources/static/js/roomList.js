var roomtable;

$(document).ready(function() {
        
        initRoomTable();
        getRoomList();

        $("#roomTable tbody").on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
              $(this).removeClass('selected');
              emptyRoomModals();
            }
            else {
                roomtable.$('tr.selected').removeClass('selected');
              emptyRoomModals();
                $(this).addClass('selected');
            }
          });

        
    // View guest details: get ID from selected row
     $("#viewRoom").click(function() {
        // Get guest info by ID
        getRoom(roomtable.row($('.selected')).data());
        // console.log("Viewing guest: " + row.name);
      });

          // View guest details: get ID from selected row
    $(".editRoom").click(function() {
        // Get guest info by ID
        editRoomModal(roomtable.row($('.selected')).data());
        //console.log("Editing guest: " + row.name);
    });

    $("#saveRoom").click(function() {
        updateRoom();
    });

    $("#deleteRoom").click(function() {
        // Check if guest is selected
        if (roomtable.row($('.selected')).data() == undefined) {
            $("#noRoomSelectedModal").modal("show");
        } else {
            $("#deleteRoomModal").modal("show");
        }
    });

    // Delete guest that is currently selected
     $("#deleteRoomConfirm").click(function() {
        // Delete guest from database & datatable
        deleteRoom(roomtable.row($('.selected')).data());
        // console.log("Deleting guest: " + row.name);
      });

      
});



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


function getRoom(room){
    if(room.id !=null){
    let api = "/api/rooms/" + room.id;
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function(data){
            if(data.disabledRoom == false){
                data.disabledRoom = "False";
            }
            if(data.available == false){
                data.available = "False";
            }

                showRoom(data);
        },

        error: function (error) {
            console.log(error);
        }
    });
}
};



// Enter guest information in the 'view guest' modal
function showRoom(result){
    // Add guest information to View modal fields
    $("#viewRoomId").html(result.id);
    $("#viewRoomName").html(result.roomNumber);
    $("#viewRoomAvailability").html(result.available);
    $("#viewSingleBeds").html(result.numberOfSingleBeds);
    $("#viewDoubleBeds").html(result.numberOfDoubleBeds);
    $("#viewBabyBeds").html(result.numberOfBabyBeds);
    $("#viewDisabledRoom").html(result.disabledRoom);
    $("#viewFacilities").html(result.facilities);
    $("#viewNoAdults").html(result.noOfAdults);
    $("#viewNoChildren").html(result.noOfChildren);
    $("#viewroomType").html(result.roomType);
    $("#viewStatus").html(result.roomStatus);
};


// Empty modals after closing
function emptyRoomModals() {
    // Remove room information from View modal fields
    $("#viewRoomId").empty();
    $("#viewRoomName").empty();
    $("#viewRoomAvailability").empty();
    $("#viewSingleBeds").empty();
    $("#viewDoubleBeds").empty();
    $("#viewBabyBeds").empty();
    $("#viewDisabledRoom").empty();
 //   $("#viewFacilities").empty();
    $("#viewNoAdults").empty();
    $("#viewNoChildren").empty();
    $("#viewroomType").empty();
  //  $("#viewStatus").empty();
};

function editRoomModal(room) {
    // Check if guest is selected
    if (room == undefined) {
        $("#noRoomSelectedModal").modal("show");
    } else {

        if(room.available){
            document.getElementById('editRoomAvailability').checked = true;
        }
        if(room.disabledRoom){
            document.getElementById('editDisabledRoom').checked = true;
        }

        $("#editRoomModal").modal("show");
        // Add guest information to View modal fields
        $("#editRoomId").val(room.id);
        $("#editRoomName").val(room.roomNumber);
        $("#editSingleBeds").val(room.numberOfSingleBeds);
        $("#editDoubleBeds").val(room.numberOfDoubleBeds);
        $("#editBabyBeds").val(room.numberOfBabyBeds);
      //  $("#editFacilities").val(room.facilities);
        $("#editNoAdults").val(room.noOfAdults);
        $("#editNoChildren").val(room.noOfChildren);
        $("#editroomType").val(room.roomType);
      //  $("#editStatus").val(room.roomStatus);
    }


}

function updateRoom(){
    var room = {
        id : +$("#editRoomId").val(),
        roomNumber : $('#editRoomName').val(),
        numberOfSingleBeds : +$('#editSingleBeds').val(),
        numberOfDoubleBeds : +$('#editDoubleBeds').val(),
        numberOfBabyBeds : +$('#editBabyBeds').val(),
        available : $('#editRoomAvailability').prop('checked'),
        disabledRoom : $('#editDisabledRoom').prop('checked'),
        noOfAdults :  +$("#editNoAdults").val(),
        noOfChildren : +$("#editNoChildren").val(),
        roomType : $("#editroomType").val()
    };

    
    var jsonObject =JSON.stringify(room);
    
    let api = "/api/rooms/";
    $.ajax({
        url: api,
        type: "put",
        data: jsonObject,
        contentType: "application/json",
        success: function(data){
            alert("Room is successfully updated.")
            getRoomList();
        },
    
        error: function (error) {
            console.log(error);
        }
    });

    
}


function deleteRoom(data){
    $("#deleteRoomModal").modal("show");
    let api = "/api/rooms/" + data.id;
    $.ajax({
        url: api,
        type: "delete",
        contentType: "application/json",
        success: function(data){
            window.location.replace("/Admin/roomList.html");
        },
    
        error: function (error) {
            console.log(error);
        }
    });
}