function testFunction(id, row1, col1){
    console.log(row1);
    console.log(col1);

    var formData = new FormData();
    formData.set('row', row1);
    formData.set('column', col1);
    
    axios({
        method: 'POST',
        url: "/game/"+id,
        data: formData,
        config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function() {
        location.reload(true);
    })
    .catch(function() {});

}

function flagFunction(id, row1, col1){
    console.log(row1);
    console.log(col1);

    var formData = new FormData();
    formData.set('row', row1);
    formData.set('column', col1);

    axios({
        method: 'PUT',
        url: "/game/"+id,
        data: formData,
        config: { headers: {'Content-Type': 'multipart/form-data' }}
    })
    .then(function() {
        location.reload(true);
    })
    .catch(function() {});
}

function postUser(){
    user = document.getElementById("username").value;

    $.post("/games/start/",{
        username: user
    });
    
    console.log(user);
    
}

function endGame()
{
    console.log("hello");
    var id = document.getElementById("idNum").textContent
    $.post( id+"/end/");
}

function endgame2()
{
    location.reload(true);
}