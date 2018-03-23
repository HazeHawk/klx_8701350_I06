package sample;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class BoardController {

    private Board board;
    private double displayCounter;
    private double displayCounterForDisplaying;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Group> changableWeightedGroups = new ArrayList<>();

    public BoardController()
    {
        buildBoard();
        displayCounter = 0;
        displayCounterForDisplaying = 10000;
    }

    public void solve(){
        display();

        for (Group group : groups) {
            group.fillIfNoOtherOption();
            group.blockIfValue0();
        }

        display();

        firstFillWeightedGroups();

        display();

        while (!board.boardOnlyHasRectangles())
        {
            board.panelCycle();
            display();
        }

        display();
        System.out.println("Finished");
    }

    private void firstFillWeightedGroups() {
        for (Group group : groups){
            if (group.isHasValue() && !group.full()){
                changableWeightedGroups.add(group);
            }
        }

        for (Group group : changableWeightedGroups){
            group.fillAllPossible();
        }
    }

    public void display()
    {
        displayCounter++;
        displayCounterForDisplaying++;
        if (displayCounterForDisplaying == 10000) {
            displayCounterForDisplaying = 0;
            System.out.println("____________ No. " + displayCounter);
            for (int y = 0; y < 10; y++) {
                System.out.print(y + ":");
                for (int x = 0; x < 10; x++) {
                    if (board.getBoardPanel(y, x).isBlack()) {
                        System.out.print("B");
                    } else {
                        System.out.print("W");
                    }
                }
                System.out.println();
            }
            System.out.println("____________");
            System.out.println();
        }
    }

    private void buildBoard() {
        board = new Board(this);

        //Initialisierung der Gruppen
        ArrayList<Panel> groupPanels = new ArrayList<>();

        groupPanels.add(board.getBoardPanel(0,0));
        groups.add(new Group(false,0, groupPanels));

        groupPanels = new ArrayList<>();
        groupPanels.add(board.getBoardPanel(0,1));
        groupPanels.add(board.getBoardPanel(0,2));
        groupPanels.add(board.getBoardPanel(0,3));
        groups.add(new Group(true, 1, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(0,4));
        groupPanels.add(board.getBoardPanel(0,5));
        groupPanels.add(board.getBoardPanel(0,6));
        groupPanels.add(board.getBoardPanel(1,4));
        groupPanels.add(board.getBoardPanel(1,5));
        groups.add(new Group(true, 3, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(0,7));
        groupPanels.add(board.getBoardPanel(1,7));
        groupPanels.add(board.getBoardPanel(2,7));
        groupPanels.add(board.getBoardPanel(3,7));
        groupPanels.add(board.getBoardPanel(4,5));
        groupPanels.add(board.getBoardPanel(4,6));
        groupPanels.add(board.getBoardPanel(4,7));
        groupPanels.add(board.getBoardPanel(5,7));
        groups.add(new Group(true, 4, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(0,8));
        groupPanels.add(board.getBoardPanel(0,9));
        groupPanels.add(board.getBoardPanel(1,8));
        groupPanels.add(board.getBoardPanel(2,8));
        groupPanels.add(board.getBoardPanel(3,8));
        groupPanels.add(board.getBoardPanel(4,8));
        groupPanels.add(board.getBoardPanel(4,9));
        groups.add(new Group(true, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(1,9));
        groupPanels.add(board.getBoardPanel(2,9));
        groupPanels.add(board.getBoardPanel(3,9));
        groups.add(new Group(true, 3, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(1,0));
        groupPanels.add(board.getBoardPanel(1,1));
        groupPanels.add(board.getBoardPanel(2,0));
        groups.add(new Group(true, 2, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(1,2));
        groupPanels.add(board.getBoardPanel(1,3));
        groupPanels.add(board.getBoardPanel(2,3));
        groupPanels.add(board.getBoardPanel(2,4));
        groupPanels.add(board.getBoardPanel(2,5));
        groupPanels.add(board.getBoardPanel(3,2));
        groupPanels.add(board.getBoardPanel(3,3));
        groupPanels.add(board.getBoardPanel(3,4));
        groupPanels.add(board.getBoardPanel(3,5));
        groupPanels.add(board.getBoardPanel(3,6));
        groupPanels.add(board.getBoardPanel(4,4));
        groups.add(new Group(true, 8, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(1,6));
        groupPanels.add(board.getBoardPanel(2,6));
        groups.add(new Group(true, 2, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(2,1));
        groupPanels.add(board.getBoardPanel(2,2));
        groupPanels.add(board.getBoardPanel(3,0));
        groupPanels.add(board.getBoardPanel(3,1));
        groups.add(new Group(true, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(4,0));
        groupPanels.add(board.getBoardPanel(5,0));
        groups.add(new Group(true, 2, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(4,1));
        groupPanels.add(board.getBoardPanel(4,2));
        groupPanels.add(board.getBoardPanel(4,3));
        groupPanels.add(board.getBoardPanel(5,1));
        groupPanels.add(board.getBoardPanel(5,3));
        groupPanels.add(board.getBoardPanel(5,4));
        groupPanels.add(board.getBoardPanel(5,5));
        groupPanels.add(board.getBoardPanel(6,1));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(5,2));
        groupPanels.add(board.getBoardPanel(6,2));
        groups.add(new Group(true, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(5,6));
        groupPanels.add(board.getBoardPanel(6,5));
        groupPanels.add(board.getBoardPanel(6,6));
        groupPanels.add(board.getBoardPanel(7,5));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(5,8));
        groupPanels.add(board.getBoardPanel(6,7));
        groupPanels.add(board.getBoardPanel(6,8));
        groupPanels.add(board.getBoardPanel(7,8));
        groups.add(new Group(true, 3, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(5,9));
        groupPanels.add(board.getBoardPanel(6,9));
        groupPanels.add(board.getBoardPanel(7,9));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(6,0));
        groupPanels.add(board.getBoardPanel(7,0));
        groupPanels.add(board.getBoardPanel(8,0));
        groups.add(new Group(true, 3, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(6,3));
        groupPanels.add(board.getBoardPanel(7,1));
        groupPanels.add(board.getBoardPanel(7,2));
        groupPanels.add(board.getBoardPanel(7,3));
        groupPanels.add(board.getBoardPanel(8,1));
        groupPanels.add(board.getBoardPanel(8,2));
        groupPanels.add(board.getBoardPanel(8,3));
        groupPanels.add(board.getBoardPanel(9,1));
        groups.add(new Group(true, 4, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(6,4));
        groupPanels.add(board.getBoardPanel(7,4));
        groups.add(new Group(true, 2, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(7,6));
        groupPanels.add(board.getBoardPanel(7,7));
        groupPanels.add(board.getBoardPanel(8,6));
        groupPanels.add(board.getBoardPanel(8,7));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(9,0));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(9,2));
        groupPanels.add(board.getBoardPanel(9,3));
        groups.add(new Group(false, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(8,4));
        groupPanels.add(board.getBoardPanel(8,5));
        groupPanels.add(board.getBoardPanel(9,4));
        groupPanels.add(board.getBoardPanel(9,5));
        groupPanels.add(board.getBoardPanel(9,6));
        groups.add(new Group(true, 5, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(8,8));
        groupPanels.add(board.getBoardPanel(9,7));
        groupPanels.add(board.getBoardPanel(9,8));
        groups.add(new Group(true, 0, groupPanels));

        groupPanels.clear();
        groupPanels.add(board.getBoardPanel(8,9));
        groupPanels.add(board.getBoardPanel(9,9));
        groups.add(new Group(true, 2, groupPanels));
    }

    public Board getBoard() {
        return board;
    }
}
