package org.example.model;

public class Ej5 {


    public static Stack removeDuplicatesAndSort(Stack original) {

        Stack copy = copyStack(original);

        Stack result = new DynamicStack();

        Stack temp = new DynamicStack();

        while (!copy.isEmpty()) {
            int current = copy.getTop();
            copy.remove();


            if (!contains(result, current)) {
                insertInOrder(result, temp, current);
            }
        }
        return result;
    }


    private static Stack copyStack(Stack s) {
        Stack aux = new DynamicStack();
        Stack aux2 = new DynamicStack();
        while (!s.isEmpty()) {
            int topVal = s.getTop();
            s.remove();
            aux.add(topVal);
            aux2.add(topVal);
        }

        while (!aux2.isEmpty()) {
            s.add(aux2.getTop());
            aux2.remove();
        }

        Stack result = new DynamicStack();
        while (!aux.isEmpty()) {
            result.add(aux.getTop());
            aux.remove();
        }
        return result;
    }


    private static boolean contains(Stack st, int value) {
        Stack cpy = copyStack(st);
        while (!cpy.isEmpty()) {
            if (cpy.getTop() == value) {
                return true;
            }
            cpy.remove();
        }
        return false;
    }

    private static void insertInOrder(Stack result, Stack temp, int value) {
        while (!result.isEmpty() && result.getTop() > value) {
            temp.add(result.getTop());
            result.remove();
        }
        result.add(value);
        while (!temp.isEmpty()) {
            result.add(temp.getTop());
            temp.remove();
        }
    }


    private static final String ALPHABET = "abcdefghijklmnñopqrstuvwxyz";

    public static Dictionary buildFrequencyDictionary(String text) {
        Dictionary dict = new DynamicDictionary();

        for (int i = 0; i < ALPHABET.length(); i++) {
            dict.add(i, 0);
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char lower = toLowerCase(c);
            int index = indexOf(ALPHABET, lower);
            if (index != -1) {
                int currentVal = dict.get(index);
                dict.remove(index);
                dict.add(index, currentVal + 1);
            }
        }
        return dict;
    }


    public static String caesarEncode(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char lower = toLowerCase(c);
            int idx = indexOf(ALPHABET, lower);
            if (idx == -1) {
                result += c;
            } else {
                int newIndex = (idx + shift) % ALPHABET.length();
                if (newIndex < 0) {
                    newIndex += ALPHABET.length();
                }
                char newChar = ALPHABET.charAt(newIndex);
                if (isUpperCase(c)) {
                    newChar = (char)(newChar - 32);
                }
                result += newChar;
            }
        }
        return result;
    }


    public static String caesarDecode(String text, int shift) {
        return caesarEncode(text, -shift);
    }


    private static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char)(c + 32);
        }
        return c;
    }

    private static boolean isUpperCase(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    private static int indexOf(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }


    public static boolean isBalanced(String text) {
        Stack stack = new DynamicStack();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isOpening(c)) {
                stack.add(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = (char) stack.getTop();
                stack.remove();
                if (!matches(top, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpening(char c) {
        return (c == '(' || c == '{' || c == '[');
    }

    private static boolean isClosing(char c) {
        return (c == ')' || c == '}' || c == ']');
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '{' && close == '}')
                || (open == '[' && close == ']');
    }


    public static void main(String[] args) {
        // ---------- ITEM 1: Pila sin duplicados y ordenada ----------
        System.out.println("ITEM 1: Pila sin duplicados y ordenada");
        Stack s = new DynamicStack();
        s.add(3);
        s.add(5);
        s.add(1);
        s.add(5);
        s.add(7);
        s.add(3);

        System.out.println("Pila original (tope primero):");
        printStack(s);

        Stack sorted = removeDuplicatesAndSort(s);
        System.out.println("Pila resultante (sin duplicados, orden ascendente, tope = mayor):");
        printStack(sorted);

        // ---------- ITEM 2: Diccionario de frecuencias + Cifrado/Descifrado César ----------
        System.out.println("\nITEM 2: Diccionario de frecuencias + Cifrado/Descifrado César");
        String text = "Hola Mundo Ñandú! Abcde... ABCDEFGHIJKLMNÑOPQRSTUVWXYZ??";
        System.out.println("Texto original:");
        System.out.println(text);

        Dictionary freqDict = buildFrequencyDictionary(text);
        System.out.println("\nFrecuencias (clave = índice en alfabeto, valor = ocurrencias):");
        printDictionary(freqDict);

        int shift = 3;
        String encoded = caesarEncode(text, shift);
        String decoded = caesarDecode(encoded, shift);
        System.out.println("\nTexto cifrado (shift = " + shift + "):");
        System.out.println(encoded);
        System.out.println("\nTexto descifrado:");
        System.out.println(decoded);

        // ---------- ITEM 3: Validación de paréntesis balanceados ----------
        System.out.println("\nITEM 3: Paréntesis balanceados");
        String balanced = "{[()]}";
        String unbalanced = "{[(])}";
        System.out.println(balanced + " -> " + (isBalanced(balanced) ? "BALANCEADO" : "NO BALANCEADO"));
        System.out.println(unbalanced + " -> " + (isBalanced(unbalanced) ? "BALANCEADO" : "NO BALANCEADO"));
    }


    private static void printStack(Stack st) {
        Stack copy = copyStack(st);
        while (!copy.isEmpty()) {
            System.out.println(copy.getTop());
            copy.remove();
        }
    }

    private static void printDictionary(Dictionary dict) {
        Set keys = dict.getKeys();
        while (!keys.isEmpty()) {
            int k = keys.choose();
            int v = dict.get(k);

            dict.remove(k);
            dict.add(k, v);
            System.out.println("Clave: " + k + " (letra: '" + ALPHABET.charAt(k) + "') -> " + v);
            keys.remove(k);
        }
    }
}
