import java.util.*;

class Board{
  private int  board_size;
  private int no_of_dice;
  private HashMap<Integer,Snake> snakes;
  private HashMap<Integer,Ladder> ladders;
  private Queue<Player> player_turn;
  Board(int board_size,int no_of_dice){
    this.board_size=board_size;
    this.no_of_dice=no_of_dice; //our own board size
  }
  Board(){
    this(100,1); //default board size
  }
  public int getBoardSize(){
    return board_size;
  }
  public void setSnakes(HashMap<Integer,Snake> snakes){
    this.snakes=snakes;
  }
  public void setLadders(HashMap<Integer,Ladder> ladders){
    this.ladders=ladders;
  }
  public void setPlayers(Queue<Player> player_turn){
    this.player_turn=player_turn;
  }
  public void startGame(){
    Dice dice = new Dice(no_of_dice);
    Stack<Integer> consicutive_sixes= new Stack<>(); 
    boolean wonGame=false;
    while(true){    
          Player player=player_turn.poll();
          while(true){
                int current_position=player.getPosition();
                consicutive_sixes.push(current_position);
                int roll=dice.getRoleDice();
                System.out.println(roll+"dice roll value");
                int next_position=current_position+roll;
                System.out.println(player.getPlayerName()+"moved from"+current_position+"to"+next_position+"  ");
                if(next_position<=getBoardSize()){
                        if(snakes.containsKey(next_position)){
                          System.out.println(player.getPlayerName()+"snake head move to tail");
                          Snake s=snakes.get(next_position);
                          next_position=s.getTail();
                        }
                        if(ladders.containsKey(next_position)){
                          System.out.println(player.getPlayerName()+"you clambed up");
                          Ladder l=ladders.get(next_position);
                          next_position=l.getEnd();
                        }
                        current_position=next_position;
                        System.out.println(player.getPlayerName()+"moved to"+current_position);
                        if(current_position==getBoardSize()){
                          System.out.println(player.getPlayerName()+"congrats you won the game");
                          wonGame=true;
                          break;
                        }
                        player.setPosition(current_position);
                }
                //if we role dice 3 times consicutivelly
                if(roll==6){
                  if(consicutive_sixes.size()==3){
                            while(consicutive_sixes.size()!=0){
                              player.setPosition(consicutive_sixes.pop());
                            }
                  }
                }
                else{
                  consicutive_sixes.clear();
                  break;
                }
          }
          if(wonGame){
                  break;
                }
          player_turn.add(player);
    }
  }
}