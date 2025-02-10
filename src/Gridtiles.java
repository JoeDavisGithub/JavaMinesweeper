//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
public class Gridtiles {
    private static final int rows = 15;
    private static final int columns = 15;
    private int rowit =1;
    private int colit=0;
    private ArrayList<ArrayList<GridItem>> gridmap = new ArrayList<ArrayList<GridItem>>();


    public ArrayList<ArrayList<GridItem>> getGridmap(){
        return this.gridmap;
    }
    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
    public void initialisemap(){
        for(int RC=0;RC<this.rows;RC++){
            ArrayList<GridItem> temp = new ArrayList<GridItem>();
            for(int CC=0;CC<this.rows;CC++){
                Random rn = new Random();
                int decider = rn.nextInt(10);
                temp.add(new GridItem(decider));
            }
        this.gridmap.add(temp);

        }

    }
    public void DisplayMap(){
        this.rowit=15;

        System.out.println("------------------------------------------------");
        for(ArrayList<GridItem> RL:this.gridmap){

            if(String.valueOf(rowit).length()<2){
                System.out.print(" ");
                System.out.print(rowit);
                System.out.print("|");
            }else{
                System.out.print(rowit);
                System.out.print("|");
            }
            System.out.print("  ");
            /*  Legacy formatting. kept incase of reform
            switch(String.valueOf(rowit).length()){
                case 1:
                    System.out.print("  ");
                    break;
                case 2:
                    System.out.print(" ");
                    break;
            }*/
            rowit=rowit-1;
            for(GridItem CL:RL){
                int tt = CL.getStatus();
                switch(tt){
                    case 99:
                        System.out.print("BB");
                        break;
                    case 10:
                        System.out.print("##");
                        break;
                    default:
                        System.out.print(CL.getStatus());
                        break;

                }
                //System.out.print(CL.getStatus());
                switch(String.valueOf(CL.getStatus()).length()){
                    case 1:
                        System.out.print("  ");
                        break;
                    case 2:
                        System.out.print(" ");
                        break;
                }
            }
            System.out.print("\n");
        }
        System.out.println("------------------------------------------------");
        System.out.println("     1  2  3  4  5  6  7  8  9  10 11 12 13 14 15");
        System.out.println("------------------------------------------------");
    }

    public void initialiseStatus() {
        for(int RC=0;RC<this.rows;RC++){


            for(int CC=0;CC<this.rows;CC++){
                boolean topcheck = true;
                boolean leftcheck = true;
                boolean botcheck = true;
                boolean rightcheck = true;
                if(RC==0){
                    topcheck = false;
                }
                if(RC==(this.rows-1)){
                    botcheck = false;
                }
                if(CC==0){
                    leftcheck=false;
                }
                if(CC==(this.columns-1)){
                    rightcheck=false;
                }
                if(topcheck){
                    if(leftcheck){
                        if(this.gridmap.get(RC-1).get(CC-1).getBomb()){
                            this.gridmap.get(RC).get(CC).updateStatus();
                        }
                    }
                    if(this.gridmap.get(RC-1).get(CC).getBomb()){
                        this.gridmap.get(RC).get(CC).updateStatus();
                    }
                    if(rightcheck){
                        if(this.gridmap.get(RC-1).get(CC+1).getBomb()){
                            this.gridmap.get(RC).get(CC).updateStatus();
                        }
                    }
                }
                if(botcheck){
                    if(leftcheck){
                        if(this.gridmap.get(RC+1).get(CC-1).getBomb()){
                            this.gridmap.get(RC).get(CC).updateStatus();
                        }
                    }
                    if(this.gridmap.get(RC+1).get(CC).getBomb()){
                        this.gridmap.get(RC).get(CC).updateStatus();
                    }
                    if(rightcheck){
                        if(this.gridmap.get(RC+1).get(CC+1).getBomb()){
                            this.gridmap.get(RC).get(CC).updateStatus();
                        }
                    }
                }
                if(leftcheck){
                    if(this.gridmap.get(RC).get(CC-1).getBomb()){
                        this.gridmap.get(RC).get(CC).updateStatus();
                    }
                }
                if(rightcheck){
                    if(this.gridmap.get(RC).get(CC+1).getBomb()){
                        this.gridmap.get(RC).get(CC).updateStatus();
                    }
                }

                //this.gridmap.get(RC).get(CC).updateStatus();
                //System.out.println(this.gridmap.get(RC).get(CC).getStatus());
            }
        }
    }
    // NEEDS TO BE IMPLEMENTED AND CHECKED
    public void sanitychecker(){
        boolean sancheck = false;
        for(ArrayList<GridItem> RL:this.gridmap){
            for(GridItem CL:RL){
                if(CL.getBomb()){
                    sancheck=true;
                }
            }

        }
        if(!sancheck){
            System.out.println("GAME HAS NO BOMBS");
        }else{
            System.out.println("GAME HAS BOMBS");
        }
    }

    public void selector(int x, int y){
        System.out.println(this.gridmap.get(15-y).get(x-1).getStatus());
        this.gridmap.get(15-y).get(x-1).updateStatus(44);
    }

    public void gameSelector(int x, int y){
        this.gridmap.get(15-y).get(x-1).setRevealed();
        this.DisplayMap();
    }
    public static void main(String[] args) {
        Gridtiles Gameenv = new Gridtiles();
        Gameenv.initialisemap();
        Gameenv.DisplayMap();
        Gameenv.initialiseStatus();
        Gameenv.DisplayMap();
        Gameenv.sanitychecker();
        Gameenv.gameSelector(1,1);


        /*testing selection within boundaries working
        Gameenv.selector(1,1);
        Gameenv.selector(1,15);
        Gameenv.selector(15,1);
        Gameenv.selector(15,15);
        Gameenv.DisplayMap();
         */
    }

/*
    CHECKLIST LEFT:
    implement hidden / revealed
    implement checking algo to reveal nearby zeroes
    implement user interaction
    implement game over state
    implement game win state
    implement use of sanity checker to regen map if no bombs.
    implement catches for stupid user input
 */
}
