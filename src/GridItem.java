public class GridItem {
    private int status=0;
    private boolean revealed = true;
    private boolean isBomb = false;

    public int getStatus(){
        if(this.isBomb){
            return 99;
        } else if(this.revealed){
            return this.status;
        }
        else{
            return 10;
        }
    }
    public void updateStatus(){
        this.status = this.status+1;
    }
    public boolean getBomb(){
        return this.isBomb;
    }
    public GridItem(float decision){
        //System.out.println(decision);
        if(decision==1){
            isBomb=true;
        }
    }
}
