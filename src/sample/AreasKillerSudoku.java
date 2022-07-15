package sample;

import java.util.ArrayList;

public class AreasKillerSudoku {
    private ArrayList<MyPoint> indexes;
    private int Sum;
    private int Colour;



    public AreasKillerSudoku(ArrayList<MyPoint> ind, int sum, int colour){
        indexes = new ArrayList<>(ind) ;
        Sum=sum;
        Colour=colour;
    }

    public ArrayList<MyPoint> GetIndexes(){
        return indexes;
    }

    public int GetSum(){
        return Sum;
    }

    public int GetColour(){
        return Colour;
    }
}

