# Gradient Shader Program

This Java console application generates and displays a gradient grid using a combination of user-defined brightness values and customizable character sets. It’s a great way to visualize the power of arrays and interpolation while creating cool text-based designs!

## Features

- Dynamic Gradient Generation
  - Define brightness levels for the left, right, top, and bottom edges.
  - Adjust the brightness in the middle for horizontal and vertical gradients.
- Customizable Character Set
  - Modify the characters used to represent brightness levels.
  - Save your custom character set to a file for reuse.
- Grid Customization
  - Set the grid width and height (10x10 to 100x100).

## How to Run

1. **Clone the Repository**:

   ```
   git clone https://github.com/Force5vey/ConsoleShader.git
   cd ConsoleShader
   ```

2. **Compile the Program**: Use the Java compiler to compile the program:

   ```
   javac ConsoleShader.java
   ```

3. **Run the Program**: Execute the program in the terminal:

   ```
   java ConsoleShader
   ```

## Menu Options

Once the program is running, use the menu to explore its functionality:

1. **Generate and Display Gradient**:
   Input brightness values to create a gradient grid.
2. **Set Grid Size**:
   Customize the dimensions of the gradient grid.
3. **Customize Character Set**:
   Modify the characters used for shading (e.g., from darkest to brightest).
4. **Save Character Set to File**:
   Save your custom character set for future runs.
5. **Exit**:
   Close the program.

## Example Output

Here’s a sample grid using the default character set (`{' ', '.', '-', '+', '=', '%', '@', '#', '█'}`):

```
 .  -  +  =  %  @  #  █
-  +  =  %  @  #  █  █
+  =  %  @  #  █  █  █
=  %  @  #  █  █  █  █
%  @  #  █  █  █  █  █
@  #  █  █  █  █  █  █
#  █  █  █  █  █  █  █
```

## Requirements

- Java 8 or later

  : Ensure you have Java installed. Check with:

  ```
  java -version
  ```

## Contributing

Feel free to fork this repository, submit pull requests, or open issues for suggestions and improvements.

## License

This project is licensed under the MIT License.