package ru.vsu.cs.ivanov;

import java.util.Locale;
import java.util.Scanner;

class Program {
    public static double enterPointCoordinate(char coordinateName) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %S: ", coordinateName);
        return scanner.nextDouble();
    }

    public static void printColorForPoint(double x, double y) {
        Locale.setDefault(Locale.ROOT);
        SimpleColor color = Program.getColor(x, y);
        System.out.printf("(%.1f, %.1f) -> %S%n", x, y, color);
    }

    enum SimpleColor {
        BLUE, GREEN, WHITE, ORANGE, GRAY, YELLOW
    }

    private static Square square = new Square (-2, 1, 8);
    private static HorizontalParabola parabola = new HorizontalParabola (4,6,-1);
    private static Circle circleBig = new Circle (-4,5,5);
    private static Circle circleSmall = new Circle (3,4,2);

    private static SimpleColor getColor(double x, double y) {
        if ((parabola.isPointInside(x, y) && square.isPointInside(x, y) && circleBig.isPointInside(x, y))
                || (parabola.isPointInside(x, y) && !square.isPointInside(x, y) && !circleBig.isPointInside(x, y))) {
            return SimpleColor.GREEN;
        }

        if (parabola.isPointInside(x, y) && !circleBig.isPointInside(x, y) && !circleSmall.isPointInside(x, y)) {
            return SimpleColor.BLUE;
        }

        if ((circleBig.isPointInside(x, y) && square.isPointInside(x, y) && !parabola.isPointInside(x, y))
                || (circleBig.isPointInside(x, y) && !parabola.isPointInside(x, y) && (circleBig.isLowerHalfCircle(y)))) {
            return SimpleColor.WHITE;
        }

        if ((square.isPointInside(x, y) && !parabola.isPointInside(x, y) && !circleBig.isPointInside(x, y)
                && !circleSmall.isPointInside(x, y)) || (circleBig.isPointInside(x, y)
                && parabola.isPointInside(x, y) && !square.isPointInside(x, y))) {
            return SimpleColor.GRAY;
        }

        if ((circleSmall.isPointInside(x, y) && !parabola.isPointInside(x, y)) || (circleBig.isPointInside(x, y)
                && !parabola.isPointInside(x, y) && !square.isPointInside(x, y) && (circleBig.isUpperHalfCircle(y)))) {
            return SimpleColor.YELLOW;
        }

        return SimpleColor.ORANGE;
    }
}

