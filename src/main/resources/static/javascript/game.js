function testFunction(id, row1, col1){
    console.log(row1);
    console.log(col1);

    $.post("/game/"+id,
    {
        row: row1,
        column: col1
    });

    location.reload(true);
    
}

function postUser(){
    user = document.getElementById("username").value;

    $.post("/games/start/",{
        username: user
    });
    
    console.log(user);
}