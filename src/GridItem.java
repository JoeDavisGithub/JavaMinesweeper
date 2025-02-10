public class GridItem {
    private int status=0;
    private boolean revealed = false;
    private boolean isBomb = false;
    private boolean marked = false;

    public int getStatus(){
        if(this.revealed){
            if(this.isBomb){
                return 99;
            }else{
                return this.status;
            }
        }
        else{
            return 10;
        }
    }
    public int gamePeek(){
        return this.status;
    }
    public void updateStatus(){
        this.status = this.status+1;
    }
    //DEBUGTOOL
    public void updateStatus(int db){
        this.status = db;
    }
    public boolean getBomb(){
        return this.isBomb;
    }
    public void setRevealed(){
        this.revealed=true;
    }
    public boolean isRevealed(){
        return this.revealed;
    }
    public GridItem(float decision){
        //System.out.println(decision);
        if(decision<5){
            isBomb=true;
        }
    }
}
