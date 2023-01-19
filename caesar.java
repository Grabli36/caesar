public class caesar {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid number of arguments");
            return;
        }

        char operation = 'a';
        int offset = -1;
        String message = "";

        if (args[0].charAt(1) == 'e' || args[0].charAt(1) == 'd' || args[2].charAt(1) == 'o') {
            operation = args[0].charAt(1);
            message = args[1];
            offset = Integer.parseInt(args[3]);
        } else if (args[2].charAt(1) == 'e' || args[2].charAt(1) == 'd' || args[0].charAt(1) == 'o') {
            operation = args[2].charAt(1);
            message = args[3];
            offset = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid arguments");
            return;
        }

        if (operation == 'e' && !message.equals("") && offset > 0) {
            String result = encrypt(message, offset);
            System.out.println(result);
        } else if (operation == 'd' && !message.equals("") && offset > 0) {
            String result = decipher(message, offset);
            System.out.println(result);
        } else {
            System.out.println("Invalid arguments");
        }
    }

    public static String encrypt(String message, int offset) {
        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!Character.isLetter(c)) {
                continue;
            }

            int base = 'A';
            if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                base = 'А';
            }

            if (Character.isUpperCase(c)) {
                c = (char) (((c - base + offset) % 26) + base);
            } else {
                base += 32;
                c = (char) (((c - base + offset) % 26) + base);
            }

            chars[i] = c;
        }
        return new String(chars);
    }

    public static String decipher(String message, int offset) {
        return encrypt(message, 26 - (offset % 26));
    }
}