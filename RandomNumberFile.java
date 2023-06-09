import java.io.*;

public class RandomNumberFile {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Syntax: java RandomNumberFile <min_value> <max_value> <number_of_values>");
            return;
        }

        String fileName = getInput("Enter a file name: ");
        int min = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        int count = Integer.parseInt(args[2]);

        try {
            File file = new File(fileName);
            boolean fileExists = file.exists();

            // Перевірка існування файлу
            if (!fileExists) {
                if (file.createNewFile()) {
                    System.out.println("File " + fileName + " create.");
                } else {
                    System.out.println("Не вдалося створити файл " + fileName);
                    return;
                }
            }

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Запис послідовності випадкових чисел у файл
            for (int i = 0; i < count; i++) {
                int randomNumber = generateRandomNumber(min, max);
                bufferedWriter.write(Integer.toString(randomNumber));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("All ok " + fileName);
        } catch (IOException e) {
            System.out.println("Сталася помилка під час запису в файл: " + e.getMessage());
        }

        // Читання та виведення інформації з файлу
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("The contents of the file " + fileName + ":");

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Сталася помилка під час читання файлу: " + e.getMessage());
        }
    }

    private static int generateRandomNumber(int min, int max) {
        return new java.util.Random().nextInt(max - min + 1) + min;
    }

    private static String getInput(String message) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Помилка введення: " + e.getMessage());
            return null;
        }
    }
}