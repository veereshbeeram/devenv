package ${PACKAGE_NAME};

import com.google.common.collect.ImmutableList;

public class Test${NAME}Data {
    public static Object[] provideData() {
        final ImmutableList<String> input;
        final ImmutableList<String> output;
        // put all input data here. one input per add.
        input = new ImmutableList.Builder<String>()
                .build();

        // put all output data here. on output per add
        output = new ImmutableList.Builder<String>()
                .build();
        final Object[] returnArray = new Object[input.size()];
        for (int i = 0; i < input.size(); i++) {
            returnArray[i] = new Object[]{input.get(i), output.get(i)};
        }
        return returnArray;
    }
}
