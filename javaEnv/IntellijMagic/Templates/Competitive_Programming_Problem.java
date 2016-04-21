package ${PACKAGE_NAME};

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ${NAME} {
    public static void main(String[] args) {
        // Instantiate & RUN!!!
        ${NAME} instance = new ${NAME}();
        instance.run(System.in, System.out);
    }

    public void run(InputStream inputStream, PrintStream printStream) {
        final Scanner scan = new Scanner(inputStream);
    }

    class ${NAME}Data{
            public ${NAME}Data(){}
     }
}
