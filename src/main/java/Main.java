public class Main {
    public static void main(String[] args) {
        String doc = "this, is, a, case, with, \"multi \"double \"quotes\"\"\"";

        System.out.println(CSVParser.Convert(doc));
    }
}


class CSVParser {
    private enum State {
        FIELD_START,
        QUOTE_LESS,
        QUOTE_START,
        QUOTE_IN_QUOTE
    }

    public static String Convert(String doc) {
        StringBuilder sb = new StringBuilder();
        State state = State.FIELD_START;

        for (int i = 0; i < doc.length(); ++i) {
            char ch = doc.charAt(i);

            switch (state) {
                case FIELD_START:
                    if (ch == '"') {
                        state = State.QUOTE_START;
                        continue;
                    }
                    state = State.QUOTE_LESS;
                    break;
                case QUOTE_LESS:
                    switch (ch) {
                        case ',':
                            ch = '|';  // fall through
                        case '\n':
                            state = State.FIELD_START;
                            break;
                        case '"':
                            Error(i, ch);
                    }
                    break;
                case QUOTE_START:
                    if (ch == '"') {
                        state = State.QUOTE_IN_QUOTE;
                        continue;
                    }
                    break;
                case QUOTE_IN_QUOTE:
                    switch (ch) {
                        case '"':
                            state = State.QUOTE_START;
                            break;
                        case ',':
                            state = State.FIELD_START;
                            ch = '|';
                            break;
                        default:
                            Error(i, ch);
                    }
                    break;
            }
            sb.append(ch);
        }

        return sb.toString();
    }

    private static void Error(int position, char ch) {
        throw new RuntimeException(
                "Invalid character " + ch + " at position " + position);
    }
}