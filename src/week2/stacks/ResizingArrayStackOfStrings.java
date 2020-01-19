package week2.stacks;

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public void push(String item) {
        if (N == s.length) {
            resize (2 * s.length);
        }
        s[N++] = item;
    }

    public String pop(){
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) {
            resize(s.length/2);
        }
        return N > 0? s[--N] : null;
    }

    private void resize(int capacity) {
        String[] array = new String[capacity];
        for (int i = 0; i < N; ++i) {
            array[i] = s[i];
        }
        s = array;
    }
}
