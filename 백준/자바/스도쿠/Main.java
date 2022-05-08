package 백준.자바.스도쿠;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int col, row;

    public Point(int col, int row) {
        this.col = col;
        this.row = row;
    }
}

public class Main {

    static int[][] board = new int[9][9];
    static Queue<Point> blankQ = new LinkedList<>();


    public static void main(String[] args) throws IOException {

        Main T = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int col = 0; col < 9; col++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int row = 0; row < 9; row++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 0) {
                    blankQ.add(new Point(col, row));
                }
                board[col][row] = value;
            }
        }

        T.dfs();

        br.close();
    }

    public void dfs() throws IOException {
        if (blankQ.isEmpty()) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for (int[] cols : board) {
                for (int value : cols) {
                    bw.write(value + " ");
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();

            System.exit(0);
        }

        Point polledPoint = blankQ.poll();

        for (int value = 1; value <= 9; value++) {
            if (isValueForPointOk(polledPoint, value)) {
                board[polledPoint.col][polledPoint.row] = value;
                dfs();
                board[polledPoint.col][polledPoint.row] = 0;
                blankQ.add(polledPoint);
            }
        }
        blankQ.add(polledPoint);
    }

    public boolean isValueForPointOk(Point point, int value) {
        if (!isValueForColExists(point, value)
                && !isValueForRowExists(point, value)
                && !isValueForSquareZoneExists(point, value))
            return true;
        return false;
    }

    public boolean isValueForColExists(Point point, int value) {
        for (int col = 0; col < 9; col++) {
            if (board[col][point.row] == value) return true;
        }
        return false;
    }

    public boolean isValueForRowExists(Point point, int value) {
        for (int row = 0; row < 9; row++) {
            if (board[point.col][row] == value) return true;
        }
        return false;
    }

    public boolean isValueForSquareZoneExists(Point point, int value) {
        int colLocation = (int) ((Math.floor((point.col) / 3)) * 3);
        int rowLocation = (int) ((Math.floor((point.row) / 3)) * 3);
        for (int col = colLocation; col < colLocation + 3; col++) {
            for (int row = rowLocation; row < rowLocation + 3; row++) {
                if (board[col][row] == value) return true;
            }
        }
        return false;
    }

}
