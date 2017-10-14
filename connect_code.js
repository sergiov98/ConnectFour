
var MAXROWS = 6;
var MAXCOLS = 7;
var WIN = 4;

var playerId = 0;
var highestPosition = [0,0,0,0,0,0,0];
var grid =[
  [-1,-1,-1,-1,-1,-1,-1],
  [-1,-1,-1,-1,-1,-1,-1],
  [-1,-1,-1,-1,-1,-1,-1],
  [-1,-1,-1,-1,-1,-1,-1],
  [-1,-1,-1,-1,-1,-1,-1],
  [-1,-1,-1,-1,-1,-1,-1]

];

var recentR;
var recentC;
var tempC, tempR;
var gameEnd = 0;

/*
 * Given a column, add a piece to the first empty row in the column
 */
function addPiece(column){
  if(gameEnd == 1){
    alert("Player " + (playerId+1) + " won! Play Again?");
    return;
  }
  var row = getRow(column);
  if(row == 0){
    if(gameOver() == 3){
      alert("It's a draw, reset?");
      return;
    }
    alert("That column is filled! Please pick another one");
    return;
  }
  var id = row + "," + column;
  if( playerId == 0){
    document.getElementById(id).style.fill = "red";
    grid[row-1][column-1] = 0;
  }
  else{
    document.getElementById(id).style.fill = "yellow";
    grid[row-1][column-1] = 1;
  }
  recentC =  column-1;
  recentR = row-1;
  if( gameOver() == 1){
    confirm("Player " + (playerId+1) + " won! Play Again?")
    gameEnd = 1;
    return;
  }
  changePlayer();
}

/*
 * Gets the highest empty row in a specific column
 */
function getRow(column){
  highestPosition[column-1] =  highestPosition[column-1]+1;
  var row = highestPosition[column-1];
  if(row > MAXROWS){
    return 0;
  }

  return row;
}

/*
 * Changes the player
 */
function changePlayer(){
  playerId = (playerId + 1) % 2;
  return playerId;
}

/*
 * Checks if player won the game
 */
function gameOver(){

  //checks if the board is filled
  var colsFilled = 1;
  for(c = 0; c < MAXCOLS; c++){
    if(highestPosition[c] == 6 ){
      colsFilled++;
    }
  }
  if(colsFilled == 7){
    return 3;
  }

  //checks horizontally for a 4 in a row
  var inARow = 0;
  for(col = 0; col < MAXCOLS; col++){
    if( grid[recentR][col] == playerId  ){
      inARow++;
      if(inARow == WIN){
        return 1;
      }
    }
    else{
      inARow = 0;
    }
  }


  //checks vertically for a 4 in a row
  inARow = 0;
  for(row = 0; row < MAXROWS; row++){
    if( grid[row][recentC] == playerId ){
      inARow++;
      if( inARow == WIN){
        return 1;
      }
    }
    else{
      inARow = 0;
    }
  }

  //checks diagonally for a four in a row (bottom left to top right)
  inARow = 0;
  tempR = recentR;
  tempC = recentC;
  while(tempR > 0 && tempC > 0){
    tempR--;
    tempC--;
  }
  while(tempR < MAXROWS && tempC < MAXCOLS){
    if( grid[tempR][tempC] == playerId ){
      inARow++;
      if(inARow == WIN){
        return 1;
      }
    }
    else{
      inARow = 0;
    }
    tempR++;
    tempC++;
  }

  //checks diagonally for a four in a row (bottom right to top left)
  inARow = 0;
  tempR = recentR;
  tempC = recentC;
  while(tempR < MAXROWS - 1 && tempC > 0) {
    tempR++;
    tempC--;
  }
  while(tempR >= 0 && tempC < MAXCOLS) {
    if( grid[tempR][tempC] == playerId){
      inARow++;
      if(inARow == WIN){
        return 1;
      }
    }
    else{
      inARow = 0;
    }
    tempR--;
    tempC++;
  }
  return 0;

}
/*
 * Resets the game, changing colors to white and playerId to zero
 */
function reset(){
  for(row = 1; row <= MAXROWS; row++){
    for(col = 1; col <= MAXCOLS; col++){
      var id = row + "," + col;
      document.getElementById(id).style.fill = "white";
    }
  }
  playerId = 0;
  // Changes all highest positions to zero
  for(i = 0; i < MAXCOLS; i++){
    highestPosition[i] = 0;
  }

  //changes every elem in grid into -1
  for(row = 0; row < MAXROWS; row++){
    for(col = 0; col < MAXCOLS; col++){
      grid[row][col] = -1;
    }
  }
  gameEnd = 0;
}
