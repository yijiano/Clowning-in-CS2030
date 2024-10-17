import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        Roster roster = IntStream.range(0, n)
            .mapToObj(i -> sc.nextLine())
            .reduce(new Roster("Roster"), (r, input) -> {
                Scanner inputScanner = new Scanner(input);

                String student = inputScanner.next();
                String course = inputScanner.next();
                String assessment = inputScanner.next();
                String grade = inputScanner.next();

                return r.add(student, course, assessment, grade);
            }, (r1, r2) -> r1);

        Stream.generate(() -> sc.hasNextLine() ? sc.nextLine() : "")
            .takeWhile(line -> !line.isEmpty())
            .map(query -> {
                Scanner queryScanner = new Scanner(query);

                String student = queryScanner.next();
                String course = queryScanner.next();
                String assessment = queryScanner.next();

                return roster.getGrade(student, course, assessment);
            })
            .forEach(result -> System.out.println(result));

        sc.close();
    }
}


