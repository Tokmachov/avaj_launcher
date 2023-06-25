package avaj.launcher.airport_simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestUtils {
    public static void assertContains(String expected, String received, String testName) {
        if (!received.contains(expected)) {
            System.err.println(String.format("Test %s failed. Expected: \"%s\" \ndoesn't contain received: \"%s\"", testName, expected, received));
            System.exit(-1);
        }
        System.out.println("Test " + testName + " Succeeded.");
    }

    public static void assertContainsAll(List<String> expectedList, String received, String testName) {
        for (String expected: expectedList) {
            if (!received.contains(expected)) {
                System.err.println(String.format("Test %s failed. Expected: \"%s\" \ndoesn't contain received: \"%s\"", testName, expected, received));
                System.exit(-1);
            }
        }
        System.out.println("Test " + testName + " Succeeded.");
    }

    public static String getEnclosingMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static void assertAllKeyValuePairsFollowCondition(String source, String key, Predicate<Integer> valuePredicate, String testName) {
        List<Integer> values = new ArrayList<>();
        int index = 0;
        while (index != -1) {
            index = source.indexOf(key, index);
            if (index == -1) { break; }
            int numStart = source.indexOf('=', index) + 1;
            int numEndIndex = numStart;
            char c = source.charAt(numEndIndex);
            while (Character.isDigit(c)) {
                numEndIndex++;
                c = source.charAt(numEndIndex);
            }
            int value = Integer.parseInt(source.substring(numStart, numEndIndex));
            values.add(value);

            index += 1;
        }
        if (values.stream().filter(el -> !valuePredicate.test(el)).count() != 0) {
            System.err.println(String.format("Test %s failed. Key: %s, values: %s", testName, key, values));
            System.exit(-1);
        }
        System.out.println("Test " + testName + " Succeeded.");
    }
}
