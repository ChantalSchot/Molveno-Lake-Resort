function roomList(){
    window.location.replace("/Admin/roomList.html");
}


function postRoom() {
    var one = $('#name').val();
    var two = $('#single').val();
    var two = $('#double').val();
    var two = $('#baby').val();
    var two = $('#available').val();
    var two = $('#disabled').val();

var room = {
    roomNumber : $('#name').val(),
    numberOfSingleBeds : $('#single').val(),
    numberOfDoubleBeds : $('#double').val(),
    numberOfBabyBeds : $('#baby').val(),
    available : $('#available').val(),
    disabledRoom : $('#disabled').val(),
};
if(room.available == "on"){
    room.available = true
}
if(room.available == "off"){
    room.available = false
}
if(room.disabledRoom == "on"){
    room.disabledRoom = true
}
if(room.disabledRoom == "off"){
    room.disabledRoom = false
}

var jsonObject =JSON.stringify(room);

let api = "/api/rooms";
$.ajax({
    url: api,
    type: "post",
    data: jsonObject,
    contentType: "application/json",
    success: function(data){
        console.log("success post data!")
    },

    error: function (error) {
        console.log(error);
    }
});
}