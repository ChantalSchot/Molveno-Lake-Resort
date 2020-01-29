function getRoomList() {
    let api = "/api/rooms";
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function(data){
            let string = "";
            $.each( data, function( index, value ){
                string+="<p>"+ value.roomNumber + " , "+ value.available + "</p>"
            });
            $('#rooms').html(string);
        },

        error: function (error) {
            console.log(error);
        }
    });
}

function createRoom(){
    window.location.replace("/Admin/roomCreate.html");
}