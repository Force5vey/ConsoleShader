import java.io.*;
import java.util.Scanner;

public class ConsoleShader
{

    private static final String DEFAULT_CHAR_FILE = "chars.txt";
    private static int gridWidth = 40;
    private static int gridHeight = 40;
    private static char[] charSet =
    { ' ', '.', '-', '+', '=', '%', '#', '@', '█' };

    public static void main(String[] args)
    {
        loadCharacterSet(DEFAULT_CHAR_FILE);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            System.out.println("\nGradient Shader Menu:");
            System.out.println("1. Generate and Display Gradient");
            System.out.println("2. Set Grid Size (Current: " + gridWidth + " x " + gridHeight + ")");
            System.out.println("3. Customize Character Set");
            System.out.println("4. Save Character Set to File");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = validatedIntegerInput(scanner, 1, 5, "Invalid choice. Please enter a number between 1 and 5.");

            switch (choice) {
            case 1 -> generateGradient(scanner);
            case 2 -> setGridSize(scanner);
            case 3 -> customizeCharacterSet(scanner);
            case 4 -> saveCharacterSet(DEFAULT_CHAR_FILE);
            case 5 -> running = false;
            }
        }

        scanner.close();

    }

    private static void loadCharacterSet(String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            charSet = br.lines().filter(line -> !line.isEmpty()).map(line -> line.charAt(0))
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().toCharArray();

            System.out.println("Character set loaded from file.");
        } catch (IOException e)
        {
            System.out.println("Default character set will be used. No custom file found.");
        }
    }

    private static void saveCharacterSet(String fileName)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for (char c : charSet)
            {
                bw.write(c);
                bw.newLine();
            }
            System.out.println("Character set saved to " + fileName);
        } catch (IOException e)
        {
            System.out.println("Failed to save character set: " + e.getMessage());
        }
    }

    private static void setGridSize(Scanner scanner)
    {
        System.out.print("Enter grid width (10-100): ");
        gridWidth = validatedIntegerInput(scanner, 10, 100, "Invalid width. Please enter a number between 10 and 100.");

        System.out.print("Enter grid height (10-100): ");
        gridHeight = validatedIntegerInput(scanner, 10, 100,
                "Invalid height. Please enter a number between 10 and 100.");
    }

    private static void customizeCharacterSet(Scanner scanner)
    {
        System.out.println("Current Character Set: " + new String(charSet));
        System.out.println("Enter new characters in order from dark to light (one per line, enter 'done' to finish):");

        char[] newCharSet = new char[100];
        int count = 0;

        scanner.nextLine(); // Consume leftover newline
        while (true)
        {
            if (count >= newCharSet.length)
            {
                System.out.println("Maximum character set size reached.");
                break;
            }

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done"))
                break;

            if (input.length() == 1)
            {
                newCharSet[count++] = input.charAt(0);
            } else
            {
                System.out.println("Please enter only a single character per line.");
            }
        }

        charSet = new char[count];
        System.arraycopy(newCharSet, 0, charSet, 0, count);
        System.out.println("Updated Character Set: " + new String(charSet));
    }

    private static void generateGradient(Scanner scanner)
    {
        System.out.print("Enter brightness (0-255) for the left side: ");
        int leftStart = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        System.out.print("Enter brightness (0-255) for the middle column: ");
        int midHoriz = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        System.out.print("Enter brightness (0-255) for the right side: ");
        int rightEnd = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        System.out.print("Enter brightness (0-255) for the top side: ");
        int topStart = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        System.out.print("Enter brightness (0-255) for the middle row: ");
        int midVert = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        System.out.print("Enter brightness (0-255) for the bottom side: ");
        int bottomEnd = validatedIntegerInput(scanner, 0, 255,
                "Invalid input. Enter a brightness value between 0 and 255.");

        int[][] grid = new int[gridHeight][gridWidth];

        for (int row = 0; row < gridHeight; row++)
        {
            for (int col = 0; col < gridWidth; col++)
            {
                double horizontal = interpolate(leftStart, midHoriz, rightEnd, col, gridWidth);
                double vertical = interpolate(topStart, midVert, bottomEnd, row, gridHeight);
                grid[row][col] = (int) ((horizontal + vertical) / 2);
            }
        }

        for (int[] row : grid)
        {
            for (int brightness : row)
            {
                System.out.print(mapToCharacter(brightness));
            }
            System.out.println();
        }
    }

    private static double interpolate(int start, int mid, int end, int pos, int total)
    {
        if (pos < total / 2)
        {
            return start + (mid - start) * (double) pos / (total / 2);
        } else
        {
            return mid + (end - mid) * (double) (pos - total / 2) / (total / 2);
        }
    }

    private static char mapToCharacter(int brightness)
    {
        int index = (int) ((double) brightness / 256 * charSet.length);
        return charSet[Math.min(index, charSet.length - 1)];
    }

    private static int validatedIntegerInput(Scanner scanner, int min, int max, String errorMessage)
    {
        while (true)
        {
            if (scanner.hasNextInt())
            {
                int value = scanner.nextInt();
                if (value >= min && value <= max)
                {
                    return value;
                }
            }
            System.out.println(errorMessage);
            scanner.nextLine();
        }
    }

}
