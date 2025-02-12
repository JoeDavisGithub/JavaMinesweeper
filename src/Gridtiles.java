//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class Gridtiles {
    private static final int rows = 15;
    private static final int columns = 15;
    private int rowit =1;
    private int colit=0;
    private ArrayList<ArrayList<GridItem>> gridmap = new ArrayList<ArrayList<GridItem>>();
    private String gameState = "Playing";


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
                int decider = rn.nextInt(50);
                //System.out.println(decider);
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
                    case 11:
                        System.out.print("⚑ ");
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

    //debug purposes
    public void selector(int x, int y){
        System.out.println(this.gridmap.get(15-y).get(x-1).getStatus());
        this.gridmap.get(15-y).get(x-1).updateStatus(44);
    }

    public void gameSelector(int x, int y,String mode){
        if(Objects.equals(mode, "c")){
            if(this.gridmap.get(15-y).get(x-1).getBomb()){
                this.gridmap.get(15-y).get(x-1).setRevealed();
                return;
            }
            if(this.gridmap.get(15-y).get(x-1).gamePeek()==0){

                this.gameRevealer(x-1,15-y);
            }
            this.gridmap.get(15-y).get(x-1).setRevealed();
            this.DisplayMap();
        }else if(Objects.equals(mode, "f")){
            this.gridmap.get(15-y).get(x-1).setMarked();
            this.DisplayMap();
        }
    }
    public void gameRevealer(int x, int y){
        boolean topchecker = true;
        boolean leftchecker = true;
        boolean rightchecker = true;
        boolean botchecker = true;
        if(x==0){
            leftchecker=false;
        }else if(x==(this.columns-1)){
            rightchecker=false;
        }
        if(y==(this.rows-1)){
            botchecker=false;
        } else if(y==0){
            topchecker=false;
        }

        if(!this.gridmap.get(y).get(x).isRevealed()){
            this.gridmap.get(y).get(x).setRevealed();
            if(topchecker){
                if(leftchecker){
                    if(this.gridmap.get(y-1).get(x-1).gamePeek()==0){
                        gameRevealer(x-1,y-1);
                    }
                    this.gridmap.get(y-1).get(x-1).gamePeek();
                }

                if(this.gridmap.get(y-1).get(x).gamePeek()==0){
                    gameRevealer(x,y-1);
                }
                this.gridmap.get(y-1).get(x).setRevealed();

                if(rightchecker){
                    if(this.gridmap.get(y-1).get(x+1).gamePeek()==0){
                        gameRevealer(x+1,y-1);
                    }
                    this.gridmap.get(y-1).get(x+1).setRevealed();
                }
            }
            if(botchecker){
                if(leftchecker){

                    if(this.gridmap.get(y+1).get(x-1).gamePeek()==0){
                        gameRevealer(x-1,y+1);
                    }
                    this.gridmap.get(y+1).get(x-1).setRevealed();
                }


                if(this.gridmap.get(y+1).get(x).gamePeek()==0){
                    gameRevealer(x,y+1);
                }
                this.gridmap.get(y+1).get(x).setRevealed();

                if(rightchecker){
                    if(this.gridmap.get(y+1).get(x+1).gamePeek()==0){
                        gameRevealer(x+1,y+1);
                    }
                    this.gridmap.get(y+1).get(x+1).setRevealed();
                }
            }
            if(leftchecker){

                if(this.gridmap.get(y).get(x-1).gamePeek()==0){
                    gameRevealer(x-1,y);
                }
                this.gridmap.get(y).get(x-1).setRevealed();
            }
            if(rightchecker){

                if(this.gridmap.get(y).get(x+1).gamePeek()==0){
                    gameRevealer(x+1,y);
                }
                this.gridmap.get(y).get(x+1).setRevealed();
            }

        }
    }
    public static boolean intstringchecker(String input) {
        try
        {
            int I = Integer.parseInt(input);
            if(I<0){
                System.out.println("The value entered cannot be used, Please enter an integer value between 1 and 15: ");
                return false;
            }else if(I > 15){
                System.out.println("This value is outside of the coordinates. Please enter an integer value between 1 and 15: ");
                return false;
            }
        } catch(Exception e){
            System.out.println("The value entered cannot be used, Please enter a positive integer value between 1 and 15: ");
            return false;
        }
        return true;
    }
    public int gameInteraction(){
        Scanner Uinput = new Scanner(System.in);
        boolean checkflag = false;
        String uinputted ="";
        while (checkflag==false) {
            uinputted = Uinput.nextLine();
            checkflag = intstringchecker(uinputted);
        }
        return Integer.parseInt(uinputted);
    }
    public boolean isChecked(int x,int y){
        if(this.gridmap.get(15-y).get(x-1).isRevealed()){
            System.out.println("This coordinate is already revealed. Try again");
            return false;
        }else{
            return true;
        }
    }
    public void gameStep(){
        int xval=0,yval = 0;
        boolean prog=false;
        String mode="";
        while(!prog) {
            Scanner Uinput = new Scanner(System.in);
            String uinputted ="";
            boolean checkflag = false;
            System.out.println("Would you like to check or flag a coordinate? (Enter c or f): ");
            while (!checkflag) {
                uinputted = Uinput.nextLine();
                if(Objects.equals(uinputted, "c")){
                    checkflag=true;
                    mode=uinputted;
                }else if(Objects.equals(uinputted, "f")){
                    checkflag=true;
                    mode=uinputted;
                }else{
                    System.out.println("This input is invalid, please enter c for check, or f for flag");
                }
            }
            System.out.print("Please enter an X coordinate between 1 and 15: ");
            xval = gameInteraction();
            System.out.print("Please enter a Y coordinate between 1 and 15: ");
            yval = gameInteraction();
            prog = isChecked(xval,yval);
        }
        if(mode.equals("c")){
            gameSelector(xval,yval,"c");
        }else if(mode.equals("f")){
            gameSelector(xval,yval,"f");
        }else{
            System.out.println("Something has gone very wrong");
        }

    }

    public void gameRun(){
        while(Objects.equals(this.gameState, "Playing")){
            this.gameStep();
            this.winCheck();
        }
        this.debugreveal();
        this.DisplayMap();
        if(gameState=="Won"){
            System.out.println("---------------You Won---------------");
        }else{
            System.out.println("---------------You Lost---------------");
        }
    }
    public void winCheck(){
        boolean winflag=true;
        for(ArrayList<GridItem> RL:this.gridmap){
            for(GridItem CL:RL){
                if(!CL.isRevealed()){
                    if(!CL.getBomb()){
                        winflag=false;
                    }
                }
                if(CL.isRevealed()){
                    if(CL.getBomb()){
                        winflag=false;
                        this.gameState="Lost";
                    }
                }
            }
        }

        if(winflag){
            System.out.println("We got here?");
            this.gameState="Won";
        }
    }

    public void debugreveal(){
        for(ArrayList<GridItem> RL:this.gridmap){
            for(GridItem CL:RL){
                CL.setRevealed();
            }
        }
    }
    public void debugconceal(){
        for(ArrayList<GridItem> RL:this.gridmap){
            for(GridItem CL:RL){
                CL.setConcealed();
            }
        }
    }
    // NEEDS TO BE IMPLEMENTED AND CHECKED
    public boolean sanitychecker(){
        boolean sancheck = false;
        int bombcount =0;
        for(ArrayList<GridItem> RL:this.gridmap){
            for(GridItem CL:RL){
                if(CL.getBomb()){
                    sancheck=true;
                    bombcount=bombcount+1;
                }
            }
        }
        if(!sancheck){
            System.out.println("GAME HAS NO BOMBS");
            return false;
        }else{
            if(bombcount<((this.getRows()*this.getColumns())/15)){
                //System.out.println("There is not enough bombs, restarting: "+bombcount+"/"+((this.getRows()*this.getColumns())/15)+" bombs");
                this.gridmap.clear();
                return false;
            }else{
                //System.out.println("There is enough bombs: "+bombcount+"/"+((this.getRows()*this.getColumns())/15)+" bombs");
                return true;
            }
        }
    }
    public static void main(String[] args) {
        Gridtiles Gameenv = new Gridtiles();
        boolean sanity=false;
        while(!sanity){
            Gameenv.initialisemap();
            Gameenv.initialiseStatus();
            sanity = Gameenv.sanitychecker();
        }
        Gameenv.debugreveal();
        Gameenv.DisplayMap();
        Gameenv.debugconceal();
        Gameenv.DisplayMap();
        Gameenv.gameRun();

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
    implement hidden / revealed //// DONE
    implement checking algo to reveal nearby zeroes //// DONE
    implement user interaction //// DONE
    implement game over state //// DONE
    implement game win state //// DONE
    implement flag placer //// Done
    implement use of sanity checker to regen map if no bombs. //// DONE
    implement change to sanity checker to also regen map if minimum number of bombs not implemented. //// DONE
    implement catches for stupid user input /// done?
    implement for custom sized maps
 */

}
