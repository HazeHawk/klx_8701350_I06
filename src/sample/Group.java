package sample;

import java.util.ArrayList;

public class Group {

    private ArrayList<Panel> groupPanels = new ArrayList<>();
    private int value;
    private int nextValue;
    private int nextSize;
    private boolean hasValue;
    private int size;

    public Group(boolean hasValue, int value, ArrayList<Panel> groupPanels) {
        this.hasValue = hasValue;
        this.value = value;
        this.nextValue = value;
        this.groupPanels.addAll(groupPanels);

        int count=0;
        for (Panel panel : groupPanels){
            panel.setGroup(this);
            count++;
        }
        this.size=count;
        this.nextSize = size - 1;
    }

    public boolean full(){
        if(!hasValue) return false;

        int counter = 0;
        for (Panel panel : groupPanels){
            if (panel.isBlack()) counter++;
        }
        if (counter >= value) {
            return true;
        }
        else return false;
    }

    public void fillAllPossible(){
        if (!full()) {
            for (int i=0; i<value; i++) {
                groupPanels.get(i).setBlack(true);
            }
        }
    }

    public void fillIfNoOtherOption()
    {
        if (this.hasValue) {
            int counter = 0;
            for (Panel panel : this.groupPanels) {
                counter++;
            }
            if (counter == value) {
                for (Panel panel : this.groupPanels) {
                    panel.setBlack(true);
                    panel.setBlocked(true);
                }
            }
        }
    }

    public void blockIfValue0(){
        if (this.isHasValue() && this.value == 0) {
            for (Panel panel : groupPanels){
                panel.setBlocked(true);
            }
        }
    }

    public boolean makePanelBlack(Panel pPanel){
        for (Panel panel : groupPanels) {
            if (panel.isBlack() && !panel.isBlocked() && panel != pPanel)
            {
                  panel.setBlack(false);
                  pPanel.setBlack(true);
                  return true;
            }
        }
        return false;
    }

    public boolean makePanelWhite(Panel pPanel){
        for (Panel panel : groupPanels) {
            if (!panel.isBlack() && !panel.isBlocked() && panel != pPanel)
            {
                pPanel.setBlack(false);
                panel.setBlack(true);
                return true;
            }
        }
        return false;
    }

    private void clear(){
        for (Panel panel : groupPanels){
            panel.setBlack(false);
        }
    }

    /*
    public void nextConstellation(){
        int count=0;
        for (Panel panel : groupPanels) {
            if (panel.isBlack())
            {
                count++;
            }
            if (count == this.value){
                if (groupPanels.indexOf(panel) >= nextSize)
                {
                    panel.setBlack(false);
                    groupPanels.get(nextValue-1).setBlack(false);
                    groupPanels.get(nextValue).setBlack(true);
                    groupPanels.get(nextValue+1).setBlack(true);

                }
                else {
                    panel.setBlack(false);
                    groupPanels.get(groupPanels.indexOf(panel)+1).setBlack(true);
                }
            }
        }
    } */

    public Integer getIndexOfPanel(Panel panel){
        return groupPanels.indexOf(panel);
    }

    public int getValue() {
        return value;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public int getSize()
    {
        return size;
    }
}
