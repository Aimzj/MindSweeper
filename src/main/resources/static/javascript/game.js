function testFunction( row, col){
    console.log(row);
    console.log(col);

    var board = za.co.bbd.model.Board;
    board.openSpace(col,row);
    
}