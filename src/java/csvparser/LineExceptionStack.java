package csvparser;

import java.io.IOException;
import java.util.Stack;

public class LineExceptionStack extends IOException {
    Stack<LineException> m_oStack;

    public LineExceptionStack() {
        this("Some errors might have occurred while reading the file(s)");
    }

    public LineExceptionStack(String msg) {
        super(msg);
        m_oStack = new Stack<>();
    }

    public void push(LineException e) {
        m_oStack.push(e);
    }

    public LineException pop() {
        return m_oStack.pop();
    }

    public LineException peek() {
        return m_oStack.peek();
    }

    public boolean empty() {
        return m_oStack.empty();
    }

    public void push(LineExceptionStack e) {
        m_oStack.addAll(e.m_oStack);
    }

    public Stack<LineException> getStack() {
        return this.m_oStack;
    }
}