//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;

public class Gridtiles {
    private static final int rows = 15;
    private static final int columns = 15;
    private ArrayList<ArrayList<String>> gridmap = new ArrayList<ArrayList<String>>();



    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
    public void initialisemap(){
        for(int RC=0;RC<this.rows;RC++){
            ArrayList<String> temp = new ArrayList<String>();
            for(int CC=0;CC<this.rows;CC++){
                if(RC==0){
                    temp.add(String.valueOf(CC));
                }
                else{
                    if(CC==0) {
                        temp.add(String.valueOf(RC));
                    } else{
                        temp.add("X");
                    }
                }
            }
        this.gridmap.add(temp);

        }

    }
    public void DisplayMap(){
        for(ArrayList<String> RL:this.gridmap){
            for(String CL:RL){
                System.out.print(CL);
                switch(CL.length()){
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
    }



    public static void main(String[] args) {
        Gridtiles Gameenv = new Gridtiles();
        Gameenv.initialisemap();
        Gameenv.DisplayMap();
    }


}
