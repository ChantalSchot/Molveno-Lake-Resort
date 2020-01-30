function getRoomList() {
    let api = "/api/rooms";
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            let string = "";
            $.each(data, function (index, value) {
                let availability = "unavailable";

                if(value.available == true){
                    availability = "available";
                }
                
                let link = "<a href=\"/admin/roomUpdate.html?id="+ value.roomID  +"\">update</a>";
                string += "<p>" + value.roomNumber + " , " + availability +" , " + link + "</p>"
            });
            $('#rooms').html(string);
        },

        error: function (error) {
            console.log(error);
        }
    });
}

function roomCreate() {
    window.location.replace("/Admin/roomCreate.html");
}