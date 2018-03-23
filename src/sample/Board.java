package sample;

import java.util.ArrayList;

public class Board {

    private BoardController boardController;
    private Panel[][] boardPanels = new Panel[10][10];
    private ArrayList<Panel> newBlockedPanels;
    private int x,y;
    private boolean changeY;

    public Board(BoardController boardController){

        this.boardController = boardController;
        newBlockedPanels = new ArrayList<>();

        for (int y=0; y<10; y++)
        {
            for (int x=0; x<10; x++)
            {
                boardPanels[y][x] = new Panel(y,x, this);
            }
        }

        this.x = 0;
        this.y = 0;
        changeY = false;
    }

    public void panelCycle()
    {
        if (boardPanels[this.y][this.x].getGroup().isHasValue()) {
            if (boardPanels[this.y][this.x].isBlack()) {
                if(!boardPanels[this.y][this.x].getGroup().makePanelWhite(boardPanels[this.y][this.x])){
                    incYX();
                    panelCycle();
                }
            }
            else {
                if(!boardPanels[this.y][this.x].getGroup().makePanelBlack(boardPanels[this.y][this.x])){
                    incYX();
                    panelCycle();
                }
            }
        }
        incYX();
    }

    private void incYX(){
        if (this.x < 8) this.x++;
        else {
            this.x=0;
            changeY = true;
        }
        if (changeY){
            this.y++;
            changeY = false;
        }
        if (y>8) {
            this.y = 0;
            this.x = 0;
        }
    }

    public Panel getBoardPanel(int y, int x) {
        return boardPanels[y][x];
    }

    public boolean boardOnlyHasRectangles()
    {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 9; x++) {
                if (boardPanels[y][x].isBlack()) {
                    int count = 0;
                    if (boardPanels[y][x + 1].isBlack()) count++;
                    if (boardPanels[y + 1][x].isBlack()) count++;
                    if (boardPanels[y + 1][x + 1].isBlack()) count++;
                    if (count == 2) {
                        if (boardPanels[y][x + 1].getGroup().isHasValue() && !boardPanels[y][x + 1].isBlocked() && !boardPanels[y][x + 1].isBlack()) {
                            if (boardPanels[y][x + 1].getGroup().makePanelBlack(boardPanels[y][x + 1])) {
                                boardPanels[y][x + 1].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y][x + 1]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (boardPanels[y + 1][x].getGroup().isHasValue() && !boardPanels[y + 1][x].isBlocked() && !boardPanels[y + 1][x].isBlack()) {
                            if (boardPanels[y + 1][x].getGroup().makePanelBlack(boardPanels[y + 1][x])) {
                                boardPanels[y + 1][x].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y + 1][x]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (boardPanels[y + 1][x + 1].getGroup().isHasValue() && !boardPanels[y + 1][x + 1].isBlocked() && !boardPanels[y + 1][x].isBlack()) {
                            if (boardPanels[y + 1][x + 1].getGroup().makePanelBlack(boardPanels[y + 1][x + 1])) {
                                boardPanels[y + 1][x + 1].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y + 1][x + 1]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (!boardPanels[y][x + 1].getGroup().isHasValue() && !boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(true);
                            y = 0;
                            x = 0;
                        } else if (!boardPanels[y + 1][x].getGroup().isHasValue() && !boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(true);
                            y = 0;
                            x = 0;
                        } else if (!boardPanels[y + 1][x + 1].getGroup().isHasValue() && !boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(true);
                            y = 0;
                            x = 0;
                        } else return returnFalse();
                    } else {
                        //System.out.println("y = " + y + " x = " + x);
                        if (!newBlockedPanels.contains(boardPanels[y][x])){
                            boardPanels[y][x].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y][x]);
                        }
                       if (!newBlockedPanels.contains(boardPanels[y][x + 1])){
                           boardPanels[y][x + 1].setBlocked(true);
                           newBlockedPanels.add(boardPanels[y][x + 1]);
                       }
                        if (!newBlockedPanels.contains( boardPanels[y + 1][x])){
                            boardPanels[y + 1][x].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y + 1][x]);
                        }
                        if (newBlockedPanels.contains(boardPanels[y + 1][x + 1])){
                            boardPanels[y + 1][x + 1].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y + 1][x + 1]);
                        }
                    }
                }
                else {
                    int count = 0;
                    if (boardPanels[y][x + 1].isBlack()) count++;
                    if (boardPanels[y + 1][x].isBlack()) count++;
                    if (boardPanels[y + 1][x + 1].isBlack()) count++;
                    if (count == 3) {
                        if (boardPanels[y][x + 1].getGroup().isHasValue() && !boardPanels[y][x + 1].isBlocked() && boardPanels[y][x + 1].isBlack()) {
                            if (boardPanels[y][x + 1].getGroup().makePanelWhite(boardPanels[y][x + 1])) {
                                boardPanels[y][x + 1].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y][x + 1]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (boardPanels[y + 1][x].getGroup().isHasValue() && !boardPanels[y + 1][x].isBlocked() && boardPanels[y + 1][x].isBlack()) {
                            if (boardPanels[y + 1][x].getGroup().makePanelWhite(boardPanels[y + 1][x])) {
                                boardPanels[y + 1][x].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y + 1][x]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (boardPanels[y + 1][x + 1].getGroup().isHasValue() && !boardPanels[y + 1][x + 1].isBlocked() && boardPanels[y + 1][x].isBlack()) {
                            if (boardPanels[y + 1][x + 1].getGroup().makePanelWhite(boardPanels[y + 1][x + 1])) {
                                boardPanels[y + 1][x + 1].setBlocked(true);
                                newBlockedPanels.add(boardPanels[y + 1][x + 1]);
                                y = 0;
                                x = 0;
                            } else return returnFalse();
                        } else if (!boardPanels[y][x + 1].getGroup().isHasValue() && boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(false);
                            y = 0;
                            x = 0;
                        } else if (!boardPanels[y + 1][x].getGroup().isHasValue() && boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(false);
                            y = 0;
                            x = 0;
                        } else if (!boardPanels[y + 1][x + 1].getGroup().isHasValue() && boardPanels[y][x + 1].isBlack()) {
                            boardPanels[y][x + 1].setBlack(false);
                            y = 0;
                            x = 0;
                        } else return returnFalse();
                    } else {
                        //System.out.println("y = " + y + " x = " + x);
                        if (!newBlockedPanels.contains(boardPanels[y][x])){
                            boardPanels[y][x].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y][x]);
                        }
                        if (!newBlockedPanels.contains(boardPanels[y][x + 1])){
                            boardPanels[y][x + 1].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y][x + 1]);
                        }
                        if (!newBlockedPanels.contains( boardPanels[y + 1][x])){
                            boardPanels[y + 1][x].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y + 1][x]);
                        }
                        if (newBlockedPanels.contains(boardPanels[y + 1][x + 1])){
                            boardPanels[y + 1][x + 1].setBlocked(true);
                            newBlockedPanels.add(boardPanels[y + 1][x + 1]);
                        }
                    }
                }
            } //for x
        } //for y
        return true;
        }

        private void unBlock()
        {
            for (Panel panel : newBlockedPanels){
                panel.setBlocked(false);
            }
        }

        private boolean returnFalse()
        {
            unBlock();
            return false;
        }
}
