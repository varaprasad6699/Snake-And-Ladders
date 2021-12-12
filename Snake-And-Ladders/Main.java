import java.util.*;
class Main {
  public static void main(String[] args) {
    System.out.println("Lets Play Snake and Ladders !!!");
    Scanner in = new Scanner(System.in);
    // enetr board size  the default is 100
    System.out.println("Enter Board Size  the default is 100");
    int board_size=in.nextInt();
    // enetr no of  dice  the default is 1
    System.out.println("Enter no of dice  the default is 1");
    int no_of_dice=in.nextInt();
    // enter no of snakes
    System.out.println("Enter no of snakes");
    int no_of_snakes=in.nextInt();
    in.nextLine();
    HashMap<Integer, Snake> snakeMap = new HashMap<Integer, Snake>();
    while(no_of_snakes>0){
      // snake head(val1) has more value than tail(val2)
      String[] str= in.nextLine().split(" ");
      int val1=Integer.parseInt(str[0]);
      int val2=Integer.parseInt(str[1]);
      Snake snake= new Snake(val1,val2);
      snakeMap.put(val1,snake);
      no_of_snakes--;
    }
    System.out.println("Enter no of ladders");
    int no_of_ladders=in.nextInt();
    in.nextLine();
    HashMap<Integer, Ladder> laddersMap = new HashMap<Integer, Ladder>();
    while(no_of_ladders>0){
      // ladder start(val1) has less value than end(val2)
      String[] str= in.nextLine().split(" ");
      int val1=Integer.parseInt(str[0]);
      int val2=Integer.parseInt(str[1]);
      Ladder ladder= new Ladder(val1,val2);
      laddersMap.put(val1,ladder);
      no_of_ladders--;
    }
    System.out.println("Enter no of players");
    int no_of_players=in.nextInt();
    in.nextLine();
    Queue<Player> players= new LinkedList<Player>();
    while(no_of_players>0){
      String str= in.nextLine();
      Player player= new Player(str);
      players.add(player);
      no_of_players--;
    }
    //here we start with our main code
    Board board = new Board(board_size,no_of_dice);
    // we haved done with setting snake ;adder players to board 
    board.setSnakes(snakeMap);
    board.setLadders(laddersMap);
    board.setPlayers(players);
    // lets start the game
    board.startGame();
  }
}