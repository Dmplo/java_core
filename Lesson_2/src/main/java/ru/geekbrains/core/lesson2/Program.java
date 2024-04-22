package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {


    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }

    }

    /**
     * Инициализация объектов игры
     */
    static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print("-" + (x + 1));
        }
        System.out.println("-");


        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y\n(от 1 до 3) через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }


    /**
     * Проверка, является ли ячейка игрового поля пустой
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Ход игрока (компьютера)
     * В методе реализована проверка на выигрышные комбинации компьютера и игрока. Приоритет отдан выигрышному ходу компьютера,
     * если такого хода нет, но есть шанс помешать игроку сделать выигрышный ход то будут выбраны именно эти координаты. Если нет
     * выигрышных комбинаций, ход будет сделан на пустое поле.
     */
    static void aiTurn() {
        int winHumanCoordX = -1;
        int winHumanCoordY = -1;
        int winAiCoordX = -1;
        int winAiCoordY = -1;

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) {
                    field[x][y] = DOT_AI;
                    if (checkWinV2(DOT_AI, WIN_COUNT)) {
                        winAiCoordX = x;
                        winAiCoordY = y;
                    } else {
                        field[x][y] = DOT_HUMAN;
                        if (checkWinV2(DOT_HUMAN, WIN_COUNT)) {
                            winHumanCoordX = x;
                            winHumanCoordY = y;
                        }
                    }
                    field[x][y] = DOT_EMPTY;
                }
            }
        }
        if (winAiCoordX >= 0) {
            field[winAiCoordX][winAiCoordY] = DOT_AI;
        } else if (winHumanCoordX >= 0) {
            field[winHumanCoordX][winHumanCoordY] = DOT_AI;
        } else {
            randomAiTurn();
        }
     }


    static void randomAiTurn(){
        int x;
        int y;
        do{
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }


    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    static boolean checkWinV2(char dot, int win) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (check1(x, y, dot, win) || check2(x, y, dot, win) || check3(x, y, dot, win) || check4(x, y, dot, win)) {
                    return true;
                }
            }
        }
        return false;
    }


    static boolean check1(int x, int y, char dot, int win) {
        for (int i = 0; i < win; i++) {
            if (!checkByDot(x, y + i, dot)) {
                return false;
            }
        }
        return true;
    }

    static boolean check2(int x, int y, char dot, int win) {
        for (int i = 0; i < win; i++) {
            if (!checkByDot(x + i, y, dot)) {
                return false;
            }
        }
        return true;
    }

    static boolean check3(int x, int y, char dot, int win) {
        for (int i = 0; i < win; i++) {
            if (!checkByDot(x + i, y + i, dot)) {
                return false;
            }
        }
        return true;
    }

    static boolean check4(int x, int y, char dot, int win) {
        for (int i = 0; i < win; i++) {
            if (!checkByDot(x - i, y + i, dot)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkByDot(int x, int y, char dot) {
        if (isCellValid(x, y)) {
            return field[x][y] == dot;
        } else {
            return false;
        }
    }


    /**
     * Проверка состояния игры
     *
     * @param dot фишка игрока
     * @param s   победный слоган
     * @return
     */
    static boolean checkState(char dot, String s) {
//        if (checkWin(dot)) {
        if (checkWinV2(dot, WIN_COUNT)) {
            System.out.println(s);
            return true;
        } else if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

}
