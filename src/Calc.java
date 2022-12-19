import java.util.*;

public class Calc {
    public static String[] calc(String inputString) {
        String[] calc_inputString = inputString.split(" ");
        if (calc_inputString.length != 3) {
            Scanner inputString_a_value_again = new Scanner(System.in);
            System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");
            inputString = inputString_a_value_again.nextLine();
            return calc(inputString);
        } else {
            return calc_inputString;
        }
    }
    public static void main(String[] args)   {
        int rez = 0, value1 = 0 , value2 = 0;// обьявляем переменные для вычислений
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение в формате \"а + b\". Числа должных быть римскими или арабскими, целыми от 1 до 10 : ");
        String sum = in.nextLine(); // записываем выражение в переменную sum
        //создаём массив с римскими цифрами от 1 до 10
        String[] rimValues10 = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        //создаём массив с римскими цифрами от 10 до 100
        String[] rimValues100 = new String[]{"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        String words[] = calc(sum);// разделяем выражение в массив words на значения и арфметический знак
        // проверка есть ли введённые значения  в массиве римских цифр
        List rimChar = new ArrayList<>(Arrays.asList(rimValues10));
        boolean rim = (rimChar.contains(words[2]) & rimChar.contains(words[0]));
        if (rim) {//если ввведены римские цифры
            value1 = rimChar.indexOf(words[0]) + 1; //пер евод из римских в арабские
            value2 = rimChar.indexOf(words[2]) + 1;
        } else {//если арабские цифры
            try {
                value1 = Integer.parseInt(words[0].trim()); //переводим из формата текста в фомат числа
                value2 = Integer.parseInt(words[2].trim()); //переводим из формата текста в фомат числа
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных введенных операндов");
                return;
                }
        }
            //Условия для вводимых данных
        boolean a = (value1 <= 10);
        boolean b = (value1 >= 1);
        boolean c = (value2 <= 10);
        boolean d = (value2 >= 1);
        if (a&b&c&d==false) {// если условия не выполняются
            System.out.println("Неверный диапазон данных введенных операндов. Числа должны быть больше 1 и меньше или равны 10.");
            return;
        }

            switch (words[1]) {// определяем тип аррифметической операции
                case "+" -> rez = value1 + value2;
                case "-" -> rez = value1 - value2;
                case "/" -> rez = value1 / value2;
                case "*" -> rez = value1 * value2;
                default -> {// если оператор не определился, выходим
                    System.out.println("Некорректный оператор");
                    return;
                }
            }
        try {
            if (rim & rez % 10 > 0 & rez > 10) {//если результат - римские цифры больше 10 не кратные 10
                System.out.println("Ваше выражение равно: " + rimValues100[(rez / 10 - 1)] + rimValues10[(rez % 10 - 1)]);
            } else if (rim & (rez % 10 == 0) & rez > 10) {//если результат - римские цифры кратные 10
                System.out.println("Ваше выражение равно: " + rimValues100[(rez / 10 - 1)]);
            } else if (rim & rez <= 10) {//если результат - римская цифра от 1 до 10
                System.out.println("Ваше выражение равно: " + rimValues10[(rez - 1)]);
            } else {// если результат - арабское число
                System.out.println("Ваше выражение равно: " + rez);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Некорректные данные (получен отрицательный результат в римской системе исчисления)");
            return;
        }

    }
}