public class caesar {
    public static void main(String[] args) {

        char operation = 'a';
        String message = "";
        int offset = -1;

        if (args.length == 4) {
            if (args[0].charAt(1) == 'e' || args[0].charAt(1) == 'd') {
                operation = args[0].charAt(1);
                message = args[1];
                offset = Integer.parseInt(args[3]);
            } else if (args[2].charAt(1) == 'e' || args[2].charAt(1) == 'd') {
                operation = args[2].charAt(1);
                message = args[3];
                offset = Integer.parseInt(args[1]);
            }

            if (operation == 'e' && !message.equals("") && offset > 0) {
                String result = encrypt(message, offset);
                System.out.println(result);
            } else if (operation == 'd' && !message.equals("") && offset > 0) {
                String result = decipher(message, offset);
                System.out.println(result);
            }else {
                System.out.println("Неправильные аргументы");
            }

        } else if (args.length < 4) {
            System.out.println("Слишком мало аргументов");
        } else {
            System.out.println("Слишком много аргументов");
        }


    }

    public static String decipher(String message, int offset) {
        return encrypt(message, 26 - (offset % 26));
    }

    public static String encrypt(String message, int offset) {

        char[] element = new char[message.length()];


        for (int i = 0; i < message.length(); i++) {

            if (Character.UnicodeBlock.of(message.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                if (isLetterUpperCase(message, i))
                    element[i] = (char) (((((int) (message.charAt(i))) - 'А' + offset) % 26) + 'А');
                else if (isLetterLowerCase(message, i))
                    element[i] = (char) (((((int) (message.charAt(i))) - 'а' + offset) % 26) + 'а');
                else
                    element[i] = message.charAt(i);
            } else {

                if (isLetterUpperCase(message, i))
                    element[i] = (char) (((((int) (message.charAt(i))) - 'A' + offset) % 26) + 'A');
                else if (isLetterLowerCase(message, i))
                    element[i] = (char) (((((int) (message.charAt(i))) - 'a' + offset) % 26) + 'a');
                else
                    element[i] = message.charAt(i);
            }
        }
        return String.copyValueOf(element);
    }

    public static boolean isLetterUpperCase(String message, int i) {
        return Character.isLetter(message.charAt(i)) && Character.isUpperCase(message.charAt(i));
    }

    public static boolean isLetterLowerCase(String message, int i) {
        return Character.isLetter(message.charAt(i)) && Character.isLowerCase(message.charAt(i));
    }
}