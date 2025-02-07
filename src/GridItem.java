public class GridItem {
    private int status=0;
    private boolean revealed = false;
    private boolean isBomb = false;

    public int getStatus(){
        if(revealed){
            return this.status;
        }
        else{
            return 10;
        }
    }
    public GridItem(float decision){
        if(decision<10){
            isBomb=true;
        }
    }
}
