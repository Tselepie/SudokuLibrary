package sample;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {

    //An array that holds the numbers that will be used in the Classical Sudoku game mode
    private int[] sudokuNumbers = new int[81];
    private ArrayList<AreasKillerSudoku> areas=new ArrayList<>();

    /*
    This method crates a new txt file in the Users folder and names it with the username that was
    given in the SIGN UP stage and stores inside the password that was given there as well
     */
    public void createFile(String username, String password) {
        Path path = Paths.get("Users").toAbsolutePath();
        File file = new File(path.toString(), username + ".txt");

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(password);
            fw.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    /*
    This method searches the txt files inside the Users folder
    It is used when the users is trying to login
    It searches if the username given exists as a txt file AND the password inside is the
    same as the one given
     */
    public boolean searchInFile(String username, String password) {
        Path path = Paths.get("Users").toAbsolutePath();
        File dir = new File(path.toString());
        BufferedReader in;
        String line;

        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\\\s]*");
        Matcher matcher1 = pattern.matcher(username);
        Matcher matcher2 = pattern.matcher(password);
        if(!matcher1.matches() || !matcher2.matches())
            return false;
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.getName().equals(username + ".txt")) {
                try {
                    in = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    line = in.readLine();
                    if (line.equals(password))
                        return true;
                    return false;
                } catch (Exception e2) {
                    System.out.println(e2);
                    return false;
                }
            }
        }
        return false;
    }

    /*
    This method searches the txt file holding the numbers of the Classical Sudoku game
    of the stage(one through ten) that was pressed in the STAGE SELECT stage
     */
    public void classicSudokuFile(String mapName) {
        try {
            Path path = Paths.get("SudokuMaps").toAbsolutePath();
            File dir = new File(path.toString());
            BufferedReader in;
            String line;
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                try {
                    in = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    if (f.getName().equals(mapName + ".txt")) {
                        int counter = 0;
                        while (counter < 81) {
                            line = in.readLine();
                            sudokuNumbers[counter] = Integer.parseInt(line, 10);
                            counter++;
                        }
                    }
                }
                catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        }
        catch (Exception e2) {
            System.out.println(e2);
        }
    }

    //Returns the number in the i position in the sudokuNumbers array
    public int getNumbers(int i){
        return sudokuNumbers[i];
    }




    public void KillerSudokuFile(String filename) {
        try {

            Path path = Paths.get("KillerSudokuMaps").toAbsolutePath();
            File dir = new File(path.toString());

            for (File f : Objects.requireNonNull(dir.listFiles())) {
                try {
                    if(f.getName().equals(filename + ".txt")) {
                        Scanner scanner = new Scanner(f);
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine(); //reads line
                            // format of line  Colour|Sum|row1 column1,row2 column2,...
                            String[] arrayOfLine = line.split("-", 5); //splits line in 3 parts by "-"
                            int colours = Integer.parseInt(arrayOfLine[0]); //takes first integer
                            int sum = Integer.parseInt(arrayOfLine[1]); //takes second integer
                            String rest = arrayOfLine[2]; //stores the cells of area:  row1 column1,row2 column2,...
                            String[] arrayOfIndexes = rest.split(",", 8); //["row1 column1","row2 column2",...
                            ArrayList<MyPoint> temp = new ArrayList<>();  //Creates a list of Point to store the cells

                            for (String arr : arrayOfIndexes) {
                                String[] indexes = arr.split(" ", 8);
                                MyPoint a = new MyPoint(Integer.parseInt(indexes[0]), Integer.parseInt(indexes[1]));
                                temp.add(a);
                            }
                            AreasKillerSudoku area = new AreasKillerSudoku(temp, sum, colours);
                            areas.add(area);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<AreasKillerSudoku> getAreas(){
        return areas;
    }
}
