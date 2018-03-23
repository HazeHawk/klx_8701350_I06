package sample;

public class Panel {

    private int x;
    private int y;
    private boolean black;
    private Group group;
    private Board board;
    private boolean blocked;

    public Panel(int y, int x, Board board) {
        this.y = y;
        this.x = x;
        this.board = board;
        this.black = false;
        this.blocked = false;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean setBlack(boolean black) {

        if(black && group.full() || this.blocked)
            return false;
        else {
            this.black = black;
            return true;
        }
    }

    public boolean isBlack() {
        return black;
    }
}
