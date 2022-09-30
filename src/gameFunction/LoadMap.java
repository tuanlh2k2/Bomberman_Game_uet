package gameFunction;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadMap implements Constant {
    public static int lever = 1;

    public String[][] mapTileNum = new String[100][100];


    int col = maxScreenColum;
    int row = maxScreenRow;

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/lever1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < row; i++) {
                String line = br.readLine();
                for (int j = 0; j < line.length(); j++) {
                    mapTileNum[i][j] = line[j];
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }
}
